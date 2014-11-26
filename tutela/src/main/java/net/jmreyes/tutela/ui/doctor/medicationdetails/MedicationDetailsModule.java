package net.jmreyes.tutela.ui.doctor.medicationdetails;

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
                MedicationDetailsActivity.class
        },
        complete = false,
        library = true
)
public class MedicationDetailsModule {
    private MedicationDetailsView view;

    public MedicationDetailsModule(MedicationDetailsView view) {
        this.view = view;
    }

    /**
     * Provide MedicationDetailsView
     */
    @Provides
    @Singleton
    public MedicationDetailsView provideMedicationDetailsView() {
        return view;
    }

    /**
     * Provide MedicationDetailsPresenter
     */
    @Provides
    @Singleton
    public MedicationDetailsPresenter provideMedicationDetailsPresenter(MedicationDetailsView view) {
        return new MedicationDetailsPresenterImpl(view);
    }
}
