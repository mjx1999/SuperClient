package com.dont.daoGerner;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
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
        employee.addLongProperty("ShopID").index();
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
        trader.addLongProperty("ShopID").index();
        trader.addStringProperty("TraderCode").index();
        trader.addStringProperty("TraderName");
        trader.addStringProperty("FullName");
        trader.addIntProperty("IsClient");
        trader.addIntProperty("IsVendor");
        trader.addLongProperty("TraderTypeID");
        trader.addLongProperty("AreaID").index();
        trader.addIntProperty("Lev");
        trader.addLongProperty("EmpID").index();
        trader.addLongProperty("DepartmentID").index();
        trader.addStringProperty("Legalrep");
        trader.addStringProperty("Contactor");
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
        store.addLongProperty("ShopID").index();
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


        Entity goodsUnit = schema.addEntity("Unit");
        goodsUnit.addLongProperty("UnitID").index();
        goodsUnit.addLongProperty("GoodsID").index();
        goodsUnit.addStringProperty("UnitName");
        goodsUnit.addStringProperty("BarCode");
        goodsUnit.addDoubleProperty("Rate");
        goodsUnit.addIntProperty("IsBase");
        goodsUnit.addDoubleProperty("SPrice");
        goodsUnit.addDoubleProperty("PPrice");
        goodsUnit.addDoubleProperty("HighPrice");
        goodsUnit.addDoubleProperty("LowPrice");
        goodsUnit.addDoubleProperty("VipPrice");
        goodsUnit.addDoubleProperty("LPrice1");
        goodsUnit.addDoubleProperty("LPrice2");
        goodsUnit.addDoubleProperty("LPrice3");
        goodsUnit.addDoubleProperty("LPrice4");
        goodsUnit.addDoubleProperty("LPrice5");
        goodsUnit.addIntProperty("IsSale");
        goodsUnit.addIntProperty("IsPurchase");
        goodsUnit.addIntProperty("IsStore");
        goodsUnit.implementsSerializable();


        Entity traderType = schema.addEntity("TraderPrice");
        traderType.addLongProperty("ID").index();
        traderType.addLongProperty("TraderID").index();
        traderType.addLongProperty("GoodsID").index();
        traderType.addLongProperty("UnitID").index();
        traderType.addLongProperty("ShopID").index();
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
        onHand.addLongProperty("ShopID").index();
        onHand.addDoubleProperty("Quantity");
        onHand.addDoubleProperty("RealQty");
        onHand.implementsSerializable();


        Entity account = schema.addEntity("Account");
        account.addLongProperty("AccountID").index();
        account.addLongProperty("ShopID").index();
        account.addStringProperty("AccountName");
        account.addLongProperty("MoneyID");
        account.addStringProperty("MoneyName");
        account.addLongProperty("BankID");
        account.addStringProperty("BankName");
        account.addStringProperty("AccountNo");
        account.addIntProperty("Closed");
        account.implementsSerializable();

        Entity payMethod = schema.addEntity("PayMethod");
        payMethod.addLongProperty("PayMethodID").index();
        payMethod.addStringProperty("PaymethodCode");
        payMethod.addStringProperty("PaymethodName");
        payMethod.addLongProperty("AccountID");
        payMethod.addIntProperty("Closed");
        payMethod.implementsSerializable();

        Entity amKind = schema.addEntity("AMKind");
        amKind.addLongProperty("ID").index();
        amKind.addStringProperty("Name");
        amKind.addLongProperty("Kind").index();
        amKind.addStringProperty("KindName");
        amKind.implementsSerializable();


        //--------------------------------------------销售单------------------------------------------------------------------
        //
