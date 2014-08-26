package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Department;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class DepartmentAdapter extends BaseAdapter {
    private List<Department> data;
    private LayoutInflater inflater;

    public DepartmentAdapter(Context context, List<Department> data) {
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
        if(data!=null)return data.get(i).getDepartmentID();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Department department = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.spiner_drop_down,null);
            viewHolder.DepartmentName = (TextView) view.findViewById(R.id.text1);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.DepartmentName.setText(department.getDepartmentName());
        return view;
    }

    @Override
    public boolean isEmpty() {
        if(data!=null)return data.isEmpty();
        return false;
    }


    class ViewHolder {
        TextView DepartmentName;
    }
}
