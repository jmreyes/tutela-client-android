package net.jmreyes.tutela.ui.doctor.medicationdetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.ui.common.BaseActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 3/11/14.
 */
public class MedicationDetailsActivity extends BaseActivity implements MedicationDetailsView {
    public static final String ARG_MEDICATION_ID = "medicationId";

    @Inject
    MedicationDetailsPresenter presenter;

    @InjectView(R.id.subtitle) TextView medicationName;
    @InjectView(R.id.subtitle_edit) EditText medicatioNameEdit;

    private String medicationId;
    private Medication medication;

    private boolean isNewMedication;

    private boolean showingEditDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_details);
        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            medicationId = bundle.getString(ARG_MEDICATION_ID);
        } else {
            isNewMedication = true;
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

        if (medicationId != null && !isNewMedication) {
            showLoadingBar();
            presenter.makeRequest(medicationId);
        } else {
            medication = new Medication();
            populateEditDetails();
            showingEditDetails = true;
            invalidateOptionsMenu();
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MedicationDetailsModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!showingEditDetails) {
            getMenuInflater().inflate(R.menu.symptom_medication_details, menu);
        } else if (isNewMedication) {
            getMenuInflater().inflate(R.menu.symptom_medication_details_edit_new, menu);
        } else {
            getMenuInflater().inflate(R.menu.symptom_medication_details_edit, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public void displayResults(Medication medication) {
        hideLoadingBar();
        this.medication = medication;
        populateViewDetails();
        showingEditDetails = false;
        invalidateOptionsMenu();
    }

    @Override
    public void saveDetailsSuccess() {
        if (!isNewMedication) {
            hideLoadingBar();
            populateViewDetails();
            showingEditDetails = false;
            invalidateOptionsMenu();
        } else {
            Toast.makeText(this, R.string.toast_new_medication_success, Toast.LENGTH_SHORT).show();
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
        presenter.makeRequest(medicationId);
    }

    private void populateViewDetails() {
        medicationName.setVisibility(View.VISIBLE);
        medicatioNameEdit.setVisibility(View.GONE);
        medicationName.setText(medication.getName());
    }

    private void populateEditDetails() {
        medicationName.setVisibility(View.GONE);
        medicatioNameEdit.setVisibility(View.VISIBLE);

        if (isNewMedication) return;

        medicatioNameEdit.setText(medication.getName());
    }


    private void saveDetails() {
        showLoadingBar();

        String medicationName = medicatioNameEdit.getText().toString();
        medication.setName(medicationName);
        presenter.postDetails(medication);
    }

}