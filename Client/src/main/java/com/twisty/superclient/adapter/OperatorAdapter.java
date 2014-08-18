package com.twisty.superclient.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Operator;

import java.util.List;

/**
 * Created by twisty on 14-8-10.
 */
public class OperatorAdapter implements SpinnerAdapter {
    private List<Operator> data;
    private Context context;
    private LayoutInflater inflater;

    public OperatorAdapter(List<Operator> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.spinner_item,null);
        TextView opNameView = (TextView) view.findViewById(R.id.text1);
        opNameView.setText(data.get(position).getOpName());
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        if(data!=null)return data.isEmpty();
        return false;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Operator operator = data.get(position);
        convertView = inflater.inflate(R.layout.spiner_drop_down,null);
        TextView textView = (TextView) convertView.findViewById(R.id.text1);
        textView.setText(operator.getOpName());
        return convertView;
    }
}
