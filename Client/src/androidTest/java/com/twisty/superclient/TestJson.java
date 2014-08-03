package com.twisty.superclient;

import com.google.gson.Gson;
import com.twisty.superclient.bean.RequestParams;

/**
 * Created by twisty on 14-8-3.
 */
public class TestJson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        RequestParams requestParams = new RequestParams();
        requestParams.setMethod("GetAccount");
        String json = gson.toJson(requestParams);
        System.out.println(json);
    }
}
