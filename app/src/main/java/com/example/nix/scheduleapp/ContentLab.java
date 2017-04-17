package com.example.nix.scheduleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.nix.scheduleapp.auditorydb.AuditoryBaseHelper;
import com.example.nix.scheduleapp.auditorydb.AuditoryCursorWrapper;
import com.example.nix.scheduleapp.auditorydb.AuditoryDbSchema.AuditoryTable;
import com.example.nix.scheduleapp.disciplinedb.DisciplineBaseHelper;
import com.example.nix.scheduleapp.disciplinedb.DisciplineCursorWrapper;
import com.example.nix.scheduleapp.model.Entity;
import com.example.nix.scheduleapp.model.Subject;
import com.example.nix.scheduleapp.subjectdb.SubjectBaseHelper;
import com.example.nix.scheduleapp.subjectdb.SubjectCursorWrapper;
import com.example.nix.scheduleapp.subjectdb.SubjectDbSchema.SubjectTable;
import com.example.nix.scheduleapp.teacherdb.TeacherBaseHelper;
import com.example.nix.scheduleapp.teacherdb.TeacherCursorWrapper;
import com.example.nix.scheduleapp.timesdb.TimesBaseHelper;
import com.example.nix.scheduleapp.timesdb.TimesCursorWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static com.example.nix.scheduleapp.disciplinedb.DisciplineDbSchema.*;
import static com.example.nix.scheduleapp.teacherdb.TeacherDbSchema.*;
import static com.example.nix.scheduleapp.timesdb.TimesDbSchema.*;

/**
 * Created by Nix on 24.08.2016.
 */
public class ContentLab {
    private static ContentLab sContentLab;
    private Context mContext;
    private SQLiteDatabase mSubjectDatabase;
    private SQLiteDatabase mDisciplineDatabase;
    private SQLiteDatabase mTeacherDatabase;
    private SQLiteDatabase mAuditoryDatabase;
    private SQLiteDatabase mTimeDatabase;
    public static ContentLab get(Context context)
    {
        if (sContentLab == null)
        {
            sContentLab = new ContentLab(context);
        }
        return sContentLab;
    }
    private ContentLab(Context context) {
        mContext = context.getApplicationContext();
        mSubjectDatabase = new SubjectBaseHelper(mContext).getWritableDatabase();
        mDisciplineDatabase = new DisciplineBaseHelper(mContext).getWritableDatabase();
        mTeacherDatabase = new TeacherBaseHelper(mContext).getWritableDatabase();
        mAuditoryDatabase = new AuditoryBaseHelper(mContext).getWritableDatabase();
        mTimeDatabase = new TimesBaseHelper(mContext).getWritableDatabase();
    }

