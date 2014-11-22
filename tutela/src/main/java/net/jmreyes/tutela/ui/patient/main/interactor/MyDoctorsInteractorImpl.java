package net.jmreyes.tutela.ui.patient.main.interactor;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.extra.MyDoctor;
import net.jmreyes.tutela.ui.patient.main.presenter.MyDoctorsPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
