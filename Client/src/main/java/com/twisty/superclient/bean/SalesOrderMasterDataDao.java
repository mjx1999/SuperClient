package com.twisty.superclient.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table SALES_ORDER_MASTER_DATA.
 */
public class SalesOrderMasterDataDao extends AbstractDao<SalesOrderMasterData, Long> {

    public static final String TABLENAME = "SALES_ORDER_MASTER_DATA";
    private DaoSession daoSession;
    ;

    public SalesOrderMasterDataDao(DaoConfig config) {
        super(config);
    }


    public SalesOrderMasterDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'SALES_ORDER_MASTER_DATA' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'BILL_ID' INTEGER," + // 1: BillID
                "'BILL_STATE' INTEGER," + // 2: BillState
                "'BILL_STATE_NAME' TEXT," + // 3: BillStateName
                "'AMOUNT' REAL," + // 4: Amount
                "'BILL_CODE' TEXT," + // 5: BillCode
                "'BILL_DATE' TEXT," + // 6: BillDate
                "'BILLTO' TEXT," + // 7: Billto
                "'CHECKOR_CODE' TEXT," + // 8: CheckorCode
                "'CHECKOR_ID' INTEGER," + // 9: CheckorID
                "'CHECKOR_NAME' TEXT," + // 10: CheckorName
                "'CLOSE_REASON' TEXT," + // 11: CloseReason
                "'CLOSED' INTEGER," + // 12: Closed
                "'CONTACT_FAX' TEXT," + // 13: ContactFax
                "'CONTACT_PHONE' TEXT," + // 14: ContactPhone
                "'CONTACTOR' TEXT," + // 15: Contactor
                "'CONTRACT_NO' TEXT," + // 16: ContractNo
                "'DEPARTMENT_CODE' TEXT," + // 17: DepartmentCode
                "'DEPARTMENT_NAME' TEXT," + // 18: DepartmentName
                "'DEPARTMENT_ID' INTEGER," + // 19: DepartmentID
                "'EMP_CODE' TEXT," + // 20: EmpCode
                "'EMP_ID' INTEGER," + // 21: EmpID
                "'EMP_NAME' TEXT," + // 22: EmpName
                "'LINK_MAN' TEXT," + // 23: LinkMan
                "'OP_CODE' TEXT," + // 24: OpCode
                "'OP_ID' INTEGER," + // 25: OpID
                "'OP_NAME' TEXT," + // 26: OpName
                "'PAYMETHOD_ID' INTEGER," + // 27: PaymethodID
                "'PAYMETHOD_NAME' TEXT," + // 28: PaymethodName
                "'REFER_AMT' REAL," + // 29: ReferAmt
                "'REMARK' TEXT," + // 30: Remark
                "'REV_DATE' TEXT," + // 31: RevDate
                "'SFLAG' INTEGER," + // 32: Sflag
                "'SHIP_TYPE' INTEGER," + // 33: ShipType
                "'SHIP_TYPE_NAME' TEXT," + // 34: ShipTypeName
                "'SHOPID' INTEGER," + // 35: Shopid
                "'TRADER_CODE' TEXT," + // 36: TraderCode
                "'TRADER_ID' INTEGER," + // 37: TraderId
                "'TRADER_NAME' TEXT," + // 38: TraderName
                "'UPDATE_TIME' TEXT," + // 39: UpdateTime
                "'USER_DEF1' TEXT," + // 40: UserDef1
                "'USER_DEF2' TEXT," + // 41: UserDef2
                "'USER_DEF3' TEXT," + // 42: UserDef3
                "'USER_DEF4' TEXT," + // 43: UserDef4
                "'USER_DEF5' TEXT," + // 44: UserDef5
                "'VALID_DATE' TEXT);"); // 45: ValidDate
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_SALES_ORDER_MASTER_DATA__id ON SALES_ORDER_MASTER_DATA" +
                " (_id);");
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SALES_ORDER_MASTER_DATA'";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, SalesOrderMasterData entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        Long BillID = entity.getBillID();
        if (BillID != null) {
            stmt.bindLong(2, BillID);
        }

        Integer BillState = entity.getBillState();
        if (BillState != null) {
            stmt.bindLong(3, BillState);
        }

        String BillStateName = entity.getBillStateName();
        if (BillStateName != null) {
            stmt.bindString(4, BillStateName);
        }

        Double Amount = entity.getAmount();
        if (Amount != null) {
            stmt.bindDouble(5, Amount);
        }

        String BillCode = entity.getBillCode();
        if (BillCode != null) {
            stmt.bindString(6, BillCode);
        }

        String BillDate = entity.getBillDate();
        if (BillDate != null) {
            stmt.bindString(7, BillDate);
        }

        String Billto = entity.getBillto();
        if (Billto != null) {
            stmt.bindString(8, Billto);
        }

        String CheckorCode = entity.getCheckorCode();
        if (CheckorCode != null) {
            stmt.bindString(9, CheckorCode);
        }

        Long CheckorID = entity.getCheckorID();
        if (CheckorID != null) {
            stmt.bindLong(10, CheckorID);
        }

        String CheckorName = entity.getCheckorName();
        if (CheckorName != null) {
            stmt.bindString(11, CheckorName);
        }

        String CloseReason = entity.getCloseReason();
        if (CloseReason != null) {
            stmt.bindString(12, CloseReason);
        }

        Integer Closed = entity.getClosed();
        if (Closed != null) {
            stmt.bindLong(13, Closed);
        }

        String ContactFax = entity.getContactFax();
        if (ContactFax != null) {
            stmt.bindString(14, ContactFax);
        }

        String ContactPhone = entity.getContactPhone();
        if (ContactPhone != null) {
            stmt.bindString(15, ContactPhone);
        }

        String Contactor = entity.getContactor();
        if (Contactor != null) {
            stmt.bindString(16, Contactor);
        }

        String ContractNo = entity.getContractNo();
        if (ContractNo != null) {
            stmt.bindString(17, ContractNo);
        }

        String DepartmentCode = entity.getDepartmentCode();
        if (DepartmentCode != null) {
            stmt.bindString(18, DepartmentCode);
        }

        String DepartmentName = entity.getDepartmentName();
        if (DepartmentName != null) {
            stmt.bindString(19, DepartmentName);
        }

        Long DepartmentID = entity.getDepartmentID();
        if (DepartmentID != null) {
            stmt.bindLong(20, DepartmentID);
        }

        String EmpCode = entity.getEmpCode();
        if (EmpCode != null) {
            stmt.bindString(21, EmpCode);
        }

        Long EmpID = entity.getEmpID();
        if (EmpID != null) {
            stmt.bindLong(22, EmpID);
        }

        String EmpName = entity.getEmpName();
        if (EmpName != null) {
            stmt.bindString(23, EmpName);
        }

        String LinkMan = entity.getLinkMan();
        if (LinkMan != null) {
            stmt.bindString(24, LinkMan);
        }

        String OpCode = entity.getOpCode();
        if (OpCode != null) {
            stmt.bindString(25, OpCode);
        }

        Long OpID = entity.getOpID();
        if (OpID != null) {
            stmt.bindLong(26, OpID);
        }

        String OpName = entity.getOpName();
        if (OpName != null) {
            stmt.bindString(27, OpName);
        }

        Long PaymethodID = entity.getPaymethodID();
        if (PaymethodID != null) {
            stmt.bindLong(28, PaymethodID);
        }

        String PaymethodName = entity.getPaymethodName();
        if (PaymethodName != null) {
            stmt.bindString(29, PaymethodName);
        }

        Double ReferAmt = entity.getReferAmt();
        if (ReferAmt != null) {
            stmt.bindDouble(30, ReferAmt);
        }

        String Remark = entity.getRemark();
        if (Remark != null) {
            stmt.bindString(31, Remark);
        }

        String RevDate = entity.getRevDate();
        if (RevDate != null) {
            stmt.bindString(32, RevDate);
        }

        Integer Sflag = entity.getSflag();
        if (Sflag != null) {
            stmt.bindLong(33, Sflag);
        }

        Long ShipType = entity.getShipType();
        if (ShipType != null) {
            stmt.bindLong(34, ShipType);
        }

        String ShipTypeName = entity.getShipTypeName();
        if (ShipTypeName != null) {
            stmt.bindString(35, ShipTypeName);
        }

        Long Shopid = entity.getShopid();
        if (Shopid != null) {
            stmt.bindLong(36, Shopid);
        }

        String TraderCode = entity.getTraderCode();
        if (TraderCode != null) {
            stmt.bindString(37, TraderCode);
        }

        Long TraderId = entity.getTraderId();
        if (TraderId != null) {
            stmt.bindLong(38, TraderId);
        }

        String TraderName = entity.getTraderName();
        if (TraderName != null) {
            stmt.bindString(39, TraderName);
        }

        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(40, UpdateTime);
        }

        String UserDef1 = entity.getUserDef1();
        if (UserDef1 != null) {
            stmt.bindString(41, UserDef1);
        }

        String UserDef2 = entity.getUserDef2();
        if (UserDef2 != null) {
            stmt.bindString(42, UserDef2);
        }

        String UserDef3 = entity.getUserDef3();
        if (UserDef3 != null) {
            stmt.bindString(43, UserDef3);
        }

        String UserDef4 = entity.getUserDef4();
        if (UserDef4 != null) {
            stmt.bindString(44, UserDef4);
        }

        String UserDef5 = entity.getUserDef5();
        if (UserDef5 != null) {
            stmt.bindString(45, UserDef5);
        }

        String ValidDate = entity.getValidDate();
        if (ValidDate != null) {
            stmt.bindString(46, ValidDate);
        }
    }

    @Override
    protected void attachEntity(SalesOrderMasterData entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public SalesOrderMasterData readEntity(Cursor cursor, int offset) {
        SalesOrderMasterData entity = new SalesOrderMasterData( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // BillID
                cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // BillState
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // BillStateName
                cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // Amount
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // BillCode
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // BillDate
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Billto
                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // CheckorCode
                cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // CheckorID
                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // CheckorName
                cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // CloseReason
                cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // Closed
                cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ContactFax
                cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ContactPhone
                cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // Contactor
                cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // ContractNo
                cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // DepartmentCode
                cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // DepartmentName
                cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19), // DepartmentID
                cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // EmpCode
                cursor.isNull(offset + 21) ? null : cursor.getLong(offset + 21), // EmpID
                cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // EmpName
                cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // LinkMan
                cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // OpCode
                cursor.isNull(offset + 25) ? null : cursor.getLong(offset + 25), // OpID
                cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // OpName
                cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27), // PaymethodID
                cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // PaymethodName
                cursor.isNull(offset + 29) ? null : cursor.getDouble(offset + 29), // ReferAmt
                cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // Remark
                cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // RevDate
                cursor.isNull(offset + 32) ? null : cursor.getInt(offset + 32), // Sflag
                cursor.isNull(offset + 33) ? null : cursor.getLong(offset + 33), // ShipType
                cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // ShipTypeName
                cursor.isNull(offset + 35) ? null : cursor.getLong(offset + 35), // Shopid
                cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // TraderCode
                cursor.isNull(offset + 37) ? null : cursor.getLong(offset + 37), // TraderId
                cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // TraderName
                cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // UpdateTime
                cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // UserDef1
                cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // UserDef2
                cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // UserDef3
                cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // UserDef4
                cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44), // UserDef5
                cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45) // ValidDate
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, SalesOrderMasterData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBillID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setBillState(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setBillStateName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAmount(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setBillCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBillDate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBillto(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCheckorCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCheckorID(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setCheckorName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCloseReason(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setClosed(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setContactFax(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setContactPhone(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setContactor(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setContractNo(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setDepartmentCode(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setDepartmentName(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setDepartmentID(cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19));
        entity.setEmpCode(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setEmpID(cursor.isNull(offset + 21) ? null : cursor.getLong(offset + 21));
        entity.setEmpName(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setLinkMan(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setOpCode(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setOpID(cursor.isNull(offset + 25) ? null : cursor.getLong(offset + 25));
        entity.setOpName(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setPaymethodID(cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27));
        entity.setPaymethodName(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setReferAmt(cursor.isNull(offset + 29) ? null : cursor.getDouble(offset + 29));
        entity.setRemark(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setRevDate(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setSflag(cursor.isNull(offset + 32) ? null : cursor.getInt(offset + 32));
        entity.setShipType(cursor.isNull(offset + 33) ? null : cursor.getLong(offset + 33));
        entity.setShipTypeName(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setShopid(cursor.isNull(offset + 35) ? null : cursor.getLong(offset + 35));
        entity.setTraderCode(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setTraderId(cursor.isNull(offset + 37) ? null : cursor.getLong(offset + 37));
        entity.setTraderName(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setUpdateTime(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setUserDef1(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setUserDef2(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setUserDef3(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setUserDef4(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setUserDef5(cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44));
        entity.setValidDate(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(SalesOrderMasterData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(SalesOrderMasterData entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Properties of entity SalesOrderMasterData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property BillID = new Property(1, Long.class, "BillID", false, "BILL_ID");
        public final static Property BillState = new Property(2, Integer.class, "BillState", false, "BILL_STATE");
        public final static Property BillStateName = new Property(3, String.class, "BillStateName", false, "BILL_STATE_NAME");
        public final static Property Amount = new Property(4, Double.class, "Amount", false, "AMOUNT");
        public final static Property BillCode = new Property(5, String.class, "BillCode", false, "BILL_CODE");
        public final static Property BillDate = new Property(6, String.class, "BillDate", false, "BILL_DATE");
        public final static Property Billto = new Property(7, String.class, "Billto", false, "BILLTO");
        public final static Property CheckorCode = new Property(8, String.class, "CheckorCode", false, "CHECKOR_CODE");
        public final static Property CheckorID = new Property(9, Long.class, "CheckorID", false, "CHECKOR_ID");
        public final static Property CheckorName = new Property(10, String.class, "CheckorName", false, "CHECKOR_NAME");
        public final static Property CloseReason = new Property(11, String.class, "CloseReason", false, "CLOSE_REASON");
        public final static Property Closed = new Property(12, Integer.class, "Closed", false, "CLOSED");
        public final static Property ContactFax = new Property(13, String.class, "ContactFax", false, "CONTACT_FAX");
        public final static Property ContactPhone = new Property(14, String.class, "ContactPhone", false, "CONTACT_PHONE");
        public final static Property Contactor = new Property(15, String.class, "Contactor", false, "CONTACTOR");
        public final static Property ContractNo = new Property(16, String.class, "ContractNo", false, "CONTRACT_NO");
        public final static Property DepartmentCode = new Property(17, String.class, "DepartmentCode", false, "DEPARTMENT_CODE");
        public final static Property DepartmentName = new Property(18, String.class, "DepartmentName", false, "DEPARTMENT_NAME");
        public final static Property DepartmentID = new Property(19, Long.class, "DepartmentID", false, "DEPARTMENT_ID");
        public final static Property EmpCode = new Property(20, String.class, "EmpCode", false, "EMP_CODE");
        public final static Property EmpID = new Property(21, Long.class, "EmpID", false, "EMP_ID");
        public final static Property EmpName = new Property(22, String.class, "EmpName", false, "EMP_NAME");
        public final static Property LinkMan = new Property(23, String.class, "LinkMan", false, "LINK_MAN");
        public final static Property OpCode = new Property(24, String.class, "OpCode", false, "OP_CODE");
        public final static Property OpID = new Property(25, Long.class, "OpID", false, "OP_ID");
        public final static Property OpName = new Property(26, String.class, "OpName", false, "OP_NAME");
        public final static Property PaymethodID = new Property(27, Long.class, "PaymethodID", false, "PAYMETHOD_ID");
        public final static Property PaymethodName = new Property(28, String.class, "PaymethodName", false, "PAYMETHOD_NAME");
        public final static Property ReferAmt = new Property(29, Double.class, "ReferAmt", false, "REFER_AMT");
        public final static Property Remark = new Property(30, String.class, "Remark", false, "REMARK");
        public final static Property RevDate = new Property(31, String.class, "RevDate", false, "REV_DATE");
        public final static Property Sflag = new Property(32, Integer.class, "Sflag", false, "SFLAG");
        public final static Property ShipType = new Property(33, Long.class, "ShipType", false, "SHIP_TYPE");
        public final static Property ShipTypeName = new Property(34, String.class, "ShipTypeName", false, "SHIP_TYPE_NAME");
        public final static Property Shopid = new Property(35, Long.class, "Shopid", false, "SHOPID");
        public final static Property TraderCode = new Property(36, String.class, "TraderCode", false, "TRADER_CODE");
        public final static Property TraderId = new Property(37, Long.class, "TraderId", false, "TRADER_ID");
        public final static Property TraderName = new Property(38, String.class, "TraderName", false, "TRADER_NAME");
        public final static Property UpdateTime = new Property(39, String.class, "UpdateTime", false, "UPDATE_TIME");
        public final static Property UserDef1 = new Property(40, String.class, "UserDef1", false, "USER_DEF1");
        public final static Property UserDef2 = new Property(41, String.class, "UserDef2", false, "USER_DEF2");
        public final static Property UserDef3 = new Property(42, String.class, "UserDef3", false, "USER_DEF3");
        public final static Property UserDef4 = new Property(43, String.class, "UserDef4", false, "USER_DEF4");
        public final static Property UserDef5 = new Property(44, String.class, "UserDef5", false, "USER_DEF5");
        public final static Property ValidDate = new Property(45, String.class, "ValidDate", false, "VALID_DATE");
    }

}
