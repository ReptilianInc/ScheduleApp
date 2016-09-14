package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Date mDate;
    private Calendar mCalendar;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private FragmentManager fm;
    private FloatingActionButton mFab;
    private Fragment fragment;
    public final static String DATA = "DAY_OF_WEEK";
    private int day;
    private Bundle bundle;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton)findViewById(R.id.fab);
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.content_fragment);
        fragment = new TodayFragment();
        fm.beginTransaction()
                .add(R.id.content_fragment, fragment)
                .commit();
        setSupportActionBar(mToolbar);
        bundle = new Bundle();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationview = (NavigationView) findViewById(R.id.navigation);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.mon:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Понедельник");
                        day = 2;
                        bundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(bundle);
                        fm.beginTransaction()
                                .add(R.id.content_fragment, fragment)
                                .commit();
                        return true;
                    case R.id.tue:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Вторник");
                        day = 3;
                        bundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(bundle);
                        fm.beginTransaction()
                                .add(R.id.content_fragment, fragment)
                                .commit();
                        return true;
                    case R.id.wed:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Среда");
                        day = 4;
                        bundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(bundle);
                        fm.beginTransaction()
                                .add(R.id.content_fragment, fragment)
                                .commit();
                        return true;
                    case R.id.thu:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Четверг");
                        day = 5;
                        bundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(bundle);
                        fm.beginTransaction()
                                .add(R.id.content_fragment, fragment)
                                .commit();
                        return true;
                    case R.id.fri:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Пятница");
                        day = 6;
                        bundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(bundle);
                        fm.beginTransaction()
                                .add(R.id.content_fragment, fragment)
                                .commit();
                        return true;
                    case R.id.sat:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Суббота");
                        day = 7;
                        bundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(bundle);
                        fm.beginTransaction()
                                .add(R.id.content_fragment, fragment)
                                .commit();
                        return true;
                }
                return false;
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNewSubjectActivity.newIntent(getApplication(), null, 0);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mToolbar.setTitle("Сегодня");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                fm.beginTransaction()
                        .remove(fragment)
                        .commit();
                mToolbar.setTitle("Сегодня");
                fragment = new TodayFragment();
                fm.beginTransaction()
                        .add(R.id.content_fragment, fragment)
                        .commit();
                break;
            case R.id.pref:
                Intent intentPref = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentPref);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume(){
        super.onResume();
        mDate = new Date();
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        mSharedPreferences = getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        int check_week = 0;
        if ((mSharedPreferences.contains(SettingsActivity.COUNTDOWN_WEEK)) &&
                (mSharedPreferences.contains(SettingsActivity.WEEK_NUMBER))){
            if (mSharedPreferences.getInt(SettingsActivity.COUNTDOWN_WEEK, 0) != 0 &&
                    mSharedPreferences.getInt(SettingsActivity.WEEK_NUMBER, 0) !=0){
                check_week = mCalendar.get(Calendar.WEEK_OF_YEAR) - mSharedPreferences.getInt(SettingsActivity.COUNTDOWN_WEEK, 0)
                        + mSharedPreferences.getInt(SettingsActivity.WEEK_NUMBER, 0);
                mToolbar.setSubtitle(check_week + " неделя");
            }
        }
    }
}
