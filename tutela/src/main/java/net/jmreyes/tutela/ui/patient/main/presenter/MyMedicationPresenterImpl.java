package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.api.MedicationService;
import net.jmreyes.tutela.ui.patient.main.interactor.MyMedicationInteractor;
import net.jmreyes.tutela.ui.patient.main.interactor.MyMedicationInteractorImpl;
import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;

import javax.inject.Inject;
import javax.inject.Named;

import static net.jmreyes.tutela.ui.patient.main.presenter.MyMedicationPresenter.*;

/**
 * Created by juanma on 29/10/14.
 */
public class MyMedicationPresenterImpl implements MyMedicationPresenter, OnFinishedListener {
    private MyMedicationView view;
    private MyMedicationInteractor myMedicationInteractor;

    @Inject
    @Named("medicationService")
    MedicationService medicationService;

    @Inject
    public MyMedicationPresenterImpl() {
        myMedicationInteractor = new MyMedicationInteractorImpl();
    }

    @Override
    public void init(MyMedicationView view) {
        this.view = view;
    }

    @Override
    public void requestMyMedication() {
        myMedicationInteractor.requestMyMedication(this);
    }

    // OnFinishedListener methods, callbacks from the interactor.

    @Override
    public void requestMyMedicationFinished() {

    }
}