//                "APrice": 123.123,
//                "Amount": 3444444.44,
//                "BReferID": null,
//                "BarCode": null,
//                "BillID": 15,
//                "Disc": 1231.123,"DiscAmt": -69643016.930000007, "GoodsAmt": 124444.12, "GoodsCode": "1008","GoodsID": 8,
//                "GoodsName": "麦克风",   "IOQty": 0.0,  "IsLargess": 0,    "ItemNO": 2,
//                "OrigPrice": 23.0,
//                "OrigTaxPrice": 234.234,
//                "Quantity": 23.2,
//                "ReferBillCode": null,
//                "ReferBillID": null,
//                "ReferBillType": 0,
//                "ReferItemNo": null,
//                "ReferQty": 0.0,
//                "ShortName": "",
//                "Specs": "大朋",
//                "StoreCode": null,
//                "StoreID": null,
//                "StoreName": null,
//                "TaxAmt": 22.22,
//                "TaxPrice": 0.0,
//                "TaxRate": 2.33,
//                "UnitID": 129,
//                "UnitName": "对",
//                "UnitPrice": 222.22222,
//                "UnitQuantity": 333333.333,
//                "UnitRate": 1.0


        Entity salesBillDetail1Data = schema.addEntity("SalesBillDetail1Data");
        Property sbDetailID = salesBillDetail1Data.addIdProperty().index().autoincrement().getProperty();
        Property sbMasterID = salesBillDetail1Data.addLongProperty("MasterID").getProperty();
        salesBillDetail1Data.addLongProperty("BillID").index();
        salesBillDetail1Data.addIntProperty("ItemNO");
        salesBillDetail1Data.addDoubleProperty("APrice");
        salesBillDetail1Data.addDoubleProperty("Amount");
        salesBillDetail1Data.addLongProperty("BReferID");
        salesBillDetail1Data.addDoubleProperty("Disc");
        salesBillDetail1Data.addDoubleProperty("DiscAmt");
        salesBillDetail1Data.addDoubleProperty("ReferQty");
        salesBillDetail1Data.addDoubleProperty("IOQty");
        salesBillDetail1Data.addLongProperty("GoodsID");
        salesBillDetail1Data.addDoubleProperty("GoodsAmt");
        salesBillDetail1Data.addIntProperty("IsLargess");
        salesBillDetail1Data.addDoubleProperty("OrigPrice");
        salesBillDetail1Data.addDoubleProperty("OrigTaxPrice");
        salesBillDetail1Data.addDoubleProperty("Quantity");
        salesBillDetail1Data.addStringProperty("ReferBillCode");
        salesBillDetail1Data.addStringProperty("UnitName");
        salesBillDetail1Data.addStringProperty("StoreCode");
        salesBillDetail1Data.addStringProperty("Specs");
        salesBillDetail1Data.addStringProperty("StoreName");
        salesBillDetail1Data.addStringProperty("GoodsName");
        salesBillDetail1Data.addStringProperty("GoodsCode");
        salesBillDetail1Data.addStringProperty("BarCode");
        salesBillDetail1Data.addStringProperty("ShortName");
        salesBillDetail1Data.addLongProperty("ReferBillID");
        salesBillDetail1Data.addIntProperty("ReferBillType");
        salesBillDetail1Data.addIntProperty("ReferItemNo");
        salesBillDetail1Data.addLongProperty("StoreID");
        salesBillDetail1Data.addDoubleProperty("TaxAmt");
        salesBillDetail1Data.addDoubleProperty("TaxPrice");
        salesBillDetail1Data.addDoubleProperty("TaxRate");
        salesBillDetail1Data.addLongProperty("UnitID");
        salesBillDetail1Data.addDoubleProperty("UnitPrice");
        salesBillDetail1Data.addDoubleProperty("UnitQuantity");
        salesBillDetail1Data.addDoubleProperty("UnitRate");
        salesBillDetail1Data.addStringProperty("UserDef1");
        salesBillDetail1Data.addStringProperty("UserDef2");
        salesBillDetail1Data.addStringProperty("UserDef3");
        salesBillDetail1Data.addStringProperty("UserDef4");
        salesBillDetail1Data.addStringProperty("UserDef5");
        salesBillDetail1Data.addStringProperty("UserDef6");
        salesBillDetail1Data.addStringProperty("UserDef7");
        salesBillDetail1Data.addStringProperty("UserDef8");
        salesBillDetail1Data.addStringProperty("UserDef9");
        salesBillDetail1Data.addStringProperty("UserDef10");
        salesBillDetail1Data.implementsSerializable();

