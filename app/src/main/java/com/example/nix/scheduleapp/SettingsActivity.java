package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mClearButton, mSetFirstWeekButton;
    private EditText mSetWeekNumber;
    private boolean mSetFirstWeekChecker;
    private SharedPreferences mSharedPreferences;
    public final static String APP_PREFERENCES = "preferences";
    public final static String COUNTDOWN_WEEK = "countdown_week";
    public final static String WEEK_NUMBER = "week_number";
    private Date mDate;
    private Calendar mCalendar;
    private int mAnswerEditText = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mClearButton = (Button)findViewById(R.id.clear_button);
        mSetFirstWeekButton = (Button)findViewById(R.id.set_first_week_button);
        mSetWeekNumber = (EditText)findViewById(R.id.edit_week);
        mClearButton.setOnClickListener(this);
        mSetFirstWeekButton.setOnClickListener(this);
        mSetWeekNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0){
                    mAnswerEditText = Integer.parseInt(s.toString());
                }else{
                    mAnswerEditText = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onClick(View v){
        FragmentManager manager = getSupportFragmentManager();
        switch (v.getId()){
            case R.id.clear_button:
                ProveItFragment dialog = new ProveItFragment();
                dialog.show(manager, "");
                break;
            case R.id.set_first_week_button:
                mSetFirstWeekChecker = true;
                Toast.makeText(getApplicationContext(), "Нажмите \"сохранить\" для установления значения", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.save_button:
                if (mAnswerEditText >= 1){
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putInt(WEEK_NUMBER, mAnswerEditText);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Сохранено", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Неверное числовое значение, изменения не сохранены", Toast.LENGTH_SHORT).show();
                }
                if (mSetFirstWeekChecker){
                    mDate = new Date();
                    mCalendar = Calendar.getInstance();
                    mCalendar.setTime(mDate);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putInt(COUNTDOWN_WEEK, mCalendar.get(Calendar.WEEK_OF_YEAR));
                    editor.apply();
                }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
