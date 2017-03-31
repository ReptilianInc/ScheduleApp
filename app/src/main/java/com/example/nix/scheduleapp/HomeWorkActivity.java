package com.example.nix.scheduleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeWorkActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private static boolean VIEW_CODE = true;
    private RecyclerView mRecyclerView;
    private SimpleHomeworkAdapter mAdapter;
    private StaggeredGridLayoutManager SGLM;
    private LinearLayoutManager LLM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);
        LLM = new LinearLayoutManager(this);
        SGLM = new StaggeredGridLayoutManager(2,1);
        mToolbar = (Toolbar)findViewById(R.id.toolbar_homework);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_homework);
        mRecyclerView.setLayoutManager(SGLM);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homework, menu);
        MenuItem item = menu.findItem(R.id.change);
        if (VIEW_CODE){
            item.setIcon(R.mipmap.ic_view_agenda_white_36dp);
        }else{
            item.setIcon(R.mipmap.ic_dashboard_white_36dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.change:
                VIEW_CODE = !VIEW_CODE;
                changeListView();
                invalidateOptionsMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SimpleHomeworkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNoteTextView;
        private Homework mNoteHolderExmp;
        private SimpleHomeworkHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(getApplicationContext(), "Заметка удалена", Toast.LENGTH_SHORT).show();
                    /*SimpleNoteLab.get(getApplicationContext()).deleteSimpleNote(mNoteHolderExmp);
                    updateUI();*/
                    return true;
                }
            });
            mNoteTextView = (TextView)itemView.findViewById(R.id.note_text);
        }
        private void bindHomework(Homework simpleNote){
            mNoteHolderExmp = simpleNote;
            mNoteTextView.setText(mNoteHolderExmp.getDescr());
        }

        @Override
        public void onClick(View v) {

        }
    }
    private class SimpleHomeworkAdapter extends RecyclerView.Adapter<SimpleHomeworkHolder>{
        private List<Homework> mNotes;
        private SimpleHomeworkAdapter(List<Homework> notes){
            mNotes = notes;
        }

        @Override
        public SimpleHomeworkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.homework_card, parent, false);
            return new SimpleHomeworkHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleHomeworkHolder holder, int position) {
            Homework note = mNotes.get(position);
            holder.bindHomework(note);
            if (note.getDescr().length() < 7){
                holder.mNoteTextView.setTextSize(40);
            }else{
                holder.mNoteTextView.setTextSize(20);
            }
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
        private void setNotes(List<Homework> notes){
            mNotes = notes;
        }
    }
    private void updateUI(){
        List<Homework> notes = new ArrayList<>();
        Homework h1 = new Homework();
        h1.setDescr("kek");
        Homework h2 = new Homework();
        h2.setDescr("Сделать какую-нибудь штуку на завтра");
        Homework h3 = new Homework();
        h3.setDescr("Автор программы - Никита");
        Homework h4 = new Homework();
        h4.setDescr("Типа пример");
        notes.add(h1);
        notes.add(h2);
        notes.add(h3);
        notes.add(h4);
        if (mAdapter == null)
        {
            mAdapter = new SimpleHomeworkAdapter(notes);
            mRecyclerView.setAdapter(mAdapter);
        } else
        {
            mAdapter.setNotes(notes);
            mAdapter.notifyDataSetChanged();
        }
    }
    private void changeListView(){
        if (VIEW_CODE){
            mRecyclerView.setLayoutManager(SGLM);
        }else {
            mRecyclerView.setLayoutManager(LLM);
        }
    }
}
