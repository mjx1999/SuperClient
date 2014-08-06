package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.Trader;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TRADER.
*/
public class TraderDao extends AbstractDao<Trader, Void> {

    public static final String TABLENAME = "TRADER";

    /**
     * Properties of entity Trader.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property TraderID = new Property(0, Long.class, "TraderID", false, "TRADER_ID");
        public final static Property TraderCode = new Property(1, String.class, "TraderCode", false, "TRADER_CODE");
        public final static Property TraderName = new Property(2, String.class, "TraderName", false, "TRADER_NAME");
        public final static Property FullName = new Property(3, String.class, "FullName", false, "FULL_NAME");
        public final static Property IsClient = new Property(4, Boolean.class, "IsClient", false, "IS_CLIENT");
        public final static Property IsVendor = new Property(5, Boolean.class, "IsVendor", false, "IS_VENDOR");
        public final static Property TraderTypeID = new Property(6, Long.class, "TraderTypeID", false, "TRADER_TYPE_ID");
        public final static Property AreaID = new Property(7, Long.class, "AreaID", false, "AREA_ID");
        public final static Property Lev = new Property(8, String.class, "Lev", false, "LEV");
        public final static Property EmpID = new Property(9, Long.class, "EmpID", false, "EMP_ID");
        public final static Property DepartmentID = new Property(10, Long.class, "DepartmentID", false, "DEPARTMENT_ID");
        public final static Property Legalrep = new Property(11, Long.class, "Legalrep", false, "LEGALREP");
        public final static Property Contactor = new Property(12, Long.class, "Contactor", false, "CONTACTOR");
        public final static Property Phone = new Property(13, String.class, "Phone", false, "PHONE");
        public final static Property Tel1 = new Property(14, String.class, "Tel1", false, "TEL1");
        public final static Property Tel2 = new Property(15, String.class, "Tel2", false, "TEL2");
        public final static Property Fax = new Property(16, String.class, "Fax", false, "FAX");
        public final static Property Zip = new Property(17, String.class, "Zip", false, "ZIP");
        public final static Property Address = new Property(18, String.class, "Address", false, "ADDRESS");
        public final static Property ShipTo = new Property(19, String.class, "ShipTo", false, "SHIP_TO");
        public final static Property EMail = new Property(20, String.class, "EMail", false, "EMAIL");
        public final static Property Url = new Property(21, String.class, "Url", false, "URL");
        public final static Property Bank = new Property(22, String.class, "Bank", false, "BANK");
        public final static Property BankAccno = new Property(23, String.class, "BankAccno", false, "BANK_ACCNO");
        public final static Property TaxNo = new Property(24, String.class, "TaxNo", false, "TAX_NO");
        public final static Property CreditDay = new Property(25, String.class, "CreditDay", false, "CREDIT_DAY");
        public final static Property Credit = new Property(26, String.class, "Credit", false, "CREDIT");
        public final static Property Closed = new Property(27, Boolean.class, "Closed", false, "CLOSED");
        public final static Property AccTrader = new Property(28, Boolean.class, "AccTrader", false, "ACC_TRADER");
    };


    public TraderDao(DaoConfig config) {
        super(config);
    }
    
    public TraderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TRADER' (" + //
                "'TRADER_ID' INTEGER," + // 0: TraderID
                "'TRADER_CODE' TEXT," + // 1: TraderCode
                "'TRADER_NAME' TEXT," + // 2: TraderName
                "'FULL_NAME' TEXT," + // 3: FullName
                "'IS_CLIENT' INTEGER," + // 4: IsClient
                "'IS_VENDOR' INTEGER," + // 5: IsVendor
                "'TRADER_TYPE_ID' INTEGER," + // 6: TraderTypeID
                "'AREA_ID' INTEGER," + // 7: AreaID
                "'LEV' TEXT," + // 8: Lev
                "'EMP_ID' INTEGER," + // 9: EmpID
                "'DEPARTMENT_ID' INTEGER," + // 10: DepartmentID
                "'LEGALREP' INTEGER," + // 11: Legalrep
                "'CONTACTOR' INTEGER," + // 12: Contactor
                "'PHONE' TEXT," + // 13: Phone
                "'TEL1' TEXT," + // 14: Tel1
                "'TEL2' TEXT," + // 15: Tel2
                "'FAX' TEXT," + // 16: Fax
                "'ZIP' TEXT," + // 17: Zip
                "'ADDRESS' TEXT," + // 18: Address
                "'SHIP_TO' TEXT," + // 19: ShipTo
                "'EMAIL' TEXT," + // 20: EMail
                "'URL' TEXT," + // 21: Url
                "'BANK' TEXT," + // 22: Bank
                "'BANK_ACCNO' TEXT," + // 23: BankAccno
                "'TAX_NO' TEXT," + // 24: TaxNo
                "'CREDIT_DAY' TEXT," + // 25: CreditDay
                "'CREDIT' TEXT," + // 26: Credit
                "'CLOSED' INTEGER," + // 27: Closed
                "'ACC_TRADER' INTEGER);"); // 28: AccTrader
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_TRADER_ID ON TRADER" +
                " (TRADER_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_TRADER_CODE ON TRADER" +
                " (TRADER_CODE);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_AREA_ID ON TRADER" +
                " (AREA_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_EMP_ID ON TRADER" +
                " (EMP_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TRADER_DEPARTMENT_ID ON TRADER" +
                " (DEPARTMENT_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TRADER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Trader entity) {
        stmt.clearBindings();
 
        Long TraderID = entity.getTraderID();
        if (TraderID != null) {
            stmt.bindLong(1, TraderID);
        }
 
        String TraderCode = entity.getTraderCode();
        if (TraderCode != null) {
            stmt.bindString(2, TraderCode);
        }
 
        String TraderName = entity.getTraderName();
        if (TraderName != null) {
            stmt.bindString(3, TraderName);
        }
 
        String FullName = entity.getFullName();
        if (FullName != null) {
            stmt.bindString(4, FullName);
        }
 
        Boolean IsClient = entity.getIsClient();
        if (IsClient != null) {
            stmt.bindLong(5, IsClient ? 1l: 0l);
        }
 
        Boolean IsVendor = entity.getIsVendor();
        if (IsVendor != null) {
            stmt.bindLong(6, IsVendor ? 1l: 0l);
        }
 
        Long TraderTypeID = entity.getTraderTypeID();
        if (TraderTypeID != null) {
            stmt.bindLong(7, TraderTypeID);
        }
 
        Long AreaID = entity.getAreaID();
        if (AreaID != null) {
            stmt.bindLong(8, AreaID);
        }
 
        String Lev = entity.getLev();
        if (Lev != null) {
            stmt.bindString(9, Lev);
        }
 
        Long EmpID = entity.getEmpID();
        if (EmpID != null) {
            stmt.bindLong(10, EmpID);
        }
 
        Long DepartmentID = entity.getDepartmentID();
        if (DepartmentID != null) {
            stmt.bindLong(11, DepartmentID);
        }
 
        Long Legalrep = entity.getLegalrep();
        if (Legalrep != null) {
            stmt.bindLong(12, Legalrep);
        }
 
        Long Contactor = entity.getContactor();
        if (Contactor != null) {
            stmt.bindLong(13, Contactor);
        }
 
        String Phone = entity.getPhone();
        if (Phone != null) {
            stmt.bindString(14, Phone);
        }
 
        String Tel1 = entity.getTel1();
        if (Tel1 != null) {
            stmt.bindString(15, Tel1);
        }
 
        String Tel2 = entity.getTel2();
        if (Tel2 != null) {
            stmt.bindString(16, Tel2);
        }
 
        String Fax = entity.getFax();
        if (Fax != null) {
            stmt.bindString(17, Fax);
        }
 
        String Zip = entity.getZip();
        if (Zip != null) {
            stmt.bindString(18, Zip);
        }
 
        String Address = entity.getAddress();
        if (Address != null) {
            stmt.bindString(19, Address);
        }
 
        String ShipTo = entity.getShipTo();
        if (ShipTo != null) {
            stmt.bindString(20, ShipTo);
        }
 
        String EMail = entity.getEMail();
        if (EMail != null) {
            stmt.bindString(21, EMail);
        }
 
        String Url = entity.getUrl();
        if (Url != null) {
            stmt.bindString(22, Url);
        }
 
        String Bank = entity.getBank();
        if (Bank != null) {
            stmt.bindString(23, Bank);
        }
 
        String BankAccno = entity.getBankAccno();
        if (BankAccno != null) {
            stmt.bindString(24, BankAccno);
        }
 
        String TaxNo = entity.getTaxNo();
        if (TaxNo != null) {
            stmt.bindString(25, TaxNo);
        }
 
        String CreditDay = entity.getCreditDay();
        if (CreditDay != null) {
            stmt.bindString(26, CreditDay);
        }
 
        String Credit = entity.getCredit();
        if (Credit != null) {
            stmt.bindString(27, Credit);
        }
 
        Boolean Closed = entity.getClosed();
        if (Closed != null) {
            stmt.bindLong(28, Closed ? 1l: 0l);
        }
 
        Boolean AccTrader = entity.getAccTrader();
        if (AccTrader != null) {
            stmt.bindLong(29, AccTrader ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Trader readEntity(Cursor cursor, int offset) {
        Trader entity = new Trader( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // TraderID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // TraderCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // TraderName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FullName
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // IsClient
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // IsVendor
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // TraderTypeID
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // AreaID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // Lev
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // EmpID
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // DepartmentID
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11), // Legalrep
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12), // Contactor
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // Phone
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // Tel1
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // Tel2
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // Fax
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // Zip
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // Address
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // ShipTo
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // EMail
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // Url
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // Bank
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // BankAccno
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // TaxNo
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // CreditDay
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // Credit
            cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27) != 0, // Closed
            cursor.isNull(offset + 28) ? null : cursor.getShort(offset + 28) != 0 // AccTrader
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Trader entity, int offset) {
        entity.setTraderID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTraderCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTraderName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFullName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsClient(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setIsVendor(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setTraderTypeID(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setAreaID(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setLev(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setEmpID(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setDepartmentID(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setLegalrep(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.setContactor(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
        entity.setPhone(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setTel1(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setTel2(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setFax(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setZip(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setAddress(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setShipTo(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setEMail(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setUrl(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setBank(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setBankAccno(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setTaxNo(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setCreditDay(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setCredit(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setClosed(cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27) != 0);
        entity.setAccTrader(cursor.isNull(offset + 28) ? null : cursor.getShort(offset + 28) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Trader entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Trader entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
