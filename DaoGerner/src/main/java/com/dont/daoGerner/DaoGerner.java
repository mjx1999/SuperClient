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
        employee.addIntProperty("Close");
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
        trader.addIntProperty("IsClient");
        trader.addIntProperty("IsVendor");
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
        trader.addIntProperty("CreditDay");
        trader.addDoubleProperty("Credit");
        trader.addIntProperty("Closed");
        trader.addIntProperty("AccTrader");
        trader.implementsSerializable();

        Entity tradeType = schema.addEntity("TradeType");
        tradeType.addLongProperty("TradeTypeID").index();
        tradeType.addStringProperty("TradeTypeCode");
        tradeType.addStringProperty("TradeTypeName");
        tradeType.addStringProperty("LCode").index();
        tradeType.addLongProperty("ParentID");
        tradeType.implementsSerializable();


        Entity ioType = schema.addEntity("IoType");
        ioType.addLongProperty("IoTypeID").index();
        ioType.addStringProperty("IoTypeCode");
        ioType.addStringProperty("IoTypeName");
        ioType.addIntProperty("IoFlag");
        ioType.addIntProperty("Closed");
        ioType.implementsSerializable();

        Entity store = schema.addEntity("Store");
        store.addLongProperty("StoreID").index();
        store.addStringProperty("StoreCode");
        store.addStringProperty("StoreName");
        store.addStringProperty("Location");
        store.addIntProperty("Closed");
        store.implementsSerializable();

        Entity gdType = schema.addEntity("GDType");
        gdType.addLongProperty("GDTypeID").index();
        gdType.addStringProperty("GDTypeCode");
        gdType.addStringProperty("GDTypeName");
        gdType.addLongProperty("ParentID");
        gdType.addStringProperty("LCode");
        gdType.addIntProperty("Closed");
        gdType.implementsSerializable();

        Entity goods = schema.addEntity("Goods");
        goods.addLongProperty("GoodsID");
        goods.addStringProperty("GoodsCode");
        goods.addStringProperty("GoodsName");
        goods.addStringProperty("ShortName");
        goods.addStringProperty("Specs");
        goods.addLongProperty("GDTypeID").index();
        goods.addStringProperty("GDUserDef1");
        goods.addStringProperty("GDUserDef2");
        goods.addStringProperty("GDUserDef3");
        goods.addStringProperty("GDUserDef4");
        goods.addStringProperty("GDUserDef5");
        goods.addStringProperty("GDUserDef6");
        goods.addStringProperty("GDUserDef7");
        goods.addStringProperty("GDUserDef8");
        goods.addStringProperty("GDUserDef9");
        goods.addStringProperty("GDUserDef10");
        goods.implementsSerializable();


        Entity goodsUtil = schema.addEntity("Unit");
        goodsUtil.addLongProperty("UnitID").index();
        goodsUtil.addLongProperty("GoodsID").index();
        goodsUtil.addStringProperty("UnitName");
        goodsUtil.addStringProperty("BarCode");
        goodsUtil.addFloatProperty("Rate");
        goodsUtil.addIntProperty("IsBase");
        goodsUtil.addDoubleProperty("SPrice");
        goodsUtil.addDoubleProperty("PPrice");
        goodsUtil.addDoubleProperty("HighPrice");
        goodsUtil.addDoubleProperty("LowPrice");
        goodsUtil.addDoubleProperty("VipPrice");
        goodsUtil.addDoubleProperty("LPrice1");
        goodsUtil.addDoubleProperty("LPrice2");
        goodsUtil.addDoubleProperty("LPrice3");
        goodsUtil.addDoubleProperty("LPrice4");
        goodsUtil.addDoubleProperty("LPrice5");
        goodsUtil.addIntProperty("IsSale");
        goodsUtil.addIntProperty("IsPurchase");
        goodsUtil.addIntProperty("IsStore");
        goodsUtil.implementsSerializable();



        Entity traderType = schema.addEntity("TraderPrice");
        traderType.addLongProperty("ID").index();
        traderType.addLongProperty("TraderID").index();
        traderType.addLongProperty("GoodsID").index();
        traderType.addLongProperty("UnitID").index();
        traderType.addDoubleProperty("Price");
        traderType.addDoubleProperty("APrice");


        Entity goodsPicture = schema.addEntity("GoodsPicture");
        goodsPicture.addLongProperty("ID").index();
        goodsPicture.addStringProperty("Picture");
        goodsPicture.addStringProperty("PictureExt");
        goodsPicture.implementsSerializable();

        Entity onHand = schema.addEntity("OnHand");
        onHand.addLongProperty("StoreID").index();
        onHand.addLongProperty("GoodsID").index();
        onHand.addDoubleProperty("Quantity");
        onHand.addDoubleProperty("RealQty");
        onHand.implementsSerializable();


        Entity account = schema.addEntity("Account");
        account.addLongProperty("AccountID").index();
        account.addStringProperty("AccountName");
        account.addLongProperty("MoneyID");
        account.addLongProperty("MoneyName");
        account.addLongProperty("BankID");
        account.addStringProperty("BankName");
        account.addStringProperty("AccountNo");
        account.addIntProperty("Closed");
        account.implementsSerializable();



        new DaoGenerator().generateAll(schema,"./DaoGerner/Gen");
    }
}































