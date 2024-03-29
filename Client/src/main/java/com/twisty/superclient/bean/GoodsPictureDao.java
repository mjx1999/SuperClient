package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.GoodsPicture;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GOODS_PICTURE.
*/
public class GoodsPictureDao extends AbstractDao<GoodsPicture, Void> {

    public static final String TABLENAME = "GOODS_PICTURE";

    /**
     * Properties of entity GoodsPicture.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", false, "ID");
        public final static Property Picture = new Property(1, String.class, "Picture", false, "PICTURE");
        public final static Property PictureExt = new Property(2, String.class, "PictureExt", false, "PICTURE_EXT");
    };


    public GoodsPictureDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsPictureDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GOODS_PICTURE' (" + //
                "'ID' INTEGER," + // 0: ID
                "'PICTURE' TEXT," + // 1: Picture
                "'PICTURE_EXT' TEXT);"); // 2: PictureExt
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_GOODS_PICTURE_ID ON GOODS_PICTURE" +
                " (ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GOODS_PICTURE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GoodsPicture entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String Picture = entity.getPicture();
        if (Picture != null) {
            stmt.bindString(2, Picture);
        }
 
        String PictureExt = entity.getPictureExt();
        if (PictureExt != null) {
            stmt.bindString(3, PictureExt);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public GoodsPicture readEntity(Cursor cursor, int offset) {
        GoodsPicture entity = new GoodsPicture( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Picture
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // PictureExt
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GoodsPicture entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPicture(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPictureExt(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(GoodsPicture entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(GoodsPicture entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
