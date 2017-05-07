package com.example.nix.scheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nix.scheduleapp.model.Subject;
import java.util.List;

/**
 * Created by Nix on 28.08.2016.
 */
public abstract class ShowSubjectsAbstract extends Fragment{
    protected abstract void updateUI();
    protected RecyclerView mRecyclerView;
    protected NewSubjectAdapter mAdapter;
    public static final int ACTION_CREATE = 0, ACTION_EDIT = 1, ACTION_COPY = 2;
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
        //updateUI();
        return v;
    }

    protected class NewSubjectViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public TextView mNameTextView;
        public TextView mDateTextView;
        public TextView mTeacherTextView;
        public TextView mAudView;
        public Subject mSubjectExample;
        public NewSubjectViewHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView)itemView.findViewById(R.id.name_view);
            mDateTextView = (TextView)itemView.findViewById(R.id.date_view);
            mTeacherTextView = (TextView)itemView.findViewById(R.id.teacher_view);
            mAudView = (TextView)itemView.findViewById(R.id.audit_view);
            itemView.setOnCreateContextMenuListener(this);
        }
        public void bindSubject(Subject subject){
            mSubjectExample = subject;
            mNameTextView.setText(ContentLab.get(getContext()).getDiscipline(subject.getDisciplineId()).getName());
            mTeacherTextView.setText(ContentLab.get(getContext()).getTeacher(subject.getTeacherId()).getName());
            mAudView.setText(ContentLab.get(getContext()).getAuditory(subject.getAuditoryId()).getName());
            mDateTextView.setText(ContentLab.get(getContext()).getTimes(subject.getTimesId()).getName());
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
            menu.setHeaderTitle("Действие: ");
            menu.add(0, v.getId(), 0, "Редактировать").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = AddNewSubjectActivity.newIntent(getActivity(), mSubjectExample.getId(), ACTION_EDIT);
                    startActivity(intent);
                    return false;
                }
            });
            menu.add(0, v.getId(), 0, "Копировать в новую запись").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = AddNewSubjectActivity.newIntent(getActivity(), mSubjectExample.getId(), ACTION_COPY);
                    startActivity(intent);
                    updateUI();
                    return false;
                }
            });
            menu.add(0, v.getId(), 0, "Удалить").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    ContentLab.get(getActivity()).deleteSubject(mSubjectExample);
                    updateUI();
                    return false;
                }
            });
        }
    }
    protected class NewSubjectAdapter extends RecyclerView.Adapter<NewSubjectViewHolder>{
        private List<Subject> mSubjects;
        public NewSubjectAdapter(List<Subject> subjects){
            this.mSubjects = subjects;
        }
        public int getItemCount() {
            return mSubjects.size();
        }
        public void setSubjects(List<Subject> subjects){
            mSubjects = subjects;
        }
        public NewSubjectViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shedule_item, viewGroup, false);
            NewSubjectViewHolder pvh = new NewSubjectViewHolder(v);
            return pvh;
        }
        public void onBindViewHolder(NewSubjectViewHolder holder, int position){
            Subject subject = mSubjects.get(position);
            holder.bindSubject(subject);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
