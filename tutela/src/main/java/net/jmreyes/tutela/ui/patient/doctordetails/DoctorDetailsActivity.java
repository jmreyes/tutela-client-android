package net.jmreyes.tutela.ui.patient.doctordetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.ui.common.BaseActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class DoctorDetailsActivity extends BaseActivity implements DoctorDetailsView {
    public static final String ARG_DOCTOR_ID = "doctorId";

    @Inject
    DoctorDetailsPresenter presenter;

    @InjectView(R.id.firstname_content) TextView firstName;
    @InjectView(R.id.lastname_content) TextView lastName;
    @InjectView(R.id.email_content) TextView email;
    @InjectView(R.id.phone_number_content) TextView phoneNumber;

    private String doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            doctorId = bundle.getString(ARG_DOCTOR_ID);
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
        presenter.makeRequest(doctorId);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DoctorDetailsModule(this));
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
    public void displayResults(Doctor doctor) {
        hideLoadingBar();

        firstName.setText(doctor.getFirstName());
        lastName.setText(doctor.getLastName());
        email.setText(doctor.getEmail());
        phoneNumber.setText(doctor.getPhoneNumber());
        getSupportActionBar().setTitle(doctor.getName());
    }

    @Override
    public void displayError() {
        showErrorInLoadingBar(null);
    }

    @OnClick(R.id.loadingLayoutRetryButton)
    public void onRetryClick() {
        hideErrorInLoadingBar();
        presenter.makeRequest(doctorId);
    }
}
