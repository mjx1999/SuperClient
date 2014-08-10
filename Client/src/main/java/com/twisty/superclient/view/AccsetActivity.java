package com.twisty.superclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.twisty.superclient.R;
import com.twisty.superclient.adapter.AccsetAdapter;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.AccsetDao;
import com.twisty.superclient.bean.AccsetResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.A3Client;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;

import java.util.List;

public class AccsetActivity extends BaseActivity {
    private AccsetDao accsetDao;
    private boolean isOnline;
    private A3Client client;
    private  String ip;
    private int port;
    private ListView listView;
    private AccsetAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if(what==RESULT_OK){
                listView.setAdapter(adapter);
            }else {
                CommonUtil.showToastError(AccsetActivity.this,msg.obj.toString());
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accset);
        isOnline = SuperClient.getIsOnline();
        accsetDao = SuperClient.getDaoSession(this).getAccsetDao();
        listView = (ListView) findViewById(R.id.listView);
        List<Accset> accsets = accsetDao.loadAll();
        if(isOnline){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 获取帐套列表
                     */
                    ReqClient client = ReqClient.newInstance();
                    Request request = new Request(GlobalConstant.METHOD_GET_ACC_LIST);
                    Params params = new Params();
                    params.setClientVer("1.2");
                    request.setParams(params);
                    String accListJson = null;
                    try {
                        accListJson = client.requestData(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.i(accListJson);
                    AccsetResp accsetResp = new Gson().fromJson(accListJson, AccsetResp.class);
                    if(accsetResp.isCorrect()){
                        adapter = new AccsetAdapter(accsetResp.getResultData(),AccsetActivity.this);
                        handler.sendEmptyMessage(RESULT_OK);
                    }else{
                        Message message = handler.obtainMessage();
                        message.what = RESULT_CANCELED;
                        message.obj = accsetResp.getErrMessage();
                        handler.sendMessage(message);
                    }
                }
            }).start();
        }else{
            adapter = new AccsetAdapter(accsets,this);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Accset accset = (Accset) parent.getItemAtPosition(position);
                Intent intent = new Intent(AccsetActivity.this,LoginActivity.class);
                intent.putExtra("Accset",accset);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
