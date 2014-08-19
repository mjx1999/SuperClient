package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Goods;

import java.util.List;

/**
 * Created by twisty on 14-8-16.
 */
public class GoodsAdapter extends BaseAdapter {
    private List<Goods> data;
    private LayoutInflater inflater;

    public GoodsAdapter(Context context, List<Goods> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<Goods> getData() {
        return data;
    }

    public void setData(List<Goods> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        if(data!=null)return data.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(data!=null)return data.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getGoodsID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Goods goods = data.get(position);
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.goods_item,null);
            viewHolder.GoodsCode = (TextView) convertView.findViewById(R.id.GoodsCode);
            viewHolder.GoodsName = (TextView) convertView.findViewById(R.id.GoodsName);
            viewHolder.Spec = (TextView) convertView.findViewById(R.id.Spec);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.GoodsCode.setText(goods.getGoodsCode());
        viewHolder.GoodsName.setText(goods.getGoodsName());
        viewHolder.Spec.setText(goods.getSpecs());
        return convertView;
    }

    class ViewHolder {
        TextView GoodsCode,GoodsName,Spec;
    }
}
