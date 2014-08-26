package com.twisty.superclient.view.stockCheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.StockCheckDetail1Data;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.filter.GoodsFilterActivity;
import com.twisty.superclient.view.filter.UnitPop;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class StockCheckAddGoodsActivity extends BaseActivity implements View.OnClickListener {
    private StockCheckDetail1Data stockCheckDetail1Data = new StockCheckDetail1Data();
    private ArrayList<StockCheckDetail1Data> stockCheckDetail1Datas = new ArrayList<StockCheckDetail1Data>();
    private TextView GoodsName, Spec, Unit;
    private EditText GoodsCode, BarCode,AccQty,UnitRealQty,UnitPrice,Amount;
    private UnitDao unitDao;
    private GoodsDao goodsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_check_add_goods);
        GoodsName = (TextView) findViewById(R.id.GoodsName);
        Spec = (TextView) findViewById(R.id.Spec);
        Unit = (TextView) findViewById(R.id.Unit);
        GoodsCode = (EditText) findViewById(R.id.GoodsCode);
        BarCode = (EditText) findViewById(R.id.Barcode);
        AccQty = (EditText) findViewById(R.id.AccQty);
        UnitRealQty = (EditText) findViewById(R.id.UnitRealQty);
        UnitPrice = (EditText) findViewById(R.id.UnitPrice);
        Amount = (EditText) findViewById(R.id.Amount);

        DaoSession session = SuperClient.getDaoSession(this);
        unitDao = session.getUnitDao();
        goodsDao = session.getGoodsDao();
        GoodsName.setOnClickListener(this);
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
                            Goods goods = goodsQueryBuilder.unique();
                            if (goods != null) {
                                stockCheckDetail1Data.setGoodsID(goods.getGoodsID());
                                stockCheckDetail1Data.setUnitID(unit.getUnitID());
                                GoodsCode.setText(goods.getGoodsCode());
                                GoodsName.setText(goods.getGoodsName());
                                Spec.setText(goods.getSpecs());
                                Unit.setText(unit.getUnitName());
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
//            if(UnitPrice.getText().toString().length()<=0){
//                CommonUtil.showToastError(this,"业务单价不能为空!",null);
//                return true;
//            }

            stockCheckDetail1Data.setQuantity(23.2);
            stockCheckDetail1Data.setBillID(-1L);
            stockCheckDetail1Data.setUnitPrice(222.22222);
            stockCheckDetail1Data.setUnitQuantity(333333.333);
            stockCheckDetail1Data.setUnitRate(13.2);
            stockCheckDetail1Data.setAPrice(123.123);
            stockCheckDetail1Data.setAccQty(333.2);
            stockCheckDetail1Data.setUnitRealQty(333.2);
            stockCheckDetail1Data.setUnitPrice(333.2);
            stockCheckDetail1Data.setAmount(333.2);
//            stockCheckDetail1Data.setAccQty(Double.valueOf(AccQty.getText().toString()));
//            stockCheckDetail1Data.setUnitRealQty(Double.valueOf(UnitRealQty.getText().toString()));
//            stockCheckDetail1Data.setUnitPrice(Double.valueOf(UnitPrice.getText().toString()));
//            stockCheckDetail1Data.setAmount(Double.valueOf(Amount.getText().toString()));


            stockCheckDetail1Datas.add(stockCheckDetail1Data);
            Intent intent = new Intent();
            intent.putExtra("com.twisty.superclient.Data", stockCheckDetail1Datas);
            setResult(RESULT_OK, intent);
            finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Goods goods = (Goods) data.getSerializableExtra("Data");
                    if (goods != null) {
                        stockCheckDetail1Data.setGoodsID(goods.getGoodsID());
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
                                        stockCheckDetail1Data.setUnitID(unit.getUnitID());
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