    /////Получение объектов///////////
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
    public Entity getDiscipline(UUID id){
        DisciplineCursorWrapper cursor = queryDisciplines(
                DisciplineTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try{
            if (cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getDiscipline();
        }finally {
            cursor.close();
        }
    }

    public Entity getTeacher(UUID id){
        TeacherCursorWrapper cursor = queryTeachers(
                TimesTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try{
            if (cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTeacher();
        }finally {
            cursor.close();
        }
    }

    public Entity getAuditory(UUID id){
        AuditoryCursorWrapper cursor = queryAuditories(
                AuditoryTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try{
            if (cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAuditory();
        }finally {
            cursor.close();
        }
    }

    public Entity getTimes(UUID id){
        TimesCursorWrapper cursor = queryTimes(
                TimesTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try{
            if (cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTimes();
        }finally {
            cursor.close();
        }
    }
    ///////Обновление объектов/////////
    public void updateSubject(Subject subject){
        String uuidString = subject.getId().toString();
        ContentValues values = getSubjectContentValues(subject);
        mSubjectDatabase.update(SubjectTable.NAME, values, SubjectTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void updateDiscipline(Entity discipline){
        String uuidString = discipline.getId().toString();
        ContentValues values = getDisciplineContentValues(discipline);
        mDisciplineDatabase.update(DisciplineTable.NAME, values, DisciplineTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void updateTeacher(Entity teacher){
        String uuidString = teacher.getId().toString();
        ContentValues values = getTeacherContentValues(teacher);
        mTeacherDatabase.update(TeacherTable.NAME, values, TeacherTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void updateAuditory(Entity auditory){
        String uuidString = auditory.getId().toString();
        ContentValues values = getAuditoryContentValues(auditory);
        mAuditoryDatabase.update(AuditoryTable.NAME, values, AuditoryTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void updateTimes(Entity times){
        String uuidString = times.getId().toString();
        ContentValues values = getTimesContentValues(times);
        mTimeDatabase.update(TimesTable.NAME, values, TimesTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private static ContentValues getSubjectContentValues(Subject subject){
        ContentValues values = new ContentValues();
        values.put(SubjectTable.Cols.UUID, subject.getId().toString());
        values.put(SubjectTable.Cols.TITLE, subject.getName());
        values.put(SubjectTable.Cols.DAY, subject.getDay());
        values.put(SubjectTable.Cols.TEACHERNAME, subject.getTeacherName());
        values.put(SubjectTable.Cols.AUDITORY, subject.getAuditory());
        values.put(SubjectTable.Cols.WEEKTYPE, subject.isWeekType());
        return values;
    }

    private static ContentValues getDisciplineContentValues(Entity discipline){
        ContentValues values = new ContentValues();
        values.put(DisciplineTable.Cols.UUID, discipline.getId().toString());
        values.put(DisciplineTable.Cols.TITLE, discipline.getName());
        return values;
    }

    private static ContentValues getTeacherContentValues(Entity teacher){
        ContentValues values = new ContentValues();
        values.put(TeacherTable.Cols.UUID, teacher.getId().toString());
        values.put(TeacherTable.Cols.NAME, teacher.getName());
        return values;
    }

    private static ContentValues getAuditoryContentValues(Entity auditory){
        ContentValues values = new ContentValues();
        values.put(AuditoryTable.Cols.UUID, auditory.getId().toString());
        values.put(AuditoryTable.Cols.TITLE, auditory.getName());
        return values;
    }

    private static ContentValues getTimesContentValues(Entity times){
        ContentValues values = new ContentValues();
        values.put(TimesTable.Cols.UUID, times.getId().toString());
        values.put(TimesTable.Cols.NAME, times.getName());
        return values;
    }
    /////Добавление объектов////////
    public void addSubject(Subject subject){
        ContentValues values = getSubjectContentValues(subject);
        mSubjectDatabase.insert(SubjectTable.NAME, null, values);
    }
    public void addDiscipline(Entity discipline){
        ContentValues values = getDisciplineContentValues(discipline);
        mDisciplineDatabase.insert(DisciplineTable.NAME, null, values);
    }
    public void addTeacher(Entity teacher){
        ContentValues values = getTeacherContentValues(teacher);
        mTeacherDatabase.insert(TeacherTable.NAME, null, values);
    }
    public void addAuditory(Entity auditory){
        ContentValues values = getAuditoryContentValues(auditory);
        mAuditoryDatabase.insert(AuditoryTable.NAME, null, values);
    }
    public void addTimes(Entity times){
        ContentValues values = getTimesContentValues(times);
        mTimeDatabase.insert(TimesTable.NAME, null, values);
    }
    /////Удаление объектов////////
    public void deleteSubject(Subject subject){
        String idstring = subject.getName();
        mSubjectDatabase.delete(SubjectTable.NAME, SubjectTable.Cols.TITLE + " = ?", new String[]{idstring});
    }
    public void deleteDiscipline(Entity discipline){
        String idstring = discipline.getName();
        mDisciplineDatabase.delete(DisciplineTable.NAME, DisciplineTable.Cols.TITLE + " = ?", new String[]{idstring});
    }
    public void deleteTeacher(Entity teacher){
        String idstring = teacher.getName();
        mTeacherDatabase.delete(TeacherTable.NAME, TeacherTable.Cols.NAME + " = ?", new String[]{idstring});
    }
    public void deleteAuditory(Entity auditory){
        String idstring = auditory.getName();
        mAuditoryDatabase.delete(AuditoryTable.NAME, AuditoryTable.Cols.TITLE + " = ?", new String[]{idstring});
    }
    public void deleteTimes(Entity times){
        String idstring = times.getName();
        mTimeDatabase.delete(TimesTable.NAME, TimesTable.Cols.NAME + " = ?", new String[]{idstring});
    }
    /////Очистка всего////////
    public void clearAll(){
        mSubjectDatabase.delete(SubjectTable.NAME, null, null);
        mDisciplineDatabase.delete(DisciplineTable.NAME,null,null);
        mTeacherDatabase.delete(TeacherTable.NAME, null, null);
        mAuditoryDatabase.delete(AuditoryTable.NAME, null, null);
        mTimeDatabase.delete(TimesTable.NAME, null, null);
    }
    /////Получение списков////////
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

    public List<Entity> getDisciplines(){
        List<Entity> disciplines = new ArrayList<>();
        DisciplineCursorWrapper cursor = queryDisciplines(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                if (cursor.getDiscipline() != null){
                    disciplines.add(cursor.getDiscipline());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return disciplines;
    }
    public List<Entity> getTeachers(){
        List<Entity> teachers = new ArrayList<>();
        TeacherCursorWrapper cursor = queryTeachers(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                if (cursor.getTeacher() != null){
                    teachers.add(cursor.getTeacher());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return teachers;
    }
    public List<Entity> getAuditory(){
        List<Entity> auditories = new ArrayList<>();
        AuditoryCursorWrapper cursor = queryAuditories(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                if (cursor.getAuditory() != null){
                    auditories.add(cursor.getAuditory());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return auditories;
    }
    public List<Entity> getTimes(){
        List<Entity> times = new ArrayList<>();
        TimesCursorWrapper cursor = queryTimes(null, null);
        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                if (cursor.getTimes() != null){
                    times.add(cursor.getTimes());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return times;
    }
    /////Запросы////////
    private SubjectCursorWrapper querySubjects(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mSubjectDatabase.query(
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
    private DisciplineCursorWrapper queryDisciplines(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDisciplineDatabase.query(
                DisciplineTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DisciplineCursorWrapper(cursor);
    }
    private TeacherCursorWrapper queryTeachers(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mTeacherDatabase.query(
                TeacherTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new TeacherCursorWrapper(cursor);
    }
    private AuditoryCursorWrapper queryAuditories(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mAuditoryDatabase.query(
                AuditoryTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new AuditoryCursorWrapper(cursor);
    }
    private TimesCursorWrapper queryTimes(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mTimeDatabase.query(
                TimesTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new TimesCursorWrapper(cursor);
    }
}