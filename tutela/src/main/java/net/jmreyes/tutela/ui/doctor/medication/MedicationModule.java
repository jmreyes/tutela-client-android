package net.jmreyes.tutela.ui.doctor.medication;

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
                MedicationActivity.class
        },
        complete = false,
        library = true
)
public class MedicationModule {

    private MedicationView view;

    public MedicationModule(MedicationView view) {
        this.view = view;
    }

    /**
     * Provide MedicationView
     */
    @Provides
    @Singleton
    public MedicationView provideMedicationView() {
        return view;
    }

    /**
     * Provide MedicationPresenter
     */
    @Provides
    @Singleton
    public MedicationPresenter provideMedicationPresenter(MedicationView view) {
        return new MedicationPresenterImpl(view);
    }
}
