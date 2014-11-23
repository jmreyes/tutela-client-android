package net.jmreyes.tutela.ui.doctor.main.dashboard;

import net.jmreyes.tutela.model.Alert;
import net.jmreyes.tutela.model.extra.DoctorStatus;
import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface DashboardPresenter extends BaseFragmentPresenter<DashboardView> {

    public void makeRequest();

    public static interface OnFinishedListener {
        public void onSuccess(List<Alert> results);
        public void onGetStatusSuccess(DoctorStatus status);
        public void onError();
    }
}
