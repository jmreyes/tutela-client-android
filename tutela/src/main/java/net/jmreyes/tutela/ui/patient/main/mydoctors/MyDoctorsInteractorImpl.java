package net.jmreyes.tutela.ui.patient.main.mydoctors;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.extra.MyDoctor;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 1/11/14.
 */
public class MyDoctorsInteractorImpl implements MyDoctorsInteractor {
    @Override
    public void makeRequest(final MyDoctorsPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().getDoctors(new Callback<Collection<MyDoctor>>() {
            @Override
            public void success(Collection<MyDoctor> results, Response response) {
                listener.onSuccess(new ArrayList<MyDoctor>(results));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
