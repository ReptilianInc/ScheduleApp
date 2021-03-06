package com.example.nix.scheduleapp.model;

import java.util.UUID;
/**
 * Created by Nix on 24.08.2016.
 */
public class Subject {
    private int mDay;//
    private int mWeekType;//
    public static final int WEEK_TYPE_ODD = 1;
    public static final int WEEK_TYPE_EVEN = 2;
    private UUID mId, mDisciplineId, mTeacherId, mAuditoryId, mTimesId;
    public Subject(){
        this(UUID.randomUUID());
    }
    public Subject(UUID id){
        mId = id;
    }
    public Subject (Subject subjectParent){
        mDay = subjectParent.getDay();
        mWeekType = subjectParent.getWeekType();
        mId = UUID.randomUUID();
        mDisciplineId = subjectParent.getDisciplineId();
        mTeacherId = subjectParent.getTeacherId();
        mAuditoryId = subjectParent.getAuditoryId();
        mTimesId = subjectParent.getTimesId();
    }
    public String getDayString(){
        String str;
        switch (mDay){
            case 2:
                str = "Понедельник";
                break;
            case 3:
                str = "Вторник";
                break;
            case 4:
                str = "Среда";
                break;
            case 5:
                str = "Четверг";
                break;
            case 6:
                str = "Пятница";
                break;
            case 7:
                str = "Суббота";
                break;
            default:
                str  = "Коснитесь, чтобы назначить";
        }
        return str;
    }
    public String getWeekString(){
        String str = "Коснитесь, чтобы назначить";
        if (mWeekType == WEEK_TYPE_EVEN){
            str = "Чётная";
        }
        if (mWeekType == WEEK_TYPE_ODD){
            str = "Нечётная";
        }
        return str;
    }





    public int getWeekType() {
        return mWeekType;
    }

    public void setWeekType(int weekType) {
        mWeekType = weekType;
    }
    public UUID getId() {
        return mId;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }


    public UUID getDisciplineId() {
        return mDisciplineId;
    }

    public void setDisciplineId(UUID titleId) {
        mDisciplineId = titleId;
    }

    public UUID getTeacherId() {
        return mTeacherId;
    }

    public void setTeacherId(UUID teacherId) {
        mTeacherId = teacherId;
    }

    public UUID getAuditoryId() {
        return mAuditoryId;
    }

    public void setAuditoryId(UUID auditoryId) {
        mAuditoryId = auditoryId;
    }

    public UUID getTimesId() {
        return mTimesId;
    }

    public void setTimesId(UUID timesId) {
        mTimesId = timesId;
    }
}
