package com.example.nix.scheduleapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.nix.scheduleapp.Subject;
import com.example.nix.scheduleapp.database.SubjectDbSchema.SubjectTable;

import java.util.UUID;

/**
 * Created by Nix on 28.08.2016.
 */
public class SubjectCursorWrapper extends CursorWrapper {
    public SubjectCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }
    public Subject getSubject(int weekTypeNeed, int dayNeed){ //рашка говно путин чмо
        int weektype = getInt(getColumnIndex(SubjectTable.Cols.WEEKTYPE));
        int day = getInt(getColumnIndex(SubjectTable.Cols.DAY));
        if (weektype == weekTypeNeed && day == dayNeed){
            Subject subject = new Subject(UUID.fromString(getString(getColumnIndex(SubjectTable.Cols.UUID))));
            subject.setName(getString(getColumnIndex(SubjectTable.Cols.TITLE)));
            subject.setStartHours(getInt(getColumnIndex(SubjectTable.Cols.STARTHOURS)));
            subject.setStartMinutes(getInt(getColumnIndex(SubjectTable.Cols.STARTMINUTES)));
            subject.setEndHours(getInt(getColumnIndex(SubjectTable.Cols.ENDHOURS)));
            subject.setEndMinutes(getInt(getColumnIndex(SubjectTable.Cols.ENDMINUTES)));
            subject.setDay(getInt(getColumnIndex(SubjectTable.Cols.DAY)));
            subject.setTeacherName(getString(getColumnIndex(SubjectTable.Cols.TEACHERNAME)));
            subject.setAuditory(getString(getColumnIndex(SubjectTable.Cols.AUDITORY)));
            subject.setWeekType(getInt(getColumnIndex(SubjectTable.Cols.WEEKTYPE)));
            return subject;
        }else {
            return null;
        }
    }
    public Subject getSubject(){
        Subject subject = new Subject(UUID.fromString(getString(getColumnIndex(SubjectTable.Cols.UUID))));
        subject.setName(getString(getColumnIndex(SubjectTable.Cols.TITLE)));
        subject.setStartHours(getInt(getColumnIndex(SubjectTable.Cols.STARTHOURS)));
        subject.setStartMinutes(getInt(getColumnIndex(SubjectTable.Cols.STARTMINUTES)));
        subject.setEndHours(getInt(getColumnIndex(SubjectTable.Cols.ENDHOURS)));
        subject.setEndMinutes(getInt(getColumnIndex(SubjectTable.Cols.ENDMINUTES)));
        subject.setDay(getInt(getColumnIndex(SubjectTable.Cols.DAY)));
        subject.setTeacherName(getString(getColumnIndex(SubjectTable.Cols.TEACHERNAME)));
        subject.setAuditory(getString(getColumnIndex(SubjectTable.Cols.AUDITORY)));
        subject.setWeekType(getInt(getColumnIndex(SubjectTable.Cols.WEEKTYPE)));
        return subject;
    }
}