package net.jmreyes.tutela.ui.patient.doctordetails;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;

import javax.inject.Singleton;

/**
 * Created by juanma on 3/11/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                DoctorDetailsActivity.class
        },
        complete = false,
        library = true
)
public class DoctorDetailsModule {
    private DoctorDetailsView view;

    public DoctorDetailsModule(DoctorDetailsView view) {
        this.view = view;
    }

    /**
     * Provide DoctorDetailsView
     */
    @Provides
    @Singleton
    public DoctorDetailsView provideDoctorDetailsView() {
        return view;
    }

    /**
     * Provide DoctorDetailsPresenter
     */
    @Provides
    @Singleton
    public DoctorDetailsPresenter provideDoctorDetailsPresenter(DoctorDetailsView view) {
        return new DoctorDetailsPresenterImpl(view);
    }
}
