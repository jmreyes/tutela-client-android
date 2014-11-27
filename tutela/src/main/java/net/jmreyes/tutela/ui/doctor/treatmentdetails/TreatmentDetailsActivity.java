package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.Treatment;
import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.ui.common.BaseActivity;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * Created by juanma on 3/11/14.
 */
public class TreatmentDetailsActivity extends BaseActivity implements TreatmentDetailsView {
    public static final String ARG_TREATMENT_ID = "treatmentId";

    @Inject
    TreatmentDetailsPresenter presenter;

    @InjectView(R.id.subtitle) TextView patientName;

    @InjectView(R.id.medication_holder) LinearLayout medicationHolder;
    @InjectView(R.id.symptoms_holder) LinearLayout symptomsHolder;

    @InjectView(R.id.layout_view_details) LinearLayout layoutView;
    @InjectView(R.id.layout_edit_details) LinearLayout layoutEdit;

    @InjectView(R.id.medication_edit_layout) LinearLayout medicationEditHolder;
    @InjectView(R.id.symptoms_edit_layout) LinearLayout symptomsEditHolder;

    private String treatmentId;
    private Treatment treatment;

    private HashMap<String, ArrayList<String[]>> medicationHistory;
    private HashMap<String, ArrayList<String[]>> symptomsHistory;

    private ArrayList<ArrayList<String>> medicationFromDoctor;
    private ArrayList<ArrayList<String>> symptomsFromDoctor;

    private boolean isNewTreatment;

