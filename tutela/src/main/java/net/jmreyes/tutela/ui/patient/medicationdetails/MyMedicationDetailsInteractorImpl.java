package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.extra.MyMedication;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class MyMedicationDetailsInteractorImpl implements MyMedicationDetailsInteractor {
    @Override
    public void makeRequest(String treatmentId, final String medicationId, final MyMedicationDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().getOneMedication(treatmentId, medicationId, new Callback<MyMedication>() {
            @Override
            public void success(MyMedication result, Response response) {
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
