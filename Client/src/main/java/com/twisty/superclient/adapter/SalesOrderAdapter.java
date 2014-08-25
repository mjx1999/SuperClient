package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.SalesOrder;

import java.util.List;

/**
 * Created by twisty on 14-8-25.
 */
public class SalesOrderAdapter extends BaseAdapter {
    private List<SalesOrder> data;
    private LayoutInflater inflater;

    public SalesOrderAdapter(Context context, List<SalesOrder> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<SalesOrder> getData() {
        return data;
    }

    public void setData(List<SalesOrder> data) {
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
        ViewHolder viewHolder;
        SalesOrder salesOrder = data.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sales_order_item, null);
            viewHolder.BillCode = (TextView) convertView.findViewById(R.id.BillCode);
            viewHolder.BillDate = (TextView) convertView.findViewById(R.id.BillDate);
            viewHolder.BillStateName = (TextView) convertView.findViewById(R.id.BillStateName);
            viewHolder.TraderName = (TextView) convertView.findViewById(R.id.TraderName);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            viewHolder.OpName = (TextView) convertView.findViewById(R.id.OpName);
            viewHolder.CheckorName = (TextView) convertView.findViewById(R.id.CheckorName);
            viewHolder.RevDate = (TextView) convertView.findViewById(R.id.RevDate);
            viewHolder.ValidDate = (TextView) convertView.findViewById(R.id.ValidDate);
            viewHolder.ForwardAmt = (TextView) convertView.findViewById(R.id.ForwardAmt);
            viewHolder.LinkMan = (TextView) convertView.findViewById(R.id.LinkMan);
            viewHolder.ShipTypeName = (TextView) convertView.findViewById(R.id.ShipTypeName);
            viewHolder.PayMethodName = (TextView) convertView.findViewById(R.id.PaymethodName);
            viewHolder.EmpName = (TextView) convertView.findViewById(R.id.EmpName);
            viewHolder.DepartmentName = (TextView) convertView.findViewById(R.id.DepartmentName);
            viewHolder.Closed = (TextView) convertView.findViewById(R.id.Closed);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.BillCode.setText(salesOrder.getBillCode());
        viewHolder.BillDate.setText(salesOrder.getBillDate());
        viewHolder.BillStateName.setText(salesOrder.getBillStateName());
        viewHolder.TraderName.setText(salesOrder.getTraderName());
        viewHolder.Amount.setText(salesOrder.getAmount() + "");
        viewHolder.OpName.setText(salesOrder.getOpName());
        viewHolder.CheckorName.setText(salesOrder.getCheckorName());
        viewHolder.RevDate.setText(salesOrder.getRevDate());
        viewHolder.ValidDate.setText(salesOrder.getValidDate());
        viewHolder.ForwardAmt.setText(salesOrder.getForwardAmt() + "");
        viewHolder.LinkMan.setText(salesOrder.getLinkMan());
        viewHolder.ShipTypeName.setText(salesOrder.getShipTypeName());
        viewHolder.PayMethodName.setText(salesOrder.getPaymethodName());
        viewHolder.EmpName.setText(salesOrder.getEmpName());
        viewHolder.DepartmentName.setText(salesOrder.getDepartmentName());
        viewHolder.Closed.setText(salesOrder.getClosed()==0?"否":"是");

        return convertView;
    }

    class ViewHolder {
        TextView BillCode, BillDate, BillStateName, RevDate, ValidDate,
                TraderName, ForwardAmt, Amount, LinkMan, ShipTypeName, PayMethodName,
                OpName, CheckorName, EmpName, DepartmentName, Closed;
    }
}
