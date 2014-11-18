package net.jmreyes.tutela.ui.patient.doctordetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class DoctorDetailsInteractorImpl implements DoctorDetailsInteractor {
    @Override
    public void makeRequest(String doctorId, final DoctorDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().getDoctor(doctorId, new Callback<Doctor>() {
            @Override
            public void success(Doctor doctor, Response response) {
                listener.onSuccess(doctor);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
