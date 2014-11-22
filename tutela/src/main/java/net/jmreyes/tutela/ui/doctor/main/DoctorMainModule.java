package net.jmreyes.tutela.ui.doctor.main;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;
import net.jmreyes.tutela.ui.doctor.alerts.AlertsFragment;
import net.jmreyes.tutela.ui.doctor.alerts.AlertsPresenter;
import net.jmreyes.tutela.ui.doctor.alerts.AlertsPresenterImpl;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardFragment;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardPresenter;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardPresenterImpl;
import net.jmreyes.tutela.ui.doctor.main.medication.MedicationFragment;
import net.jmreyes.tutela.ui.doctor.main.medication.MedicationPresenter;
import net.jmreyes.tutela.ui.doctor.main.medication.MedicationPresenterImpl;
import net.jmreyes.tutela.ui.doctor.main.mypatients.MyPatientsFragment;
import net.jmreyes.tutela.ui.doctor.main.mypatients.MyPatientsPresenter;
import net.jmreyes.tutela.ui.doctor.main.mypatients.MyPatientsPresenterImpl;
import net.jmreyes.tutela.ui.doctor.main.symptoms.SymptomsFragment;
import net.jmreyes.tutela.ui.doctor.main.symptoms.SymptomsPresenter;
import net.jmreyes.tutela.ui.doctor.main.symptoms.SymptomsPresenterImpl;

import javax.inject.Singleton;

/**
 * Created by juanma on 8/11/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                DoctorMainActivity.class,
                DashboardFragment.class,
                MedicationFragment.class,
                MyPatientsFragment.class,
                SymptomsFragment.class,
                AlertsFragment.class
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

    /**
     * Provide DashboardPresenter
     */
    @Provides
    @Singleton
    public DashboardPresenter provideDashboardPresenter() {
        return new DashboardPresenterImpl();
    }

    /**
     * Provide MedicationPresenter
     */
    @Provides
    @Singleton
    public MedicationPresenter provideMedicationPresenter() {
        return new MedicationPresenterImpl();
    }

    /**
     * Provide MyPatientsPresenter
     */
    @Provides
    @Singleton
    public MyPatientsPresenter provideMyPatientsPresenter() {
        return new MyPatientsPresenterImpl();
    }

    /**
     * Provide SymptomsPresenter
     */
    @Provides
    @Singleton
    public SymptomsPresenter provideSymptomsPresenter() {
        return new SymptomsPresenterImpl();
    }

    /**
     * Provide AlertsPresenter
     */
    @Provides
    @Singleton
    public AlertsPresenter provideAlertsPresenter() {
        return new AlertsPresenterImpl();
    }
}
