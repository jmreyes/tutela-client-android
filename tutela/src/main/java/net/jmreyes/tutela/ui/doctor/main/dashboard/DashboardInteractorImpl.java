package net.jmreyes.tutela.ui.doctor.main.dashboard;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Alert;
import net.jmreyes.tutela.model.extra.DoctorStatus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 8/11/14.
 */
public class DashboardInteractorImpl implements DashboardInteractor {
    @Override
    public void makeRequest(final DashboardPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getAlerts(new Callback<Collection<Alert>>() {
            @Override
            public void success(Collection<Alert> alerts, Response response) {
                listener.onSuccess(new ArrayList<Alert>(alerts));
                getStatus(listener);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    private void getStatus(final DashboardPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getStatus(new Callback<DoctorStatus>() {

            @Override
            public void success(DoctorStatus doctorStatus, Response response) {
                listener.onGetStatusSuccess(doctorStatus);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}