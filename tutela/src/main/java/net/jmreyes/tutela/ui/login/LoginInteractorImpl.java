package net.jmreyes.tutela.ui.login;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.LoginResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import javax.inject.Inject;

/**
 * Created by juanma on 6/11/14.
 */
public class LoginInteractorImpl implements LoginInteractor {


    @Override
    public void doLogin(final String email, String password, final LoginPresenter.OnFinishedListener listener) {

        String clientIdBase64 = Base64.encodeToString((ApiManager.CLIENT_ID + ":").getBytes(), Base64.DEFAULT);

        ApiManager.getAuthService().getAccessToken("Basic " + clientIdBase64, email, password,
                new Callback<LoginResponse>() {

                    @Override
                    public void success(LoginResponse loginResponse, Response response) {

                        String accessToken = loginResponse.getAccessToken();
                        listener.onSuccess(accessToken, email);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        listener.onError();
                    }
                });


    }


}
