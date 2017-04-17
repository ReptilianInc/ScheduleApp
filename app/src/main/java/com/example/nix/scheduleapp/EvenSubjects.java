package com.example.nix.scheduleapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        mAdapter = new NewSubjectAdapter(subjects);
        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
}
