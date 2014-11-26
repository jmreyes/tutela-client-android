package net.jmreyes.tutela.ui.doctor.main.medication;

import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface MedicationView {
    public void displayResults(List<Medication> results);
    public void displayError();
}
