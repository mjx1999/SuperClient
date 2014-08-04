package test;

import com.google.gson.Gson;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.RespAccset;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-3.
 */
public class TestJson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Request requestParams = new Request();
        requestParams.setMethod("GetAccount");
        Params params = new Params();
        params.setClientVer("1.2");
        requestParams.setParams(params);
        String json = gson.toJson(requestParams);


        RespAccset respAccset = new RespAccset();
        respAccset.setErrNo("0");
        respAccset.setErrMessage("错误的帐号.");
        Accset accset1 = new Accset();
        accset1.setAccsetID(1);
        accset1.setAccsetCode("hehehe");
        accset1.setAccsetName("测试帐号001");

        Accset accset2 = new Accset();
        accset2.setAccsetID(2);
        accset2.setAccsetCode("ahahahaha");
        accset2.setAccsetName("ceshizhanghao002");

        ArrayList<Accset> accsets = new ArrayList<Accset>();
        accsets.add(accset1);
        accsets.add(accset2);
        respAccset.setResultData(accsets);

        String respJson = gson.toJson(respAccset);
        System.out.println(json);
        System.out.println(respJson);

        String jsonResp = "{\"ResultData\":[{\"AccsetID\":\"1\",\"AccsetCode\":\"hehehe\",\"AccsetName\":\"测试帐号001\"},{\"AccsetID\":\"2\",\"AccsetCode\":\"ahahahaha\",\"AccsetName\":\"ceshizhanghao002\"}],\"ErrNo\":\"0\",\"ErrMessage\":\"错误的帐号.\"}\n";
        System.out.println(gson.fromJson(jsonResp,RespAccset.class));


    }
}
