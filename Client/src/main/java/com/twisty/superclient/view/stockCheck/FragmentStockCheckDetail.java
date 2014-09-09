package com.twisty.superclient.view.stockCheck;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
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
import com.twisty.superclient.adapter.StockCheckDetailAdapter;
import com.twisty.superclient.bean.StockCheckDetail1Data;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseFragment;

import java.util.ArrayList;

public class FragmentStockCheckDetail extends BaseFragment {
    public static final int ADDGOODS = 1, UPDATAGOODS = 2;
    private FragmentStockCheckHeader fragmentStockCheckHeader;

    private ListView listView;
    private ArrayList<StockCheckDetail1Data> Detail1Data = new ArrayList<StockCheckDetail1Data>();
    private StockCheckDetailAdapter adapter;
    private Double amount;
    private StockCheckDetail1Data currentDetail;
    private int currentItemNo;
    public FragmentStockCheckDetail() {
        // Required empty public constructor
    }

    public ArrayList<StockCheckDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<StockCheckDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
        if (adapter == null) {
            adapter = new StockCheckDetailAdapter(getActivity(), Detail1Data);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(Detail1Data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        fragmentStockCheckHeader = (FragmentStockCheckHeader) getFragmentManager().findFragmentByTag("header");
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
                Long storeID = fragmentStockCheckHeader.getMasterData().getStoreID();
                if (storeID == null || storeID == 0) {
                    CommonUtil.showToastError(getActivity(), "请选择仓库!", null);
                    return true;
                }
                Intent intent = new Intent(getActivity(), StockCheckAddGoodsActivity.class);
                intent.putExtra("StoreID", storeID);
                startActivityForResult(intent, ADDGOODS);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_check_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView) parent).setItemChecked(position, true);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentDetail = (StockCheckDetail1Data) parent.getItemAtPosition(position);
                currentItemNo = position;
                return false;
            }
        });
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("删除");
                menu.add("修改");
            }
        });
        return view;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("删除")) {
            Detail1Data.remove(currentDetail);
            adapter.setData(Detail1Data);
            adapter.notifyDataSetChanged();
        } else {

            Long storeID = fragmentStockCheckHeader.getMasterData().getStoreID();
            if (storeID == null || storeID == 0) {
                CommonUtil.showToastError(getActivity(), "请选择仓库!", null);
                return true;
            }
            Intent intent = new Intent(getActivity(), StockCheckAddGoodsActivity.class);
            intent.putExtra("StoreID", storeID);
            intent.putExtra("CurrentData", currentDetail);
            intent.putExtra("Type", UPDATAGOODS);
            startActivityForResult(intent, UPDATAGOODS);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADDGOODS) {
                ArrayList<StockCheckDetail1Data> retunData = (ArrayList<StockCheckDetail1Data>) data.getSerializableExtra("com.twisty.superclient.Data");
                if (adapter == null) {
                    Detail1Data = retunData;
                    if (Detail1Data != null) {
                        adapter = new StockCheckDetailAdapter(getActivity(), Detail1Data);
                        listView.setAdapter(adapter);
                    }
                } else {
                    if (Detail1Data != null) {
                        Detail1Data.addAll(retunData);
                        adapter.setData(Detail1Data);
                        adapter.notifyDataSetChanged();
                    }
                }
                setDetail1Data(Detail1Data);
            } else if (requestCode == UPDATAGOODS) {
                StockCheckDetail1Data returnData = (StockCheckDetail1Data) data.getSerializableExtra("Data");
                Detail1Data.remove(currentItemNo);
                Detail1Data.add(currentItemNo, returnData);
                adapter.setData(Detail1Data);
                adapter.notifyDataSetChanged();
            }
            if (Detail1Data != null) {
                double amount = 0;
                for (StockCheckDetail1Data stockCheckDetail1Data : Detail1Data) {
                    amount += stockCheckDetail1Data.getAmount();
                }
            }

        }
    }
}