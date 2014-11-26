package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.model.Symptom;

/**
 * Created by juanma on 4/11/14.
 */
public interface SymptomDetailsInteractor {
    public void makeRequest(String symptomId, final TreatmentDetailsPresenter.OnFinishedListener listener);

    public void postDetails(Symptom symptom, final TreatmentDetailsPresenter.OnFinishedListener listener);
}
