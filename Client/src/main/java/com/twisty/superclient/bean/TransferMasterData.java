package com.twisty.superclient.bean;

import java.util.List;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table TRANSFER_MASTER_DATA.
 */
public class TransferMasterData implements java.io.Serializable {

    private Long id;
    private Long BillID;
    private Integer BillKind;
    private Integer BillState;
    private String BillCode;
    private String BillKindName;
    private String BillStateName;
    private String BillDate;
    private String CheckorCode;
    private String CheckorName;
    private String DepartmentCode;
    private String DepartmentName;
    private String EmpCode;
    private String EmpName;
    private String InStoreCode;
    private String StoreCode;
    private String InStoreName;
    private String StoreName;
    private String UpdateTime;
    private String BillTo;
    private String OpCode;
    private String OpName;
    private Double Amount;
    private Long CheckorID;
    private Long DepartmentID;
    private Long EmpID;
    private Long InStoreID;
    private Long OpID;
    private Long ShipType;
    private Long ShopID;
    private Long StoreID;
    private Integer ReferCount;
    private String Remark;
    private String ShipTypeName;
    private String SFlag;
    private String UserDef1;
    private String UserDef2;
    private String UserDef3;
    private String UserDef4;
    private String UserDef5;

    /**
     * Used to resolve relations
     */
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    private transient TransferMasterDataDao myDao;

    private List<TransferDetail1Data> transferDetail1DataList;

    public TransferMasterData() {
    }

    public TransferMasterData(Long id) {
        this.id = id;
    }

