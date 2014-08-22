package com.twisty.superclient.view.salesBill;

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
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.ParamsSalesBill;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.bean.SalesBillMasterData;
import com.twisty.superclient.bean.SalesBillResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.BluetoothListActivity;
import com.twisty.superclient.view.filter.FilterActivity;

import java.util.ArrayList;

public class SalesBillActivity extends BaseActivity implements View.OnClickListener, ActionBar.TabListener{
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4;
    private ActionBar actionBar;
    private Button searchBTN, saveBTN;
    private boolean isAddNew = true;
    private boolean isCommit;
    private ParamsSalesBill params;
    private SalesBillMasterData salesBillMasterData = new SalesBillMasterData();
    private ProgressDialog pd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(pd!=null)pd.dismiss();
            switch (msg.what) {
                case PRE_RESULT:
                    fragmentSalesBIllHeader.setSalesBillMasterData(salesBillMasterData);
                    fragmentSalesBillDetail.setSalesBillDetail1Datas(salesBillDetail1Datas);
                    if(msg.obj!=null){
                        CommonUtil.showToastError(SalesBillActivity.this,String.valueOf(msg.obj),null);
                    }
                    break;
                case NEXT_RESULT:
                    fragmentSalesBIllHeader.setSalesBillMasterData(salesBillMasterData);
                    fragmentSalesBillDetail.setSalesBillDetail1Datas(salesBillDetail1Datas);
                    if(msg.obj!=null){
                        CommonUtil.showToastError(SalesBillActivity.this,String.valueOf(msg.obj),null);
                    }
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(SalesBillActivity.this, String.valueOf(msg.obj),null);
                    break;
            }
        }
    };
    private ArrayList<SalesBillDetail1Data> salesBillDetail1Datas = new ArrayList<SalesBillDetail1Data>();
    private FragmentSalesBillDetail fragmentSalesBillDetail;
    private FragmentSalesBIllHeader fragmentSalesBIllHeader;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_bill);
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

        fragmentSalesBillDetail = FragmentSalesBillDetail.newInstance();
        fragmentSalesBillDetail.setRetainInstance(true);
        fragmentSalesBIllHeader = FragmentSalesBIllHeader.newInstance();
        fragmentSalesBIllHeader.setRetainInstance(true);


        fragmentTransaction.add(R.id.fragment_content, fragmentSalesBillDetail, "detail").add(R.id.fragment_content, fragmentSalesBIllHeader, "header").commit();


        actionBar.addTab(headerTab1);
        actionBar.addTab(orderDetailTab);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_module, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.print:
                Intent intent = new Intent(this, BluetoothListActivity.class);
                startActivity(intent);
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
                        paramsPRE.setBillName("s_sale");
                        paramsPRE.setBillCode(fragmentSalesBIllHeader.getSalesBillMasterData().getBillCode());
                        request.setParams(paramsPRE);
                        try {
                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            String preBillJson = client.requestData(request);
                            log.i(preBillJson);
                            SalesBillResp salesBillResp = gson.fromJson(preBillJson, SalesBillResp.class);
                            Message message = handler.obtainMessage();
                            if(salesBillResp!=null){
                                if(salesBillResp.isCorrect()){
                                    if (salesBillResp.getMasterData() != null) {
                                        salesBillMasterData = salesBillResp.getMasterData();
                                    }
                                    if (salesBillResp.getDetail1Data() != null) {
                                        salesBillDetail1Datas = salesBillResp.getDetail1Data();
                                    }
                                }else{
                                    message.obj = salesBillResp.getErrMessage();
                                }
                            }else{
                                message.obj = "服务器错误";
                            }
                            message.what = PRE_RESULT;
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
                        paramsPRE.setBillName("s_sale");
                        paramsPRE.setBillCode(fragmentSalesBIllHeader.getSalesBillMasterData().getBillCode());
                        request.setParams(paramsPRE);
                        try {
                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            String nextBillJson = client.requestData(request);
                            log.i(nextBillJson);
                            SalesBillResp salesBillResp = gson.fromJson(nextBillJson, SalesBillResp.class);
                            Message message = handler.obtainMessage();
                            if(salesBillResp!=null){
                                if(salesBillResp.isCorrect()){
                                    if (salesBillResp.getMasterData() != null) {
                                        salesBillMasterData = salesBillResp.getMasterData();
                                    }
                                    if (salesBillResp.getDetail1Data() != null) {
                                        salesBillDetail1Datas = salesBillResp.getDetail1Data();
                                    }
                                }else{
                                    log.i(salesBillResp.getErrMessage());
                                    message.obj = salesBillResp.getErrMessage();
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (tab.getText().equals("明细")) {
            ft.hide(fragmentSalesBIllHeader);
            ft.show(fragmentSalesBillDetail);
        } else if (tab.getText().equals("表头")) {
            ft.hide(fragmentSalesBillDetail);
            ft.show(fragmentSalesBIllHeader);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        log.i(tab.getText());

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.search:
                Intent intent = new Intent(this, FilterActivity.class);
                intent.putExtra("BillType", "s_sale");
                intent.putExtra("BillKind", 1);
                startActivity(intent);
                break;
            case R.id.save:
                FragmentSalesBIllHeader fragmentSalesBIllHeader = (FragmentSalesBIllHeader) getFragmentManager().findFragmentByTag("header");
                if (fragmentSalesBIllHeader != null) {
                    salesBillMasterData = fragmentSalesBIllHeader.getSalesBillMasterData();
                    log.i(salesBillMasterData.getBillCode());
                }
                if (salesBillMasterData.getTraderID() == null) {
                    CommonUtil.showToastError(this, "客户不能为空!",null);
                    return;
                }
                FragmentSalesBillDetail fragmentSalesBillDetail = (FragmentSalesBillDetail) getFragmentManager().findFragmentByTag("detail");
                if (fragmentSalesBillDetail != null) {
                    if(fragmentSalesBillDetail.getSalesBillDetail1Datas()!=null){
                        salesBillDetail1Datas = fragmentSalesBillDetail.getSalesBillDetail1Datas();
                    }
                    if(salesBillDetail1Datas !=null){
                        log.i(salesBillDetail1Datas.size());
                    }
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        params = new ParamsSalesBill();
                        params.setBillName("s_sale");
                        params.setOperate("Save");
                        params.setAddnew(isAddNew);
                        params.setMasterData(salesBillMasterData);
                        params.setDetail1Data(salesBillDetail1Datas);
                        request.setParams(params);
                        ReqClient client = ReqClient.newInstance();
                        try {
                            boolean isSuccess = client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            if (isSuccess) {
                                CommonUtil.getGson().toJson(request);
                                log.i(SuperClient.getCurrentIP());
                                log.i(SuperClient.getCurrentPort());
                                log.i(SuperClient.getCurrentLoginRequest());
                                String saveJson = client.requestData(request);
                                log.i(saveJson);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            client.close();
                        }
                    }
                }).start();
                break;
        }

    }

}
