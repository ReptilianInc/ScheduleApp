package com.example.nix.scheduleapp.auditorydb;

/**
 * Created by Nix on 17.04.2017.
 */

public class AuditoryDbSchema {
    public static final class AuditoryTable{
        public static final String NAME = "auditories";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
        }
    }
}
