package net.jmreyes.tutela.ui.doctor.main.symptoms;

/**
 * Created by juanma on 8/11/14.
 */
public interface SymptomsInteractor {
    public void makeRequest(final SymptomsPresenter.OnFinishedListener listener);
}
