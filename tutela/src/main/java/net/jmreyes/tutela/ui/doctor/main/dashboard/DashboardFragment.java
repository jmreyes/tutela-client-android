package net.jmreyes.tutela.ui.doctor.main.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Alert;
import net.jmreyes.tutela.model.extra.DoctorStatus;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.doctor.main.OnFragmentInteractionListener;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class DashboardFragment extends BaseFragment implements DashboardView {

    @Inject
    DashboardPresenter presenter;

    @InjectView(R.id.listView) ListView listView;

    @InjectView(R.id.top_bar) LinearLayout topBar;
    @InjectView(R.id.top_bar_text) TextView topBarText;

    private OnFragmentInteractionListener mListener;

    DashboardAdapter dashboardAdapter;

    public DashboardFragment() {
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
        View view = inflater.inflate(R.layout.fragment_doctor_dashboard, container, false);

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

    @OnItemClick(R.id.listView)
    void onItemSelected(int position, View v) {
//        String id = alertAdapter.getId(position);
//        Bundle bundle = new Bundle();
//        //bundle.putString(DoctorDetailsActivity.ARG_DOCTOR_ID, id);
//        mListener.loadActivity(OnFragmentInteractionListener.Subsections.DOCTOR_DETAILS, bundle, v);
    }


    @Override
    public void displayResults(List<Alert> results) {
        hideLoadingBar();
        dashboardAdapter = new DashboardAdapter(listView.getContext(), results, this);
        listView.setAdapter(dashboardAdapter);
    }

    @Override
    public void displayStatus(DoctorStatus doctorStatus) {
        int unseenAlerts = doctorStatus.getUnseenAlerts();
        if (unseenAlerts > 0) {
            topBar.setVisibility(View.VISIBLE);
            topBarText.setText(getString(R.string.dashboard_top_bar_text, unseenAlerts));
        }
        // TODO update nav drawer
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @OnClick(R.id.top_bar)
    public void onTopBarClick() {
        mListener.loadFragment(OnFragmentInteractionListener.Sections.ALERTS);
    }

    @OnClick(R.id.loadingLayoutRetryButton)
    public void onRetryClick() {
        hideErrorInLoadingBar();
        presenter.makeRequest();
    }
}
