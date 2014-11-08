package net.jmreyes.tutela.ui.patient.main.view;

import net.jmreyes.tutela.model.Doctor;

import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public interface MyDoctorsView {
    public void displayResults(List<Doctor> results);
    public void displayError();
}
