package com.twisty.superclient.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.twisty.superclient.R;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.LogFactory;
import com.umeng.analytics.MobclickAgent;

public class ServerActivity extends Activity implements View.OnClickListener {
    private EditText serverName, serverPort;
    private Button onlineLogin, offlinLogin;
    private CommonLog log = LogFactory.createLog();
    private SharedPreferences sp;
    private ProgressDialog pd;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(pd!=null)pd.dismiss();
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
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);
        sp = PreferenceManager.getDefaultSharedPreferences(ServerActivity.this);

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
                pd = ProgressDialog.show(this,null,"正在连接服务器");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ReqClient client = ReqClient.newInstance();
                        try {
                            if (client.connectServer(serverName.getText().toString(), Integer.valueOf(serverPort.getText().toString()),null)) {

//                                DaoSession session = SuperClient.getDaoSession(ServerActivity.this);
//                                Request request;
//                                Params params;
//                                Gson gson = new Gson();
//                                /**
//                                 * 获取帐套列表
//                                 */
//                                request = new Request(GlobalConstant.METHOD_GET_ACC_LIST);
//                                params = new Params();
//                                params.setClientVer("1.2");
//                                request.setParams(params);
//                                String accListJson = client.requestData(request);
//                                log.i(accListJson);
//                                AccsetResp accsetResp = gson.fromJson(accListJson, AccsetResp.class);
//                                log.i(accsetResp);
//                                session.getAccsetDao().insertOrReplaceInTx(accsetResp.getResultData());



                                sp.edit().putString("serverName", serverName.getText().toString()).putString("serverPort", serverPort.getText().toString()).apply();
                                SuperClient.setCurrentIP(serverName.getText().toString().trim());
                                SuperClient.setCurrentPort(Integer.valueOf(serverPort.getText().toString()));
                                SuperClient.setIsOnline(true);
                                handler.sendEmptyMessage(RESULT_OK);
                            }else{
                                log.i("vvvvvvvvvvvvvvvvvvvvvvvv");
                                Message msg = handler.obtainMessage();
                                msg.what = RESULT_CANCELED;
                                msg.obj = "服务器连接失败,请检查IP或端口是否正确";
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message msg = handler.obtainMessage();
                            msg.what = RESULT_CANCELED;
                            msg.obj = "服务器连接失败,请检查IP或端口是否正确";
                            handler.sendMessage(msg);
                        }finally {
                            client.close();
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
