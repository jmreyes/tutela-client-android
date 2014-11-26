package net.jmreyes.tutela.ui.doctor.medicationdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Medication;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class MedicationDetailsInteractorImpl implements MedicationDetailsInteractor {

    @Override
    public void makeRequest(String medicationId, final MedicationDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getOneMedication(medicationId, new Callback<Medication>() {
            @Override
            public void success(Medication result, Response response) {
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    @Override
    public void postDetails(Medication medication, final MedicationDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().postMedication(medication, new Callback<Void>() {
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
