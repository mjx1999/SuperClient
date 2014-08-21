package com.twisty.superclient.net;

import com.google.gson.Gson;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by twisty on 14-8-10.
 */
public class ReqClient {
    static Socket socket = null;
    private static ReqClient client;
    private static Gson gson;

    private ReqClient() {
    }

    public static ReqClient newInstance(){
        if(client==null){
            client = new ReqClient();
            gson = new Gson();
        }
        return client;
    }
    private CommonLog log = LogFactory.createLog();
    public boolean connectServer(String ip,int port,Request loginRequest) throws Exception {
        try {
            socket = new Socket(ip,port);
            log.i(socket);
            if(loginRequest!=null)requestData(loginRequest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public String requestData(Request request)
            throws Exception {
        PrintWriter oos = new PrintWriter(socket.getOutputStream());
        String reqString = gson.toJson(request);
        log.i(reqString);
        String asData64 = new BASE64Encoder().encodeBuffer(reqString.getBytes("gbk")) ;
        asData64 = asData64.replaceAll("\\r", "");
        asData64 = asData64.replaceAll("\\n", "");
        oos.println(asData64);
        oos.flush();
        oos = null;

        String strResponse = "";
        long iBeg = System.currentTimeMillis();


        socket.setSoTimeout(30 * 1000);
        BufferedReader ois = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        while ((strResponse == null || strResponse.isEmpty())
                && System.currentTimeMillis() - iBeg < 30 * 1000) {
            strResponse = ois.readLine();
        }
        ois = null;
        String asResult;
        if (strResponse == null || strResponse.isEmpty())
        {
            asResult = "{\"ErrNo\":-1,\"ErrMessage\":\"连接服务器超时!\"}";
        }
        else
        {
            asResult = new String((new BASE64Decoder()).decodeBuffer(strResponse),"gbk");
        }

        return asResult;
    }

    public void close() {
        if (socket != null) {
            if (socket.isConnected()) {
                try {
                    socket.close();
                } catch (Exception E) {
                }
            }
            socket = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
