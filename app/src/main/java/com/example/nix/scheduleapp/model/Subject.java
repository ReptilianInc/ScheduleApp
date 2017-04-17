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
    private boolean mWeekType;//
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
        mWeekType = subjectParent.isWeekType();
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
        if (mWeekType){
            str = "Чётная";
        }
        if (!mWeekType){
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

    public boolean isWeekType() {
        return mWeekType;
    }

    public void setWeekType(boolean weekType) {
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
}
