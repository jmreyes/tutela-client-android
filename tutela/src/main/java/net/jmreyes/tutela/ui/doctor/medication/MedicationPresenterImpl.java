package net.jmreyes.tutela.ui.doctor.medication;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class MedicationPresenterImpl implements MedicationPresenter, MedicationPresenter.OnFinishedListener {
    private MedicationView view;
    private MedicationInteractor medicationInteractor;

    @Inject
    public MedicationPresenterImpl(MedicationView view) {
        this.view = view;
        medicationInteractor = new MedicationInteractorImpl();
    }
}
