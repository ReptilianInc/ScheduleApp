package com.example.nix.scheduleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.nix.scheduleapp.model.Entity;
import java.util.UUID;

/**
 * Created by Nix on 02.04.2017.
 */

public class AddEntityDialog extends DialogFragment {
    public static final String GET_UUID = "string";
    public static final String GET_CODE = "code";
    private UUID getExtraUUID;
    private int getExtraCode;
    private EditText mEditText;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        getExtraUUID = (UUID)bundle.getSerializable(GET_UUID);
        getExtraCode = (int)bundle.getSerializable(GET_CODE);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.add_entity_layout, null);
        mEditText = (EditText) v.findViewById(R.id.set_discipline);
        final Entity entity = getEntity(getExtraCode, getExtraUUID);
        if(entity != null){
            mEditText.setText(entity.getName());
        }
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Ввод данных")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContentLab cb = ContentLab.get(getContext());
                        if(entity != null){
                            if(mEditText.getText().toString().length() != 0) {
                                entity.setName(mEditText.getText().toString());
                                switch (getExtraCode) {
                                    case EntityFragment.DISCIPLINE_CODE:
                                        cb.updateDiscipline(entity);
                                        break;
                                    case EntityFragment.TEACHER_CODE:
                                        cb.updateTeacher(entity);
                                        break;
                                    case EntityFragment.AUDITORY_CODE:
                                        cb.updateAuditory(entity);
                                        break;
                                    case EntityFragment.TIMES_CODE:
                                        cb.updateTimes(entity);
                                        break;
                                }
                            }else{
                                Toast.makeText(getContext(), "Введите данные", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            if(mEditText.getText().toString().length() != 0){
                                switch (getExtraCode){
                                    case EntityFragment.DISCIPLINE_CODE:
                                        cb.addDiscipline(new Entity(mEditText.getText().toString()));
                                        break;
                                    case EntityFragment.TEACHER_CODE:
                                        cb.addTeacher(new Entity(mEditText.getText().toString()));
                                        break;
                                    case EntityFragment.AUDITORY_CODE:
                                        cb.addAuditory(new Entity(mEditText.getText().toString()));
                                        break;
                                    case EntityFragment.TIMES_CODE:
                                        cb.addTimes(new Entity(mEditText.getText().toString()));
                                        break;
                                }
                            }else {
                                Toast.makeText(getContext(), "Введите данные", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .create();
    }

    public static AddEntityDialog newInstance(UUID id, int code){
        Bundle bundle = new Bundle();
        bundle.putSerializable(GET_UUID, id);
        bundle.putSerializable(GET_CODE, code);
        AddEntityDialog dialog = new AddEntityDialog();
        dialog.setArguments(bundle);
        return dialog;
    }
    public static AddEntityDialog newInstance(int code){
        Bundle bundle = new Bundle();
        bundle.putSerializable(GET_CODE, code);
        AddEntityDialog dialog = new AddEntityDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
    private Entity getEntity(int code, UUID id){
        Entity entity = new Entity();
        if (id != null){
            switch (code){
                case EntityFragment.DISCIPLINE_CODE:
                    entity = ContentLab.get(getContext()).getDiscipline(id);
                    break;
                case EntityFragment.TEACHER_CODE:
                    entity = ContentLab.get(getContext()).getTeacher(id);
                    break;
                case EntityFragment.AUDITORY_CODE:
                    entity = ContentLab.get(getContext()).getAuditory(id);
                    break;
                case EntityFragment.TIMES_CODE:
                    entity = ContentLab.get(getContext()).getTimes(id);
                    break;
            }
            return entity;
        }else{
            return null;
        }
    }
}
