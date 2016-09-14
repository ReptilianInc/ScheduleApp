package com.example.nix.scheduleapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;


/**
 * Created by Nix on 27.08.2016.
 */
public class TimePickerFragment extends DialogFragment {
    public static final String EXTRA_HOUR = "com.bignerdranch.android.criminalintent.HOUR";
    public static final String EXTRA_MINUTE = "com.bignerdranch.android.criminalintent.MINUTE";
    private TimePicker mTimePicker;
    private int mHour;
    private int mMinute;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        mTimePicker = (TimePicker)v.findViewById(R.id.dialog_time);
        return new AlertDialog.Builder(getActivity())
                .setView(v).setTitle(R.string.name_of_time_fragment)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mHour = mTimePicker.getCurrentHour();
                        mMinute = mTimePicker.getCurrentMinute();
                        sendResult(Activity.RESULT_OK, mHour, Activity.RESULT_OK, mMinute);
                    }
                })
                .create();
    }
    private void sendResult(int resultCodeHour, int hour, int resultCodeMinute, int minute)
    {
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HOUR, hour);
        intent.putExtra(EXTRA_MINUTE, minute);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCodeHour, intent);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCodeMinute, intent);
    }
}
