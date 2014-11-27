package net.jmreyes.tutela.ui.doctor.symptomdetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.*;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.ui.common.BaseActivity;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * Created by juanma on 3/11/14.
 */
public class SymptomDetailsActivity extends BaseActivity implements SymptomDetailsView {
    public static final String ARG_SYMPTOM_ID = "symptomId";

    @Inject
    SymptomDetailsPresenter presenter;

    @InjectView(R.id.subtitle) TextView symptomName;
    @InjectView(R.id.subtitle_edit) EditText symptomNameEdit;

    @InjectView(R.id.question_content) TextView question;
    @InjectView(R.id.question_content_edit) EditText questionEdit;

    @InjectView(R.id.answers_layout) LinearLayout answersLayout;
    @InjectView(R.id.answer_1) TextView answer1;
    @InjectView(R.id.answer_2) TextView answer2;
    @InjectView(R.id.answer_3) TextView answer3;
    @InjectView(R.id.answers_edit_layout) LinearLayout answersEditLayout;
    @InjectView(R.id.answer_edit_1) EditText answerEdit1;
    @InjectView(R.id.answer_edit_2) EditText answerEdit2;
    @InjectView(R.id.answer_edit_3) EditText answerEdit3;

    @InjectView(R.id.alerts_layout) LinearLayout alertsLayout;
    @InjectView(R.id.alerts_edit_layout) LinearLayout alertsEditLayout;
    @InjectView(R.id.add_new_alert_button) Button addNewAlertButton;

    private String symptomId;
    private Symptom symptom;

    private boolean isNewSymptom;

