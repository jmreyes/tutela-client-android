package net.jmreyes.tutela.ui.doctor.main.symptoms;

import net.jmreyes.tutela.model.Symptom;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface SymptomsView {
    public void displayResults(List<Symptom> results);
    public void displayError();
}
