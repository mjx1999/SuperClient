package com.twisty.superclient.view.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.NotUploadTSAdapter;
import com.twisty.superclient.bean.TransferDetail1Data;
import com.twisty.superclient.bean.TransferMasterData;
import com.twisty.superclient.bean.TransferMasterDataDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotUploadTS extends BaseActivity {
    TransferMasterDataDao masterDataDao;
    private ListView listView;
    private List<TransferMasterData> adapterData;
    private NotUploadTSAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_upload_sb);
        listView = (ListView) findViewById(R.id.listView);
        masterDataDao = SuperClient.getDaoSession(this).getTransferMasterDataDao();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TransferMasterData masterData = adapterData.get(position);
                masterData.resetTransferDetail1DataList();
                List<TransferDetail1Data> detail1Datas = masterData.getTransferDetail1DataList();
                Intent intent = new Intent(NotUploadTS.this, TransferActivity.class);
                intent.putExtra("From", GlobalConstant.FROM_DB);
                intent.putExtra("MasterData", masterData);
                intent.putExtra("DetailData", new ArrayList<TransferDetail1Data>(detail1Datas));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterData = masterDataDao.loadAll();
        adapter = new NotUploadTSAdapter(this, adapterData);
        listView.setAdapter(adapter);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.not_upload_t, menu);
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
