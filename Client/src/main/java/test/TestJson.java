package test;

import com.google.gson.Gson;
import com.twisty.superclient.bean.SalesBillResp;

/**
 * Created by twisty on 14-8-3.
 */
public class TestJson {
    public static void main(String[] args) {
        Gson gson = new Gson();
//        Request requestParams = new Request();
//        requestParams.setMethod("GetAccount");
//        Params params = new Params();
//        params.setClientVer("1.2");
//        requestParams.setParams(params);
//        String json = gson.toJson(requestParams);
//
//
//        AccsetResp respAccset = new AccsetResp();
//        respAccset.setErrNo(0);
//        respAccset.setErrMessage("错误的帐号.");
//        Accset accset1 = new Accset();
//        accset1.setAccsetID(1L);
//        accset1.setAccsetCode("hehehe");
//        accset1.setAccsetName("测试帐号001");
//
//        Accset accset2 = new Accset();
//        accset2.setAccsetID(2L);
//        accset2.setAccsetCode("ahahahaha");
//        accset2.setAccsetName("ceshizhanghao002");
//
//        ArrayList<Accset> accsets = new ArrayList<Accset>();
//        accsets.add(accset1);
//        accsets.add(accset2);
//        respAccset.setResultData(accsets);
//
//        String respJson = gson.toJson(respAccset);
//        System.out.println(json);
//        System.out.println(respJson);
//
//        String jsonResp = "{\"ResultData\":[{\"AccsetID\":\"1\",\"AccsetCode\":\"hehehe\",\"AccsetName\":\"测试帐号001\"},{\"AccsetID\":\"2\",\"AccsetCode\":\"ahahahaha\",\"AccsetName\":\"ceshizhanghao002\"}],\"ErrNo\":\"0\",\"ErrMessage\":\"错误的帐号.\"}\n";
//        System.out.println(gson.fromJson(jsonResp,AccsetResp.class));
//
//        Department department = new Department();
//        department.setClose(false);
//
//        System.out.println(gson.toJson(department));
//
//        String xso = "{\"DepartmentID\":0,\"Close\":\"false\"}";

//        String xx = "{\"ErrMessage\": \"\",\n" +
//                "                \"ErrNo\": 0,\n" +
//                "                \"ResultData\": [\n" +
//                "        {\n" +
//                "            \"AccTrader\": 0,\n" +
//                "                \"Address\": \"\",\n" +
//                "                \"AreaID\": 0,\n" +
//                "                \"Bank\": \"\",\n" +
//                "                \"BankAccno\": \"\",\n" +
//                "                \"Closed\": 0,\n" +
////                "                \"Contactor\": \"\",\n" +
//                "                \"Credit\": 0.0,\n" +
//                "                \"CreditDay\": 0,\n" +
//                "                \"DepartmentID\": null,\n" +
//                "                \"EMail\": \"\",\n" +
//                "                \"EmpID\": null,\n" +
//                "                \"Fax\": \"\",\n" +
//                "                \"FullName\": \"\",\n" +
//                "                \"IsClient\": 0,\n" +
//                "                \"IsVendor\": 1,\n" +
////                "                \"Legalrep\": \"\",\n" +
//                "                \"Lev\": null,\n" +
//                "                \"Phone\": \"\",\n" +
//                "                \"ShipTo\": \"\",\n" +
//                "                \"ShopID\": 0,\n" +
//                "                \"TaxNo\": \"\",\n" +
//                "                \"Tel1\": \"\",\n" +
//                "                \"Tel2\": \"\",\n" +
//                "                \"TradeTypeID\": 0,\n" +
//                "                \"TraderCode\": \"00\",\n" +
//                "                \"TraderID\": -1,\n" +
//                "                \"TraderName\": \"估价入库\",\n" +
//                "                \"Url\": \"\",\n" +
//                "                \"Zip\": \"\"\n" +
//                "        },{\n" +
//                "            \"AccTrader\": 0,\n" +
//                "                    \"Address\": \"\",\n" +
//                "                    \"AreaID\": 2,\n" +
//                "                    \"Bank\": \"工行前门支行\",\n" +
//                "                    \"BankAccno\": \"1202026119900006711\",\n" +
//                "                    \"Closed\": 0,\n" +
////                "                    \"Contactor\": \"袁萍\",\n" +
//                "                    \"Credit\": 60000.0,\n" +
//                "                    \"CreditDay\": 30,\n" +
//                "                    \"DepartmentID\": 5,\n" +
//                "                    \"EMail\": \"\",\n" +
//                "                    \"EmpID\": 23,\n" +
//                "                    \"Fax\": \"010-88920603\",\n" +
//                "                    \"FullName\": \"中通股份有限公司\",\n" +
//                "                    \"IsClient\": 1,\n" +
//                "                    \"IsVendor\": 0,\n" +
////                "                    \"Legalrep\": \"王迅\",\n" +
//                "                    \"Lev\": 5,\n" +
//                "                    \"Phone\": \"\",\n" +
//                "                    \"ShipTo\": \"\",\n" +
//                "                    \"ShopID\": 0,\n" +
//                "                    \"TaxNo\": \"130165710971234\",\n" +
//                "                    \"Tel1\": \"010-89820610\",\n" +
//                "                    \"Tel2\": \"\",\n" +
//                "                    \"TradeTypeID\": 0,\n" +
//                "                    \"TraderCode\": \"K0001\",\n" +
//                "                    \"TraderID\": 37,\n" +
//                "                    \"TraderName\": \"中通股份\",\n" +
//                "                    \"Url\": \"WWW.DCEE.COM\",\n" +
//                "                    \"Zip\": \"110005\"\n" +
//                "        }]}";
        String xx = "{\n" +
                "    \"Detail1Data\": [\n" +
                "    {\n" +
                "    \"APrice\": 140.06633333,\n" +
                "    \"Amount\": 14040.0,\n" +
                "    \"BReferID\": null,\n" +
                "    \"BarCode\": null,\n" +
                "    \"BillID\": 7,\n" +
                "    \"Disc\": 100.0,\n" +
                "    \"DiscAmt\": 0.0,\n" +
                "    \"GUserDef1\": \"\",\n" +
                "    \"GUserDef10\": \"\",\n" +
                "    \"GUserDef2\": \"\",\n" +
                "    \"GUserDef3\": \"\",\n" +
                "    \"GUserDef4\": \"\",\n" +
                "    \"GUserDef5\": \"\",\n" +
                "    \"GUserDef6\": \"\",\n" +
                "    \"GUserDef7\": \"\",\n" +
                "    \"GUserDef8\": \"\",\n" +
                "    \"GUserDef9\": \"\",\n" +
                "    \"GoodsAmt\": 12000.0,\n" +
                "    \"GoodsCode\": \"1001\",\n" +
                "    \"GoodsName\": \"DVD\",\n" +
                "    \"IOQty\": 0.0,\n" +
                "    \"IsLargess\": 0,\n" +
                "    \"ItemNO\": 2,\n" +
                "    \"OrigPrice\": 1200.0,\n" +
                "    \"OrigTaxPrice\": 1404.0,\n" +
                "    \"Quantity\": 10.0,\n" +
                "    \"ReferBillCode\": \"SD-2014-01-00001\",\n" +
                "    \"ReferBillID\": 3,\n" +
                "    \"ReferBillType\": 13,\n" +
                "    \"ReferItemNo\": 2,\n" +
                "    \"ReferQty\": 0.0,\n" +
                "    \"ShortName\": \"DVD\",\n" +
                "    \"Specs\": \"采风\",\n" +
                "    \"StoreCode\": \"01\",\n" +
                "    \"StoreID\": 1,\n" +
                "    \"StoreName\": \"成品仓\",\n" +
                "    \"TaxAmt\": 2040.0,\n" +
                "    \"TaxPrice\": 1404.0,\n" +
                "    \"TaxRate\": 17.0,\n" +
                "    \"UnitID\": 1,\n" +
                "    \"UnitName\": \"台\",\n" +
                "    \"UnitPrice\": 1200.0,\n" +
                "    \"UnitQuantity\": 10.0,\n" +
                "    \"UnitRate\": 1.0,\n" +
                "    \"UserDef1\": \"\",\n" +
                "    \"UserDef10\": \"\",\n" +
                "    \"UserDef2\": \"\",\n" +
                "    \"UserDef3\": \"\",\n" +
                "    \"UserDef4\": \"\",\n" +
                "    \"UserDef5\": \"\",\n" +
                "    \"UserDef6\": \"\",\n" +
                "    \"UserDef7\": \"\",\n" +
                "    \"UserDef8\": \"\",\n" +
                "    \"UserDef9\": \"\"\n" +
                "    }],\n" +
                "    \"ErrMessage\": \"\",\n" +
                "    \"ErrNo\": 0,\n" +
                "    \"MasterData\": {\n" +
                "    \"AccountID\": null,\n" +
                "    \"AccountName\": null,\n" +
                "    \"Amount\": 14040.0,\n" +
                "    \"BillCode\": \"SS-2014-01-00009\",\n" +
                "    \"BillDate\": \"2014-01-20 00:00:00\",\n" +
                "    \"BillID\": 7,\n" +
                "    \"BillKind\": 3,\n" +
                "    \"BillKindName\": \"代销结算\",\n" +
                "    \"BillState\": 1,\n" +
                "    \"BillStateName\": \"已审核\",\n" +
                "    \"BillTo\": \"\",\n" +
                "    \"CheckNo\": null,\n" +
                "    \"Checkor\": \"系统管理员\",\n" +
                "    \"CheckorID\": 0,\n" +
                "    \"ContactFax\": \"0432-58360440\",\n" +
                "    \"ContactPhone\": \"0432-58360440\",\n" +
                "    \"DepartmentCode\": \"05\",\n" +
                "    \"DepartmentID\": 5,\n" +
                "    \"DepartmentName\": \"销售部\",\n" +
                "    \"Disc\": 100.0,\n" +
                "    \"EmpCode\": null,\n" +
                "    \"EmpID\": null,\n" +
                "    \"EmpName\": null,\n" +
                "    \"InvoiceAmt\": null,\n" +
                "    \"IsCashTrader\": 0,\n" +
                "    \"LinkManID\": null,\n" +
                "    \"Linkman\": \"詹星\",\n" +
                "    \"NoteNo\": null,\n" +
                "    \"NoteTypeID\": 3,\n" +
                "    \"NoteTypeName\": \"增值税发票\",\n" +
                "    \"OpID\": 0,\n" +
                "    \"OpName\": \"系统管理员\",\n" +
                "    \"PayAmt\": 0.0,\n" +
                "    \"PayDate\": \"2014-05-16 00:00:00\",\n" +
                "    \"PayMethodID\": null,\n" +
                "    \"PaymethodCode\": null,\n" +
                "    \"PaymethodName\": null,\n" +
                "    \"Printed\": 0,\n" +
                "    \"ReferAmt\": 0.0,\n" +
                "    \"Remark\": null,\n" +
                "    \"SFlag\": 0,\n" +
                "    \"ScAmt\": 14040.0,\n" +
                "    \"ShipType\": null,\n" +
                "    \"ShipTypeName\": null,\n" +
                "    \"ShopID\": 0,\n" +
                "    \"TermDays\": 30,\n" +
                "    \"TraderCode\": \"K0003\",\n" +
                "    \"TraderID\": 39,\n" +
                "    \"TraderName\": \"吉林朝中\",\n" +
                "    \"UpdateTime\": \"2014-04-16 09:11:08\",\n" +
                "    \"UserDef1\": null,\n" +
                "    \"UserDef2\": null,\n" +
                "    \"UserDef3\": null,\n" +
                "    \"UserDef4\": null,\n" +
                "    \"UserDef5\": null\n" +
                "    }\n" +
                "    }";

        System.out.println(gson.fromJson(xx,SalesBillResp.class));

    }
}
