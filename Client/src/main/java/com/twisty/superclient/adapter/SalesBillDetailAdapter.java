package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.SalesBillDetail1Data;

import java.util.List;

/**
 * Created by twisty on 14-8-16.
 */
public class SalesBillDetailAdapter extends BaseAdapter {
    private List<SalesBillDetail1Data> data;
    private LayoutInflater inflater;
//    private StoreDao storeDao;
//    private GoodsDao goodsDao;
//    private UnitDao unitDao;

    public SalesBillDetailAdapter(Context context, List<SalesBillDetail1Data> data) {
        this.data = data;
//        DaoSession session = SuperClient.getDaoSession(context);
//        storeDao = session.getStoreDao();
//        goodsDao = session.getGoodsDao();
//        unitDao = session.getUnitDao();
        inflater = LayoutInflater.from(context);
    }

    public List<SalesBillDetail1Data> getData() {
        return data;
    }

    public void setData(List<SalesBillDetail1Data> data) {
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
        SalesBillDetail1Data salesBillDetail1Data = data.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sales_bill_detail_item, null);
            viewHolder.StoreCode = (TextView) convertView.findViewById(R.id.StoreCode);
            viewHolder.StoreName = (TextView) convertView.findViewById(R.id.StoreName);
            viewHolder.GoodsCode = (TextView) convertView.findViewById(R.id.GoodsCode);
            viewHolder.GoodsName = (TextView) convertView.findViewById(R.id.GoodsName);
            viewHolder.Spec = (TextView) convertView.findViewById(R.id.Spec);
            viewHolder.Unit = (TextView) convertView.findViewById(R.id.Unit);
            viewHolder.Quantity = (TextView) convertView.findViewById(R.id.Quantity);
            viewHolder.OrigTaxPrice = (TextView) convertView.findViewById(R.id.OrigTaxPrice);
            viewHolder.Disc = (TextView) convertView.findViewById(R.id.Disc);
            viewHolder.TaxPrice = (TextView) convertView.findViewById(R.id.TaxPrice);
            viewHolder.UnitPrice = (TextView) convertView.findViewById(R.id.UnitPrice);
            viewHolder.TaxRate = (TextView) convertView.findViewById(R.id.TaxRate);
            viewHolder.TaxAmt = (TextView) convertView.findViewById(R.id.TaxAmt);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.StoreCode.setText(salesBillDetail1Data.getStoreCode());
        viewHolder.StoreName.setText(salesBillDetail1Data.getStoreName());
        viewHolder.GoodsCode.setText(salesBillDetail1Data.getGoodsCode());
        viewHolder.GoodsName.setText(salesBillDetail1Data.getGoodsName());
        viewHolder.Spec.setText(salesBillDetail1Data.getSpecs());
        viewHolder.Unit.setText(salesBillDetail1Data.getUnitName());

        viewHolder.Quantity.setText(salesBillDetail1Data.getQuantity()+"");
        viewHolder.OrigTaxPrice.setText(salesBillDetail1Data.getOrigPrice()+"");
        viewHolder.Disc.setText(salesBillDetail1Data.getDisc()+"");
        viewHolder.TaxRate.setText(salesBillDetail1Data.getTaxRate()+"");
        viewHolder.UnitPrice.setText(salesBillDetail1Data.getUnitPrice()+"");
        viewHolder.TaxRate.setText(salesBillDetail1Data.getTaxRate()+"");
        viewHolder.TaxAmt.setText(salesBillDetail1Data.getTaxAmt()+"");
        viewHolder.Amount.setText(salesBillDetail1Data.getAmount()+"");

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
