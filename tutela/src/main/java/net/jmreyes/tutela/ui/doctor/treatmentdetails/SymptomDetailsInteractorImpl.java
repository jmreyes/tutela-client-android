package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Symptom;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class SymptomDetailsInteractorImpl implements SymptomDetailsInteractor {

    @Override
    public void makeRequest(String symptomId, final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getSymptom(symptomId, new Callback<Symptom>() {
            @Override
            public void success(Symptom result, Response response) {
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    @Override
    public void postDetails(Symptom symptom, final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().postSymptom(symptom, new Callback<Void>() {
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
