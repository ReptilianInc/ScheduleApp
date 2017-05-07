package com.example.nix.scheduleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import com.example.nix.scheduleapp.model.Subject;
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
        if (check_week % 2 == 0){
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)){
                case 2:
                    subjects = contentLab.getSubjects(1, 2);
                    break;
                case 3:
                    subjects = contentLab.getSubjects(1,3);
                    break;
                case 4:
                    subjects = contentLab.getSubjects(1,4);
                    break;
                case 5:
                    subjects = contentLab.getSubjects(1,5);
                    break;
                case 6:
                    subjects = contentLab.getSubjects(1,6);
                    break;
                case 7:
                    subjects = contentLab.getSubjects(1,7);
                    break;
                default:
                    subjects = contentLab.getSubjects(0,0);
            }
        }else {
            switch (mCalendar.get(Calendar.DAY_OF_WEEK)){
                case 2:
                    subjects = contentLab.getSubjects(2, 2);
                    break;
                case 3:
                    subjects = contentLab.getSubjects(2,3);
                    break;
                case 4:
                    subjects = contentLab.getSubjects(2,4);
                    break;
                case 5:
                    subjects = contentLab.getSubjects(2,5);
                    break;
                case 6:
                    subjects = contentLab.getSubjects(2,6);
                    break;
                case 7:
                    subjects = contentLab.getSubjects(2,7);
                    break;
                default:
                    subjects = contentLab.getSubjects(0,0);
            }
        }
        if(subjects.isEmpty()){
            mEmptyTextView.setVisibility(TextView.VISIBLE);
        }else{
            mEmptyTextView.setVisibility(TextView.INVISIBLE);
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
