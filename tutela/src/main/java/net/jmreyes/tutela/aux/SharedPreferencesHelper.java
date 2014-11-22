package net.jmreyes.tutela.aux;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import net.jmreyes.tutela.model.extra.Reminder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by juanma on 21/11/14.
 */
public class SharedPreferencesHelper {
    public static final String REMINDERS_PREF_KEY = "alarms";

    public static boolean addReminder(Context context, Reminder reminder) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        int hourOfDay = reminder.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minute = reminder.getCalendar().get(Calendar.MINUTE);

        List <Reminder> currentReminders = getReminders(context);
        for (Reminder a : currentReminders) {
            if (hourOfDay == a.getCalendar().get(Calendar.HOUR_OF_DAY)
                    && (minute == a.getCalendar().get(Calendar.MINUTE))) {
                return false;
            }
        }

        currentReminders.add(reminder);

        String currentAlarmsJSONString = new Gson().toJson(currentReminders);
        editor.putString(REMINDERS_PREF_KEY, currentAlarmsJSONString);
        editor.apply();

        return true;
    }

    public static List<Reminder> getReminders(Context context) {
        List<Reminder> result = new ArrayList<Reminder>();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String alarmsJsonString = prefs.getString(REMINDERS_PREF_KEY, null);

        if (alarmsJsonString == null) return result;

        Type type = new TypeToken<List<Reminder>>(){}.getType();
        try {
            result = new Gson().fromJson(alarmsJsonString, type);
        } catch (JsonParseException e) {
        }

        return result;
    }

    public static void removeReminder(Context context, Reminder reminder) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        int hourOfDay = reminder.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minute = reminder.getCalendar().get(Calendar.MINUTE);

        List<Reminder> currentReminders = getReminders(context);
        Iterator<Reminder> i = currentReminders.iterator();
        while (i.hasNext()) {
            Reminder r = i.next();

            if (hourOfDay == r.getCalendar().get(Calendar.HOUR_OF_DAY)
                    && (minute == r.getCalendar().get(Calendar.MINUTE))) {
                i.remove();
                break;
            }
        }

        String currentAlarmsJSONString = new Gson().toJson(currentReminders);
        editor.putString(REMINDERS_PREF_KEY, currentAlarmsJSONString);
        editor.apply();
    }

    /**
     * Used from CheckInFragment after a user CheckIn so that it is not shown again for past Reminders.
     * The Reminders in the past are added an offset of the N + 1 days elapsed so they are set to the following day.
     *
     * @param context
     */
    public static void resetReminders(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        long msNow = Calendar.getInstance().getTimeInMillis();

        List <Reminder> currentReminders = getReminders(context);

        Iterator<Reminder> i = currentReminders.iterator();
        ArrayList<Reminder> newReminders = new ArrayList<Reminder>();
        while (i.hasNext()) {
            Reminder r = i.next();
            long reminderTime = r.getCalendar().getTimeInMillis();
            if (reminderTime < msNow) {
                i.remove();

                long nDays = (msNow - reminderTime) / (24*60*60*1000);

                Calendar newTime = Calendar.getInstance();
                newTime.setTimeInMillis(reminderTime + (nDays + 1) * 24*60*60*1000);
                newReminders.add(new Reminder(newTime));
            }
        }

        currentReminders.addAll(newReminders);

        String currentAlarmsJSONString = new Gson().toJson(currentReminders);
        editor.putString(REMINDERS_PREF_KEY, currentAlarmsJSONString);
        editor.apply();
    }
}
