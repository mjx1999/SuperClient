package com.twisty.superclient.view.salesBill;

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
import com.twisty.superclient.adapter.SalesBillDetailAdapter;
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.view.BaseFragment;

import java.util.ArrayList;

public class FragmentSalesBillDetail extends BaseFragment {

    private ListView listView;
    private SalesBillDetailAdapter adapter;
    private ArrayList<SalesBillDetail1Data> adapterData;
    private static final int ADDGOODS = 1;
    private ArrayList<SalesBillDetail1Data> salesBillDetail1Datas;


    public ArrayList<SalesBillDetail1Data> getSalesBillDetail1Datas() {
        return salesBillDetail1Datas;
    }

    public void setSalesBillDetail1Datas(ArrayList<SalesBillDetail1Data> salesBillDetail1Datas) {
        this.salesBillDetail1Datas = salesBillDetail1Datas;
        if(adapter==null){
            adapter = new SalesBillDetailAdapter(getActivity(),salesBillDetail1Datas);
            listView.setAdapter(adapter);
        }else{
            adapter.setData(salesBillDetail1Datas);
            adapter.notifyDataSetChanged();
        }
    }

    public static FragmentSalesBillDetail newInstance() {
        FragmentSalesBillDetail fragment = new FragmentSalesBillDetail();
        return fragment;
    }
    public FragmentSalesBillDetail() {
        // Required empty public constructor
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
                Intent intent = new Intent(getActivity(), SalesBillAddGoodsActivity.class);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==ADDGOODS){
                ArrayList<SalesBillDetail1Data> retunData = (ArrayList<SalesBillDetail1Data>) data.getSerializableExtra("com.twisty.superclient.Data");
                if(adapter==null){
                    adapterData = retunData;
                    if(adapterData!=null){
                        adapter = new SalesBillDetailAdapter(getActivity(),adapterData);
                        listView.setAdapter(adapter);
                    }
                }else{
                    if(adapterData!=null){
                        adapterData.addAll(retunData);
                        adapter.setData(adapterData);
                        adapter.notifyDataSetChanged();
                    }
                }
                setSalesBillDetail1Datas(adapterData);
            }
            if(adapterData!=null){
                double amount = 0;
                for (SalesBillDetail1Data salesBillDetail1Data :adapterData){
                    amount+= salesBillDetail1Data.getAmount();
                }
            }

        }
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


}
