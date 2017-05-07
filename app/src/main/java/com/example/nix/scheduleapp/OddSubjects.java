package com.example.nix.scheduleapp;

import android.widget.TextView;
import com.example.nix.scheduleapp.model.Subject;
import java.util.List;

/**
 * Created by Nix on 25.08.2016.
 */
public class OddSubjects extends ShowSubjectsAbstract {

    @Override
    protected void updateUI(){
        ContentLab contentLab = ContentLab.get(getActivity());
        List<Subject> subjects;
        switch (i){
            case 2:
                subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,2);
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
            default:
                subjects = contentLab.getSubjects(Subject.WEEK_TYPE_ODD,2);
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
}
