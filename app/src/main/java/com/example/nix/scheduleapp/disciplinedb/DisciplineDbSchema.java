package com.example.nix.scheduleapp.disciplinedb;

/**
 * Created by Nix on 02.04.2017.
 */

public class DisciplineDbSchema {
    public static final class DisciplineTable{
        public static final String NAME = "disciplines";
        public static final class Cols
        {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
        }
    }
}
