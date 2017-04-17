package com.example.nix.scheduleapp.timesdb;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.nix.scheduleapp.model.Entity;
import com.example.nix.scheduleapp.timesdb.TimesDbSchema.TimesTable;

import java.util.UUID;

/**
 * Created by Nix on 17.04.2017.
 */

public class TimesCursorWrapper extends CursorWrapper {
    public TimesCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Entity getTimes(){
        Entity times = new Entity(UUID.fromString(getString(getColumnIndex(TimesTable.Cols.UUID))));
        times.setName(getString(getColumnIndex(TimesTable.Cols.NAME)));
        return times;
    }
}
