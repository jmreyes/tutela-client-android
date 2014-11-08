package net.jmreyes.tutela.ui.patient.doctordetails;

/**
 * Created by juanma on 4/11/14.
 */
public interface DoctorDetailsInteractor {
    public void makeRequest(String doctorId, final DoctorDetailsPresenter.OnFinishedListener callback);
}
