package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.model.CheckIn;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.Treatment;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 4/11/14.
 */
public class TreatmentDetailsInteractorImpl implements TreatmentDetailsInteractor {

    @Override
    public void makeRequest(final String treatmentId, final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getTreatment(treatmentId, new Callback<Treatment>() {
            @Override
            public void success(Treatment treatment, Response response) {
                getCheckIns(treatmentId, treatment, listener);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    public void getCheckIns(final String treatmentId,
                            final Treatment treatment,
                            final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getCheckIns(treatmentId, new Callback<Collection<CheckIn>>() {
            @Override
            public void success(Collection<CheckIn> checkIns, Response response) {
                getMedication(treatmentId, treatment, checkIns, listener);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    private void getMedication(final String treatmentId,
                               final Treatment treatment,
                               final Collection<CheckIn> checkIns,
                               final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getMedication(new Callback<Collection<Medication>>() {
            @Override
            public void success(Collection<Medication> medication, Response response) {
                getSymptoms(treatmentId, treatment, checkIns, medication, listener);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    private void getSymptoms(String treatmentId,
                             final Treatment treatment,
                             final Collection<CheckIn> checkIns,
                             final Collection<Medication> medication,
                             final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().getSymptoms(new Callback<Collection<Symptom>>() {
            @Override
            public void success(Collection<Symptom> symptoms, Response response) {
                listener.onSuccess(treatment, new ArrayList<CheckIn>(checkIns), new ArrayList<Medication>(medication),
                        new ArrayList<Symptom>(symptoms));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    @Override
    public void postDetails(Treatment treatment, final TreatmentDetailsPresenter.OnFinishedListener listener) {
        ApiManager.getDocService().postTreatment(treatment, new Callback<Void>() {
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
