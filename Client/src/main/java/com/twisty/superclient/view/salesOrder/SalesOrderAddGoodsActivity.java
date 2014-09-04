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
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.PriceUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.filter.GoodsFilterActivity;
import com.twisty.superclient.view.filter.UnitPop;
import com.twisty.superclient.view.salesBill.FragmentSalesBillDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class SalesOrderAddGoodsActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private SalesOrderDetail1Data salesOrderDetail1Data = new SalesOrderDetail1Data();
    private ArrayList<SalesOrderDetail1Data> salesOrderDetail1Datas = new ArrayList<SalesOrderDetail1Data>();
    private TextView GoodsName, Spec, Unit, ChargeDate;
    private EditText GoodsCode, BarCode, UnitQuanty, OrigTaxPrice, Disc, TaxPrice, UnitPrice, TaxRate, TaxAmt, Amount;
    private UnitDao unitDao;
    private Trader trader;
    private PriceUtil priceUtil;
    private BigDecimal price;
    private int type;

    private GoodsDao goodsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_add_goods);
        trader = (Trader) getIntent().getSerializableExtra("Trader");
        type = getIntent().getIntExtra("Type", FragmentSalesBillDetail.ADDGOODS);
        SalesOrderDetail1Data currentData = (SalesOrderDetail1Data) getIntent().getSerializableExtra("CurrentData");
        priceUtil = new PriceUtil(this);
        DaoSession session = SuperClient.getDaoSession(this);
        unitDao = session.getUnitDao();
        goodsDao = session.getGoodsDao();
        ChargeDate = (TextView) findViewById(R.id.ChargeDate);
        GoodsName = (TextView) findViewById(R.id.GoodsName);
        Spec = (TextView) findViewById(R.id.Spec);
        Unit = (TextView) findViewById(R.id.Unit);
        GoodsCode = (EditText) findViewById(R.id.GoodsCode);
        BarCode = (EditText) findViewById(R.id.BarCode);
        UnitQuanty = (EditText) findViewById(R.id.UnitQuanty);
        OrigTaxPrice = (EditText) findViewById(R.id.OrigTaxPrice);
        Disc = (EditText) findViewById(R.id.Disc);
        TaxPrice = (EditText) findViewById(R.id.TaxPrice);
        UnitPrice = (EditText) findViewById(R.id.UnitPrice);
        TaxRate = (EditText) findViewById(R.id.TaxRate);
        TaxAmt = (EditText) findViewById(R.id.TaxAmt);
        Amount = (EditText) findViewById(R.id.Amount);
        GoodsName.setOnClickListener(this);
        ChargeDate.setOnClickListener(this);
        if (type == FragmentSalesBillDetail.UPDATAGOODS && currentData != null) {
            salesOrderDetail1Data = currentData;
            price = new BigDecimal(currentData.getOrigPrice() + "");
            ChargeDate.setText(salesOrderDetail1Data.getChargeDate());
            BarCode.setText(salesOrderDetail1Data.getBarCode());
            GoodsName.setText(salesOrderDetail1Data.getGoodsName());
            Spec.setText(salesOrderDetail1Data.getSpecs());
            Unit.setText(salesOrderDetail1Data.getUnitName());

            GoodsCode.setText(salesOrderDetail1Data.getGoodsCode());
            UnitQuanty.setText(salesOrderDetail1Data.getUnitQuantity() + "");
            OrigTaxPrice.setText(salesOrderDetail1Data.getOrigTaxPrice() + "");
            Disc.setText(salesOrderDetail1Data.getDisc() + "");
            TaxPrice.setText(salesOrderDetail1Data.getTaxPrice() + "");
            UnitPrice.setText(salesOrderDetail1Data.getUnitPrice() + "");
            TaxRate.setText(salesOrderDetail1Data.getTaxRate() + "");
            TaxAmt.setText(salesOrderDetail1Data.getTaxAmt() + "");
            Amount.setText(salesOrderDetail1Data.getAmount() + "");
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
                    UnitQuanty.setText("1");
                    UnitQuanty.setSelection(1);
                    UnitQuanty.requestFocus();
                }
            }
        });

        BarCode.setOnFocusChangeListener(this);
        UnitQuanty.setOnFocusChangeListener(this);
        TaxPrice.setOnFocusChangeListener(this);
        Disc.setOnFocusChangeListener(this);
        OrigTaxPrice.setOnFocusChangeListener(this);
        UnitPrice.setOnFocusChangeListener(this);

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
                                salesOrderDetail1Data.setGoodsName(goods.getGoodsName());
                                salesOrderDetail1Data.setGoodsCode(goods.getGoodsCode());
                                salesOrderDetail1Data.setSpecs(goods.getSpecs());
                                salesOrderDetail1Data.setUnitID(unit.getUnitID());
                                salesOrderDetail1Data.setUnitRate(unit.getRate());
                                salesOrderDetail1Data.setUnitName(unit.getUnitName());


                                UnitQuanty.setText("1");
                                UnitQuanty.setSelection(1);
