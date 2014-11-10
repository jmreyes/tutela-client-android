package net.jmreyes.tutela.ui.doctor.alerts;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.doctor.AbstractDrawerActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class AlertsActivity extends AbstractDrawerActivity implements AlertsView {

    @Inject
    AlertsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        setUpNavigationDrawer(toolbar);
    }


    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AlertsModule(this));
    }
}
