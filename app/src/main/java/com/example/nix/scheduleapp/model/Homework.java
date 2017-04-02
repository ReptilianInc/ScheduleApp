package com.example.nix.scheduleapp.model;

import java.util.UUID;

/**
 * Created by Nix on 28.03.2017.
 */

public class Homework {
    private UUID mId;
    private UUID mIdSubject;
    private String mDescr;
    public Homework(){
        this(UUID.randomUUID());
    }

    public Homework(UUID id){
        mId = id;
    }
    public Homework(Homework hm){
        mId = UUID.randomUUID();
        mDescr = hm.getDescr();
    }
    public UUID getId() {
        return mId;
    }

    public void setId(UUID Id) {
        mId = Id;
    }

    public String getDescr() {
        return mDescr;
    }

    public void setDescr(String descr) {
        mDescr = descr;
    }

    public UUID getIdSubject() {
        return mIdSubject;
    }

    public void setIdSubject(UUID idSubject) {
        mIdSubject = idSubject;
    }
}
