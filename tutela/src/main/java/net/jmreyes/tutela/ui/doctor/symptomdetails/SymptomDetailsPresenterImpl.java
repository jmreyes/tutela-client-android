package net.jmreyes.tutela.ui.doctor.symptomdetails;

import net.jmreyes.tutela.model.Symptom;

import javax.inject.Inject;

/**
 * Created by juanma on 4/11/14.
 */
public class SymptomDetailsPresenterImpl implements SymptomDetailsPresenter, SymptomDetailsPresenter.OnFinishedListener {
    private SymptomDetailsView view;
    private SymptomDetailsInteractor symptomDetailsInteractor;

    @Inject
    public SymptomDetailsPresenterImpl(SymptomDetailsView view) {
        this.view = view;
        symptomDetailsInteractor = new SymptomDetailsInteractorImpl();
    }

    @Override
    public void makeRequest(String symptomId) {
        symptomDetailsInteractor.makeRequest(symptomId, this);
    }

    @Override
    public void postDetails(Symptom symptom) {
        symptomDetailsInteractor.postDetails(symptom, this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(Symptom symptom) {
        view.displayResults(symptom);
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
