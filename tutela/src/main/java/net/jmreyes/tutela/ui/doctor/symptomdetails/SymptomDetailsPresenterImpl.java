package net.jmreyes.tutela.ui.doctor.symptomdetails;

import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.extra.MyMedication;

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
        view.displayError();
    }
}
