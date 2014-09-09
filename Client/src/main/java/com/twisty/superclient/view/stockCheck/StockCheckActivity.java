package com.twisty.superclient.view.stockCheck;

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
import com.twisty.superclient.bean.ParamsStockCheck;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Response;
import com.twisty.superclient.bean.StockCheckDetail1Data;
import com.twisty.superclient.bean.StockCheckDetail1DataDao;
import com.twisty.superclient.bean.StockCheckMasterData;
import com.twisty.superclient.bean.StockCheckMasterDataDao;
import com.twisty.superclient.bean.StockCheckResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;

public class StockCheckActivity extends BaseActivity implements ActionBar.TabListener, View.OnClickListener {
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4, DELETE_RESULT = 5, DB_UPDATE = 6;
    ;
    StockCheckMasterDataDao stockCheckMasterDataDao;
    StockCheckDetail1DataDao stockCheckDetail1DataDao;
    private FragmentStockCheckHeader fragmentStockCheckHeader;
    private FragmentStockCheckDetail fragmentStockCheckDetail;
    private StockCheckMasterData stockCheckMasterData;
    private ArrayList<StockCheckDetail1Data> stockCheckDetail1Datas;
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
                    fragmentStockCheckHeader.setMasterData(stockCheckMasterData);
                    fragmentStockCheckDetail.setDetail1Data(stockCheckDetail1Datas);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(StockCheckActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case NEXT_RESULT:
                    fragmentStockCheckHeader.setMasterData(stockCheckMasterData);
                    fragmentStockCheckDetail.setDetail1Data(stockCheckDetail1Datas);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(StockCheckActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case RESULT_OK:
                    CommonUtil.showToastInfo(StockCheckActivity.this, "保存成功!", null);
                    isCommit = true;
                    break;
                case DELETE_RESULT:
                    fragmentStockCheckHeader.setMasterData(null);
                    fragmentStockCheckDetail.setDetail1Data(null);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(StockCheckActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case DB_UPDATE:
                    CommonUtil.showToastInfo(StockCheckActivity.this, "当前离线模式,数据保存在本地,为了数据安全,请及时联网上传到服务器!", null);
                    isCommit = true;
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(StockCheckActivity.this, String.valueOf(msg.obj), null);
                    break;
            }
        }
    };
    private Gson gson;
    private int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_check);
        stockCheckMasterDataDao = SuperClient.getDaoSession(this).getStockCheckMasterDataDao();
        stockCheckDetail1DataDao = SuperClient.getDaoSession(this).getStockCheckDetail1DataDao();
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

        fragmentStockCheckDetail = new FragmentStockCheckDetail();
        fragmentStockCheckDetail.setRetainInstance(true);
        fragmentStockCheckHeader = new FragmentStockCheckHeader();
        fragmentStockCheckHeader.setRetainInstance(true);


        fragmentTransaction.add(R.id.fragment_content, fragmentStockCheckDetail, "detail").add(R.id.fragment_content, fragmentStockCheckHeader, "header").commit();


        actionBar.addTab(headerTab1);
        actionBar.addTab(orderDetailTab);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (from != -1) {
            if (from == GlobalConstant.FROM_DB) {
                stockCheckMasterData = (StockCheckMasterData) getIntent().getSerializableExtra("MasterData");
                stockCheckDetail1Datas = (ArrayList<StockCheckDetail1Data>) getIntent().getSerializableExtra("DetailData");
                fragmentStockCheckHeader.setMasterData(stockCheckMasterData);
                fragmentStockCheckDetail.setDetail1Data(stockCheckDetail1Datas);
            } else if (from == GlobalConstant.FROM_LIST) {
                //TODO 从列表进来
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (from == GlobalConstant.FROM_DB) {
            getMenuInflater().inflate(R.menu.order_from_db, menu);

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
                if (!isAddNew || isCommit) {
                    //TODO 打印
                }
                return true;
            case R.id.delete:
                if (SuperClient.getIsOnline()) {

                    new AlertDialog.Builder(this).setMessage("确定要删除吗?")
                            .setPositiveButton("确定删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (from == GlobalConstant.FROM_DB) {
                                        stockCheckMasterDataDao.delete(stockCheckMasterData);
                                        stockCheckDetail1DataDao.deleteInTx(stockCheckDetail1Datas);
                                        finish();

                                    } else {

                                        pd = ProgressDialog.show(StockCheckActivity.this, null, "正在删除...");
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ReqClient client = ReqClient.newInstance();
                                                Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                                                Params paramsDel = new Params();
                                                paramsDel.setOperate("Delete");
                                                paramsDel.setBillName("i_balitem");
                                                paramsDel.setBillID(fragmentStockCheckHeader.getMasterData().getBillID());
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

                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

                } else {
                    if (from == GlobalConstant.FROM_DB) {
                        stockCheckMasterDataDao.delete(stockCheckMasterData);
                        stockCheckDetail1DataDao.deleteInTx(stockCheckDetail1Datas);
                        finish();

                    } else {
                        CommonUtil.showToastError(StockCheckActivity.this, "当前离线模式不能删除单据!", null);
                    }
                }
                return true;
            case R.id.preOrder:
                if (SuperClient.getIsOnline()) {

                    isAddNew = false;
                    pd = ProgressDialog.show(this, null, "正在加载数据...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            ReqClient client = ReqClient.newInstance();
                            Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                            Params paramsPRE = new Params();
                            paramsPRE.setOperate("GetPriorBill");
                            paramsPRE.setBillName("i_balitem");
                            paramsPRE.setBillCode(fragmentStockCheckHeader.getMasterData().getBillCode());
                            request.setParams(paramsPRE);
                            Message message = handler.obtainMessage();
                            try {
                                client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                String preBillJson = client.requestData(request);
                                log.i(preBillJson);
                                StockCheckResp stockCheckResp = gson.fromJson(preBillJson, StockCheckResp.class);
                                if (stockCheckResp != null) {
                                    if (stockCheckResp.isCorrect()) {
                                        if (stockCheckResp.getMasterData() != null) {
                                            stockCheckMasterData = stockCheckResp.getMasterData();
                                        }
                                        if (stockCheckResp.getDetail1Data() != null) {
                                            stockCheckDetail1Datas = stockCheckResp.getDetail1Data();
                                        }
                                    } else {
                                        message.obj = stockCheckResp.getErrMessage();
                                    }
                                } else {
                                    message.obj = "服务器错误";
                                }
                                message.what = PRE_RESULT;
                            } catch (Exception e) {
                                message.what = RESULT_CANCELED;
                                message.obj = "加载数据失败,请重试...";
                                e.printStackTrace();
                            } finally {
                                handler.sendMessage(message);
                                client.close();
                            }
                        }
                    }).start();
                } else {
                    CommonUtil.showToastError(this, "当前离线模式不能翻单!", null);
                }

                return true;
            case R.id.nextOrder:
                if (SuperClient.getIsOnline()) {

                    isAddNew = false;
                    pd = ProgressDialog.show(this, null, "正在加载数据...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ReqClient client = ReqClient.newInstance();
                            Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                            Params paramsPRE = new Params();
                            paramsPRE.setOperate("GetNextBill");
                            paramsPRE.setBillName("i_balitem");
                            paramsPRE.setBillCode(fragmentStockCheckHeader.getMasterData().getBillCode());
                            request.setParams(paramsPRE);
                            Message message = handler.obtainMessage();
                            try {
                                client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                String preBillJson = client.requestData(request);
                                log.i(preBillJson);
                                StockCheckResp stockCheckResp = gson.fromJson(preBillJson, StockCheckResp.class);
                                if (stockCheckResp != null) {
                                    if (stockCheckResp.isCorrect()) {
                                        if (stockCheckResp.getMasterData() != null) {
                                            stockCheckMasterData = stockCheckResp.getMasterData();
                                        }
                                        if (stockCheckResp.getDetail1Data() != null) {
                                            stockCheckDetail1Datas = stockCheckResp.getDetail1Data();
                                        }
                                    } else {
                                        message.obj = stockCheckResp.getErrMessage();
                                    }
                                } else {
                                    message.obj = "服务器错误";
                                }
                                message.what = NEXT_RESULT;
                            } catch (Exception e) {
                                message.what = RESULT_CANCELED;
                                message.obj = "加载数据失败,请重试...";
                                e.printStackTrace();
                            } finally {
                                handler.sendMessage(message);
                                client.close();
                            }
                        }
                    }).start();
                } else {
                    CommonUtil.showToastError(this, "当前离线模式不能翻单!", null);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        if (tab.getText().equals("明细")) {
            ft.hide(fragmentStockCheckHeader);
            ft.show(fragmentStockCheckDetail);
        } else if (tab.getText().equals("表头")) {
            ft.hide(fragmentStockCheckDetail);
            ft.show(fragmentStockCheckHeader);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, StockCheckFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.save:
                if (fragmentStockCheckHeader.getMasterData() != null) {
                    stockCheckMasterData = fragmentStockCheckHeader.getMasterData();
                }
                if (stockCheckMasterData.getStoreID() == null) {
                    CommonUtil.showToastError(this, "仓库不能为空!", null);
                    return;
                }
                if (fragmentStockCheckDetail.getDetail1Data() != null) {
                    stockCheckDetail1Datas = fragmentStockCheckDetail.getDetail1Data();
                }
                pd = ProgressDialog.show(this, null, "正在保存盘点单.");
                final Message message = handler.obtainMessage();

                if (SuperClient.getIsOnline()) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                            ParamsStockCheck params = new ParamsStockCheck();
                            params.setBillName("i_balitem");
                            params.setOperate("Save");
                            params.setAddnew(isAddNew);
                            params.setMasterData(stockCheckMasterData);
                            params.setDetail1Data(stockCheckDetail1Datas);
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

                    StockCheckMasterDataDao stockCheckMasterDataDao = SuperClient.getDaoSession(this).getStockCheckMasterDataDao();
                    StockCheckDetail1DataDao stockCheckDetail1DataDao = SuperClient.getDaoSession(this).getStockCheckDetail1DataDao();
                    if (from == GlobalConstant.FROM_DB) {
                        stockCheckMasterDataDao.update(stockCheckMasterData);
                        stockCheckDetail1DataDao.updateInTx(stockCheckDetail1Datas);
                        for (StockCheckDetail1Data detail1Data : stockCheckDetail1Datas) {
                            detail1Data.setMasterID(stockCheckMasterData.getId());
                            stockCheckDetail1DataDao.insertOrReplace(detail1Data);
                        }
                    } else {

                        long masterID = stockCheckMasterDataDao.insert(stockCheckMasterData);
                        for (StockCheckDetail1Data detail1Data : stockCheckDetail1Datas) {
                            detail1Data.setMasterID(masterID);
                            stockCheckDetail1DataDao.insert(detail1Data);
                        }
                    }

                    message.what = RESULT_OK;
                    handler.sendMessage(message);
                }
                break;
        }
    }
}
