package net.jmreyes.tutela.ui.doctor.main.symptoms;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.ui.common.BaseFragment;
import net.jmreyes.tutela.ui.doctor.main.OnFragmentInteractionListener;
import net.jmreyes.tutela.ui.doctor.symptomdetails.SymptomDetailsActivity;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class SymptomsFragment extends BaseFragment implements SymptomsView {

    @Inject
    SymptomsPresenter presenter;

    @InjectView(R.id.listView) ListView listView;

    private OnFragmentInteractionListener mListener;

    SymptomsAdapter symptomsAdapter;

    public SymptomsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);

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
    public void displayResults(List<Symptom> results) {
        hideLoadingBar();
        symptomsAdapter = new SymptomsAdapter(listView.getContext(), results, this);
        listView.setAdapter(symptomsAdapter);
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @OnItemClick(R.id.listView)
    void onItemSelected(int position, View v) {
        String id = symptomsAdapter.getId(position);
        Bundle bundle = new Bundle();
        bundle.putString(SymptomDetailsActivity.ARG_SYMPTOM_ID, id);
        mListener.loadActivity(OnFragmentInteractionListener.Subsections.SYMPTOM_DETAILS, bundle, v);
    }
}
