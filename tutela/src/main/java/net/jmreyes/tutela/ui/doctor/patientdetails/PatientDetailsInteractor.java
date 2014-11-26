package net.jmreyes.tutela.ui.doctor.patientdetails;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

/**
 * Created by juanma on 4/11/14.
 */
public interface PatientDetailsInteractor {
    public void makeRequest(String patientDetailsId, final PatientDetailsPresenter.OnFinishedListener listener);

    public void postDetails(String patientDetailsId, PatientDetails patientDetails, final PatientDetailsPresenter.OnFinishedListener listener);
}
