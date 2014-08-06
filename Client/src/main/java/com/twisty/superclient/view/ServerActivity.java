package com.twisty.superclient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.AccsetDao;
import com.twisty.superclient.global.SuperClient;

public class ServerActivity extends Activity implements View.OnClickListener{
    private EditText serverName,serverPort;
    private Button onlineLogin,offlinLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);
        AccsetDao accsetDao = SuperClient.getDaoSession(this).getAccsetDao();


        for(int i = 0;i<10;i++){
            Accset accset = new Accset();
            accset.setAccsetID((long)i);
            accset.setAccsetCode("0000"+i);
            accset.setAccsetName("twisty");
            accsetDao.insert(accset);
        }

        serverName = (EditText) findViewById(R.id.serverName);
        serverPort = (EditText) findViewById(R.id.serverPort);
        onlineLogin = (Button) findViewById(R.id.onlineLogin);
        offlinLogin = (Button) findViewById(R.id.offlineLogin);
        onlineLogin.setOnClickListener(this);
        offlinLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onlineLogin:
                SuperClient.setIsOnline(true);
                startActivity(new Intent(this,AccsetActivity.class));
                overridePendingTransition(R.anim.push_left_in_sba,R.anim.fade_out_sba);
                break;
            case R.id.offlineLogin:
                SuperClient.setIsOnline(false);
                startActivity(new Intent(this,AccsetActivity.class));
                overridePendingTransition(R.anim.push_left_in_sba,R.anim.fade_out_sba);
                break;
            default:break;
        }
    }
}