    public TransferMasterData(Long id, Long BillID, Integer BillKind, Integer BillState, String BillCode, String BillKindName, String BillStateName, String BillDate, String CheckorCode, String CheckorName, String DepartmentCode, String DepartmentName, String EmpCode, String EmpName, String InStoreCode, String StoreCode, String InStoreName, String StoreName, String UpdateTime, String BillTo, String OpCode, String OpName, Double Amount, Long CheckorID, Long DepartmentID, Long EmpID, Long InStoreID, Long OpID, Long ShipType, Long ShopID, Long StoreID, Integer ReferCount, String Remark, String ShipTypeName, String SFlag, String UserDef1, String UserDef2, String UserDef3, String UserDef4, String UserDef5) {
        this.id = id;
        this.BillID = BillID;
        this.BillKind = BillKind;
        this.BillState = BillState;
        this.BillCode = BillCode;
        this.BillKindName = BillKindName;
        this.BillStateName = BillStateName;
        this.BillDate = BillDate;
        this.CheckorCode = CheckorCode;
        this.CheckorName = CheckorName;
        this.DepartmentCode = DepartmentCode;
        this.DepartmentName = DepartmentName;
        this.EmpCode = EmpCode;
        this.EmpName = EmpName;
        this.InStoreCode = InStoreCode;
        this.StoreCode = StoreCode;
        this.InStoreName = InStoreName;
        this.StoreName = StoreName;
        this.UpdateTime = UpdateTime;
        this.BillTo = BillTo;
        this.OpCode = OpCode;
        this.OpName = OpName;
        this.Amount = Amount;
        this.CheckorID = CheckorID;
        this.DepartmentID = DepartmentID;
        this.EmpID = EmpID;
        this.InStoreID = InStoreID;
        this.OpID = OpID;
        this.ShipType = ShipType;
        this.ShopID = ShopID;
        this.StoreID = StoreID;
        this.ReferCount = ReferCount;
        this.Remark = Remark;
        this.ShipTypeName = ShipTypeName;
        this.SFlag = SFlag;
        this.UserDef1 = UserDef1;
        this.UserDef2 = UserDef2;
        this.UserDef3 = UserDef3;
        this.UserDef4 = UserDef4;
        this.UserDef5 = UserDef5;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTransferMasterDataDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillID() {
        return BillID;
    }

    public void setBillID(Long BillID) {
        this.BillID = BillID;
    }

    public Integer getBillKind() {
        return BillKind;
    }

    public void setBillKind(Integer BillKind) {
        this.BillKind = BillKind;
    }

    public Integer getBillState() {
        return BillState;
    }

    public void setBillState(Integer BillState) {
        this.BillState = BillState;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String BillCode) {
        this.BillCode = BillCode;
    }

    public String getBillKindName() {
        return BillKindName;
    }

    public void setBillKindName(String BillKindName) {
        this.BillKindName = BillKindName;
    }

    public String getBillStateName() {
        return BillStateName;
    }

    public void setBillStateName(String BillStateName) {
        this.BillStateName = BillStateName;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String BillDate) {
        this.BillDate = BillDate;
    }

    public String getCheckorCode() {
        return CheckorCode;
    }

    public void setCheckorCode(String CheckorCode) {
        this.CheckorCode = CheckorCode;
    }

    public String getCheckorName() {
        return CheckorName;
    }

    public void setCheckorName(String CheckorName) {
        this.CheckorName = CheckorName;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String DepartmentCode) {
        this.DepartmentCode = DepartmentCode;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public String getEmpCode() {
        return EmpCode;
    }

    public void setEmpCode(String EmpCode) {
        this.EmpCode = EmpCode;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getInStoreCode() {
        return InStoreCode;
    }

    public void setInStoreCode(String InStoreCode) {
        this.InStoreCode = InStoreCode;
    }

    public String getStoreCode() {
        return StoreCode;
    }

    public void setStoreCode(String StoreCode) {
        this.StoreCode = StoreCode;
    }

    public String getInStoreName() {
        return InStoreName;
    }

    public void setInStoreName(String InStoreName) {
        this.InStoreName = InStoreName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getBillTo() {
        return BillTo;
    }

    public void setBillTo(String BillTo) {
        this.BillTo = BillTo;
    }

    public String getOpCode() {
        return OpCode;
    }

    public void setOpCode(String OpCode) {
        this.OpCode = OpCode;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String OpName) {
        this.OpName = OpName;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public Long getCheckorID() {
        return CheckorID;
    }

    public void setCheckorID(Long CheckorID) {
        this.CheckorID = CheckorID;
    }

    public Long getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(Long DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public Long getEmpID() {
        return EmpID;
    }

    public void setEmpID(Long EmpID) {
        this.EmpID = EmpID;
    }

    public Long getInStoreID() {
        return InStoreID;
    }

    public void setInStoreID(Long InStoreID) {
        this.InStoreID = InStoreID;
    }

    public Long getOpID() {
        return OpID;
    }

    public void setOpID(Long OpID) {
        this.OpID = OpID;
    }

    public Long getShipType() {
        return ShipType;
    }

    public void setShipType(Long ShipType) {
        this.ShipType = ShipType;
    }

    public Long getShopID() {
        return ShopID;
    }

    public void setShopID(Long ShopID) {
        this.ShopID = ShopID;
    }

    public Long getStoreID() {
        return StoreID;
    }

    public void setStoreID(Long StoreID) {
        this.StoreID = StoreID;
    }

    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer ReferCount) {
        this.ReferCount = ReferCount;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getShipTypeName() {
        return ShipTypeName;
    }

    public void setShipTypeName(String ShipTypeName) {
        this.ShipTypeName = ShipTypeName;
    }

    public String getSFlag() {
        return SFlag;
    }

    public void setSFlag(String SFlag) {
        this.SFlag = SFlag;
    }

    public String getUserDef1() {
        return UserDef1;
    }

    public void setUserDef1(String UserDef1) {
        this.UserDef1 = UserDef1;
    }

    public String getUserDef2() {
        return UserDef2;
    }

    public void setUserDef2(String UserDef2) {
        this.UserDef2 = UserDef2;
    }

    public String getUserDef3() {
        return UserDef3;
    }

    public void setUserDef3(String UserDef3) {
        this.UserDef3 = UserDef3;
    }

    public String getUserDef4() {
        return UserDef4;
    }

    public void setUserDef4(String UserDef4) {
        this.UserDef4 = UserDef4;
    }

    public String getUserDef5() {
        return UserDef5;
    }

    public void setUserDef5(String UserDef5) {
        this.UserDef5 = UserDef5;
    }

    /**
     * To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity.
     */
    public List<TransferDetail1Data> getTransferDetail1DataList() {
        if (transferDetail1DataList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TransferDetail1DataDao targetDao = daoSession.getTransferDetail1DataDao();
            List<TransferDetail1Data> transferDetail1DataListNew = targetDao._queryTransferMasterData_TransferDetail1DataList(id);
            synchronized (this) {
                if (transferDetail1DataList == null) {
                    transferDetail1DataList = transferDetail1DataListNew;
                }
            }
        }
        return transferDetail1DataList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    public synchronized void resetTransferDetail1DataList() {
        transferDetail1DataList = null;
    }

    /**
     * Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context.
     */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context.
     */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context.
     */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

}
