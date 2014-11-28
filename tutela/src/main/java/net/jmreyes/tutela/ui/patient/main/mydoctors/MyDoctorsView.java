package net.jmreyes.tutela.ui.patient.main.mydoctors;

import net.jmreyes.tutela.model.extra.MyDoctor;

import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public interface MyDoctorsView {
    public void displayResults(List<MyDoctor> results);
    public void displayError();
}
