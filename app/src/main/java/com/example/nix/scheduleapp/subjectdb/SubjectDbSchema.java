package com.example.nix.scheduleapp.subjectdb;

/**
 * Created by Nix on 28.08.2016.
 */
public class SubjectDbSchema {
    public static final class SubjectTable{
        public static final String NAME = "subjects";
        public static final class Cols
        {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DAY = "day";
            public static final String TEACHER_UUID = "teachername";
            public static final String AUDITORY_UUID = "auditory";
            public static final String TIMES_UUID = "times";
            public static final String WEEKTYPE = "weektype";
        }
    }
}
