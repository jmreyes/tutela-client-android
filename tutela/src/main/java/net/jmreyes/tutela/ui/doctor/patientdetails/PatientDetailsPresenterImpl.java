package net.jmreyes.tutela.ui.doctor.patientdetails;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

import javax.inject.Inject;

/**
 * Created by juanma on 4/11/14.
 */
public class PatientDetailsPresenterImpl implements PatientDetailsPresenter, PatientDetailsPresenter.OnFinishedListener {
    private PatientDetailsView view;
    private PatientDetailsInteractor patientDetailsInteractor;

    @Inject
    public PatientDetailsPresenterImpl(PatientDetailsView view) {
        this.view = view;
        patientDetailsInteractor = new PatientDetailsInteractorImpl();
    }

    @Override
    public void makeRequest(String patientDetailsId) {
        patientDetailsInteractor.makeRequest(patientDetailsId, this);
    }

    @Override
    public void postDetails(String patientDetailsId, PatientDetails patientDetails) {
        patientDetailsInteractor.postDetails(patientDetailsId, patientDetails, this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(PatientDetails patientDetails) {
        view.displayResults(patientDetails);
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
