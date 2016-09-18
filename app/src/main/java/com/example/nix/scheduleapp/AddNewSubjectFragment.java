package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by Nix on 27.08.2016.
 */
public class AddNewSubjectFragment extends Fragment {
    private EditText mTeacherView, mAuditView, mNameView;
    private TextView mStartView, mEndView, mDayOfWeekView, mWeekTypeView;
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_DATE2 = 1;
    private static final String DIALOG_DAY = "DialogDay";
    private static final String DIALOG_DAY2 = "DialogDay2";
    private static final int REQUEST_DATE3 = 2;
    private static final int REQUEST_DATE4 = 3;
    private static final String DIALOG_DAY3 = "DialogDay3";
    private static final String DIALOG_DAY4 = "DialogDay4";
    private Subject mSubject;
    private int copy;
    private UUID crimeId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        crimeId = (UUID) getActivity().getIntent().getSerializableExtra(AddNewSubjectActivity.EXTRA_SUBJECT_ID);
        copy = (int) getActivity().getIntent().getSerializableExtra(AddNewSubjectActivity.EXTRA_FOR_COPY);
        if(crimeId == null && copy == 0){ // создание нового
            mSubject = new Subject();
        }
        if (copy == 2){ //копирование
            mSubject = new Subject(SubjectLab.get(getActivity()).getSubject(crimeId));
        }
        if(copy == 1){ // редактирование
            mSubject = SubjectLab.get(getContext()).getSubject(crimeId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_new_subject, container, false);
        mTeacherView = (EditText) v.findViewById(R.id.teacher_name_redact);
        mAuditView = (EditText) v.findViewById(R.id.audit_redact);
        mNameView = (EditText) v.findViewById(R.id.name_redact);
        mStartView = (TextView) v.findViewById(R.id.start_text);
        mDayOfWeekView = (TextView) v.findViewById(R.id.day_week);
        mEndView = (TextView) v.findViewById(R.id.end_text);
        mWeekTypeView = (TextView) v.findViewById(R.id.week_type);
        initText();
        mDayOfWeekView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DayPickerFragment dialog = new DayPickerFragment();
                dialog.setTargetFragment(AddNewSubjectFragment.this, REQUEST_DATE3);
                dialog.show(manager, DIALOG_DAY3);
            }
        });
        mWeekTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                WeekPickerFragment dialog = new WeekPickerFragment();
                dialog.setTargetFragment(AddNewSubjectFragment.this, REQUEST_DATE4);
                dialog.show(manager, DIALOG_DAY4);
            }
        });
        mStartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.setTargetFragment(AddNewSubjectFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DAY);
            }
        });
        mEndView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment2 dialog = new TimePickerFragment2();
                dialog.setTargetFragment(AddNewSubjectFragment.this, REQUEST_DATE2);
                dialog.show(manager, DIALOG_DAY2);
            }
        });
        mTeacherView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSubject.setTeacherName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSubject.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mAuditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSubject.setAuditory(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            mSubject.setStartHours((int) data.getSerializableExtra(TimePickerFragment.EXTRA_HOUR));
            mSubject.setStartMinutes((int) data.getSerializableExtra(TimePickerFragment.EXTRA_MINUTE));
            mStartView.setText(mSubject.getStartTime());


        }
        if (requestCode == REQUEST_DATE2) {
            mSubject.setEndHours((int) data.getSerializableExtra(TimePickerFragment2.EXTRA_HOUR));
            mSubject.setEndMinutes((int) data.getSerializableExtra(TimePickerFragment2.EXTRA_MINUTE));
            mEndView.setText(mSubject.getEndTime());
        }
        if(requestCode == REQUEST_DATE3){
            mSubject.setDay((int) data.getSerializableExtra(DayPickerFragment.EXTRA_DAY));
            mDayOfWeekView.setText(mSubject.getDayString());
        }
        if(requestCode == REQUEST_DATE4){
            mSubject.setWeekType((int) data.getSerializableExtra(WeekPickerFragment.EXTRA_WEEKTYPE));
            mWeekTypeView.setText(mSubject.getWeekString());
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.save, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        SubjectLab subjectLab = SubjectLab.get(getActivity());
        switch (item.getItemId()){
            case R.id.save_button:
                switch (copy){
                    case 0:
                        if (mSubject.getName() == null || mDayOfWeekView.getText() == "Коснитесь, чтобы назначить" ||
                                mWeekTypeView.getText() == "Коснитесь, чтобы назначить" || mStartView.getText() == "Коснитесь, чтобы назначить" ||
                                mEndView.getText() == "Коснитесь, чтобы назначить"){
                            Toast.makeText(getContext(), "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Запись сохранена", Toast.LENGTH_SHORT).show();
                            subjectLab.addSubject(mSubject);
                            getActivity().finish();

                        }
                        break;
                    case 1:
                        if (mSubject.getName() == null || mDayOfWeekView.getText() == "Коснитесь, чтобы назначить" ||
                                mWeekTypeView.getText() == "Коснитесь, чтобы назначить" || mStartView.getText() == "Коснитесь, чтобы назначить" ||
                                mEndView.getText() == "Коснитесь, чтобы назначить"){
                            Toast.makeText(getContext(), "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Запись отредактирована", Toast.LENGTH_SHORT).show();
                            subjectLab.updateSubject(mSubject);
                            getActivity().finish();
                        }
                        break;
                    case 2:
                        if (mSubject.getName() == null || mDayOfWeekView.getText() == "Коснитесь, чтобы назначить" ||
                                mWeekTypeView.getText() == "Коснитесь, чтобы назначить" || mStartView.getText() == "Коснитесь, чтобы назначить" ||
                                mEndView.getText() == "Коснитесь, чтобы назначить"){
                            Toast.makeText(getContext(), "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Запись скопирована", Toast.LENGTH_SHORT).show();
                            subjectLab.addSubject(mSubject);
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
        mTeacherView.setText(mSubject.getTeacherName());
        mAuditView.setText(mSubject.getAuditory());
        mNameView.setText(mSubject.getName());
        mDayOfWeekView.setText(mSubject.getDayString());
        mStartView.setText(mSubject.getStartTime());
        mEndView.setText(mSubject.getEndTime());
        mWeekTypeView.setText(mSubject.getWeekString());
    }
}
