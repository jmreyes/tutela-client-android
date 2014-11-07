package net.jmreyes.tutela.api;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Application;
import android.util.Log;
import com.squareup.okhttp.*;

import javax.inject.Inject;
import java.io.IOException;
import java.net.Proxy;

/**
 * Created by juanma on 2/11/14.
 */
public class ApiAuthenticator implements Authenticator {
    AccountManager accountManager;
    Application application;

    @Inject
    public ApiAuthenticator(Application application, AccountManager accountManager) {
        this.application = application;
        this.accountManager = accountManager;
    }

    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        // Do not try to authenticate oauth related endpoints
        if (response.request().url().getPath().startsWith("/oauth")) return null;

        for (Challenge challenge : response.challenges()) {
            if (challenge.getScheme().equals("Bearer")) {
                Account[] accounts = accountManager.getAccountsByType(ApiManager.ACCOUNT_TYPE);
                if (accounts.length != 0) {
                    String oldToken = accountManager.peekAuthToken(accounts[0],
                            ApiManager.AUTHTOKEN_TYPE);
                    if (oldToken != null) {
                        Log.d("ApiAuthenticator","invalidating auth token");
                        accountManager.invalidateAuthToken(ApiManager.ACCOUNT_TYPE, oldToken);
                    }
                    try {
                        Log.d("ApiAuthenticator","calling accountManager.blockingGetAuthToken");
                        String token = accountManager.blockingGetAuthToken(accounts[0],
                                ApiManager.AUTHTOKEN_TYPE, false);

                        if (token == null) {
                            accountManager.removeAccount(accounts[0], null, null);
                        } else {
                            return response.request().newBuilder().header("Authorization", "Bearer " + token).build();
                        }
                    } catch (OperationCanceledException e) {
                        e.printStackTrace();
                    } catch (AuthenticatorException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }
}
