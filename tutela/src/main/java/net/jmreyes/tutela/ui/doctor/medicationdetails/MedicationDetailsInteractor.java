package net.jmreyes.tutela.ui.doctor.medicationdetails;

import net.jmreyes.tutela.model.Medication;

/**
 * Created by juanma on 4/11/14.
 */
public interface MedicationDetailsInteractor {
    public void makeRequest(String medicationId, final MedicationDetailsPresenter.OnFinishedListener listener);

    public void postDetails(Medication medication, final MedicationDetailsPresenter.OnFinishedListener listener);
}
