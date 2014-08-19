package com.twisty.superclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Detail1Data;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.filter.GoodsFilterActivity;
import com.twisty.superclient.view.filter.StorePop;

public class AddGoodsActivity extends BaseActivity implements View.OnClickListener{
    private StoreDao storeDao;
    private TextView StoreCode,GoodsName,Spec,Unit;
    private EditText GoodsCode,BarCode,Quantity,OrigTaxPrice,Disc,TaxPrice,UnitPrice,TaxRate,TaxAmt,Amount;
    private Detail1Data detail1Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        DaoSession session = SuperClient.getDaoSession(this);
        storeDao = session.getStoreDao();
        StoreCode = (TextView) findViewById(R.id.StoreCode);
        GoodsName = (TextView) findViewById(R.id.GoodsName);
        Spec = (TextView) findViewById(R.id.Spec);
        Unit = (TextView) findViewById(R.id.Unit);
        GoodsCode = (EditText) findViewById(R.id.GoodsCode);
        BarCode = (EditText) findViewById(R.id.Barcode);
        Quantity = (EditText) findViewById(R.id.Quantity);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_goods, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.commit) {

            //TODO 返回货品信息

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.StoreCode:
                StorePop storePop = new StorePop(this,storeDao.loadAll(),new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        StoreCode.setText(store.getStoreCode());
                    }
                });
                storePop.showPopupWindow(v);
                break;
            case R.id.GoodsName:
                Intent intent = new Intent(this, GoodsFilterActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 1:
                    Goods goods = (Goods) data.getSerializableExtra("Data");
                    GoodsName.setText(goods.getGoodsName());
                    GoodsCode.setText(goods.getGoodsCode());
                    Spec.setText(goods.getSpecs());
                    break;
            }
        }
    }
}
