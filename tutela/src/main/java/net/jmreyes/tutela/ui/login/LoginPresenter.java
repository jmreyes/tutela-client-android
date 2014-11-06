package net.jmreyes.tutela.ui.login;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by juanma on 6/11/14.
 */
public interface LoginPresenter {

    public void doLogin(String email, String password);

    public static interface OnFinishedListener {
        public void onSuccess(String accessToken, String email);
        public void onError();
    }

}
