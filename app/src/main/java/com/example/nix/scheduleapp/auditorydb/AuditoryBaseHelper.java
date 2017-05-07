package com.example.nix.scheduleapp.auditorydb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.nix.scheduleapp.auditorydb.AuditoryDbSchema.*;

/**
 * Created by Nix on 17.04.2017.
 */

public class AuditoryBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "auditoryBase.db";
    public AuditoryBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + AuditoryTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                AuditoryTable.Cols.UUID + ", " +
                AuditoryTable.Cols.TITLE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
