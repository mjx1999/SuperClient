package com.twisty.superclient.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.OperatorAdapter;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.OperatorDao;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private boolean isOnline;
    private CommonLog log = LogFactory.createLog();
    private Spinner opNameView;
    private EditText opPassView;
    private Button loginBTN,cancelBTN;
    private OperatorDao operatorDao ;
    private ProgressDialog pd;
    private Accset accset;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(pd!=null)pd.dismiss();
            switch (msg.what){
                case RESULT_OK:

                    break;
                case RESULT_CANCELED:

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accset = (Accset) getIntent().getSerializableExtra("Accset");
        opNameView = (Spinner) findViewById(R.id.opName);
        opPassView = (EditText) findViewById(R.id.opPass);
        loginBTN = (Button) findViewById(R.id.login);
        cancelBTN = (Button) findViewById(R.id.cancel);
        loginBTN.setOnClickListener(this);
        cancelBTN.setOnClickListener(this);
        isOnline = SuperClient.getIsOnline();
        operatorDao = SuperClient.getDaoSession(this).getOperatorDao();
        if(isOnline){

        }else{
            List<Operator> operators = operatorDao.loadAll();
            opNameView.setAdapter(new OperatorAdapter(operators,this));
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if(isOnline){

                    pd = ProgressDialog.show(this,null,"正在登录,请稍等");
                    final ReqClient client = ReqClient.newInstance();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 登录
                         */
                        Request request = new Request(GlobalConstant.METHOD_LOGIN);
                        Params params = new Params();
                        params.setAccsetCode(accset.getAccsetCode());
                        params.setOpID(opNameView.getSelectedItemId());
                        params.setOpPassword("");
                        request.setParams(params);
                        String loginResult = null;
                        try {
                            loginResult = client.requestData(request);
                            log.i(loginResult);
                            //TODO 接收返回转换Json
                            handler.sendEmptyMessage(RESULT_OK);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                }else{
                    //TODO 离线登录.
                }
            break;
            case R.id.cancel:

            break;
            default:break;
        }
    }
}
