package net.jmreyes.tutela.ui.doctor.main.alerts;

/**
 * Created by juanma on 8/11/14.
 */
public interface AlertInteractor {
    public void makeRequest(final AlertPresenter.OnFinishedListener callback);
}
