package net.jmreyes.tutela.ui.patient.main.interactor;

import net.jmreyes.tutela.ui.patient.main.presenter.MyDoctorsPresenter;

/**
 * Created by juanma on 1/11/14.
 */
public interface MyDoctorsInteractor {
    public void makeRequest(final MyDoctorsPresenter.OnFinishedListener callback);

}
