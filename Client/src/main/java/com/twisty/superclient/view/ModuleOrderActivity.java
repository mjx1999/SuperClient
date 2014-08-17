package com.twisty.superclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.ModuleOrderAdapter;
import com.twisty.superclient.bean.OrderModuleItem;

import java.util.ArrayList;
import java.util.List;

public class ModuleOrderActivity extends BaseActivity {
    private ListView listView;
    private ModuleOrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_order);
        listView = (ListView) findViewById(R.id.orderList);
        List<OrderModuleItem> data = new ArrayList<OrderModuleItem>();
        OrderModuleItem sellItem = new OrderModuleItem();
        sellItem.setBillIcon(R.drawable.ic_launcher);
        sellItem.setBillName("销售订单");

        data.add(sellItem);


        adapter = new ModuleOrderAdapter(this,data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(ModuleOrderActivity.this,SellOrderActivity.class));
                        break;
                }
            }
        });
    }

}
