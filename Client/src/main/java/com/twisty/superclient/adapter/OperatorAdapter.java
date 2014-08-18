package com.twisty.superclient.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Operator;

import java.util.List;

/**
 * Created by twisty on 14-8-10.
 */
public class OperatorAdapter extends BaseAdapter {
    private List<Operator> data;
    private LayoutInflater inflater;

    public OperatorAdapter(Context context,List<Operator> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if(data!=null)return data.size();
        return 0;
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.spiner_drop_down, null);
            viewHolder.OperatorName = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.OperatorName.setText(data.get(position).getOpName());
        return convertView;
    }


    @Override
    public boolean isEmpty() {
        return data != null && data.isEmpty();
    }

    class ViewHolder{
        TextView OperatorName;
    }

}
