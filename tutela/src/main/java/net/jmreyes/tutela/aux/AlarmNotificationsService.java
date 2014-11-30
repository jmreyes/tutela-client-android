package net.jmreyes.tutela.aux;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.patient.main.PatientMainActivity;

/**
 * Created by juanma on 22/11/14.
 */
public class AlarmNotificationsService extends IntentService {
    public AlarmNotificationsService() {
        super("AlarmNotificationsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Intent mainIntent = new Intent(this, PatientMainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager nm = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_healthcare_stethoscope)
                .setContentTitle(getString(R.string.checkin_notification_title))
                .setContentText(getString(R.string.checkin_notification_text));

        nm.notify(0, builder.build());

    }
}
