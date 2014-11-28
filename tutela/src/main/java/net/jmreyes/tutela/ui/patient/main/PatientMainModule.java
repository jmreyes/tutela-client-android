package net.jmreyes.tutela.ui.patient.main;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;
import net.jmreyes.tutela.ui.patient.main.checkin.CheckInFragment;
import net.jmreyes.tutela.ui.patient.main.checkin.CheckInPresenter;
import net.jmreyes.tutela.ui.patient.main.checkin.CheckInPresenterImpl;
import net.jmreyes.tutela.ui.patient.main.mydoctors.MyDoctorsFragment;
import net.jmreyes.tutela.ui.patient.main.mydoctors.MyDoctorsPresenter;
import net.jmreyes.tutela.ui.patient.main.mydoctors.MyDoctorsPresenterImpl;
import net.jmreyes.tutela.ui.patient.main.mymedication.MyMedicationFragment;
import net.jmreyes.tutela.ui.patient.main.mymedication.MyMedicationPresenter;
import net.jmreyes.tutela.ui.patient.main.mymedication.MyMedicationPresenterImpl;
import net.jmreyes.tutela.ui.patient.main.myreminders.MyRemindersFragment;

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
                MyMedicationFragment.class,
                MyRemindersFragment.class
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
