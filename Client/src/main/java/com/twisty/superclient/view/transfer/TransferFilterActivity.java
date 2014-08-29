package com.twisty.superclient.view.transfer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.AMKindDao;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.ParamsTransfer;
import com.twisty.superclient.bean.Request;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;
import com.twisty.superclient.view.filter.AMKindPop;
import com.twisty.superclient.view.filter.OperatorPop;
import com.twisty.superclient.view.filter.StorePop;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.greenrobot.dao.query.QueryBuilder;

public class TransferFilterActivity extends BaseActivity implements View.OnClickListener {
    private TextView beginDate, endDate, OutStore, InStore, BillKindName, BillStateName, operatorName;
    private EditText billCode;
    private DateTime startDateTime, endDateTime;
    private DaoSession session;
    private StoreDao storeDao;
    private AMKindDao amKindDao;
    private long instoreID = -1, outstoreID = -1, billState = -1, billKind = -1, opid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_filter);
        session = SuperClient.getDaoSession(this);
        storeDao = session.getStoreDao();
        amKindDao = session.getAMKindDao();

        beginDate = (TextView) findViewById(R.id.beginDate);
        endDate = (TextView) findViewById(R.id.endDate);
        OutStore = (TextView) findViewById(R.id.OutStore);
        InStore = (TextView) findViewById(R.id.InStore);
        BillKindName = (TextView) findViewById(R.id.BillKindName);
        BillStateName = (TextView) findViewById(R.id.BillStateName);
        operatorName = (TextView) findViewById(R.id.operator);
        billCode = (EditText) findViewById(R.id.billCode);
        beginDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        OutStore.setOnClickListener(this);
        InStore.setOnClickListener(this);
        BillKindName.setOnClickListener(this);
        BillStateName.setOnClickListener(this);
        operatorName.setOnClickListener(this);
        startDateTime = new DateTime();
        endDateTime = new DateTime();
        beginDate.setText(startDateTime.getYear() + "-" + String.format("%02d", startDateTime.getMonthOfYear()) + "-" + startDateTime.toCalendar(Locale.CHINA).getActualMinimum(Calendar.DAY_OF_MONTH));
        endDate.setText(endDateTime.getYear() + "-" + String.format("%02d", endDateTime.getMonthOfYear()) + "-" + endDateTime.toCalendar(Locale.CHINA).getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.filterBtn) {
            Request request = new Request(GlobalConstant.METHOD_DO_BILL);
            ParamsTransfer params = new ParamsTransfer();
            params.setBillName("i_allot");
            params.setOperate("GetListBill");
            params.setPageSize(30);
            params.setPageNo(1);
            params.setBillCode(billCode.getText().toString().trim());
            params.setBegDate(beginDate.getText().toString().trim());
            params.setEndDate(endDate.getText().toString().trim());
            params.setInStoreID(instoreID);
            params.setStoreID(outstoreID);
            params.setBillState(billState);
            params.setBillKind(billKind);
            params.setOpID(opid);
            request.setParams(params);
            Intent intent = new Intent(this, TransferListActivity.class);
            intent.putExtra("Request", request);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beginDate:

                DatePickerDialog dpdStart = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        beginDate.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + dayOfMonth);
                    }
                }, startDateTime.getYear(), startDateTime.getMonthOfYear() - 1, startDateTime.toCalendar(Locale.CHINA).getActualMinimum(Calendar.DAY_OF_MONTH));
                dpdStart.show();
                break;
            case R.id.endDate:
                DatePickerDialog dpdEnd = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endDate.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + dayOfMonth);
                    }
                }, endDateTime.getYear(), endDateTime.getMonthOfYear() - 1, endDateTime.toCalendar(Locale.CHINA).getActualMaximum(Calendar.DAY_OF_MONTH));
                dpdEnd.show();
                break;
            case R.id.InStore:
                StorePop storePop = new StorePop(this, storeDao.loadAll(), new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        instoreID = store.getStoreID();
                        InStore.setText(store.getStoreCode() + " " + store.getStoreName());
                    }
                });
                storePop.showPopupWindow(v);
                break;
            case R.id.OutStore:
                StorePop outstorePop = new StorePop(this, storeDao.loadAll(), new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        outstoreID = store.getStoreID();
                        OutStore.setText(store.getStoreCode() + " " + store.getStoreName());
                    }
                });
                outstorePop.showPopupWindow(v);
                break;
            case R.id.BillStateName:
                QueryBuilder<AMKind> amKindQueryBuilder = amKindDao.queryBuilder();
                amKindQueryBuilder.where(AMKindDao.Properties.Kind.eq(99));
                List<AMKind> amKinds = amKindQueryBuilder.list();
                AMKindPop amKindPop = new AMKindPop(this, amKinds, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        billState = amKind.getID();
                        BillStateName.setText(amKind.getName());
                    }
                });
                amKindPop.showPopupWindow(v);
                break;
            case R.id.BillKindName:
                QueryBuilder<AMKind> kindQueryBuilder = amKindDao.queryBuilder();
                kindQueryBuilder.where(AMKindDao.Properties.Kind.eq(8));
                List<AMKind> billKinds = kindQueryBuilder.list();
                AMKindPop billKindPop = new AMKindPop(this, billKinds, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        billKind = amKind.getID();
                        BillKindName.setText(amKind.getName());
                    }
                });
                billKindPop.showPopupWindow(v);
                break;
            case R.id.operator:
                List<Operator> operators = session.getOperatorDao().loadAll();
                OperatorPop operatorPop = new OperatorPop(this, operators, new OperatorPop.onItemClickListener() {
                    @Override
                    public void onItemClick(Operator operator) {
                        opid = operator.getOpID();
                        operatorName.setText(operator.getOpName());
                    }
                });
                operatorPop.showPopupWindow(v);
                break;


        }
    }
}
