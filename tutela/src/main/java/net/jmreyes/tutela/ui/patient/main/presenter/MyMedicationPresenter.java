package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;
import net.jmreyes.tutela.ui.patient.main.interactor.MyMedicationInteractor;
import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;

import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public interface MyMedicationPresenter extends BaseFragmentPresenter<MyMedicationView> {

    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<Medication> results);
        public void onError();
    }
}
