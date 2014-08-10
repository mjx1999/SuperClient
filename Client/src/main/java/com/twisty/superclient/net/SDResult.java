package com.twisty.superclient.net;

public class SDResult {
   public int ErrNo;
   public String ErrMsg;
   public String ErrTitle;
   public String OtherData;

   public SDResult()
   {
	   super();
	   ErrNo = 0;
	   ErrMsg = "操作成功";
	   ErrTitle = "";
	   OtherData = "";
   }
}
