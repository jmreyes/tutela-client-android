package net.jmreyes.tutela.ui.doctor.main.medication;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class MedicationPresenterImpl implements MedicationPresenter, MedicationPresenter.OnFinishedListener {
    private MedicationView view;
    private MedicationInteractor medicationInteractor;

    @Inject
    public MedicationPresenterImpl() {
        medicationInteractor = new MedicationInteractorImpl();
    }

    @Override
    public void init(MedicationView view) {
        this.view = view;
    }
}
