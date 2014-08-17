package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-16.
 */
public class Bill {
    private long BillID;
    private String BillCode;
    private String BillDate;
    private double Amount;
    private double ReferAmt;
    private String CheckNo;
    private double Disc;
    private String Linkman;
    private String NoteNo;
    private double PayAmt;
    private String PayDate;
    private int TermDays;
    private String TraderName;
    private String OpName;
    private String Checkor;
    private String DepartmentName;
    private String PaymethodName;
    private String AccountName;
    private String EmpName;
    private String BillKindName;
    private String BillStateName;
    private String NoteTypeName;
    private long ShopID;

    public long getBillID() {
        return BillID;
    }

    public void setBillID(long billID) {
        BillID = billID;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String billCode) {
        BillCode = billCode;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getReferAmt() {
        return ReferAmt;
    }

    public void setReferAmt(double referAmt) {
        ReferAmt = referAmt;
    }

    public String getCheckNo() {
        return CheckNo;
    }

    public void setCheckNo(String checkNo) {
        CheckNo = checkNo;
    }

    public double getDisc() {
        return Disc;
    }

    public void setDisc(double disc) {
        Disc = disc;
    }

    public String getLinkman() {
        return Linkman;
    }

    public void setLinkman(String linkman) {
        Linkman = linkman;
    }

    public String getNoteNo() {
        return NoteNo;
    }

    public void setNoteNo(String noteNo) {
        NoteNo = noteNo;
    }

    public double getPayAmt() {
        return PayAmt;
    }

    public void setPayAmt(double payAmt) {
        PayAmt = payAmt;
    }

    public String getPayDate() {
        return PayDate;
    }

    public void setPayDate(String payDate) {
        PayDate = payDate;
    }

    public int getTermDays() {
        return TermDays;
    }

    public void setTermDays(int termDays) {
        TermDays = termDays;
    }

    public String getTraderName() {
        return TraderName;
    }

    public void setTraderName(String traderName) {
        TraderName = traderName;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String opName) {
        OpName = opName;
    }

    public String getCheckor() {
        return Checkor;
    }

    public void setCheckor(String checkor) {
        Checkor = checkor;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getPaymethodName() {
        return PaymethodName;
    }

    public void setPaymethodName(String paymethodName) {
        PaymethodName = paymethodName;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getBillKindName() {
        return BillKindName;
    }

    public void setBillKindName(String billKindName) {
        BillKindName = billKindName;
    }

    public String getBillStateName() {
        return BillStateName;
    }

    public void setBillStateName(String billStateName) {
        BillStateName = billStateName;
    }

    public String getNoteTypeName() {
        return NoteTypeName;
    }

    public void setNoteTypeName(String noteTypeName) {
        NoteTypeName = noteTypeName;
    }

    public long getShopID() {
        return ShopID;
    }

    public void setShopID(long shopID) {
        ShopID = shopID;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "BillID=" + BillID +
                ", BillCode='" + BillCode + '\'' +
                ", BillDate='" + BillDate + '\'' +
                ", Amount=" + Amount +
                ", ReferAmt=" + ReferAmt +
                ", CheckNo='" + CheckNo + '\'' +
                ", Disc=" + Disc +
                ", Linkman='" + Linkman + '\'' +
                ", NoteNo='" + NoteNo + '\'' +
                ", PayAmt=" + PayAmt +
                ", PayDate='" + PayDate + '\'' +
                ", TermDays=" + TermDays +
                ", TraderName='" + TraderName + '\'' +
                ", OpName='" + OpName + '\'' +
                ", Checkor='" + Checkor + '\'' +
                ", DepartmentName='" + DepartmentName + '\'' +
                ", PaymethodName='" + PaymethodName + '\'' +
                ", AccountName='" + AccountName + '\'' +
                ", EmpName='" + EmpName + '\'' +
                ", BillKindName='" + BillKindName + '\'' +
                ", BillStateName='" + BillStateName + '\'' +
                ", NoteTypeName='" + NoteTypeName + '\'' +
                ", ShopID=" + ShopID +
                '}';
    }
}
