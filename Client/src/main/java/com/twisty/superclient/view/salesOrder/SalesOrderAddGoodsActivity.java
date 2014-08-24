package com.twisty.superclient.view.salesOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

public class SalesOrderAddGoodsActivity extends BaseActivity {
    private SalesOrderDetail1Data salesOrderDetail1Data = new SalesOrderDetail1Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_add_goods);
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
            salesOrderDetail1Data.setStoreID(SuperClient.getDefaultStoreID());
            Intent intent = new Intent();
            intent.putExtra("Data",salesOrderDetail1Data);
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
