package com.twisty.superclient.view.transfer;


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
import com.twisty.superclient.adapter.TransferDetailAdapter;
import com.twisty.superclient.bean.TransferDetail1Data;
import com.twisty.superclient.view.BaseFragment;

import java.util.ArrayList;

public class FragmentTransferDetail extends BaseFragment {
    private static final int ADDGOODS = 1;
    private ListView listView;
    private ArrayList<TransferDetail1Data> detail1Data = new ArrayList<TransferDetail1Data>();
    private TransferDetailAdapter adapter;

    public FragmentTransferDetail() {
    }

    public ArrayList<TransferDetail1Data> getDetail1Data() {
        return detail1Data;
    }

    public void setDetail1Data(ArrayList<TransferDetail1Data> detail1Data) {
        if (detail1Data != null) {
            this.detail1Data = detail1Data;
            if (adapter == null) {
                adapter = new TransferDetailAdapter(getActivity(), this.detail1Data);
                listView.setAdapter(adapter);
            } else {
                adapter.setData(this.detail1Data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_bill_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sales_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        switch (itemID) {
            case R.id.add:
                Intent intent = new Intent(getActivity(), TransferAddGoodsActivity.class);
                startActivityForResult(intent, ADDGOODS);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView) parent).setItemChecked(position, true);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADDGOODS) {
                ArrayList<TransferDetail1Data> retunData = (ArrayList<TransferDetail1Data>) data.getSerializableExtra("com.twisty.superclient.Data");
                if (adapter == null) {
                    detail1Data = retunData;
                    if (detail1Data != null) {
                        adapter = new TransferDetailAdapter(getActivity(), detail1Data);
                        listView.setAdapter(adapter);
                    }
                } else {
                    if (detail1Data != null) {
                        detail1Data.addAll(retunData);
                        adapter.setData(detail1Data);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

        }
    }
}
