package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.UUID;

public class AddNewSubjectActivity extends AppCompatActivity {
    public static final String EXTRA_SUBJECT_ID = "com.bignerdranch.android.criminalintent.crime_id";
    public static final String EXTRA_FOR_COPY = "com.bignerdranch.android.criminalintent.for_copy";
    public static Intent newIntent(Context packageContext, UUID subjectId, int forCopy)
    {
        Intent intent = new Intent(packageContext, AddNewSubjectActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        intent.putExtra(EXTRA_FOR_COPY, forCopy);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_fragment);
        if (fragment == null)
        {
            fragment = new AddNewSubjectFragment();
            fm.beginTransaction()
                    .add(R.id.content_fragment, fragment)
                    .commit();
        }
    }

}
