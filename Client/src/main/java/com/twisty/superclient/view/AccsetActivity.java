package com.twisty.superclient.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.AccsetDao;
import com.twisty.superclient.global.SuperClient;

import java.util.List;

public class AccsetActivity extends BaseActivity {
    private AccsetDao accsetDao;
    private boolean isOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accset);
        isOnline = SuperClient.getIsOnline();
        accsetDao = SuperClient.getDaoSession(this).getAccsetDao();
        List<Accset> accsets = accsetDao.loadAll();
        log.i(accsets.size());
        Toast.makeText(this, accsets.size()+(isOnline?"&Online":"&Offline"), Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
