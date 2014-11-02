package net.jmreyes.tutela.ui.patient.main.interactor;

import net.jmreyes.tutela.ui.patient.main.presenter.MyMedicationPresenter;

/**
 * Created by juanma on 1/11/14.
 */
public interface MyMedicationInteractor {
    public void makeRequest(final MyMedicationPresenter.OnFinishedListener callback);
}
