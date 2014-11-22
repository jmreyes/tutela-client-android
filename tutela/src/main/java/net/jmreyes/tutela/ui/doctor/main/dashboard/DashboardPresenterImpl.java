package net.jmreyes.tutela.ui.doctor.main.dashboard;

import javax.inject.Inject;

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
}
