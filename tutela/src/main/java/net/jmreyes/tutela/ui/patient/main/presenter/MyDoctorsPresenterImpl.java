package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.ui.patient.main.interactor.MyDoctorsInteractor;
import net.jmreyes.tutela.ui.patient.main.interactor.MyDoctorsInteractorImpl;
import net.jmreyes.tutela.ui.patient.main.view.MyDoctorsView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public class MyDoctorsPresenterImpl implements MyDoctorsPresenter, MyDoctorsPresenter.OnFinishedListener {
    private MyDoctorsView view;
    private MyDoctorsInteractor myDoctorsInteractor;

    @Inject
    public MyDoctorsPresenterImpl() {
        myDoctorsInteractor = new MyDoctorsInteractorImpl();
    }

    @Override
    public void init(MyDoctorsView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        myDoctorsInteractor.makeRequest(this);
    }

    @Override
    public void onSuccess(List<Doctor> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
