package net.jmreyes.tutela.ui.patient.main.interactor;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.ui.patient.main.presenter.MyDoctorsPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * Created by juanma on 1/11/14.
 */
public class MyDoctorsInteractorImpl implements MyDoctorsInteractor {

    @Override
    public void makeRequest(final MyDoctorsPresenter.OnFinishedListener listener) {
        ApiManager.getDoctorService().getMyDoctors(new Callback<List<Doctor>>() {
            @Override
            public void success(List<Doctor> results, Response response) {
                listener.onSuccess(results);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
