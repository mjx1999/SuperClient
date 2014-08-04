package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-4.
 */
public class Area {
    private long AreaID;/*主键ID*/
    private String AreaCode; /*地区编码*/
    private String AreaName; /*地区名称*/
    private String LCode;/*分层码*/
    private String ParentID; /*父级ID*/

    public long getAreaID() {
        return AreaID;
    }

    public void setAreaID(long areaID) {
        AreaID = areaID;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getLCode() {
        return LCode;
    }

    public void setLCode(String LCode) {
        this.LCode = LCode;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }
}
