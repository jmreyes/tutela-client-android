package net.jmreyes.tutela.ui.patient.main.myreminders;

import net.jmreyes.tutela.model.extra.Reminder;

import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public interface MyRemindersView {
    public void displayResults(List<Reminder> results);
    public void displayError();

    public void removeAlarm(Reminder reminder);
    public void editAlarm(Reminder reminder);
}
