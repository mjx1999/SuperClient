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
import com.twisty.superclient.bean.AMKind;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class SalesBillAddGoodsActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    ArrayList<SalesBillDetail1Data> salesBillDetail1Datas = new ArrayList<SalesBillDetail1Data>();
    private TextView StoreCode, GoodsName, Spec, Unit;
    private EditText GoodsCode, BarCode, UnitQuanty, OrigPrice, OrigTaxPrice, Disc, TaxPrice, UnitPrice, TaxRate, TaxAmt, Amount;
    private SalesBillDetail1Data salesBillDetail1Data = new SalesBillDetail1Data();
    private StoreDao storeDao;
    private UnitDao unitDao;
    private GoodsDao goodsDao;
    private Trader trader;
    private AMKind noteType;
    private PriceUtil priceUtil;
    private BigDecimal price;
    private int type;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_bill_add_goods);
        decimalFormat = new DecimalFormat("#.########");
        type = getIntent().getIntExtra("Type", FragmentSalesBillDetail.ADDGOODS);
        SalesBillDetail1Data currentData = (SalesBillDetail1Data) getIntent().getSerializableExtra("CurrentData");
        priceUtil = new PriceUtil(this);
        DaoSession session = SuperClient.getDaoSession(this);
        trader = (Trader) getIntent().getSerializableExtra("Trader");
        noteType = (AMKind) getIntent().getSerializableExtra("NoteType");
        storeDao = session.getStoreDao();
        unitDao = session.getUnitDao();
        goodsDao = session.getGoodsDao();
        StoreCode = (TextView) findViewById(R.id.StoreCode);
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
        OrigPrice = (EditText) findViewById(R.id.OrigPrice);
        StoreCode.setOnClickListener(this);
        GoodsName.setOnClickListener(this);

        QueryBuilder<Store> queryBuilder = storeDao.queryBuilder();
        queryBuilder.where(StoreDao.Properties.StoreCode.eq(SuperClient.getDefaultStoreCode()));
        Store store = queryBuilder.unique();
        StoreCode.setText(store.getStoreCode() + " " + store.getStoreName());
        salesBillDetail1Data.setStoreID(store.getStoreID());
        salesBillDetail1Data.setStoreName(store.getStoreName());
        salesBillDetail1Data.setStoreCode(store.getStoreCode());
        if (type == FragmentSalesBillDetail.UPDATAGOODS && currentData != null) {
            salesBillDetail1Data = currentData;
            price = new BigDecimal(decimalFormat.format(currentData.getOrigPrice()));

            BarCode.setText(salesBillDetail1Data.getBarCode());
            StoreCode.setText(salesBillDetail1Data.getStoreCode() + " " + salesBillDetail1Data.getStoreName());
            GoodsName.setText(salesBillDetail1Data.getGoodsName());
            Spec.setText(salesBillDetail1Data.getSpecs());
            Unit.setText(salesBillDetail1Data.getUnitName());
            OrigPrice.setText(decimalFormat.format(salesBillDetail1Data.getOrigPrice()));
            GoodsCode.setText(salesBillDetail1Data.getGoodsCode());
            UnitQuanty.setText(decimalFormat.format(salesBillDetail1Data.getUnitQuantity()));
            OrigTaxPrice.setText(decimalFormat.format(salesBillDetail1Data.getOrigTaxPrice()));
            Disc.setText(decimalFormat.format(salesBillDetail1Data.getDisc()));
            TaxPrice.setText(decimalFormat.format(salesBillDetail1Data.getTaxPrice()));
            UnitPrice.setText(decimalFormat.format(salesBillDetail1Data.getUnitPrice()));
            TaxRate.setText(decimalFormat.format(salesBillDetail1Data.getTaxRate()));
            TaxAmt.setText(decimalFormat.format(salesBillDetail1Data.getTaxAmt()));
            Amount.setText(decimalFormat.format(salesBillDetail1Data.getAmount()));
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
        OrigPrice.setOnFocusChangeListener(this);
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

            try {
                salesBillDetail1Data.setOrigTaxPrice(Double.valueOf(OrigTaxPrice.getText().toString()));
                salesBillDetail1Data.setDisc(Double.valueOf(Disc.getText().toString()));
                salesBillDetail1Data.setTaxPrice(Double.valueOf(TaxPrice.getText().toString()));
                salesBillDetail1Data.setTaxRate(Double.valueOf(TaxRate.getText().toString()));
                salesBillDetail1Data.setTaxAmt(Double.valueOf(TaxAmt.getText().toString()));
                salesBillDetail1Data.setUnitPrice(Double.valueOf(UnitPrice.getText().toString()));
                salesBillDetail1Data.setUnitQuantity(Double.valueOf(UnitQuanty.getText().toString()));
                salesBillDetail1Data.setGoodsAmt(calAmount().subtract(new BigDecimal(TaxAmt.getText().toString())).doubleValue());
                salesBillDetail1Data.setAmount(calAmount().doubleValue());
                salesBillDetail1Data.setQuantity(Double.valueOf(UnitQuanty.getText().toString()) * salesBillDetail1Data.getUnitRate());
            } catch (NumberFormatException nfe) {
                CommonUtil.showToastError(this, "数据格式不正确,请确认!", null);
                return true;
            }
            if (type == FragmentSalesBillDetail.UPDATAGOODS) {


                Intent intent = new Intent();
                intent.putExtra("Data", salesBillDetail1Data);
                setResult(RESULT_OK, intent);
                finish();
            } else {

                salesBillDetail1Data.setIsLargess(0);

                salesBillDetail1Data.setBillID(-1L);
                salesBillDetail1Data.setItemNO(-1);
//                salesBillDetail1Data.setAPrice(123.123);


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
                        salesBillDetail1Data.setStoreCode(store.getStoreCode());
                        salesBillDetail1Data.setStoreName(store.getStoreName());
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
                        salesBillDetail1Data.setGoodsName(goods.getGoodsName());
                        salesBillDetail1Data.setGoodsCode(goods.getGoodsCode());
                        salesBillDetail1Data.setSpecs(goods.getSpecs());
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
                                        salesBillDetail1Data.setUnitName(unit.getUnitName());
                                        salesBillDetail1Data.setUnitRate(unit.getRate());
                                        salesBillDetail1Data.setBarCode(unit.getBarCode());
                                        UnitQuanty.setText("1");
                                        UnitQuanty.setSelection(1);
                                        UnitQuanty.requestFocus();
                                        TaxRate.setText(getTaxRateByNoteType(noteType) + "");

                                        price = new BigDecimal(priceUtil.getPrice(trader, unit.getUnitID()) + "");//不含税价/业务单价
                                        OrigPrice.setText(price.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

                                        salesBillDetail1Data.setOrigPrice(price.doubleValue());
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
                                salesBillDetail1Data.setGoodsID(goods.getGoodsID());
                                salesBillDetail1Data.setGoodsName(goods.getGoodsName());
                                salesBillDetail1Data.setGoodsCode(goods.getGoodsCode());
                                salesBillDetail1Data.setSpecs(goods.getSpecs());
                                salesBillDetail1Data.setUnitID(unit.getUnitID());
                                salesBillDetail1Data.setUnitRate(unit.getRate());
                                salesBillDetail1Data.setUnitName(unit.getUnitName());


                                UnitQuanty.setText("1");
                                UnitQuanty.setSelection(1);
////                                        UnitQuanty.requestFocus();
                                TaxRate.setText(getTaxRateByNoteType(noteType) + "");

                                price = new BigDecimal(priceUtil.getPrice(trader, unit.getUnitID()) + "");//不含税价/业务单价
                                OrigPrice.setText(price.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                salesBillDetail1Data.setOrigPrice(price.doubleValue());
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
//            case R.id.TaxPrice:
//                //TODO 1
//                break;
//            case R.id.OrigTaxPrice:
//                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//                TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//                break;
            case R.id.UnitPrice:
                OrigTaxPrice.setText(calOrignPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                break;
            case R.id.OrigPrice:
                if (!hasFocus) {
                    price = new BigDecimal(OrigPrice.getText().toString());
                    salesBillDetail1Data.setOrigPrice(price.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    OrigTaxPrice.setText(calOrignPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    TaxPrice.setText(calTaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    TaxAmt.setText(calTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    Amount.setText(calAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                }
                break;
        }
    }

    //
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
