package net.jmreyes.tutela.ui.login;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;

import javax.inject.Singleton;

/**
 * Created by juanma on 6/11/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                LoginActivity.class
        },
        complete = false,
        library = true
)
public class LoginModule {
    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    /**
     * Provide LoginView
     */
    @Provides
    @Singleton
    public LoginView provideLoginView() {
        return view;
    }

    /**
     * Provide LoginPresenter
     */
    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter(LoginView view) {
        return new LoginPresenterImpl(view);
    }

}
