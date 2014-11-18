package net.jmreyes.tutela.ui.patient.main.interactor;

import com.google.common.collect.Lists;
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
        ApiManager.getPatientService().getMyMedication(new Callback<Collection<MyMedication>>() {
            @Override
            public void success(Collection<MyMedication> results, Response response) {
                listener.onSuccess(new ArrayList<MyMedication>(results));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
