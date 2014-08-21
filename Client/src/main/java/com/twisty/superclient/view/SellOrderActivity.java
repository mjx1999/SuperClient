package com.twisty.superclient.view;

import android.app.ActionBar;
import android.app.FragmentTransaction;
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
import com.twisty.superclient.bean.Detail1Data;
import com.twisty.superclient.bean.MasterData;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.SellOrderResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.filter.FilterActivity;

import java.util.ArrayList;

public class SellOrderActivity extends BaseActivity implements View.OnClickListener, ActionBar.TabListener{
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4;
    private ActionBar actionBar;
    private Button searchBTN, saveBTN;
    private boolean isAddNew = true;
    private boolean isCommit;
    private Params params;
    private MasterData masterData = new MasterData();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PRE_RESULT:
                    fragmentHeader.setMasterData(masterData);
                    break;
                case NEXT_RESULT:
                    fragmentHeader.setMasterData(masterData);
                    break;
            }
        }
    };
    private ArrayList<Detail1Data> detail1Datas = new ArrayList<Detail1Data>();
    private FragmentOrderDetail fragmentOrderDetail;
    private FragmentHeader fragmentHeader;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_order);
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

        fragmentOrderDetail = FragmentOrderDetail.newInstance();
        fragmentOrderDetail.setRetainInstance(true);
        fragmentHeader = FragmentHeader.newInstance();
        fragmentHeader.setRetainInstance(true);


        fragmentTransaction.add(R.id.sellOrder, fragmentOrderDetail, "detail").add(R.id.sellOrder, fragmentHeader, "header").commit();


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
                //TODO 前单
                isAddNew = false;
                CommonUtil.showToastInfo(SellOrderActivity.this, "前单");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ReqClient client = ReqClient.newInstance();
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        Params paramsPRE = new Params();
                        paramsPRE.setOperate("GetPriorBill");
                        paramsPRE.setBillName("s_sale");
                        paramsPRE.setBillCode(fragmentHeader.getMasterData().getBillCode());
                        request.setParams(paramsPRE);
                        try {
                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            String preBillJson = client.requestData(request);
                            log.i(preBillJson);
                            SellOrderResp sellOrderResp = gson.fromJson(preBillJson, SellOrderResp.class);
                            if (sellOrderResp.getMasterData() != null) {
                                masterData = sellOrderResp.getMasterData();
                            }
                            if (sellOrderResp.getDetail1Data() != null) {
                                detail1Datas = sellOrderResp.getDetail1Data();
                            }
                            handler.sendEmptyMessage(PRE_RESULT);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                return true;
            case R.id.nextOrder:
                //TODO 后单
                isAddNew = false;
                CommonUtil.showToastInfo(SellOrderActivity.this, "后单");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ReqClient client = ReqClient.newInstance();
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        Params paramsPRE = new Params();
                        paramsPRE.setOperate("GetNextBill");
                        paramsPRE.setBillName("s_sale");
                        paramsPRE.setBillCode(fragmentHeader.getMasterData().getBillCode());
                        request.setParams(paramsPRE);
                        try {
                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                            String nextBillJson = client.requestData(request);
                            log.i(nextBillJson);
                            SellOrderResp sellOrderResp = gson.fromJson(nextBillJson, SellOrderResp.class);
                            if (sellOrderResp.getMasterData() != null) {
                                masterData = sellOrderResp.getMasterData();
                            }
                            if (sellOrderResp.getDetail1Data() != null) {
                                detail1Datas = sellOrderResp.getDetail1Data();
                            }
                            handler.sendEmptyMessage(NEXT_RESULT);
                        } catch (Exception e) {
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
            ft.hide(fragmentHeader);
            ft.show(fragmentOrderDetail);
        } else if (tab.getText().equals("表头")) {
            ft.hide(fragmentOrderDetail);
            ft.show(fragmentHeader);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        ft.commit();
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
                FragmentHeader fragmentHeader = (FragmentHeader) getFragmentManager().findFragmentByTag("header");
                if (fragmentHeader != null) {
                    masterData = fragmentHeader.getMasterData();
                    log.i(masterData.getBillCode());
                }
                if (masterData.getTraderID() == null) {
                    CommonUtil.showToastError(this, "客户不能为空!");
                    return;
                }
                FragmentOrderDetail fragmentOrderDetail = (FragmentOrderDetail) getFragmentManager().findFragmentByTag("detail");
                if (fragmentOrderDetail != null) {
                    detail1Datas = fragmentOrderDetail.getDetail1Datas();
                    log.i(detail1Datas.size());
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                        params = new Params();
                        params.setBillName("s_sale");
                        params.setOperate("Save");
                        params.setAddnew(isAddNew);
                        params.setMasterData(masterData);
                        params.setDetail1Data(detail1Datas);
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
