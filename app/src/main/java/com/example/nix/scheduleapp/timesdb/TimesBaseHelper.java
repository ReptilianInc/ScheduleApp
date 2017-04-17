package com.example.nix.scheduleapp.timesdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.nix.scheduleapp.timesdb.TimesDbSchema.TimesTable;

/**
 * Created by Nix on 17.04.2017.
 */

public class TimesBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timesBase.db";
    public TimesBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TimesTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TimesTable.Cols.UUID + ", " +
                TimesTable.Cols.NAME +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
