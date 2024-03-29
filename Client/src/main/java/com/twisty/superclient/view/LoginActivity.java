package com.twisty.superclient.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.LoginResp;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.OperatorDao;
import com.twisty.superclient.bean.OperatorResp;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.net.ReqClient;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.util.LogFactory;
import com.twisty.superclient.view.filter.OperatorPop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.dao.query.QueryBuilder;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private boolean isOnline;
    private CommonLog log = LogFactory.createLog();
    private TextView opNameView;
    private EditText opPassView, defaultStoreCodeView;
    private Button loginBTN;
    private OperatorDao operatorDao;
    private StoreDao storeDao;
    private ProgressDialog pd;
    private Accset accset;
    private List<Operator> adapterData;
    private SharedPreferences sp;
    private Operator currentOperator;
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
                    CommonUtil.showToastError(LoginActivity.this, msg.obj.toString(),null);
                    break;
                case RESULT_FIRST_USER:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        accset = SuperClient.getCurrentAccset();
        opNameView = (TextView) findViewById(R.id.opName);
        opPassView = (EditText) findViewById(R.id.opPass);
        defaultStoreCodeView = (EditText) findViewById(R.id.defaultStoreCode);
        loginBTN = (Button) findViewById(R.id.login);
        loginBTN.setOnClickListener(this);
        isOnline = SuperClient.getIsOnline();
        operatorDao = SuperClient.getDaoSession(this).getOperatorDao();
        storeDao = SuperClient.getDaoSession(this).getStoreDao();
        String lastOPStr = sp
                .getString("LastOP", null);
        if (lastOPStr != null) {
            HashMap opinfo = CommonUtil.getGson().fromJson(lastOPStr, HashMap.class);


            String opPass = (String) opinfo.get("OpPass");
            defaultStoreCodeView.setText((String) opinfo.get("StoreCode"));
            String operatorStr = (String) opinfo.get("Operator");
            Operator lastOP = CommonUtil.getGson().fromJson(operatorStr,Operator.class);


            opNameView.setText(lastOP.getOpName());
            opPassView.setText(opPass);
            currentOperator = lastOP;
        } else {
            opPassView.setText("");
            defaultStoreCodeView.setText("");
        }


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
                        client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), SuperClient.getCurrentLoginRequest());
                        operators = client.requestData(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        client.close();
                    }
                    log.i(operators);
                    OperatorResp operatorResp = CommonUtil.getGson().fromJson(operators, OperatorResp.class);
                    if (operatorResp != null) {
                        if (operatorResp.isCorrect()) {
                            adapterData = operatorResp.getResultData();
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
            adapterData = operatorDao.loadAll();
        }
        opNameView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opName:
                OperatorPop operatorPop = new OperatorPop(this, adapterData, new OperatorPop.onItemClickListener() {
                    @Override
                    public void onItemClick(Operator operator) {
                        currentOperator = operator;
                        opNameView.setText(operator.getOpName());
                        String opinfoStr = sp
                                .getString(currentOperator.getOpName(), null);
                        if (opinfoStr != null) {
                            HashMap opinfo = CommonUtil.getGson().fromJson(opinfoStr, HashMap.class);
                            opPassView.setText((String) opinfo.get("OpPass"));
                            defaultStoreCodeView.setText((String) opinfo.get("StoreCode"));
                        } else {
                            opPassView.setText("");
                            defaultStoreCodeView.setText("");
                        }
                    }
                });
                operatorPop.showPopupWindow(v);
                break;

            case R.id.login:
                if (currentOperator == null) {
                    CommonUtil.showToastError(this, "操作员不能为空!",null);
                    return;
                }
                if (defaultStoreCodeView.getText().toString() == null || defaultStoreCodeView.getText().toString().trim().length() <= 0) {
                    CommonUtil.showToastError(this, "默认仓库不能为空",null);
                    return;
                }
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
                            params.setOpID(currentOperator.getOpID());
                            params.setOpPassword(opPassView.getText().toString());
                            params.setDefaultStoreCode(defaultStoreCodeView.getText().toString());
                            request.setParams(params);
                            String loginResult = null;
                            try {
                                client.connectServer(SuperClient.getCurrentIP(), SuperClient.getCurrentPort(), null);
                                loginResult = client.requestData(request);
                                log.i(loginResult);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Message message = handler.obtainMessage();
                                message.what = RESULT_CANCELED;
                                message.obj = "网络连接中断,请重新登陆!";
                                handler.sendMessage(message);
                            } finally {
                                client.close();
                            }
                            LoginResp response = CommonUtil.getGson().fromJson(loginResult, LoginResp.class);
                            if (response != null) {
                                if (response.isCorrect()) {
                                    SuperClient.setCurrentLoginRequest(request);
                                    saveLastOp();
                                    saveOpInfo();
                                    SuperClient.setDefaultStoreID(response.getStoreID());
                                    SuperClient.setDefaultStoreCode(defaultStoreCodeView.getText().toString());
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
                            .where(OperatorDao.Properties.OpPassword.eq(opPassView.getText().toString())
                                    , OperatorDao.Properties.OpID.eq(currentOperator.getOpID())
                            ).count();
                    QueryBuilder<Store> storeQueryBuilder = storeDao.queryBuilder();
                    storeQueryBuilder.where(StoreDao.Properties.StoreCode.eq(defaultStoreCodeView.getText().toString()));
                    Store store = storeQueryBuilder.unique();
                    if(store==null){
                        CommonUtil.showToastError(LoginActivity.this,"默认仓库编码不正确!",null);
                        return;
                    }else{
                        SuperClient.setDefaultStoreID(store.getStoreID());
                        SuperClient.setDefaultStoreCode(store.getStoreCode());
                    }

                    if (count > 0) {
                        saveLastOp();
                        saveOpInfo();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        SuperClient.setCurrentOperator(currentOperator);
                        startActivity(intent);
                    } else {
                        CommonUtil.showToastError(LoginActivity.this, "登录密码不正确",null);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void saveOpInfo() {
        SuperClient.setCurrentOperator(currentOperator);
        Map<String,String> opInf = new HashMap<String,String>();
//        opInf.put("Operator",CommonUtil.getGson().toJson(currentOperator));
        opInf.put("OpPass", opPassView.getText().toString());
        opInf.put("StoreCode", defaultStoreCodeView.getText().toString());
        sp.edit().putString(currentOperator.getOpName(), CommonUtil.getGson().toJson(opInf)).apply();
    }

    private void saveLastOp(){
        Map<String,String> opInf = new HashMap<String,String>();
        opInf.put("Operator",CommonUtil.getGson().toJson(currentOperator));
        opInf.put("OpPass", opPassView.getText().toString());
        opInf.put("StoreCode", defaultStoreCodeView.getText().toString());
        sp.edit().putString("LastOP", CommonUtil.getGson().toJson(opInf)).apply();
    }
}
