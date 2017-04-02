package com.example.nix.scheduleapp;

import java.util.UUID;

/**
 * Created by Nix on 02.04.2017.
 */

public class Discipline {
    private UUID mId;
    private String mTitle;
    public Discipline(){
        this(UUID.randomUUID());
    }
    public Discipline(UUID id){
        mId = id;
    }
    public Discipline (Discipline disciplineParent){
        mTitle = disciplineParent.getTitle();
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
