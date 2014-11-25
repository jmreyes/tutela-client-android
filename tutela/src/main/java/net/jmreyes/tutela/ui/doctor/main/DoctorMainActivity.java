package net.jmreyes.tutela.ui.doctor.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.doctor.AbstractDrawerActivity;
import net.jmreyes.tutela.ui.doctor.alerts.AlertsFragment;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardFragment;
import net.jmreyes.tutela.ui.doctor.symptomdetails.SymptomDetailsActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class DoctorMainActivity extends AbstractDrawerActivity implements DoctorMainView, OnFragmentInteractionListener {

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

        loadFragment(Sections.DASHBOARD);
    }


    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DoctorMainModule(this));
    }


    @Override
    public void loadActivity(Subsections subsection, Bundle args, View transitionView) {
        Intent intent;
        String animationEndViewString = null;

        switch (subsection) {
            case SYMPTOM_DETAILS:
                intent = new Intent(this, SymptomDetailsActivity.class);
                animationEndViewString = getString(R.string.transition_action_bar);
                break;
            default:
                return;
        }

        if (args != null)
            intent.putExtras(args);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, transitionView, animationEndViewString);

        ActivityCompat.startActivity(this, intent,
                options.toBundle());
    }

    @Override
    public void loadFragment(Sections section) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (section) {
            case DASHBOARD:
                ft.replace(R.id.content_frame, new DashboardFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.doctor_dashboard));
                break;
            case ALERTS:
                ft.replace(R.id.content_frame, new AlertsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.alerts));
                break;
        }
    }

}
