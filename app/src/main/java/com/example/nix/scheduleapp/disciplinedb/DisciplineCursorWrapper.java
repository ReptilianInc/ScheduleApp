package com.example.nix.scheduleapp.disciplinedb;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.nix.scheduleapp.model.Entity;

import java.util.UUID;

import static com.example.nix.scheduleapp.disciplinedb.DisciplineDbSchema.*;

/**
 * Created by Nix on 02.04.2017.
 */

public class DisciplineCursorWrapper extends CursorWrapper {
    public DisciplineCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Entity getDiscipline(){
        Entity entity = new Entity(UUID.fromString(getString(getColumnIndex(DisciplineTable.Cols.UUID))));
        entity.setName(getString(getColumnIndex(DisciplineTable.Cols.TITLE)));
        return entity;
    }
}
