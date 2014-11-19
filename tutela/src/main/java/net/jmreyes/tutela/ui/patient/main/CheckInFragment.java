package net.jmreyes.tutela.ui.patient.main;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.patient.main.presenter.CheckInPresenterImpl;
import net.jmreyes.tutela.ui.patient.main.view.CheckInView;

import javax.inject.Inject;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckInFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CheckInFragment extends BaseFragment implements CheckInView {
    @Inject
    CheckInPresenterImpl presenter;

    @InjectView(R.id.layout_checkin_ok) LinearLayout okLayout;
    @InjectView(R.id.layout_checkin_medication) LinearLayout medicationLayout;
    @InjectView(R.id.layout_checkin_symptom) LinearLayout symptomLayout;

    private OnFragmentInteractionListener mListener;

    public CheckInFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
        presenter.makeRequest();
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
        medicationLayout.setVisibility(View.VISIBLE);
        symptomLayout.setVisibility(View.GONE);
        okLayout.setVisibility(View.GONE);
    }

    @Override
    public void displaySymptom(String question, Collection<Answer> answers) {

    }

    @Override
    public void displayOkay() {
        medicationLayout.setVisibility(View.GONE);
        symptomLayout.setVisibility(View.GONE);
        okLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayError() {

    }
}
