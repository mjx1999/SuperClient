package com.twisty.superclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.twisty.superclient.R;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

public class BaseActivity extends Activity {
    protected CommonLog log = LogFactory.createLog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in_sba,R.anim.fade_out_sba);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in_sba,R.anim.push_right_out_sba);
    }
}
