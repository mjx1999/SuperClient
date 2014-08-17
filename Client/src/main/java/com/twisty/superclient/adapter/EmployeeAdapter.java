package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Department;
import com.twisty.superclient.bean.DepartmentDao;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.global.SuperClient;

import java.util.List;

/**
 * Created by twisty on 2014-8-17.
 */
public class EmployeeAdapter extends BaseAdapter {
    private List<Employee> data;
    private LayoutInflater inflater;
    private DepartmentDao departmentDao;
    public EmployeeAdapter(Context context,List<Employee> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        departmentDao = SuperClient.getDaoSession(context).getDepartmentDao();
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
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
        if(data!=null)return data.get(i).getEmpID();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Employee employee = data.get(i);
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.employee_item,null);
            viewHolder.EmpCode = (TextView) view.findViewById(R.id.EmpCode);
            viewHolder.EmpName = (TextView) view.findViewById(R.id.EmpName);
            viewHolder.Department = (TextView) view.findViewById(R.id.Department);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.EmpCode.setText(employee.getEmpCode());
        viewHolder.EmpName.setText(employee.getEmpName());
        Department department = departmentDao.queryBuilder().where(DepartmentDao.Properties.DepartmentID.eq(employee.getDepartmentID())).unique();
        if(department!=null)viewHolder.Department.setText(department.getDepartmentName());
        return view;
    }

    class ViewHolder {
        TextView EmpCode,EmpName,Department;
    }
}
