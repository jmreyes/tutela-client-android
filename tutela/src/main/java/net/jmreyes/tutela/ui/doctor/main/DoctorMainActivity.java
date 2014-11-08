package net.jmreyes.tutela.ui.doctor.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class DoctorMainActivity extends BaseActivity implements DoctorMainView {

    @Inject
    DoctorMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);

            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                    this,  drawerLayout, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close
            ){
                public void onDrawerClosed(View view)
                {
                    super.onDrawerClosed(view);
                    invalidateOptionsMenu();
                    syncState();
                }

                public void onDrawerOpened(View drawerView)
                {
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

    }


    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DoctorMainModule(this));
    }
}
