package net.jmreyes.tutela.ui.doctor.mypatients;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class MyPatientsPresenterImpl implements MyPatientsPresenter, MyPatientsPresenter.OnFinishedListener {
    private MyPatientsView view;
    private MyPatientsInteractor myPatientsInteractor;

    @Inject
    public MyPatientsPresenterImpl(MyPatientsView view) {
        this.view = view;
        myPatientsInteractor = new MyPatientsInteractorImpl();
    }
}
