package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.extra.MyMedication;

import javax.inject.Inject;

/**
 * Created by juanma on 4/11/14.
 */
public class MedicationDetailsPresenterImpl implements MedicationDetailsPresenter, MedicationDetailsPresenter.OnFinishedListener {
    private MedicationDetailsView view;
    private MedicationDetailsInteractor medicationDetailsInteractor;

    @Inject
    public MedicationDetailsPresenterImpl(MedicationDetailsView view) {
        this.view = view;
        medicationDetailsInteractor = new MedicationDetailsInteractorImpl();
    }

    @Override
    public void makeRequest(String treatmentId, String medicationId) {
        medicationDetailsInteractor.makeRequest(treatmentId, medicationId, this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(MyMedication myMedication) {
        view.displayResults(myMedication);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
