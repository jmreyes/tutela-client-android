package net.jmreyes.tutela.ui.doctor.main.alerts;

import net.jmreyes.tutela.model.Alert;
import net.jmreyes.tutela.model.extra.DoctorStatus;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class AlertPresenterImpl implements AlertPresenter, AlertPresenter.OnFinishedListener {
    private AlertView view;
    private AlertInteractor alertInteractor;

    @Inject
    public AlertPresenterImpl() {
        alertInteractor = new AlertInteractorImpl();
    }

    @Override
    public void init(AlertView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        alertInteractor.makeRequest(this);
    }

    @Override
    public void onSuccess(List<Alert> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
