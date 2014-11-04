package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.MyDoctorsView;

import javax.inject.Inject;

/**
 * Created by juanma on 29/10/14.
 */
public class MyDoctorsPresenterImpl implements MyDoctorsPresenter, MyDoctorsPresenter.OnFinishedListener {
    private MyDoctorsView view;

    @Inject
    public MyDoctorsPresenterImpl() {
    }

    @Override
    public void init(MyDoctorsView view) {
        this.view = view;
    }
}
