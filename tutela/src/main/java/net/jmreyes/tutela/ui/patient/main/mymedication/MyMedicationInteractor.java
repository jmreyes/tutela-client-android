package net.jmreyes.tutela.ui.patient.main.mymedication;

import net.jmreyes.tutela.ui.patient.main.mymedication.MyMedicationPresenter;

/**
 * Created by juanma on 1/11/14.
 */
public interface MyMedicationInteractor {
    public void makeRequest(final MyMedicationPresenter.OnFinishedListener callback);
}
