package net.jmreyes.tutela.aux;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import net.jmreyes.tutela.model.extra.Reminder;

import java.util.Calendar;
import java.util.List;

/**
 * Created by juanma on 22/11/14.
 */
public class AlarmHelper {

    public static void setAlarm(Context context, Reminder reminder) {
        Intent mainIntent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, mainIntent, 0);

        Calendar c = reminder.getCalendar();
        Calendar now = Calendar.getInstance();

        // If the time is set in the past, the alarm would be triggered immediately. Add one day offset.
        if (c.getTimeInMillis() < now.getTimeInMillis()) {
            c.setTimeInMillis(c.getTimeInMillis() + 24*60*60*1000);
        }

        c.set(Calendar.MILLISECOND, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pIntent);

        Log.d("AlarmHelper", "Alarm set at " + reminder.getCalendar().toString());
    }

    public static void resetAlarms(Context context, List<Reminder> reminderList) {
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);

        // First we cancel all alarms
        am.cancel(pIntent);
        Log.d("AlarmHelper", "Removed all alarms");

        if (reminderList == null) return;

        long msNow = Calendar.getInstance().getTimeInMillis();

        for (Reminder r : reminderList) {
            long alarmTime = r.getCalendar().getTimeInMillis();

            // If the time is set in the past, the alarm would be triggered immediately. Add N days offset.
            if (alarmTime < msNow) {
                long nDays = (msNow - alarmTime) / (24*60*60*1000);
                alarmTime = alarmTime + (nDays + 1) * 24*60*60*1000;
            }

            am.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, pIntent);
            Log.d("AlarmHelper", "Alarm set at " + r.getCalendar().toString());
        }
    }
}
