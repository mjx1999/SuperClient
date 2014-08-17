package com.twisty.superclient.bean;

import java.io.Serializable;

/**
 * Created by twisty on 14-8-3.
 */
public class Request implements Serializable{
    private String Method;
    private Params Params;

    public Request() {
    }

    public Request(String method) {
        Method = method;
    }

    public Request(com.twisty.superclient.bean.Params params, String method) {
        Params = params;
        Method = method;
    }

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

    @Override
    public String toString() {
        return "Request{" +
                "Method='" + Method + '\'' +
                ", Params=" + Params +
                '}';
    }
}
