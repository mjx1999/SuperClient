package com.twisty.superclient.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.twisty.superclient.R;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

public class LoginActivity extends BaseActivity {
    private CommonLog log = LogFactory.createLog();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
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
