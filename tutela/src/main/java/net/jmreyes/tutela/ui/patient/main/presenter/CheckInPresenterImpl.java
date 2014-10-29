package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.CheckInView;

import javax.inject.Inject;

/**
 * Created by juanma on 29/10/14.
 */
public class CheckInPresenterImpl implements CheckInPresenter {
    private CheckInView view;

    @Inject
    public CheckInPresenterImpl() {
    }

    @Override
    public void init(CheckInView view) {
        this.view = view;
    }
}
