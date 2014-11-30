package net.jmreyes.tutela.ui.doctor.patientdetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.ui.common.BaseActivity;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 3/11/14.
 */
public class PatientDetailsActivity extends BaseActivity implements PatientDetailsView {
    public static final String ARG_PATIENTDETAILS_ID = "symptomId";

    @Inject
    PatientDetailsPresenter presenter;

    @InjectView(R.id.subtitle) TextView fullName;
    @InjectView(R.id.firstname_content) TextView firstName;
    @InjectView(R.id.firstname_edit) EditText firstNameEdit;
    @InjectView(R.id.lastname_content) TextView lastName;
    @InjectView(R.id.lastname_edit) EditText lastNameEdit;
    @InjectView(R.id.dateofbirth_content) TextView dateOfBirth;
    @InjectView(R.id.dateofbirth_edit) EditText dateOfBirthEdit;
    @InjectView(R.id.mrn_content) TextView mrn;
    @InjectView(R.id.mrn_edit) EditText mrnEdit;
    @InjectView(R.id.email_content) TextView email;
    @InjectView(R.id.email_edit) EditText emailEdit;
    @InjectView(R.id.phone_content) TextView phone;
    @InjectView(R.id.phone_edit) EditText phoneEdit;

    private String patientDetailsId;
    private PatientDetails patientDetails;

    private boolean isNewPatientDetails;

    private boolean showingEditDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            patientDetailsId = bundle.getString(ARG_PATIENTDETAILS_ID);
        } else {
            isNewPatientDetails = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (patientDetailsId != null && !isNewPatientDetails) {
            showLoadingBar();
            presenter.makeRequest(patientDetailsId);
        } else {
            patientDetails = new PatientDetails();
            populateEditDetails();
            showingEditDetails = true;
            invalidateOptionsMenu();
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new PatientDetailsModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!showingEditDetails) {
            getMenuInflater().inflate(R.menu.patient_details, menu);
        } else if (isNewPatientDetails) {
            getMenuInflater().inflate(R.menu.patient_details_edit_new, menu);
        } else {
            getMenuInflater().inflate(R.menu.patient_details_edit, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_edit_details:
                populateEditDetails();
                showingEditDetails = true;
                invalidateOptionsMenu();
                break;
            case R.id.action_edit_details_cancel:
                populateViewDetails();
                showingEditDetails = false;
                invalidateOptionsMenu();
                break;
            case R.id.action_edit_details_done:
                saveDetails();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayResults(PatientDetails patientDetails) {
        hideLoadingBar();
        this.patientDetails = patientDetails;
        populateViewDetails();
        showingEditDetails = false;
        invalidateOptionsMenu();
    }

    @Override
    public void saveDetailsSuccess() {
        if (!isNewPatientDetails) {
            hideLoadingBar();
            populateViewDetails();
            showingEditDetails = false;
            invalidateOptionsMenu();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void saveDetailsError() {
        hideLoadingBar();
        Toast.makeText(this, R.string.toast_save_details_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayResultsError() {
        showErrorInLoadingBar(null);
    }

    @OnClick(R.id.loadingLayoutRetryButton)
    public void onRetryClick() {
        hideErrorInLoadingBar();
        presenter.makeRequest(patientDetailsId);
    }

    private void populateViewDetails() {
        fullName.setText(patientDetails.getFullName());

        firstName.setVisibility(View.VISIBLE);
        firstNameEdit.setVisibility(View.GONE);
        firstName.setText(patientDetails.getFirstName());

        lastName.setVisibility(View.VISIBLE);
        lastNameEdit.setVisibility(View.GONE);
        lastName.setText(patientDetails.getLastName());

        dateOfBirth.setVisibility(View.VISIBLE);
        dateOfBirthEdit.setVisibility(View.GONE);
        dateOfBirth.setText(patientDetails.getDateOfBirth());

        mrn.setVisibility(View.VISIBLE);
        mrnEdit.setVisibility(View.GONE);
        mrn.setText(patientDetails.getMrn());

        email.setVisibility(View.VISIBLE);
        emailEdit.setVisibility(View.GONE);
        email.setText(patientDetails.getEmail());

        phone.setVisibility(View.VISIBLE);
        phoneEdit.setVisibility(View.GONE);
        phone.setText(patientDetails.getPhoneNumber());
    }

    private void populateEditDetails() {
        firstName.setVisibility(View.GONE);
        firstNameEdit.setVisibility(View.VISIBLE);

        lastName.setVisibility(View.GONE);
        lastNameEdit.setVisibility(View.VISIBLE);

        dateOfBirth.setVisibility(View.GONE);
        dateOfBirthEdit.setVisibility(View.VISIBLE);

        mrn.setVisibility(View.GONE);
        mrnEdit.setVisibility(View.VISIBLE);

        email.setVisibility(View.GONE);
        emailEdit.setVisibility(View.VISIBLE);

        phone.setVisibility(View.GONE);
        phoneEdit.setVisibility(View.VISIBLE);

        if (isNewPatientDetails) return;

        fullName.setText(patientDetails.getFullName());
        firstNameEdit.setText(patientDetails.getFirstName());
        lastNameEdit.setText(patientDetails.getLastName());
        dateOfBirthEdit.setText(patientDetails.getDateOfBirth());
        mrnEdit.setText(patientDetails.getMrn());
        emailEdit.setText(patientDetails.getEmail());
        phoneEdit.setText(patientDetails.getPhoneNumber());
    }

    private void saveDetails() {
        showLoadingBar();

        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String dateOfBirth = dateOfBirthEdit.getText().toString();
        String mrn = mrnEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String phone = phoneEdit.getText().toString();

        if ( "".equals(firstName) || "".equals(lastName) ) {
            Toast.makeText(this, R.string.toast_save_details_fields_missing, Toast.LENGTH_SHORT).show();
            hideLoadingBar();
            return;
        }

        patientDetails.setFirstName(firstName);
        patientDetails.setLastName(lastName);
        patientDetails.setDateOfBirth(dateOfBirth);
        patientDetails.setMrn(mrn);
        patientDetails.setEmail(email);
        patientDetails.setPhoneNumber(phone);

        presenter.postDetails(patientDetailsId, patientDetails);
    }
}