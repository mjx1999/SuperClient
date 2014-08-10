package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Accset;

import java.util.List;

/**
 * Created by twisty on 14-8-10.
 */
public class AccsetAdapter extends BaseAdapter {
    private List<Accset> data;
    private Context context;
    private LayoutInflater inflater;

    public AccsetAdapter(List<Accset> data, Context context) {
        this.data = data;
        this.context = context;
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
        return data.get(position).getAccsetID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.accset_listitem,null);
        TextView accCode = (TextView) view.findViewById(R.id.accsetCode);
        TextView accName = (TextView) view.findViewById(R.id.accsetName);
        accCode.setText(data.get(position).getAccsetCode());
        accName.setText(data.get(position).getAccsetName());
        return view;
    }
}
