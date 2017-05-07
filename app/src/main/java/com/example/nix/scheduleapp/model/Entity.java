package com.example.nix.scheduleapp.model;

import java.util.UUID;

/**
 * Created by Nix on 18.04.2017.
 */

public class Entity {
    private UUID mId;
    private String mName;
    public Entity(UUID id){
        mId = id;
    }
    public Entity(){
        this(UUID.randomUUID());
    }
    public Entity(String name){
        mName = name;
        mId = UUID.randomUUID();
    }
    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
