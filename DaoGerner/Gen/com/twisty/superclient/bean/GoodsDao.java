package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.Goods;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GOODS.
*/
public class GoodsDao extends AbstractDao<Goods, Void> {

    public static final String TABLENAME = "GOODS";

    /**
     * Properties of entity Goods.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property GoodsID = new Property(0, Long.class, "GoodsID", false, "GOODS_ID");
        public final static Property GoodsCode = new Property(1, String.class, "GoodsCode", false, "GOODS_CODE");
        public final static Property GoodsName = new Property(2, String.class, "GoodsName", false, "GOODS_NAME");
        public final static Property ShortName = new Property(3, String.class, "ShortName", false, "SHORT_NAME");
        public final static Property Specs = new Property(4, String.class, "Specs", false, "SPECS");
        public final static Property GDTypeID = new Property(5, Long.class, "GDTypeID", false, "GDTYPE_ID");
        public final static Property GDUserDef1 = new Property(6, String.class, "GDUserDef1", false, "GDUSER_DEF1");
        public final static Property GDUserDef2 = new Property(7, String.class, "GDUserDef2", false, "GDUSER_DEF2");
        public final static Property GDUserDef3 = new Property(8, String.class, "GDUserDef3", false, "GDUSER_DEF3");
        public final static Property GDUserDef4 = new Property(9, String.class, "GDUserDef4", false, "GDUSER_DEF4");
        public final static Property GDUserDef5 = new Property(10, String.class, "GDUserDef5", false, "GDUSER_DEF5");
        public final static Property GDUserDef6 = new Property(11, String.class, "GDUserDef6", false, "GDUSER_DEF6");
        public final static Property GDUserDef7 = new Property(12, String.class, "GDUserDef7", false, "GDUSER_DEF7");
        public final static Property GDUserDef8 = new Property(13, String.class, "GDUserDef8", false, "GDUSER_DEF8");
        public final static Property GDUserDef9 = new Property(14, String.class, "GDUserDef9", false, "GDUSER_DEF9");
        public final static Property GDUserDef10 = new Property(15, String.class, "GDUserDef10", false, "GDUSER_DEF10");
    };


    public GoodsDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GOODS' (" + //
                "'GOODS_ID' INTEGER," + // 0: GoodsID
                "'GOODS_CODE' TEXT," + // 1: GoodsCode
                "'GOODS_NAME' TEXT," + // 2: GoodsName
                "'SHORT_NAME' TEXT," + // 3: ShortName
                "'SPECS' TEXT," + // 4: Specs
                "'GDTYPE_ID' INTEGER," + // 5: GDTypeID
                "'GDUSER_DEF1' TEXT," + // 6: GDUserDef1
                "'GDUSER_DEF2' TEXT," + // 7: GDUserDef2
                "'GDUSER_DEF3' TEXT," + // 8: GDUserDef3
                "'GDUSER_DEF4' TEXT," + // 9: GDUserDef4
                "'GDUSER_DEF5' TEXT," + // 10: GDUserDef5
                "'GDUSER_DEF6' TEXT," + // 11: GDUserDef6
                "'GDUSER_DEF7' TEXT," + // 12: GDUserDef7
                "'GDUSER_DEF8' TEXT," + // 13: GDUserDef8
                "'GDUSER_DEF9' TEXT," + // 14: GDUserDef9
                "'GDUSER_DEF10' TEXT);"); // 15: GDUserDef10
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_GOODS_GDTYPE_ID ON GOODS" +
                " (GDTYPE_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GOODS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Goods entity) {
        stmt.clearBindings();
 
        Long GoodsID = entity.getGoodsID();
        if (GoodsID != null) {
            stmt.bindLong(1, GoodsID);
        }
 
        String GoodsCode = entity.getGoodsCode();
        if (GoodsCode != null) {
            stmt.bindString(2, GoodsCode);
        }
 
        String GoodsName = entity.getGoodsName();
        if (GoodsName != null) {
            stmt.bindString(3, GoodsName);
        }
 
        String ShortName = entity.getShortName();
        if (ShortName != null) {
            stmt.bindString(4, ShortName);
        }
 
        String Specs = entity.getSpecs();
        if (Specs != null) {
            stmt.bindString(5, Specs);
        }
 
        Long GDTypeID = entity.getGDTypeID();
        if (GDTypeID != null) {
            stmt.bindLong(6, GDTypeID);
        }
 
        String GDUserDef1 = entity.getGDUserDef1();
        if (GDUserDef1 != null) {
            stmt.bindString(7, GDUserDef1);
        }
 
        String GDUserDef2 = entity.getGDUserDef2();
        if (GDUserDef2 != null) {
            stmt.bindString(8, GDUserDef2);
        }
 
        String GDUserDef3 = entity.getGDUserDef3();
        if (GDUserDef3 != null) {
            stmt.bindString(9, GDUserDef3);
        }
 
        String GDUserDef4 = entity.getGDUserDef4();
        if (GDUserDef4 != null) {
            stmt.bindString(10, GDUserDef4);
        }
 
        String GDUserDef5 = entity.getGDUserDef5();
        if (GDUserDef5 != null) {
            stmt.bindString(11, GDUserDef5);
        }
 
        String GDUserDef6 = entity.getGDUserDef6();
        if (GDUserDef6 != null) {
            stmt.bindString(12, GDUserDef6);
        }
 
        String GDUserDef7 = entity.getGDUserDef7();
        if (GDUserDef7 != null) {
            stmt.bindString(13, GDUserDef7);
        }
 
        String GDUserDef8 = entity.getGDUserDef8();
        if (GDUserDef8 != null) {
            stmt.bindString(14, GDUserDef8);
        }
 
        String GDUserDef9 = entity.getGDUserDef9();
        if (GDUserDef9 != null) {
            stmt.bindString(15, GDUserDef9);
        }
 
        String GDUserDef10 = entity.getGDUserDef10();
        if (GDUserDef10 != null) {
            stmt.bindString(16, GDUserDef10);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Goods readEntity(Cursor cursor, int offset) {
        Goods entity = new Goods( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // GoodsID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // GoodsCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // GoodsName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ShortName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Specs
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // GDTypeID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // GDUserDef1
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // GDUserDef2
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // GDUserDef3
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // GDUserDef4
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // GDUserDef5
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // GDUserDef6
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // GDUserDef7
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // GDUserDef8
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // GDUserDef9
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // GDUserDef10
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Goods entity, int offset) {
        entity.setGoodsID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGoodsCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setGoodsName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setShortName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSpecs(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGDTypeID(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setGDUserDef1(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGDUserDef2(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setGDUserDef3(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setGDUserDef4(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setGDUserDef5(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setGDUserDef6(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setGDUserDef7(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setGDUserDef8(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setGDUserDef9(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setGDUserDef10(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Goods entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Goods entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
