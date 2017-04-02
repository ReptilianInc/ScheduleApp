package com.example.nix.scheduleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nix.scheduleapp.model.Discipline;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nix on 31.03.2017.
 */

public class DisciplineFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private NewDisciplineAdapter mAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.discipline_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.dis_rec);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }
    private class NewDisciplineViewHolder extends RecyclerView.ViewHolder{
        public TextView mDis;
        public Discipline mDisExample;
        public NewDisciplineViewHolder(View itemView){
            super(itemView);
            mDis = (TextView)itemView.findViewById(R.id.dis_title);
        }
        public void bindSubject(Discipline discipline){
            mDisExample = discipline;
            mDis.setText(mDisExample.getTitle());
        }

    }
    protected class NewDisciplineAdapter extends RecyclerView.Adapter<NewDisciplineViewHolder>{
        private List<Discipline> mDisciplines;
        public NewDisciplineAdapter(List<Discipline> diss){
            this.mDisciplines = diss;
        }
        public int getItemCount() {
            return mDisciplines.size();
        }
        public void setDisciplines(List<Discipline> diss){
            mDisciplines = diss;
        }
        public NewDisciplineViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.discipline_item, viewGroup, false);
            NewDisciplineViewHolder pvh = new NewDisciplineViewHolder(v);
            return pvh;
        }
        public void onBindViewHolder(NewDisciplineViewHolder holder, int position){
            Discipline d = mDisciplines.get(position);
            holder.bindSubject(d);

        }
    }
    private void updateUI(){
        List<Discipline> d = new ArrayList<>();
        d.add(new Discipline("Базы данных"));
        d.add(new Discipline("Элтех"));
        d.add(new Discipline("Философия"));
        mAdapter = new NewDisciplineAdapter(d);
        mRecyclerView.setAdapter(mAdapter);
    }
}
