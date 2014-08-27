package com.twisty.superclient.view.transfer;


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
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.AMKindDao;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.bean.TransferMasterData;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseFragment;
import com.twisty.superclient.view.filter.AMKindPop;
import com.twisty.superclient.view.filter.EmployeeFilterActivity;
import com.twisty.superclient.view.filter.StorePop;

import org.joda.time.DateTime;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTransferHeader extends BaseFragment implements View.OnClickListener {
    private TextView BillDate, BillKind, OutStore, InStore, EmpName, ShipTypeName;
    private EditText BillTo, BillCode;
    private TransferMasterData masterData = new TransferMasterData();
    private DateTime dateTime;
    private StoreDao storeDao;
    private AMKindDao amKindDao;

    public FragmentTransferHeader() {
    }

    public TransferMasterData getMasterData() {
        if (masterData.getBillID() == null) masterData.setBillID(-1L);
        masterData.setBillTo(BillTo.getText().toString());
        return masterData;
    }

    public void setMasterData(TransferMasterData masterData) {
        if (masterData != null) {
            this.masterData = masterData;
            BillCode.setText(masterData.getBillCode());
            BillDate.setText(masterData.getBillDate());
            BillKind.setText(masterData.getBillKindName());
            InStore.setText(masterData.getInStoreName());
            OutStore.setText(masterData.getStoreName());
            ShipTypeName.setText(masterData.getShipTypeName());
            BillTo.setText(masterData.getBillTo());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateTime = new DateTime();
        storeDao = SuperClient.getDaoSession(getActivity()).getStoreDao();
        amKindDao = SuperClient.getDaoSession(getActivity()).getAMKindDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer_header, container, false);

        BillCode = (EditText) view.findViewById(R.id.BillCode);
        BillDate = (TextView) view.findViewById(R.id.BillDate);
        BillKind = (TextView) view.findViewById(R.id.BillKindName);
        OutStore = (TextView) view.findViewById(R.id.OutStore);
        InStore = (TextView) view.findViewById(R.id.InStore);
        EmpName = (TextView) view.findViewById(R.id.Employee);
        ShipTypeName = (TextView) view.findViewById(R.id.ShipTypeName);
        BillTo = (EditText) view.findViewById(R.id.BillTo);
        BillDate.setOnClickListener(this);
        BillKind.setOnClickListener(this);
        OutStore.setOnClickListener(this);
        InStore.setOnClickListener(this);
        EmpName.setOnClickListener(this);
        ShipTypeName.setOnClickListener(this);
        String billCode = "AL-" + SuperClient.getDefaultStoreCode() + "-" + dateTime.toString("YYYYMMdd-HHmmss");
        BillCode.setText(billCode);
        BillDate.setText(dateTime.toString("YYYY-MM-dd"));
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.InStore:
                StorePop inStorePop = new StorePop(getActivity(), storeDao.loadAll(), new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        masterData.setStoreID(store.getStoreID());
                        InStore.setText(store.getStoreCode() + "  " + store.getStoreName());
                    }
                });
                inStorePop.showPopupWindow(v);
                break;
            case R.id.OutStore:
                StorePop outStorePop = new StorePop(getActivity(), storeDao.loadAll(), new StorePop.onItemClickListener() {
                    @Override
                    public void onItemClick(Store store) {
                        masterData.setInStoreID(store.getStoreID());
                        OutStore.setText(store.getStoreCode() + "  " + store.getStoreName());
                    }
                });
                outStorePop.showPopupWindow(v);
                break;
            case R.id.ShipTypeName:
                QueryBuilder<AMKind> shipTypeQueryBuilder = amKindDao.queryBuilder();
                shipTypeQueryBuilder.where(AMKindDao.Properties.Kind.eq(100));
                List<AMKind> shipTypes = shipTypeQueryBuilder.list();
                AMKindPop shipTypePop = new AMKindPop(getActivity(), shipTypes, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        masterData.setShipType(amKind.getID());
                        ShipTypeName.setText(amKind.getName());
                    }
                });
                shipTypePop.showPopupWindow(v);
                break;
            case R.id.Employee:
                Intent intent1 = new Intent(getActivity(), EmployeeFilterActivity.class);
                startActivityForResult(intent1, 2);
                break;
            case R.id.BillKindName:
                QueryBuilder<AMKind> billKindQueryBuilder = amKindDao.queryBuilder();
                billKindQueryBuilder.where(AMKindDao.Properties.Kind.eq(8));
                List<AMKind> billkinds = billKindQueryBuilder.list();
                log.i(billkinds.size());
                AMKindPop billKindPop = new AMKindPop(getActivity(), billkinds, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        BillKind.setText(amKind.getName());
                        masterData.setBillKind(amKind.getID().intValue());
                    }
                });
                billKindPop.showPopupWindow(v);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 2:
                    Employee employee = (Employee) data.getSerializableExtra("Data");
                    EmpName.setText(employee.getEmpName());
                    masterData.setEmpID(employee.getEmpID());
                    break;
            }
        }
    }
}
