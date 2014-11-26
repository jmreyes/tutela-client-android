package net.jmreyes.tutela.ui.doctor.main.medication;

import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;

import javax.inject.Inject;
import java.util.List;

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

    @Override
    public void makeRequest() {
        medicationInteractor.makeRequest(this);
    }

    @Override
    public void onSuccess(List<Medication> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
