package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.api.MedicationService;
import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by juanma on 29/10/14.
 */
public class MyMedicationPresenterImpl implements MyMedicationPresenter {
    private MyMedicationView view;

    @Inject
    @Named("medicationService")
    MedicationService medicationService;

    @Inject
    public MyMedicationPresenterImpl() {
    }

    @Override
    public void init(MyMedicationView view) {
        this.view = view;
    }

    @Override
    public void requestMedication() {

    }
}
