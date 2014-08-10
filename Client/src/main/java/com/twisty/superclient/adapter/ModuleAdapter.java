package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Module;

import java.util.List;

/**
 * Created by twisty on 14-8-10.
 */
public class ModuleAdapter extends BaseAdapter {
    private List<Module> data;
    private Context context;
    private LayoutInflater inflater;

    public ModuleAdapter(List<Module> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
        return data.get(position).getModuleID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.module_item,null);
        ImageView moduleIconView = (ImageView) view.findViewById(R.id.moduleIcon);
        TextView moduleNameView = (TextView) view.findViewById(R.id.moduleName);
        moduleIconView.setImageResource(data.get(position).getModuleIcon());
        moduleNameView.setText(data.get(position).getModuleName());

        return view;
    }
}
