package net.jmreyes.tutela.ui.doctor;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseActivity;
import net.jmreyes.tutela.ui.doctor.alerts.AlertsActivity;
import net.jmreyes.tutela.ui.doctor.main.DoctorMainActivity;
import net.jmreyes.tutela.ui.doctor.medication.MedicationActivity;
import net.jmreyes.tutela.ui.doctor.mypatients.MyPatientsActivity;
import net.jmreyes.tutela.ui.doctor.symptoms.SymptomsActivity;

/**
 * Created by juanma on 9/11/14.
 */
public abstract class AbstractDrawerActivity extends BaseActivity {

    protected void setUpNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);

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
        Intent intent;

        switch (v.getId()) {
            case R.id.navigation_drawer_section_doctor_dashboard:
                intent = new Intent(this, DoctorMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                break;
            case R.id.navigation_drawer_section_my_patients:
                intent = new Intent(this, MyPatientsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case R.id.navigation_drawer_section_symptoms:
                intent = new Intent(this, SymptomsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                break;
            case R.id.navigation_drawer_section_medication:
                intent = new Intent(this, MedicationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                break;
            case R.id.navigation_drawer_section_alerts:
                intent = new Intent(this, AlertsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                break;
            case R.id.navigation_drawer_section_settings:
                return;
            case R.id.navigation_drawer_section_about:
                return;
            default:
                return;
        }

        startActivity(intent);

        overridePendingTransition(0, 0);
    }

}
