package com.twisty.superclient.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.BillAdapter;
import com.twisty.superclient.bean.Bill;
import com.twisty.superclient.bean.BillListResp;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;

import java.util.List;

public class OrderListActivity extends BaseActivity {
    private ListView listView;
    private BillAdapter adapter;
    private List<Bill> adapterData;
    private ProgressDialog pd;
    private Request request;
    private ReqClient client;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            if(adapter==null){
                adapter = new BillAdapter(OrderListActivity.this,adapterData);
                listView.setAdapter(adapter);
            }else{
                adapter.setData(adapterData);
                adapter.notifyDataSetChanged();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        listView = (ListView) findViewById(R.id.listView);
        if(pd==null)pd = ProgressDialog.show(this, null, "正在检索数据");
        client = ReqClient.newInstance();
        request = (Request) getIntent().getSerializableExtra("Request");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connectServer(SuperClient.getCurrentIP(),SuperClient.getCurrentPort(),SuperClient.getCurrentLoginRequest());
                    String billListJson = client.requestData(request);
                    log.i(billListJson);
                    BillListResp billListResp = CommonUtil.getGson().fromJson(billListJson,BillListResp.class);
                    if(billListResp!=null){
                        if(billListResp.isCorrect()){
                            if(adapter==null){
                                adapterData = billListResp.getListData();
                            }else{
                                adapterData.addAll(billListResp.getListData());
                            }
                            handler.sendEmptyMessage(RESULT_OK);
                            billListResp.catchEmptyData(OrderListActivity.this);
                        }else billListResp.catchException(OrderListActivity.this);
                        handler.sendEmptyMessage(RESULT_OK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }finally {
                    client.close();
                }
            }
        }).start();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView)parent).setItemChecked(position,true);
            }
        });
    }

}
