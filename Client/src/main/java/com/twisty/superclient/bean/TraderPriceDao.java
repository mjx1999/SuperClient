package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.TraderPrice;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TRADER_PRICE.
*/
public class TraderPriceDao extends AbstractDao<TraderPrice, Void> {

    public static final String TABLENAME = "TRADER_PRICE";

    /**
     * Properties of entity TraderPrice.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", false, "ID");
        public final static Property TraderID = new Property(1, Long.class, "TraderID", false, "TRADER_ID");
        public final static Property GoodsID = new Property(2, Long.class, "GoodsID", false, "GOODS_ID");
        public final static Property UnitID = new Property(3, Long.class, "UnitID", false, "UNIT_ID");
        public final static Property ShopID = new Property(4, Long.class, "ShopID", false, "SHOP_ID");
        public final static Property Price = new Property(5, Double.class, "Price", false, "PRICE");
        public final static Property APrice = new Property(6, Double.class, "APrice", false, "APRICE");
    };


    public TraderPriceDao(DaoConfig config) {
        super(config);
    }
    
    public TraderPriceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TRADER_PRICE' (" + //
                "'ID' INTEGER," + // 0: ID
                "'TRADER_ID' INTEGER," + // 1: TraderID
                "'GOODS_ID' INTEGER," + // 2: GoodsID
                "'UNIT_ID' INTEGER," + // 3: UnitID
                "'SHOP_ID' INTEGER," + // 4: ShopID
                "'PRICE' REAL," + // 5: Price
                "'APRICE' REAL);"); // 6: APrice
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_PRICE_ID ON TRADER_PRICE" +
                " (ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_PRICE_TRADER_ID ON TRADER_PRICE" +
                " (TRADER_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_PRICE_GOODS_ID ON TRADER_PRICE" +
                " (GOODS_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_PRICE_UNIT_ID ON TRADER_PRICE" +
                " (UNIT_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_PRICE_SHOP_ID ON TRADER_PRICE" +
                " (SHOP_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TRADER_PRICE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TraderPrice entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long TraderID = entity.getTraderID();
        if (TraderID != null) {
            stmt.bindLong(2, TraderID);
        }
 
        Long GoodsID = entity.getGoodsID();
        if (GoodsID != null) {
            stmt.bindLong(3, GoodsID);
        }
 
        Long UnitID = entity.getUnitID();
        if (UnitID != null) {
            stmt.bindLong(4, UnitID);
        }
 
        Long ShopID = entity.getShopID();
        if (ShopID != null) {
            stmt.bindLong(5, ShopID);
        }
 
        Double Price = entity.getPrice();
        if (Price != null) {
            stmt.bindDouble(6, Price);
        }
 
        Double APrice = entity.getAPrice();
        if (APrice != null) {
            stmt.bindDouble(7, APrice);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public TraderPrice readEntity(Cursor cursor, int offset) {
        TraderPrice entity = new TraderPrice( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // TraderID
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // GoodsID
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // UnitID
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // ShopID
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // Price
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6) // APrice
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TraderPrice entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTraderID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setGoodsID(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setUnitID(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setShopID(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setPrice(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setAPrice(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(TraderPrice entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(TraderPrice entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
