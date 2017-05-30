package com.barbapapateam.barbapapa;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.barbapapateam.barbapapa.Database.*;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout menuDrawerLayout;
    private NavigationView drawer;
    private ListView beerList;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Database.initialized)
        {
            initDb(this);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //setting up the drawerLayout (menu latéral)
        menuDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer = (NavigationView) findViewById(R.id.left_drawer);
        drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

       // tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(viewPager);

        /*ImageButton drawerButton = (ImageButton) findViewById(R.id.drawerButton);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDrawerLayout.openDrawer(GravityCompat.START);
            }
        });*/

    }

  /*  @Override
    protected void onStop() {
        super.onStop();
        LinkedList<Beer> beers = Database.beers;
        Utils.saveBeersToJSON(beers);
    }
*/
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), "Liste");
        //adapter.addFragment(new GuideFragment(), "Guide");
        //adapter.addFragment(new BasicRecomFragment(), "Recomm.");
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


        switch(item.getItemId())
        {
            case R.id.action_settings:
                return true;

        }


        return super.onOptionsItemSelected(item);
    }


    public void selectDrawerItem(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.list:
                menuDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(0);
                break;

            case R.id.guide:
                menuDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(1);
                break;

            case R.id.recommandee:
                menuDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(2);
                break;

            case R.id.debug:
                menuDrawerLayout.closeDrawers();
                LaunchBarmanActivity();
                break;
        }
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

        Beer beer = new Beer("Test", (float)2, null, "Trappist", "Test2", (float) 3, true, true, "");
        Intent commandIntent = new Intent(this, CommandActivity.class);
        commandIntent.putExtra("BEER", beer);
        startActivity(commandIntent);

    }

    private void LaunchNotationActivity()
    {
        Intent notationIntent = new Intent(this, NotationActivity.class);
        startActivity(notationIntent);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        //listFragment.onRadioButtonClicked(view);
    }
}
