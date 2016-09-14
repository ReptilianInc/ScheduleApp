package com.example.nix.scheduleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.nix.scheduleapp.database.SubjectBaseHelper;
import com.example.nix.scheduleapp.database.SubjectCursorWrapper;
import com.example.nix.scheduleapp.database.SubjectDbSchema.SubjectTable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nix on 24.08.2016.
 */
public class SubjectLab {
    private static SubjectLab sSubjectLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static SubjectLab get(Context context)
    {
        if (sSubjectLab == null)
        {
            sSubjectLab = new SubjectLab(context);
        }
        return sSubjectLab;
    }
    private SubjectLab (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SubjectBaseHelper(mContext).getWritableDatabase();
    }
    public List<Subject> getSubjects()
    {
        List<Subject> subjects = new ArrayList<>();
        SubjectCursorWrapper cursor = querySubjects(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                subjects.add(cursor.getSubject());
                cursor.moveToNext();
            }
        } finally
        {
            cursor.close();
        }
        return subjects;
    }
    public Subject getSubject(UUID id)
    {
        SubjectCursorWrapper cursor = querySubjects(
                SubjectTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try{
            if (cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getSubject();
        }finally {
            cursor.close();
        }
    }
    public void updateSubject(Subject subject){
        String uuidString = subject.getId().toString();
        ContentValues values = getContentValues(subject);
        mDatabase.update(SubjectTable.NAME, values, SubjectTable.Cols.UUID + " = ?", new String[]{uuidString});
    }
    private static ContentValues getContentValues(Subject subject){
        ContentValues values = new ContentValues();
        values.put(SubjectTable.Cols.UUID, subject.getId().toString());
        values.put(SubjectTable.Cols.TITLE, subject.getName());
        values.put(SubjectTable.Cols.STARTHOURS, subject.getStartHours());
        values.put(SubjectTable.Cols.STARTMINUTES, subject.getStartMinutes());
        values.put(SubjectTable.Cols.ENDHOURS, subject.getEndHours());
        values.put(SubjectTable.Cols.ENDMINUTES, subject.getEndMinutes());
        values.put(SubjectTable.Cols.DAY, subject.getDay());
        values.put(SubjectTable.Cols.TEACHERNAME, subject.getTeacherName());
        values.put(SubjectTable.Cols.AUDITORY, subject.getAuditory());
        values.put(SubjectTable.Cols.WEEKTYPE, subject.isWeekType());
        return values;
    }

    public void addSubject(Subject subject){
        ContentValues values = getContentValues(subject);
        mDatabase.insert(SubjectTable.NAME, null, values);
    }
    public void deleteSubject(Subject subject){
        String idstring = subject.getName();
        mDatabase.delete(SubjectTable.NAME, SubjectTable.Cols.TITLE + " = ?", new String[]{idstring});
    }
    public void clearAll(){
        mDatabase.delete(SubjectTable.NAME, null, null);
    }
    public List<Subject> getSubjects(int weekType, int day){
        List<Subject> Subjects = new ArrayList<>();
        SubjectCursorWrapper cursor = querySubjects(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                if (cursor.getSubject(weekType, day) != null){
                    Subjects.add(cursor.getSubject(weekType, day));
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Subjects;
    }
    /*public List<Subject> getOddSubjects(int day){
        List<Subject> OddSubjects = new ArrayList<>();
        for (Subject subject : mSubjects)
        {
            if (subject.isWeekType() == 2 && subject.getDay() == day)
            {
                OddSubjects.add(subject);
            }
        }
        return OddSubjects;
    }*/
    private SubjectCursorWrapper querySubjects(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                SubjectTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
                );
        return new SubjectCursorWrapper(cursor);
    }
}
