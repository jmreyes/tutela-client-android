package net.jmreyes.tutela.ui.patient.main.view;

import net.jmreyes.tutela.model.extra.Alarm;

import java.util.Calendar;
import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public interface MyRemindersView {
    public void displayResults(List<Alarm> results);
    public void displayError();

    public void removeAlarm(Alarm alarm);
    public void editAlarm(Alarm alarm);
}
