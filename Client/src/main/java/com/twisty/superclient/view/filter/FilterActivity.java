package com.twisty.superclient.view.filter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.OrderListActivity;

import org.joda.time.DateTime;

public class FilterActivity extends BaseActivity implements View.OnClickListener {
    private EditText traderView, stateView, billCodeView, operatorView;
    private TextView begDateView, endDateView;
    private Button commitBTN, cancelBTN;
    private DateTime startDateTime, endDateTime;

    private String billType;
    private int billKind;
    private int traderID;
    private int billState;
    private long opID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        billType = getIntent().getStringExtra("BillType");
        billKind = getIntent().getIntExtra("BillKind", 0);
        begDateView = (TextView) findViewById(R.id.beginDate);
        endDateView = (TextView) findViewById(R.id.endDate);
        traderView = (EditText) findViewById(R.id.trader);
        stateView = (EditText) findViewById(R.id.state);
        billCodeView = (EditText) findViewById(R.id.billCode);
        operatorView = (EditText) findViewById(R.id.operator);
        commitBTN = (Button) findViewById(R.id.commit);
        cancelBTN = (Button) findViewById(R.id.cancel);
        begDateView.setOnClickListener(this);
        endDateView.setOnClickListener(this);
        traderView.setOnClickListener(this);
        stateView.setOnClickListener(this);
        operatorView.setOnClickListener(this);
        commitBTN.setOnClickListener(this);
        cancelBTN.setOnClickListener(this);
        startDateTime = new DateTime();
        endDateTime = new DateTime();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.beginDate:

                DatePickerDialog dpdStart = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        begDateView.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }, startDateTime.getYear(), startDateTime.getMonthOfYear() + 1, startDateTime.getDayOfMonth());
                dpdStart.show();
                break;
            case R.id.endDate:
                DatePickerDialog dpdEnd = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endDateView.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }, endDateTime.getYear(), endDateTime.getMonthOfYear() + 1, endDateTime.getDayOfMonth());
                dpdEnd.show();
                break;
            case R.id.trader:

                break;
            case R.id.state:

                break;
            case R.id.operator:

                break;
            case R.id.commit:
                Request request = new Request(GlobalConstant.METHOD_DO_BILL);
                Params params = new Params();
                params.setBillName(billType);
                params.setOperate("GetListBill");
                params.setPageSize(30);
                params.setPageNo(1);
                params.setBegDate(begDateView.getText().toString());
                params.setEndDate(endDateView.getText().toString());
                params.setBillKind(billKind);
                params.setTraderID(traderID);
                params.setBillState(billState);
                params.setBillCode(billCodeView.getText().toString());
                params.setOpID(opID);
                request.setParams(params);
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra("Request", request);
                startActivity(intent);
                break;
            case R.id.cancel:

                break;
        }
    }
}
