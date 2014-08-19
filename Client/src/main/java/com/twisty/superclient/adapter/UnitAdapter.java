package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Unit;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class UnitAdapter extends BaseAdapter {
    private List<Unit> data;
    private LayoutInflater inflater;

    public UnitAdapter(Context context, List<Unit> data) {
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
        if(data!=null)return data.get(i).getUnitID();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Unit unit = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.spiner_drop_down,null);
            viewHolder.UnitName = (TextView) view.findViewById(R.id.text1);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.UnitName.setText(unit.getUnitName());
        return view;
    }

    @Override
    public boolean isEmpty() {
        if(data!=null)return data.isEmpty();
        return false;
    }

//    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        AMKind amKind = data.get(position);
//        convertView = inflater.inflate(R.layout.spiner_drop_down,null);
//        TextView textView    = (TextView) convertView.findViewById(R.id.text1);
//        textView.setText(amKind.getName());
//        return convertView;
//    }

    class ViewHolder {
        TextView UnitName;
    }
}
