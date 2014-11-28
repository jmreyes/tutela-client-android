package net.jmreyes.tutela.ui.doctor.main.alerts;

import net.jmreyes.tutela.model.Alert;
import net.jmreyes.tutela.model.extra.DoctorStatus;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface AlertView {
    public void displayResults(List<Alert> results);

    public void displayError();

    public void loadTreatmentDetails(String treatmentId);

    public void loadPatientDetails(String patientId);
}
