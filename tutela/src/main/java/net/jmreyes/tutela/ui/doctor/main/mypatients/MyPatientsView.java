package net.jmreyes.tutela.ui.doctor.main.mypatients;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface MyPatientsView {
    public void displayResults(List<PatientDetails> results);
    public void displayError();
    public void loadTreatmentDetails(String treatmentId);
}
