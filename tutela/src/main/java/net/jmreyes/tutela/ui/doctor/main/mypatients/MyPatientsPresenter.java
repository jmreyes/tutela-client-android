package net.jmreyes.tutela.ui.doctor.main.mypatients;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface MyPatientsPresenter extends BaseFragmentPresenter<MyPatientsView> {

    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<PatientDetails> results);
        public void onError();
    }
}
