package com.twisty.superclient.view.filter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.EmployeeAdapter;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.EmployeeDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class EmployeeFilterActivity extends BaseActivity implements View.OnClickListener {
    private ListView listView;
    private EmployeeAdapter adapter;
    private List<Employee> adapterData;
    private EditText filterStr;
    private Button filterBtn;
    private EmployeeDao employeeDao;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_filter);
        listView = (ListView) findViewById(R.id.listView);
        filterStr = (EditText) findViewById(R.id.filterStr);
        filterBtn = (Button) findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(this);
        pd = ProgressDialog.show(this,null,"正在检索数据");
        employeeDao = SuperClient.getDaoSession(this).getEmployeeDao();
        adapterData = employeeDao.queryBuilder().where(EmployeeDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID())).list();
        adapter = new EmployeeAdapter(this, adapterData);
        listView.setAdapter(adapter);
        if(pd!=null)pd.dismiss();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("Data", (Employee) adapterView.getItemAtPosition(i));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filterBtn:
                String str = filterStr.getText().toString().trim();
                QueryBuilder<Employee> queryBuilder = employeeDao.queryBuilder();
                queryBuilder.where(
                        EmployeeDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID()),
                        queryBuilder.or(
                                EmployeeDao.Properties.EmpCode.like("%" + str + "%"),
                                EmployeeDao.Properties.EmpName.like("%" + str + "%")
                        )
                );
                adapterData = queryBuilder.list();
                if(adapter==null){
                    adapter = new EmployeeAdapter(this,adapterData);
                    listView.setAdapter(adapter);
                }else {
                    adapter.setData(adapterData);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
