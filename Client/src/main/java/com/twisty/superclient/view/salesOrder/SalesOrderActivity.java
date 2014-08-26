package com.twisty.superclient.view.salesOrder;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.twisty.superclient.R;
import com.twisty.superclient.bean.BillSaveResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.ParamsSalesOrder;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.bean.SalesOrderMasterData;
import com.twisty.superclient.bean.SalesOrderResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;

public class SalesOrderActivity extends BaseActivity implements View.OnClickListener, ActionBar.TabListener {
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4;
    FragmentSalesOrderHeader fragmentSalesOrderHeader;
    FragmentSalesOrderDetail fragmentSalesOrderDetail;
    ParamsSalesOrder paramsSalesOrder;
    private SalesOrderMasterData salesOrderMasterData;
    private ArrayList<SalesOrderDetail1Data> salesOrderDetail1Datas;
    private ActionBar actionBar;
    private Button searchBTN, saveBTN;
    private boolean isAddNew = true;
    private boolean isCommit;
    private ProgressDialog pd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            switch (msg.what) {
                case PRE_RESULT:
                    fragmentSalesOrderHeader.setMasterData(salesOrderMasterData);
                    fragmentSalesOrderDetail.setDetail1Data(salesOrderDetail1Datas);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case NEXT_RESULT:
                    fragmentSalesOrderHeader.setMasterData(salesOrderMasterData);
                    fragmentSalesOrderDetail.setDetail1Data(salesOrderDetail1Datas);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case RESULT_OK:
                    CommonUtil.showToastInfo(SalesOrderActivity.this,"保存成功!",null);
                    isCommit = true;
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj), null);
                    break;
            }
        }
    };
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        gson = CommonUtil.getGson();
        searchBTN = (Button) findViewById(R.id.search);
        saveBTN = (Button) findViewById(R.id.save);
        searchBTN.setOnClickListener(this);
        saveBTN.setOnClickListener(this);
        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab headerTab1 = actionBar.newTab().setText("表头").setTabListener(this);
        ActionBar.Tab orderDetailTab = actionBar.newTab().setText("明细").setTabListener(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentSalesOrderDetail = new FragmentSalesOrderDetail();
        fragmentSalesOrderDetail.setRetainInstance(true);
        fragmentSalesOrderHeader = new FragmentSalesOrderHeader();
        fragmentSalesOrderHeader.setRetainInstance(true);


        fragmentTransaction.add(R.id.fragment_content, fragmentSalesOrderDetail, "detail").add(R.id.fragment_content, fragmentSalesOrderHeader, "header").commit();


        actionBar.addTab(headerTab1);
        actionBar.addTab(orderDetailTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bill_actionbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.print:

                return true;
            case R.id.preOrder:
                isAddNew = false;
                pd = ProgressDialog.show(this,null,"正在加载数据...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ReqClient client = ReqClient.newInstance();
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        Params paramsPRE = new Params();
                        paramsPRE.setOperate("GetPriorBill");
                        paramsPRE.setBillName("s_sorder");
                        paramsPRE.setBillCode(fragmentSalesOrderHeader.getMasterData().getBillCode());
                        request.setParams(paramsPRE);
                        try {
                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            String preBillJson = client.requestData(request);
                            log.i(preBillJson);
                            SalesOrderResp salesOrderResp = gson.fromJson(preBillJson, SalesOrderResp.class);
                            Message message = handler.obtainMessage();
                            if (salesOrderResp != null) {
                                if (salesOrderResp.isCorrect()) {
                                    if (salesOrderResp.getMasterData() != null) {
                                        salesOrderMasterData = salesOrderResp.getMasterData();
                                    }
                                    if (salesOrderResp.getDetail1Data() != null) {
                                        salesOrderDetail1Datas = salesOrderResp.getDetail1Data();
                                    }
                                } else {
                                    message.obj = salesOrderResp.getErrMessage();
                                }
                            } else {
                                message.obj = "服务器错误";
                            }
                            message.what = PRE_RESULT;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            Message message = handler.obtainMessage();
                            message.what = RESULT_CANCELED;
                            message.obj = "加载数据失败,请重试...";
                            handler.sendMessage(message);
                            e.printStackTrace();
                        } finally {
                            client.close();
                        }
                    }
                }).start();


                return true;
            case R.id.nextOrder:
                isAddNew = false;
                pd = ProgressDialog.show(this,null,"正在加载数据...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ReqClient client = ReqClient.newInstance();
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        Params paramsPRE = new Params();
                        paramsPRE.setOperate("GetNextBill");
                        paramsPRE.setBillName("s_sorder");
                        paramsPRE.setBillCode(fragmentSalesOrderHeader.getMasterData().getBillCode());
                        request.setParams(paramsPRE);
                        try {
                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            String nextBillJson = client.requestData(request);
                            log.i(nextBillJson);
                            SalesOrderResp salesOrderResp = gson.fromJson(nextBillJson, SalesOrderResp.class);
                            Message message = handler.obtainMessage();
                            if(salesOrderResp!=null){
                                if(salesOrderResp.isCorrect()){
                                    if (salesOrderResp.getMasterData() != null) {
                                        salesOrderMasterData = salesOrderResp.getMasterData();
                                    }
                                    if (salesOrderResp.getDetail1Data() != null) {
                                        salesOrderDetail1Datas = salesOrderResp.getDetail1Data();
                                    }
                                }else{
                                    log.i(salesOrderResp.getErrMessage());
                                    message.obj = salesOrderResp.getErrMessage();
                                }
                            }else{
                                message.obj = "服务器错误";
                            }
                            message.what=NEXT_RESULT;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            Message message = handler.obtainMessage();
                            message.what = RESULT_CANCELED;
                            message.obj="加载数据失败,请重试...";
                            handler.sendMessage(message);
                            e.printStackTrace();
                        }
                    }
                }).start();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SalesOrderFilterActivity.class);
                intent.putExtra("BillType", "s_sorder");
