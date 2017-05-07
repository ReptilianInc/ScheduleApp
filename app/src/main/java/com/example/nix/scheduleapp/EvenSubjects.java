package com.example.nix.scheduleapp;

import android.widget.TextView;
import com.example.nix.scheduleapp.model.Subject;
import java.util.List;

/**
 * Created by Nix on 24.08.2016.
 */
public class EvenSubjects extends ShowSubjectsAbstract {
    @Override
    protected void updateUI(){
        ContentLab contentLab = ContentLab.get(getActivity());
        List<Subject> subjects;
        switch (i){
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
            default:
                subjects = contentLab.getSubjects(Subject.WEEK_TYPE_EVEN,2);
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
