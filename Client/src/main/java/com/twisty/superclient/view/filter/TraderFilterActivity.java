package com.twisty.superclient.view.filter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.TraderAdapter;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseActivity;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class TraderFilterActivity extends BaseActivity implements View.OnClickListener {
    private ListView listView;
    private TraderAdapter adapter;
    private List<Trader> adapterData;
    private EditText filterStr;
    private Button filterBtn;
    private ProgressDialog pd;
    private DaoSession session;
    private String CoV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader_filter);
        CoV = getIntent().getStringExtra("CoV");
        listView = (ListView) findViewById(R.id.listView);
        filterStr = (EditText) findViewById(R.id.filterStr);
        filterBtn = (Button) findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(this);
        pd = ProgressDialog.show(this, null, "正在检索数据");
        session = SuperClient.getDaoSession(this);
        QueryBuilder<Trader> queryBuilder = session.getTraderDao().queryBuilder();
        if(CoV!=null){
            if(CoV.equals("C")){
                queryBuilder.where(
                        TraderDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID()),
                        TraderDao.Properties.IsClient.eq(1)
                );
            }else if(CoV.equals("V")){
                queryBuilder.where(
                        TraderDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID()),
                        TraderDao.Properties.IsVendor.eq(1)
                );
            }
        }
        adapterData = queryBuilder.list();
        adapter = new TraderAdapter(this, adapterData);
        listView.setAdapter(adapter);
        if(pd!=null)pd.dismiss();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("Data", (Trader) adapterView.getItemAtPosition(i));
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
                QueryBuilder<Trader> queryBuilder = session.getTraderDao().queryBuilder();
                if(CoV!=null){
                    if(CoV.equals("C")){
                        queryBuilder.where(
                                TraderDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID()),
                                queryBuilder.or(
                                        TraderDao.Properties.TraderCode.like("%"+str+"%"),
                                        TraderDao.Properties.TraderName.like("%"+str+"%"),
                                        TraderDao.Properties.FullName.like("%"+str+"%")
                                ),
                                TraderDao.Properties.IsClient.eq(1)
                        );
                    }else if(CoV.equals("V")){
                        queryBuilder.where(
                                TraderDao.Properties.ShopID.eq(SuperClient.getCurrentOperator().getShopID()),
                                queryBuilder.or(
                                        TraderDao.Properties.TraderCode.like("%"+str+"%"),
                                        TraderDao.Properties.TraderName.like("%"+str+"%"),
                                        TraderDao.Properties.FullName.like("%"+str+"%")
                                ),
                                TraderDao.Properties.IsVendor.eq(1)
                        );
                    }
                    adapterData = queryBuilder.list();
                    if (adapter == null) {
                        adapter = new TraderAdapter(this, adapterData);
                        listView.setAdapter(adapter);
                    } else {
                        adapter.setData(adapterData);
                        adapter.notifyDataSetChanged();
                    }
                }

                break;
        }
    }
}
