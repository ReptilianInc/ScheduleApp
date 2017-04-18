package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PickEntityActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private FragmentManager fm;
    private Fragment mFragment;
    public static final String EXTRA_ENTITY_ID = "com.example.nix.scheduleapp";
    public static Intent newIntent(Context packageContext, int entity_code){
        Intent intent = new Intent(packageContext, PickEntityActivity.class);
        intent.putExtra(EXTRA_ENTITY_ID, entity_code);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_entity);
        mToolbar = (Toolbar) findViewById(R.id.add_entity_toolbar);
        setSupportActionBar(mToolbar);
        fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.pick_container);
        mFragment = createFragment();
        fm.beginTransaction()
                .add(R.id.pick_container, mFragment)
                .commit();
    }
    private Fragment createFragment(){
        int entity_code = (int)getIntent().getSerializableExtra(EXTRA_ENTITY_ID);
        return EntityFragment.newInstance(entity_code);
    }
}
