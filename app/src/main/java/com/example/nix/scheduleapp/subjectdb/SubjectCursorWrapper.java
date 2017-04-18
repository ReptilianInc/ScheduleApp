package com.example.nix.scheduleapp.subjectdb;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.nix.scheduleapp.model.Subject;
import com.example.nix.scheduleapp.subjectdb.SubjectDbSchema.SubjectTable;
import java.util.UUID;

/**
 * Created by Nix on 28.08.2016.
 */
public class SubjectCursorWrapper extends CursorWrapper {
    public SubjectCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }
    public Subject getSubject(int weekTypeNeed, int dayNeed){
        int weektype = getInt(getColumnIndex(SubjectTable.Cols.WEEKTYPE));
        int day = getInt(getColumnIndex(SubjectTable.Cols.DAY));
        if (weektype == weekTypeNeed && day == dayNeed){
            Subject subject = new Subject(UUID.fromString(getString(getColumnIndex(SubjectTable.Cols.UUID))));
            subject.setName(getString(getColumnIndex(SubjectTable.Cols.TITLE)));
            subject.setDay(getInt(getColumnIndex(SubjectTable.Cols.DAY)));
            subject.setTeacherName(getString(getColumnIndex(SubjectTable.Cols.TEACHER_UUID)));
            subject.setAuditory(getString(getColumnIndex(SubjectTable.Cols.AUDITORY_UUID)));
            subject.setTimes(getString(getColumnIndex(SubjectTable.Cols.TIMES_UUID)));
            subject.setWeekType(getInt(getColumnIndex(SubjectTable.Cols.WEEKTYPE)));
            return subject;
        }else {
            return null;
        }
    }
    public Subject getSubject(){
        Subject subject = new Subject(UUID.fromString(getString(getColumnIndex(SubjectTable.Cols.UUID))));
        subject.setName(getString(getColumnIndex(SubjectTable.Cols.TITLE)));
        subject.setDay(getInt(getColumnIndex(SubjectTable.Cols.DAY)));
        subject.setTeacherName(getString(getColumnIndex(SubjectTable.Cols.TEACHER_UUID)));
        subject.setAuditory(getString(getColumnIndex(SubjectTable.Cols.AUDITORY_UUID)));
        subject.setTimes(getString(getColumnIndex(SubjectTable.Cols.TIMES_UUID)));
        subject.setWeekType(getInt(getColumnIndex(SubjectTable.Cols.WEEKTYPE)));
        return subject;
    }
}
