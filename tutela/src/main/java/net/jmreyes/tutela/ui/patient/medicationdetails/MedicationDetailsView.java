package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;

/**
 * Created by juanma on 3/11/14.
 */
public interface MedicationDetailsView {

    public void displayResults(Medication medication);
    public void displayError();
}
