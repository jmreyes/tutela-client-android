package net.jmreyes.tutela.ui.login;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

/**
 * Created by juanma on 6/11/14.
 */
public interface LoginPresenter {

    public void doLogin(String email, String password);

    public void getRole();

    public static interface OnFinishedListener {
        public void onLoginSuccess(String accessToken, String email);
        public void onGetRoleSuccess(String role);
        public void onError();
    }

}
