package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.nix.scheduleapp.model.Entity;
import java.util.List;


/**
 * Created by Nix on 31.03.2017.
 */

public class EntityFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private NewAdapter mAdapter;
    private Button mAddButton;
    private static final int REQUEST_CODE = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                contentLab.deleteDiscipline(mAdapter.mEntities.get(viewHolder.getAdapterPosition()));
                updateUI();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDisciplineDialog add = new AddDisciplineDialog();
                add.setTargetFragment(EntityFragment.this, REQUEST_CODE);
                add.show(getFragmentManager(), "kek");
            }
        });
        updateUI();
        return v;
    }
    private class NewViewHolder extends RecyclerView.ViewHolder{
        public TextView mDis;
        public ImageView mImageView;
        public Entity mExample;
        public NewViewHolder(View itemView){
            super(itemView);
            mDis = (TextView)itemView.findViewById(R.id.dis_title);
            mImageView = (ImageView) itemView.findViewById(R.id.image_d);
        }
        public void bindSubject(Entity entity){
            mExample = entity;
            mDis.setText(mExample.getName());
            mImageView.setImageResource(R.drawable.ic_disc);
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
    private void updateUI(){
        ContentLab contentLab = ContentLab.get(getContext());
        List<Entity> disciplines;
        disciplines = contentLab.getDisciplines();
        mAdapter = new NewAdapter(disciplines);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            String s = (String) data.getSerializableExtra(AddDisciplineDialog.EXTRA_TEXT);
            ContentLab contentLab = ContentLab.get(getContext());
            contentLab.addDiscipline(new Entity(s));
            updateUI();
        }
    }
}
