package net.jmreyes.tutela.ui.login;

import android.util.Base64;
import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.extra.LoginResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
                        listener.onLoginSuccess(accessToken, email);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        listener.onError();
                    }
                });


    }

    @Override
    public void getRole(final LoginPresenter.OnFinishedListener listener) {
        ApiManager.getUserService().getRole(
                new Callback<String>() {
                    @Override
                    public void success(String role, Response response) {

                        listener.onGetRoleSuccess(role);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        listener.onError();
                    }
                }
        );
    }


}
