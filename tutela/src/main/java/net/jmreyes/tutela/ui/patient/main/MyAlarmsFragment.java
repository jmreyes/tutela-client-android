package net.jmreyes.tutela.ui.patient.main;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.patient.main.presenter.MyAlarmsPresenter;
import net.jmreyes.tutela.ui.patient.main.view.MyAlarmsView;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAlarmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MyAlarmsFragment extends BaseFragment implements MyAlarmsView {
    @Inject
    MyAlarmsPresenter presenter;

    private OnFragmentInteractionListener mListener;

    public MyAlarmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_alarms, container, false);
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

}
