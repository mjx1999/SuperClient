package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.Accset;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ACCSET.
*/
public class AccsetDao extends AbstractDao<Accset, Void> {

    public static final String TABLENAME = "ACCSET";

    /**
     * Properties of entity Accset.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property AccsetID = new Property(0, Long.class, "AccsetID", false, "ACCSET_ID");
        public final static Property AccsetCode = new Property(1, String.class, "AccsetCode", false, "ACCSET_CODE");
        public final static Property AccsetName = new Property(2, String.class, "AccsetName", false, "ACCSET_NAME");
    };


    public AccsetDao(DaoConfig config) {
        super(config);
    }
    
    public AccsetDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ACCSET' (" + //
                "'ACCSET_ID' INTEGER," + // 0: AccsetID
                "'ACCSET_CODE' TEXT," + // 1: AccsetCode
                "'ACCSET_NAME' TEXT);"); // 2: AccsetName
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_ACCSET_ACCSET_ID ON ACCSET" +
                " (ACCSET_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ACCSET'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Accset entity) {
        stmt.clearBindings();
 
        Long AccsetID = entity.getAccsetID();
        if (AccsetID != null) {
            stmt.bindLong(1, AccsetID);
        }
 
        String AccsetCode = entity.getAccsetCode();
        if (AccsetCode != null) {
            stmt.bindString(2, AccsetCode);
        }
 
        String AccsetName = entity.getAccsetName();
        if (AccsetName != null) {
            stmt.bindString(3, AccsetName);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Accset readEntity(Cursor cursor, int offset) {
        Accset entity = new Accset( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // AccsetID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // AccsetCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // AccsetName
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Accset entity, int offset) {
        entity.setAccsetID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccsetCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAccsetName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Accset entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Accset entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
