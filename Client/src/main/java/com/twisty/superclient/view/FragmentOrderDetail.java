package com.twisty.superclient.view;

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
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.OrderDetailAdapter;
import com.twisty.superclient.bean.Detail1Data;

import java.util.ArrayList;

public class FragmentOrderDetail extends BaseFragment {

    private ListView listView;
    private TextView Amount;
    private OrderDetailAdapter adapter;
    private ArrayList<Detail1Data> adapterData;
    private static final int ADDGOODS = 1;
    private ArrayList<Detail1Data> detail1Datas;


    public ArrayList<Detail1Data> getDetail1Datas() {
        return detail1Datas;
    }

    public void setDetail1Datas(ArrayList<Detail1Data> detail1Datas) {
        this.detail1Datas = detail1Datas;

    }

    public static FragmentOrderDetail newInstance() {
        FragmentOrderDetail fragment = new FragmentOrderDetail();
        return fragment;
    }
    public FragmentOrderDetail() {
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
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        inflater.inflate(R.menu.order_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID  = item.getItemId();
        switch (itemID){
            case R.id.add:
                Intent intent = new Intent(getActivity(), AddGoodsActivity.class);
                startActivityForResult(intent, ADDGOODS);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_order_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        Amount = (TextView) view.findViewById(R.id.Amount);
        return  view;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if(adapterData!=null){
                double amount = 0;
                for (Detail1Data detail1Data:adapterData){
                    amount+=detail1Data.getAmount();
                }
                Amount.setText(amount+"");
            }
            if(requestCode==ADDGOODS){
                ArrayList<Detail1Data> retunData = (ArrayList<Detail1Data>) data.getSerializableExtra("com.twisty.superclient.Data");
                if(adapter==null){
                    adapterData = retunData;
                    if(adapterData!=null){
                        adapter = new OrderDetailAdapter(getActivity(),adapterData);
                        listView.setAdapter(adapter);
                    }
                }else{
                    if(adapterData!=null){
                        adapterData.addAll(retunData);
                        adapter.setData(adapterData);
                        adapter.notifyDataSetChanged();
                    }
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
