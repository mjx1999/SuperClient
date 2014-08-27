package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.StockCheckDetail1Data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table STOCK_CHECK_DETAIL1_DATA.
*/
public class StockCheckDetail1DataDao extends AbstractDao<StockCheckDetail1Data, Void> {

    public static final String TABLENAME = "STOCK_CHECK_DETAIL1_DATA";

    /**
     * Properties of entity StockCheckDetail1Data.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property BillID = new Property(0, Long.class, "BillID", false, "BILL_ID");
        public final static Property GoodsID = new Property(1, Long.class, "GoodsID", false, "GOODS_ID");
        public final static Property UnitID = new Property(2, Long.class, "UnitID", false, "UNIT_ID");
        public final static Property ItemNo = new Property(3, Integer.class, "ItemNo", false, "ITEM_NO");
        public final static Property ReferCount = new Property(4, Integer.class, "ReferCount", false, "REFER_COUNT");
        public final static Property APrice = new Property(5, Double.class, "APrice", false, "APRICE");
        public final static Property UnitPrice = new Property(6, Double.class, "UnitPrice", false, "UNIT_PRICE");
        public final static Property UnitRate = new Property(7, Double.class, "UnitRate", false, "UNIT_RATE");
        public final static Property UnitQuantity = new Property(8, Double.class, "UnitQuantity", false, "UNIT_QUANTITY");
        public final static Property UnitRealQty = new Property(9, Double.class, "UnitRealQty", false, "UNIT_REAL_QTY");
        public final static Property AccQty = new Property(10, Double.class, "AccQty", false, "ACC_QTY");
        public final static Property Amount = new Property(11, Double.class, "Amount", false, "AMOUNT");
        public final static Property IOQty = new Property(12, Double.class, "IOQty", false, "IOQTY");
        public final static Property Price = new Property(13, Double.class, "Price", false, "PRICE");
        public final static Property Quantity = new Property(14, Double.class, "Quantity", false, "QUANTITY");
        public final static Property GoodsCode = new Property(15, String.class, "GoodsCode", false, "GOODS_CODE");
        public final static Property GoodsName = new Property(16, String.class, "GoodsName", false, "GOODS_NAME");
        public final static Property Remark = new Property(17, String.class, "Remark", false, "REMARK");
        public final static Property Specs = new Property(18, String.class, "Specs", false, "SPECS");
        public final static Property UnitName = new Property(19, String.class, "UnitName", false, "UNIT_NAME");
        public final static Property UserDef1 = new Property(20, String.class, "UserDef1", false, "USER_DEF1");
        public final static Property UserDef2 = new Property(21, String.class, "UserDef2", false, "USER_DEF2");
        public final static Property UserDef3 = new Property(22, String.class, "UserDef3", false, "USER_DEF3");
        public final static Property UserDef4 = new Property(23, String.class, "UserDef4", false, "USER_DEF4");
        public final static Property UserDef5 = new Property(24, String.class, "UserDef5", false, "USER_DEF5");
    };


    public StockCheckDetail1DataDao(DaoConfig config) {
        super(config);
    }
    
    public StockCheckDetail1DataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'STOCK_CHECK_DETAIL1_DATA' (" + //
                "'BILL_ID' INTEGER," + // 0: BillID
                "'GOODS_ID' INTEGER," + // 1: GoodsID
                "'UNIT_ID' INTEGER," + // 2: UnitID
                "'ITEM_NO' INTEGER," + // 3: ItemNo
                "'REFER_COUNT' INTEGER," + // 4: ReferCount
                "'APRICE' REAL," + // 5: APrice
                "'UNIT_PRICE' REAL," + // 6: UnitPrice
                "'UNIT_RATE' REAL," + // 7: UnitRate
                "'UNIT_QUANTITY' REAL," + // 8: UnitQuantity
                "'UNIT_REAL_QTY' REAL," + // 9: UnitRealQty
                "'ACC_QTY' REAL," + // 10: AccQty
                "'AMOUNT' REAL," + // 11: Amount
                "'IOQTY' REAL," + // 12: IOQty
                "'PRICE' REAL," + // 13: Price
                "'QUANTITY' REAL," + // 14: Quantity
                "'GOODS_CODE' TEXT," + // 15: GoodsCode
                "'GOODS_NAME' TEXT," + // 16: GoodsName
                "'REMARK' TEXT," + // 17: Remark
                "'SPECS' TEXT," + // 18: Specs
                "'UNIT_NAME' TEXT," + // 19: UnitName
                "'USER_DEF1' TEXT," + // 20: UserDef1
                "'USER_DEF2' TEXT," + // 21: UserDef2
                "'USER_DEF3' TEXT," + // 22: UserDef3
                "'USER_DEF4' TEXT," + // 23: UserDef4
                "'USER_DEF5' TEXT);"); // 24: UserDef5
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_STOCK_CHECK_DETAIL1_DATA_BILL_ID ON STOCK_CHECK_DETAIL1_DATA" +
                " (BILL_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'STOCK_CHECK_DETAIL1_DATA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, StockCheckDetail1Data entity) {
        stmt.clearBindings();
 
        Long BillID = entity.getBillID();
        if (BillID != null) {
            stmt.bindLong(1, BillID);
        }
 
        Long GoodsID = entity.getGoodsID();
        if (GoodsID != null) {
            stmt.bindLong(2, GoodsID);
        }
 
        Long UnitID = entity.getUnitID();
        if (UnitID != null) {
            stmt.bindLong(3, UnitID);
        }
 
        Integer ItemNo = entity.getItemNo();
        if (ItemNo != null) {
            stmt.bindLong(4, ItemNo);
        }
 
        Integer ReferCount = entity.getReferCount();
        if (ReferCount != null) {
            stmt.bindLong(5, ReferCount);
        }
 
        Double APrice = entity.getAPrice();
        if (APrice != null) {
            stmt.bindDouble(6, APrice);
        }
 
        Double UnitPrice = entity.getUnitPrice();
        if (UnitPrice != null) {
            stmt.bindDouble(7, UnitPrice);
        }
 
        Double UnitRate = entity.getUnitRate();
        if (UnitRate != null) {
            stmt.bindDouble(8, UnitRate);
        }
 
        Double UnitQuantity = entity.getUnitQuantity();
        if (UnitQuantity != null) {
            stmt.bindDouble(9, UnitQuantity);
        }
 
        Double UnitRealQty = entity.getUnitRealQty();
        if (UnitRealQty != null) {
            stmt.bindDouble(10, UnitRealQty);
        }
 
        Double AccQty = entity.getAccQty();
        if (AccQty != null) {
            stmt.bindDouble(11, AccQty);
        }
 
        Double Amount = entity.getAmount();
        if (Amount != null) {
            stmt.bindDouble(12, Amount);
        }
 
        Double IOQty = entity.getIOQty();
        if (IOQty != null) {
            stmt.bindDouble(13, IOQty);
        }
 
        Double Price = entity.getPrice();
        if (Price != null) {
            stmt.bindDouble(14, Price);
        }
 
        Double Quantity = entity.getQuantity();
        if (Quantity != null) {
            stmt.bindDouble(15, Quantity);
        }
 
        String GoodsCode = entity.getGoodsCode();
        if (GoodsCode != null) {
            stmt.bindString(16, GoodsCode);
        }
 
        String GoodsName = entity.getGoodsName();
        if (GoodsName != null) {
            stmt.bindString(17, GoodsName);
        }
 
        String Remark = entity.getRemark();
        if (Remark != null) {
            stmt.bindString(18, Remark);
        }
 
        String Specs = entity.getSpecs();
        if (Specs != null) {
            stmt.bindString(19, Specs);
        }
 
        String UnitName = entity.getUnitName();
        if (UnitName != null) {
            stmt.bindString(20, UnitName);
        }
 
        String UserDef1 = entity.getUserDef1();
        if (UserDef1 != null) {
            stmt.bindString(21, UserDef1);
        }
 
        String UserDef2 = entity.getUserDef2();
        if (UserDef2 != null) {
            stmt.bindString(22, UserDef2);
        }
 
        String UserDef3 = entity.getUserDef3();
        if (UserDef3 != null) {
            stmt.bindString(23, UserDef3);
        }
 
        String UserDef4 = entity.getUserDef4();
        if (UserDef4 != null) {
            stmt.bindString(24, UserDef4);
        }
 
        String UserDef5 = entity.getUserDef5();
        if (UserDef5 != null) {
            stmt.bindString(25, UserDef5);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public StockCheckDetail1Data readEntity(Cursor cursor, int offset) {
        StockCheckDetail1Data entity = new StockCheckDetail1Data( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // BillID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // GoodsID
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // UnitID
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // ItemNo
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // ReferCount
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // APrice
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // UnitPrice
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // UnitRate
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // UnitQuantity
            cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9), // UnitRealQty
            cursor.isNull(offset + 10) ? null : cursor.getDouble(offset + 10), // AccQty
            cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11), // Amount
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12), // IOQty
            cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13), // Price
            cursor.isNull(offset + 14) ? null : cursor.getDouble(offset + 14), // Quantity
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // GoodsCode
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // GoodsName
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // Remark
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // Specs
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // UnitName
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // UserDef1
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // UserDef2
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // UserDef3
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // UserDef4
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24) // UserDef5
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, StockCheckDetail1Data entity, int offset) {
        entity.setBillID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGoodsID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setUnitID(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setItemNo(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setReferCount(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setAPrice(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setUnitPrice(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setUnitRate(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setUnitQuantity(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setUnitRealQty(cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9));
        entity.setAccQty(cursor.isNull(offset + 10) ? null : cursor.getDouble(offset + 10));
        entity.setAmount(cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11));
        entity.setIOQty(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
        entity.setPrice(cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13));
        entity.setQuantity(cursor.isNull(offset + 14) ? null : cursor.getDouble(offset + 14));
        entity.setGoodsCode(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setGoodsName(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setRemark(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setSpecs(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setUnitName(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setUserDef1(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setUserDef2(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setUserDef3(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setUserDef4(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setUserDef5(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(StockCheckDetail1Data entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(StockCheckDetail1Data entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}