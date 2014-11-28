package net.jmreyes.tutela.ui.patient.main.mymedication;

import net.jmreyes.tutela.model.extra.MyMedication;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;

import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public interface MyMedicationPresenter extends BaseFragmentPresenter<MyMedicationView> {

    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<MyMedication> results);
        public void onError();
    }
}
