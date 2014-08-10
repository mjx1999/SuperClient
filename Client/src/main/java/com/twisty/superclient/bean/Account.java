package com.twisty.superclient.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table ACCOUNT.
 */
public class Account implements java.io.Serializable {

    private Long AccountID;
    private String AccountName;
    private Long MoneyID;
    private Long MoneyName;
    private Long BankID;
    private String BankName;
    private String AccountNo;
    private Integer Closed;

    public Account() {
    }

    public Account(Long AccountID, String AccountName, Long MoneyID, Long MoneyName, Long BankID, String BankName, String AccountNo, Integer Closed) {
        this.AccountID = AccountID;
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

    public Long getMoneyName() {
        return MoneyName;
    }

    public void setMoneyName(Long MoneyName) {
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
