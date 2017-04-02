package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nix on 02.04.2017.
 */

public class AddDisciplineOrHWDialog extends DialogFragment implements View.OnClickListener {
    public static final String EXTRA_ACTION = "com.example.nix.scheduleapp.kek";
    private TextView disc, hw;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Добавить...");
        View v = inflater.inflate(R.layout.add_disc_or_hw_dialog, null);
        disc = (TextView) v.findViewById(R.id.var1);
        hw = (TextView)v.findViewById(R.id.var2);
        disc.setOnClickListener(this);
        hw.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.var1:
                sendResult(Activity.RESULT_OK, true);
                dismiss();
                break;
            case R.id.var2:
                sendResult(Activity.RESULT_OK, false);
                dismiss();
                break;
        }
    }
    private void sendResult(int resultCode, boolean action){
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ACTION, action);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
