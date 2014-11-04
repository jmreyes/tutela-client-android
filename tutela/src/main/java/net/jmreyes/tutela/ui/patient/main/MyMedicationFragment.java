package net.jmreyes.tutela.ui.patient.main;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.patient.main.adapter.MyMedicationListAdapter;
import net.jmreyes.tutela.ui.patient.main.presenter.MyMedicationPresenter;
import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 *
 */
public class MyMedicationFragment extends BaseFragment implements MyMedicationView {
    @Inject MyMedicationPresenter presenter;

    @InjectView(R.id.listView) ListView listView;

    private OnFragmentInteractionListener mListener;

    public MyMedicationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);

        showLoadingBar();
        presenter.makeRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_medication, container, false);

        ButterKnife.inject(this, view);

        // Inflate the layout for this fragment
//        List<Medication> medList = new ArrayList<Medication>(12);
//        for (int i = 0; i < 12.; i++) {
//            medList.add(new Medication());
//        }
//        MyMedicationListAdapter myMedicationListAdapter = new MyMedicationListAdapter(view.getContext(), medList);
//        listView.setAdapter(myMedicationListAdapter);

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
    public void displayResults(List<Medication> results) {
        hideLoadingBar();
        MyMedicationListAdapter myMedicationListAdapter = new MyMedicationListAdapter(listView.getContext(), results);
        listView.setAdapter(myMedicationListAdapter);
    }

    @OnItemClick(R.id.listView)
    void onItemSelected(int position, View v) {
        mListener.loadActivity(OnFragmentInteractionListener.Subsections.MEDICATION_DETAILS, null, v);
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }
}
