package com.twisty.superclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.ModuleOrderAdapter;
import com.twisty.superclient.bean.OrderModuleItem;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.view.salesBill.SalesBillActivity;
import com.twisty.superclient.view.salesOrder.SalesOrderActivity;
import com.twisty.superclient.view.stockCheck.StockCheckActivity;
import com.twisty.superclient.view.transfer.TransferActivity;

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
        OrderModuleItem salesBIllItem = new OrderModuleItem();
        salesBIllItem.setBillIcon(R.drawable.ic_launcher);
        salesBIllItem.setBillName("销售开单");

        OrderModuleItem salesOrderItem = new OrderModuleItem();
        salesOrderItem.setBillIcon(R.drawable.ic_launcher);
        salesOrderItem.setBillName("客户订单");


        OrderModuleItem allotBillItem = new OrderModuleItem();
        allotBillItem.setBillIcon(R.drawable.ic_launcher);
        allotBillItem.setBillName("调拨单");





        OrderModuleItem balitem = new OrderModuleItem();
        balitem.setBillIcon(R.drawable.ic_launcher);
        balitem.setBillName("盘点单");





        data.add(salesBIllItem);
        data.add(salesOrderItem);
        data.add(allotBillItem);
        data.add(balitem);

        adapter = new ModuleOrderAdapter(this,data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(ModuleOrderActivity.this, SalesBillActivity.class);
                        intent.putExtra("From", GlobalConstant.FROM_NEW);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ModuleOrderActivity.this, SalesOrderActivity.class);
                        intent.putExtra("From", GlobalConstant.FROM_NEW);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(ModuleOrderActivity.this, TransferActivity.class);
                        intent.putExtra("From", GlobalConstant.FROM_NEW);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(ModuleOrderActivity.this, StockCheckActivity.class);
                        intent.putExtra("From", GlobalConstant.FROM_NEW);
                        startActivity(intent);

                        break;
                }
            }
        });
    }

}
