package net.jmreyes.tutela.ui.patient.medicationdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Treatment;
import net.jmreyes.tutela.model.extra.MyMedication;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juanma on 4/11/14.
 */
public class MedicationDetailsInteractorImpl implements MedicationDetailsInteractor {
    @Override
    public void makeRequest(String treatmentId, final String medicationId, final MedicationDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getPatientService().getTreatment(treatmentId, new Callback<Treatment>() {
            @Override
            public void success(Treatment treatment, Response response) {
                MyMedication result = MyMedication.createFromTreatment(treatment, medicationId);
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });

    }
}
