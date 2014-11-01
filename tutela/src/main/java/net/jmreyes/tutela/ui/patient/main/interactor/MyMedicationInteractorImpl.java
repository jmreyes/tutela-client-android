package net.jmreyes.tutela.ui.patient.main.interactor;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.ui.patient.main.presenter.MyMedicationPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * Created by juanma on 1/11/14.
 */
public class MyMedicationInteractorImpl implements MyMedicationInteractor {

    @Override
    public void requestMyMedication(final MyMedicationPresenter.OnFinishedListener listener) {
        ApiManager.getMedicationService().listMedication("test", new Callback<List<Medication>>() {
            @Override
            public void success(List<Medication> result, Response response) {
                listener.requestMyMedicationFinished();
                System.out.println("OK");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("FAIL");
            }
        });
    }
}
