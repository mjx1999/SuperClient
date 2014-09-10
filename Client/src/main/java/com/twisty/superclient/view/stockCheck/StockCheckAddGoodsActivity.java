package com.twisty.superclient.view.stockCheck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.AccOnHandResp;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.ParamsAccOnHand;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.StockCheckDetail1Data;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.filter.GoodsFilterActivity;
import com.twisty.superclient.view.filter.UnitPop;
import com.twisty.superclient.view.salesBill.FragmentSalesBillDetail;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class StockCheckAddGoodsActivity extends BaseActivity implements View.OnClickListener {
    private StockCheckDetail1Data stockCheckDetail1Data = new StockCheckDetail1Data();
    private ArrayList<StockCheckDetail1Data> stockCheckDetail1Datas = new ArrayList<StockCheckDetail1Data>();
    private TextView GoodsName, Spec, Unit;
    private EditText GoodsCode, BarCode, AccQty, UnitRealQty, UnitPrice, Amount;
    private UnitDao unitDao;
    private GoodsDao goodsDao;
    private int type;
    private DecimalFormat decimalFormat;
    private Long storeID;
    private DateTime dateTime;
    private ProgressDialog pd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            if (msg.what == RESULT_CANCELED) {
                CommonUtil.showToastError(StockCheckAddGoodsActivity.this, "获取库存失败", null);
            } else if (msg.what == RESULT_OK) {
                AccOnHandResp accOnHandResp = (AccOnHandResp) msg.obj;
                AccQty.setText(decimalFormat.format(accOnHandResp.getEndQty()));
                UnitPrice.setText(decimalFormat.format(accOnHandResp.getEndPrice()));
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_check_add_goods);
        type = getIntent().getIntExtra("Type", FragmentStockCheckDetail.ADDGOODS);
        StockCheckDetail1Data currentData = (StockCheckDetail1Data) getIntent().getSerializableExtra("CurrentData");
        dateTime = new DateTime();
        storeID = getIntent().getLongExtra("StoreID", -1);
        decimalFormat = new DecimalFormat("#.########");
        GoodsName = (TextView) findViewById(R.id.GoodsName);
        Spec = (TextView) findViewById(R.id.Spec);
        Unit = (TextView) findViewById(R.id.Unit);
        GoodsCode = (EditText) findViewById(R.id.GoodsCode);
        BarCode = (EditText) findViewById(R.id.BarCode);
        AccQty = (EditText) findViewById(R.id.AccQty);
        UnitRealQty = (EditText) findViewById(R.id.UnitRealQty);
        UnitPrice = (EditText) findViewById(R.id.UnitPrice);
        Amount = (EditText) findViewById(R.id.Amount);

        DaoSession session = SuperClient.getDaoSession(this);
        unitDao = session.getUnitDao();
        goodsDao = session.getGoodsDao();
        GoodsName.setOnClickListener(this);

        if (!SuperClient.getIsOnline()) {
            AccQty.setText("当前离线模式,无法查询");
            UnitPrice.setText("当前离线模式,无法查询");
            AccQty.setEnabled(false);
            UnitPrice.setEnabled(false);
        }

        if (type == FragmentStockCheckDetail.UPDATAGOODS && currentData != null) {
            stockCheckDetail1Data = currentData;

            BarCode.setText(stockCheckDetail1Data.getBarCode());
            GoodsCode.setText(stockCheckDetail1Data.getGoodsCode());
            GoodsName.setText(stockCheckDetail1Data.getGoodsName());
            Spec.setText(stockCheckDetail1Data.getSpecs());
            Unit.setText(stockCheckDetail1Data.getUnitName());
            AccQty.setText(decimalFormat.format(stockCheckDetail1Data.getAccQty()));
            UnitRealQty.setText(decimalFormat.format(stockCheckDetail1Data.getUnitRealQty()));
            UnitPrice.setText(decimalFormat.format(stockCheckDetail1Data.getUnitPrice()));
            Amount.setText(decimalFormat.format(stockCheckDetail1Data.getAmount()));
        }

        BarCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains("\n")) {
                    BarCode.setText(s.toString().trim());
                    Unit.setEnabled(false);
                    UnitRealQty.requestFocus();
                    UnitRealQty.setText("1");
                    UnitRealQty.setSelection(1);
                }
            }
        });
        BarCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String barCode = BarCode.getText().toString().trim();
                    if (barCode.length() > 0) {
                        QueryBuilder<com.twisty.superclient.bean.Unit> unitQueryBuilder = unitDao.queryBuilder();
                        com.twisty.superclient.bean.Unit unit = unitQueryBuilder.where(UnitDao.Properties.BarCode.eq(barCode)).unique();
                        if (unit != null) {
                            QueryBuilder<Goods> goodsQueryBuilder = goodsDao.queryBuilder();
                            goodsQueryBuilder.where(GoodsDao.Properties.GoodsID.eq(unit.getGoodsID()));
                            final Goods goods = goodsQueryBuilder.unique();
                            if (goods != null) {
                                stockCheckDetail1Data.setGoodsID(goods.getGoodsID());
                                stockCheckDetail1Data.setUnitID(unit.getUnitID());
                                stockCheckDetail1Data.setSpecs(goods.getSpecs());
                                stockCheckDetail1Data.setGoodsName(goods.getGoodsName());
                                stockCheckDetail1Data.setGoodsCode(goods.getGoodsCode());
                                stockCheckDetail1Data.setUnitName(unit.getUnitName());
                                stockCheckDetail1Data.setUnitRate(unit.getRate());
                                stockCheckDetail1Data.setBarCode(unit.getBarCode());
                                GoodsCode.setText(goods.getGoodsCode());
                                GoodsName.setText(goods.getGoodsName());
                                Spec.setText(goods.getSpecs());
                                Unit.setText(unit.getUnitName());

                                if (SuperClient.getIsOnline()) {

                                    pd = ProgressDialog.show(StockCheckAddGoodsActivity.this, null, "正在获取库存和单价", true, true);
                                    final Message msg = handler.obtainMessage();
                                    msg.what = RESULT_CANCELED;
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Request request = new Request(GlobalConstant.METHOD_GET_ACC_ONHAND);
                                        ParamsAccOnHand paramsAccOnHand = new ParamsAccOnHand();
                                        paramsAccOnHand.setGoodsID(goods.getGoodsID());
                                        paramsAccOnHand.setStoreID(storeID);
                                        paramsAccOnHand.setDate(dateTime.toString("YYYY-MM-dd"));
                                        request.setParams(paramsAccOnHand);
                                        ReqClient client = ReqClient.newInstance();
                                        try {
                                            client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                            String accOnHandJson = client.requestData(request);
                                            log.i(accOnHandJson);
                                            AccOnHandResp accOnHandResp = CommonUtil.getGson().fromJson(accOnHandJson, AccOnHandResp.class);
                                            if (accOnHandResp != null) {
                                                if (accOnHandResp.isCorrect()) {
                                                    msg.obj = accOnHandResp;
                                                    msg.what = RESULT_OK;
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {
                                            handler.sendMessage(msg);
                                            client.close();
                                        }

                                    }
                                }).start();
                                }


                            } else {
                                CommonUtil.showToastError(StockCheckAddGoodsActivity.this, "没有找到当前条码的货品!", null);
                            }
                        } else {
                            GoodsCode.setText("");
                            GoodsName.setText("");
                            Spec.setText("");
                            Unit.setText("");
                            CommonUtil.showToastError(StockCheckAddGoodsActivity.this, "没有找到当前条码的货品!", null);
                        }

                    }
                }
            }
        });
        UnitRealQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        BigDecimal realQty = new BigDecimal(UnitRealQty.getText().toString());
                        BigDecimal accQty = new BigDecimal(AccQty.getText().toString());
                        BigDecimal unitPrice = new BigDecimal(UnitPrice.getText().toString());
                        BigDecimal amount = (realQty.subtract(accQty)).multiply(unitPrice);
                        Amount.setText(decimalFormat.format(amount));
                    } catch (Exception e) {
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_goods, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.commit) {
            try {
                Double realQty = Double.valueOf(UnitRealQty.getText().toString());
                stockCheckDetail1Data.setUnitRealQty(realQty);

                if (SuperClient.getIsOnline()) {
                    stockCheckDetail1Data.setUnitPrice(Double.valueOf(UnitPrice.getText().toString()));
                    Double accQty = Double.valueOf(AccQty.getText().toString());
                    stockCheckDetail1Data.setAccQty(accQty);
                    stockCheckDetail1Data.setQuantity(accQty - realQty);
                    stockCheckDetail1Data.setAmount(Double.valueOf(Amount.getText().toString()));
                }

            } catch (NumberFormatException nfe) {
                CommonUtil.showToastError(this, "数据格式错误!", null);
                return true;
            }


            if (type == FragmentSalesBillDetail.UPDATAGOODS) {


                Intent intent = new Intent();
                intent.putExtra("Data", stockCheckDetail1Data);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                stockCheckDetail1Data.setBillID(-1L);
                stockCheckDetail1Data.setItemNo(-1);
                stockCheckDetail1Datas.add(stockCheckDetail1Data);
                Intent intent = new Intent();
                intent.putExtra("com.twisty.superclient.Data", stockCheckDetail1Datas);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoodsName:
                Intent intent = new Intent(this, GoodsFilterActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    final Goods goods = (Goods) data.getSerializableExtra("Data");
                    if (goods != null) {
                        stockCheckDetail1Data.setGoodsID(goods.getGoodsID());
                        stockCheckDetail1Data.setGoodsName(goods.getGoodsName());
                        stockCheckDetail1Data.setGoodsCode(goods.getGoodsCode());
                        stockCheckDetail1Data.setSpecs(goods.getSpecs());
                        BarCode.setText("");
                        GoodsName.setText(goods.getGoodsName());
                        GoodsCode.setText(goods.getGoodsCode());
                        Spec.setText(goods.getSpecs());
                        QueryBuilder<com.twisty.superclient.bean.Unit> unitQueryBuilder = unitDao.queryBuilder();
                        unitQueryBuilder.where(UnitDao.Properties.GoodsID.eq(goods.getGoodsID()));
                        final List<com.twisty.superclient.bean.Unit> units = unitQueryBuilder.list();
                        Unit.setEnabled(true);
                        Unit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UnitPop unitPop = new UnitPop(StockCheckAddGoodsActivity.this, units, new UnitPop.onItemClickListener() {
                                    @Override
                                    public void onItemClick(Unit unit) {
                                        BarCode.setText(unit.getBarCode());
                                        Unit.setText(unit.getUnitName());
                                        stockCheckDetail1Data.setBarCode(unit.getBarCode());
                                        stockCheckDetail1Data.setUnitID(unit.getUnitID());
                                        stockCheckDetail1Data.setUnitName(unit.getUnitName());
                                        stockCheckDetail1Data.setUnitRate(unit.getRate());
                                        UnitRealQty.requestFocus();
                                        if (SuperClient.getIsOnline()) {

                                            pd = ProgressDialog.show(StockCheckAddGoodsActivity.this, null, "正在获取库存和单价", true, true);
                                            final Message msg = handler.obtainMessage();
                                            msg.what = RESULT_CANCELED;
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Request request = new Request(GlobalConstant.METHOD_GET_ACC_ONHAND);
                                                ParamsAccOnHand paramsAccOnHand = new ParamsAccOnHand();
                                                paramsAccOnHand.setGoodsID(goods.getGoodsID());
                                                paramsAccOnHand.setStoreID(storeID);
                                                paramsAccOnHand.setDate(dateTime.toString("YYYY-MM-dd"));
                                                request.setParams(paramsAccOnHand);
                                                ReqClient client = ReqClient.newInstance();
                                                try {
                                                    client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                                                    String accOnHandJson = client.requestData(request);
                                                    log.i(accOnHandJson);
                                                    AccOnHandResp accOnHandResp = CommonUtil.getGson().fromJson(accOnHandJson, AccOnHandResp.class);
                                                    if (accOnHandResp != null) {
                                                        if (accOnHandResp.isCorrect()) {
                                                            msg.obj = accOnHandResp;
                                                            msg.what = RESULT_OK;
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                } finally {
                                                    handler.sendMessage(msg);
                                                    client.close();
                                                }

                                            }
                                        }).start();
                                        }

                                    }
                                });
                                unitPop.showPopupWindow(Unit);
                            }
                        });
                    }
                    break;
            }
        }
    }
}
