package net.jmreyes.tutela.model.extra;

import java.util.Calendar;

/**
 * Created by juanma on 21/11/14.
 */
public class Alarm {
    Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public Alarm(Calendar calendar) {

        this.calendar = calendar;
    }
}
