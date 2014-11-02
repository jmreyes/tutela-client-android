package net.jmreyes.tutela;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import dagger.Module;
import dagger.Provides;

/**
 * Created by juanma on 28/10/14.
 */
@Module(
        injects = {
                App.class
        },
        library = true
)
public class AppModule {
    private App app;
    private AccountManager am;

    public AppModule(App app) {
        this.app = app;
        this.am = AccountManager.get(app);
    }

    @Provides
    public Application provideApplication() {
        return app;
    }

    @Provides
    public AccountManager provideAccountManager() {
        return am;
    }
}