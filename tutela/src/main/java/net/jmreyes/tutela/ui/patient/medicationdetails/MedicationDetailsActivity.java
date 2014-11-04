package net.jmreyes.tutela.ui.patient.medicationdetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 3/11/14.
 */
public class MedicationDetailsActivity extends BaseActivity implements MedicationDetailsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MedicationDetailsModule(this));
    }

    protected boolean requireLogin() {
        return false;
    }

}