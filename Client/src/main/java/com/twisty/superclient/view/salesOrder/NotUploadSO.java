package com.twisty.superclient.view.salesOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.NotUploadSOAdapter;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.bean.SalesOrderMasterData;
import com.twisty.superclient.bean.SalesOrderMasterDataDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotUploadSO extends BaseActivity {
    SalesOrderMasterDataDao masterDataDao;
    private ListView listView;
    private List<SalesOrderMasterData> adapterData;
    private NotUploadSOAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_upload_sb);
        listView = (ListView) findViewById(R.id.listView);
        masterDataDao = SuperClient.getDaoSession(this).getSalesOrderMasterDataDao();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SalesOrderMasterData masterData = adapterData.get(position);
                masterData.resetSalesOrderDetail1DataList();
                List<SalesOrderDetail1Data> detail1Datas = masterData.getSalesOrderDetail1DataList();
                Intent intent = new Intent(NotUploadSO.this, SalesOrderActivity.class);
                intent.putExtra("From", GlobalConstant.FROM_DB);
                intent.putExtra("MasterData", masterData);
                intent.putExtra("DetailData", new ArrayList<SalesOrderDetail1Data>(detail1Datas));
                log.i(adapterData.get(position).getSalesOrderDetail1DataList());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(adapter==null){
        adapterData = masterDataDao.loadAll();
        adapter = new NotUploadSOAdapter(this, adapterData);
        listView.setAdapter(adapter);
//        }else{
//            adapter.setData(adapterData);
//            adapter.notifyDataSetChanged();
//            adapter.notifyDataSetInvalidated();
//        }

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.not_upload_so, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
