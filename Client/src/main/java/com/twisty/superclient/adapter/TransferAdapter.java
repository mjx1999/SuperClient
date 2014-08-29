package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Transfer;

import java.util.List;

/**
 * Created by twisty on 14-8-26.
 */
public class TransferAdapter extends BaseAdapter {
    private List<Transfer> data;
    private LayoutInflater inflater;

    public TransferAdapter(Context context, List<Transfer> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<Transfer> getData() {
        return data;
    }

    public void setData(List<Transfer> data) {
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
        Transfer transfer = data.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.transfer_item, null);
            viewHolder.BillCode = (TextView) convertView.findViewById(R.id.BillCode);
            viewHolder.BillDate = (TextView) convertView.findViewById(R.id.BillDate);
            viewHolder.BillStateName = (TextView) convertView.findViewById(R.id.BillStateName);
            viewHolder.BillKindName = (TextView) convertView.findViewById(R.id.BillKindName);
            viewHolder.InStore = (TextView) convertView.findViewById(R.id.InStoreName);
            viewHolder.OutStore = (TextView) convertView.findViewById(R.id.OutStore);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            viewHolder.OpName = (TextView) convertView.findViewById(R.id.OpName);
            viewHolder.CheckorName = (TextView) convertView.findViewById(R.id.CheckorName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.BillCode.setText(transfer.getBillCode());
        viewHolder.BillDate.setText(transfer.getBillDate());
        viewHolder.BillStateName.setText(transfer.getBillStateName());
        viewHolder.Amount.setText(transfer.getAmount() + "");
        viewHolder.OpName.setText(transfer.getOpName());
        viewHolder.CheckorName.setText(transfer.getCheckorName());
        viewHolder.BillKindName.setText(transfer.getBillKindName());
        viewHolder.InStore.setText(transfer.getInStoreName());
        viewHolder.OutStore.setText(transfer.getStoreName());
        return convertView;
    }

    class ViewHolder {
        TextView BillCode, BillDate, BillStateName, BillKindName,
                Amount, InStore, OutStore,
                OpName, CheckorName;
    }
}