    private boolean showingEditDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);
        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            treatmentId = bundle.getString(ARG_TREATMENT_ID);
        } else {
            isNewTreatment = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (treatmentId != null && !isNewTreatment) {
            showLoadingBar();
            presenter.makeRequest(treatmentId);
        } else {
            treatment = new Treatment();
            populateEditDetails();
            showingEditDetails = true;
            invalidateOptionsMenu();
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new TreatmentDetailsModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!showingEditDetails) {
            getMenuInflater().inflate(R.menu.treatment_details, menu);
        } else if (isNewTreatment) {
            getMenuInflater().inflate(R.menu.treatment_details_edit_new, menu);
        } else {
            getMenuInflater().inflate(R.menu.treatment_details_edit, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_edit_details:
                populateEditDetails();
                showingEditDetails = true;
                invalidateOptionsMenu();
                break;
            case R.id.action_edit_details_cancel:
                populateViewDetails();
                showingEditDetails = false;
                invalidateOptionsMenu();
                break;
            case R.id.action_edit_details_done:
                saveDetails();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayResults(Treatment treatment,
                               HashMap<String, ArrayList<String[]>> medicationHistory,
                               HashMap<String, ArrayList<String[]>> symptomsHistory,
                               ArrayList<ArrayList<String>> medicationFromDoctor,
                               ArrayList<ArrayList<String>> symptomsFromDoctor) {
        hideLoadingBar();
        this.treatment = treatment;
        this.medicationHistory = medicationHistory;
        this.symptomsHistory = symptomsHistory;
        this.medicationFromDoctor = medicationFromDoctor;
        this.symptomsFromDoctor = symptomsFromDoctor;
        populateViewDetails();
        showingEditDetails = false;
        invalidateOptionsMenu();
    }

    @Override
    public void saveDetailsSuccess() {
        if (!isNewTreatment) {
            hideLoadingBar();
            populateViewDetails();
            showingEditDetails = false;
            invalidateOptionsMenu();
        } else {
            Toast.makeText(this, R.string.toast_new_treatment_success, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    @Override
    public void saveDetailsError() {
        hideLoadingBar();
        Toast.makeText(this, R.string.toast_save_details_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayResultsError() {
        showErrorInLoadingBar(null);
    }

    @OnClick(R.id.loadingLayoutRetryButton)
    public void onRetryClick() {
        hideErrorInLoadingBar();
        presenter.makeRequest(treatmentId);
    }

    private void populateViewDetails() {
        patientName.setVisibility(View.VISIBLE);
        patientName.setText(treatment.getPatientName());

        layoutView.setVisibility(View.VISIBLE);
        layoutEdit.setVisibility(View.GONE);

        medicationHolder.removeAllViews();
        symptomsHolder.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (Map.Entry<String, ArrayList<String[]>> entry : medicationHistory.entrySet()) {
            LinearLayout view = (LinearLayout) inflater.inflate(R.layout.tile_list_history, medicationHolder, false);

            TextView name = (TextView) view.findViewById(R.id.title);
            name.setText(entry.getKey());

            ArrayList<String[]> datesAndAnswers = entry.getValue();
            for (int i = 0; i < datesAndAnswers.size(); i++) {
                LinearLayout row = (LinearLayout) inflater.inflate(R.layout.subtile_list_history, view, false);
                TextView date = (TextView) row.findViewById(R.id.date);
                TextView answer = (TextView) row.findViewById(R.id.answer);
                date.setText(datesAndAnswers.get(i)[0]);
                answer.setText(datesAndAnswers.get(i)[1]);
                view.addView(row);
            }

            medicationHolder.addView(view);
        }

        for (Map.Entry<String, ArrayList<String[]>> entry : symptomsHistory.entrySet()) {
            LinearLayout view = (LinearLayout) inflater.inflate(R.layout.tile_list_history, symptomsHolder, false);

            TextView name = (TextView) view.findViewById(R.id.title);
            name.setText(entry.getKey());

            ArrayList<String[]> datesAndAnswers = entry.getValue();
            for (int i = 0; i < datesAndAnswers.size(); i++) {
                LinearLayout row = (LinearLayout) inflater.inflate(R.layout.subtile_list_history, view, false);
                TextView date = (TextView) row.findViewById(R.id.date);
                TextView answer = (TextView) row.findViewById(R.id.answer);
                date.setText(datesAndAnswers.get(i)[0]);
                answer.setText(datesAndAnswers.get(i)[1]);
                view.addView(row);
            }

            symptomsHolder.addView(view);
        }
    }

    private void populateEditDetails() {
        patientName.setVisibility(View.VISIBLE);

        layoutView.setVisibility(View.GONE);
        layoutEdit.setVisibility(View.VISIBLE);

        if (isNewTreatment) return;

        patientName.setText(treatment.getPatientName());

        medicationEditHolder.removeAllViews();
        symptomsEditHolder.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ArrayList<Treatment.EmbeddedMedication> embeddedMedications = new ArrayList<Treatment.EmbeddedMedication>(treatment.getMedication());

        for (int i = 0; i < embeddedMedications.size(); i++) {
            final View view = inflater.inflate(R.layout.tile_simple_list_textview_with_delete, medicationEditHolder, false);

            TextView textView = (TextView) view.findViewById(R.id.content);
            final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete);

            Treatment.EmbeddedMedication em = embeddedMedications.get(i);
            String text = em.getMedicationName();
            textView.setText(text);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    medicationEditHolder.removeView(view);
                }
            });

            view.setTag(R.string.tag_medication_id, em.getMedicationId());
            view.setTag(R.string.tag_medication_name, em.getMedicationName());

            medicationEditHolder.addView(view);
        }

        ArrayList<Treatment.EmbeddedSymptom> embeddedSymptoms = new ArrayList<Treatment.EmbeddedSymptom>(treatment.getSymptoms());

        for (int i = 0; i < embeddedSymptoms.size(); i++) {
            final View view = inflater.inflate(R.layout.tile_simple_list_textview_with_delete, symptomsEditHolder, false);

            TextView textView = (TextView) view.findViewById(R.id.content);
            final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete);

            Treatment.EmbeddedSymptom es = embeddedSymptoms.get(i);
            String text = es.getSymptomName();
            textView.setText(text);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    symptomsEditHolder.removeView(view);
                }
            });

            view.setTag(R.string.tag_symptom_id, es.getSymptomId());
            view.setTag(R.string.tag_symptom_name, es.getSymptomName());

            symptomsEditHolder.addView(view);
        }
    }

    private void saveDetails() {
        showLoadingBar();

        ArrayList<Treatment.EmbeddedMedication> embeddedMedications = new ArrayList<Treatment.EmbeddedMedication>();

        for (int i = 0; i < medicationEditHolder.getChildCount(); i++) {
            View view = medicationEditHolder.getChildAt(i);

            String medicationId = (String) view.getTag(R.string.tag_medication_id);
            String medicationName = (String) view.getTag(R.string.tag_medication_name);

            Treatment.EmbeddedMedication em = new Treatment.EmbeddedMedication(medicationId, medicationName, null);

            embeddedMedications.add(em);
        }

        ArrayList<Treatment.EmbeddedSymptom> embeddedSymptoms = new ArrayList<Treatment.EmbeddedSymptom>();

        for (int i = 0; i < symptomsEditHolder.getChildCount(); i++) {
            View view = symptomsEditHolder.getChildAt(i);

            String symptomId = (String) view.getTag(R.string.tag_symptom_id);
            String symptomName = (String) view.getTag(R.string.tag_symptom_name);

            Treatment.EmbeddedSymptom es = new Treatment.EmbeddedSymptom(symptomId, symptomName);

            embeddedSymptoms.add(es);
        }

        treatment.setMedication(embeddedMedications);
        treatment.setSymptoms(embeddedSymptoms);

        presenter.postDetails(treatment);
    }

    @OnClick(R.id.add_new_medication_button)
    public void clickOnAddNewMedication () {
        showAnswerPicker(AnswerPickerFragment.TYPE_MEDICATION_PICKER);
    }

    @OnClick(R.id.add_new_symptom_button)
    public void clickOnAddNewSymptom () {
        showAnswerPicker(AnswerPickerFragment.TYPE_SYMPTOM_PICKER);
    }

    private void showAnswerPicker(int type) {
        AnswerPickerFragment apf = new AnswerPickerFragment();

        Bundle args = new Bundle();
        args.putInt("type", type);
        switch (type) {
            case AnswerPickerFragment.TYPE_MEDICATION_PICKER:
                args.putStringArrayList("ids", medicationFromDoctor.get(0));
                args.putStringArrayList("values", medicationFromDoctor.get(1));
                break;
            case AnswerPickerFragment.TYPE_SYMPTOM_PICKER:
                args.putStringArrayList("ids", symptomsFromDoctor.get(0));
                args.putStringArrayList("values", symptomsFromDoctor.get(1));
                break;
            default:
                return;
        }

        apf.setArguments(args);

        apf.setCallBack(this);
        apf.show(getFragmentManager(), "AnswerPicker");
    }

    private void finishAddMedication(String medicationId, String medicationName) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflater.inflate(R.layout.tile_simple_list_textview_with_delete, medicationEditHolder, false);

        TextView textView = (TextView) view.findViewById(R.id.content);
        final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete);

        textView.setText(medicationName);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicationEditHolder.removeView(view);
            }
        });

        view.setTag(R.string.tag_medication_id, medicationId);
        view.setTag(R.string.tag_medication_name, medicationName);

        medicationEditHolder.addView(view);
    }

    private void finishAddSymptom(String symptomId, String symptomName) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflater.inflate(R.layout.tile_simple_list_textview_with_delete, symptomsEditHolder, false);

        TextView textView = (TextView) view.findViewById(R.id.content);
        final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete);

        textView.setText(symptomName);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptomsEditHolder.removeView(view);
            }
        });

        view.setTag(R.string.tag_symptom_id, symptomId);
        view.setTag(R.string.tag_symptom_name, symptomName);

        symptomsEditHolder.addView(view);
    }

    public static class AnswerPickerFragment extends DialogFragment {
        static final int TYPE_MEDICATION_PICKER = 1;
        static final int TYPE_SYMPTOM_PICKER = 2;

        private ArrayList<String> ids;
        private ArrayList<String> values;
        private int type;
        private WeakReference<TreatmentDetailsActivity> view;

        public AnswerPickerFragment() {
        }

        public void setCallBack(TreatmentDetailsActivity view) {
            this.view = new WeakReference<TreatmentDetailsActivity>(view);
        }

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            values = args.getStringArrayList("values");
            ids = args.getStringArrayList("ids");
            type = args.getInt("type");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final CharSequence[] answers = values.toArray(new CharSequence[values.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(view.get());



            builder.setTitle((type == TYPE_MEDICATION_PICKER) ? R.string.choose_medication : R.string.choose_symptom)
                    .setItems(answers, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (type == TYPE_MEDICATION_PICKER) {
                                view.get().finishAddMedication(ids.get(which), values.get(which));
                            } else {
                                view.get().finishAddSymptom(ids.get(which), values.get(which));
                            }
                        }
                    });
            return builder.create();
        }
    }


}