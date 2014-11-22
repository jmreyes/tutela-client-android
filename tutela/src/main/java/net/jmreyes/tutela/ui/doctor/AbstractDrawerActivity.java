package net.jmreyes.tutela.ui.doctor;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseActivity;
import net.jmreyes.tutela.ui.doctor.alerts.AlertsFragment;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardFragment;
import net.jmreyes.tutela.ui.doctor.main.medication.MedicationFragment;
import net.jmreyes.tutela.ui.doctor.main.mypatients.MyPatientsFragment;
import net.jmreyes.tutela.ui.doctor.main.symptoms.SymptomsFragment;

/**
 * Created by juanma on 9/11/14.
 */
public abstract class AbstractDrawerActivity extends BaseActivity {
    @InjectView(R.id.content_frame) FrameLayout contentFrame;
    @InjectView(R.id.my_drawer_layout) DrawerLayout drawerLayout;

    protected void setUpNavigationDrawer(Toolbar toolbar) {

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,  drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle.syncState();
    }



    @OnClick({R.id.navigation_drawer_section_doctor_dashboard,
            R.id.navigation_drawer_section_my_patients,
            R.id.navigation_drawer_section_symptoms,
            R.id.navigation_drawer_section_medication,
            R.id.navigation_drawer_section_alerts,
            R.id.navigation_drawer_section_settings,
            R.id.navigation_drawer_section_about
    })
    public void loadSubSection(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.navigation_drawer_section_doctor_dashboard:
                ft.replace(R.id.content_frame, new DashboardFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.doctor_dashboard));
                break;
            case R.id.navigation_drawer_section_my_patients:
                ft.replace(R.id.content_frame, new MyPatientsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.my_patients));
                break;
            case R.id.navigation_drawer_section_symptoms:
                ft.replace(R.id.content_frame, new SymptomsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.symptoms));
                break;
            case R.id.navigation_drawer_section_medication:
                ft.replace(R.id.content_frame, new MedicationFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.medication));
                break;
            case R.id.navigation_drawer_section_alerts:
                ft.replace(R.id.content_frame, new AlertsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.alerts));
                break;
            case R.id.navigation_drawer_section_settings:
                return;
            case R.id.navigation_drawer_section_about:
                return;
        }

        drawerLayout.closeDrawers();
    }

}
