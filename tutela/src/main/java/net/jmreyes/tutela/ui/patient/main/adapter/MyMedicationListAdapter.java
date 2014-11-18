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
import net.jmreyes.tutela.model.extra.MyMedication;

import java.util.List;

/**
 * Created by juanma on 29/10/14.
 */
public class MyMedicationListAdapter extends ArrayAdapter<MyMedication> {
    private final Context context;
    private final List<MyMedication> values;

    public MyMedicationListAdapter(Context context, List<MyMedication> values) {
        super(context, R.layout.tile_list_two_items, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.tile_list_two_items, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.title.setText(values.get(position).getName());
        holder.subtitle.setText(context.getString(R.string.doctor) + ": " + values.get(position).getFullName());

        return convertView;
    }

    public String getId(int position) {
        return values.get(position).getId();
    }

    public String getTreatmentId(int position) {
        return values.get(position).getTreatmentId();
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.subtitle) TextView subtitle;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
