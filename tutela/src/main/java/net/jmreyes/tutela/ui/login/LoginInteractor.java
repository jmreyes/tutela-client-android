package net.jmreyes.tutela.ui.login;

/**
 * Created by juanma on 6/11/14.
 */
public interface LoginInteractor {

    public void doLogin(String email, String password, final LoginPresenter.OnFinishedListener listener);
}
