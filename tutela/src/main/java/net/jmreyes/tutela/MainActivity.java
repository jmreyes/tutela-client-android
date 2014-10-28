package net.jmreyes.tutela;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import net.jmreyes.tutela.aux.PagerSlidingTabStrip;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setTextColor(Color.WHITE);
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

    public static class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new PlaceholderFragment();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new PlaceholderFragment();
                case 2: // Fragment # 1 - This will show SecondFragment
                    return new PlaceholderFragment();
                case 3: // Fragment # 1 - This will show SecondFragment
                    return new PlaceholderFragment();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public int getPageIconResId(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return R.drawable.ic_home_black_36dp;
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return R.drawable.ic_local_pharmacy_black_36dp;
                case 2: // Fragment # 1 - This will show SecondFragment
                    return R.drawable.ic_healthcare_stethoscope;
                case 3: // Fragment # 1 - This will show SecondFragment
                    return R.drawable.ic_alarm_add_black_36dp;
                default:
                    return 0;
            }
        }


        /**
         * A placeholder fragment containing a simple view.
         */
        public static class PlaceholderFragment extends Fragment {

            public PlaceholderFragment() {
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                return rootView;
            }
        }

    }



}
