package net.jmreyes.tutela.ui.doctor.main.medication;

/**
 * Created by juanma on 8/11/14.
 */
public interface MedicationInteractor {
    public void makeRequest(final MedicationPresenter.OnFinishedListener listener);
}
