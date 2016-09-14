package com.example.nix.scheduleapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nix on 27.08.2016.
 */
public class WeekPickerFragment extends DialogFragment{
    private TextView mEven, mOdd;
    public static final String EXTRA_WEEKTYPE = "com.bignerdranch.android.criminalintent.WEEKTYPE";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getDialog().setTitle(R.string.name_of_week_fragment);
        View v = inflater.inflate(R.layout.dialog_week, null);
        mEven = (TextView)v.findViewById(R.id.even);
        mOdd = (TextView)v.findViewById(R.id.odd);
        mEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(Activity.RESULT_OK, 1);
                getDialog().dismiss();
            }
        });
        mOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(Activity.RESULT_OK, 2);
                getDialog().dismiss();
            }
        });
        return v;
    }
    @Override
    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
    }
    @Override
    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }
    private void sendResult(int resultCodeWeekType, int type_of_week)
    {
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_WEEKTYPE, type_of_week);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCodeWeekType, intent);
    }
}
