package net.jmreyes.tutela.ui.doctor.symptomdetails;

import net.jmreyes.tutela.model.Symptom;

/**
 * Created by juanma on 4/11/14.
 */
public interface SymptomDetailsInteractor {
    public void makeRequest(String symptomId, final SymptomDetailsPresenter.OnFinishedListener listener);

    public void postDetails(Symptom symptom, final SymptomDetailsPresenter.OnFinishedListener listener);
}
