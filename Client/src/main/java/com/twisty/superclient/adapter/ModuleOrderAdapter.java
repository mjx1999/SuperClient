package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.OrderModuleItem;

import java.util.List;

/**
 * Created by twisty on 14-8-16.
 */
public class ModuleOrderAdapter extends BaseAdapter
{
    private List<OrderModuleItem> data;
    private LayoutInflater inflater;

    public ModuleOrderAdapter(Context context, List<OrderModuleItem> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderModuleItem orderModuleItem = data.get(position);
        View view = inflater.inflate(R.layout.order_module_item,null);
        ImageView iconView = (ImageView) view.findViewById(R.id.orderItem_Icon);
        TextView nameView = (TextView) view.findViewById(R.id.orderItem_Name);
        iconView.setImageResource(orderModuleItem.getBillIcon());
        nameView.setText(orderModuleItem.getBillName());
        return view;
    }
}
