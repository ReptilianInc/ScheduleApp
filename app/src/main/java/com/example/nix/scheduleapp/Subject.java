package com.example.nix.scheduleapp;

import java.util.UUID;
/**
 * Created by Nix on 24.08.2016.
 */
public class Subject {
    private String mName;//
    private int mStartHours;//
    private int mStartMinutes;//
    private int mEndHours;//
    private int mEndMinutes;//
    private int mDay;//
    private String mTeacherName;//
    private String mAuditory;//
    private int mWeekType;//
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
        mStartHours = subjectParent.getStartHours();
        mEndHours = subjectParent.getEndHours();
        mStartMinutes = subjectParent.getStartMinutes();
        mEndMinutes = subjectParent.getEndMinutes();
        mDay = subjectParent.getDay();
        mWeekType = subjectParent.isWeekType();
        mId = UUID.randomUUID();
    }
    public String getStartTime(){
        String str = "";
        if (mStartMinutes <= 9){
            str = mStartHours + ":" + "0" + mStartMinutes;
        }

        if (mStartMinutes > 9){
            str = mStartHours + ":" + mStartMinutes;
        }
        if (mStartHours == 0 && mStartMinutes == 0){
            str = "Коснитесь, чтобы назначить";
        }
        return str;
    }
    public String getEndTime(){
        String str = "";
        if (mEndMinutes <= 9){
            str = mEndHours + ":" + "0" + mEndMinutes;
        }

        if (mEndMinutes > 9){
            str = mEndHours + ":" + mEndMinutes;
        }
        if (mEndHours == 0 && mEndMinutes == 0){
            str = "Коснитесь, чтобы назначить";
        }
        return str;
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
        if (mWeekType == 1){
            str = "Чётная";
        }
        if (mWeekType == 2){
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

    public int isWeekType() {
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

    public int getStartHours() {
        return mStartHours;
    }

    public void setStartHours(int start) {
        mStartHours = start;
    }

    public int getStartMinutes() {
        return mStartMinutes;
    }

    public void setStartMinutes(int startMinutes) {
        mStartMinutes = startMinutes;
    }

    public int getEndMinutes() {
        return mEndMinutes;
    }

    public void setEndMinutes(int endMinutes) {
        mEndMinutes = endMinutes;
    }

    public int getEndHours() {
        return mEndHours;
    }

    public void setEndHours(int endHours) {
        mEndHours = endHours;
    }
}
