package com.twisty.superclient.view.salesBill;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.NotUploadSBAdapter;
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.bean.SalesBillMasterData;
import com.twisty.superclient.bean.SalesBillMasterDataDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotUploadSB extends BaseActivity {
    SalesBillMasterDataDao masterDataDao;
    private ListView listView;
    private List<SalesBillMasterData> adapterData;
    private NotUploadSBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_upload_sb);
        listView = (ListView) findViewById(R.id.listView);
        masterDataDao = SuperClient.getDaoSession(this).getSalesBillMasterDataDao();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SalesBillMasterData masterData = adapterData.get(position);
                masterData.resetSalesBillDetail1DataList();
                ArrayList<SalesBillDetail1Data> detail1Datas = new ArrayList<SalesBillDetail1Data>(masterData.getSalesBillDetail1DataList());
                Intent intent = new Intent(NotUploadSB.this, SalesBillActivity.class);
                intent.putExtra("From", GlobalConstant.FROM_DB);
                intent.putExtra("MasterData", masterData);
                intent.putExtra("DetailData", detail1Datas);
                log.i(adapterData.get(position).getSalesBillDetail1DataList());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(adapter==null){
        adapterData = masterDataDao.loadAll();
        adapter = new NotUploadSBAdapter(this, adapterData);
        listView.setAdapter(adapter);
//        }else{
//            adapter.setData(adapterData);
//            adapter.notifyDataSetChanged();
//            adapter.notifyDataSetInvalidated();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.not_upload_sb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
