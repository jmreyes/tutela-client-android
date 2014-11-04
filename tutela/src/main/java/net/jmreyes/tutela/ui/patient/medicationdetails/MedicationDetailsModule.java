package net.jmreyes.tutela.ui.patient.medicationdetails;

import dagger.Module;
import net.jmreyes.tutela.AppModule;

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
}
