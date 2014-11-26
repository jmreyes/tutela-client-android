package net.jmreyes.tutela.ui.doctor.main.mypatients;

/**
 * Created by juanma on 8/11/14.
 */
public interface MyPatientsInteractor {
    public void makeRequest(final MyPatientsPresenter.OnFinishedListener listener);
}
