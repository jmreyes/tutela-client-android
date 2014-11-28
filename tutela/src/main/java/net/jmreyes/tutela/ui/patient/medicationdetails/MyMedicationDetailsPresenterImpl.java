package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.model.extra.MyMedication;

import javax.inject.Inject;

/**
 * Created by juanma on 4/11/14.
 */
public class MyMedicationDetailsPresenterImpl implements MyMedicationDetailsPresenter, MyMedicationDetailsPresenter.OnFinishedListener {
    private MyMedicationDetailsView view;
    private MyMedicationDetailsInteractor myMedicationDetailsInteractor;

    @Inject
    public MyMedicationDetailsPresenterImpl(MyMedicationDetailsView view) {
        this.view = view;
        myMedicationDetailsInteractor = new MyMedicationDetailsInteractorImpl();
    }

    @Override
    public void makeRequest(String treatmentId, String medicationId) {
        myMedicationDetailsInteractor.makeRequest(treatmentId, medicationId, this);
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
