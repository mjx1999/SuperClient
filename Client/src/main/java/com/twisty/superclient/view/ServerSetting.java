package com.twisty.superclient.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.twisty.superclient.R;

public class ServerSetting extends Activity implements View.OnClickListener{
    private EditText serverName,serverPort;
    private Button onlineLogin,offlinLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);
        serverName = (EditText) findViewById(R.id.serverName);
        serverPort = (EditText) findViewById(R.id.serverPort);
        onlineLogin = (Button) findViewById(R.id.onlineLogin);
        offlinLogin = (Button) findViewById(R.id.offlineLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onlineLogin:

                break;
            case R.id.offlineLogin:

                break;
            default:break;
        }
    }
}
