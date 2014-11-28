package net.jmreyes.tutela.ui.doctor.main.alerts;

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
public class AlertInteractorImpl implements AlertInteractor {
    @Override
    public void makeRequest(final AlertPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getAlerts(new Callback<Collection<Alert>>() {
            @Override
            public void success(Collection<Alert> alerts, Response response) {
                getStatus(alerts, listener);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    private void getStatus(final Collection<Alert> alerts, final AlertPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().postAlertsMarkAsSeen(new Callback<Void>() {

            @Override
            public void success(Void aVoid, Response response) {
                listener.onSuccess(new ArrayList<Alert>(alerts));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}