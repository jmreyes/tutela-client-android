package net.jmreyes.tutela.ui.doctor.alerts;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class AlertsPresenterImpl implements AlertsPresenter, AlertsPresenter.OnFinishedListener {
    private AlertsView view;
    private AlertsInteractor alertsInteractor;

    @Inject
    public AlertsPresenterImpl(AlertsView view) {
        this.view = view;
        alertsInteractor = new AlertsInteractorImpl();
    }
}
