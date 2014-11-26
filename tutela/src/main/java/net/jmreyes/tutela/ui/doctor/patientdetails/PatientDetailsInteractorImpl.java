package net.jmreyes.tutela.ui.doctor.patientdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class PatientDetailsInteractorImpl implements PatientDetailsInteractor {

    @Override
    public void makeRequest(String patientDetailsId, final PatientDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getPatient(patientDetailsId, new Callback<PatientDetails>() {
            @Override
            public void success(PatientDetails result, Response response) {
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    @Override
    public void postDetails(String patientDetailsId, PatientDetails patientDetails, final PatientDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().updatePatient(patientDetailsId, patientDetails, new Callback<Void>() {
            @Override
            public void success(Void aVoid, Response response) {
                listener.onPostDetailsSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onPostDetailsError();
            }
        });
    }
}
