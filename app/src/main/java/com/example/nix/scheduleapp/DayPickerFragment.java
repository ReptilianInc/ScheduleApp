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
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nix on 26.08.2016.
 */
public class DayPickerFragment extends DialogFragment{
    private TextView mMonday, mTuesday, mWednesday, mThursday, mFriday,mSaturday;
    public static final String EXTRA_DAY = "com.bignerdranch.android.criminalintent.DAY";
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       getDialog().setTitle(R.string.name_of_day_fragment);
       View v = inflater.inflate(R.layout.dialog_date, null);
       mMonday = (TextView)v.findViewById(R.id.mon);
       mTuesday = (TextView)v.findViewById(R.id.tue);
       mWednesday = (TextView)v.findViewById(R.id.wed);
       mThursday = (TextView)v.findViewById(R.id.thu);
       mFriday = (TextView)v.findViewById(R.id.fri);
       mSaturday = (TextView)v.findViewById(R.id.sat);
       mMonday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendResult(Activity.RESULT_OK, 2);
               getDialog().dismiss();
           }
       });
       mTuesday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendResult(Activity.RESULT_OK, 3);
               getDialog().dismiss();
           }
       });
       mWednesday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendResult(Activity.RESULT_OK, 4);
               getDialog().dismiss();
           }
       });
       mThursday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendResult(Activity.RESULT_OK, 5);
               getDialog().dismiss();
           }
       });
       mFriday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendResult(Activity.RESULT_OK, 6);
               getDialog().dismiss();
           }
       });
       mSaturday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendResult(Activity.RESULT_OK, 7);
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
    private void sendResult(int resultCodeDay, int day_of_week)
    {
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DAY, day_of_week);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCodeDay, intent);
    }
}
