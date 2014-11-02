package net.jmreyes.tutela.ui.patient.main;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;
import net.jmreyes.tutela.ui.patient.main.presenter.*;
import net.jmreyes.tutela.ui.patient.main.view.MainView;

import javax.inject.Singleton;

/**
 * Created by juanma on 28/10/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                PatientMainActivity.class,
                CheckInFragment.class,
                MyAlarmsFragment.class,
                MyDoctorsFragment.class,
                MyMedicationFragment.class
        },
        complete = false,
        library = true
)
public class PatientMainModule {
    private MainView view;

    public PatientMainModule(MainView view) {
        this.view = view;
    }

    /**
     * Provide MainView
     */
    @Provides
    @Singleton
    public MainView provideMainView() {
        return view;
    }

    /**
     * Provide MainPresenter
     */
    @Provides
    @Singleton
    public PatientMainPresenter provideMainPresenter(MainView view) {
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
     * Provide MyAlarmsPresenter
     */
    @Provides
    @Singleton
    public MyAlarmsPresenter provideMyAlarmsPresenter() {
        return new MyAlarmsPresenterImpl();
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