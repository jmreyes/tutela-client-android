package net.jmreyes.tutela.ui.patient.main;

import android.os.Bundle;
import android.view.View;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    public enum Subsections {
        MEDICATION_DETAILS,
        DOCTOR_DETAILS
    }

    void loadActivity(Subsections subsection, Bundle args, View transitionView);
}
