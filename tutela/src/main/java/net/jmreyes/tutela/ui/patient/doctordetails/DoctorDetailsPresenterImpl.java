package net.jmreyes.tutela.ui.patient.doctordetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;

import javax.inject.Inject;

/**
 * Created by juanma on 4/11/14.
 */
public class DoctorDetailsPresenterImpl implements DoctorDetailsPresenter, DoctorDetailsPresenter.OnFinishedListener {
    private DoctorDetailsView view;
    private DoctorDetailsInteractor doctorDetailsInteractor;

    @Inject
    public DoctorDetailsPresenterImpl(DoctorDetailsView view) {
        this.view = view;
        doctorDetailsInteractor = new DoctorDetailsInteractorImpl();
    }

    @Override
    public void makeRequest(String doctorId) {
        doctorDetailsInteractor.makeRequest(doctorId, this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(Doctor doctor) {
        view.displayResults(doctor);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
