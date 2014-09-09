package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.TransferDetail1Data;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by twisty on 14-8-16.
 */
public class TransferDetailAdapter extends BaseAdapter {
    private List<TransferDetail1Data> data;
    private LayoutInflater inflater;
    private DecimalFormat decimalFormat;
//    private StoreDao storeDao;
//    private GoodsDao goodsDao;
//    private UnitDao unitDao;

    public TransferDetailAdapter(Context context, List<TransferDetail1Data> data) {
        this.data = data;
//        DaoSession session = SuperClient.getDaoSession(context);
//        storeDao = session.getStoreDao();
//        goodsDao = session.getGoodsDao();
//        unitDao = session.getUnitDao();
        inflater = LayoutInflater.from(context);
        decimalFormat = new DecimalFormat("#.00");//格式化设置

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
        return data.get(position).getBillID() == null ? -1 : data.get(position).getBillID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        TransferDetail1Data transferDetail1Data = data.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.transfer_detail_item, null);
            viewHolder.ItemNo = (TextView) convertView.findViewById(R.id.ItemNo);
            viewHolder.GoodsCode = (TextView) convertView.findViewById(R.id.GoodsCode);
            viewHolder.GoodsName = (TextView) convertView.findViewById(R.id.GoodsName);
//            viewHolder.Spec = (TextView) convertView.findViewById(R.id.Spec);
            viewHolder.UnitName = (TextView) convertView.findViewById(R.id.Unit);
            viewHolder.Quantity = (TextView) convertView.findViewById(R.id.Quantity);
            viewHolder.UnitPrice = (TextView) convertView.findViewById(R.id.UnitPrice);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ItemNo.setText(position + 1 + "");
//        viewHolder.GoodsCode.setText(transferDetail1Data.getGoodsCode());
        viewHolder.GoodsName.setText(transferDetail1Data.getGoodsName() + transferDetail1Data.getSpecs());
//        viewHolder.Spec.setText(transferDetail1Data.getSpecs());
        viewHolder.UnitName.setText(transferDetail1Data.getUnitName());
        viewHolder.Quantity.setText(decimalFormat.format(transferDetail1Data.getUnitQuantity()));
        viewHolder.UnitPrice.setText(decimalFormat.format(transferDetail1Data.getUnitPrice()));
        viewHolder.Amount.setText(decimalFormat.format(transferDetail1Data.getAmount()));
        return convertView;
    }

    class ViewHolder {
        TextView ItemNo,
                GoodsCode, GoodsName,
        /* Spec, */UnitName,
                Quantity, UnitPrice, Amount;
    }
}
