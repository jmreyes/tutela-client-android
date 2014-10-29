package net.jmreyes.tutela;

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

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return app;
    }
}