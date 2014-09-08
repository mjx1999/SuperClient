package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.SalesOrderMasterData;

import java.util.List;

/**
 * Created by twisty on 14-9-4.
 */
public class NotUploadSOAdapter extends BaseAdapter {
    private List<SalesOrderMasterData> data;
    private LayoutInflater inflater;

    public NotUploadSOAdapter(Context context, List<SalesOrderMasterData> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<SalesOrderMasterData> getData() {
        return data;
    }

    public void setData(List<SalesOrderMasterData> data) {
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
        if (data != null) return data.get(position).getId();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SalesOrderMasterData masterData = data.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.not_upload_sb_item, null);
            viewHolder.BillCode = (TextView) convertView.findViewById(R.id.BillCode);
            viewHolder.TraderName = (TextView) convertView.findViewById(R.id.TraderName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.BillCode.setText(masterData.getBillCode());
        viewHolder.TraderName.setText(masterData.getTraderName());
        return convertView;
    }

    class ViewHolder {
        TextView BillCode, TraderName;
    }
}
