package net.jmreyes.tutela.ui.doctor.main.symptoms;

import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface SymptomsPresenter extends BaseFragmentPresenter<SymptomsView> {

    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<Symptom> results);
        public void onError();
    }
}
