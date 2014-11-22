package net.jmreyes.tutela.ui.patient.main;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;
import net.jmreyes.tutela.ui.patient.main.presenter.*;
import net.jmreyes.tutela.ui.patient.main.view.PatientMainView;

import javax.inject.Singleton;

/**
 * Created by juanma on 28/10/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                PatientMainActivity.class,
                CheckInFragment.class,
                MyDoctorsFragment.class,
                MyMedicationFragment.class
        },
        complete = false,
        library = true
)
public class PatientMainModule {
    private PatientMainView view;

    public PatientMainModule(PatientMainView view) {
        this.view = view;
    }

    /**
     * Provide PatientMainView
     */
    @Provides
    @Singleton
    public PatientMainView providePatientMainView() {
        return view;
    }

    /**
     * Provide PatientMainPresenter
     */
    @Provides
    @Singleton
    public PatientMainPresenter providePatientMainPresenter(PatientMainView view) {
        return new PatientMainPresenterImpl(view);
    }

    /**
     * Provide CheckInPresenter
     */
    @Provides
    @Singleton
    public CheckInPresenter provideCheckInPresenter() {
        return new CheckInPresenterImpl();
    }

    /**
     * Provide MyDoctorsPresenter
     */
    @Provides
    @Singleton
    public MyDoctorsPresenter provideMyDoctorsPresenter() {
        return new MyDoctorsPresenterImpl();
    }

    /**
     * Provide MyMedicationPresenter
     */
    @Provides
    @Singleton
    public MyMedicationPresenter provideMyMedicationPresenter() {
        return new MyMedicationPresenterImpl();
    }

}
