package com.twisty.superclient.view.salesOrder;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.twisty.superclient.bean.Response;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.bean.SalesOrderDetail1DataDao;
import com.twisty.superclient.bean.SalesOrderMasterData;
import com.twisty.superclient.bean.SalesOrderMasterDataDao;
import com.twisty.superclient.bean.SalesOrderResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;

public class SalesOrderActivity extends BaseActivity implements View.OnClickListener, ActionBar.TabListener {
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4, DELETE_RESULT = 5, DB_UPDATE = 6;
    FragmentSalesOrderHeader fragmentSalesOrderHeader;
    FragmentSalesOrderDetail fragmentSalesOrderDetail;
    ParamsSalesOrder paramsSalesOrder;
    SalesOrderMasterDataDao salesOrderMasterDataDao;
    SalesOrderDetail1DataDao salesOrderDetail1DataDao;
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
                    CommonUtil.showToastInfo(SalesOrderActivity.this, "保存成功!", null);
                    isCommit = true;
                    break;
                case DELETE_RESULT:
                    fragmentSalesOrderHeader.setMasterData(null);
                    fragmentSalesOrderDetail.setDetail1Data(null);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case DB_UPDATE:
                    CommonUtil.showToastInfo(SalesOrderActivity.this, "当前离线模式,数据保存在本地,为了数据安全,请及时联网上传到服务器!", null);
                    isCommit = true;
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj), null);
                    break;
            }
        }
    };
    private Gson gson;
    private int from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        salesOrderMasterDataDao = SuperClient.getDaoSession(this).getSalesOrderMasterDataDao();
        salesOrderDetail1DataDao = SuperClient.getDaoSession(this).getSalesOrderDetail1DataDao();
        from = getIntent().getIntExtra("From", -1);
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (from != -1) {
            if (from == GlobalConstant.FROM_DB) {
                salesOrderMasterData = (SalesOrderMasterData) getIntent().getSerializableExtra("MasterData");
                salesOrderDetail1Datas = (ArrayList<SalesOrderDetail1Data>) getIntent().getSerializableExtra("DetailData");
                fragmentSalesOrderHeader.setMasterData(salesOrderMasterData);
                fragmentSalesOrderDetail.setDetail1Data(salesOrderDetail1Datas);
            } else if (from == GlobalConstant.FROM_LIST) {
                //TODO 从列表进来
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (from == GlobalConstant.FROM_DB) {

        } else if (from == GlobalConstant.FROM_LIST) {

        } else if (from == GlobalConstant.FROM_NEW) {
            getMenuInflater().inflate(R.menu.bill_actionbar, menu);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.print:

                return true;
            case R.id.delete:
                if (SuperClient.getIsOnline()) {

                    new AlertDialog.Builder(this).setMessage("确定要删除吗?").setPositiveButton("确定删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pd = ProgressDialog.show(SalesOrderActivity.this, null, "正在删除...");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ReqClient client = ReqClient.newInstance();
                                    Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                                    Params paramsDel = new Params();
                                    paramsDel.setOperate("Delete");
                                    paramsDel.setBillName("s_sorder");
                                    paramsDel.setBillID(fragmentSalesOrderHeader.getMasterData().getBillID());
                                    request.setParams(paramsDel);
                                    Message message = handler.obtainMessage();
                                    try {
                                        if (client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest())) {
                                            String delJson = client.requestData(request);
                                            log.i(delJson);
                                            Response response = gson.fromJson(delJson, Response.class);
                                            if (response.isCorrect()) {
                                                message.obj = "删除成功!";
                                                message.what = DELETE_RESULT;
                                            } else {
                                                message.what = RESULT_CANCELED;
                                                message.obj = response.getErrMessage();
                                            }
                                        } else {
                                            message.what = RESULT_CANCELED;
                                            message.obj = "连接服务器超时!";
                                        }
                                    } catch (Exception e) {
                                        message.what = RESULT_CANCELED;
                                        message.obj = "连接服务器超时!";
                                        e.printStackTrace();
                                    } finally {
                                        handler.sendMessage(message);
                                        client.close();
                                    }
                                }
                            }).start();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

                } else {
                    if (from == GlobalConstant.FROM_DB) {
                        salesOrderMasterDataDao.delete(salesOrderMasterData);
                        salesOrderDetail1DataDao.deleteInTx(salesOrderDetail1Datas);
                    } else {
                        CommonUtil.showToastError(SalesOrderActivity.this, "当前离线模式不能删除单据!", null);
                    }
                }
                return true;
            case R.id.preOrder:
                isAddNew = false;
                pd = ProgressDialog.show(this, null, "正在加载数据...");
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
                pd = ProgressDialog.show(this, null, "正在加载数据...");
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
                            if (salesOrderResp != null) {
                                if (salesOrderResp.isCorrect()) {
                                    if (salesOrderResp.getMasterData() != null) {
                                        salesOrderMasterData = salesOrderResp.getMasterData();
                                    }
                                    if (salesOrderResp.getDetail1Data() != null) {
                                        salesOrderDetail1Datas = salesOrderResp.getDetail1Data();
                                    }
                                } else {
                                    log.i(salesOrderResp.getErrMessage());
                                    message.obj = salesOrderResp.getErrMessage();
                                }
                            } else {
                                message.obj = "服务器错误";
                            }
                            message.what = NEXT_RESULT;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            Message message = handler.obtainMessage();
                            message.what = RESULT_CANCELED;
                            message.obj = "加载数据失败,请重试...";
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
                if (fragmentSalesOrderHeader.getMasterData() != null) {
                    salesOrderMasterData = fragmentSalesOrderHeader.getMasterData();
                }
                if (salesOrderMasterData.getTraderId() == null) {
                    CommonUtil.showToastError(this, "客户不能为空!", null);
                    return;
                }
                if (fragmentSalesOrderDetail.getDetail1Data() != null) {
                    salesOrderDetail1Datas = fragmentSalesOrderDetail.getDetail1Data();
                }
                pd = ProgressDialog.show(this, null, "正在保存客户订单.");
                final Message message = handler.obtainMessage();
                if (SuperClient.getIsOnline()) {


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
                            try {
                                boolean isSuccess = client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                if (isSuccess) {
                                    String saveJson = client.requestData(request);
                                    log.i(saveJson);
                                    BillSaveResp billSaveResp = gson.fromJson(saveJson, BillSaveResp.class);
                                    if (billSaveResp != null) {
                                        if (billSaveResp.isCorrect()) {
                                            message.what = RESULT_OK;
                                        } else {
                                            message.what = RESULT_CANCELED;
                                            message.obj = billSaveResp.getErrMessage();
                                        }
                                    } else {
                                        message.what = RESULT_CANCELED;
                                        message.obj = "保存失败.";
                                    }
                                }
                            } catch (Exception e) {
                                message.what = RESULT_CANCELED;
                                message.obj = "保存失败.";
                                e.printStackTrace();
                            } finally {
                                handler.sendMessage(message);
                                client.close();
                            }
                        }
                    }).start();
                } else {

                    if (from == GlobalConstant.FROM_DB) {
                        salesOrderMasterDataDao.update(salesOrderMasterData);
                        for (SalesOrderDetail1Data detail1Data : salesOrderDetail1Datas) {
                            detail1Data.setMasterID(salesOrderMasterData.getId());
                            salesOrderDetail1DataDao.insertOrReplace(detail1Data);
                        }
                    } else {

                        long masterID = salesOrderMasterDataDao.insert(salesOrderMasterData);
                        for (SalesOrderDetail1Data detail1Data : salesOrderDetail1Datas) {
                            detail1Data.setMasterID(masterID);
                            salesOrderDetail1DataDao.insert(detail1Data);
                        }
                    }

                    message.what = DB_UPDATE;
                    handler.sendMessage(message);
                }

                break;
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
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
