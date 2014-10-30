package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.api.MedicationService;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public class MyMedicationPresenterImpl implements MyMedicationPresenter {
    private MyMedicationView view;

    @Inject
    @Named("medicationService")
    MedicationService medicationService;

    @Inject
    public MyMedicationPresenterImpl() {
    }

    @Override
    public void init(MyMedicationView view) {
        this.view = view;
    }

    @Override
    public void requestMedication() {


        ApiManager.getMedicationService().listMedication("test", new Callback<List<Medication>>() {
            @Override
            public void success(List<Medication> postListResponse, Response response) {
                System.out.println("OK");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("FAIL");
            }
        });
    }
}
