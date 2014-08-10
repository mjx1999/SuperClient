package com.twisty.superclient.net;

import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import sun.misc.BASE64Decoder;     
import sun.misc.BASE64Encoder;  

public class A3Client {
	private static int waitId = 0;
	Socket socket = null;
    private CommonLog log = LogFactory.createLog();
	public boolean connectServer(String ip,int port) throws Exception {
		try {
			socket = new Socket( ip, port);
            log.i(socket);
            return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String webInvoke(String asData, int iTimeOut)
			throws Exception {
		
		PrintWriter oos = new PrintWriter(socket.getOutputStream());
        String asData64 = (new BASE64Encoder()).encodeBuffer(asData.getBytes());
		asData64 = asData64.replaceAll("\r", "");
	    asData64 = asData64.replaceAll("\n", "");
        
		oos.println(asData64);
		oos.flush();
		oos = null;

		String strResponse = "";
		long iBeg = System.currentTimeMillis();
		if (iTimeOut < 30) {
			iTimeOut = 30;
		}

		socket.setSoTimeout(iTimeOut * 1000);
		BufferedReader ois = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		while ((strResponse == null || strResponse.isEmpty())
				&& System.currentTimeMillis() - iBeg < iTimeOut * 1000) {
			strResponse = ois.readLine();
		}
		ois = null;
		String asResult;
		if (strResponse == null || strResponse.isEmpty())
		{
			asResult = "{\"ErrNo\":-1,\"ErrMessage\":\"提交数据处理超时\"}";
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

	public String getAccList() throws Exception
	{
	    String asData = "{\"Method\":\"GetAccList\",\"Params\": {\"ClientVer\":\"1.2\"}}";
	    String asResult = "";
        asResult = this.webInvoke(asData,30);
        return asResult;
	}
	
	public String downloadOperator(String accsetCode) throws Exception
	{
	    String asData = "{\"Method\":\"DownloadOperator\",\"Params\": {\"AccsetCode\":\"" + accsetCode + "\"}}";
	    String asResult = "";
        asResult = this.webInvoke(asData,30);
        return asResult;
	}
	
	public String Login(String AccsetCode,int OpID, String OpPassword,String DefaultStoreCode)
                  throws Exception 
	{
		 String asData = "{\"Method\":\"Login\",\"Params\": {"
			         + "\"AccsetCode\":\"" + AccsetCode +"\","
			         + "\"OpID\":" + OpID +","
			         + "\"OpPassword\":\"" + OpPassword +"\","
			         + "\"DefaultStoreCode\":\"" +  DefaultStoreCode +"\"}}";
         String asResult = "";
	     asResult = this.webInvoke(asData,30);
	     return asResult;
	}
	
	public String DownloadBaseData(String DataName,String ParamsJSon ) throws Exception
	{
		 if (ParamsJSon == null)
		 {
			 ParamsJSon = "{}";
		 }
		 
		 String asData = "{\"Method\":\"" + DataName+"\","
		                +" \"Params\":" + ParamsJSon
		                +" }";
         String asResult = "";
         asResult = this.webInvoke(asData,30);
         return asResult;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		close();
		super.finalize();
	}

}
