package com.twisty.superclient.view.salesBill;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.SalesBillAdapter;
import com.twisty.superclient.bean.SalesBill;
import com.twisty.superclient.bean.SalesBillListResp;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;

import java.util.List;

public class SalesBillListActivity extends BaseActivity {
    private ListView listView;
    private SalesBillAdapter adapter;
    private List<SalesBill> adapterData;
    private ProgressDialog pd;
    private Request request;
    private ReqClient client;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            if(adapter==null){
                adapter = new SalesBillAdapter(SalesBillListActivity.this,adapterData);
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
        setContentView(R.layout.activity_sales_bill_list);
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
                    SalesBillListResp salesBillListResp = CommonUtil.getGson().fromJson(billListJson,SalesBillListResp.class);
                    if(salesBillListResp !=null){
                        if(salesBillListResp.isCorrect()){
                            if(adapter==null){
                                adapterData = salesBillListResp.getListData();
                            }else{
                                adapterData.addAll(salesBillListResp.getListData());
                            }
                            handler.sendEmptyMessage(RESULT_OK);
                        }else{
                            CommonUtil.showToastError(SalesBillListActivity.this, salesBillListResp.getErrMessage(),null);
                        }
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