//        "AccountID":null,"AccountName":null,"Amount":123123.12,"BillCode":"SS-01-20140825-222903",
//                "BillDate":"2014-08-25 00:00:00","BillID":12,"BillKind":1, "BillKindName":"销售开单",
//                "BillState":0, "BillStateName":"待审核","BillTo":"上海松江区胡角公路600号","CheckNo":null,
//                "Checkor":null, "CheckorID":null, "ContactFax":null,"ContactPhone":"021-57899208",
//                "DepartmentCode":null, "DepartmentID":null,"DepartmentName":null, "Disc":100.0,
//                "EmpCode":null, "EmpID":null, "EmpName":null,"InvoiceAmt":0.0, "IsCashTrader":0,
//                "LinkManID":null, "Linkman":null,"NoteNo":null,"NoteTypeID":0, "NoteTypeName":null,
//                "OpID":0, "OpName":"系统管理员", "PayAmt":0.0, "PayDate":null,"PayMethodID":null,
//                "PaymethodCode":null,"PaymethodName":null, "Printed":0, "ReferAmt":0.0,"Remark":null,
//                "SFlag":0, "ScAmt":0.0, "ShipType":null,"ShipTypeName":null, "ShopID":0, "TermDays":0,
//                "TraderCode":"K0020", "TraderID":54,"TraderName":"雅森公司", "UpdateTime":"2014-08-25 22:29:01",


        //销售单
        Entity salesBillMasterData = schema.addEntity("SalesBillMasterData");
        salesBillMasterData.addIdProperty().index().autoincrement().getProperty();
        salesBillMasterData.addToMany(salesBillDetail1Data, sbMasterID);
        salesBillMasterData.addLongProperty("BillID").index();
        salesBillMasterData.addLongProperty("CheckorID").index();
        salesBillMasterData.addIntProperty("BillKind");
        salesBillMasterData.addIntProperty("BillState");
        salesBillMasterData.addIntProperty("IsCashTrader");
        salesBillMasterData.addStringProperty("BillCode");
        salesBillMasterData.addStringProperty("Checkor");
        salesBillMasterData.addStringProperty("TraderCode");
        salesBillMasterData.addStringProperty("TraderName");
        salesBillMasterData.addStringProperty("DepartmentCode");
        salesBillMasterData.addStringProperty("PaymethodCode");
        salesBillMasterData.addStringProperty("PaymethodName");
        salesBillMasterData.addStringProperty("ShipTypeName");
        salesBillMasterData.addStringProperty("NoteTypeName");
        salesBillMasterData.addStringProperty("EmpName");
        salesBillMasterData.addStringProperty("EmpCode");
        salesBillMasterData.addStringProperty("DepartmentName");
        salesBillMasterData.addStringProperty("BillDate");
        salesBillMasterData.addStringProperty("BillKindName");
        salesBillMasterData.addStringProperty("OpName");
        salesBillMasterData.addStringProperty("BillStateName");
        salesBillMasterData.addStringProperty("BillTo");
        salesBillMasterData.addLongProperty("AccountID");
        salesBillMasterData.addDoubleProperty("Amount");
        salesBillMasterData.addStringProperty("Remark");
        salesBillMasterData.addDoubleProperty("ReferAmt");
        salesBillMasterData.addDoubleProperty("InvoiceAmt");
        salesBillMasterData.addStringProperty("CheckNo");
        salesBillMasterData.addStringProperty("AccountName");
        salesBillMasterData.addStringProperty("ContactFax");
        salesBillMasterData.addStringProperty("ContactPhone");
        salesBillMasterData.addLongProperty("DepartmentID");
        salesBillMasterData.addDoubleProperty("Disc");
        salesBillMasterData.addLongProperty("EmpID");
        salesBillMasterData.addLongProperty("LinkManID");
        salesBillMasterData.addStringProperty("Linkman");
        salesBillMasterData.addStringProperty("NoteNo");
        salesBillMasterData.addLongProperty("NoteTypeID");
        salesBillMasterData.addLongProperty("OpID");
        salesBillMasterData.addDoubleProperty("PayAmt");
        salesBillMasterData.addStringProperty("PayDate");
        salesBillMasterData.addLongProperty("PayMethodID");
        salesBillMasterData.addIntProperty("Printed");
        salesBillMasterData.addIntProperty("SFlag");
        salesBillMasterData.addLongProperty("ShipType");
        salesBillMasterData.addLongProperty("ShopID");
        salesBillMasterData.addIntProperty("TermDays");
        salesBillMasterData.addLongProperty("TraderID");
        salesBillMasterData.addStringProperty("UpdateTime");
        salesBillMasterData.addStringProperty("UserDef1");
        salesBillMasterData.addStringProperty("UserDef2");
        salesBillMasterData.addStringProperty("UserDef3");
        salesBillMasterData.addStringProperty("UserDef4");
        salesBillMasterData.addStringProperty("UserDef5");

        salesBillMasterData.implementsSerializable();


