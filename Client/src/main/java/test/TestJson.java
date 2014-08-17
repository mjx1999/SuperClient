package test;

import com.google.gson.Gson;
import com.twisty.superclient.bean.TraderResp;

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

        String xx = "{\"ErrMessage\": \"\",\n" +
                "                \"ErrNo\": 0,\n" +
                "                \"ResultData\": [\n" +
                "        {\n" +
                "            \"AccTrader\": 0,\n" +
                "                \"Address\": \"\",\n" +
                "                \"AreaID\": 0,\n" +
                "                \"Bank\": \"\",\n" +
                "                \"BankAccno\": \"\",\n" +
                "                \"Closed\": 0,\n" +
//                "                \"Contactor\": \"\",\n" +
                "                \"Credit\": 0.0,\n" +
                "                \"CreditDay\": 0,\n" +
                "                \"DepartmentID\": null,\n" +
                "                \"EMail\": \"\",\n" +
                "                \"EmpID\": null,\n" +
                "                \"Fax\": \"\",\n" +
                "                \"FullName\": \"\",\n" +
                "                \"IsClient\": 0,\n" +
                "                \"IsVendor\": 1,\n" +
//                "                \"Legalrep\": \"\",\n" +
                "                \"Lev\": null,\n" +
                "                \"Phone\": \"\",\n" +
                "                \"ShipTo\": \"\",\n" +
                "                \"ShopID\": 0,\n" +
                "                \"TaxNo\": \"\",\n" +
                "                \"Tel1\": \"\",\n" +
                "                \"Tel2\": \"\",\n" +
                "                \"TradeTypeID\": 0,\n" +
                "                \"TraderCode\": \"00\",\n" +
                "                \"TraderID\": -1,\n" +
                "                \"TraderName\": \"估价入库\",\n" +
                "                \"Url\": \"\",\n" +
                "                \"Zip\": \"\"\n" +
                "        },{\n" +
                "            \"AccTrader\": 0,\n" +
                "                    \"Address\": \"\",\n" +
                "                    \"AreaID\": 2,\n" +
                "                    \"Bank\": \"工行前门支行\",\n" +
                "                    \"BankAccno\": \"1202026119900006711\",\n" +
                "                    \"Closed\": 0,\n" +
//                "                    \"Contactor\": \"袁萍\",\n" +
                "                    \"Credit\": 60000.0,\n" +
                "                    \"CreditDay\": 30,\n" +
                "                    \"DepartmentID\": 5,\n" +
                "                    \"EMail\": \"\",\n" +
                "                    \"EmpID\": 23,\n" +
                "                    \"Fax\": \"010-88920603\",\n" +
                "                    \"FullName\": \"中通股份有限公司\",\n" +
                "                    \"IsClient\": 1,\n" +
                "                    \"IsVendor\": 0,\n" +
//                "                    \"Legalrep\": \"王迅\",\n" +
                "                    \"Lev\": 5,\n" +
                "                    \"Phone\": \"\",\n" +
                "                    \"ShipTo\": \"\",\n" +
                "                    \"ShopID\": 0,\n" +
                "                    \"TaxNo\": \"130165710971234\",\n" +
                "                    \"Tel1\": \"010-89820610\",\n" +
                "                    \"Tel2\": \"\",\n" +
                "                    \"TradeTypeID\": 0,\n" +
                "                    \"TraderCode\": \"K0001\",\n" +
                "                    \"TraderID\": 37,\n" +
                "                    \"TraderName\": \"中通股份\",\n" +
                "                    \"Url\": \"WWW.DCEE.COM\",\n" +
                "                    \"Zip\": \"110005\"\n" +
                "        }]}";


        System.out.println(gson.fromJson(xx,TraderResp.class));

    }
}
