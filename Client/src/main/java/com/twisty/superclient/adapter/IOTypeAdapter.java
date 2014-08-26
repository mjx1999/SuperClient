package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.IoType;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class IOTypeAdapter extends BaseAdapter {
    private List<IoType> data;
    private LayoutInflater inflater;

    public IOTypeAdapter(Context context, List<IoType> data) {
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
        if(data!=null)return data.get(i).getIoTypeID();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        IoType ioType = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.spiner_drop_down,null);
            viewHolder.IOTypeName = (TextView) view.findViewById(R.id.text1);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.IOTypeName.setText(ioType.getIoTypeName());
        return view;
    }

    @Override
    public boolean isEmpty() {
        return data != null && data.isEmpty();
    }

    class ViewHolder {
        TextView IOTypeName;
    }
}
