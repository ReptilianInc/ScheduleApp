package com.example.nix.scheduleapp.homeworkdb;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.nix.scheduleapp.Homework;
import java.util.UUID;
import static com.example.nix.scheduleapp.homeworkdb.HomeworkDbSchema.*;

/**
 * Created by Nix on 28.03.2017.
 */

public class HomeworkCursorWrapper extends CursorWrapper{
    public HomeworkCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Homework getHomework(){
        Homework homework = new Homework(UUID.fromString(getString(getColumnIndex(HomeworkTable.Cols.UUID))));
        homework.setDescr(getString(getColumnIndex(HomeworkTable.Cols.DESCR)));
        homework.setIdSubject(UUID.fromString(getString(getColumnIndex(HomeworkTable.Cols.SUBJECT_UUID))));
        return homework;
    }
}
