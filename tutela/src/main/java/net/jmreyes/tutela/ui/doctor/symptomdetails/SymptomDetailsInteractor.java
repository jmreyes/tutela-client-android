package net.jmreyes.tutela.ui.doctor.symptomdetails;

/**
 * Created by juanma on 4/11/14.
 */
public interface SymptomDetailsInteractor {
    public void makeRequest(String symptomId, final SymptomDetailsPresenter.OnFinishedListener listener);
}
