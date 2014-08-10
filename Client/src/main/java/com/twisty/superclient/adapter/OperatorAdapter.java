package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Operator;

import java.util.List;

/**
 * Created by twisty on 14-8-10.
 */
public class OperatorAdapter extends BaseAdapter {
    private List<Operator> data;
    private Context context;
    private LayoutInflater inflater;

    public OperatorAdapter(List<Operator> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
        return data.get(position).getOpID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.dropdown_item,null);
        TextView opNameView = (TextView) view.findViewById(R.id.dropName);
        opNameView.setText(data.get(position).getOpName());
        return view;
    }
}
