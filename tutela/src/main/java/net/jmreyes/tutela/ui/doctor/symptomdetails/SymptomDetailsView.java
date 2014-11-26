package net.jmreyes.tutela.ui.doctor.symptomdetails;

import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.extra.MyMedication;

/**
 * Created by juanma on 3/11/14.
 */
public interface SymptomDetailsView {
    public void displayResults(Symptom symptom);
    public void displayResultsError();
    public void saveDetailsSuccess();
    public void saveDetailsError();
}
