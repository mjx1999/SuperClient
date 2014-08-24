package com.twisty.superclient.view.salesOrder;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.twisty.superclient.R;
import com.twisty.superclient.bean.ParamsSalesOrder;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseActivity;

public class SalesOrderActivity extends BaseActivity implements View.OnClickListener ,ActionBar.TabListener{
    FragmentSalesOrderHeader fragmentSalesOrderHeader ;
    FragmentSalesOrderDetail fragmentSalesOrderDetail;
    ParamsSalesOrder paramsSalesOrder ;
    private static final int PRE_RESULT = 3, NEXT_RESULT = 4;
    private ActionBar actionBar;
    private Button searchBTN, saveBTN;
    private boolean isAddNew = true;
    private boolean isCommit;
    private ProgressDialog pd;
    private Gson gson;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(pd!=null)pd.dismiss();
            switch (msg.what) {
                case PRE_RESULT:
                    if(msg.obj!=null){
                        CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj), null);
                    }
                    break;
                case NEXT_RESULT:
                    if(msg.obj!=null){
                        CommonUtil.showToastError(SalesOrderActivity.this,String.valueOf(msg.obj),null);
                    }
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(SalesOrderActivity.this, String.valueOf(msg.obj),null);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        gson = CommonUtil.getGson();
        searchBTN = (Button) findViewById(R.id.search);
        saveBTN = (Button) findViewById(R.id.save);
        searchBTN.setOnClickListener(this);
        saveBTN.setOnClickListener(this);
        actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab headerTab1 = actionBar.newTab().setText("表头").setTabListener(this);
        ActionBar.Tab orderDetailTab = actionBar.newTab().setText("明细").setTabListener(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentSalesOrderDetail = new FragmentSalesOrderDetail();
        fragmentSalesOrderDetail.setRetainInstance(true);
        fragmentSalesOrderHeader = new FragmentSalesOrderHeader();
        fragmentSalesOrderHeader.setRetainInstance(true);


        fragmentTransaction.add(R.id.fragment_content, fragmentSalesOrderDetail, "detail").add(R.id.fragment_content, fragmentSalesOrderHeader, "header").commit();


        actionBar.addTab(headerTab1);
        actionBar.addTab(orderDetailTab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bill_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.print:

                return true;
            case R.id.preOrder:

                return true;
            case R.id.nextOrder:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:

                break;
            case R.id.save:

                break;
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        log.i(tab.getText());
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        if (tab.getText().equals("明细")) {
            ft.hide(fragmentSalesOrderHeader);
            ft.show(fragmentSalesOrderDetail);
        } else if (tab.getText().equals("表头")) {
            ft.hide(fragmentSalesOrderDetail);
            ft.show(fragmentSalesOrderHeader);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
