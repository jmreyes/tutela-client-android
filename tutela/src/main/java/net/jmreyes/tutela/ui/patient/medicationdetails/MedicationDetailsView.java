package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.extra.MyMedication;

/**
 * Created by juanma on 3/11/14.
 */
public interface MedicationDetailsView {

    public void displayResults(MyMedication myMedication);
    public void displayError();
}
