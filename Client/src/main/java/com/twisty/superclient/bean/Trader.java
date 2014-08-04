package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-4.
 */
public class Trader {
    private long TraderID;/*主键ID*/
    private String TraderCode;      /*往来单位编码*/
    private String TraderName;      /*往来单位名称*/
    private String FullName;        /*往来单位全名*/
    private boolean IsClient;       /*是否客户*/
    private boolean IsVendor;       /*是否供应商*/
    private long TraderTypeID;      /*往来单位类型ID*/
    private long AreaID;            /*地区ID*/
    private String Lev;             /*等级*/
    private long EmpID;             /*默认业务员ID*/
    private long DepartmentID;      /*默认部门ID*/
    private long Legalrep;          /*法人代表*/
    private long Contactor;         /*收货人*/
    private String Phone;           /*联系手机*/
    private String Tel1;            /*常用电话*/
    private String Tel2;            /*备用电话*/
    private String Fax;             /*传真*/
    private String Zip;             /*邮编*/
    private String Address;         /*通信地址*/
    private String ShipTo;          /*送货地址*/
    private String EMail;           /*电子邮件*/
    private String Url;             /*网址*/
    private String Bank;            /*开户银行*/
    private String BankAccno;       /*银行帐号*/
    private String TaxNo;           /*纳税号*/
    private String CreditDay;       /*信用天数*/
    private String Credit;          /*信用额度*/
    private String Closed;          /*停用*/
    private String AccTrader;       /*双重身份*/

    public long getTraderID() {
        return TraderID;
    }

    public void setTraderID(long traderID) {
        TraderID = traderID;
    }

    public String getTraderCode() {
        return TraderCode;
    }

    public void setTraderCode(String traderCode) {
        TraderCode = traderCode;
    }

    public String getTraderName() {
        return TraderName;
    }

    public void setTraderName(String traderName) {
        TraderName = traderName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public boolean isClient() {
        return IsClient;
    }

    public void setClient(boolean isClient) {
        IsClient = isClient;
    }

    public boolean isVendor() {
        return IsVendor;
    }

    public void setVendor(boolean isVendor) {
        IsVendor = isVendor;
    }

    public long getTraderTypeID() {
        return TraderTypeID;
    }

    public void setTraderTypeID(long traderTypeID) {
        TraderTypeID = traderTypeID;
    }

    public long getAreaID() {
        return AreaID;
    }

    public void setAreaID(long areaID) {
        AreaID = areaID;
    }

    public String getLev() {
        return Lev;
    }

    public void setLev(String lev) {
        Lev = lev;
    }

    public long getEmpID() {
        return EmpID;
    }

    public void setEmpID(long empID) {
        EmpID = empID;
    }

    public long getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(long departmentID) {
        DepartmentID = departmentID;
    }

    public long getLegalrep() {
        return Legalrep;
    }

    public void setLegalrep(long legalrep) {
        Legalrep = legalrep;
    }

    public long getContactor() {
        return Contactor;
    }

    public void setContactor(long contactor) {
        Contactor = contactor;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTel1() {
        return Tel1;
    }

    public void setTel1(String tel1) {
        Tel1 = tel1;
    }

    public String getTel2() {
        return Tel2;
    }

    public void setTel2(String tel2) {
        Tel2 = tel2;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getShipTo() {
        return ShipTo;
    }

    public void setShipTo(String shipTo) {
        ShipTo = shipTo;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getBankAccno() {
        return BankAccno;
    }

    public void setBankAccno(String bankAccno) {
        BankAccno = bankAccno;
    }

    public String getTaxNo() {
        return TaxNo;
    }

    public void setTaxNo(String taxNo) {
        TaxNo = taxNo;
    }

    public String getCreditDay() {
        return CreditDay;
    }

    public void setCreditDay(String creditDay) {
        CreditDay = creditDay;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }

    public String getClosed() {
        return Closed;
    }

    public void setClosed(String closed) {
        Closed = closed;
    }

    public String getAccTrader() {
        return AccTrader;
    }

    public void setAccTrader(String accTrader) {
        AccTrader = accTrader;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "TraderID=" + TraderID +
                ", TraderCode='" + TraderCode + '\'' +
                ", TraderName='" + TraderName + '\'' +
                ", FullName='" + FullName + '\'' +
                ", IsClient=" + IsClient +
                ", IsVendor=" + IsVendor +
                ", TraderTypeID=" + TraderTypeID +
                ", AreaID=" + AreaID +
                ", Lev='" + Lev + '\'' +
                ", EmpID=" + EmpID +
                ", DepartmentID=" + DepartmentID +
                ", Legalrep=" + Legalrep +
                ", Contactor=" + Contactor +
                ", Phone='" + Phone + '\'' +
                ", Tel1='" + Tel1 + '\'' +
                ", Tel2='" + Tel2 + '\'' +
                ", Fax='" + Fax + '\'' +
                ", Zip='" + Zip + '\'' +
                ", Address='" + Address + '\'' +
                ", ShipTo='" + ShipTo + '\'' +
                ", EMail='" + EMail + '\'' +
                ", Url='" + Url + '\'' +
                ", Bank='" + Bank + '\'' +
                ", BankAccno='" + BankAccno + '\'' +
                ", TaxNo='" + TaxNo + '\'' +
                ", CreditDay='" + CreditDay + '\'' +
                ", Credit='" + Credit + '\'' +
                ", Closed='" + Closed + '\'' +
                ", AccTrader='" + AccTrader + '\'' +
                '}';
    }
}
