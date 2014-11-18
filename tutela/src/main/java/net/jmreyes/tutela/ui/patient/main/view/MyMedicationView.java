package net.jmreyes.tutela.ui.patient.main.view;

import net.jmreyes.tutela.model.extra.MyMedication;

import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public interface MyMedicationView {
    public void displayResults(List<MyMedication> results);
    public void displayError();
}
