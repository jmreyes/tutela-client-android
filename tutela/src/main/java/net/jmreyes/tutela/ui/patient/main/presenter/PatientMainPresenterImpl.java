package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.PatientMainView;

import javax.inject.Inject;

/**
 * Created by juanma on 28/10/14.
 */
public class PatientMainPresenterImpl implements PatientMainPresenter {
    private PatientMainView view;

    @Inject
    public PatientMainPresenterImpl(PatientMainView view) {
        this.view = view;
    }
}
