package com.twisty.superclient.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table PAY_METHOD.
 */
public class PayMethod implements java.io.Serializable {

    private Long PayMethodID;
    private String PaymethodCode;
    private String PaymethodName;
    private Long AccountID;
    private Integer Closed;

    public PayMethod() {
    }

    public PayMethod(Long PayMethodID, String PaymethodCode, String PaymethodName, Long AccountID, Integer Closed) {
        this.PayMethodID = PayMethodID;
        this.PaymethodCode = PaymethodCode;
        this.PaymethodName = PaymethodName;
        this.AccountID = AccountID;
        this.Closed = Closed;
    }

    public Long getPayMethodID() {
        return PayMethodID;
    }

    public void setPayMethodID(Long PayMethodID) {
        this.PayMethodID = PayMethodID;
    }

    public String getPaymethodCode() {
        return PaymethodCode;
    }

    public void setPaymethodCode(String PaymethodCode) {
        this.PaymethodCode = PaymethodCode;
    }

    public String getPaymethodName() {
        return PaymethodName;
    }

    public void setPaymethodName(String PaymethodName) {
        this.PaymethodName = PaymethodName;
    }

    public Long getAccountID() {
        return AccountID;
    }

    public void setAccountID(Long AccountID) {
        this.AccountID = AccountID;
    }

    public Integer getClosed() {
        return Closed;
    }

    public void setClosed(Integer Closed) {
        this.Closed = Closed;
    }

}
