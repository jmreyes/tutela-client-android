package net.jmreyes.tutela.ui.patient.medicationdetails;

/**
 * Created by juanma on 4/11/14.
 */
public interface MedicationDetailsInteractor {
    public void makeRequest(String medicationId, final MedicationDetailsPresenter.OnFinishedListener callback);
}
