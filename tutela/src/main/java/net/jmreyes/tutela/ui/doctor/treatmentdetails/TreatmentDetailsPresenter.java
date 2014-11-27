package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.model.CheckIn;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.Treatment;

import java.util.ArrayList;

/**
 * Created by juanma on 4/11/14.
 */
public interface TreatmentDetailsPresenter {
    public void makeRequest(String treatmentId);
    public void postDetails(Treatment treatment);

    public static interface OnFinishedListener {
        public void onSuccess(Treatment treatment, ArrayList<CheckIn> checkIns,
                              ArrayList<Medication> medication, ArrayList<Symptom> symptoms);
        public void onError();
        public void onPostDetailsSuccess();
        public void onPostDetailsError();
    }
}
