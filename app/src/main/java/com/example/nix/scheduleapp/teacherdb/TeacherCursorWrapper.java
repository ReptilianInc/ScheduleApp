package com.example.nix.scheduleapp.teacherdb;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.nix.scheduleapp.model.Entity;
import com.example.nix.scheduleapp.teacherdb.TeacherDbSchema.TeacherTable;
import java.util.UUID;

/**
 * Created by Nix on 17.04.2017.
 */

public class TeacherCursorWrapper extends CursorWrapper {
    public TeacherCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Entity getTeacher(){
        Entity teacher = new Entity(UUID.fromString(getString(getColumnIndex(TeacherTable.Cols.UUID))));
        teacher.setName(getString(getColumnIndex(TeacherTable.Cols.NAME)));
        return teacher;
    }
}
