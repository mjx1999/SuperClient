package com.twisty.superclient.view.transfer;

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
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.TransferDetail1Data;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.filter.GoodsFilterActivity;
import com.twisty.superclient.view.filter.UnitPop;
import com.twisty.superclient.view.salesBill.FragmentSalesBillDetail;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class TransferAddGoodsActivity extends BaseActivity implements View.OnClickListener {
    private TransferDetail1Data transferDetail1Data = new TransferDetail1Data();
    private ArrayList<TransferDetail1Data> transferDetail1Datas = new ArrayList<TransferDetail1Data>();
    private UnitDao unitDao;
    private GoodsDao goodsDao;
    private EditText Barcode, GoodsCode, Quantity, UnitPrice, Amount;
    private TextView GoodsName, Spec, Unit;

    //    private PriceUtil priceUtil;
    private BigDecimal price;
    private int type;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_add_goods);
        type = getIntent().getIntExtra("Type", FragmentTransferDetail.ADDGOODS);
        TransferDetail1Data currentData = (TransferDetail1Data) getIntent().getSerializableExtra("CurrentData");
        decimalFormat = new DecimalFormat("#.########");
        goodsDao = SuperClient.getDaoSession(this).getGoodsDao();
        unitDao = SuperClient.getDaoSession(this).getUnitDao();
        Barcode = (EditText) findViewById(R.id.BarCode);
        GoodsCode = (EditText) findViewById(R.id.GoodsCode);
        Quantity = (EditText) findViewById(R.id.Quantity);
        UnitPrice = (EditText) findViewById(R.id.UnitPrice);
        Amount = (EditText) findViewById(R.id.Amount);
        GoodsName = (TextView) findViewById(R.id.GoodsName);
        Spec = (TextView) findViewById(R.id.Spec);
        Unit = (TextView) findViewById(R.id.Unit);
        GoodsName.setOnClickListener(this);

        if (type == FragmentTransferDetail.UPDATAGOODS && currentData != null) {
            transferDetail1Data = currentData;
            price = new BigDecimal(decimalFormat.format(currentData.getPrice()));

            Barcode.setText(currentData.getBarCode());
            GoodsCode.setText(transferDetail1Data.getGoodsCode());
            GoodsName.setText(transferDetail1Data.getGoodsName());
            Spec.setText(transferDetail1Data.getSpecs());
            Unit.setText(transferDetail1Data.getUnitName());
            Quantity.setText(decimalFormat.format(transferDetail1Data.getUnitQuantity()));
            UnitPrice.setText(decimalFormat.format(transferDetail1Data.getUnitPrice()));
            Amount.setText(decimalFormat.format(transferDetail1Data.getAmount()));
        }

        Barcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains("\n")) {
                    Barcode.setText(s.toString().trim());
                    Unit.setEnabled(false);
                    Quantity.requestFocus();
                    Quantity.setText("1");
                    Quantity.setSelection(1);
                }
            }
        });
        Barcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String barCode = Barcode.getText().toString().trim();
                    if (barCode.length() > 0) {
                        QueryBuilder<com.twisty.superclient.bean.Unit> unitQueryBuilder = unitDao.queryBuilder();
                        com.twisty.superclient.bean.Unit unit = unitQueryBuilder.where(UnitDao.Properties.BarCode.eq(barCode)).unique();
                        if (unit != null) {
                            QueryBuilder<Goods> goodsQueryBuilder = goodsDao.queryBuilder();
                            goodsQueryBuilder.where(GoodsDao.Properties.GoodsID.eq(unit.getGoodsID()));
                            Goods goods = goodsQueryBuilder.unique();
                            if (goods != null) {
                                transferDetail1Data.setGoodsID(goods.getGoodsID());
                                transferDetail1Data.setUnitID(unit.getUnitID());
                                GoodsCode.setText(goods.getGoodsCode());
                                GoodsName.setText(goods.getGoodsName());
                                Spec.setText(goods.getSpecs());
                                Unit.setText(unit.getUnitName());
                                UnitPrice.setText(unit.getSPrice().toString());
                                transferDetail1Data.setGoodsCode(goods.getGoodsCode());
                                transferDetail1Data.setGoodsName(goods.getGoodsName());
                                transferDetail1Data.setSpecs(goods.getSpecs());
                                transferDetail1Data.setUnitName(unit.getUnitName());
                                transferDetail1Data.setUnitRate(unit.getRate());
                            } else {
                                CommonUtil.showToastError(TransferAddGoodsActivity.this, "没有找到当前条码的货品!", null);
                            }
                        } else {
                            GoodsCode.setText("");
                            GoodsName.setText("");
                            Spec.setText("");
                            Unit.setText("");
                            CommonUtil.showToastError(TransferAddGoodsActivity.this, "没有找到当前条码的货品!", null);
                        }

                    }
                }
            }
        });
        Quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {

                        BigDecimal amount = new BigDecimal(UnitPrice.getText().toString()).multiply(new BigDecimal(Quantity.getText().toString()));
                        transferDetail1Data.setAmount(amount.doubleValue());
                        Amount.setText(amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    } catch (Exception e) {
                        Amount.setText(decimalFormat.format(0));
                    }
                }
            }
        });
        UnitPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {

                    BigDecimal amount = new BigDecimal(UnitPrice.getText().toString()).multiply(new BigDecimal(Quantity.getText().toString()));
                    transferDetail1Data.setAmount(amount.doubleValue());
                    Amount.setText(amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                } catch (Exception e) {
                    Amount.setText(decimalFormat.format(0));
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
                transferDetail1Data.setUnitQuantity(Double.valueOf(Quantity.getText().toString()));
            } catch (NumberFormatException e) {
                CommonUtil.showToastError(this, "调入数量不能为空或者非数字!", null);
                return true;

            }
            try {
                transferDetail1Data.setUnitPrice(Double.valueOf(UnitPrice.getText().toString()));
            } catch (NumberFormatException e) {
                CommonUtil.showToastError(this, "单价不能为空或者非数字!", null);
                return true;
            }
            try {
                transferDetail1Data.setAmount(Double.valueOf(Amount.getText().toString()));
            } catch (NumberFormatException e) {
                CommonUtil.showToastError(this, "总金额不能为空或者非数字!", null);
                return true;

            }
            if (type == FragmentSalesBillDetail.UPDATAGOODS) {


                Intent intent = new Intent();
                intent.putExtra("Data", transferDetail1Data);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                transferDetail1Data.setBillID(-1L);
                transferDetail1Data.setItemNo(-1);
                transferDetail1Datas.add(transferDetail1Data);
                Intent intent = new Intent();
                intent.putExtra("com.twisty.superclient.Data", transferDetail1Datas);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Goods goods = (Goods) data.getSerializableExtra("Data");
                    if (goods != null) {
                        transferDetail1Data.setGoodsID(goods.getGoodsID());
                        Barcode.setText("");
                        GoodsName.setText(goods.getGoodsName());
                        GoodsCode.setText(goods.getGoodsCode());
                        Spec.setText(goods.getSpecs());
                        transferDetail1Data.setGoodsCode(goods.getGoodsCode());
                        transferDetail1Data.setGoodsName(goods.getGoodsName());
                        transferDetail1Data.setSpecs(goods.getSpecs());
                        QueryBuilder<com.twisty.superclient.bean.Unit> unitQueryBuilder = unitDao.queryBuilder();
                        unitQueryBuilder.where(UnitDao.Properties.GoodsID.eq(goods.getGoodsID()));
                        final List<com.twisty.superclient.bean.Unit> units = unitQueryBuilder.list();
                        Unit.setEnabled(true);
                        Unit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UnitPop unitPop = new UnitPop(TransferAddGoodsActivity.this, units, new UnitPop.onItemClickListener() {
                                    @Override
                                    public void onItemClick(Unit unit) {
                                        Barcode.setText(unit.getBarCode());
                                        Unit.setText(unit.getUnitName());
                                        UnitPrice.setText(unit.getSPrice().toString());


                                        Quantity.setText("1");
                                        Quantity.setSelection(1);
                                        Quantity.requestFocus();
                                        transferDetail1Data.setUnitID(unit.getUnitID());
                                        transferDetail1Data.setUnitName(unit.getUnitName());
                                        transferDetail1Data.setBarCode(unit.getBarCode());
                                        transferDetail1Data.setUnitRate(unit.getRate());
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
