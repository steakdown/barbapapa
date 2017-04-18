package com.barbapapateam.barbapapa;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.barbapapateam.barbapapa.Database.*;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListView beerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(!Database.initialized)
        {
            initDb(this);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //LaunchAdvancedRecommandationActivity();


                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();

                //LaunchAdvancedRecommandationActivity();
                //LaunchCommandActivity();
                //LaunchBarmanActivity();
                LaunchNotationActivity();
            }
        });

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), "Liste");
        adapter.addFragment(new TwoFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void LaunchBarmanActivity()
    {
        Intent barmanIntent = new Intent(this, BarmanActivity.class);
        startActivity(barmanIntent);
    }

    private void LaunchAdvancedRecommandationActivity()
    {
        Intent ARIntent = new Intent(this, AdvancedRecommandationActivity.class);
        startActivity(ARIntent);
    }

    private void LaunchCommandActivity() {

        Beer beer = new Beer("Test", (float)2, null, "Trappist", "Test2", (float) 3, true);
        Intent commandIntent = new Intent(this, CommandActivity.class);
        commandIntent.putExtra("BEER", beer);
        startActivity(commandIntent);

    }

    private void LaunchNotationActivity()
    {
        Intent notationIntent = new Intent(this, NotationActivity.class);
        startActivity(notationIntent);

    }
}
