package net.jmreyes.tutela.ui.doctor.main.dashboard;

import net.jmreyes.tutela.model.Alert;
import net.jmreyes.tutela.model.extra.DoctorStatus;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class DashboardPresenterImpl implements DashboardPresenter, DashboardPresenter.OnFinishedListener {
    private DashboardView view;
    private DashboardInteractor dashboardInteractor;

    @Inject
    public DashboardPresenterImpl() {
        dashboardInteractor = new DashboardInteractorImpl();
    }

    @Override
    public void init(DashboardView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        dashboardInteractor.makeRequest(this);
    }

    @Override
    public void onSuccess(List<Alert> results) {
        view.displayResults(results);
    }

    @Override
    public void onGetStatusSuccess(DoctorStatus status) {
        view.displayStatus(status);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
