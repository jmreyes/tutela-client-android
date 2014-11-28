package net.jmreyes.tutela.ui.patient.main.checkin;

import net.jmreyes.tutela.model.CheckIn;

import java.util.ArrayList;

/**
 * Created by juanma on 1/11/14.
 */
public interface CheckInInteractor {
    public void makeRequest(final CheckInPresenter.OnFinishedListener callback);
    public void sendCheckIn(ArrayList<CheckIn> checkIn, final CheckInPresenter.OnFinishedListener callback);
}
