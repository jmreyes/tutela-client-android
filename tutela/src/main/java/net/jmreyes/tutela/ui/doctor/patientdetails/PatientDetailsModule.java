package net.jmreyes.tutela.ui.doctor.patientdetails;

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
                PatientDetailsActivity.class
        },
        complete = false,
        library = true
)
public class PatientDetailsModule {
    private PatientDetailsView view;

    public PatientDetailsModule(PatientDetailsView view) {
        this.view = view;
    }

    /**
     * Provide SymptomDetailsView
     */
    @Provides
    @Singleton
    public PatientDetailsView provideMedicationDetailsView() {
        return view;
    }

    /**
     * Provide SymptomDetailsPresenter
     */
    @Provides
    @Singleton
    public PatientDetailsPresenter provideMedicationDetailsPresenter(PatientDetailsView view) {
        return new PatientDetailsPresenterImpl(view);
    }
}
