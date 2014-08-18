package com.twisty.superclient.view.filter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.AMKindAdapter;
import com.twisty.superclient.adapter.OperatorAdapter;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.AMKindDao;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.Params;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.OrderListActivity;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.greenrobot.dao.query.QueryBuilder;

public class FilterActivity extends BaseActivity implements View.OnClickListener {
    private EditText  billCodeView;
    private TextView begDateView, endDateView,traderView;
    private Spinner stateView,operatorView;
    private Button commitBTN, cancelBTN;
    private DateTime startDateTime, endDateTime;
    private DaoSession session;
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
        traderView = (TextView) findViewById(R.id.trader);
        stateView = (Spinner) findViewById(R.id.state);
        billCodeView = (EditText) findViewById(R.id.billCode);
        operatorView = (Spinner) findViewById(R.id.operator);
        commitBTN = (Button) findViewById(R.id.commit);
        cancelBTN = (Button) findViewById(R.id.cancel);
        begDateView.setOnClickListener(this);
        endDateView.setOnClickListener(this);
        traderView.setOnClickListener(this);
        commitBTN.setOnClickListener(this);
        cancelBTN.setOnClickListener(this);
        startDateTime = new DateTime();
        endDateTime = new DateTime();
        begDateView.setText(startDateTime.getYear()+"-"+startDateTime.getMonthOfYear()+"-"+startDateTime.toCalendar(Locale.CHINA).getActualMinimum(Calendar.DAY_OF_MONTH));
        endDateView.setText(endDateTime.getYear()+"-"+endDateTime.getMonthOfYear()+"-"+endDateTime.toCalendar(Locale.CHINA).getActualMaximum(Calendar.DAY_OF_MONTH));
        session = SuperClient.getDaoSession(this);
        QueryBuilder<AMKind> queryBuilder = session.getAMKindDao().queryBuilder();
        queryBuilder.where(AMKindDao.Properties.Kind.eq(99));
        AMKindAdapter amKindAdapter = new AMKindAdapter(this,queryBuilder.list());
        stateView.setAdapter(amKindAdapter);
        stateView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                billState = (int) adapterView.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<Operator> operators = session.getOperatorDao().loadAll();
        Operator operator = new Operator();
        operator.setOpID(-1L);
        operator.setOpName("全部");
        operators.add(0,operator);
        OperatorAdapter operatorAdapter = new OperatorAdapter(operators,this);
        operatorView.setAdapter(operatorAdapter);
        operatorView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opID = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 1:
                    Trader trader = (Trader) data.getSerializableExtra("Data");
                    traderView.setText(trader.getTraderName());
                    break;
            }
        }
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
                }, startDateTime.getYear(), startDateTime.getMonthOfYear()-1, startDateTime.toCalendar(Locale.CHINA).getActualMinimum(Calendar.DAY_OF_MONTH));
                dpdStart.show();
                break;
            case R.id.endDate:
                DatePickerDialog dpdEnd = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endDateView.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }, endDateTime.getYear(), endDateTime.getMonthOfYear() -1, endDateTime.toCalendar(Locale.CHINA).getActualMaximum(Calendar.DAY_OF_MONTH));
                dpdEnd.show();
                break;
            case R.id.trader:
                Intent intent2 = new Intent(this, TraderFilterActivity.class);
                intent2.putExtra("CoV","C");
                startActivityForResult(intent2, 1);
                break;
            case R.id.state:

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
