package com.twisty.superclient.view;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.MasterData;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.filter.FilterActivity;

public class SellOrderActivity extends BaseActivity implements View.OnClickListener, ActionBar.TabListener, FragmentHeader.OnSaveListener, FragmentOrderDetail.OnSaveListener {
    private ActionBar actionBar;
    private Button searchBTN,saveBTN,resetBTN;
    private boolean isCommit;
    private Params params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_order);
        searchBTN = (Button) findViewById(R.id.search);
        saveBTN = (Button) findViewById(R.id.save);
        resetBTN = (Button) findViewById(R.id.reset);
        searchBTN.setOnClickListener(this);
        saveBTN.setOnClickListener(this);
        resetBTN.setOnClickListener(this);
        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab headerTab1 = actionBar.newTab().setText("表头").setTabListener(this);
        ActionBar.Tab orderDetailTab = actionBar.newTab().setText("明细").setTabListener(this);


        actionBar.addTab(headerTab1);
        actionBar.addTab(orderDetailTab);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_module, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.print:
                Intent intent = new Intent(this, BluetoothListActivity.class);
                startActivity(intent);
                return true;
            case R.id.preOrder:
                //TODO 前单
                CommonUtil.showToastInfo(SellOrderActivity.this,"前单");
                return true;
            case R.id.nextOrder:
                //TODO 后单
                CommonUtil.showToastInfo(SellOrderActivity.this,"后单");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        if (tab.getText().equals("明细")) {
            Fragment fragmentOrderDetail = FragmentOrderDetail.newInstance();
            fragmentOrderDetail.setRetainInstance(true);
            ft.replace(R.id.sellOrder, fragmentOrderDetail,"detail");
        } else if (tab.getText().equals("表头")) {
            Fragment fragmentHeader = FragmentHeader.newInstance(null);
            fragmentHeader.setRetainInstance(true);
            ft.replace(R.id.sellOrder,fragmentHeader ,"header");
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        ft.commit();
    }


    @Override
    public void onSaveDetail(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.search:
                Intent intent = new Intent(this,FilterActivity.class);
                intent.putExtra("BillType","s_sale");
                intent.putExtra("BillKind",1);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onSaveHeader(MasterData masterData) {
        log.i(masterData.getTraderID());
    }

}
