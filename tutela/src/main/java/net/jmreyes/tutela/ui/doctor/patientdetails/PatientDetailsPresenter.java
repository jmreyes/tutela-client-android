package net.jmreyes.tutela.ui.doctor.patientdetails;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

/**
 * Created by juanma on 4/11/14.
 */
public interface PatientDetailsPresenter {
    public void makeRequest(String patientDetailsId);
    public void postDetails(String patientDetailsId, PatientDetails patientDetails);

    public static interface OnFinishedListener {
        public void onSuccess(PatientDetails patientDetails);
        public void onError();
        public void onPostDetailsSuccess();
        public void onPostDetailsError();
    }
}