//
//        Entity salesBillDBBean = schema.addEntity("SalesBillDBBean");
//        salesBillDBBean.addIdProperty().index().autoincrement();
//        salesBillDBBean.addToOne(salesBillMasterData, sbMasterID);
//        salesBillDBBean.addToMany(salesBillDetail1Data, sbDetailID);
//        salesBillDBBean.implementsSerializable();

        //--------------------------------------------客户订单------------------------------------------------------------------
        Entity salesOrderDetail1Data = schema.addEntity("SalesOrderDetail1Data");
        Property soDetailID = salesOrderDetail1Data.addIdProperty().index().autoincrement().getProperty();
        Property soMasterID = salesOrderDetail1Data.addLongProperty("MasterID").getProperty();
        salesOrderDetail1Data.addLongProperty("BillID");
        salesOrderDetail1Data.addIntProperty("ItemNo");
        salesOrderDetail1Data.addDoubleProperty("APrice");
        salesOrderDetail1Data.addDoubleProperty("Amount");
        salesOrderDetail1Data.addStringProperty("BarCode");
        salesOrderDetail1Data.addStringProperty("ChargeDate");
        salesOrderDetail1Data.addIntProperty("Closed");
        salesOrderDetail1Data.addDoubleProperty("Disc");
        salesOrderDetail1Data.addStringProperty("GUserDef1");
        salesOrderDetail1Data.addStringProperty("GUserDef2");
        salesOrderDetail1Data.addStringProperty("GUserDef3");
        salesOrderDetail1Data.addStringProperty("GUserDef4");
        salesOrderDetail1Data.addStringProperty("GUserDef5");
        salesOrderDetail1Data.addDoubleProperty("GoodsAmt");
        salesOrderDetail1Data.addStringProperty("GoodsCode");
        salesOrderDetail1Data.addLongProperty("GoodsID");
        salesOrderDetail1Data.addStringProperty("GoodsName");
        salesOrderDetail1Data.addIntProperty("IsLargess");

        salesOrderDetail1Data.addDoubleProperty("OrigPrice");
        salesOrderDetail1Data.addDoubleProperty("OrigTaxPrice");
        salesOrderDetail1Data.addDoubleProperty("TaxPrice");
        salesOrderDetail1Data.addDoubleProperty("Price");
        salesOrderDetail1Data.addDoubleProperty("Quantity");

        salesOrderDetail1Data.addStringProperty("ReferBillCode");
        salesOrderDetail1Data.addLongProperty("ReferBillID");
        salesOrderDetail1Data.addStringProperty("ReferBillType");
        salesOrderDetail1Data.addIntProperty("ReferCount");
        salesOrderDetail1Data.addIntProperty("ReferItemNo");
        salesOrderDetail1Data.addDoubleProperty("ReferQty");


        salesOrderDetail1Data.addStringProperty("Remark");
        salesOrderDetail1Data.addStringProperty("ShortName");
        salesOrderDetail1Data.addStringProperty("Specs");
        salesOrderDetail1Data.addLongProperty("StoreID");
        salesOrderDetail1Data.addDoubleProperty("TaxAmt");
        salesOrderDetail1Data.addDoubleProperty("TaxRate");
        salesOrderDetail1Data.addLongProperty("UnitID");
        salesOrderDetail1Data.addStringProperty("UnitName");
        salesOrderDetail1Data.addDoubleProperty("UnitPrice");
        salesOrderDetail1Data.addDoubleProperty("UnitQuantity");
        salesOrderDetail1Data.addDoubleProperty("UnitRate");
        salesOrderDetail1Data.addStringProperty("UserDef1");
        salesOrderDetail1Data.addStringProperty("UserDef2");
        salesOrderDetail1Data.addStringProperty("UserDef3");
        salesOrderDetail1Data.addStringProperty("UserDef4");
        salesOrderDetail1Data.addStringProperty("UserDef5");

        salesOrderDetail1Data.implementsSerializable();


        Entity salesOrderMasterData = schema.addEntity("SalesOrderMasterData");
        salesOrderMasterData.addIdProperty().index().autoincrement().getProperty();
        salesOrderMasterData.addToMany(salesOrderDetail1Data, soMasterID);
        salesOrderMasterData.addLongProperty("BillID");
        salesOrderMasterData.addIntProperty("BillState");
        salesOrderMasterData.addStringProperty("BillStateName");
        salesOrderMasterData.addDoubleProperty("Amount");
        salesOrderMasterData.addStringProperty("BillCode");
        salesOrderMasterData.addStringProperty("BillDate");
        salesOrderMasterData.addStringProperty("Billto");
        salesOrderMasterData.addStringProperty("CheckorCode");
        salesOrderMasterData.addLongProperty("CheckorID");
        salesOrderMasterData.addStringProperty("CheckorName");
        salesOrderMasterData.addStringProperty("CloseReason");
        salesOrderMasterData.addIntProperty("Closed");
        salesOrderMasterData.addStringProperty("ContactFax");
        salesOrderMasterData.addStringProperty("ContactPhone");
        salesOrderMasterData.addStringProperty("Contactor");
        salesOrderMasterData.addStringProperty("ContractNo");
        salesOrderMasterData.addStringProperty("DepartmentCode");
        salesOrderMasterData.addStringProperty("DepartmentName");
        salesOrderMasterData.addLongProperty("DepartmentID");
        salesOrderMasterData.addStringProperty("EmpCode");
        salesOrderMasterData.addLongProperty("EmpID");
        salesOrderMasterData.addStringProperty("EmpName");
        salesOrderMasterData.addStringProperty("LinkMan");
        salesOrderMasterData.addStringProperty("OpCode");

        salesOrderMasterData.addLongProperty("OpID");
        salesOrderMasterData.addStringProperty("OpName");
        salesOrderMasterData.addLongProperty("PaymethodID");
        salesOrderMasterData.addStringProperty("PaymethodName");
        salesOrderMasterData.addDoubleProperty("ReferAmt");

        salesOrderMasterData.addStringProperty("Remark");
        salesOrderMasterData.addStringProperty("RevDate");

        salesOrderMasterData.addIntProperty("Sflag");
        salesOrderMasterData.addLongProperty("ShipType");
        salesOrderMasterData.addStringProperty("ShipTypeName");

        salesOrderMasterData.addLongProperty("Shopid");

        salesOrderMasterData.addStringProperty("TraderCode");
        salesOrderMasterData.addLongProperty("TraderId");
        salesOrderMasterData.addStringProperty("TraderName");
        salesOrderMasterData.addStringProperty("UpdateTime");
        salesOrderMasterData.addStringProperty("UserDef1");
        salesOrderMasterData.addStringProperty("UserDef2");
        salesOrderMasterData.addStringProperty("UserDef3");
        salesOrderMasterData.addStringProperty("UserDef4");
        salesOrderMasterData.addStringProperty("UserDef5");
        salesOrderMasterData.addStringProperty("ValidDate");

        salesOrderMasterData.implementsSerializable();


