package com.example.nix.scheduleapp.teacherdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.nix.scheduleapp.teacherdb.TeacherDbSchema.*;

/**
 * Created by Nix on 17.04.2017.
 */

public class TeacherBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "teacherBase.db";
    public TeacherBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TeacherTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TeacherTable.Cols.UUID + ", " +
                TeacherTable.Cols.NAME +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
