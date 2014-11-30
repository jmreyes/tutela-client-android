package net.jmreyes.tutela.ui.doctor;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.extra.DoctorStatus;
import net.jmreyes.tutela.ui.common.BaseActivity;
import net.jmreyes.tutela.ui.doctor.main.OnFragmentInteractionListener;
import net.jmreyes.tutela.ui.doctor.main.alerts.AlertFragment;
import net.jmreyes.tutela.ui.doctor.main.dashboard.DashboardFragment;
import net.jmreyes.tutela.ui.doctor.main.medication.MedicationFragment;
import net.jmreyes.tutela.ui.doctor.main.mypatients.MyPatientsFragment;
import net.jmreyes.tutela.ui.doctor.main.symptoms.SymptomsFragment;
import net.jmreyes.tutela.ui.doctor.medicationdetails.MedicationDetailsActivity;
import net.jmreyes.tutela.ui.doctor.patientdetails.PatientDetailsActivity;
import net.jmreyes.tutela.ui.doctor.symptomdetails.SymptomDetailsActivity;
import net.jmreyes.tutela.ui.doctor.treatmentdetails.TreatmentDetailsActivity;

/**
 * Created by juanma on 9/11/14.
 */
public abstract class AbstractDrawerActivity extends BaseActivity implements OnFragmentInteractionListener {
    @InjectView(R.id.content_frame) FrameLayout contentFrame;
    @InjectView(R.id.my_drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.drawer_unseen_alerts_text) TextView unseenAlertsText;
    @InjectView(R.id.drawer_doctor_name_text) TextView doctorNameText;
    @InjectView(R.id.drawer_doctor_username_text) TextView doctorUsernameText;

    protected void setUpNavigationDrawer(Toolbar toolbar) {

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,  drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
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

    @Override
    public void updateNavigationDrawer(DoctorStatus doctorStatus) {
        int unseenAlerts = doctorStatus.getUnseenAlerts();
        unseenAlertsText.setText(Integer.toString(unseenAlerts));
        if (unseenAlerts > 0) {
            unseenAlertsText.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            unseenAlertsText.setBackgroundColor(getResources().getColor(R.color.button_material_dark));
        }

        doctorNameText.setText(doctorStatus.getName());
        doctorUsernameText.setText(doctorStatus.getUsername());
    }

    @Override
    public void updateNavigationDrawerUnseenAlerts(int unseenAlerts) {
        unseenAlertsText.setText(Integer.toString(unseenAlerts));
        if (unseenAlerts > 0) {
            unseenAlertsText.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            unseenAlertsText.setBackgroundColor(getResources().getColor(R.color.button_material_dark));
        }
    }

    @OnClick({R.id.navigation_drawer_section_doctor_dashboard,
            R.id.navigation_drawer_section_my_patients,
            R.id.navigation_drawer_section_symptoms,
            R.id.navigation_drawer_section_medication,
            R.id.navigation_drawer_section_alerts,
            R.id.navigation_drawer_section_settings,
            R.id.navigation_drawer_section_about,
            R.id.navigation_drawer_section_logout
    })
    public void loadSection(View v) {
        switch (v.getId()) {
            case R.id.navigation_drawer_section_doctor_dashboard:
                loadFragment(Section.DASHBOARD);
                break;
            case R.id.navigation_drawer_section_my_patients:
                loadFragment(Section.MY_PATIENTS);
                break;
            case R.id.navigation_drawer_section_symptoms:
                loadFragment(Section.SYMPTOMS);
                break;
            case R.id.navigation_drawer_section_medication:
                loadFragment(Section.MEDICATION);
                break;
            case R.id.navigation_drawer_section_alerts:
                loadFragment(Section.ALERTS);
                break;
            case R.id.navigation_drawer_section_settings:
                return;
            case R.id.navigation_drawer_section_about:
                return;
            case R.id.navigation_drawer_section_logout:
                userLogout();
                return;
        }

        drawerLayout.closeDrawers();
    }

    @Override
    public void loadActivity(Subsection subsection, Bundle args, View transitionView) {
        Intent intent;
        String animationEndViewString = null;

        switch (subsection) {
            case SYMPTOM_DETAILS:
                intent = new Intent(this, SymptomDetailsActivity.class);
                animationEndViewString = getString(R.string.transition_action_bar);
                break;
            case MEDICATION_DETAILS:
                intent = new Intent(this, MedicationDetailsActivity.class);
                animationEndViewString = getString(R.string.transition_action_bar);
                break;
            case PATIENT_DETAILS:
                intent = new Intent(this, PatientDetailsActivity.class);
                animationEndViewString = getString(R.string.transition_action_bar);
                break;
            case TREATMENT_DETAILS:
                intent = new Intent(this, TreatmentDetailsActivity.class);
                animationEndViewString = getString(R.string.transition_action_bar);
                break;
            default:
                return;
        }

        if (args != null)
            intent.putExtras(args);

        if (transitionView != null) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, transitionView, animationEndViewString);

            ActivityCompat.startActivity(this, intent,
                    options.toBundle());
        } else {
            ActivityCompat.startActivity(this, intent,
                    null);
        }

    }

    @Override
    public void loadFragment(Section section) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (section) {
            case DASHBOARD:
                ft.replace(R.id.content_frame, new DashboardFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.doctor_dashboard));
                break;
            case MY_PATIENTS:
                ft.replace(R.id.content_frame, new MyPatientsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.my_patients));
                break;
            case SYMPTOMS:
                ft.replace(R.id.content_frame, new SymptomsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.symptoms));
                break;
            case MEDICATION:
                ft.replace(R.id.content_frame, new MedicationFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.medication));
                break;
            case ALERTS:
                ft.replace(R.id.content_frame, new AlertFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.alerts));
                break;
        }
    }
}
