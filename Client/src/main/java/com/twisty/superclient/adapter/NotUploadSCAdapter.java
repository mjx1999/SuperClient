package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.StockCheckMasterData;

import java.util.List;

/**
 * Created by twisty on 14-9-4.
 */
public class NotUploadSCAdapter extends BaseAdapter {
    private List<StockCheckMasterData> data;
    private LayoutInflater inflater;

    public NotUploadSCAdapter(Context context, List<StockCheckMasterData> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<StockCheckMasterData> getData() {
        return data;
    }

    public void setData(List<StockCheckMasterData> data) {
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
        StockCheckMasterData masterData = data.get(position);
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
        viewHolder.TraderName.setText(masterData.getStoreName());
        return convertView;
    }

    class ViewHolder {
        TextView BillCode, TraderName;
    }
}
