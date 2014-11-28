package net.jmreyes.tutela.ui.patient.main.mymedication;

import net.jmreyes.tutela.model.extra.MyMedication;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by juanma on 29/10/14.
 */
public class MyMedicationPresenterImpl implements MyMedicationPresenter, MyMedicationPresenter.OnFinishedListener {
    private MyMedicationView view;
    private MyMedicationInteractor myMedicationInteractor;

    @Inject
    public MyMedicationPresenterImpl() {
        myMedicationInteractor = new MyMedicationInteractorImpl();
    }

    @Override
    public void init(MyMedicationView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        myMedicationInteractor.makeRequest(this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(List<MyMedication> results) {
        view.displayResults(results);
    }

    @Override
    public void onError() {
        view.displayError();
    }
}
