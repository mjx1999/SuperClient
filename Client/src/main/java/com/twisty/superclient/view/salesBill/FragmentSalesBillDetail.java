package com.twisty.superclient.view.salesBill;

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
import com.twisty.superclient.adapter.SalesBillDetailAdapter;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.AMKindDao;
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.util.CommonUtil;
import com.twisty.superclient.view.BaseFragment;

import java.util.ArrayList;

import de.greenrobot.dao.query.QueryBuilder;

public class FragmentSalesBillDetail extends BaseFragment {

    public static final int ADDGOODS = 1, UPDATAGOODS = 2;
    private ListView listView;
    private SalesBillDetailAdapter adapter;
    private ArrayList<SalesBillDetail1Data> salesBillDetail1Datas;
    private SalesBillDetail1Data currentDetail;
    private int currentItemNo;

    public FragmentSalesBillDetail() {
        // Required empty public constructor
    }

    public static FragmentSalesBillDetail newInstance() {
        FragmentSalesBillDetail fragment = new FragmentSalesBillDetail();
        return fragment;
    }


    public ArrayList<SalesBillDetail1Data> getSalesBillDetail1Datas() {
        return salesBillDetail1Datas;
    }

    public void setSalesBillDetail1Datas(ArrayList<SalesBillDetail1Data> salesBillDetail1Datas) {
        this.salesBillDetail1Datas = salesBillDetail1Datas;
        if (adapter == null) {
            adapter = new SalesBillDetailAdapter(getActivity(), salesBillDetail1Datas);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(salesBillDetail1Datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
                FragmentSalesBIllHeader fragmentSalesBIllHeader = (FragmentSalesBIllHeader) getFragmentManager().findFragmentByTag("header");
                Long traderid = fragmentSalesBIllHeader.getSalesBillMasterData().getTraderID();
                Long noteTypeID = fragmentSalesBIllHeader.getSalesBillMasterData().getNoteTypeID();
                if (traderid == null) {
                    CommonUtil.showToastError(getActivity(), "请选择客户!", null);
                    return true;
                }
                if (noteTypeID == null) {
                    CommonUtil.showToastError(getActivity(), "请选择发票类型!", null);
                    return true;
                }
                TraderDao traderDao = SuperClient.getDaoSession(getActivity()).getTraderDao();
                QueryBuilder<Trader> traderQueryBuilder = traderDao.queryBuilder();
                traderQueryBuilder.where(TraderDao.Properties.TraderID.eq(traderid));
                Trader trader = traderQueryBuilder.unique();

                AMKindDao amKindDao = SuperClient.getDaoSession(getActivity()).getAMKindDao();
                QueryBuilder<AMKind> amKindQueryBuilder = amKindDao.queryBuilder();
                amKindQueryBuilder.where(AMKindDao.Properties.Kind.eq(14), AMKindDao.Properties.ID.eq(noteTypeID));
                AMKind noteType = amKindQueryBuilder.unique();


                Intent intent = new Intent(getActivity(), SalesBillAddGoodsActivity.class);
                intent.putExtra("Trader", trader);
                intent.putExtra("NoteType", noteType);
                startActivityForResult(intent, ADDGOODS);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_bill_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADDGOODS) {
                ArrayList<SalesBillDetail1Data> retunData = (ArrayList<SalesBillDetail1Data>) data.getSerializableExtra("com.twisty.superclient.Data");
                if (adapter == null) {
                    salesBillDetail1Datas = retunData;
                    if (salesBillDetail1Datas != null) {
                        adapter = new SalesBillDetailAdapter(getActivity(), salesBillDetail1Datas);
                        listView.setAdapter(adapter);
                    }
                } else {
                    if (salesBillDetail1Datas != null) {
                        salesBillDetail1Datas.addAll(retunData);
                        adapter.setData(salesBillDetail1Datas);
                        adapter.notifyDataSetChanged();
                    }
                }
                setSalesBillDetail1Datas(salesBillDetail1Datas);
            } else if (requestCode == UPDATAGOODS) {
                SalesBillDetail1Data returnData = (SalesBillDetail1Data) data.getSerializableExtra("Data");
                salesBillDetail1Datas.remove(currentItemNo);
                salesBillDetail1Datas.add(currentItemNo, returnData);
                adapter.setData(salesBillDetail1Datas);
                adapter.notifyDataSetChanged();
            }
//            if (salesBillDetail1Datas != null) {
//                double amount = 0;
//                for (SalesBillDetail1Data salesBillDetail1Data : salesBillDetail1Datas) {
//                    amount += salesBillDetail1Data.getAmount();
//                }
//            }

        }
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentDetail = (SalesBillDetail1Data) parent.getItemAtPosition(position);
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
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("删除")) {
            salesBillDetail1Datas.remove(currentDetail);
            adapter.setData(salesBillDetail1Datas);
            adapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(getActivity(), SalesBillAddGoodsActivity.class);
            intent.putExtra("CurrentData", currentDetail);
            intent.putExtra("Type", UPDATAGOODS);
            startActivityForResult(intent, UPDATAGOODS);
        }
        return super.onContextItemSelected(item);
    }
}
