package net.jmreyes.tutela.ui.login;


import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 6/11/14.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginPresenter.OnFinishedListener {
    private LoginView view;
    private LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        loginInteractor = new LoginInteractorImpl();
    }


    @Override
    public void doLogin(String email, String password) {
        loginInteractor.doLogin(email, password, this);
    }

    @Override
    public void getRole() {
        loginInteractor.getRole(this);
    }


    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onLoginSuccess(String accessToken, String email) {
        view.loginSuccess(accessToken, email);
    }

    @Override
    public void onGetRoleSuccess(String role) {
        view.getRoleSuccess(role);
    }

    @Override
    public void onError() {
        view.loginError();
    }

}
