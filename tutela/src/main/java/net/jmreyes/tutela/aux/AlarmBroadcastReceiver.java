package net.jmreyes.tutela.aux;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by juanma on 22/11/14.
 */
public class AlarmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmBroadcastReceiver", "AlarmBroadcastReceiver called!");

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            restoreAlarms(context);
            return;
        }

        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmNotificationsService.class.getName());
        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }

    public void restoreAlarms(Context context) {
        AlarmHelper.resetAlarms(context, SharedPreferencesHelper.getReminders(context));
    }
}