//                intent.putExtra("BillKind", 1);
                startActivity(intent);
                break;
            case R.id.save:
                if(fragmentSalesOrderHeader.getMasterData()!=null){
                    salesOrderMasterData = fragmentSalesOrderHeader.getMasterData();
                }
                if (salesOrderMasterData.getTraderId() == null) {
                    CommonUtil.showToastError(this, "客户不能为空!",null);
                    return;
                }
                if(fragmentSalesOrderDetail.getDetail1Data()!=null){
                    salesOrderDetail1Datas = fragmentSalesOrderDetail.getDetail1Data();
                }
                pd = ProgressDialog.show(this,null,"正在保存客户订单.");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        ParamsSalesOrder params = new ParamsSalesOrder();
                        params.setBillName("s_sorder");
                        params.setOperate("Save");
                        params.setAddnew(isAddNew);
                        params.setMasterData(salesOrderMasterData);
                        params.setDetail1Data(salesOrderDetail1Datas);
                        request.setParams(params);
                        ReqClient client = ReqClient.newInstance();
                        Message message = handler.obtainMessage();
                        try {
                            boolean isSuccess = client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            if (isSuccess) {
                                String saveJson = client.requestData(request);
                                log.i(saveJson);
                                BillSaveResp billSaveResp = gson.fromJson(saveJson,BillSaveResp.class);
                                if(billSaveResp!=null){
                                    if(billSaveResp.isCorrect()){
                                        message.what = RESULT_OK;
                                    }else{
                                        message.what = RESULT_CANCELED;
                                        message.obj = billSaveResp.getErrMessage();
                                    }
                                }else{
                                    message.what = RESULT_CANCELED;
                                    message.obj="保存失败.";
                                }
                            }
                        } catch (Exception e) {
                            message.what = RESULT_CANCELED;
                            message.obj="保存失败.";
                            e.printStackTrace();
                        } finally {
                            handler.sendMessage(message);
                            client.close();
                        }
                    }
                }).start();
                break;
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        if (tab.getText().equals("明细")) {
            ft.hide(fragmentSalesOrderHeader);
            ft.show(fragmentSalesOrderDetail);
        } else if (tab.getText().equals("表头")) {
            ft.hide(fragmentSalesOrderDetail);
            ft.show(fragmentSalesOrderHeader);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
