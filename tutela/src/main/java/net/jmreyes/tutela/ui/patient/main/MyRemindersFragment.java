package net.jmreyes.tutela.ui.patient.main;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TimePicker;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.aux.SharedPreferencesHelper;
import net.jmreyes.tutela.model.extra.Alarm;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.patient.main.adapter.MyRemindersListAdapter;
import net.jmreyes.tutela.ui.patient.main.aux.TimePickerFragment;
import net.jmreyes.tutela.ui.patient.main.presenter.MyRemindersPresenter;
import net.jmreyes.tutela.ui.patient.main.view.MyRemindersView;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 *
 */
public class MyRemindersFragment extends BaseFragment implements MyRemindersView {
    @Inject
    MyRemindersPresenter presenter;

    @InjectView(R.id.listView) ListView listView;

    private OnFragmentInteractionListener mListener;

    MyRemindersListAdapter myRemindersListAdapter;

    public MyRemindersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);

        //showLoadingBar();

        List<Alarm> alarms = SharedPreferencesHelper.getAlarmsFromSharedPreferences(getActivity());
        myRemindersListAdapter = new MyRemindersListAdapter(getActivity(), alarms, this);
        listView.setAdapter(myRemindersListAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_reminders, container, false);

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
    public void displayResults(List<Alarm> results) {
        hideLoadingBar();
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @Override
    public void removeAlarm(Alarm alarm) {
        myRemindersListAdapter.remove(alarm);
        myRemindersListAdapter.notifyDataSetChanged();
        SharedPreferencesHelper.removeAlarmFromSharedPreferences(getActivity(), alarm);
    }

    @Override
    public void editAlarm(Alarm alarm) {

    }

    @OnClick(R.id.add_alarm_button)
    public void onClickAddAlarmButton() {
        showDatePicker();
    }

    private void showDatePicker() {
        TimePickerFragment date = new TimePickerFragment();

        Calendar calendar =  Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("hourOfDay", calendar.get(Calendar.HOUR_OF_DAY));
        args.putInt("minute", calendar.get(Calendar.MINUTE));
        date.setArguments(args);

        date.setCallBack(onTimeSetListener);
        date.show(getFragmentManager(), "DatePicker");
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            Alarm alarm = new Alarm(calendar);

            SharedPreferencesHelper.addAlarmToSharedPreferences(getActivity(), alarm);
            setAlarm(alarm);

            myRemindersListAdapter.add(alarm);
            myRemindersListAdapter.notifyDataSetChanged();
        }
    };


    private void setAlarm(Alarm alarm) {};
}