    private boolean showingEditDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_details);
        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            symptomId = bundle.getString(ARG_SYMPTOM_ID);
        } else {
            isNewSymptom = true;
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

        if (symptomId != null && !isNewSymptom) {
            showLoadingBar();
            presenter.makeRequest(symptomId);
        } else {
            symptom = new Symptom();
            populateEditDetails();
            showingEditDetails = true;
            invalidateOptionsMenu();
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new SymptomDetailsModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!showingEditDetails) {
            getMenuInflater().inflate(R.menu.symptom_medication_details, menu);
        } else if (isNewSymptom) {
            getMenuInflater().inflate(R.menu.symptom_medication_details_edit_new, menu);
        } else {
            getMenuInflater().inflate(R.menu.symptom_medication_details_edit, menu);
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
    public void displayResults(Symptom symptom) {
        hideLoadingBar();
        this.symptom = symptom;
        populateViewDetails();
        showingEditDetails = false;
        invalidateOptionsMenu();
    }

    @Override
    public void saveDetailsSuccess() {
        if (!isNewSymptom) {
            hideLoadingBar();
            populateViewDetails();
            showingEditDetails = false;
            invalidateOptionsMenu();
        } else {
            Toast.makeText(this, R.string.toast_new_symptom_success, Toast.LENGTH_SHORT).show();
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
        presenter.makeRequest(symptomId);
    }

    private void populateViewDetails() {
        symptomName.setVisibility(View.VISIBLE);
        symptomNameEdit.setVisibility(View.GONE);
        symptomName.setText(symptom.getName());

        question.setVisibility(View.VISIBLE);
        questionEdit.setVisibility(View.GONE);
        question.setText(symptom.getQuestion());

        answer1.setVisibility(View.GONE);
        answer2.setVisibility(View.GONE);
        answer3.setVisibility(View.GONE);
        answerEdit1.setVisibility(View.GONE);
        answerEdit2.setVisibility(View.GONE);
        answerEdit3.setVisibility(View.GONE);
        SparseArray<String> hashAnswerIndexString= new SparseArray<String>();
        for (Answer a : symptom.getAnswers()) {
            int index = a.getAnsIndex();
            String answerString = a.getAnsText();
            hashAnswerIndexString.put(index, answerString);
            switch (index) {
                case 1:
                    answer1.setText(answerString);
                    answer1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    answer2.setText(answerString);
                    answer2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    answer3.setText(answerString);
                    answer3.setVisibility(View.VISIBLE);
                    break;
            }
        }

        ArrayList<Symptom.EmbeddedAlert> embeddedAlerts = new ArrayList<Symptom.EmbeddedAlert>(symptom.getAlerts());

        alertsLayout.setVisibility(View.VISIBLE);
        alertsEditLayout.setVisibility(View.GONE);
        addNewAlertButton.setVisibility(View.GONE);
        alertsLayout.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < embeddedAlerts.size(); i++) {
            TextView view = (TextView) inflater.inflate(R.layout.tile_simple_list_textview, alertsLayout, false);

            Symptom.EmbeddedAlert ea = embeddedAlerts.get(i);
            String alertText = hashAnswerIndexString.get(ea.getAnsIndex());
            String formattedAlert = getString(R.string.more_than) + " " + ea.getHours()
                    + " " + getString(R.string.hours_of) + " " + alertText;
            view.setText(formattedAlert);

            alertsLayout.addView(view);
        }
    }

    private void populateEditDetails() {
        symptomName.setVisibility(View.GONE);
        symptomNameEdit.setVisibility(View.VISIBLE);

        question.setVisibility(View.GONE);
        questionEdit.setVisibility(View.VISIBLE);

        answer1.setVisibility(View.GONE);
        answer2.setVisibility(View.GONE);
        answer3.setVisibility(View.GONE);
        answerEdit1.setVisibility(View.VISIBLE);
        answerEdit2.setVisibility(View.VISIBLE);
        answerEdit3.setVisibility(View.VISIBLE);

        alertsLayout.setVisibility(View.GONE);
        alertsEditLayout.setVisibility(View.VISIBLE);
        addNewAlertButton.setVisibility(View.VISIBLE);
        alertsEditLayout.removeAllViews();

        if (isNewSymptom) return;

        symptomNameEdit.setText(symptom.getName());
        questionEdit.setText(symptom.getQuestion());

        SparseArray<String> hashAnswerIndexString= new SparseArray<String>();
        for (Answer a : symptom.getAnswers()) {
            int index = a.getAnsIndex();
            String answerString = a.getAnsText();
            hashAnswerIndexString.put(index, answerString);
            switch (index) {
                case 1:
                    answerEdit1.setText(answerString);
                    break;
                case 2:
                    answerEdit2.setText(answerString);
                    break;
                case 3:
                    answerEdit3.setText(answerString);
                    break;
            }
        }

        ArrayList<Symptom.EmbeddedAlert> embeddedAlerts = new ArrayList<Symptom.EmbeddedAlert>(symptom.getAlerts());

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < embeddedAlerts.size(); i++) {
            final View view = inflater.inflate(R.layout.tile_simple_list_textview_with_delete, alertsLayout, false);

            TextView alertEditText = (TextView) view.findViewById(R.id.content);
            final ImageButton alertDelete = (ImageButton) view.findViewById(R.id.delete);

            Symptom.EmbeddedAlert ea = embeddedAlerts.get(i);
            String alertText = hashAnswerIndexString.get(ea.getAnsIndex());
            String formattedAlert = getString(R.string.more_than) + " " + ea.getHours()
                    + " " + getString(R.string.hours_of) + " " + alertText;
            alertEditText.setText(formattedAlert);

            alertDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertsEditLayout.removeView(view);
                }
            });

            view.setTag(R.string.tag_alert_hours, ea.getHours());
            view.setTag(R.string.tag_alert_ansindex, ea.getAnsIndex());

            alertsEditLayout.addView(view);
        }
    }

    @OnClick(R.id.add_new_alert_button)
    public void clickOnAddNewAnswer () {
        showAnswerPicker();
    }

    private void saveDetails() {
        showLoadingBar();


        String symptomName = symptomNameEdit.getText().toString();
        String question = questionEdit.getText().toString();
        String answer1 = answerEdit1.getText().toString();
        String answer2 = answerEdit2.getText().toString();
        String answer3 = answerEdit3.getText().toString();

        if ( "".equals(symptomName) || "".equals(question) || "".equals(answer1)  || "".equals(answer2)) {
            Toast.makeText(this, R.string.toast_save_details_fields_missing, Toast.LENGTH_SHORT).show();
            hideLoadingBar();
            return;
        }

        ArrayList<Symptom.EmbeddedAlert> embeddedAlerts = new ArrayList<Symptom.EmbeddedAlert>();

        for (int i = 0; i < alertsEditLayout.getChildCount(); i++) {
            View view = alertsEditLayout.getChildAt(i);

            int hours = (Integer) view.getTag(R.string.tag_alert_hours);
            int ansIndex = (Integer) view.getTag(R.string.tag_alert_ansindex);

            Symptom.EmbeddedAlert ea = new Symptom.EmbeddedAlert(hours, ansIndex);

            embeddedAlerts.add(ea);
        }

        ArrayList<Answer> answers = new ArrayList<Answer>();

        if (answer1 != null) {
            answers.add(new Answer(answer1, 1));
        }
        if (answer2 != null) {
            answers.add(new Answer(answer2, 2));
        }
        if (answer3 != null && !"".equals(answer3)) {
            answers.add(new Answer(answer3, 3));
        }

        symptom.setName(symptomName);
        symptom.setQuestion(question);
        symptom.setAlerts(embeddedAlerts);
        symptom.setAnswers(answers);

        presenter.postDetails(symptom);
    }

    private void showAnswerPicker() {
        AnswerPickerFragment apf = new AnswerPickerFragment();

        ArrayList<String> answersArrayList = new ArrayList<String>();

        String answer1 = answerEdit1.getText().toString();
        String answer2 = answerEdit2.getText().toString();
        String answer3 = answerEdit3.getText().toString();

        answersArrayList.add(answer1);
        answersArrayList.add(answer2);
        answersArrayList.add(answer3);

        Bundle args = new Bundle();
        args.putStringArrayList("answersArrayList", answersArrayList);
        apf.setArguments(args);

        apf.setCallBack(this);
        apf.show(getFragmentManager(), "AnswerPicker");
    }

    private void showHoursPicker(int ansIndex, String ansString) {
        HoursPickerFragment hpf = new HoursPickerFragment();

        Bundle args = new Bundle();
        args.putInt("ansIndex", ansIndex);
        args.putString("ansString", ansString);
        hpf.setArguments(args);

        hpf.setCallBack(this);
        hpf.show(getFragmentManager(), "HoursPicker");
    }

    private void finishAddAlert(int ansIndex, String ansString, int hours) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflater.inflate(R.layout.tile_simple_list_textview_with_delete, alertsLayout, false);

        TextView alertEditText = (TextView) view.findViewById(R.id.content);
        final ImageButton alertDelete = (ImageButton) view.findViewById(R.id.delete);

        String formattedAlert = getString(R.string.more_than) + " " + hours
                + " " + getString(R.string.hours_of) + " " + ansString;
        alertEditText.setText(formattedAlert);

        alertDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertsEditLayout.removeView(view);
            }
        });

        view.setTag(R.string.tag_alert_hours, hours);
        view.setTag(R.string.tag_alert_ansindex, ansIndex);

        alertsEditLayout.addView(view);
    }

    public static class AnswerPickerFragment extends DialogFragment {
        ArrayList<String> answersArrayList;
        WeakReference<SymptomDetailsActivity> view;

        public AnswerPickerFragment() {
        }

        public void setCallBack(SymptomDetailsActivity view) {
            this.view = new WeakReference<SymptomDetailsActivity>(view);
        }

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            answersArrayList = args.getStringArrayList("answersArrayList");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final CharSequence[] answers = answersArrayList.toArray(new CharSequence[answersArrayList.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(view.get());
            builder.setTitle(R.string.choose_answer)
                    .setItems(answers, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            view.get().showHoursPicker(which + 1, (String) answers[which]);
                        }
                    });
            return builder.create();
        }
    }

    public static class HoursPickerFragment extends DialogFragment {
        WeakReference<SymptomDetailsActivity> view;

        private int ansIndex;
        private String ansString;

        public HoursPickerFragment() {
        }

        public void setCallBack(SymptomDetailsActivity view) {
            this.view =  new WeakReference<SymptomDetailsActivity>(view);
        }

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            ansIndex = args.getInt("ansIndex");
            ansString = args.getString("ansString");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            final NumberPicker np = (NumberPicker) inflater.inflate(R.layout.dialog_numberpicker, null);

            np.setMinValue(0);
            np.setMaxValue(100);
            np.setValue(8);

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(np)
                    .setTitle(R.string.choose_hours)
                    // Add action buttons
                    .setPositiveButton(R.string.select, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            view.get().finishAddAlert(ansIndex, ansString, np.getValue());
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HoursPickerFragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();

        }
    }
}