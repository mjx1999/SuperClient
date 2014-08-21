package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.Detail1Data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DETAIL1_DATA.
*/
public class Detail1DataDao extends AbstractDao<Detail1Data, Void> {

    public static final String TABLENAME = "DETAIL1_DATA";

    /**
     * Properties of entity Detail1Data.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property BillID = new Property(0, Long.class, "BillID", false, "BILL_ID");
        public final static Property ItemNO = new Property(1, Integer.class, "ItemNO", false, "ITEM_NO");
        public final static Property APrice = new Property(2, Double.class, "APrice", false, "APRICE");
        public final static Property Amount = new Property(3, Double.class, "Amount", false, "AMOUNT");
        public final static Property BReferID = new Property(4, Long.class, "BReferID", false, "BREFER_ID");
        public final static Property Disc = new Property(5, Double.class, "Disc", false, "DISC");
        public final static Property DiscAmt = new Property(6, Double.class, "DiscAmt", false, "DISC_AMT");
        public final static Property GoodsID = new Property(7, Long.class, "GoodsID", false, "GOODS_ID");
        public final static Property GoodsAmt = new Property(8, Double.class, "GoodsAmt", false, "GOODS_AMT");
        public final static Property IsLargess = new Property(9, Integer.class, "IsLargess", false, "IS_LARGESS");
        public final static Property OrigPrice = new Property(10, Double.class, "OrigPrice", false, "ORIG_PRICE");
        public final static Property OrigTaxPrice = new Property(11, Double.class, "OrigTaxPrice", false, "ORIG_TAX_PRICE");
        public final static Property Quantity = new Property(12, Double.class, "Quantity", false, "QUANTITY");
        public final static Property ReferBillCode = new Property(13, String.class, "ReferBillCode", false, "REFER_BILL_CODE");
        public final static Property ReferBillID = new Property(14, Long.class, "ReferBillID", false, "REFER_BILL_ID");
        public final static Property ReferBillType = new Property(15, Integer.class, "ReferBillType", false, "REFER_BILL_TYPE");
        public final static Property ReferItemNo = new Property(16, Integer.class, "ReferItemNo", false, "REFER_ITEM_NO");
        public final static Property StoreID = new Property(17, Long.class, "StoreID", false, "STORE_ID");
        public final static Property TaxAmt = new Property(18, Double.class, "TaxAmt", false, "TAX_AMT");
        public final static Property TaxPrice = new Property(19, Double.class, "TaxPrice", false, "TAX_PRICE");
        public final static Property TaxRate = new Property(20, Double.class, "TaxRate", false, "TAX_RATE");
        public final static Property UnitID = new Property(21, Long.class, "UnitID", false, "UNIT_ID");
        public final static Property UnitPrice = new Property(22, Double.class, "UnitPrice", false, "UNIT_PRICE");
        public final static Property UnitQuantity = new Property(23, Double.class, "UnitQuantity", false, "UNIT_QUANTITY");
        public final static Property UnitRate = new Property(24, Double.class, "UnitRate", false, "UNIT_RATE");
        public final static Property UserDef1 = new Property(25, String.class, "UserDef1", false, "USER_DEF1");
        public final static Property UserDef2 = new Property(26, String.class, "UserDef2", false, "USER_DEF2");
        public final static Property UserDef3 = new Property(27, String.class, "UserDef3", false, "USER_DEF3");
        public final static Property UserDef4 = new Property(28, String.class, "UserDef4", false, "USER_DEF4");
        public final static Property UserDef5 = new Property(29, String.class, "UserDef5", false, "USER_DEF5");
        public final static Property UserDef6 = new Property(30, String.class, "UserDef6", false, "USER_DEF6");
        public final static Property UserDef7 = new Property(31, String.class, "UserDef7", false, "USER_DEF7");
        public final static Property UserDef8 = new Property(32, String.class, "UserDef8", false, "USER_DEF8");
        public final static Property UserDef9 = new Property(33, String.class, "UserDef9", false, "USER_DEF9");
        public final static Property UserDef10 = new Property(34, String.class, "UserDef10", false, "USER_DEF10");
    };


    public Detail1DataDao(DaoConfig config) {
        super(config);
    }
    
    public Detail1DataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DETAIL1_DATA' (" + //
                "'BILL_ID' INTEGER," + // 0: BillID
                "'ITEM_NO' INTEGER," + // 1: ItemNO
                "'APRICE' REAL," + // 2: APrice
                "'AMOUNT' REAL," + // 3: Amount
                "'BREFER_ID' INTEGER," + // 4: BReferID
                "'DISC' REAL," + // 5: Disc
                "'DISC_AMT' REAL," + // 6: DiscAmt
                "'GOODS_ID' INTEGER," + // 7: GoodsID
                "'GOODS_AMT' REAL," + // 8: GoodsAmt
                "'IS_LARGESS' INTEGER," + // 9: IsLargess
                "'ORIG_PRICE' REAL," + // 10: OrigPrice
                "'ORIG_TAX_PRICE' REAL," + // 11: OrigTaxPrice
                "'QUANTITY' REAL," + // 12: Quantity
                "'REFER_BILL_CODE' TEXT," + // 13: ReferBillCode
                "'REFER_BILL_ID' INTEGER," + // 14: ReferBillID
                "'REFER_BILL_TYPE' INTEGER," + // 15: ReferBillType
                "'REFER_ITEM_NO' INTEGER," + // 16: ReferItemNo
                "'STORE_ID' INTEGER," + // 17: StoreID
                "'TAX_AMT' REAL," + // 18: TaxAmt
                "'TAX_PRICE' REAL," + // 19: TaxPrice
                "'TAX_RATE' REAL," + // 20: TaxRate
                "'UNIT_ID' INTEGER," + // 21: UnitID
                "'UNIT_PRICE' REAL," + // 22: UnitPrice
                "'UNIT_QUANTITY' REAL," + // 23: UnitQuantity
                "'UNIT_RATE' REAL," + // 24: UnitRate
                "'USER_DEF1' TEXT," + // 25: UserDef1
                "'USER_DEF2' TEXT," + // 26: UserDef2
                "'USER_DEF3' TEXT," + // 27: UserDef3
                "'USER_DEF4' TEXT," + // 28: UserDef4
                "'USER_DEF5' TEXT," + // 29: UserDef5
                "'USER_DEF6' TEXT," + // 30: UserDef6
                "'USER_DEF7' TEXT," + // 31: UserDef7
                "'USER_DEF8' TEXT," + // 32: UserDef8
                "'USER_DEF9' TEXT," + // 33: UserDef9
                "'USER_DEF10' TEXT);"); // 34: UserDef10
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_DETAIL1_DATA_BILL_ID ON DETAIL1_DATA" +
                " (BILL_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DETAIL1_DATA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Detail1Data entity) {
        stmt.clearBindings();
 
        Long BillID = entity.getBillID();
        if (BillID != null) {
            stmt.bindLong(1, BillID);
        }
 
        Integer ItemNO = entity.getItemNO();
        if (ItemNO != null) {
            stmt.bindLong(2, ItemNO);
        }
 
        Double APrice = entity.getAPrice();
        if (APrice != null) {
            stmt.bindDouble(3, APrice);
        }
 
        Double Amount = entity.getAmount();
        if (Amount != null) {
            stmt.bindDouble(4, Amount);
        }
 
        Long BReferID = entity.getBReferID();
        if (BReferID != null) {
            stmt.bindLong(5, BReferID);
        }
 
        Double Disc = entity.getDisc();
        if (Disc != null) {
            stmt.bindDouble(6, Disc);
        }
 
        Double DiscAmt = entity.getDiscAmt();
        if (DiscAmt != null) {
            stmt.bindDouble(7, DiscAmt);
        }
 
        Long GoodsID = entity.getGoodsID();
        if (GoodsID != null) {
            stmt.bindLong(8, GoodsID);
        }
 
        Double GoodsAmt = entity.getGoodsAmt();
        if (GoodsAmt != null) {
            stmt.bindDouble(9, GoodsAmt);
        }
 
        Integer IsLargess = entity.getIsLargess();
        if (IsLargess != null) {
            stmt.bindLong(10, IsLargess);
        }
 
        Double OrigPrice = entity.getOrigPrice();
        if (OrigPrice != null) {
            stmt.bindDouble(11, OrigPrice);
        }
 
        Double OrigTaxPrice = entity.getOrigTaxPrice();
        if (OrigTaxPrice != null) {
            stmt.bindDouble(12, OrigTaxPrice);
        }
 
        Double Quantity = entity.getQuantity();
        if (Quantity != null) {
            stmt.bindDouble(13, Quantity);
        }
 
        String ReferBillCode = entity.getReferBillCode();
        if (ReferBillCode != null) {
            stmt.bindString(14, ReferBillCode);
        }
 
        Long ReferBillID = entity.getReferBillID();
        if (ReferBillID != null) {
            stmt.bindLong(15, ReferBillID);
        }
 
        Integer ReferBillType = entity.getReferBillType();
        if (ReferBillType != null) {
            stmt.bindLong(16, ReferBillType);
        }
 
        Integer ReferItemNo = entity.getReferItemNo();
        if (ReferItemNo != null) {
            stmt.bindLong(17, ReferItemNo);
        }
 
        Long StoreID = entity.getStoreID();
        if (StoreID != null) {
            stmt.bindLong(18, StoreID);
        }
 
        Double TaxAmt = entity.getTaxAmt();
        if (TaxAmt != null) {
            stmt.bindDouble(19, TaxAmt);
        }
 
        Double TaxPrice = entity.getTaxPrice();
        if (TaxPrice != null) {
            stmt.bindDouble(20, TaxPrice);
        }
 
        Double TaxRate = entity.getTaxRate();
        if (TaxRate != null) {
            stmt.bindDouble(21, TaxRate);
        }
 
        Long UnitID = entity.getUnitID();
        if (UnitID != null) {
            stmt.bindLong(22, UnitID);
        }
 
        Double UnitPrice = entity.getUnitPrice();
        if (UnitPrice != null) {
            stmt.bindDouble(23, UnitPrice);
        }
 
        Double UnitQuantity = entity.getUnitQuantity();
        if (UnitQuantity != null) {
            stmt.bindDouble(24, UnitQuantity);
        }
 
        Double UnitRate = entity.getUnitRate();
        if (UnitRate != null) {
            stmt.bindDouble(25, UnitRate);
        }
 
        String UserDef1 = entity.getUserDef1();
        if (UserDef1 != null) {
            stmt.bindString(26, UserDef1);
        }
 
        String UserDef2 = entity.getUserDef2();
        if (UserDef2 != null) {
            stmt.bindString(27, UserDef2);
        }
 
        String UserDef3 = entity.getUserDef3();
        if (UserDef3 != null) {
            stmt.bindString(28, UserDef3);
        }
 
        String UserDef4 = entity.getUserDef4();
        if (UserDef4 != null) {
            stmt.bindString(29, UserDef4);
        }
 
        String UserDef5 = entity.getUserDef5();
        if (UserDef5 != null) {
            stmt.bindString(30, UserDef5);
        }
 
        String UserDef6 = entity.getUserDef6();
        if (UserDef6 != null) {
            stmt.bindString(31, UserDef6);
        }
 
        String UserDef7 = entity.getUserDef7();
        if (UserDef7 != null) {
            stmt.bindString(32, UserDef7);
        }
 
        String UserDef8 = entity.getUserDef8();
        if (UserDef8 != null) {
            stmt.bindString(33, UserDef8);
        }
 
        String UserDef9 = entity.getUserDef9();
        if (UserDef9 != null) {
            stmt.bindString(34, UserDef9);
        }
 
        String UserDef10 = entity.getUserDef10();
        if (UserDef10 != null) {
            stmt.bindString(35, UserDef10);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Detail1Data readEntity(Cursor cursor, int offset) {
        Detail1Data entity = new Detail1Data( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // BillID
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // ItemNO
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // APrice
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // Amount
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // BReferID
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // Disc
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // DiscAmt
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // GoodsID
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // GoodsAmt
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // IsLargess
            cursor.isNull(offset + 10) ? null : cursor.getDouble(offset + 10), // OrigPrice
            cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11), // OrigTaxPrice
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12), // Quantity
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ReferBillCode
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14), // ReferBillID
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // ReferBillType
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // ReferItemNo
            cursor.isNull(offset + 17) ? null : cursor.getLong(offset + 17), // StoreID
            cursor.isNull(offset + 18) ? null : cursor.getDouble(offset + 18), // TaxAmt
            cursor.isNull(offset + 19) ? null : cursor.getDouble(offset + 19), // TaxPrice
            cursor.isNull(offset + 20) ? null : cursor.getDouble(offset + 20), // TaxRate
            cursor.isNull(offset + 21) ? null : cursor.getLong(offset + 21), // UnitID
            cursor.isNull(offset + 22) ? null : cursor.getDouble(offset + 22), // UnitPrice
            cursor.isNull(offset + 23) ? null : cursor.getDouble(offset + 23), // UnitQuantity
            cursor.isNull(offset + 24) ? null : cursor.getDouble(offset + 24), // UnitRate
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // UserDef1
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // UserDef2
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // UserDef3
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // UserDef4
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // UserDef5
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // UserDef6
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // UserDef7
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // UserDef8
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // UserDef9
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34) // UserDef10
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Detail1Data entity, int offset) {
        entity.setBillID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setItemNO(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setAPrice(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setAmount(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setBReferID(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setDisc(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setDiscAmt(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setGoodsID(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setGoodsAmt(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setIsLargess(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setOrigPrice(cursor.isNull(offset + 10) ? null : cursor.getDouble(offset + 10));
        entity.setOrigTaxPrice(cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11));
        entity.setQuantity(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
        entity.setReferBillCode(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setReferBillID(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
        entity.setReferBillType(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setReferItemNo(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setStoreID(cursor.isNull(offset + 17) ? null : cursor.getLong(offset + 17));
        entity.setTaxAmt(cursor.isNull(offset + 18) ? null : cursor.getDouble(offset + 18));
        entity.setTaxPrice(cursor.isNull(offset + 19) ? null : cursor.getDouble(offset + 19));
        entity.setTaxRate(cursor.isNull(offset + 20) ? null : cursor.getDouble(offset + 20));
        entity.setUnitID(cursor.isNull(offset + 21) ? null : cursor.getLong(offset + 21));
        entity.setUnitPrice(cursor.isNull(offset + 22) ? null : cursor.getDouble(offset + 22));
        entity.setUnitQuantity(cursor.isNull(offset + 23) ? null : cursor.getDouble(offset + 23));
        entity.setUnitRate(cursor.isNull(offset + 24) ? null : cursor.getDouble(offset + 24));
        entity.setUserDef1(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setUserDef2(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setUserDef3(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setUserDef4(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setUserDef5(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setUserDef6(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setUserDef7(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setUserDef8(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setUserDef9(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setUserDef10(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Detail1Data entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Detail1Data entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
