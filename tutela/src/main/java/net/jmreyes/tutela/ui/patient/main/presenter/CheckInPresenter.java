package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.model.extra.CheckInProposal;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;
import net.jmreyes.tutela.ui.patient.main.view.CheckInView;

import java.util.ArrayList;

/**
 * Created by juanma on 29/10/14.
 */
public interface CheckInPresenter extends BaseFragmentPresenter<CheckInView> {
    public void makeRequest();

    public void registerMedication(boolean taken);
    public void registerSymptom(Answer answer);

    public void previousQuestion();

    public static interface OnFinishedListener {
        public void onSuccess(ArrayList<CheckInProposal> results);
        public void onError();

        public void onCheckInSuccess();
    }
}
