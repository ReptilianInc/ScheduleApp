package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nix.scheduleapp.model.Subject;
import java.util.UUID;

/**
 * Created by Nix on 27.08.2016.
 */
public class AddNewSubjectFragment extends Fragment implements View.OnClickListener{
    private TextView mTeacherView, mAuditView, mNameView, mTimesView;
    private TextView mDayOfWeekView, mWeekTypeView;
    private RelativeLayout mTitle, mTeacher, mAuditory, mTime, mDay, mWeek;
    private static final int REQUEST_DATE3 = 2;
    private static final int REQUEST_DATE4 = 3;
    private static final int REQUEST_UUID = 0;
    private static final String DIALOG_DAY3 = "DialogDay3";
    private static final String DIALOG_DAY4 = "DialogDay4";
    private Subject mSubject;
    private int action;
    private UUID subjectId;
    private int action_type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        subjectId = (UUID) getActivity().getIntent().getSerializableExtra(AddNewSubjectActivity.EXTRA_SUBJECT_ID);
        action = (int) getActivity().getIntent().getSerializableExtra(AddNewSubjectActivity.EXTRA_FOR_COPY);
        if(subjectId == null && action == ShowSubjectsAbstract.ACTION_CREATE){ // создание нового
            mSubject = new Subject();
        }
        if (action == ShowSubjectsAbstract.ACTION_COPY){ //копирование
            mSubject = new Subject(ContentLab.get(getActivity()).getSubject(subjectId));
        }
        if(action == ShowSubjectsAbstract.ACTION_EDIT){ // редактирование
            mSubject = ContentLab.get(getContext()).getSubject(subjectId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_new_subject, container, false);
        mTitle = (RelativeLayout)v.findViewById(R.id.rel1);
        mTeacher = (RelativeLayout)v.findViewById(R.id.rel2);
        mAuditory = (RelativeLayout)v.findViewById(R.id.rel3);
        mTime = (RelativeLayout)v.findViewById(R.id.rel4);
        mDay = (RelativeLayout)v.findViewById(R.id.rel5);
        mWeek = (RelativeLayout)v.findViewById(R.id.rel6);
        mTeacherView = (TextView) v.findViewById(R.id.teacher_name_redact);
        mAuditView = (TextView) v.findViewById(R.id.audit_redact);
        mNameView = (TextView) v.findViewById(R.id.name_redact);
        mTimesView = (TextView)v.findViewById(R.id.times_redact);
        mDayOfWeekView = (TextView) v.findViewById(R.id.day_week);
        mWeekTypeView = (TextView) v.findViewById(R.id.week_type);
        if(action == ShowSubjectsAbstract.ACTION_COPY || action == ShowSubjectsAbstract.ACTION_EDIT){
            initText();
        }
        mTitle.setOnClickListener(this);
        mTeacher.setOnClickListener(this);
        mAuditory.setOnClickListener(this);
        mTime.setOnClickListener(this);
        mDay.setOnClickListener(this);
        mWeek.setOnClickListener(this);
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        if(requestCode == REQUEST_DATE3){
            mSubject.setDay((int) data.getSerializableExtra(DayPickerFragment.EXTRA_DAY));
            mDayOfWeekView.setText(mSubject.getDayString());
        }
        if(requestCode == REQUEST_DATE4){
            mSubject.setWeekType((int) data.getSerializableExtra(WeekPickerFragment.EXTRA_WEEKTYPE));
            mWeekTypeView.setText(mSubject.getWeekString());
        }
        if(requestCode == REQUEST_UUID){
            UUID id = (UUID)data.getSerializableExtra(EntityFragment.EXTRA_TEXT);
            ContentLab contentLab = ContentLab.get(getContext());
            switch (action_type){
                case EntityFragment.DISCIPLINE_CODE:
                    mSubject.setDisciplineId(contentLab.getDiscipline(id).getId());
                    mNameView.setText(contentLab.getDiscipline(id).getName());
                    break;
                case EntityFragment.TEACHER_CODE:
                    mSubject.setTeacherId(contentLab.getTeacher(id).getId());
                    mTeacherView.setText(contentLab.getTeacher(id).getName());
                    break;
                case EntityFragment.AUDITORY_CODE:
                    mSubject.setAuditoryId(contentLab.getAuditory(id).getId());
                    mAuditView.setText(contentLab.getAuditory(id).getName());
                    break;
                case EntityFragment.TIMES_CODE:
                    mSubject.setTimesId(contentLab.getTimes(id).getId());
                    mTimesView.setText(contentLab.getTimes(id).getName());
                    break;
            }
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.save, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        ContentLab contentLab = ContentLab.get(getActivity());
        switch (item.getItemId()){
            case R.id.save_button:
                switch (action){
                    case 0:
                        if (mSubject.getDisciplineId() == null || mSubject.getAuditoryId() == null || mSubject.getTeacherId() == null ||
                                mSubject.getTimesId() == null || mDayOfWeekView.getText() == "Коснитесь, чтобы назначить" ||
                                mWeekTypeView.getText() == "Коснитесь, чтобы назначить"){
                            Toast.makeText(getContext(), "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Запись сохранена", Toast.LENGTH_SHORT).show();
                            contentLab.addSubject(mSubject);
                            getActivity().finish();

                        }
                        break;
                    case 1:
                        if (mSubject.getDisciplineId() == null || mSubject.getAuditoryId() == null || mSubject.getTeacherId() == null ||
                                mSubject.getTimesId() == null || mDayOfWeekView.getText() == "Коснитесь, чтобы назначить" ||
                                mWeekTypeView.getText() == "Коснитесь, чтобы назначить"){
                            Toast.makeText(getContext(), "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Запись отредактирована", Toast.LENGTH_SHORT).show();
                            contentLab.updateSubject(mSubject);
                            getActivity().finish();
                        }
                        break;
                    case 2:
                        if (mSubject.getDisciplineId() == null || mSubject.getAuditoryId() == null || mSubject.getTeacherId() == null ||
                                mSubject.getTimesId() == null || mDayOfWeekView.getText() == "Коснитесь, чтобы назначить" ||
                                mWeekTypeView.getText() == "Коснитесь, чтобы назначить"){
                            Toast.makeText(getContext(), "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Запись скопирована", Toast.LENGTH_SHORT).show();
                            contentLab.addSubject(mSubject);
                            getActivity().finish();
                        }
                        break;
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void initText(){
        ContentLab cb = ContentLab.get(getContext());
        mNameView.setText(cb.getDiscipline(mSubject.getDisciplineId()).getName());
        mTeacherView.setText(cb.getTeacher(mSubject.getTeacherId()).getName());
        mAuditView.setText(cb.getAuditory(mSubject.getAuditoryId()).getName());
        mTimesView.setText(cb.getTimes(mSubject.getTimesId()).getName());
        mDayOfWeekView.setText(mSubject.getDayString());
        mWeekTypeView.setText(mSubject.getWeekString());
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        DialogFragment dialog;
        Intent intent;
        switch (v.getId()){
            case R.id.rel1:
                intent = PickEntityActivity.newIntent(getContext(), EntityFragment.DISCIPLINE_CODE);
                startActivityForResult(intent, REQUEST_UUID);
                action_type = EntityFragment.DISCIPLINE_CODE;
                break;
            case R.id.rel2:
                intent = PickEntityActivity.newIntent(getContext(), EntityFragment.TEACHER_CODE);
                startActivityForResult(intent, REQUEST_UUID);
                action_type = EntityFragment.TEACHER_CODE;
                break;
            case R.id.rel3:
                intent = PickEntityActivity.newIntent(getContext(), EntityFragment.AUDITORY_CODE);
                startActivityForResult(intent, REQUEST_UUID);
                action_type = EntityFragment.AUDITORY_CODE;
                break;
            case R.id.rel4:
                intent = PickEntityActivity.newIntent(getContext(), EntityFragment.TIMES_CODE);
                startActivityForResult(intent, REQUEST_UUID);
                action_type = EntityFragment.TIMES_CODE;
                break;
            case R.id.rel5:
                dialog = new DayPickerFragment();
                dialog.setTargetFragment(AddNewSubjectFragment.this, REQUEST_DATE3);
                dialog.show(manager, DIALOG_DAY3);
                break;
            case R.id.rel6:
                dialog = new WeekPickerFragment();
                dialog.setTargetFragment(AddNewSubjectFragment.this, REQUEST_DATE4);
                dialog.show(manager, DIALOG_DAY4);
                break;
        }
    }
}
