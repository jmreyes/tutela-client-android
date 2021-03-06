package net.jmreyes.tutela.ui.patient.main.mydoctors;

import net.jmreyes.tutela.model.extra.MyDoctor;

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
    public void onSuccess(List<MyDoctor> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
