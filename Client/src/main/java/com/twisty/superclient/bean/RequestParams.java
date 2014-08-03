package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-3.
 */
public class RequestParams {
    private String Method;
    private Params Params;

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public Params getParams() {
        return Params;
    }

    public void setParams(Params params) {
        Params = params;
    }
}
