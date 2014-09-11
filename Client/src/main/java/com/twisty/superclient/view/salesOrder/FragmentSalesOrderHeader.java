package com.twisty.superclient.view.salesOrder;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.PayMethodDao;
import com.twisty.superclient.bean.SalesOrderMasterData;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseFragment;
import com.twisty.superclient.view.filter.EmployeeFilterActivity;
import com.twisty.superclient.view.filter.PayMethodPop;
import com.twisty.superclient.view.filter.TraderFilterActivity;

import org.joda.time.DateTime;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSalesOrderHeader extends BaseFragment implements View.OnClickListener {
    private SalesOrderMasterData masterData = new SalesOrderMasterData();
    private TextView BillDate, RevDate, ValidDate, TraderName, Employee, PayMethod;
    private EditText BillCode, BillTo, LinkMan, ContactPhone, Contactor;
    private DateTime dateTime = new DateTime();
    private PayMethodDao payMethodDao;

    public FragmentSalesOrderHeader() {
    }

    //    private AccountDao accountDao;
//    private AMKindDao amKindDao;
//    private TraderDao traderDao;
//    private EmployeeDao employeeDao;
    public SalesOrderMasterData getMasterData() {
        masterData.setBillState(0);
        if (masterData.getBillID() == null || masterData.getBillID() == 0)
            masterData.setBillID(-1L);
        masterData.setBillCode(BillCode.getText().toString());
        masterData.setContactPhone(ContactPhone.getText().toString().trim());
        masterData.setShopid(SuperClient.getCurrentOperator().getShopID());
        masterData.setBillDate(BillDate.getText().toString());
        masterData.setRevDate(RevDate.getText().toString());
        masterData.setValidDate(ValidDate.getText().toString());
        masterData.setOpID(SuperClient.getCurrentOperator().getOpID());
        masterData.setContactor(Contactor.getText().toString());
        masterData.setLinkMan(LinkMan.getText().toString());
        return masterData;
    }

    public void setMasterData(SalesOrderMasterData masterData) {
        if (masterData != null) {
            this.masterData = masterData;
            BillCode.setText(masterData.getBillCode());
            BillDate.setText(masterData.getBillDate());
            RevDate.setText(masterData.getRevDate());
            ValidDate.setText(masterData.getValidDate());
            TraderName.setText(masterData.getTraderName());
            BillTo.setText(masterData.getBillto());
            Contactor.setText(masterData.getContactor());
            LinkMan.setText(masterData.getLinkMan());
            ContactPhone.setText(masterData.getContactPhone());
            PayMethod.setText(masterData.getPaymethodName());
            Employee.setText(masterData.getEmpName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payMethodDao = SuperClient.getDaoSession(getActivity()).getPayMethodDao();
//        accountDao = SuperClient.getDaoSession(getActivity()).getAccountDao();
//        amKindDao = SuperClient.getDaoSession(getActivity()).getAMKindDao();
//        traderDao = SuperClient.getDaoSession(getActivity()).getTraderDao();
//        employeeDao = SuperClient.getDaoSession(getActivity()).getEmployeeDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_order_header, container, false);
        BillCode = (EditText) view.findViewById(R.id.BillCode);
        TraderName = (TextView) view.findViewById(R.id.TraderName);
        BillDate = (TextView) view.findViewById(R.id.BillDate);
        BillTo = (EditText) view.findViewById(R.id.BillTo);
        ContactPhone = (EditText) view.findViewById(R.id.ContactPhone);
        Employee = (TextView) view.findViewById(R.id.Employee);
        RevDate = (TextView) view.findViewById(R.id.RevDate);
        ValidDate = (TextView) view.findViewById(R.id.ValidDate);
        LinkMan = (EditText) view.findViewById(R.id.LinkMan);
        Contactor = (EditText) view.findViewById(R.id.Contactor);
        PayMethod = (TextView) view.findViewById(R.id.PayMethod);
        Employee.setOnClickListener(this);
        TraderName.setOnClickListener(this);
        BillDate.setOnClickListener(this);
        RevDate.setOnClickListener(this);
        ValidDate.setOnClickListener(this);
        PayMethod.setOnClickListener(this);
        String billCode = "SO-" + SuperClient.getDefaultStoreCode() + "-" + dateTime.toString("YYYYMMdd-HHmmss");
        BillCode.setText(billCode);
        BillDate.setText(dateTime.toString("YYYY-MM-dd"));
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TraderName:
                Intent intent = new Intent(getActivity(), TraderFilterActivity.class);
                intent.putExtra("CoV", "C");
                startActivityForResult(intent, 1);
                break;
            case R.id.BillDate:
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        String billDate = i + "-" + String.format("%02d", (i2 + 1)) + "-" + i3;
                        BillDate.setText(billDate);
                        masterData.setBillDate(billDate);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());
                dpd.show();
                break;
            case R.id.RevDate:
                DatePickerDialog dpdRev = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        String billDate = i + "-" + String.format("%02d", (i2 + 1)) + "-" + i3;
                        RevDate.setText(billDate);
                        masterData.setRevDate(billDate);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());
                dpdRev.show();
                break;
            case R.id.ValidDate:
                DatePickerDialog dpdVali = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        String billDate = i + "-" + String.format("%02d", (i2 + 1)) + "-" + i3;
                        ValidDate.setText(billDate);
                        masterData.setValidDate(billDate);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());
                dpdVali.show();
                break;
            case R.id.Employee:
                Intent intent1 = new Intent(getActivity(), EmployeeFilterActivity.class);
                startActivityForResult(intent1, 2);
                break;
            case R.id.PayMethod:
                List<com.twisty.superclient.bean.PayMethod> payMethods = payMethodDao.loadAll();
                PayMethodPop payMethodPop = new PayMethodPop(getActivity(), payMethods, new PayMethodPop.onItemClickListener() {
                    @Override
                    public void onItemClick(com.twisty.superclient.bean.PayMethod payMethod) {
                        PayMethod.setText(payMethod.getPaymethodName());
                        masterData.setPaymethodID(payMethod.getPayMethodID());
                        masterData.setPaymethodName(payMethod.getPaymethodName());
                    }
                });
                payMethodPop.showPopupWindow(v);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Trader trader = (Trader) data.getSerializableExtra("Data");
                    masterData.setTraderId(trader.getTraderID());
                    masterData.setTraderCode(trader.getTraderCode());
                    masterData.setTraderName(trader.getTraderName());
                    masterData.setContactPhone(trader.getTel1());
                    masterData.setLinkMan(trader.getContactor());
                    masterData.setBillto(trader.getShipTo());

                    TraderName.setText(trader.getTraderName());
                    ContactPhone.setText(trader.getTel1());
                    BillTo.setText(trader.getShipTo());
                    LinkMan.setText(trader.getContactor());
                    break;
                case 2:
                    com.twisty.superclient.bean.Employee employee = (Employee) data.getSerializableExtra("Data");
                    Employee.setText(employee.getEmpName());
                    masterData.setEmpID(employee.getEmpID());
                    masterData.setEmpCode(employee.getEmpCode());
                    masterData.setEmpName(employee.getEmpName());
                    break;
            }
        }
    }
}
