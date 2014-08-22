package com.twisty.superclient.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EMPLOYEE.
 */
public class Employee implements java.io.Serializable {

    private Long EmpID;
    private Long ShopID;
    private String EmpCode;
    private String EmpName;
    private Long DepartmentID;
    private String Sex;
    private Integer Close;

    public Employee() {
    }

    public Employee(Long EmpID, Long ShopID, String EmpCode, String EmpName, Long DepartmentID, String Sex, Integer Close) {
        this.EmpID = EmpID;
        this.ShopID = ShopID;
        this.EmpCode = EmpCode;
        this.EmpName = EmpName;
        this.DepartmentID = DepartmentID;
        this.Sex = Sex;
        this.Close = Close;
    }

    public Long getEmpID() {
        return EmpID;
    }

    public void setEmpID(Long EmpID) {
        this.EmpID = EmpID;
    }

    public Long getShopID() {
        return ShopID;
    }

    public void setShopID(Long ShopID) {
        this.ShopID = ShopID;
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

    public Long getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(Long DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public Integer getClose() {
        return Close;
    }

    public void setClose(Integer Close) {
        this.Close = Close;
    }

}