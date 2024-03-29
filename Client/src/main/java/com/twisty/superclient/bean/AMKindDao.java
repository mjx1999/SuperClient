package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.AMKind;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table AMKIND.
*/
public class AMKindDao extends AbstractDao<AMKind, Void> {

    public static final String TABLENAME = "AMKIND";

    /**
     * Properties of entity AMKind.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", false, "ID");
        public final static Property Name = new Property(1, String.class, "Name", false, "NAME");
        public final static Property Kind = new Property(2, Long.class, "Kind", false, "KIND");
        public final static Property KindName = new Property(3, String.class, "KindName", false, "KIND_NAME");
    };


    public AMKindDao(DaoConfig config) {
        super(config);
    }
    
    public AMKindDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'AMKIND' (" + //
                "'ID' INTEGER," + // 0: ID
                "'NAME' TEXT," + // 1: Name
                "'KIND' INTEGER," + // 2: Kind
                "'KIND_NAME' TEXT);"); // 3: KindName
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_AMKIND_ID ON AMKIND" +
                " (ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_AMKIND_KIND ON AMKIND" +
                " (KIND);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'AMKIND'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AMKind entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(2, Name);
        }
 
        Long Kind = entity.getKind();
        if (Kind != null) {
            stmt.bindLong(3, Kind);
        }
 
        String KindName = entity.getKindName();
        if (KindName != null) {
            stmt.bindString(4, KindName);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public AMKind readEntity(Cursor cursor, int offset) {
        AMKind entity = new AMKind( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // Kind
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // KindName
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AMKind entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKind(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setKindName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(AMKind entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(AMKind entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
