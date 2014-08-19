package com.twisty.superclient.view.filter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.GoodsAdapter;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.List;

public class GoodsFilterActivity extends BaseActivity implements View.OnClickListener {
    private EditText filterStr;
    private Button filterBtn;
    private ListView listView;

    private GoodsAdapter adapter;
    private List<Goods> adapterData;
    private GoodsDao goodsDao;
    private ProgressDialog pd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_filter);
        filterStr = (EditText) findViewById(R.id.filterStr);
        filterBtn = (Button) findViewById(R.id.filterBtn);
        listView = (ListView) findViewById(R.id.listView);
        filterBtn.setOnClickListener(this);
        pd = ProgressDialog.show(this,null,"正在检索数据");
        goodsDao = SuperClient.getDaoSession(this).getGoodsDao();
//        adapterData = goodsDao.queryBuilder().where(EmployeeDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID())).list();
        adapterData = goodsDao.loadAll();
        adapter = new GoodsAdapter(this, adapterData);
        listView.setAdapter(adapter);
        if(pd!=null)pd.dismiss();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("Data",(Goods)parent.getItemAtPosition(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
