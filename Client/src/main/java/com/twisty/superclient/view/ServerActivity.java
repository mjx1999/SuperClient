package com.twisty.superclient.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.twisty.superclient.R;
import com.twisty.superclient.bean.AccsetDao;
import com.twisty.superclient.bean.AccsetResp;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.OperatorResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.TradeTypeResp;
import com.twisty.superclient.bean.TraderPriceResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.LogFactory;

public class ServerActivity extends Activity implements View.OnClickListener {
    private EditText serverName, serverPort;
    private Button onlineLogin, offlinLogin;
    private CommonLog log = LogFactory.createLog();
    private SharedPreferences sp;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if(what == RESULT_OK){
                startActivity(new Intent(ServerActivity.this, AccsetActivity.class));
                overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
            }else{
                CommonUtil.showToastError(ServerActivity.this,msg.obj.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);
        sp = PreferenceManager.getDefaultSharedPreferences(ServerActivity.this);
        AccsetDao accsetDao = SuperClient.getDaoSession(this).getAccsetDao();


//        for(int i = 0;i<10;i++){
//            Accset accset = new Accset();
//            accset.setAccsetID((long)i);
//            accset.setAccsetCode("0000"+i);
//            accset.setAccsetName("twisty");
//            accsetDao.insert(accset);
//        }

        serverName = (EditText) findViewById(R.id.serverName);
        serverPort = (EditText) findViewById(R.id.serverPort);
        serverName.setText(sp.getString("serverName", ""));
        serverPort.setText(sp.getString("serverPort", ""));
        onlineLogin = (Button) findViewById(R.id.onlineLogin);
        offlinLogin = (Button) findViewById(R.id.offlineLogin);
        onlineLogin.setOnClickListener(this);
        offlinLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onlineLogin:
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ReqClient client = ReqClient.newInstance();
                        try {
                            if (client.connectServer(serverName.getText().toString(), Integer.valueOf(serverPort.getText().toString()))) {

                                DaoSession session = SuperClient.getDaoSession(ServerActivity.this);
                                sp.edit().putString("serverName", serverName.getText().toString()).putString("serverPort", serverPort.getText().toString()).apply();
                                Request request;
                                Params params;
                                Gson gson = new Gson();
                                /**
                                 * 获取帐套列表
                                 */
                                request = new Request(GlobalConstant.METHOD_GET_ACC_LIST);
                                params = new Params();
                                params.setClientVer("1.2");
                                request.setParams(params);
                                String accListJson = client.requestData(request);
                                log.i(accListJson);
                                AccsetResp accsetResp = gson.fromJson(accListJson, AccsetResp.class);
                                log.i(accsetResp);
                                session.getAccsetDao().insertOrReplaceInTx(accsetResp.getResultData());

                                /**
                                 * 获取操作员列表
                                 */

                                request = new Request(GlobalConstant.METHOD_DOWNLOAD_OPERATOR);
                                params = new Params();
                                params.setAccsetCode(accsetResp.getResultData().get(0).getAccsetCode());
                                request.setParams(params);
                                String operatorListJson = client.requestData(request);
                                log.i(operatorListJson);
                                OperatorResp operatorResp = gson.fromJson(operatorListJson, OperatorResp.class);
                                log.i(operatorResp);
                                session.getOperatorDao().insertOrReplaceInTx(operatorResp.getResultData());

                                /**
                                 * 登录
                                 */
                                request = new Request(GlobalConstant.METHOD_LOGIN);
                                params = new Params();
                                Operator operator = operatorResp.getResultData().get(0);
                                params.setAccsetCode(accsetResp.getResultData().get(0).getAccsetCode());
                                params.setOpID(operator.getOpID());
                                params.setOpPassword("");
                                request.setParams(params);
                                String loginResult = client.requestData(request);
                                log.i(loginResult);
                                /**
                                 * 往来单位类型
                                 */
                                String traderTypeJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_TRADER_TYPE));
                                log.i(traderTypeJson);
                                TradeTypeResp tradeTypeResp = gson.fromJson(traderTypeJson, TradeTypeResp.class);
                                session.getTradeTypeDao().insertOrReplaceInTx(tradeTypeResp.getResultData());

                                /**
                                 * 往来单位
                                 */
                                //TODO * Double 需要小数位,否则报错
//                                String traderJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_TRADER));
//                                log.i(traderJson);
//                                TraderResp traderResp = gson.fromJson(traderJson, TraderResp.class);
//                                session.getTraderDao().insertOrReplaceInTx(traderResp.getResultData());
                                /**
                                 *
                                 */
                                String traderPriceJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_TRADER_PRICE));
                                log.i(traderPriceJson);
                                TraderPriceResp traderPriceResp = gson.fromJson(traderPriceJson, TraderPriceResp.class);
                                session.getTraderPriceDao().insertOrReplaceInTx(traderPriceResp.getResultData());
                                SuperClient.setIsOnline(true);
                                handler.sendEmptyMessage(RESULT_OK);
                            }else{
                                Message msg = handler.obtainMessage();
                                msg.what = RESULT_CANCELED;
                                msg.obj = "服务器连接失败,请检查IP或端口是否正确";
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            Message msg = handler.obtainMessage();
                            msg.what = RESULT_CANCELED;
                            msg.obj = "服务器连接失败,请检查IP或端口是否正确";
                            handler.sendMessage(msg);
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.offlineLogin:
                SuperClient.setIsOnline(false);
                startActivity(new Intent(this, AccsetActivity.class));
                overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
                break;
            default:
                break;
        }
    }
}