//
//        Entity salesOrderDBBean = schema.addEntity("SalesOrderDBBean");
//        salesOrderDBBean.addIdProperty().index().autoincrement();
//        salesOrderDBBean.addToOne(salesOrderMasterData, soMasterID);
//        salesOrderDBBean.addToMany(salesOrderDetail1Data, soDetailID);
//        salesOrderDBBean.implementsSerializable();
//--------------------------------------------调拨单------------------------------------------------------------------
        Entity transferDetail1Data = schema.addEntity("TransferDetail1Data");
        Property tsDetailID = transferDetail1Data.addIdProperty().index().autoincrement().getProperty();
        Property tsMasterID = transferDetail1Data.addLongProperty("MasterID").getProperty();
        transferDetail1Data.addLongProperty("BillID").index();
        transferDetail1Data.addLongProperty("GoodsID");
        transferDetail1Data.addLongProperty("ReferBillID");
        transferDetail1Data.addLongProperty("UnitID");
        transferDetail1Data.addIntProperty("ItemNo");
        transferDetail1Data.addIntProperty("ReferCount");
        transferDetail1Data.addIntProperty("ReferItemNo");
        transferDetail1Data.addIntProperty("ReferBillType");
        transferDetail1Data.addDoubleProperty("APrice");
        transferDetail1Data.addDoubleProperty("BeforePrice");
        transferDetail1Data.addDoubleProperty("Amount");
        transferDetail1Data.addDoubleProperty("UnitPrice");
        transferDetail1Data.addDoubleProperty("Price");
        transferDetail1Data.addDoubleProperty("Quantity");
        transferDetail1Data.addDoubleProperty("IOQty");
        transferDetail1Data.addDoubleProperty("OIQty");
        transferDetail1Data.addDoubleProperty("UnitQuantity");
        transferDetail1Data.addDoubleProperty("UnitRate");
        transferDetail1Data.addStringProperty("BarCode");
        transferDetail1Data.addStringProperty("GoodsCode");
        transferDetail1Data.addStringProperty("ReferBillCode");
        transferDetail1Data.addStringProperty("Remark");
        transferDetail1Data.addStringProperty("GoodsName");
        transferDetail1Data.addStringProperty("ShortName");
        transferDetail1Data.addStringProperty("Specs");
        transferDetail1Data.addStringProperty("UnitName");

        transferDetail1Data.addStringProperty("UserDef1");
        transferDetail1Data.addStringProperty("UserDef2");
        transferDetail1Data.addStringProperty("UserDef3");
        transferDetail1Data.addStringProperty("UserDef4");
        transferDetail1Data.addStringProperty("UserDef5");
        transferDetail1Data.implementsSerializable();

        Entity transferMasterData = schema.addEntity("TransferMasterData");
        transferMasterData.addIdProperty().index().autoincrement().getProperty();
        transferMasterData.addToMany(transferDetail1Data, tsMasterID);
        transferMasterData.addLongProperty("BillID").index();
        transferMasterData.addIntProperty("BillKind");
        transferMasterData.addIntProperty("BillState");
        transferMasterData.addStringProperty("BillCode");
        transferMasterData.addStringProperty("BillKindName");
        transferMasterData.addStringProperty("BillStateName");
        transferMasterData.addStringProperty("BillDate");
        transferMasterData.addStringProperty("CheckorCode");
        transferMasterData.addStringProperty("CheckorName");
        transferMasterData.addStringProperty("DepartmentCode");
        transferMasterData.addStringProperty("DepartmentName");
        transferMasterData.addStringProperty("EmpCode");
        transferMasterData.addStringProperty("EmpName");
        transferMasterData.addStringProperty("InStoreCode");
        transferMasterData.addStringProperty("StoreCode");
        transferMasterData.addStringProperty("InStoreName");
        transferMasterData.addStringProperty("StoreName");
        transferMasterData.addStringProperty("UpdateTime");
        transferMasterData.addStringProperty("BillTo");
        transferMasterData.addStringProperty("OpCode");
        transferMasterData.addStringProperty("OpName");
        transferMasterData.addDoubleProperty("Amount");
        transferMasterData.addLongProperty("CheckorID");
        transferMasterData.addLongProperty("DepartmentID");
        transferMasterData.addLongProperty("EmpID");
        transferMasterData.addLongProperty("InStoreID");
        transferMasterData.addLongProperty("OpID");
        transferMasterData.addLongProperty("ShipType");
        transferMasterData.addLongProperty("ShopID");
        transferMasterData.addLongProperty("StoreID");
        transferMasterData.addIntProperty("ReferCount");
        transferMasterData.addStringProperty("Remark");
        transferMasterData.addStringProperty("ShipTypeName");
        transferMasterData.addStringProperty("SFlag");
        transferMasterData.addStringProperty("UserDef1");
        transferMasterData.addStringProperty("UserDef2");
        transferMasterData.addStringProperty("UserDef3");
        transferMasterData.addStringProperty("UserDef4");
        transferMasterData.addStringProperty("UserDef5");
        transferMasterData.implementsSerializable();
