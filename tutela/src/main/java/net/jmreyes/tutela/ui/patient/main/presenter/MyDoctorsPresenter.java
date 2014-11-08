package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;
import net.jmreyes.tutela.ui.patient.main.view.MyDoctorsView;

import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public interface MyDoctorsPresenter extends BaseFragmentPresenter<MyDoctorsView> {
    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<Doctor> results);
        public void onError();
    }
}
