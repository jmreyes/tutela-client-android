package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;

/**
 * Created by juanma on 4/11/14.
 */
public interface MedicationDetailsPresenter {
    public void makeRequest(String medicationId);

    public static interface OnFinishedListener {
        public void onSuccess(Medication medication);
        public void onError();
    }
}
