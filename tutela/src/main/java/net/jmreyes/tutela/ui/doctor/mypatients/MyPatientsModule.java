package net.jmreyes.tutela.ui.doctor.mypatients;

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
                MyPatientsActivity.class
        },
        complete = false,
        library = true
)
public class MyPatientsModule {

    private MyPatientsView view;

    public MyPatientsModule(MyPatientsView view) {
        this.view = view;
    }

    /**
     * Provide MyPatientsView
     */
    @Provides
    @Singleton
    public MyPatientsView provideMyPatientsView() {
        return view;
    }

    /**
     * Provide MyPatientsPresenter
     */
    @Provides
    @Singleton
    public MyPatientsPresenter provideMyPatientsPresenter(MyPatientsView view) {
        return new MyPatientsPresenterImpl(view);
    }
}
