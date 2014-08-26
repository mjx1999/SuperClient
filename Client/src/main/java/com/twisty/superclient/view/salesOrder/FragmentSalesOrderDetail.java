package com.twisty.superclient.view.salesOrder;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.SalesOrderDetailAdapter;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.view.BaseFragment;

import java.util.ArrayList;

public class FragmentSalesOrderDetail extends BaseFragment {

    private ListView listView;
    private ArrayList<SalesOrderDetail1Data> Detail1Data = new ArrayList<SalesOrderDetail1Data>();
    private SalesOrderDetailAdapter adapter;
    private Double amount;
    private static final int ADDGOODS = 1;

    public FragmentSalesOrderDetail() {
    }

    public ArrayList<SalesOrderDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<SalesOrderDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
        if(adapter==null){
            adapter = new SalesOrderDetailAdapter(getActivity(),Detail1Data);
            listView.setAdapter(adapter);
        }else{
            adapter.setData(Detail1Data);
            adapter.notifyDataSetChanged();
        }
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sales_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID  = item.getItemId();
        switch (itemID){
            case R.id.add:
                Intent intent = new Intent(getActivity(), SalesOrderAddGoodsActivity.class);
                startActivityForResult(intent, ADDGOODS);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sales_bill_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView)parent).setItemChecked(position,true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(resultCode==Activity.RESULT_OK){
                    if(requestCode==ADDGOODS){
                        ArrayList<SalesOrderDetail1Data> retunData = (ArrayList<SalesOrderDetail1Data>) data.getSerializableExtra("com.twisty.superclient.Data");
                        if(adapter==null){
                            Detail1Data = retunData;
                            if(Detail1Data!=null){
                                adapter = new SalesOrderDetailAdapter(getActivity(),Detail1Data);
                                listView.setAdapter(adapter);
                            }
                        }else{
                            if(Detail1Data!=null){
                                Detail1Data.addAll(retunData);
                                adapter.setData(Detail1Data);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        setDetail1Data(Detail1Data);
                    }
                    if(Detail1Data!=null){
                        double amount = 0;
                        for (SalesOrderDetail1Data salesOrderDetail1Data :Detail1Data){
                            amount+= salesOrderDetail1Data.getAmount();
                        }
                    }

                }
    }
}
