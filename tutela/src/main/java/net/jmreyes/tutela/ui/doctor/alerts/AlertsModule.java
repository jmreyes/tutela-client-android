package net.jmreyes.tutela.ui.doctor.alerts;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;

import javax.inject.Singleton;

/**
 * Created by juanma on 8/11/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                AlertsActivity.class
        },
        complete = false,
        library = true
)
public class AlertsModule {

    private AlertsView view;

    public AlertsModule(AlertsView view) {
        this.view = view;
    }

    /**
     * Provide AlertsView
     */
    @Provides
    @Singleton
    public AlertsView provideAlertsView() {
        return view;
    }

    /**
     * Provide AlertsPresenter
     */
    @Provides
    @Singleton
    public AlertsPresenter provideAlertsPresenter(AlertsView view) {
        return new AlertsPresenterImpl(view);
    }
}
