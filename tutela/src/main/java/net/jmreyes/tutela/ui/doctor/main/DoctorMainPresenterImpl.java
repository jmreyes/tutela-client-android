package net.jmreyes.tutela.ui.doctor.main;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class DoctorMainPresenterImpl implements DoctorMainPresenter, DoctorMainPresenter.OnFinishedListener {
    private DoctorMainView view;
    private DoctorMainInteractor doctorMainInteractor;

    @Inject
    public DoctorMainPresenterImpl(DoctorMainView view) {
        this.view = view;
        doctorMainInteractor = new DoctorMainInteractorImpl();
    }
}
