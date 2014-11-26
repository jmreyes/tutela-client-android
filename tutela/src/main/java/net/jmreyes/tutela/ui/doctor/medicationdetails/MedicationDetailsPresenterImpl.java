package net.jmreyes.tutela.ui.doctor.medicationdetails;

import net.jmreyes.tutela.model.Medication;

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
    public void makeRequest(String symptomId) {
        medicationDetailsInteractor.makeRequest(symptomId, this);
    }

    @Override
    public void postDetails(Medication medication) {
        medicationDetailsInteractor.postDetails(medication, this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(Medication medication) {
        view.displayResults(medication);
    }

    @Override
    public void onError() {
        view.displayResultsError();
    }

    @Override
    public void onPostDetailsSuccess() {
        view.saveDetailsSuccess();
    }

    @Override
    public void onPostDetailsError() {
        view.saveDetailsError();
    }
}
