package net.jmreyes.tutela.ui.patient.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.extra.Reminder;
import net.jmreyes.tutela.ui.patient.main.view.MyRemindersView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public class MyRemindersListAdapter extends ArrayAdapter<Reminder> {
    private final Context context;
    private final List<Reminder> values;

    private MyRemindersView myRemindersView;

    public MyRemindersListAdapter(Context context, List<Reminder> values, MyRemindersView myRemindersView) {
        super(context, R.layout.tile_list_alarm, values);
        this.context = context;
        this.values = values;
        this.myRemindersView = myRemindersView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.tile_list_alarm, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Calendar c = values.get(position).getCalendar();
        String hoursOfDay = String.format("%02d", c.get(Calendar.HOUR_OF_DAY));
        String minute = String.format("%02d", c.get(Calendar.MINUTE));
        holder.title.setText(hoursOfDay+":"+minute);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRemindersView.editAlarm(values.get(position));
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (values.size() > 4) {
                    myRemindersView.removeAlarm(values.get(position));
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.error_minimum_reminders), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    public Calendar getCalendar(int position) {
        return values.get(position).getCalendar();
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.button) Button button;
        @InjectView(R.id.delete_button) ImageButton deleteButton;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
