package com.example.nix.scheduleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nix on 02.04.2017.
 */

public class AddDisciplineDialog extends DialogFragment {
    public static final String EXTRA_TEXT = "com.example.nix.scheduleapp.kek";
    private EditText mEditText;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.add_dsicipline_layout, null);
        mEditText = (EditText) v.findViewById(R.id.set_discipline);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Новая дисциплина")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mEditText.getText().toString().length() > 0){
                            sendResult(Activity.RESULT_OK, mEditText.getText().toString());
                        }else {
                            Toast.makeText(getContext(), "Введите название", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .create();
    }
    private void sendResult(int resultCode, String text){
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEXT, text);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
