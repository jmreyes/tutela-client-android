package net.jmreyes.tutela.ui.patient.main.aux;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

// From http://androidtrainningcenter.blogspot.de/2012/10/creating-datepicker-using.html
public class DatePickerFragment extends DialogFragment {
    private int year, monthOfYear, dayOfMonth;
    DatePickerDialog.OnDateSetListener onDateSet;

    public DatePickerFragment() {
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener onDate) {
        onDateSet = onDate;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        monthOfYear = args.getInt("monthOfYear");
        dayOfMonth = args.getInt("dayOfMonth");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), onDateSet, year, monthOfYear, dayOfMonth );
    }
}