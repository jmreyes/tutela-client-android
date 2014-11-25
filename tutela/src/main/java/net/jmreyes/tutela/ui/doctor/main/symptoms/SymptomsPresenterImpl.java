package net.jmreyes.tutela.ui.doctor.main.symptoms;

import net.jmreyes.tutela.model.Symptom;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class SymptomsPresenterImpl implements SymptomsPresenter, SymptomsPresenter.OnFinishedListener {
    private SymptomsView view;
    private SymptomsInteractor symptomsInteractor;

    @Inject
    public SymptomsPresenterImpl() {
        symptomsInteractor = new SymptomsInteractorImpl();
    }

    @Override
    public void init(SymptomsView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        symptomsInteractor.makeRequest(this);
    }

    @Override
    public void onSuccess(List<Symptom> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
