package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.SalesBill;

import java.util.List;

/**
 * Created by twisty on 14-8-16.
 */
public class SalesBillAdapter extends BaseAdapter {
    private List<SalesBill> data;
    private LayoutInflater inflater;

    public SalesBillAdapter(Context context, List<SalesBill> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<SalesBill> getData() {
        return data;
    }

    public void setData(List<SalesBill> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        if(data!=null)return data.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(data!=null)return data.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getBillID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        SalesBill salesBill = data.get(position);
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sales_bill_item,null);
            viewHolder.BillCode = (TextView) convertView.findViewById(R.id.BillCode);
            viewHolder.BillDate = (TextView) convertView.findViewById(R.id.BillDate);
            viewHolder.BillKindName = (TextView) convertView.findViewById(R.id.BillKindName);
            viewHolder.BillStateName = (TextView) convertView.findViewById(R.id.BillStateName);
            viewHolder.TraderName = (TextView) convertView.findViewById(R.id.TraderName);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            viewHolder.ReferAmt = (TextView) convertView.findViewById(R.id.ReferAmt);
            viewHolder.UnReferAmt = (TextView) convertView.findViewById(R.id.UnReferAmt);
            viewHolder.OpName = (TextView) convertView.findViewById(R.id.OpName);
            viewHolder.Checkor = (TextView) convertView.findViewById(R.id.Checkor);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.BillCode.setText(salesBill.getBillCode());
        viewHolder.BillDate.setText(salesBill.getBillDate());
        viewHolder.BillKindName.setText(salesBill.getBillKindName());
        viewHolder.BillStateName.setText(salesBill.getBillStateName());
        viewHolder.TraderName.setText(salesBill.getTraderName());
        viewHolder.Amount.setText(salesBill.getAmount()+"");
        viewHolder.ReferAmt.setText(salesBill.getReferAmt()+"");
        viewHolder.UnReferAmt.setText((salesBill.getAmount()- salesBill.getReferAmt())+"");
        viewHolder.OpName.setText(salesBill.getOpName());
        viewHolder.Checkor.setText(salesBill.getCheckor());

        return convertView;
    }

    class ViewHolder {
        TextView BillCode,BillDate,BillKindName,BillStateName,TraderName,Amount,ReferAmt,UnReferAmt,OpName,Checkor;
    }
}
