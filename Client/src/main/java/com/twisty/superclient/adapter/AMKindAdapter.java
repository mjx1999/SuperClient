package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.bean.AMKind;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class AMKindAdapter extends BaseAdapter {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        AMKind amKind = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(android.R.layout.simple_spinner_item,null);
            viewHolder.AMKindName = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.AMKindName.setText(amKind.getName());
        return view;
    }
    class ViewHolder {
        TextView AMKindName;
    }
}
