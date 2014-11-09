package net.jmreyes.tutela.ui.doctor.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.ui.common.BaseActivity;

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
