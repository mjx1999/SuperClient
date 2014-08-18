package com.twisty.superclient.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.twisty.superclient.R;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.LogFactory;
import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends Activity {
    protected CommonLog log = LogFactory.createLog();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if(actionBar!=null)actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivity(Intent intent) {
        CommonUtil.cancelAllToast();
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in_sba,R.anim.fade_out_sba);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        CommonUtil.cancelAllToast();
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
    }

    @Override
    public void finish() {
        CommonUtil.cancelAllToast();
        super.finish();
        overridePendingTransition(R.anim.fade_in_sba,R.anim.push_right_out_sba);
    }
}
