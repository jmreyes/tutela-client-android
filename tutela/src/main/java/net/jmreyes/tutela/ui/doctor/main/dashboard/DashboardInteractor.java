package net.jmreyes.tutela.ui.doctor.main.dashboard;

/**
 * Created by juanma on 8/11/14.
 */
public interface DashboardInteractor {
    public void makeRequest(final DashboardPresenter.OnFinishedListener callback);
}
