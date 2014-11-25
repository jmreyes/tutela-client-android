package net.jmreyes.tutela.ui.doctor.main;

import android.os.Bundle;
import android.view.View;
import net.jmreyes.tutela.model.extra.DoctorStatus;

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
        SYMPTOM_DETAILS
    }

    public enum Sections {
        DASHBOARD,
        ALERTS
    }

    void loadActivity(Subsections subsection, Bundle args, View transitionView);

    void loadFragment(Sections section);

    void updateNavigationDrawer(DoctorStatus doctorStatus);
}