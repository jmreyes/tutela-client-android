package net.jmreyes.tutela.ui.patient.medicationdetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
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

    @Inject
    MedicationDetailsPresenter presenter;

    @InjectView(R.id.frequency_content) TextView frequency;
    @InjectView(R.id.notes_from_doctor_content) TextView notesFromDoctor;
    @InjectView(R.id.doctor_content) TextView doctorName;

    public static final String ARG_MEDICATION_ID = "medicationId";

    private String medicationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_details);
        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            medicationId = bundle.getString(ARG_MEDICATION_ID);
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
        showLoadingBar();
        presenter.makeRequest(medicationId);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MedicationDetailsModule(this));
    }

    protected boolean requireLogin() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayResults(Medication medication) {
        hideLoadingBar();

        frequency.setText(medication.getFrequency());
        notesFromDoctor.setText(medication.getNotes());
        doctorName.setText(medication.getDoctorName());
        getSupportActionBar().setTitle(medication.getName());
    }

    @Override
    public void displayError() {

    }
}