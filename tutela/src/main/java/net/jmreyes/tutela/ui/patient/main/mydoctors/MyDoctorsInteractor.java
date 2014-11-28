package net.jmreyes.tutela.ui.patient.main.mydoctors;

import net.jmreyes.tutela.ui.patient.main.mydoctors.MyDoctorsPresenter;

/**
 * Created by juanma on 1/11/14.
 */
public interface MyDoctorsInteractor {
    public void makeRequest(final MyDoctorsPresenter.OnFinishedListener callback);

}
