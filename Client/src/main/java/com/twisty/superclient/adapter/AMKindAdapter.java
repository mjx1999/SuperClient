package com.twisty.superclient.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.AMKind;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class AMKindAdapter implements SpinnerAdapter {
    private List<AMKind> data;
    private LayoutInflater inflater;

    public AMKindAdapter(Context context,List<AMKind> data) {
        AMKind amKind = new AMKind();
        amKind.setID(-1L);
        amKind.setName("所有状态");
        data.add(0,amKind);
        this.data = data;
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
        if(data!=null)return data.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(data!=null)return data.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        if(data!=null)return data.get(i).getID();
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        AMKind amKind = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.spinner_item,null);
            viewHolder.AMKindName = (TextView) view.findViewById(R.id.text1);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.AMKindName.setText(amKind.getName());
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
        AMKind amKind = data.get(position);
        convertView = inflater.inflate(R.layout.spiner_drop_down,null);
        TextView textView    = (TextView) convertView.findViewById(R.id.text1);
        textView.setText(amKind.getName());
        return convertView;
    }

    class ViewHolder {
        TextView AMKindName;
    }
}
