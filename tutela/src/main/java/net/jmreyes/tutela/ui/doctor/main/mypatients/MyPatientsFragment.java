package net.jmreyes.tutela.ui.doctor.main.mypatients;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.*;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.doctor.main.OnFragmentInteractionListener;
import net.jmreyes.tutela.ui.doctor.patientdetails.PatientDetailsActivity;
import net.jmreyes.tutela.ui.doctor.treatmentdetails.TreatmentDetailsActivity;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class MyPatientsFragment extends BaseFragment implements MyPatientsView, SearchView.OnQueryTextListener {

    @Inject
    MyPatientsPresenter presenter;

    @InjectView(R.id.listView) ListView listView;

    private OnFragmentInteractionListener mListener;

    MyPatientsAdapter myPatientsAdapter;

    public MyPatientsFragment() {
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
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_patients, container, false);
        setHasOptionsMenu(true);

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
    public void displayResults(List<PatientDetails> results) {
        hideLoadingBar();
        myPatientsAdapter = new MyPatientsAdapter(listView.getContext(), results, this);
        listView.setAdapter(myPatientsAdapter);
        //listView.setTextFilterEnabled(true);
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @Override
    public void loadTreatmentDetails(String treatmentId) {
        Bundle bundle = new Bundle();
        bundle.putString(TreatmentDetailsActivity.ARG_TREATMENT_ID, treatmentId);
        mListener.loadActivity(OnFragmentInteractionListener.Subsection.TREATMENT_DETAILS, bundle, null);
    }

    @OnItemClick(R.id.listView)
    void onItemSelected(int position, View v) {
        String id = myPatientsAdapter.getId(position);
        Bundle bundle = new Bundle();
        bundle.putString(PatientDetailsActivity.ARG_PATIENTDETAILS_ID, id);
        mListener.loadActivity(OnFragmentInteractionListener.Subsection.PATIENT_DETAILS, bundle, v);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.my_patients, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        setupSearchView(searchView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add:
                // TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search here");
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        showLoadingBar();
        presenter.makeSearchRequest(s);
//        Before: filter listView
//        if (TextUtils.isEmpty(s)) {
//            listView.clearTextFilter();
//        } else {
//            myPatientsAdapter.getFilter().filter(s);
//            //following line was causing the ugly popup window.
//            //m_listView.setFilterText(newText);
//        }
//        return true;

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
//        Before: filter listView
//        if (TextUtils.isEmpty(s)) {
//            listView.clearTextFilter();
//        }
        return false;
    }
}
