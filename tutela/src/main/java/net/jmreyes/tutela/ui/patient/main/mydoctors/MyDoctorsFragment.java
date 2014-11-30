package net.jmreyes.tutela.ui.patient.main.mydoctors;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import butterknife.*;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.extra.MyDoctor;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.patient.doctordetails.DoctorDetailsActivity;
import net.jmreyes.tutela.ui.patient.main.OnFragmentInteractionListener;

import javax.inject.Inject;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link net.jmreyes.tutela.ui.patient.main.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyDoctorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MyDoctorsFragment extends BaseFragment implements MyDoctorsView {
    @Inject
    MyDoctorsPresenter presenter;

    @InjectView(R.id.listView) ListView listView;

    private OnFragmentInteractionListener mListener;

    MyDoctorsListAdapter myDoctorsListAdapter;

    public MyDoctorsFragment() {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_doctors, container, false);

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
    public void displayResults(List<MyDoctor> results) {
        hideLoadingBar();
        myDoctorsListAdapter = new MyDoctorsListAdapter(listView.getContext(), results);
        listView.setAdapter(myDoctorsListAdapter);
    }

    @OnItemClick(R.id.listView)
    void onItemSelected(int position, View v) {
        String id = myDoctorsListAdapter.getId(position);
        Bundle bundle = new Bundle();
        bundle.putString(DoctorDetailsActivity.ARG_DOCTOR_ID, id);
        mListener.loadActivity(OnFragmentInteractionListener.Subsections.DOCTOR_DETAILS, bundle, v);
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @OnClick(R.id.loadingLayoutRetryButton)
    public void onRetryClick() {
        hideErrorInLoadingBar();
        presenter.makeRequest();
    }

}
