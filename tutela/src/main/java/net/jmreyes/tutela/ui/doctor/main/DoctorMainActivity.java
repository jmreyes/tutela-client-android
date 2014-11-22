package net.jmreyes.tutela.ui.doctor.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.doctor.AbstractDrawerActivity;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardFragment;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class DoctorMainActivity extends AbstractDrawerActivity implements DoctorMainView {

    @Inject
    DoctorMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        setUpNavigationDrawer(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new DashboardFragment()).commit();
        getSupportActionBar().setTitle(getString(R.string.doctor_dashboard));
    }


    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DoctorMainModule(this));
    }
}
