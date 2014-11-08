package net.jmreyes.tutela.ui.patient.doctordetails;

import net.jmreyes.tutela.model.Doctor;

/**
 * Created by juanma on 3/11/14.
 */
public interface DoctorDetailsView {

    public void displayResults(Doctor doctor);
    public void displayError();
}
