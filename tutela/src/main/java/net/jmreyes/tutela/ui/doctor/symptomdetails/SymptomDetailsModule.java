package net.jmreyes.tutela.ui.doctor.symptomdetails;

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
                SymptomDetailsActivity.class
        },
        complete = false,
        library = true
)
public class SymptomDetailsModule {
    private SymptomDetailsView view;

    public SymptomDetailsModule(SymptomDetailsView view) {
        this.view = view;
    }

    /**
     * Provide SymptomDetailsView
     */
    @Provides
    @Singleton
    public SymptomDetailsView provideMedicationDetailsView() {
        return view;
    }

    /**
     * Provide SymptomDetailsPresenter
     */
    @Provides
    @Singleton
    public SymptomDetailsPresenter provideMedicationDetailsPresenter(SymptomDetailsView view) {
        return new SymptomDetailsPresenterImpl(view);
    }
}
