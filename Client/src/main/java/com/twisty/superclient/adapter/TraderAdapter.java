package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Trader;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class TraderAdapter extends BaseAdapter {
    private List<Trader> data;
    private LayoutInflater inflater;

    public TraderAdapter(Context context ,List<Trader> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public List<Trader> getData() {
        return data;
    }

    public void setData(List<Trader> data) {
        this.data = data;
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
        if(data!=null)return data.get(i).getTraderID();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Trader trader = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.trader_item,null);
            viewHolder.TraderCode = (TextView) view.findViewById(R.id.TraderCode);
            viewHolder.TraderName = (TextView) view.findViewById(R.id.TraderName);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.TraderCode.setText(trader.getTraderCode());
        viewHolder.TraderName.setText(trader.getTraderName());
        return view;
    }

    class ViewHolder {
        TextView TraderCode,TraderName;
    }
}
