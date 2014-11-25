package net.jmreyes.tutela.ui.patient.medicationdetails;

/**
 * Created by juanma on 4/11/14.
 */
public interface MyMedicationDetailsInteractor {
    public void makeRequest(String treatmentId, String medicationId, final MyMedicationDetailsPresenter.OnFinishedListener callback);
}
