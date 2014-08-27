package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.bean.TransferDetail1Data;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;

import java.util.List;

/**
 * Created by twisty on 14-8-16.
 */
public class TransferDetailAdapter extends BaseAdapter {
    private List<TransferDetail1Data> data;
    private LayoutInflater inflater;
    private StoreDao storeDao;
    private GoodsDao goodsDao;
    private UnitDao unitDao;

    public TransferDetailAdapter(Context context, List<TransferDetail1Data> data) {
        this.data = data;
        DaoSession session = SuperClient.getDaoSession(context);
        storeDao = session.getStoreDao();
        goodsDao = session.getGoodsDao();
        unitDao = session.getUnitDao();
        inflater = LayoutInflater.from(context);
    }

    public List<TransferDetail1Data> getData() {
        return data;
    }

    public void setData(List<TransferDetail1Data> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data != null) return data.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (data != null) return data.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getBillID()==null?-1:data.get(position).getBillID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        TransferDetail1Data transferDetail1Data = data.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sales_bill_detail_item, null);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TextView StoreCode, StoreName,
                GoodsCode, GoodsName,
                Spec, Unit,

                Quantity,
                OrigTaxPrice, Disc,
                TaxPrice, UnitPrice,
                TaxRate, TaxAmt, Amount;
    }
}
