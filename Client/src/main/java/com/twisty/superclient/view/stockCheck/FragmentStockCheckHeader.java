package com.twisty.superclient.view.stockCheck;



import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Department;
import com.twisty.superclient.bean.DepartmentDao;
import com.twisty.superclient.bean.IoType;
import com.twisty.superclient.bean.IoTypeDao;
import com.twisty.superclient.bean.StockCheckMasterData;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseFragment;
import com.twisty.superclient.view.filter.DepartmentPop;
import com.twisty.superclient.view.filter.EmployeeFilterActivity;
import com.twisty.superclient.view.filter.IOTypePop;
import com.twisty.superclient.view.filter.StorePop;

import org.joda.time.DateTime;

public class FragmentStockCheckHeader extends BaseFragment implements View.OnClickListener{
    private StockCheckMasterData masterData = new StockCheckMasterData();
    private EditText BillCode,Remark;
    private TextView BillDate,StoreName,IOType,DepartmentName,Employee;
    private DateTime dateTime;
    private StoreDao storeDao;
    private DepartmentDao departmentDao;
    private IoTypeDao ioTypeDao;
    public StockCheckMasterData getMasterData() {
        if(masterData !=null&& masterData.getBillID()==null) masterData.setBillID(-1L);
        if(masterData !=null&& masterData.getBillState()==null) masterData.setBillState(1);
        masterData.setOpID(SuperClient.getCurrentOperator().getOpID());
        masterData.setBillCode(BillCode.getText().toString());
        masterData.setBillDate(BillDate.getText().toString());
        masterData.setRemark(Remark.getText().toString());
        return masterData;
    }

    public void setMasterData(StockCheckMasterData masterData) {
        if(masterData!=null){
            this.masterData = masterData;
            BillCode.setText(this.masterData.getBillCode());
            Remark.setText(this.masterData.getRemark());
            BillDate.setText(this.masterData.getBillDate());
            StoreName.setText(this.masterData.getStoreName());
            IOType.setText(this.masterData.getIOTypeName());
            DepartmentName.setText(this.masterData.getDepartmentName());
            Employee.setText(this.masterData.getEmpName());
        }
    }

    public FragmentStockCheckHeader() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateTime = new DateTime();
        storeDao = SuperClient.getDaoSession(getActivity()).getStoreDao();
        departmentDao = SuperClient.getDaoSession(getActivity()).getDepartmentDao();
        ioTypeDao = SuperClient.getDaoSession(getActivity()).getIoTypeDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_stock_check_header, container, false);
        BillCode = (EditText) view.findViewById(R.id.BillCode);
        Remark = (EditText) view.findViewById(R.id.Remark);
        BillDate = (TextView) view.findViewById(R.id.BillDate);
        StoreName = (TextView) view.findViewById(R.id.StoreName);
        IOType = (TextView) view.findViewById(R.id.IOType);
        DepartmentName = (TextView) view.findViewById(R.id.DepartmentName);
        Employee = (TextView) view.findViewById(R.id.Employee);
        BillDate.setOnClickListener(this);
        StoreName.setOnClickListener(this);
        IOType.setOnClickListener(this);
        DepartmentName.setOnClickListener(this);
        Employee.setOnClickListener(this);
        String billCode = "BA-" + SuperClient.getDefaultStoreCode() + "-" + dateTime.toString("YYYYMMdd-HHmmss");
        BillCode.setText(billCode);
        BillDate.setText(dateTime.toString("YYYY-MM-dd"));

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BillDate:
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        String billDate = i + "-" + String.format("%02d",(i2+1)) + "-" + i3;
                        BillDate.setText(billDate);
                        masterData.setBillDate(billDate);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear()-1, dateTime.getDayOfMonth());
                dpd.show();
                break;
            case R.id.StoreName:
                StorePop storePop = new StorePop(getActivity(),storeDao.loadAll(),new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        masterData.setStoreID(store.getStoreID());
                        StoreName.setText(store.getStoreCode()+"  "+store.getStoreName());
                    }
                });
                storePop.showPopupWindow(v);
                break;
            case R.id.IOType:
                IOTypePop ioTypePop = new IOTypePop(getActivity(),ioTypeDao.loadAll(),new IOTypePop.onItemClickListener() {
                    @Override
                    public void onItemClick(IoType ioType) {
                        masterData.setIOTypeID(ioType.getIoTypeID());
                        masterData.setIOTypeName(ioType.getIoTypeName());
                        IOType.setText(ioType.getIoTypeName());
                    }
                });
                ioTypePop.showPopupWindow(v);
                break;
            case R.id.DepartmentName:
                DepartmentPop departmentPop = new DepartmentPop(getActivity(),departmentDao.loadAll(),new DepartmentPop.onItemClickListener() {
                    @Override
                    public void onItemClick(Department department) {
                        masterData.setDepartmentID(department.getDepartmentID());
                        masterData.setDeppartmentCode(department.getDepartmentCode());
                        masterData.setDepartmentName(department.getDepartmentName());
                        DepartmentName.setText(department.getDepartmentName());
                    }
                });
                departmentPop.showPopupWindow(v);
                break;
            case R.id.Employee:
                Intent intent1 = new Intent(getActivity(), EmployeeFilterActivity.class);
                startActivityForResult(intent1, 2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case 2:
                    com.twisty.superclient.bean.Employee employee = (com.twisty.superclient.bean.Employee) data.getSerializableExtra("Data");
                    Employee.setText(employee.getEmpName());
                    masterData.setEmpID(employee.getEmpID());
                    break;
            }
        }
    }
}
