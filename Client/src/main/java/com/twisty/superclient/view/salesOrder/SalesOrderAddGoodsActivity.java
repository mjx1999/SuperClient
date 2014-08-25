package com.twisty.superclient.view.salesOrder;

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
import com.twisty.superclient.bean.SalesOrderDetail1Data;
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

public class SalesOrderAddGoodsActivity extends BaseActivity implements View.OnClickListener {
    private SalesOrderDetail1Data salesOrderDetail1Data = new SalesOrderDetail1Data();
    private ArrayList<SalesOrderDetail1Data> salesOrderDetail1Datas = new ArrayList<SalesOrderDetail1Data>();
    private TextView GoodsName, Spec, Unit;
    private EditText GoodsCode, BarCode, UnitQuanty, OrigTaxPrice, Disc, TaxPrice, UnitPrice, TaxRate, TaxAmt, Amount;
    private UnitDao unitDao;
    private GoodsDao goodsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_add_goods);
        DaoSession session = SuperClient.getDaoSession(this);
        unitDao = session.getUnitDao();
        goodsDao = session.getGoodsDao();
        GoodsName = (TextView) findViewById(R.id.GoodsName);
        Spec = (TextView) findViewById(R.id.Spec);
        Unit = (TextView) findViewById(R.id.Unit);
        GoodsCode = (EditText) findViewById(R.id.GoodsCode);
        BarCode = (EditText) findViewById(R.id.Barcode);
        UnitQuanty = (EditText) findViewById(R.id.UnitQuanty);
        OrigTaxPrice = (EditText) findViewById(R.id.OrigTaxPrice);
        Disc = (EditText) findViewById(R.id.Disc);
        TaxPrice = (EditText) findViewById(R.id.TaxPrice);
        UnitPrice = (EditText) findViewById(R.id.UnitPrice);
        TaxRate = (EditText) findViewById(R.id.TaxRate);
        TaxAmt = (EditText) findViewById(R.id.TaxAmt);
        Amount = (EditText) findViewById(R.id.Amount);
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
                    UnitQuanty.requestFocus();
                    UnitQuanty.setText("1");
                    UnitQuanty.setSelection(1);
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
                                salesOrderDetail1Data.setGoodsID(goods.getGoodsID());
                                salesOrderDetail1Data.setUnitID(unit.getUnitID());
                                GoodsCode.setText(goods.getGoodsCode());
                                GoodsName.setText(goods.getGoodsName());
                                Spec.setText(goods.getSpecs());
                                Unit.setText(unit.getUnitName());
                            } else {
                                CommonUtil.showToastError(SalesOrderAddGoodsActivity.this, "没有找到当前条码的货品!", null);
                            }
                        } else {
                            GoodsCode.setText("");
                            GoodsName.setText("");
                            Spec.setText("");
                            Unit.setText("");
                            CommonUtil.showToastError(SalesOrderAddGoodsActivity.this, "没有找到当前条码的货品!", null);
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
//            if(OrigTaxPrice.getText().toString().length()<=0){
//                CommonUtil.showToastError(this,"原始单价(含税)不能为空!");
//                return true;
//            }
//            if(Disc.getText().toString().length()<=0){
//                CommonUtil.showToastError(this,"折扣率不能为空!");
//                return true;
//            }
//            if(TaxPrice.getText().toString().length()<=0){
//                CommonUtil.showToastError(this,"含税单价不能为空!");
//                return true;
//            }
//            if(UnitPrice.getText().toString().length()<=0){
//                CommonUtil.showToastError(this,"业务单价不能为空!");
//                return true;
//            }

            salesOrderDetail1Data.setAmount(22.2);
            salesOrderDetail1Data.setQuantity(23.2);
            salesOrderDetail1Data.setOrigPrice(23.00);
            salesOrderDetail1Data.setDisc(22.0);
            salesOrderDetail1Data.setTaxRate(2.33);
            salesOrderDetail1Data.setTaxAmt(22.222);
            salesOrderDetail1Data.setBillID(-1L);
            salesOrderDetail1Data.setUnitPrice(222.22222);
            salesOrderDetail1Data.setUnitQuantity(333333.333);
            salesOrderDetail1Data.setUnitRate(13.2);
            salesOrderDetail1Data.setAmount(3444444.44);
            salesOrderDetail1Data.setGoodsAmt(124444.1231);
            salesOrderDetail1Data.setIsLargess(0);
            salesOrderDetail1Data.setAPrice(123.123);
            salesOrderDetail1Data.setDisc(1231.123);
            salesOrderDetail1Data.setOrigTaxPrice(234.234);
            salesOrderDetail1Datas.add(salesOrderDetail1Data);
            Intent intent = new Intent();
            intent.putExtra("com.twisty.superclient.Data", salesOrderDetail1Datas);
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
                        salesOrderDetail1Data.setGoodsID(goods.getGoodsID());
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
                                UnitPop unitPop = new UnitPop(SalesOrderAddGoodsActivity.this, units, new UnitPop.onItemClickListener() {
                                    @Override
                                    public void onItemClick(Unit unit) {
                                        BarCode.setText(unit.getBarCode());
                                        Unit.setText(unit.getUnitName());
                                        salesOrderDetail1Data.setUnitID(unit.getUnitID());
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
