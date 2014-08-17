package com.twisty.superclient.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.MasterData;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.filter.EmployeeFilterActivity;
import com.twisty.superclient.view.filter.TraderFilterActivity;

import org.joda.time.DateTime;

public class FragmentHeader extends BaseFragment implements View.OnClickListener {
    private TextView BillCode,TraderName, BillDate, EmpName;
    private EditText BillTo,ContactPhone,PayAmt;
    private Spinner PayMethod,Account;

    private MasterData masterData;
    DateTime dateTime = new DateTime();

    private OnSaveListener mListener;


    public static FragmentHeader newInstance(MasterData masterData) {
        FragmentHeader fragment = new FragmentHeader();
        if (masterData != null) {
            Bundle args = new Bundle();
            args.putSerializable("Data", masterData);
            fragment.setArguments(args);
        }

        return fragment;
    }

    public FragmentHeader() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            masterData = (MasterData) getArguments().getSerializable("Data");
        }else {
            masterData = new MasterData();
            masterData.setBillKind(1);
            masterData.setBillState(0);
            masterData.setBillID(-1L);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        BillCode = (TextView) view.findViewById(R.id.BillCode);
        BillCode.setText("SS-"+ SuperClient.getDefaultStoreCode()+"-"+dateTime.toString("YYYYMMdd-HHmmss"));
        TraderName = (TextView) view.findViewById(R.id.TraderName);
        BillDate = (TextView) view.findViewById(R.id.BillDate);
        BillTo = (EditText) view.findViewById(R.id.BillTo);
        ContactPhone = (EditText) view.findViewById(R.id.ContactPhone);
        PayAmt = (EditText) view.findViewById(R.id.PayAmt);
        PayMethod = (Spinner) view.findViewById(R.id.PayMethod);
        Account = (Spinner) view.findViewById(R.id.Account);
        EmpName = (TextView) view.findViewById(R.id.Employee);
        EmpName.setOnClickListener(this);
        TraderName.setOnClickListener(this);
        BillDate.setOnClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSaveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " 需要实现OnSaveListener!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(masterData!=null)mListener.onSaveHeader(masterData);
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TraderName:
                Intent intent = new Intent(getActivity(), TraderFilterActivity.class);
                intent.putExtra("CoV","C");
                startActivityForResult(intent, 1);
                break;
            case R.id.BillDate:
                DatePickerDialog dpd = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        String billDate = i+"-"+i2+"-"+i3;
                        BillDate.setText(billDate);
                        masterData.setBillDate(billDate);
                    }
                },dateTime.getYear(),dateTime.getMonthOfYear()+1,dateTime.getDayOfMonth());
                dpd.show();
                break;
            case R.id.Employee:
                Intent intent1 = new Intent(getActivity(), EmployeeFilterActivity.class);
                startActivityForResult(intent1, 2);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //TODO 隐藏时
    }

    public MasterData getMasterData() {
        masterData.setBillCode(BillCode.getText().toString());
        masterData.setContactPhone(ContactPhone.getText().toString().trim());
        masterData.setBillTo(BillTo.getText().toString().trim());
        return masterData;
    }

    public void setMasterData(MasterData masterData) {
        this.masterData = masterData;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Trader trader = (Trader) data.getSerializableExtra("Data");
                TraderName.setText(trader.getTraderName());
                masterData.setTraderID(trader.getTraderID());
                ContactPhone.setText(trader.getPhone());
                BillTo.setText(trader.getShipTo());
            }
        }else if(requestCode==2){
            if(resultCode==Activity.RESULT_OK){
                Employee employee = (Employee) data.getSerializableExtra("Data");
                EmpName.setText(employee.getEmpName());
                masterData.setEmpID(employee.getEmpID());
            }
        }
    }

    public interface OnSaveListener {
        public void onSaveHeader(MasterData masterData);
    }

}
