package net.jmreyes.tutela.ui.patient.main.interactor;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Treatment;
import net.jmreyes.tutela.model.extra.MyMedication;
import net.jmreyes.tutela.ui.patient.main.presenter.MyMedicationPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by juanma on 1/11/14.
 */
public class MyMedicationInteractorImpl implements MyMedicationInteractor {

    @Override
    public void makeRequest(final MyMedicationPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().getTreatments(new Callback<Collection<Treatment>>() {
            @Override
            public void success(Collection<Treatment> treatments, Response response) {
                ArrayList<MyMedication> results = MyMedication.createListFromTreatments(treatments);
                listener.onSuccess(results);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
