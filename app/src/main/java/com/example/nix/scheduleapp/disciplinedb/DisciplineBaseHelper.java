package com.example.nix.scheduleapp.disciplinedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nix.scheduleapp.disciplinedb.DisciplineDbSchema.DisciplineTable;

/**
 * Created by Nix on 02.04.2017.
 */

public class DisciplineBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "disciplineBase.db";
    public DisciplineBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DisciplineTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DisciplineTable.Cols.UUID + ", " +
                DisciplineTable.Cols.TITLE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
