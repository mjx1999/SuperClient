package com.twisty.superclient.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table ACCOUNT.
 */
public class Account implements java.io.Serializable {

    private Long AccountID;
    private Long ShopID;
    private String AccountName;
    private Long MoneyID;
    private String MoneyName;
    private Long BankID;
    private String BankName;
    private String AccountNo;
    private Integer Closed;

    public Account() {
    }

    public Account(Long AccountID, Long ShopID, String AccountName, Long MoneyID, String MoneyName, Long BankID, String BankName, String AccountNo, Integer Closed) {
        this.AccountID = AccountID;
        this.ShopID = ShopID;
        this.AccountName = AccountName;
        this.MoneyID = MoneyID;
        this.MoneyName = MoneyName;
        this.BankID = BankID;
        this.BankName = BankName;
        this.AccountNo = AccountNo;
        this.Closed = Closed;
    }

    public Long getAccountID() {
        return AccountID;
    }

    public void setAccountID(Long AccountID) {
        this.AccountID = AccountID;
    }

    public Long getShopID() {
        return ShopID;
    }

    public void setShopID(Long ShopID) {
        this.ShopID = ShopID;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String AccountName) {
        this.AccountName = AccountName;
    }

    public Long getMoneyID() {
        return MoneyID;
    }

    public void setMoneyID(Long MoneyID) {
        this.MoneyID = MoneyID;
    }

    public String getMoneyName() {
        return MoneyName;
    }

    public void setMoneyName(String MoneyName) {
        this.MoneyName = MoneyName;
    }

    public Long getBankID() {
        return BankID;
    }

    public void setBankID(Long BankID) {
        this.BankID = BankID;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public Integer getClosed() {
        return Closed;
    }

    public void setClosed(Integer Closed) {
        this.Closed = Closed;
    }

}
