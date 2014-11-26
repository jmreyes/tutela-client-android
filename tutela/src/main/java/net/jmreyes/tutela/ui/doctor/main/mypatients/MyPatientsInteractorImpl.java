package net.jmreyes.tutela.ui.doctor.main.mypatients;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 8/11/14.
 */
public class MyPatientsInteractorImpl implements MyPatientsInteractor {

    @Override
    public void makeRequest(final MyPatientsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getPatients(new Callback<Collection<PatientDetails>>() {
            @Override
            public void success(Collection<PatientDetails> patientDetails, Response response) {
                listener.onSuccess(new ArrayList<PatientDetails>(patientDetails));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
