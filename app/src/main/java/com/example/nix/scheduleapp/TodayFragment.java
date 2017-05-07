package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import com.example.nix.scheduleapp.model.Subject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Nix on 25.08.2016.
 */
public class TodayFragment extends ShowSubjectsAbstract {
    private NewSubjectAdapter mAdapter;
    private Date mDate;
    private Calendar mCalendar;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void updateUI(){
        ContentLab contentLab = ContentLab.get(getActivity());
        List<Subject> subjects = new ArrayList<>();
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
        if (check_week % 2 == 0){
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)){
                case 2:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN, 2);
                    break;
                case 3:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN,3);
                    break;
                case 4:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN,4);
                    break;
                case 5:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN,5);
                    break;
                case 6:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN,6);
                    break;
                case 7:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN,7);
                    break;
            }
        }else {
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)){
                case 2:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD, 2);
                    break;
                case 3:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,3);
                    break;
                case 4:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,4);
                    break;
                case 5:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,5);
                    break;
                case 6:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,6);
                    break;
                case 7:
                    subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,7);
                    break;
            }
        }
        if(subjects.isEmpty()){
            mEmptyTextView.setVisibility(TextView.VISIBLE);
        }else{
            mEmptyTextView.setVisibility(TextView.INVISIBLE);
        }
        if(mAdapter == null){
            mAdapter = new NewSubjectAdapter(subjects);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setSubjects(subjects);
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
}
