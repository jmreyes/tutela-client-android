package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;

import javax.inject.Inject;

/**
 * Created by juanma on 29/10/14.
 */
public class MyMedicationPresenterImpl implements MyMedicationPresenter {
    private MyMedicationView view;

    @Inject
    public MyMedicationPresenterImpl() {
    }

    @Override
    public void init(MyMedicationView view) {
        this.view = view;
    }
}
