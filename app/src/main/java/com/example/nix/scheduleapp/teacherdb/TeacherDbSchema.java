package com.example.nix.scheduleapp.teacherdb;

/**
 * Created by Nix on 17.04.2017.
 */

public class TeacherDbSchema {
    public static final class TeacherTable{
        public static final String NAME = "teachers";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
        }
    }
}
