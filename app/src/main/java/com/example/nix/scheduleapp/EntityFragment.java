package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.nix.scheduleapp.model.Entity;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nix on 31.03.2017.
 */

public class EntityFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private NewAdapter mAdapter;
    private Button mAddButton;
    private int result;
    private boolean action_code;
    private static final int REQUEST_CODE = 0;
    private static final String APP_FRAGMENT_ID1 = "fragment_id1";
    private static final String APP_FRAGMENT_ID2 = "fragment_id2";
    public static final int DISCIPLINE_CODE = 0, TEACHER_CODE = 1, AUDITORY_CODE = 2, TIMES_CODE = 3;
    public static EntityFragment newInstance(int fragment_type, boolean action_type){
        Bundle args = new Bundle();
        args.putSerializable(APP_FRAGMENT_ID1, fragment_type);
        args.putSerializable(APP_FRAGMENT_ID2, action_type);
        EntityFragment fragment = new EntityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = (int)getArguments().getSerializable(APP_FRAGMENT_ID1);
        action_code = (boolean)getArguments().getSerializable(APP_FRAGMENT_ID2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.entity_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.dis_rec);
        mAddButton = (Button)v.findViewById(R.id.button_add_dc);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ContentLab contentLab = ContentLab.get(getContext());
                switch (result){
                    case EntityFragment.DISCIPLINE_CODE:
                        contentLab.deleteDiscipline(mAdapter.mEntities.get(viewHolder.getAdapterPosition()));
                        break;
                    case EntityFragment.TEACHER_CODE:
                        contentLab.deleteTeacher(mAdapter.mEntities.get(viewHolder.getAdapterPosition()));
                        break;
                    case EntityFragment.AUDITORY_CODE:
                        contentLab.deleteAuditory(mAdapter.mEntities.get(viewHolder.getAdapterPosition()));
                        break;
                    case EntityFragment.TIMES_CODE:
                        contentLab.deleteTimes(mAdapter.mEntities.get(viewHolder.getAdapterPosition()));
                        break;
                }
                updateUI(result);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEntityDialog add = AddEntityDialog.newInstance(result);
                add.setTargetFragment(EntityFragment.this, REQUEST_CODE);
                add.show(getFragmentManager(), "kek");
                getFragmentManager().executePendingTransactions();
                add.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        updateUI(result);
                    }
                });
            }
        });
        //updateUI(result);
        return v;
    }
    private class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mDis;
        public ImageView mImageView;
        public Entity mExample;
        public RelativeLayout mRelativeLayout;
        public NewViewHolder(View itemView){
            super(itemView);
            mDis = (TextView)itemView.findViewById(R.id.dis_title);
            mImageView = (ImageView) itemView.findViewById(R.id.image_d);
            mRelativeLayout = (RelativeLayout)itemView.findViewById(R.id.entity_relativelayout_item);
            mRelativeLayout.setOnClickListener(this);
        }
        public void bindSubject(Entity entity){
            mExample = entity;
            mDis.setText(mExample.getName());
            switch (result){
                case EntityFragment.DISCIPLINE_CODE:
                    mImageView.setImageResource(R.drawable.ic_disc);
                    break;
                case EntityFragment.TEACHER_CODE:
                    mImageView.setImageResource(R.drawable.ic_teacher);
                    break;
                case EntityFragment.AUDITORY_CODE:
                    mImageView.setImageResource(R.drawable.ic_room);
                    break;
                case EntityFragment.TIMES_CODE:
                    mImageView.setImageResource(R.drawable.ic_times);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            if (action_code){
                getActivity().finish();
            }else{
                /*AddEntityDialog add = new AddEntityDialog();
                add.setTargetFragment(EntityFragment.this, REQUEST_CODE);
                add.show(getFragmentManager(), "kek");*/
                AddEntityDialog dialog = AddEntityDialog.newInstance(mExample.getId(), result);
                dialog.setTargetFragment(EntityFragment.this, REQUEST_CODE);
                dialog.show(getFragmentManager(), "kek");
                getFragmentManager().executePendingTransactions();
                dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        updateUI(result);
                    }
                });
            }
        }
    }
    protected class NewAdapter extends RecyclerView.Adapter<NewViewHolder>{
        private List<Entity> mEntities;
        public NewAdapter(List<Entity> entities){
            this.mEntities = entities;
        }
        public int getItemCount() {
            return mEntities.size();
        }
        public void setEntities(List<Entity> entities) {
            mEntities = entities;
        }
        public NewViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entity_item, viewGroup, false);
            NewViewHolder pvh = new NewViewHolder(v);
            return pvh;
        }
        public void onBindViewHolder(NewViewHolder holder, int position){
            Entity d = mEntities.get(position);
            holder.bindSubject(d);

        }
    }
    private void updateUI(int entity_code){
        ContentLab contentLab = ContentLab.get(getContext());
        List<Entity> entities;
        switch (entity_code) {
            case EntityFragment.DISCIPLINE_CODE:
                entities = contentLab.getDisciplines();
                break;
            case EntityFragment.TEACHER_CODE:
                entities = contentLab.getTeachers();
                break;
            case EntityFragment.AUDITORY_CODE:
                entities = contentLab.getAuditory();
                break;
            case EntityFragment.TIMES_CODE:
                entities = contentLab.getTimes();
                break;
            default:
                entities = contentLab.getDisciplines();
                break;
        }
        if (mAdapter == null) {
            mAdapter = new NewAdapter(entities);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setEntities(entities);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            String s = (String) data.getSerializableExtra(AddEntityDialog.EXTRA_TEXT);
            ContentLab contentLab = ContentLab.get(getContext());

            switch (result) {
                case EntityFragment.DISCIPLINE_CODE:
                    contentLab.addDiscipline(new Entity(s));
                    break;
                case EntityFragment.TEACHER_CODE:
                    contentLab.addTeacher(new Entity(s));
                    break;
                case EntityFragment.AUDITORY_CODE:
                    contentLab.addAuditory(new Entity(s));
                    break;
                case EntityFragment.TIMES_CODE:
                    contentLab.addTimes(new Entity(s));
                    break;
            }

       }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(result);
        Log.d("onResume", "after dismiss");
    }
}