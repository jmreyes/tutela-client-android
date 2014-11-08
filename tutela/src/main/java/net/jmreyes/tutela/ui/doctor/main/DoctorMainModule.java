package net.jmreyes.tutela.ui.doctor.main;

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
                DoctorMainActivity.class
        },
        complete = false,
        library = true
)
public class DoctorMainModule {

    private DoctorMainView view;

    public DoctorMainModule(DoctorMainView view) {
        this.view = view;
    }

    /**
     * Provide DoctorMainView
     */
    @Provides
    @Singleton
    public DoctorMainView provideDoctorMainView() {
        return view;
    }

    /**
     * Provide DoctorMainPresenter
     */
    @Provides
    @Singleton
    public DoctorMainPresenter provideDoctorMainPresenter(DoctorMainView view) {
        return new DoctorMainPresenterImpl(view);
    }
}
