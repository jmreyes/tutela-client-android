package net.jmreyes.tutela.ui.doctor.medicationdetails;

import net.jmreyes.tutela.model.Medication;

/**
 * Created by juanma on 4/11/14.
 */
public interface MedicationDetailsPresenter {
    public void makeRequest(String medicationId);
    public void postDetails(Medication medication);

    public static interface OnFinishedListener {
        public void onSuccess(Medication symptom);
        public void onError();
        public void onPostDetailsSuccess();
        public void onPostDetailsError();
    }
}
