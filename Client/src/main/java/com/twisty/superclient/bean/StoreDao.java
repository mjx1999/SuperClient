package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.Store;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table STORE.
*/
public class StoreDao extends AbstractDao<Store, Void> {

    public static final String TABLENAME = "STORE";

    /**
     * Properties of entity Store.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property StoreID = new Property(0, Long.class, "StoreID", false, "STORE_ID");
        public final static Property ShopID = new Property(1, Long.class, "ShopID", false, "SHOP_ID");
        public final static Property StoreCode = new Property(2, String.class, "StoreCode", false, "STORE_CODE");
        public final static Property StoreName = new Property(3, String.class, "StoreName", false, "STORE_NAME");
        public final static Property Location = new Property(4, String.class, "Location", false, "LOCATION");
        public final static Property Closed = new Property(5, Integer.class, "Closed", false, "CLOSED");
    };


    public StoreDao(DaoConfig config) {
        super(config);
    }
    
    public StoreDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'STORE' (" + //
                "'STORE_ID' INTEGER," + // 0: StoreID
                "'SHOP_ID' INTEGER," + // 1: ShopID
                "'STORE_CODE' TEXT," + // 2: StoreCode
                "'STORE_NAME' TEXT," + // 3: StoreName
                "'LOCATION' TEXT," + // 4: Location
                "'CLOSED' INTEGER);"); // 5: Closed
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_STORE_STORE_ID ON STORE" +
                " (STORE_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_STORE_SHOP_ID ON STORE" +
                " (SHOP_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'STORE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Store entity) {
        stmt.clearBindings();
 
        Long StoreID = entity.getStoreID();
        if (StoreID != null) {
            stmt.bindLong(1, StoreID);
        }
 
        Long ShopID = entity.getShopID();
        if (ShopID != null) {
            stmt.bindLong(2, ShopID);
        }
 
        String StoreCode = entity.getStoreCode();
        if (StoreCode != null) {
            stmt.bindString(3, StoreCode);
        }
 
        String StoreName = entity.getStoreName();
        if (StoreName != null) {
            stmt.bindString(4, StoreName);
        }
 
        String Location = entity.getLocation();
        if (Location != null) {
            stmt.bindString(5, Location);
        }
 
        Integer Closed = entity.getClosed();
        if (Closed != null) {
            stmt.bindLong(6, Closed);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Store readEntity(Cursor cursor, int offset) {
        Store entity = new Store( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // StoreID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // ShopID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // StoreCode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // StoreName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Location
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // Closed
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Store entity, int offset) {
        entity.setStoreID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setShopID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setStoreCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStoreName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLocation(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setClosed(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Store entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Store entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