//
//
//        Entity transferDBBean = schema.addEntity("TransferDBBean");
//        transferDBBean.addIdProperty().index().autoincrement();
//        transferDBBean.addToOne(transferMasterData, tsMasterID);
//        transferDBBean.addToMany(transferDetail1Data, tsDetailID);
//        transferDBBean.implementsSerializable();
//--------------------------------------------盘点单------------------------------------------------------------------
        Entity stockCheckDetail1Data = schema.addEntity("StockCheckDetail1Data");
        Property scDetailID = stockCheckDetail1Data.addIdProperty().index().autoincrement().getProperty();
        Property scMasterID = stockCheckDetail1Data.addLongProperty("MasterID").getProperty();
        stockCheckDetail1Data.addLongProperty("BillID").index();
        stockCheckDetail1Data.addLongProperty("GoodsID");
        stockCheckDetail1Data.addLongProperty("UnitID");
        stockCheckDetail1Data.addIntProperty("ItemNo");
        stockCheckDetail1Data.addIntProperty("ReferCount");
        stockCheckDetail1Data.addDoubleProperty("APrice");
        stockCheckDetail1Data.addDoubleProperty("UnitPrice");
        stockCheckDetail1Data.addDoubleProperty("UnitRate");
        stockCheckDetail1Data.addDoubleProperty("UnitQuantity");
        stockCheckDetail1Data.addDoubleProperty("UnitRealQty");
        stockCheckDetail1Data.addDoubleProperty("AccQty");
        stockCheckDetail1Data.addDoubleProperty("Amount");
        stockCheckDetail1Data.addDoubleProperty("IOQty");
        stockCheckDetail1Data.addDoubleProperty("Price");
        stockCheckDetail1Data.addDoubleProperty("Quantity");
        stockCheckDetail1Data.addStringProperty("GoodsCode");
        stockCheckDetail1Data.addStringProperty("GoodsName");
        stockCheckDetail1Data.addStringProperty("Remark");
        stockCheckDetail1Data.addStringProperty("Specs");
        stockCheckDetail1Data.addStringProperty("UnitName");
        stockCheckDetail1Data.addStringProperty("UserDef1");
        stockCheckDetail1Data.addStringProperty("UserDef2");
        stockCheckDetail1Data.addStringProperty("UserDef3");
        stockCheckDetail1Data.addStringProperty("UserDef4");
        stockCheckDetail1Data.addStringProperty("UserDef5");

        stockCheckDetail1Data.implementsSerializable();


        Entity stockCheckMasterData = schema.addEntity("StockCheckMasterData");
        stockCheckMasterData.addIdProperty().index().autoincrement().getProperty();
        stockCheckMasterData.addToMany(stockCheckDetail1Data, scMasterID);
        stockCheckMasterData.addLongProperty("BillID").index();
        stockCheckMasterData.addLongProperty("CheckorID");
        stockCheckMasterData.addLongProperty("EmpID");
        stockCheckMasterData.addLongProperty("OpID");
        stockCheckMasterData.addLongProperty("IOTypeID");
        stockCheckMasterData.addLongProperty("StoreID");
        stockCheckMasterData.addLongProperty("DepartmentID");
        stockCheckMasterData.addDoubleProperty("Amount");
        stockCheckMasterData.addStringProperty("BillCode");
        stockCheckMasterData.addStringProperty("BillDate");
        stockCheckMasterData.addStringProperty("BillStateName");
        stockCheckMasterData.addStringProperty("CheckorCode");
        stockCheckMasterData.addStringProperty("CheckorName");
        stockCheckMasterData.addStringProperty("DepartmentName");
        stockCheckMasterData.addStringProperty("DeppartmentCode");
        stockCheckMasterData.addStringProperty("EmpCode");
        stockCheckMasterData.addStringProperty("EmpName");
        stockCheckMasterData.addStringProperty("IOTypeName");
        stockCheckMasterData.addStringProperty("OpCode");
        stockCheckMasterData.addStringProperty("OpName");
        stockCheckMasterData.addStringProperty("Remark");
        stockCheckMasterData.addStringProperty("StoreName");
        stockCheckMasterData.addStringProperty("UpdateTime");
        stockCheckMasterData.addStringProperty("StoreCode");
        stockCheckMasterData.addIntProperty("BillState");
        stockCheckMasterData.addIntProperty("ReferCount");
        stockCheckMasterData.addIntProperty("SFlag");
        stockCheckMasterData.addIntProperty("UserDef1");
        stockCheckMasterData.addIntProperty("UserDef2");
        stockCheckMasterData.addIntProperty("UserDef3");
        stockCheckMasterData.addIntProperty("UserDef4");
        stockCheckMasterData.addIntProperty("UserDef5");
        stockCheckMasterData.implementsSerializable();


//        Entity stockCheckDBBean = schema.addEntity("StockCheckDBBean");
//        stockCheckDBBean.addIdProperty().index().autoincrement();
//        stockCheckDBBean.addToOne(stockCheckMasterData, scMasterID);
//        stockCheckDBBean.addToMany(stockCheckDetail1Data, scDetailID);
//        stockCheckDBBean.implementsSerializable();

        Entity sysParam = schema.addEntity("SysParam");
        sysParam.addLongProperty("ParamID");
        sysParam.addStringProperty("ParamName");
        sysParam.addStringProperty("DataValue");
        sysParam.addStringProperty("Content");
        sysParam.addStringProperty("LCode");
        sysParam.implementsSerializable();

        new DaoGenerator().generateAll(schema, "./DaoGerner/Gen");
    }
}
































