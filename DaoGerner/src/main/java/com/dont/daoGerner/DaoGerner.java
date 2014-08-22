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
        trader.addStringProperty("Lev");
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
        goodsUnit.addFloatProperty("Rate");
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





        //销售单
        Entity salesBillMasterData = schema.addEntity("SalesBillMasterData");
        salesBillMasterData.addLongProperty("BillID").index();
        salesBillMasterData.addIntProperty("BillKind");
        salesBillMasterData.addIntProperty("BillState");
        salesBillMasterData.addStringProperty("BillCode");
        salesBillMasterData.addStringProperty("BillDate");
        salesBillMasterData.addStringProperty("BillTo");
        salesBillMasterData.addLongProperty("AccountID");
        salesBillMasterData.addDoubleProperty("Amount");
        salesBillMasterData.addStringProperty("CheckNo");
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
        salesBillMasterData.addStringProperty("Remark");
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



        Entity salesBillDetail1Data = schema.addEntity("SalesBillDetail1Data");

        salesBillDetail1Data.addLongProperty("BillID").index();
        salesBillDetail1Data.addIntProperty("ItemNO");
        salesBillDetail1Data.addDoubleProperty("APrice");
        salesBillDetail1Data.addDoubleProperty("Amount");
        salesBillDetail1Data.addLongProperty("BReferID");
        salesBillDetail1Data.addDoubleProperty("Disc");
        salesBillDetail1Data.addDoubleProperty("DiscAmt");
        salesBillDetail1Data.addLongProperty("GoodsID");
        salesBillDetail1Data.addDoubleProperty("GoodsAmt");
        salesBillDetail1Data.addIntProperty("IsLargess");
        salesBillDetail1Data.addDoubleProperty("OrigPrice");
        salesBillDetail1Data.addDoubleProperty("OrigTaxPrice");
        salesBillDetail1Data.addDoubleProperty("Quantity");
        salesBillDetail1Data.addStringProperty("ReferBillCode");
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



        Entity salesOrderMasterData = schema.addEntity("SalesOrderMasterData");
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




        Entity salesOrderDetail1Data = schema.addEntity("SalesOrderDetail1Data");
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






        new DaoGenerator().generateAll(schema,"./DaoGerner/Gen");
    }
}
































