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
 * Created by Nix on 25.08.2016.
 */
public class OddSubjects extends ShowSubjectsAbstract {
    private RecyclerView mRecyclerView;
    private NewSubjectAdapter mAdapter;
    public int i;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            i = bundle.getInt(SubjectsOfDayFragment.DAY, 0);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.content_monday, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_monday);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }
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
