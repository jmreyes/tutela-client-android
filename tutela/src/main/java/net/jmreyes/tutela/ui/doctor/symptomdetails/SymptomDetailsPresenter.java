package net.jmreyes.tutela.ui.doctor.symptomdetails;

import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.extra.MyMedication;

/**
 * Created by juanma on 4/11/14.
 */
public interface SymptomDetailsPresenter {
    public void makeRequest(String symptomId);

    public static interface OnFinishedListener {
        public void onSuccess(Symptom symptom);
        public void onError();
    }
}
