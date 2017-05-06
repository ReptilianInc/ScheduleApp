package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
    private Bundle bundle;
    private SharedPreferences mSharedPreferences;
    private boolean VISIBLE_BUTTON = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Сегодня");
        mFab = (FloatingActionButton)findViewById(R.id.fab);
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.content_fragment);
        fragment = new TodayFragment();
        fm.beginTransaction()
                .add(R.id.content_fragment, fragment)
                .commit();
        setSupportActionBar(mToolbar);
        setWeek();
        bundle = new Bundle();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationview = (NavigationView) findViewById(R.id.navigation);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                mDrawerLayout.closeDrawers();
                VISIBLE_BUTTON = true;
                invalidateOptionsMenu();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (item.getItemId()){
                            case R.id.mon:
                                setDayFragment("Понедельник", 2);
                                break;
                            case R.id.tue:
                                setDayFragment("Вторник", 3);
                                break;
                            case R.id.wed:
                                setDayFragment("Среда", 4);
                                break;
                            case R.id.thu:
                                setDayFragment("Четверг", 5);
                                break;
                            case R.id.fri:
                                setDayFragment("Пятница", 6);
                                break;
                            case R.id.sat:
                                setDayFragment("Суббота", 7);
                                break;
                            case R.id.hw:
                                setEntityFragment("Список дисциплин", EntityFragment.DISCIPLINE_CODE);
                                break;
                            case R.id.teach:
                                setEntityFragment("Список преподавателей", EntityFragment.TEACHER_CODE);
                                break;
                            case R.id.ex:
                                setEntityFragment("Список аудиторий", EntityFragment.AUDITORY_CODE);
                                break;
                            case R.id.tms:
                                setEntityFragment("Список времён", EntityFragment.TIMES_CODE);
                                break;
                        }
                    }
                },200);

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
        MenuItem toggleItem = menu.findItem(R.id.home);
        toggleItem.setVisible(VISIBLE_BUTTON);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                invalidateOptionsMenu();
                setWeek();
                VISIBLE_BUTTON = false;
                fm.beginTransaction()
                        .remove(fragment)
                        .commit();
                mToolbar.setTitle("Сегодня");
                setWeek();
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
    }
    private void setWeek(){
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
    private void setDayFragment(String title, int day_code){
        mFab.setVisibility(FloatingActionButton.VISIBLE);
        fm.beginTransaction()
                .remove(fragment)
                .commit();
        mToolbar.setTitle(title);
        mToolbar.setSubtitle("");
        bundle.putInt(DATA, day_code);
        fragment = new SubjectsOfDayFragment();
        fragment.setArguments(bundle);
        fm.beginTransaction()
                .add(R.id.content_fragment, fragment)
                .commit();
    }
    private void setEntityFragment(String title, int entity_code){
        mFab.setVisibility(FloatingActionButton.INVISIBLE);
        fm.beginTransaction()
                .remove(fragment)
                .commit();
        mToolbar.setTitle(title);
        mToolbar.setSubtitle("");
        fragment = EntityFragment.newInstance(entity_code, false);
        fm.beginTransaction()
                .add(R.id.content_fragment, fragment)
                .commit();
    }
}
