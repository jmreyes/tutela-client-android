package net.jmreyes.tutela.ui.doctor.treatmentdetails;

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
                TreatmentDetailsActivity.class
        },
        complete = false,
        library = true
)
public class TreatmentDetailsModule {
    private TreatmentDetailsView view;

    public TreatmentDetailsModule(TreatmentDetailsView view) {
        this.view = view;
    }

    /**
     * Provide TreatmentDetailsView
     */
    @Provides
    @Singleton
    public TreatmentDetailsView provideMedicationDetailsView() {
        return view;
    }

    /**
     * Provide TreatmentDetailsPresenter
     */
    @Provides
    @Singleton
    public TreatmentDetailsPresenter provideMedicationDetailsPresenter(TreatmentDetailsView view) {
        return new TreatmentDetailsPresenterImpl(view);
    }
}
