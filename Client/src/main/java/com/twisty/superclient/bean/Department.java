package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-4.
 */
public class Department {
    private long DepartmentID;/*主键ID*/
    private String DepartmentCode;/*部门编码*/
    private String DepartmentName; /*部门名称*/
    private String Close; /*是否停用*/

    public long getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(long departmentID) {
        DepartmentID = departmentID;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        DepartmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }
}
