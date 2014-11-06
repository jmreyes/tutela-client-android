package net.jmreyes.tutela.ui.login;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by juanma on 6/11/14.
 */
public interface LoginView {
    public void loginSuccess(String accessToken, String email);
    public void loginError();
}
