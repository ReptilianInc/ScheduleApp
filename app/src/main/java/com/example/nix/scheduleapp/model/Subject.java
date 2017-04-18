package com.example.nix.scheduleapp.model;

import java.util.UUID;
/**
 * Created by Nix on 24.08.2016.
 */
public class Subject {
    private String mName;//
    private int mDay;//
    private String mTeacherName;//
    private String mAuditory;//
    private String mTimes;
    private int mWeekType;//
    public static final int WEEK_TYPE_ODD = 1;
    public static final int WEEK_TYPE_EVEN = 2;
    private UUID mId;
    public Subject(){
        this(UUID.randomUUID());
    }
    public Subject(UUID id){
        mId = id;
    }
    public Subject (Subject subjectParent){
        mName = subjectParent.getName();
        mTeacherName = subjectParent.getTeacherName();
        mAuditory = subjectParent.getAuditory();
        mDay = subjectParent.getDay();
        mWeekType = subjectParent.getWeekType();
        mId = UUID.randomUUID();
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
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public String getTeacherName() {
        return mTeacherName;
    }

    public void setTeacherName(String teacherName) {
        mTeacherName = teacherName;
    }

    public String getAuditory() {
        return mAuditory;
    }

    public void setAuditory(String auditory) {
        mAuditory = auditory;
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

    public String getTimes() {
        return mTimes;
    }

    public void setTimes(String times) {
        mTimes = times;
    }
}
