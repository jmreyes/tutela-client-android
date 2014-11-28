package net.jmreyes.tutela.ui.patient.main.checkin;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.CheckIn;
import net.jmreyes.tutela.model.extra.CheckInProposal;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 1/11/14.
 */
public class CheckInInteractorImpl implements CheckInInteractor {

    @Override
    public void makeRequest(final CheckInPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().getCheckInProposal(new Callback<Collection<CheckInProposal>>() {
            @Override
            public void success(Collection<CheckInProposal> results, Response response) {
                listener.onSuccess(new ArrayList<CheckInProposal>(results));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    @Override
    public void sendCheckIn(ArrayList<CheckIn> checkIn, final CheckInPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().postCheckIn(checkIn, new Callback<Void>() {
            @Override
            public void success(Void aVoid, Response response) {
                listener.onCheckInSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
