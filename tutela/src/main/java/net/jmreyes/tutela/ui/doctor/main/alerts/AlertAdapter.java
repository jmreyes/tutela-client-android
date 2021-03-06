package net.jmreyes.tutela.ui.doctor.main.alerts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Alert;

import java.util.List;

/**
 * Created by juanma on 23/11/14.
 */
public class AlertAdapter extends ArrayAdapter<Alert> {
    private final Context context;
    private final List<Alert> values;

    private AlertView alertView;

    public AlertAdapter(Context context, List<Alert> values, AlertView alertView) {
        super(context, R.layout.tile_list_doctor_dashboard, values);
        this.context = context;
        this.values = values;
        this.alertView = alertView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.tile_list_doctor_dashboard, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.title.setText(context.getString(R.string.dashboard_alert_text,
                values.get(position).getPatientName(),
                values.get(position).getHours(),
                values.get(position).getSymptomName(),
                values.get(position).getAnsText()));

        holder.button1.setText(context.getString(R.string.dashboard_patient_button));
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertView.loadPatientDetails(values.get(position).getPatientId());
            }
        });

        holder.button2.setText(context.getString(R.string.dashboard_alert_button));
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertView.loadTreatmentDetails(values.get(position).getTreatmentId());
            }
        });

        return convertView;
    }

    public String getId(int position) {
        return values.get(position).getId();
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.button1) Button button1;
        @InjectView(R.id.button2) Button button2;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
