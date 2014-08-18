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
import com.twisty.superclient.bean.Account;

import java.util.List;

/**
 * Created by twisty on 14-8-18.
 */
public class AccountAdapter extends BaseAdapter {
    private List<Account> data;
    private LayoutInflater inflater;

    public AccountAdapter(Context context,List<Account> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<Account> getData() {
        return data;
    }

    public void setData(List<Account> data) {
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
        if(data!=null)return data.get(position).getAccountID();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Account account = data.get(position);
        Viewholder viewholder;
        if(convertView==null){
            viewholder = new Viewholder();
            convertView = inflater.inflate(R.layout.spiner_drop_down,null);
            viewholder.AccountName = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(viewholder);
        }else{
            viewholder = (Viewholder) convertView.getTag();
        }
        viewholder.AccountName.setText(account.getAccountName());
        return convertView;
    }


    @Override
    public boolean isEmpty() {
        if(data!=null)return data.isEmpty();
        return false;
    }

    class Viewholder{
        TextView AccountName;
    }
}
