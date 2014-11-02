package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.MainView;

import javax.inject.Inject;

/**
 * Created by juanma on 28/10/14.
 */
public class PatientMainPresenterImpl implements PatientMainPresenter {
    private MainView view;

    @Inject
    public PatientMainPresenterImpl(MainView view) {
        this.view = view;
    }
}
