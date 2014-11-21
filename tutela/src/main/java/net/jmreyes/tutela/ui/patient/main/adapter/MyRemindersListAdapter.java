package net.jmreyes.tutela.ui.patient.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.extra.Alarm;
import net.jmreyes.tutela.ui.patient.main.view.MyRemindersView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public class MyRemindersListAdapter extends ArrayAdapter<Alarm> {
    private final Context context;
    private final List<Alarm> values;

    private MyRemindersView myRemindersView;

    public MyRemindersListAdapter(Context context, List<Alarm> values, MyRemindersView myRemindersView) {
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

        ButterKnife.findById(convertView, R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRemindersView.editAlarm(values.get(position));
            }
        });

        ButterKnife.findById(convertView, R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRemindersView.removeAlarm(values.get(position));
            }
        });

        return convertView;
    }

    public Calendar getCalendar(int position) {
        return values.get(position).getCalendar();
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
