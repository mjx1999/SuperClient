package com.twisty.superclient.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.OperatorAdapter;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.OperatorDao;
import com.twisty.superclient.bean.OperatorResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Response;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.LogFactory;

import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private boolean isOnline;
    private CommonLog log = LogFactory.createLog();
    private Spinner opNameView;
    private EditText opPassView;
    private Button loginBTN;
    private OperatorDao operatorDao;
    private ProgressDialog pd;
    private Accset accset;
    private OperatorAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pd != null) pd.dismiss();
            switch (msg.what) {
                case RESULT_OK:
//                    CommonUtil.showToastInfo(LoginActivity.this,"登录成功");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case RESULT_CANCELED:
                    CommonUtil.showToastError(LoginActivity.this, msg.obj.toString());
                    break;
                case RESULT_FIRST_USER:
                    opNameView.setAdapter(adapter);
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
        loginBTN.setOnClickListener(this);
        isOnline = SuperClient.getIsOnline();
        operatorDao = SuperClient.getDaoSession(this).getOperatorDao();
        if (isOnline) {
            final Message msg = handler.obtainMessage();
            final ReqClient client = ReqClient.newInstance();
            pd = ProgressDialog.show(this, null, "加载操作员");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Request request = new Request(GlobalConstant.METHOD_DOWNLOAD_OPERATOR);
                    Params params = new Params();
                    params.setAccsetCode(accset.getAccsetCode());
                    request.setParams(params);
                    String operators = null;
                    try {
                        operators = client.requestData(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.i(operators);
                    OperatorResp operatorResp = CommonUtil.getGson().fromJson(operators, OperatorResp.class);
                    if (operatorResp != null) {
                        if (operatorResp.isCorrect()) {
                            adapter = new OperatorAdapter(operatorResp.getResultData(), LoginActivity.this);
                            handler.sendEmptyMessage(RESULT_FIRST_USER);
                        } else {
                            msg.obj = operatorResp.getErrMessage();
                            msg.what = RESULT_CANCELED;
                            handler.sendMessage(msg);
                        }
                    }
                }
            }).start();
        } else {
            List<Operator> operators = operatorDao.loadAll();
            opNameView.setAdapter(new OperatorAdapter(operators, this));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (isOnline) {

                    pd = ProgressDialog.show(this, null, "正在登录,请稍等");
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
                            params.setOpPassword(opPassView.getText().toString());
                            request.setParams(params);
                            String loginResult = null;
                            try {
                                loginResult = client.requestData(request);
                                log.i(loginResult);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Response response = CommonUtil.getGson().fromJson(loginResult, Response.class);
                            if (response != null) {
                                if (response.isCorrect()) {
                                    handler.sendEmptyMessage(RESULT_OK);
                                } else {
                                    Message message = handler.obtainMessage();
                                    message.what = RESULT_CANCELED;
                                    message.obj = response.getErrMessage();
                                    handler.sendMessage(message);
                                }
                            }
                        }
                    }).start();
                } else {
                    long count = operatorDao.queryBuilder()
                            .where( OperatorDao.Properties.OpPassword.eq(opPassView.getText().toString())
                                    ,OperatorDao.Properties.OpID.eq(opNameView.getSelectedItemId())
                            ).count();
                    if(count>0){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        CommonUtil.showToastError(LoginActivity.this,"登录密码不正确");
                    }
                }
                break;
            default:
                break;
        }
    }
}
