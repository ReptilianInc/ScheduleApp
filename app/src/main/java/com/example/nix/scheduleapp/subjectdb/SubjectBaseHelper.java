package com.example.nix.scheduleapp.subjectdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.nix.scheduleapp.subjectdb.SubjectDbSchema.SubjectTable;

/**
 * Created by Nix on 28.08.2016.
 */
public class SubjectBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "subjectBase.db";
    public SubjectBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SubjectTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
        SubjectTable.Cols.UUID + ", " +
        SubjectTable.Cols.TITLE_UUID + ", " +
        SubjectTable.Cols.DAY + ", " +
        SubjectTable.Cols.TEACHER_UUID + ", " +
        SubjectTable.Cols.AUDITORY_UUID + ", " +
        SubjectTable.Cols.TIMES_UUID + ", " +
        SubjectTable.Cols.WEEKTYPE +
        ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}