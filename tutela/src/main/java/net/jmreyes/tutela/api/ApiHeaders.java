package net.jmreyes.tutela.api;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import retrofit.RequestInterceptor;

import javax.inject.Inject;

/**
 * Created by juanma on 2/11/14.
 */
public class ApiHeaders implements RequestInterceptor {
    private Application application;

    @Inject
    public ApiHeaders(Application application) {
        this.application = application;
    }

    @Override
    public void intercept(RequestInterceptor.RequestFacade request) {
        AccountManager accountManager = AccountManager.get(application);
        Account[] accounts = accountManager.getAccountsByType(ApiManager.ACCOUNT_TYPE);
        if (accounts.length != 0) {
            String token =
                    accountManager.peekAuthToken(accounts[0], ApiManager.AUTHTOKEN_TYPE);
            if (token != null) {
                request.addHeader("Authorization", "Bearer " + token);
            }
        }
        request.addHeader("Accept", "application/javascript, application/json");
    }
}
