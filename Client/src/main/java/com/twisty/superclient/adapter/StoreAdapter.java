package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Store;

import java.util.List;

/**
 * Created by twisty on 14-8-18.
 */
public class StoreAdapter extends BaseAdapter {
    private List<Store> data;
    private LayoutInflater inflater;

    public StoreAdapter(Context context, List<Store> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<Store> getData() {
        return data;
    }

    public void setData(List<Store> data) {
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
        if(data!=null)return data.get(position).getStoreID();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Store store = data.get(position);
        Viewholder viewholder;
        if(convertView==null){
            viewholder = new Viewholder();
            convertView = inflater.inflate(R.layout.spinner_dropdown_2text,null);
            viewholder.storeCode = (TextView) convertView.findViewById(R.id.text1);
            viewholder.storeName = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(viewholder);
        }else{
            viewholder = (Viewholder) convertView.getTag();
        }
        viewholder.storeCode.setText(store.getStoreCode());
        viewholder.storeName.setText(store.getStoreName());
        return convertView;
    }


    @Override
    public boolean isEmpty() {
        return data != null && data.isEmpty();
    }

    class Viewholder{
        TextView storeCode,storeName;
    }
}
