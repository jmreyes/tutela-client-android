package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.extra.MyMedication;

/**
 * Created by juanma on 4/11/14.
 */
public interface MedicationDetailsPresenter {
    public void makeRequest(String treatmentId, String medicationId);

    public static interface OnFinishedListener {
        public void onSuccess(MyMedication myMedication);
        public void onError();
    }
}
