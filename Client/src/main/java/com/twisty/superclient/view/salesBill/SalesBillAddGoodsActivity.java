package com.twisty.superclient.view.salesBill;

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
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.PriceUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.filter.GoodsFilterActivity;
import com.twisty.superclient.view.filter.StorePop;
import com.twisty.superclient.view.filter.UnitPop;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class SalesBillAddGoodsActivity extends BaseActivity implements View.OnClickListener {
    ArrayList<SalesBillDetail1Data> salesBillDetail1Datas = new ArrayList<SalesBillDetail1Data>();
    private TextView StoreCode, GoodsName, Spec, Unit;
    private EditText GoodsCode, BarCode, UnitQuanty, OrigTaxPrice, Disc, TaxPrice, UnitPrice, TaxRate, TaxAmt, Amount;
    private SalesBillDetail1Data salesBillDetail1Data = new SalesBillDetail1Data();
    private StoreDao storeDao;
    private UnitDao unitDao;
    private GoodsDao goodsDao;
    private Trader trader;
    private PriceUtil priceUtil;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_bill_add_goods);
        type = getIntent().getIntExtra("Type", FragmentSalesBillDetail.ADDGOODS);
        SalesBillDetail1Data currentData = (SalesBillDetail1Data) getIntent().getSerializableExtra("CurrentData");
        priceUtil = new PriceUtil(this);
        DaoSession session = SuperClient.getDaoSession(this);
        trader = (Trader) getIntent().getSerializableExtra("Trader");
        storeDao = session.getStoreDao();
        unitDao = session.getUnitDao();
        goodsDao = session.getGoodsDao();
        StoreCode = (TextView) findViewById(R.id.StoreCode);
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
        StoreCode.setOnClickListener(this);
        GoodsName.setOnClickListener(this);
        StoreCode.setText(SuperClient.getDefaultStoreCode());
        if (type == FragmentSalesBillDetail.UPDATAGOODS && currentData != null) {
            salesBillDetail1Data = currentData;
            BarCode.setText(salesBillDetail1Data.getBarCode());
            StoreCode.setText(salesBillDetail1Data.getStoreCode() + " " + salesBillDetail1Data.getStoreName());
            GoodsName.setText(salesBillDetail1Data.getGoodsName());
            Spec.setText(salesBillDetail1Data.getSpecs());
            Unit.setText(salesBillDetail1Data.getUnitName());

            GoodsCode.setText(salesBillDetail1Data.getGoodsCode());
            UnitQuanty.setText(salesBillDetail1Data.getUnitQuantity() + "");
            OrigTaxPrice.setText(salesBillDetail1Data.getOrigTaxPrice() + "");
            Disc.setText(salesBillDetail1Data.getDisc() + "");
            TaxPrice.setText(salesBillDetail1Data.getTaxPrice() + "");
            UnitPrice.setText(salesBillDetail1Data.getUnitPrice() + "");
            TaxRate.setText(salesBillDetail1Data.getTaxRate() + "");
            TaxAmt.setText(salesBillDetail1Data.getTaxAmt() + "");
            Amount.setText(salesBillDetail1Data.getAmount() + "");
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
                                salesBillDetail1Data.setGoodsID(goods.getGoodsID());
                                salesBillDetail1Data.setUnitID(unit.getUnitID());
                                GoodsCode.setText(goods.getGoodsCode());
                                GoodsName.setText(goods.getGoodsName());
                                Spec.setText(goods.getSpecs());
                                Unit.setText(unit.getUnitName());
                            } else {
                                CommonUtil.showToastError(SalesBillAddGoodsActivity.this, "没有找到当前条码的货品!", null);
                            }
                        } else {
                            GoodsCode.setText("");
                            GoodsName.setText("");
                            Spec.setText("");
                            Unit.setText("");
                            CommonUtil.showToastError(SalesBillAddGoodsActivity.this, "没有找到当前条码的货品!", null);
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


            if (type == FragmentSalesBillDetail.UPDATAGOODS) {


                salesBillDetail1Data.setAmount(22.2);
                salesBillDetail1Data.setQuantity(23.2);
                salesBillDetail1Data.setOrigPrice(23.00);
                salesBillDetail1Data.setDisc(22.0);
                salesBillDetail1Data.setTaxPrice(28.3);
                salesBillDetail1Data.setTaxRate(288.0);
                salesBillDetail1Data.setTaxAmt(22.22);
                salesBillDetail1Data.setBillID(-1L);
                salesBillDetail1Data.setItemNO(-1);
                salesBillDetail1Data.setUnitPrice(222.22);
                salesBillDetail1Data.setUnitQuantity(333333.33);
                salesBillDetail1Data.setUnitRate(13.2);
                salesBillDetail1Data.setAmount(67868744.44);
                salesBillDetail1Data.setGoodsAmt(124466744.1231);
                salesBillDetail1Data.setIsLargess(0);
                salesBillDetail1Data.setAPrice(12883.12);
                salesBillDetail1Data.setDisc(128831.13);
                salesBillDetail1Data.setOrigTaxPrice(234.24);


                Intent intent = new Intent();
                intent.putExtra("Data", salesBillDetail1Data);
                setResult(RESULT_OK, intent);
                finish();
            } else {


                salesBillDetail1Data.setAmount(22.2);
                salesBillDetail1Data.setQuantity(23.2);
                salesBillDetail1Data.setOrigPrice(23.00);
                salesBillDetail1Data.setDisc(22.0);
                salesBillDetail1Data.setTaxPrice(2.3);
                salesBillDetail1Data.setTaxRate(2.33);
                salesBillDetail1Data.setTaxAmt(22.222);
                salesBillDetail1Data.setBillID(-1L);
                salesBillDetail1Data.setItemNO(-1);
                salesBillDetail1Data.setUnitPrice(222.22222);
                salesBillDetail1Data.setUnitQuantity(333333.333);
                salesBillDetail1Data.setUnitRate(13.2);
                salesBillDetail1Data.setAmount(3444444.44);
                salesBillDetail1Data.setGoodsAmt(124444.1231);
                salesBillDetail1Data.setIsLargess(0);
                salesBillDetail1Data.setAPrice(123.123);
                salesBillDetail1Data.setDisc(1231.123);
                salesBillDetail1Data.setOrigTaxPrice(234.234);
                salesBillDetail1Datas.add(salesBillDetail1Data);
                Intent intent = new Intent();
                intent.putExtra("com.twisty.superclient.Data", salesBillDetail1Datas);
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
            case R.id.StoreCode:
                StorePop storePop = new StorePop(this, storeDao.loadAll(), new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        salesBillDetail1Data.setStoreID(store.getStoreID());
                        StoreCode.setText(store.getStoreCode() + "  " + store.getStoreName());
                    }
                });
                storePop.showPopupWindow(v);
                break;
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
                    final Goods goods = (Goods) data.getSerializableExtra("Data");
                    if (goods != null) {
                        salesBillDetail1Data.setGoodsID(goods.getGoodsID());
                        BarCode.setText("");
                        GoodsName.setText(goods.getGoodsName());
                        GoodsCode.setText(goods.getGoodsCode());
                        Spec.setText(goods.getSpecs());
                        QueryBuilder<com.twisty.superclient.bean.Unit> unitQueryBuilder = unitDao.queryBuilder();
                        unitQueryBuilder.where(UnitDao.Properties.GoodsID.eq(goods.getGoodsID()));
                        final List<Unit> units = unitQueryBuilder.list();
                        Unit.setEnabled(true);
                        Unit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UnitPop unitPop = new UnitPop(SalesBillAddGoodsActivity.this, units, new UnitPop.onItemClickListener() {
                                    @Override
                                    public void onItemClick(Unit unit) {
                                        BarCode.setText(unit.getBarCode());
                                        Unit.setText(unit.getUnitName());
                                        salesBillDetail1Data.setUnitID(unit.getUnitID());
                                        double price = priceUtil.getUnitPrice(trader, unit.getUnitID(), goods);
                                        UnitPrice.setText(price + "");
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
