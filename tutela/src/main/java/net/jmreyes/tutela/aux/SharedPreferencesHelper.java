package net.jmreyes.tutela.aux;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.jmreyes.tutela.model.extra.Alarm;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by juanma on 21/11/14.
 */
public class SharedPreferencesHelper {
    public static final String ALARMS_PREF_KEY = "alarms";

    public static void addAlarmToSharedPreferences(Context context, Alarm alarm) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        int hourOfDay = alarm.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minute = alarm.getCalendar().get(Calendar.MINUTE);

        List <Alarm> currentAlarms = getAlarmsFromSharedPreferences(context);
        for (Alarm a : currentAlarms) {
            if (hourOfDay == a.getCalendar().get(Calendar.HOUR_OF_DAY)
                    && (minute == a.getCalendar().get(Calendar.MINUTE))) {
                currentAlarms.remove(a);
                break;
            }
        }

        currentAlarms.add(alarm);

        String currentAlarmsJSONString = new Gson().toJson(currentAlarms);
        editor.putString(ALARMS_PREF_KEY, currentAlarmsJSONString);
        editor.apply();
    }

    public static List<Alarm> getAlarmsFromSharedPreferences(Context context) {
        List<Alarm> result = new ArrayList<Alarm>();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String alarmsJsonString = prefs.getString(ALARMS_PREF_KEY, null);

        if (alarmsJsonString == null) return result;

        Type type = new TypeToken <List<Alarm>>(){}.getType();
        try {
            result = new Gson().fromJson(alarmsJsonString, type);
        } catch (JsonParseException e) {
        }

        return result;
    }

    public static void removeAlarmFromSharedPreferences(Context context, Alarm alarm) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        int hourOfDay = alarm.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minute = alarm.getCalendar().get(Calendar.MINUTE);

        List <Alarm> currentAlarms = getAlarmsFromSharedPreferences(context);
        for (Alarm a : currentAlarms) {
            if (hourOfDay == a.getCalendar().get(Calendar.HOUR_OF_DAY)
                    && (minute == a.getCalendar().get(Calendar.MINUTE))) {
                currentAlarms.remove(a);
                break;
            }
        }

        String currentAlarmsJSONString = new Gson().toJson(currentAlarms);
        editor.putString(ALARMS_PREF_KEY, currentAlarmsJSONString);
        editor.apply();
    }
}
