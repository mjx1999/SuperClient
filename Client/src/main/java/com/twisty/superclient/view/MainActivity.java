package com.twisty.superclient.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.twisty.superclient.R;
import com.twisty.superclient.adapter.ModuleAdapter;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.AMKindResp;
import com.twisty.superclient.bean.Account;
import com.twisty.superclient.bean.AccountResp;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.AccsetResp;
import com.twisty.superclient.bean.Area;
import com.twisty.superclient.bean.AreaResp;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Department;
import com.twisty.superclient.bean.DepartmentResp;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.EmployeeResp;
import com.twisty.superclient.bean.GDType;
import com.twisty.superclient.bean.GDTypeResp;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsResp;
import com.twisty.superclient.bean.IoType;
import com.twisty.superclient.bean.IoTypeResp;
import com.twisty.superclient.bean.Module;
import com.twisty.superclient.bean.OnHand;
import com.twisty.superclient.bean.OnHandResp;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.OperatorResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.PayMethod;
import com.twisty.superclient.bean.PayMethodResp;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreResp;
import com.twisty.superclient.bean.TradeType;
import com.twisty.superclient.bean.TradeTypeResp;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderPrice;
import com.twisty.superclient.bean.TraderPriceResp;
import com.twisty.superclient.bean.TraderResp;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private GridView moduleView;
    private ModuleAdapter adapter;
    private ProgressDialog pd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            switch (msg.what) {
                case RESULT_OK:
                    CommonUtil.showToastInfo(MainActivity.this, "数据下载成功!");
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(MainActivity.this, msg.obj.toString());
                    break;
            }
        }
    };
    private Accset accset;
    private Operator operator;
    private DaoSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accset = SuperClient.getCurrentAccset();
        operator = SuperClient.getCurrentOperator();
        moduleView = (GridView) findViewById(R.id.module);
        List<Module> modules = new ArrayList<Module>();
        Module module1 = new Module();
        module1.setModuleIcon(android.R.drawable.ic_dialog_alert);
        module1.setModuleName("单据管理");
        module1.setModuleID(1);
        Module module2 = new Module();
        module2.setModuleIcon(android.R.drawable.stat_sys_download);
        module2.setModuleName("下载数据");
        module2.setModuleID(2);

        Module module3 = new Module();
        module3.setModuleID(3);
        module3.setModuleIcon(android.R.drawable.ic_input_get);
        module3.setModuleName("打印测试");

        modules.add(module1);
        modules.add(module2);
        modules.add(module3);
        adapter = new ModuleAdapter(modules, this);
        moduleView.setAdapter(adapter);
        moduleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) parent.getItemIdAtPosition(position)) {
                    case 1:
                        Intent orderIntent = new Intent(MainActivity.this, ModuleOrderActivity.class);
                        startActivity(orderIntent);
                        break;
                    case 2:
                        if (SuperClient.getIsOnline()) {
                            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setMessage("下载资料将需要话费较多时间和网络流量,建议您在网络状况良好的Wifi环境下进行,是否现在进行下载?")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            pd = ProgressDialog.show(MainActivity.this, null, "正在下载数据到本地,请耐心等候!");

                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ReqClient client = ReqClient.newInstance();
                                                    Request request;
                                                    Params params;
                                                    Gson gson = CommonUtil.getGson();
                                                    session = SuperClient.getDaoSession(MainActivity.this);

                                                    try {


                                                        /**
                                                         * 获取帐套列表
                                                         */

                                                        request = new Request(GlobalConstant.METHOD_GET_ACC_LIST);
                                                        params = new Params();
                                                        params.setClientVer("1.2");
                                                        request.setParams(params);
                                                        String accListJson = client.requestData(request);
                                                        log.i(accListJson);
                                                        AccsetResp accsetResp = gson.fromJson(accListJson, AccsetResp.class);
                                                        log.i(accsetResp);
                                                        ArrayList<Accset> accsets;
                                                        if ((accsets = accsetResp.getResultData()) != null) {
                                                            session.getAccsetDao().deleteAll();
                                                            session.getAccsetDao().insertInTx(accsets);
                                                        }


                                                        /**
                                                         * 获取操作员列表
                                                         */
                                                        request = new Request(GlobalConstant.METHOD_DOWNLOAD_OPERATOR);
                                                        params = new Params();
                                                        params.setAccsetCode(accset.getAccsetCode());
                                                        request.setParams(params);
                                                        String operatorListJson = client.requestData(request);
                                                        log.i(operatorListJson);
                                                        OperatorResp operatorResp = gson.fromJson(operatorListJson, OperatorResp.class);
                                                        log.i(operatorResp);
                                                        ArrayList<Operator> operators;
                                                        if ((operators = operatorResp.getResultData()) != null) {
                                                            session.getOperatorDao().deleteAll();
                                                            session.getOperatorDao().insertInTx(operators);
                                                        }


                                                        /**
                                                         * 往来单位类型
                                                         */
                                                        String traderTypeJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_TRADER_TYPE));
                                                        log.i(traderTypeJson);
                                                        TradeTypeResp tradeTypeResp = gson.fromJson(traderTypeJson, TradeTypeResp.class);
                                                        ArrayList<TradeType> tradeTypes;
                                                        if ((tradeTypes = tradeTypeResp.getResultData()) != null) {
                                                            session.getTradeTypeDao().deleteAll();
                                                            session.getTradeTypeDao().insertInTx(tradeTypes);
                                                        }

                                                        /**
                                                         * 往来单位
                                                         */
                                                        String traderJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_TRADER));
                                                        log.i(traderJson);
                                                        TraderResp traderResp = gson.fromJson(traderJson, TraderResp.class);
                                                        ArrayList<Trader> traders;
                                                        if ((traders = traderResp.getResultData()) != null) {
                                                            session.getTraderDao().deleteAll();
                                                            session.getTraderDao().insertInTx(traders);
                                                        }
                                                        /**
                                                         *往来单位价格
                                                         */
                                                        String traderPriceJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_TRADER_PRICE));
                                                        log.i(traderPriceJson);
                                                        TraderPriceResp traderPriceResp = gson.fromJson(traderPriceJson, TraderPriceResp.class);
                                                        ArrayList<TraderPrice> traderPrices;
                                                        if ((traderPrices = traderPriceResp.getResultData()) != null) {
                                                            session.getTraderPriceDao().deleteAll();
                                                            session.getTraderPriceDao().insertInTx(traderPrices);
                                                        }

                                                        /**
                                                         * 地区
                                                         */
                                                        String areaJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_AREA));
                                                        log.i(areaJson);
                                                        AreaResp areaResp = gson.fromJson(areaJson, AreaResp.class);
                                                        ArrayList<Area> areas;
                                                        if ((areas = areaResp.getResultData()) != null) {
                                                            session.getAreaDao().deleteAll();
                                                            session.getAreaDao().insertInTx(areas);
                                                        }

                                                        /**
                                                         * 部门
                                                         */


                                                        String departmentJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_DEPARTMENT));
                                                        log.i(departmentJson);
                                                        DepartmentResp departmentResp = gson.fromJson(departmentJson, DepartmentResp.class);
                                                        ArrayList<Department> departments;
                                                        if ((departments = departmentResp.getResultData()) != null) {
                                                            session.getDepartmentDao().deleteAll();
                                                            session.getDepartmentDao().insertInTx(departments);
                                                        }


                                                        /**
                                                         * 业务员
                                                         */
                                                        String empJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_EMPLOY));
                                                        log.i(empJson);
                                                        EmployeeResp employeeResp = gson.fromJson(empJson, EmployeeResp.class);
                                                        ArrayList<Employee> employees;
                                                        if ((employees = employeeResp.getResultData()) != null) {
                                                            session.getEmployeeDao().deleteAll();
                                                            session.getEmployeeDao().insertInTx(employees);
                                                        }


                                                        /**
                                                         * 出入库类型
                                                         */
                                                        String ioTypeJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_IO_TYPE));
                                                        log.i(ioTypeJson);
                                                        IoTypeResp ioTypeResp = gson.fromJson(ioTypeJson, IoTypeResp.class);
                                                        ArrayList<IoType> ioTypes;
                                                        if ((ioTypes = ioTypeResp.getResultData()) != null) {
                                                            session.getIoTypeDao().deleteAll();
                                                            session.getIoTypeDao().insertInTx(ioTypes);
                                                        }

                                                        /**
                                                         * 仓库
                                                         */
                                                        String storeJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_STORE));
                                                        log.i(storeJson);
                                                        StoreResp storeResp = gson.fromJson(storeJson, StoreResp.class);
                                                        ArrayList<Store> stores;
                                                        if ((stores = storeResp.getResultData()) != null) {
                                                            session.getStoreDao().deleteAll();
                                                            session.getStoreDao().insertInTx(stores);
                                                        }

                                                        /**
                                                         * 货品类别
                                                         */
                                                        String gdTypeJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_GDTYPE));
                                                        log.i(gdTypeJson);
                                                        GDTypeResp gdTypeResp = gson.fromJson(gdTypeJson, GDTypeResp.class);
                                                        ArrayList<GDType> gdTypes;
                                                        if ((gdTypes = gdTypeResp.getResultData()) != null) {
                                                            session.getGDTypeDao().deleteAll();
                                                            session.getGDTypeDao().insertInTx(gdTypes);
                                                        }

                                                        /**
                                                         * 货品
                                                         */
                                                        String goodsJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_GOODS));
                                                        log.i(goodsJson);
                                                        GoodsResp goodsResp = gson.fromJson(goodsJson, GoodsResp.class);
                                                        ArrayList<Goods> goodses;
                                                        if ((goodses = goodsResp.getResultData()) != null) {
                                                            session.getGoodsDao().deleteAll();
                                                            session.getGoodsDao().insertInTx(goodses);
                                                        }
                                                        /**
                                                         * 货品单位
                                                         */
                                                        String unitJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_GOODS_UNIT));
                                                        log.i(unitJson);
                                                        UnitResp unitResp = gson.fromJson(unitJson, UnitResp.class);
                                                        ArrayList<Unit> units;
                                                        if ((units = unitResp.getResultData()) != null) {
                                                            session.getUnitDao().deleteAll();
                                                            session.getUnitDao().insertInTx(units);
                                                        }
                                                        /**
                                                         * 库存
                                                         */
                                                        String onhandJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_ONHAND));
                                                        log.i(onhandJson);
                                                        OnHandResp onHandResp = gson.fromJson(onhandJson, OnHandResp.class);
                                                        ArrayList<OnHand> onHands;
                                                        if ((onHands = onHandResp.getResultData()) != null) {
                                                            session.getOnHandDao().deleteAll();
                                                            session.getOnHandDao().insertInTx(onHands);
                                                        }
                                                        /**
                                                         * 资金帐户
                                                         */
                                                        String accountJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_ACCOUNT));
                                                        log.i(accountJson);
                                                        AccountResp accountResp = gson.fromJson(accountJson, AccountResp.class);
                                                        ArrayList<Account> accounts;
                                                        if ((accounts = accountResp.getResultData()) != null) {
                                                            session.getAccountDao().deleteAll();
                                                            session.getAccountDao().insertInTx(accounts);
                                                        }


                                                        /**
                                                         * 付款方式
                                                         */
                                                        String payMethodJson = client.requestData(new Request(GlobalConstant.METHOD_DOWNLOAD_PAYMETHOD));
                                                        log.i(payMethodJson);
                                                        PayMethodResp payMethodResp = gson.fromJson(payMethodJson,PayMethodResp.class);
                                                        ArrayList<PayMethod> payMethods;
                                                        if((payMethods=payMethodResp.getResultData())!=null){
                                                            session.getPayMethodDao().deleteAll();
                                                            session.getPayMethodDao().insertInTx(payMethods);
                                                        }


                                                        /**
                                                         * 选项
                                                         */

                                                        String amKindJson = client.requestData(new Request(GlobalConstant.MEHTOD_DOWNLOAD_AM_KIND));
                                                        log.i(amKindJson);
                                                        AMKindResp amKindResp = gson.fromJson(amKindJson,AMKindResp.class);
                                                        ArrayList<AMKind> amKinds ;
                                                        if((amKinds=amKindResp.getResultData())!=null){
                                                            session.getAMKindDao().deleteAll();
                                                            session.getAMKindDao().insertInTx(amKinds);
                                                        }

                                                        handler.sendEmptyMessage(RESULT_OK);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        Message msg = handler.obtainMessage();
                                                        msg.what = RESULT_CANCELED;
                                                        msg.obj = e.getMessage() == null ? "下载失败,请重试." : e.getMessage();
                                                        handler.sendMessage(msg);
                                                        log.e(e.getMessage());
                                                    }
                                                }
                                            }).start();


                                        }
                                    })
                                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        } else {
                            CommonUtil.showToastError(MainActivity.this, "当前是离线登录模式,下载资料请使用在线登录!");
                        }
                        break;
                    case 3:

                        break;
                }
            }
        });
    }
}
