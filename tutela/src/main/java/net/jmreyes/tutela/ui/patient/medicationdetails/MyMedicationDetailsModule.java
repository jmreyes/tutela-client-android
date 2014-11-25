package net.jmreyes.tutela.ui.patient.medicationdetails;

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
                MyMedicationDetailsActivity.class
        },
        complete = false,
        library = true
)
public class MyMedicationDetailsModule {
    private MyMedicationDetailsView view;

    public MyMedicationDetailsModule(MyMedicationDetailsView view) {
        this.view = view;
    }

    /**
     * Provide MyMedicationDetailsView
     */
    @Provides
    @Singleton
    public MyMedicationDetailsView provideMedicationDetailsView() {
        return view;
    }

    /**
     * Provide MyMedicationDetailsPresenter
     */
    @Provides
    @Singleton
    public MyMedicationDetailsPresenter provideMedicationDetailsPresenter(MyMedicationDetailsView view) {
        return new MyMedicationDetailsPresenterImpl(view);
    }
}
