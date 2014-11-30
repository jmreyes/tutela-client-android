package net.jmreyes.tutela.ui.doctor.main.medication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Medication;

import java.util.List;

/**
 * Created by juanma on 23/11/14.
 */
public class MedicationAdapter extends ArrayAdapter<Medication> {
    private final Context context;
    private final List<Medication> values;

    public MedicationAdapter(Context context, List<Medication> values) {
        super(context, R.layout.tile_list_one_item, values);
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
            convertView = inflater.inflate(R.layout.tile_list_one_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.title.setText(values.get(position).getName());

        return convertView;
    }

    public String getId(int position) {
        return values.get(position).getId();
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
