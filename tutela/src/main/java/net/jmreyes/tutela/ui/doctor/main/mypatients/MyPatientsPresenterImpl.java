package net.jmreyes.tutela.ui.doctor.main.mypatients;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class MyPatientsPresenterImpl implements MyPatientsPresenter, MyPatientsPresenter.OnFinishedListener {
    private MyPatientsView view;
    private MyPatientsInteractor myPatientsInteractor;

    @Inject
    public MyPatientsPresenterImpl() {
        myPatientsInteractor = new MyPatientsInteractorImpl();
    }

    @Override
    public void init(MyPatientsView view) {
        this.view = view;
    }
}
