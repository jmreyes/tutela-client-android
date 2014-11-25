package net.jmreyes.tutela.ui.doctor.main.symptoms;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Symptom;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 8/11/14.
 */
public class SymptomsInteractorImpl implements SymptomsInteractor {

    @Override
    public void makeRequest(final SymptomsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getSymptoms(new Callback<Collection<Symptom>>() {
            @Override
            public void success(Collection<Symptom> symptoms, Response response) {
                listener.onSuccess(new ArrayList<Symptom>(symptoms));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
