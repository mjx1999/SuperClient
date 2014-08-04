package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-4.
 */
public class Employee {
    private long EmpID;/*主键ID*/
    private String EmpCode;/*员工编码*/
    private String EmpName;/*员工姓名*/
    private String DepartmentID; /*部门ID*/
    private String Sex;/*性别*/
    private String Close;/*是否停用*/

    public long getEmpID() {
        return EmpID;
    }

    public void setEmpID(long empID) {
        EmpID = empID;
    }

    public String getEmpCode() {
        return EmpCode;
    }

    public void setEmpCode(String empCode) {
        EmpCode = empCode;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }
}
