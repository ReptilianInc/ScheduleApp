package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.nix.scheduleapp.AddDisciplineOrHWDialog.EXTRA_ACTION;


/**
 * Created by Nix on 31.03.2017.
 */

public class HomeworkFragment extends Fragment {
    private ExpListAdapter adapter;
    private ArrayList<String> children1;
    private ArrayList<String> children2;
    private ArrayList<String> groups_titles;
    ArrayList<ArrayList<String>> groups;
    private static final int CHOOSE_ACTIONS = 0;
    private Button mAddButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homework_fragment_layout, container, false);
        ExpandableListView listView = (ExpandableListView)v.findViewById(R.id.exListView);
        mAddButton = (Button)v.findViewById(R.id.button_add_hw);
        groups_titles = new ArrayList<String>();
        groups_titles.add("Базы данных");
        groups_titles.add("Философия");
        //Создаем набор данных для адаптера
        groups = new ArrayList<ArrayList<String>>();
        children1 = new ArrayList<String>();
        children2 = new ArrayList<String>();
        children1.add("Сделать что-то полезное");
        children1.add("Изучить лекцию, подготовить доклад на тему лекции");
        children1.add("Хей диджей, хей битмейкер, хей уличный денсер, шк шк шейкер шейкер, хей патимейкер");
        groups.add(children1);
        children2.add("Сходить погулять");
        children2.add("Расщепить атом");
        children2.add("Хей диджей, хей битмейкер, хей уличный денсер, шк шк шейкер шейкер, хей патимейкер");
        children2.add("Хей диджей, хей битмейкер, хей уличный денсер, шк шк шейкер шейкер, хей патимейкер");
        groups.add(children2);
        //Создаем адаптер и передаем context и список с данными
        adapter = new ExpListAdapter(getContext(), groups);
        listView.setAdapter(adapter);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDisciplineOrHWDialog dialog  = new AddDisciplineOrHWDialog();
                dialog.setTargetFragment(HomeworkFragment.this, CHOOSE_ACTIONS);
                dialog.show(getFragmentManager(), "dlg");
            }
        });
        return v;
    }
    public class ExpListAdapter extends BaseExpandableListAdapter {

        private ArrayList<ArrayList<String>> mGroups;
        private Context mContext;

        public ExpListAdapter (Context context,ArrayList<ArrayList<String>> groups){
            mContext = context;
            mGroups = groups;
        }

        @Override
        public int getGroupCount() {
            return mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mGroups.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mGroups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mGroups.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.group_view, null);
            }

            if (isExpanded){
                //Изменяем что-нибудь, если текущая Group раскрыта
            }
            else{
                //Изменяем что-нибудь, если текущая Group скрыта
            }

            TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
            textGroup.setText(groups_titles.get(groupPosition));
            ImageButton button = (ImageButton) convertView.findViewById(R.id.delete_imagebutton_group);
            button.setFocusable(false);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGroups.remove(groupPosition);
                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;

        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_view, null);
            }

            TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
            ImageButton button = (ImageButton) convertView.findViewById(R.id.delete_imagebutton_child);
            button.setFocusable(false);
            textChild.setText(mGroups.get(groupPosition).get(childPosition));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(groupPosition){
                        case 0:{
                            children1.remove(childPosition);
                            adapter.notifyDataSetChanged();
                            break;
                        }
                        case 1:
                            children2.remove(childPosition);
                            adapter.notifyDataSetChanged();
                            break;
                    }
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == CHOOSE_ACTIONS) {
            boolean hui = (boolean)data.getSerializableExtra(EXTRA_ACTION);
            mAddButton.setText(Boolean.toString(hui));
        }
    }
}
