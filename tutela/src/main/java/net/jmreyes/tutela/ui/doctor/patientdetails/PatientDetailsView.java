package net.jmreyes.tutela.ui.doctor.patientdetails;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

/**
 * Created by juanma on 3/11/14.
 */
public interface PatientDetailsView {
    public void displayResults(PatientDetails patientDetails);
    public void displayResultsError();
    public void saveDetailsSuccess();
    public void saveDetailsError();
}
