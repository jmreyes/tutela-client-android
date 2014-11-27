package net.jmreyes.tutela.ui.doctor.main.mypatients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanma on 23/11/14.
 */
public class MyPatientsAdapter extends ArrayAdapter<PatientDetails> implements Filterable {
    private final Context context;
    private List<PatientDetails> values;
    private List<PatientDetails> originalValues;

    private MyPatientsView myPatientsView;

    public MyPatientsAdapter(Context context, List<PatientDetails> values, MyPatientsView myPatientsView) {
        super(context, R.layout.tile_list_one_item, values);
        this.context = context;
        this.values = values;
        this.originalValues = values;
        this.myPatientsView = myPatientsView;
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

        final PatientDetails current = getItem(position);

        holder.title.setText(current.getFullName());

        holder.button.setVisibility(View.VISIBLE);

        if (current.getTreatmentId() == null) {
            holder.button.setText(R.string.set_treatment);
        } else {
            holder.button.setText(R.string.view_treatment);
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPatientsView.loadTreatmentDetails(current.getTreatmentId());
            }
        });

        return convertView;
    }

    public String getId(int position) {
        return values.get(position).getPatientId();
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.button) Button button;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            ArrayList<PatientDetails> tempList = new ArrayList<PatientDetails>();
            //constraint is the result from text you want to filter against.
            //originalValues is your data set you will filter from
            if (constraint != null && originalValues != null) {
                int length = originalValues.size();
                int i = 0;
                constraint = constraint.toString().toLowerCase();
                while (i < length) {
                    PatientDetails item = originalValues.get(i);
                    //do whatever you wanna do here
                    //adding result set output array

                    if (item.getFirstName().toLowerCase().startsWith(constraint.toString())
                            || item.getLastName().toLowerCase().startsWith(constraint.toString())) {
                        tempList.add(item);
                    }

                    i++;
                }
                //following two lines is very important
                //as publish result can only take FilterResults objects
                filterResults.values = tempList;
                filterResults.count = tempList.size();
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            values = (ArrayList<PatientDetails>) results.values;
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return myFilter;
    }


    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public PatientDetails getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
