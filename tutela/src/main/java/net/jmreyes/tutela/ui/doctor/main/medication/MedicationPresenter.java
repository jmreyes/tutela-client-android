package net.jmreyes.tutela.ui.doctor.main.medication;

import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface MedicationPresenter extends BaseFragmentPresenter<MedicationView> {

    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<Medication> results);
        public void onError();
    }
}
