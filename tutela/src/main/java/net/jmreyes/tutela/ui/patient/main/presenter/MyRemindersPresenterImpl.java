package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.MyRemindersView;

import javax.inject.Inject;

/**
 * Created by juanma on 29/10/14.
 */
public class MyRemindersPresenterImpl implements MyRemindersPresenter {
    private MyRemindersView view;

    @Inject
    public MyRemindersPresenterImpl() {
    }

    @Override
    public void init(MyRemindersView view) {
        this.view = view;
    }

    @Override
    public void getAlarms() {
        view.displayResults(null);
    }

}
