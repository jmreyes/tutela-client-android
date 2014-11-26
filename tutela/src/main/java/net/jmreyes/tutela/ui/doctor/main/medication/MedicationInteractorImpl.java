package net.jmreyes.tutela.ui.doctor.main.medication;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 8/11/14.
 */
public class MedicationInteractorImpl implements MedicationInteractor {

    @Override
    public void makeRequest(final MedicationPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getMedication(new Callback<Collection<Medication>>() {
            @Override
            public void success(Collection<Medication> medication, Response response) {
                listener.onSuccess(new ArrayList<Medication>(medication));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
