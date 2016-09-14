package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Nix on 25.08.2016.
 */
public class TodayFragment extends ShowSubjectsAbstract {
    private RecyclerView mRecyclerView;
    private NewSubjectAdapter mAdapter;
    private Date mDate;
    private Calendar mCalendar;
    private SharedPreferences mSharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.content_monday, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_monday);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }
    @Override
    protected void updateUI(){
        SubjectLab subjectLab = SubjectLab.get(getActivity());
        List<Subject> subjects;
        int check_week;
        mDate = new Date();
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        mSharedPreferences = getActivity().getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        if ((mSharedPreferences.contains(SettingsActivity.COUNTDOWN_WEEK)) &&
                (mSharedPreferences.contains(SettingsActivity.WEEK_NUMBER))){
            check_week = mCalendar.get(Calendar.WEEK_OF_YEAR) - mSharedPreferences.getInt(SettingsActivity.COUNTDOWN_WEEK, 0)
                    + mSharedPreferences.getInt(SettingsActivity.WEEK_NUMBER, 0);
        }else{
            check_week = 1;
        }
        Log.e("checkweek", Integer.toString(check_week));
        if (check_week % 2 == 0){
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)){
                case 2:
                    subjects = subjectLab.getSubjects(1, 2);
                    break;
                case 3:
                    subjects = subjectLab.getSubjects(1,3);
                    break;
                case 4:
                    subjects = subjectLab.getSubjects(1,4);
                    break;
                case 5:
                    subjects = subjectLab.getSubjects(1,5);
                    break;
                case 6:
                    subjects = subjectLab.getSubjects(1,6);
                    break;
                case 7:
                    subjects = subjectLab.getSubjects(1,7);
                    break;
                default:
                    subjects = subjectLab.getSubjects(0,0);
            }
        }else {
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)){
                case 2:
                    subjects = subjectLab.getSubjects(2, 2);
                    break;
                case 3:
                    subjects = subjectLab.getSubjects(2,3);
                    break;
                case 4:
                    subjects = subjectLab.getSubjects(2,4);
                    break;
                case 5:
                    subjects = subjectLab.getSubjects(2,5);
                    break;
                case 6:
                    subjects = subjectLab.getSubjects(2,6);
                    break;
                case 7:
                    subjects = subjectLab.getSubjects(2,7);
                    break;
                default:
                    subjects = subjectLab.getSubjects(0,0);
            }
        }

        mAdapter = new NewSubjectAdapter(subjects);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
}
