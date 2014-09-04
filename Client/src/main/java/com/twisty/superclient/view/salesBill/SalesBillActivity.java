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
import com.twisty.superclient.bean.BillSaveResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.ParamsSalesBill;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Response;
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.bean.SalesBillDetail1DataDao;
import com.twisty.superclient.bean.SalesBillMasterData;
import com.twisty.superclient.bean.SalesBillMasterDataDao;
import com.twisty.superclient.bean.SalesBillResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.BluetoothListActivity;

import java.util.ArrayList;

public class SalesBillActivity extends BaseActivity implements View.OnClickListener, ActionBar.TabListener {
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4, DELETE_RESULT = 5;
    private ActionBar actionBar;
    private Button searchBTN, saveBTN;
    private boolean isAddNew = true;
    private boolean isCommit;
    private SalesBillMasterData salesBillMasterData = new SalesBillMasterData();
    private ProgressDialog pd;
    private ArrayList<SalesBillDetail1Data> salesBillDetail1Datas = new ArrayList<SalesBillDetail1Data>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            switch (msg.what) {
                case PRE_RESULT:
                    fragmentSalesBIllHeader.setSalesBillMasterData(salesBillMasterData);
                    fragmentSalesBillDetail.setSalesBillDetail1Datas(salesBillDetail1Datas);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(SalesBillActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case NEXT_RESULT:
                    fragmentSalesBIllHeader.setSalesBillMasterData(salesBillMasterData);
                    fragmentSalesBillDetail.setSalesBillDetail1Datas(salesBillDetail1Datas);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(SalesBillActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case DELETE_RESULT:
                    fragmentSalesBIllHeader.setSalesBillMasterData(null);
                    fragmentSalesBillDetail.setSalesBillDetail1Datas(null);
                    if (msg.obj != null) {
                        CommonUtil.showToastError(SalesBillActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case RESULT_OK:
                    CommonUtil.showToastInfo(SalesBillActivity.this, "保存成功!", null);
                    isCommit = true;
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(SalesBillActivity.this, String.valueOf(msg.obj), null);
                    break;
            }
        }
    };
    private FragmentSalesBillDetail fragmentSalesBillDetail;
    private FragmentSalesBIllHeader fragmentSalesBIllHeader;
    private Gson gson;
    private int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from = getIntent().getIntExtra("From", -1);
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (from != -1) {
            if (from == GlobalConstant.FROM_DB) {
                SalesBillMasterData masterData = (SalesBillMasterData) getIntent().getSerializableExtra("MasterData");
                ArrayList<SalesBillDetail1Data> detail1Datas = (ArrayList<SalesBillDetail1Data>) getIntent().getSerializableExtra("DetailData");
                fragmentSalesBIllHeader.setSalesBillMasterData(masterData);
                fragmentSalesBillDetail.setSalesBillDetail1Datas(detail1Datas);
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
                if (!isAddNew || isCommit) {
                    Intent intent = new Intent(this, BluetoothListActivity.class);
                    SalesBillResp salesBillResp = new SalesBillResp();
                    salesBillResp.setMasterData(salesBillMasterData);
                    salesBillResp.setDetail1Data(salesBillDetail1Datas);
                    intent.putExtra("BillType", GlobalConstant.BILL_TYPE_SALES_BILL);
                    intent.putExtra("com.twisty.superclient.Data", salesBillResp);
                    startActivity(intent);
                } else {
                    CommonUtil.showToastError(this, "保存单据之后才能打印!", null);
                }

                return true;
            case R.id.delete:
                if (SuperClient.getIsOnline()) {

                    pd = ProgressDialog.show(this, null, "正在删除...");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ReqClient client = ReqClient.newInstance();
                            Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                            Params paramsDel = new Params();
                            paramsDel.setOperate("Delete");
                            paramsDel.setBillName("s_sale");
                            paramsDel.setBillID(fragmentSalesBIllHeader.getSalesBillMasterData().getBillID());
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
                } else {
                    CommonUtil.showToastError(SalesBillActivity.this, "当前离线模式不能删除单据!", null);
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
                            paramsPRE.setBillName("s_sale");
                            paramsPRE.setBillCode(fragmentSalesBIllHeader.getSalesBillMasterData().getBillCode());
                            request.setParams(paramsPRE);
                            try {
                                client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                String preBillJson = client.requestData(request);
                                log.i(preBillJson);
                                SalesBillResp salesBillResp = gson.fromJson(preBillJson, SalesBillResp.class);
                                Message message = handler.obtainMessage();
                                if (salesBillResp != null) {
                                    if (salesBillResp.isCorrect()) {
                                        if (salesBillResp.getMasterData() != null) {
                                            salesBillMasterData = salesBillResp.getMasterData();
                                        }
                                        if (salesBillResp.getDetail1Data() != null) {
                                            salesBillDetail1Datas = salesBillResp.getDetail1Data();
                                        }
                                    } else {
                                        message.obj = salesBillResp.getErrMessage();
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
                            }
                        }
                    }).start();
                } else {
                    CommonUtil.showToastError(SalesBillActivity.this, "当前离线模式不能翻单!", null);
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
                            paramsPRE.setBillName("s_sale");
                            paramsPRE.setBillCode(fragmentSalesBIllHeader.getSalesBillMasterData().getBillCode());
                            request.setParams(paramsPRE);
                            try {
                                client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                String nextBillJson = client.requestData(request);
                                log.i(nextBillJson);
                                SalesBillResp salesBillResp = gson.fromJson(nextBillJson, SalesBillResp.class);
                                Message message = handler.obtainMessage();
                                if (salesBillResp != null) {
                                    if (salesBillResp.isCorrect()) {
                                        if (salesBillResp.getMasterData() != null) {
                                            salesBillMasterData = salesBillResp.getMasterData();
                                        }
                                        if (salesBillResp.getDetail1Data() != null) {
                                            salesBillDetail1Datas = salesBillResp.getDetail1Data();
                                        }
                                    } else {
                                        log.i(salesBillResp.getErrMessage());
                                        message.obj = salesBillResp.getErrMessage();
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
                } else {
                    CommonUtil.showToastError(SalesBillActivity.this, "当前离线模式不能翻单!", null);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
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
                Intent intent = new Intent(this, SalesBillFilterActivity.class);
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
                    CommonUtil.showToastError(this, "客户不能为空!", null);
                    return;
                }

                if (salesBillMasterData.getNoteTypeID() == null) {
                    CommonUtil.showToastError(this, "发票类型不能为空!", null);
                    return;
                }
                FragmentSalesBillDetail fragmentSalesBillDetail = (FragmentSalesBillDetail) getFragmentManager().findFragmentByTag("detail");
                if (fragmentSalesBillDetail != null) {
                    if (fragmentSalesBillDetail.getSalesBillDetail1Datas() != null) {
                        salesBillDetail1Datas = fragmentSalesBillDetail.getSalesBillDetail1Datas();
                    }
                    if (salesBillDetail1Datas != null) {
                        log.i(salesBillDetail1Datas.size());
                    }
                }

                pd = ProgressDialog.show(this, null, "正在保存销售单.");
                final Message message = handler.obtainMessage();

                if (SuperClient.getIsOnline()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                            ParamsSalesBill params = new ParamsSalesBill();
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
                                client.close();
                                handler.sendMessage(message);
                            }
                        }
                    }).start();
                } else {
                    SalesBillMasterDataDao salesBillMasterDataDao = SuperClient.getDaoSession(this).getSalesBillMasterDataDao();
                    SalesBillDetail1DataDao salesBillDetail1DataDao = SuperClient.getDaoSession(this).getSalesBillDetail1DataDao();
                    if (from == GlobalConstant.FROM_DB) {
                        salesBillMasterDataDao.update(salesBillMasterData);
                        salesBillDetail1DataDao.updateInTx(salesBillDetail1Datas);
                    } else {

                        long masterID = salesBillMasterDataDao.insert(salesBillMasterData);
                        for (SalesBillDetail1Data detail1Data : salesBillDetail1Datas) {
                            detail1Data.setMasterID(masterID);
                            salesBillDetail1DataDao.insert(detail1Data);
                        }
                    }

                    message.what = RESULT_OK;
                    handler.sendMessage(message);
                }


                break;
        }

    }

}
