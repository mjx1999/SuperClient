package com.twisty.superclient.view.stockCheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.NotUploadSCAdapter;
import com.twisty.superclient.bean.StockCheckDetail1Data;
import com.twisty.superclient.bean.StockCheckMasterData;
import com.twisty.superclient.bean.StockCheckMasterDataDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotUploadSC extends BaseActivity {
    StockCheckMasterDataDao masterDataDao;
    private ListView listView;
    private List<StockCheckMasterData> adapterData;
    private NotUploadSCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_upload_sb);
        listView = (ListView) findViewById(R.id.listView);
        masterDataDao = SuperClient.getDaoSession(this).getStockCheckMasterDataDao();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StockCheckMasterData masterData = adapterData.get(position);
                masterData.resetStockCheckDetail1DataList();
                List<StockCheckDetail1Data> detail1Datas = masterData.getStockCheckDetail1DataList();
                Intent intent = new Intent(NotUploadSC.this, StockCheckActivity.class);
                intent.putExtra("From", GlobalConstant.FROM_DB);
                intent.putExtra("MasterData", masterData);
                intent.putExtra("DetailData", new ArrayList<StockCheckDetail1Data>(detail1Datas));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterData = masterDataDao.loadAll();
        adapter = new NotUploadSCAdapter(this, adapterData);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.not_upload_sc, menu);
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
