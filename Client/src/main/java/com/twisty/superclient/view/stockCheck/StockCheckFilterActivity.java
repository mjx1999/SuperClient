package com.twisty.superclient.view.stockCheck;

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
import com.twisty.superclient.bean.ParamsStockCheck;
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

public class StockCheckFilterActivity extends BaseActivity implements View.OnClickListener {
    private EditText billCodeView;
    private TextView begDateView, endDateView, storeView;
    private TextView stateView, operatorView;
    private DateTime startDateTime, endDateTime;
    private DaoSession session;
    private long storeID = -1;
    private long billState = -1;
    private long opID = -1;
    private StoreDao storeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_check_filter);
        storeDao = SuperClient.getDaoSession(this).getStoreDao();
        begDateView = (TextView) findViewById(R.id.beginDate);
        endDateView = (TextView) findViewById(R.id.endDate);
        storeView = (TextView) findViewById(R.id.StoreName);
        stateView = (TextView) findViewById(R.id.state);
        billCodeView = (EditText) findViewById(R.id.billCode);
        operatorView = (TextView) findViewById(R.id.operator);
        begDateView.setOnClickListener(this);
        endDateView.setOnClickListener(this);
        storeView.setOnClickListener(this);
        startDateTime = new DateTime();
        endDateTime = new DateTime();
        begDateView.setText(startDateTime.getYear() + "-" + String.format("%02d", startDateTime.getMonthOfYear()) + "-" + startDateTime.toCalendar(Locale.CHINA).getActualMinimum(Calendar.DAY_OF_MONTH));
        endDateView.setText(endDateTime.getYear() + "-" + String.format("%02d", endDateTime.getMonthOfYear()) + "-" + endDateTime.toCalendar(Locale.CHINA).getActualMaximum(Calendar.DAY_OF_MONTH));
        session = SuperClient.getDaoSession(this);
        stateView.setOnClickListener(this);
        operatorView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.beginDate:

                DatePickerDialog dpdStart = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        begDateView.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + dayOfMonth);
                    }
                }, startDateTime.getYear(), startDateTime.getMonthOfYear() - 1, startDateTime.toCalendar(Locale.CHINA).getActualMinimum(Calendar.DAY_OF_MONTH));
                dpdStart.show();
                break;
            case R.id.endDate:
                DatePickerDialog dpdEnd = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endDateView.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + dayOfMonth);
                    }
                }, endDateTime.getYear(), endDateTime.getMonthOfYear() - 1, endDateTime.toCalendar(Locale.CHINA).getActualMaximum(Calendar.DAY_OF_MONTH));
                dpdEnd.show();
                break;
            case R.id.StoreName:
                StorePop storePop = new StorePop(this, storeDao.loadAll(), new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        storeID = store.getStoreID();
                        storeView.setText(store.getStoreCode());
                    }
                });
                storePop.showPopupWindow(v);
                break;
            case R.id.state:
                AMKindDao amKindDao = session.getAMKindDao();
                QueryBuilder<AMKind> amKindQueryBuilder = amKindDao.queryBuilder();
                amKindQueryBuilder.where(AMKindDao.Properties.Kind.eq(99));
                List<AMKind> amKinds = amKindQueryBuilder.list();
                AMKindPop amKindPop = new AMKindPop(this, amKinds, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        billState = amKind.getID();
                        stateView.setText(amKind.getName());
                    }
                });
                amKindPop.showPopupWindow(v);
                break;
            case R.id.operator:
                List<Operator> operators = session.getOperatorDao().loadAll();
                OperatorPop operatorPop = new OperatorPop(this, operators, new OperatorPop.onItemClickListener() {
                    @Override
                    public void onItemClick(Operator operator) {
                        opID = operator.getOpID();
                        operatorView.setText(operator.getOpName());
                    }
                });
                operatorPop.showPopupWindow(v);
                break;
        }
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
            ParamsStockCheck params = new ParamsStockCheck();
            params.setBillName("i_balitem");
            params.setOperate("GetListBill");
            params.setPageSize(30);
            params.setPageNo(1);
            params.setBegDate(begDateView.getText().toString());
            params.setEndDate(endDateView.getText().toString());
            params.setStoreID(storeID);
            params.setBillState(billState);
            params.setBillCode(billCodeView.getText().toString());
            params.setOpID(opID);
            request.setParams(params);
            Intent intent = new Intent(this, StockCheckListActivity.class);
            intent.putExtra("Request", request);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
