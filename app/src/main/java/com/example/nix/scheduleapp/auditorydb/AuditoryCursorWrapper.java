package com.example.nix.scheduleapp.auditorydb;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.nix.scheduleapp.model.Entity;
import java.util.UUID;

/**
 * Created by Nix on 17.04.2017.
 */

public class AuditoryCursorWrapper extends CursorWrapper {
    public AuditoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Entity getAuditory(){
        Entity auditory = new Entity(UUID.fromString(getString(getColumnIndex(AuditoryDbSchema.AuditoryTable.Cols.UUID))));
        auditory.setName(getString(getColumnIndex(AuditoryDbSchema.AuditoryTable.Cols.TITLE)));
        return auditory;
    }
}
