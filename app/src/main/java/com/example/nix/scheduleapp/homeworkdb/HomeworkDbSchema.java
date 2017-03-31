package com.example.nix.scheduleapp.homeworkdb;

/**
 * Created by Nix on 28.03.2017.
 */

public class HomeworkDbSchema {
    public static final class HomeworkTable{
        public static final String NAME = "homework";
        public static final class Cols
        {
            public static final String UUID = "uuid";
            public static final String DESCR = "title";
            public static final String SUBJECT_UUID = "subject_uuid";
        }
    }
}
