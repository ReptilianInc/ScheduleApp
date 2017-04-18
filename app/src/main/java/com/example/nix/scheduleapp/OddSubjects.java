package com.example.nix.scheduleapp;

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
                subjects = contentLab.getSubjects(2,2);
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

        mAdapter = new NewSubjectAdapter(subjects);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
}
