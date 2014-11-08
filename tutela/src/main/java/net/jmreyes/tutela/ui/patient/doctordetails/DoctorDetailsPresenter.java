package net.jmreyes.tutela.ui.patient.doctordetails;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;

/**
 * Created by juanma on 4/11/14.
 */
public interface DoctorDetailsPresenter {
    public void makeRequest(String doctorId);

    public static interface OnFinishedListener {
        public void onSuccess(Doctor doctor);
        public void onError();
    }
}
