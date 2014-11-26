package net.jmreyes.tutela.ui.doctor.main.mypatients;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class MyPatientsPresenterImpl implements MyPatientsPresenter, MyPatientsPresenter.OnFinishedListener {
    private MyPatientsView view;
    private MyPatientsInteractor myPatientsInteractor;

    @Inject
    public MyPatientsPresenterImpl() {
        myPatientsInteractor = new MyPatientsInteractorImpl();
    }

    @Override
    public void init(MyPatientsView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        myPatientsInteractor.makeRequest(this);
    }

    @Override
    public void onSuccess(List<PatientDetails> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
