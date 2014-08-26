package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.StockCheck;

import java.util.List;

/**
 * Created by twisty on 14-8-26.
 */
public class StockCheckAdapter extends BaseAdapter {
    private List<StockCheck> data;
    private LayoutInflater inflater;

    public StockCheckAdapter(Context context, List<StockCheck> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<StockCheck> getData() {
        return data;
    }

    public void setData(List<StockCheck> data) {
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
        return data.get(position).getBillID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StockCheck stockCheck = data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.stock_check_item,null);
            viewHolder.BillCode = (TextView) convertView.findViewById(R.id.BillCode);
            viewHolder.BillDate = (TextView) convertView.findViewById(R.id.BillDate);
            viewHolder.BillStateName = (TextView) convertView.findViewById(R.id.BillStateName);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            viewHolder.StoreName = (TextView) convertView.findViewById(R.id.StoreName);
            viewHolder.OpName = (TextView) convertView.findViewById(R.id.OpName);
            viewHolder.CheckorName = (TextView) convertView.findViewById(R.id.CheckorName);
            viewHolder.EmpName = (TextView) convertView.findViewById(R.id.EmpName);
            viewHolder.DepartmentName = (TextView) convertView.findViewById(R.id.DepartmentName);
            viewHolder.IOTypeName = (TextView) convertView.findViewById(R.id.IOTypeName);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.BillCode.setText(stockCheck.getBillCode());
        viewHolder.BillDate.setText(stockCheck.getBillDate());
        viewHolder.BillStateName.setText(stockCheck.getBillStateName());
        viewHolder.Amount.setText(stockCheck.getAmount()+"");
        viewHolder.StoreName.setText(stockCheck.getStoreName());
        viewHolder.OpName.setText(stockCheck.getOpName());
        viewHolder.CheckorName.setText(stockCheck.getCheckorName());
        viewHolder.EmpName.setText(stockCheck.getEmpName());
        viewHolder.DepartmentName.setText(stockCheck.getDepartmentName());
        viewHolder.IOTypeName.setText(stockCheck.getIOTypeName());
        return convertView;
    }

    class ViewHolder {
        TextView BillCode, BillDate, BillStateName,
                Amount,StoreName,
                OpName, CheckorName, EmpName, DepartmentName,IOTypeName;
    }
}
