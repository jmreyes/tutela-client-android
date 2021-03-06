package net.jmreyes.tutela.ui.patient.main.mydoctors;

import net.jmreyes.tutela.model.extra.MyDoctor;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;

import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public interface MyDoctorsPresenter extends BaseFragmentPresenter<MyDoctorsView> {
    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<MyDoctor> results);
        public void onError();
    }
}
