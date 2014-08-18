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
import com.twisty.superclient.bean.PayMethod;

import java.util.List;

/**
 * Created by twisty on 14-8-18.
 */
public class PayMethodAdapter extends BaseAdapter {
    private List<PayMethod> data;
    private LayoutInflater inflater;

    public PayMethodAdapter(Context context,List<PayMethod> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<PayMethod> getData() {
        return data;
    }

    public void setData(List<PayMethod> data) {
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
        if(data!=null)return data.get(position).getPayMethodID();
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PayMethod payMethod = data.get(position);
        Viewholder viewholder;
        if(convertView==null){
            viewholder = new Viewholder();
            convertView = inflater.inflate(R.layout.spiner_drop_down,null);
            viewholder.PayMethodName = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(viewholder);
        }else{
            viewholder = (Viewholder) convertView.getTag();
        }
        viewholder.PayMethodName.setText(payMethod.getPaymethodName());
        return convertView;
    }


    @Override
    public boolean isEmpty() {
        if(data!=null)return data.isEmpty();
        return false;
    }


    class Viewholder{
        TextView PayMethodName;
    }
}
