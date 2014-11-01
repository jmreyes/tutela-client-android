package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.MyAlarmsView;

import javax.inject.Inject;

/**
 * Created by juanma on 29/10/14.
 */
public class MyAlarmsPresenterImpl implements MyAlarmsPresenter, MyAlarmsPresenter.OnFinishedListener {
    private MyAlarmsView view;

    @Inject
    public MyAlarmsPresenterImpl() {
    }

    @Override
    public void init(MyAlarmsView view) {
        this.view = view;
    }
}
