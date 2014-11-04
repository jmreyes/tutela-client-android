package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Doctor;
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
        ApiManager.getMedicationService().findOneById(medicationId, new Callback<Medication>() {
            @Override
            public void success(Medication medication, Response response) {
                listener.onSuccess(medication);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });

    }
}
