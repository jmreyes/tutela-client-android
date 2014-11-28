package net.jmreyes.tutela.ui.patient.main.checkin;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.aux.SharedPreferencesHelper;
import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.model.extra.Reminder;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.patient.main.OnFragmentInteractionListener;
import net.jmreyes.tutela.ui.patient.main.aux.DatePickerFragment;
import net.jmreyes.tutela.ui.patient.main.aux.TimePickerFragment;
import net.jmreyes.tutela.ui.patient.main.checkin.CheckInPresenter;
import net.jmreyes.tutela.ui.patient.main.checkin.CheckInView;

import javax.inject.Inject;
import java.util.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link net.jmreyes.tutela.ui.patient.main.OnFragmentInteractionListener} interface
 * to handle interaction events.
 *
 */
public class CheckInFragment extends BaseFragment implements CheckInView {
    @Inject
    CheckInPresenter presenter;

    @InjectView(R.id.layout_checkin_ok) LinearLayout okLayout;
    @InjectView(R.id.layout_checkin_medication) LinearLayout medicationLayout;
    @InjectView(R.id.layout_checkin_symptom) LinearLayout symptomLayout;

    @InjectView(R.id.layout_checkin_footer) LinearLayout footerLayout;
    @InjectView(R.id.questions_left_text) TextView questionsLeftText;
    @InjectView(R.id.previous_question_button) Button previousQuestionButton;

    @InjectView(R.id.medication_name_text) TextView medicationNameText;
    @InjectView(R.id.question_text) TextView questionText;

    @InjectView(R.id.symptom_button1) Button symptomButton1;
    @InjectView(R.id.symptom_button2) Button symptomButton2;
    @InjectView(R.id.symptom_button3) Button symptomButton3;

    private OnFragmentInteractionListener mListener;

    public CheckInFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);

        showLoadingBar();

        Calendar now = Calendar.getInstance();

        List<Reminder> reminders = SharedPreferencesHelper.getReminders(getActivity());
        for (Reminder r : reminders) {
            if (r.getCalendar().getTimeInMillis() < now.getTimeInMillis()) {
                presenter.makeRequest();
                break;
            }
        }

        displayOkay(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_in, container, false);

        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void displayMedication(String medicationName) {
        hideLoadingBar();
        medicationLayout.setVisibility(View.VISIBLE);
        symptomLayout.setVisibility(View.GONE);
        okLayout.setVisibility(View.GONE);

        footerLayout.setVisibility(View.VISIBLE);

        medicationNameText.setText(getString(R.string.did_you_take_2, medicationName));
    }

    @Override
    public void displaySymptom(String question, Collection<Answer> answers) {
        hideLoadingBar();
        medicationLayout.setVisibility(View.GONE);
        symptomLayout.setVisibility(View.VISIBLE);
        okLayout.setVisibility(View.GONE);

        footerLayout.setVisibility(View.VISIBLE);

        symptomButton1.setVisibility(View.GONE);
        symptomButton2.setVisibility(View.GONE);
        symptomButton3.setVisibility(View.GONE);

        questionText.setText(question);

        for (Answer a : answers) {
            switch (a.getAnsIndex()) {
                case 1:
                    symptomButton1.setVisibility(View.VISIBLE);
                    symptomButton1.setText(a.getAnsText());
                    symptomButton1.setTag(a);
                    break;
                case 2:
                    symptomButton2.setVisibility(View.VISIBLE);
                    symptomButton2.setText(a.getAnsText());
                    symptomButton2.setTag(a);
                    break;
                case 3:
                    symptomButton3.setVisibility(View.VISIBLE);
                    symptomButton3.setText(a.getAnsText());
                    symptomButton3.setTag(a);
                    break;
            }
        }
    }

    @Override
    public void displayOkay(boolean justCheckedIn) {
        hideLoadingBar();

        if (justCheckedIn) SharedPreferencesHelper.resetReminders(getActivity());

        medicationLayout.setVisibility(View.GONE);
        symptomLayout.setVisibility(View.GONE);
        okLayout.setVisibility(View.VISIBLE);

        footerLayout.setVisibility(View.GONE);
    }

    @Override
    public void updateFooter(int questionsLeft, boolean showPreviousButton) {
        questionsLeftText.setText(getString(R.string.questions_left, questionsLeft));
        previousQuestionButton.setVisibility(showPreviousButton ? View.VISIBLE : View.INVISIBLE);
    }


    @OnClick({ R.id.medication_button_no, R.id.medication_button_yes })
    public void answerMedication(View view) {
        switch (view.getId()) {
            case R.id.medication_button_no:
                presenter.registerMedication(false, new Date());
                break;
            case R.id.medication_button_yes:
                showDatePicker();
                break;
        }
    }

    @OnClick({ R.id.symptom_button1, R.id.symptom_button2, R.id.symptom_button3 })
    public void answerSymptom(View view) {
        presenter.registerSymptom((Answer) view.getTag());
    }

    @OnClick(R.id.previous_question_button)
    public void previousQuestion() {
        presenter.previousQuestion();
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @Override
    public void displayLoadingBar() {
        showLoadingBar();
    }

    @OnClick(R.id.loadingLayoutRetryButton)
    public void onRetryClick() {
        hideErrorInLoadingBar();
        presenter.makeRequest();
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();

        Calendar calendar =  Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("monthOfYear", calendar.get(Calendar.MONTH) + 1);
        args.putInt("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        date.setCallBack(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear - 1);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                showTimePicker(calendar);
            }
        });
        date.show(getFragmentManager(), "DatePicker");
    }

    private void showTimePicker(final Calendar calendar) {
        TimePickerFragment date = new TimePickerFragment();

        Bundle args = new Bundle();
        args.putInt("hourOfDay", calendar.get(Calendar.HOUR_OF_DAY));
        args.putInt("minute", calendar.get(Calendar.MINUTE));
        date.setArguments(args);

        date.setCallBack(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                presenter.registerMedication(true, calendar.getTime());
            }
        });
        date.show(getFragmentManager(), "TimePicker");
    }

}
