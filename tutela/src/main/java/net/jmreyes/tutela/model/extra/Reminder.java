package net.jmreyes.tutela.model.extra;

import java.util.Calendar;

/**
 * Created by juanma on 21/11/14.
 */
public class Reminder {
    Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public Reminder(Calendar calendar) {

        this.calendar = calendar;
    }
}
