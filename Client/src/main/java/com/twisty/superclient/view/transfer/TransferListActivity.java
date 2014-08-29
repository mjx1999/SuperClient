package com.twisty.superclient.view.transfer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.TransferAdapter;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Transfer;
import com.twisty.superclient.bean.TransferListResp;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;

public class TransferListActivity extends BaseActivity {
    private ListView listView;
    private TransferAdapter adapter;
    private ArrayList<Transfer> adapterData;
    private ProgressDialog pd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            if (adapter == null) {
                adapter = new TransferAdapter(TransferListActivity.this, adapterData);
                listView.setAdapter(adapter);
            } else {
                adapter.setData(adapterData);
                adapter.notifyDataSetChanged();
            }
        }
    };
    private Request request;
    private ReqClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_list);
        listView = (ListView) findViewById(R.id.listView);
        client = ReqClient.newInstance();
        request = (Request) getIntent().getSerializableExtra("Request");
        pd = ProgressDialog.show(this, null, "正在加载数据...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                    String transferList = client.requestData(request);
                    log.i(transferList);
                    TransferListResp transferListResp = CommonUtil.getGson().fromJson(transferList, TransferListResp.class);
                    if (transferListResp != null) {
                        if (transferListResp.isCorrect()) {
                            if (adapter == null) {
                                adapterData = transferListResp.getListData();
                            } else {
                                adapterData.addAll(transferListResp.getListData());
                            }
                            handler.sendEmptyMessage(RESULT_OK);
                        } else {
                            CommonUtil.showToastError(TransferListActivity.this, transferListResp.getErrMessage(), null);
                        }
                        handler.sendEmptyMessage(RESULT_OK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    client.close();
                }
            }
        }).start();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView) parent).setItemChecked(position, true);
            }
        });
    }
}
