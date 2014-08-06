package com.dont.daoGerner;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGerner {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.twisty.superclient.bean");
        Entity accset = schema.addEntity("Accset");
        accset.addLongProperty("AccsetID").index();
        accset.addStringProperty("AccsetCode");
        accset.addStringProperty("AccsetName");
        accset.implementsSerializable();

        Entity area = schema.addEntity("Area");
        area.addLongProperty("AreaID").index();
        area.addStringProperty("AreaCode");
        area.addStringProperty("AreaName");
        area.addStringProperty("LCode").index();
        area.addLongProperty("ParentID");
        area.implementsSerializable();

        Entity department = schema.addEntity("Department");
        department.addLongProperty("DepartmentID").index();
        department.addStringProperty("DepartmentCode");
        department.addStringProperty("DepartmentName");
        department.addBooleanProperty("Close");
        department.implementsSerializable();

        Entity employee = schema.addEntity("Employee");
        employee.addLongProperty("EmpID").index();
        employee.addStringProperty("EmpCode");
        employee.addStringProperty("EmpName");
        employee.addLongProperty("DepartmentID").index();
        employee.addStringProperty("Sex");
        employee.addBooleanProperty("Close");
        employee.implementsSerializable();

        Entity operator = schema.addEntity("Operator");
        operator.addLongProperty("OpID");
        operator.addStringProperty("OpCode");
        operator.addStringProperty("OpName");
        operator.addStringProperty("OpPassword");
        operator.addStringProperty("SysManager");
        operator.addLongProperty("ShopID");
        operator.implementsSerializable();

        Entity trader = schema.addEntity("Trader");
        trader.addLongProperty("TraderID").index();
        trader.addStringProperty("TraderCode").index();
        trader.addStringProperty("TraderName");
        trader.addStringProperty("FullName");
        trader.addBooleanProperty("IsClient");
        trader.addBooleanProperty("IsVendor");
        trader.addLongProperty("TraderTypeID");
        trader.addLongProperty("AreaID").index();
        trader.addStringProperty("Lev");
        trader.addLongProperty("EmpID").index();
        trader.addLongProperty("DepartmentID").index();
        trader.addLongProperty("Legalrep");
        trader.addLongProperty("Contactor");
        trader.addStringProperty("Phone");
        trader.addStringProperty("Tel1");
        trader.addStringProperty("Tel2");
        trader.addStringProperty("Fax");
        trader.addStringProperty("Zip");
        trader.addStringProperty("Address");
        trader.addStringProperty("ShipTo");
        trader.addStringProperty("EMail");
        trader.addStringProperty("Url");
        trader.addStringProperty("Bank");
        trader.addStringProperty("BankAccno");
        trader.addStringProperty("TaxNo");
        trader.addStringProperty("CreditDay");
        trader.addStringProperty("Credit");
        trader.addBooleanProperty("Closed");
        trader.addBooleanProperty("AccTrader");
        trader.implementsSerializable();

        Entity tradeType = schema.addEntity("TradeType");
        tradeType.addLongProperty("TradeTypeID").index();
        tradeType.addStringProperty("TradeTypeCode");
        tradeType.addStringProperty("TradeTypeName");
        tradeType.addStringProperty("LCode").index();
        tradeType.addLongProperty("ParentID");
        tradeType.implementsSerializable();

        new DaoGenerator().generateAll(schema,"./DaoGerner/Gen");
    }
}
