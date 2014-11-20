package net.jmreyes.tutela.ui.patient.main.aux;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

// From http://androidtrainningcenter.blogspot.de/2012/10/creating-datepicker-using.html
public class TimePickerFragment extends DialogFragment {
    private int hourOfDay, minute;
    TimePickerDialog.OnTimeSetListener onTimeSet;

    public TimePickerFragment() {
    }

    public void setCallBack(TimePickerDialog.OnTimeSetListener ontime) {
        onTimeSet = ontime;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        hourOfDay = args.getInt("hourOfDay");
        minute = args.getInt("minute");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), onTimeSet, hourOfDay, minute, true );
    }
}