////                                        UnitQuanty.requestFocus();
                                TaxRate.setText(17 + "");

                                price = new BigDecimal(priceUtil.getPrice(trader, unit.getUnitID()) + "");//不含税价/业务单价
                                salesOrderDetail1Data.setOrigPrice(price.doubleValue());
                                BigDecimal sprice = new BigDecimal(unit.getSPrice() + "");//参考价

                                BigDecimal disc = new BigDecimal(1 + "");
                                if (sprice.compareTo(new BigDecimal(0 + "")) != 0) {
                                    disc = price.divide(sprice, 8, BigDecimal.ROUND_HALF_UP);//折扣
                                }
                                Disc.setText(disc.multiply(new BigDecimal(100 + "")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());


                                UnitPrice.setText(calUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                BigDecimal origTaxPrice = calOrignPrice();
                                OrigTaxPrice.setText(origTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//                                        BigDecimal taxPrice = origTaxPrice.multiply(disc);
                                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                BigDecimal taxAmt = calTaxAmt();
                                TaxAmt.setText(taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());


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

            try {
                salesOrderDetail1Data.setOrigTaxPrice(Double.valueOf(OrigTaxPrice.getText().toString()));
                salesOrderDetail1Data.setDisc(Double.valueOf(Disc.getText().toString()));
                salesOrderDetail1Data.setTaxPrice(Double.valueOf(TaxPrice.getText().toString()));
                salesOrderDetail1Data.setTaxRate(Double.valueOf(TaxRate.getText().toString()));
                salesOrderDetail1Data.setTaxAmt(Double.valueOf(TaxAmt.getText().toString()));
                salesOrderDetail1Data.setUnitPrice(Double.valueOf(UnitPrice.getText().toString()));
                salesOrderDetail1Data.setUnitQuantity(Double.valueOf(UnitQuanty.getText().toString()));
                salesOrderDetail1Data.setGoodsAmt(calAmount().subtract(new BigDecimal(TaxAmt.getText().toString())).doubleValue());
                salesOrderDetail1Data.setAmount(calAmount().doubleValue());
                salesOrderDetail1Data.setQuantity(Double.valueOf(UnitQuanty.getText().toString()) * salesOrderDetail1Data.getUnitRate());
            } catch (NumberFormatException nfe) {
                CommonUtil.showToastError(this, "数据格式不正确,请确认!", null);
                return true;
            }
            if (type == FragmentSalesBillDetail.UPDATAGOODS) {


                Intent intent = new Intent();
                intent.putExtra("Data", salesOrderDetail1Data);
                setResult(RESULT_OK, intent);
                finish();
            } else {

                salesOrderDetail1Data.setIsLargess(0);

                salesOrderDetail1Data.setBillID(-1L);
                salesOrderDetail1Data.setItemNo(-1);
//                salesOrderDetail1Data.setAPrice(123.123);


                salesOrderDetail1Datas.add(salesOrderDetail1Data);
                Intent intent = new Intent();
                intent.putExtra("com.twisty.superclient.Data", salesOrderDetail1Datas);
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
            case R.id.ChargeDate:

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
                        salesOrderDetail1Data.setGoodsName(goods.getGoodsName());
                        salesOrderDetail1Data.setGoodsCode(goods.getGoodsCode());
                        salesOrderDetail1Data.setSpecs(goods.getSpecs());
                        QueryBuilder<com.twisty.superclient.bean.Unit> unitQueryBuilder = unitDao.queryBuilder();
                        unitQueryBuilder.where(UnitDao.Properties.GoodsID.eq(goods.getGoodsID()));
                        final List<Unit> units = unitQueryBuilder.list();
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
                                        salesOrderDetail1Data.setUnitName(unit.getUnitName());
                                        salesOrderDetail1Data.setUnitRate(unit.getRate());
                                        salesOrderDetail1Data.setBarCode(unit.getBarCode());
                                        UnitQuanty.setText("1");
                                        UnitQuanty.setSelection(1);
                                        UnitQuanty.requestFocus();
                                        TaxRate.setText(17 + "");

                                        price = new BigDecimal(priceUtil.getPrice(trader, unit.getUnitID()) + "");//不含税价/业务单价
                                        BigDecimal sprice = new BigDecimal(unit.getSPrice() + "");//参考价

                                        BigDecimal disc = new BigDecimal(1 + "");
                                        if (sprice.compareTo(new BigDecimal(0 + "")) != 0) {
                                            disc = price.divide(sprice, 8, BigDecimal.ROUND_HALF_UP);//折扣
                                        }
                                        Disc.setText(disc.multiply(new BigDecimal(100 + "")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                        UnitPrice.setText(calUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                        BigDecimal origTaxPrice = calOrignPrice();
                                        OrigTaxPrice.setText(origTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//                                        BigDecimal taxPrice = origTaxPrice.multiply(disc);
                                        TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                        BigDecimal taxAmt = calTaxAmt();
                                        TaxAmt.setText(taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                        Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {
            case R.id.BarCode:
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
                                salesOrderDetail1Data.setGoodsName(goods.getGoodsName());
                                salesOrderDetail1Data.setGoodsCode(goods.getGoodsCode());
                                salesOrderDetail1Data.setSpecs(goods.getSpecs());
                                salesOrderDetail1Data.setUnitID(unit.getUnitID());
                                salesOrderDetail1Data.setUnitRate(unit.getRate());
                                salesOrderDetail1Data.setUnitName(unit.getUnitName());
                                salesOrderDetail1Data.setUnitPrice(priceUtil.getPrice(trader, unit.getUnitID()));


                                UnitQuanty.setText("1");
                                UnitQuanty.setSelection(1);
////                                        UnitQuanty.requestFocus();
                                TaxRate.setText(17 + "");

                                price = new BigDecimal(priceUtil.getPrice(trader, unit.getUnitID()) + "");//不含税价/业务单价
                                salesOrderDetail1Data.setOrigPrice(price.doubleValue());
                                BigDecimal sprice = new BigDecimal(unit.getSPrice() + "");//参考价

                                BigDecimal disc = new BigDecimal(1 + "");
                                if (sprice.compareTo(new BigDecimal(0 + "")) != 0) {
                                    disc = price.divide(sprice, 8, BigDecimal.ROUND_HALF_UP);//折扣
                                }
                                Disc.setText(disc.multiply(new BigDecimal(100 + "")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());


                                UnitPrice.setText(calUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                BigDecimal origTaxPrice = calOrignPrice();
                                OrigTaxPrice.setText(origTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//                                        BigDecimal taxPrice = origTaxPrice.multiply(disc);
                                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                BigDecimal taxAmt = calTaxAmt();
                                TaxAmt.setText(taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());


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
                break;
            case R.id.UnitQuanty:
                if (!hasFocus) {
                    Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                }
                break;
            case R.id.Disc:
                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                UnitPrice.setText(calUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                break;
            case R.id.TaxPrice:
                //TODO 1
                break;
            case R.id.OrigTaxPrice:
//                OrigTaxPrice.setText(calOrignPrice().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                break;
            case R.id.UnitPrice:
                OrigTaxPrice.setText(calOrignPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                break;
        }
    }

    private BigDecimal calAmount() {
        BigDecimal amount;
        try {
            amount = new BigDecimal(UnitQuanty.getText().toString()).multiply(new BigDecimal(TaxPrice.getText().toString()));
        } catch (NumberFormatException nfe) {
            amount = new BigDecimal(0);
        }
        return amount;
    }

    private BigDecimal calTaxAmt() throws NumberFormatException {
        return price
                .multiply(new BigDecimal(UnitQuanty.getText().toString()))
                .multiply(new BigDecimal(Disc.getText().toString()).divide(new BigDecimal(100 + ""), 8, BigDecimal.ROUND_HALF_UP))
                .multiply(new BigDecimal(TaxRate.getText().toString()).divide(new BigDecimal(100 + ""), 8, BigDecimal.ROUND_HALF_UP));
    }

    private BigDecimal calTaxPrice() throws NumberFormatException {
        return price
                .multiply(new BigDecimal(Disc.getText().toString()).divide(new BigDecimal(100 + ""), 8, BigDecimal.ROUND_HALF_UP))
                .multiply(
                        new BigDecimal(1 + "").add((new BigDecimal(TaxRate.getText().toString())
                                .divide(new BigDecimal(100 + ""), 8, BigDecimal.ROUND_HALF_UP))));
    }

    private BigDecimal calOrignPrice() throws NumberFormatException {
        return price
                .multiply(
                        new BigDecimal(1 + "")
                                .add(new BigDecimal(TaxRate.getText().toString())
                                        .divide(new BigDecimal(100 + ""), 8, BigDecimal.ROUND_HALF_UP)));
    }

    private BigDecimal calUnitPrice() {
        BigDecimal unitPrice;
        try {
            unitPrice = price
                    .multiply(new BigDecimal(Disc.getText().toString()).divide(new BigDecimal(100 + ""), 8, BigDecimal.ROUND_HALF_UP));
        } catch (NumberFormatException nfe) {
            unitPrice = new BigDecimal(0);
        }
        return unitPrice;
    }

    private Integer getTaxRateByNoteType(AMKind noteType) {
        if (noteType != null) {
            Long noteTypeID = noteType.getID();
            if (noteTypeID == 1) {
                return 0;
            } else if (noteTypeID == 2) {
                return 6;
            } else if (noteTypeID == 3) {
                return 17;
            }
        }
        return 0;
    }
}
