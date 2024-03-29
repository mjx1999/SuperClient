package com.twisty.superclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.twisty.superclient.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean isFirst = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("isFirst", true);
        Intent intent;
        if (isFirst) {
            intent = new Intent(this, ServerActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
    }
}
