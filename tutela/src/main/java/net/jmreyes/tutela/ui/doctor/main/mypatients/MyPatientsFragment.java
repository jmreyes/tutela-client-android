package net.jmreyes.tutela.ui.doctor.main.mypatients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseFragment;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class MyPatientsFragment extends BaseFragment implements MyPatientsView {

    @Inject
    MyPatientsPresenter presenter;

    public MyPatientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);

        showLoadingBar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_patients, container, false);

        ButterKnife.inject(this, view);

        return view;
    }

}
