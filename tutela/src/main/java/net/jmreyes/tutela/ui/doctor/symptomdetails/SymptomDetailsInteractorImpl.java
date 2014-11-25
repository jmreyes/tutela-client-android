package net.jmreyes.tutela.ui.doctor.symptomdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.extra.MyMedication;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class SymptomDetailsInteractorImpl implements SymptomDetailsInteractor {

    @Override
    public void makeRequest(String symptomId, final SymptomDetailsPresenter.OnFinishedListener listener) {
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
}
