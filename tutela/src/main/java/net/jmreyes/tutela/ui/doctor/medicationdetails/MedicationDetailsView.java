package net.jmreyes.tutela.ui.doctor.medicationdetails;

import net.jmreyes.tutela.model.Medication;

/**
 * Created by juanma on 3/11/14.
 */
public interface MedicationDetailsView {
    public void displayResults(Medication medication);
    public void displayResultsError();
    public void saveDetailsSuccess();
    public void saveDetailsError();
}
