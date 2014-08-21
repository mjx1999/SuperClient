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
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.AMKindDao;
import com.twisty.superclient.bean.Account;
import com.twisty.superclient.bean.AccountDao;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.EmployeeDao;
import com.twisty.superclient.bean.MasterData;
import com.twisty.superclient.bean.PayMethod;
import com.twisty.superclient.bean.PayMethodDao;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.filter.AMKindPop;
import com.twisty.superclient.view.filter.AccountPop;
import com.twisty.superclient.view.filter.EmployeeFilterActivity;
import com.twisty.superclient.view.filter.PayMethodPop;
import com.twisty.superclient.view.filter.TraderFilterActivity;

import org.joda.time.DateTime;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;



public class FragmentHeader extends BaseFragment implements View.OnClickListener {
    DateTime dateTime = new DateTime();
    private TextView BillCode, TraderName, BillDate, EmpName, Account, NoteType, PayMethod;
    private EditText BillTo, ContactPhone, PayAmt;
    private MasterData masterData = new MasterData();
    private PayMethodDao payMethodDao;
    private AccountDao accountDao;
    private AMKindDao amKindDao;
    private TraderDao traderDao;
    private EmployeeDao employeeDao;

    public FragmentHeader() {
        // Required empty public constructor
    }

    public static FragmentHeader newInstance() {
        return new FragmentHeader();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payMethodDao = SuperClient.getDaoSession(getActivity()).getPayMethodDao();
        accountDao = SuperClient.getDaoSession(getActivity()).getAccountDao();
        amKindDao = SuperClient.getDaoSession(getActivity()).getAMKindDao();
        traderDao = SuperClient.getDaoSession(getActivity()).getTraderDao();
        employeeDao = SuperClient.getDaoSession(getActivity()).getEmployeeDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        BillCode = (TextView) view.findViewById(R.id.BillCode);
        TraderName = (TextView) view.findViewById(R.id.TraderName);
        BillDate = (TextView) view.findViewById(R.id.BillDate);
        BillTo = (EditText) view.findViewById(R.id.BillTo);
        ContactPhone = (EditText) view.findViewById(R.id.ContactPhone);
        PayAmt = (EditText) view.findViewById(R.id.PayAmt);
        PayMethod = (TextView) view.findViewById(R.id.PayMethod);
        Account = (TextView) view.findViewById(R.id.Account);
        EmpName = (TextView) view.findViewById(R.id.Employee);
        NoteType = (TextView) view.findViewById(R.id.NoteType);
        Account.setOnClickListener(this);
        PayMethod.setOnClickListener(this);
        EmpName.setOnClickListener(this);
        TraderName.setOnClickListener(this);
        BillDate.setOnClickListener(this);
        NoteType.setOnClickListener(this);


        String billCode = "SS-" + SuperClient.getDefaultStoreCode() + "-" + dateTime.toString("YYYYMMdd-HHmmss");
        BillCode.setText(billCode);
        BillDate.setText(dateTime.toString("YYYY-MM-dd"));


        return view;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TraderName:
                Intent intent = new Intent(getActivity(), TraderFilterActivity.class);
                intent.putExtra("CoV", "C");
                startActivityForResult(intent, 1);
                break;
            case R.id.BillDate:
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        String billDate = i + "-" + i2 + "-" + i3;
                        BillDate.setText(billDate);
                        masterData.setBillDate(billDate);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear() + 1, dateTime.getDayOfMonth());
                dpd.show();
                break;
            case R.id.Employee:
                Intent intent1 = new Intent(getActivity(), EmployeeFilterActivity.class);
                startActivityForResult(intent1, 2);
                break;
            case R.id.Account:
                List<Account> accounts = accountDao.loadAll();
                AccountPop accountPop = new AccountPop(getActivity(), accounts, new AccountPop.onItemClickListener() {
                    @Override
                    public void onItemClick(Account account) {
                        Account.setText(account.getAccountName());
                        masterData.setAccountID(account.getAccountID());
                    }
                });
                accountPop.showPopupWindow(view);
                break;
            case R.id.NoteType:
                QueryBuilder<AMKind> queryBuilder = amKindDao.queryBuilder();
                queryBuilder.where(AMKindDao.Properties.Kind.eq(14));
                List<AMKind> amKinds = queryBuilder.list();
                log.i(amKinds.size());
                AMKindPop amKindPop = new AMKindPop(getActivity(), amKinds, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        NoteType.setText(amKind.getName());
                        masterData.setNoteTypeID(amKind.getID());
                    }
                });
                amKindPop.showPopupWindow(view);
                break;
            case R.id.PayMethod:
                List<PayMethod> payMethods = payMethodDao.loadAll();
                PayMethodPop payMethodPop = new PayMethodPop(getActivity(), payMethods, new PayMethodPop.onItemClickListener() {
                    @Override
                    public void onItemClick(PayMethod payMethod) {
                        PayMethod.setText(payMethod.getPaymethodName());
                        masterData.setPayMethodID(payMethod.getPayMethodID());
                    }
                });
                payMethodPop.showPopupWindow(view);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public MasterData getMasterData() {
        masterData.setBillKind(1);
        masterData.setBillState(0);
        masterData.setBillID(-1L);
        masterData.setBillCode(BillCode.getText().toString());
        masterData.setContactPhone(ContactPhone.getText().toString().trim());
        masterData.setBillTo(BillTo.getText().toString().trim());
        masterData.setShopID(SuperClient.getCurrentOperator().getShopID());
        masterData.setBillDate(BillDate.getText().toString());
        masterData.setOpID(SuperClient.getCurrentOperator().getOpID());
        masterData.setAmount(123123.123);
        return masterData;
    }

    public void setMasterData(MasterData masterData) {
        if(masterData!=null){

        this.masterData = masterData;
        BillCode.setText(masterData.getBillCode());
        BillDate.setText(masterData.getBillDate());
        if (masterData.getTraderID() != null) {

            QueryBuilder<Trader> traderQueryBuilder = traderDao.queryBuilder();
            traderQueryBuilder.where(TraderDao.Properties.TraderID.eq(masterData.getTraderID()));
            Trader trader = traderQueryBuilder.unique();
            if (trader != null) {
                TraderName.setText(trader.getTraderName());
                ContactPhone.setText(trader.getTel1());
                BillTo.setText(trader.getShipTo());
            }
        }
        if (masterData.getPayMethodID() != null) {
            QueryBuilder<PayMethod> payMethodQueryBuilder = payMethodDao.queryBuilder();
            payMethodQueryBuilder.where(PayMethodDao.Properties.PayMethodID.eq(masterData.getPayMethodID()));
            PayMethod payMethod = payMethodQueryBuilder.unique();
            if (payMethod != null) {
                PayMethod.setText(payMethod.getPaymethodName());
            }
        }
        if (masterData.getPayAmt() != null) PayAmt.setText(masterData.getPayAmt().toString());
        if (masterData.getAccountID() != null) {
            QueryBuilder<Account> accountQueryBuilder = accountDao.queryBuilder();
            accountQueryBuilder.where(AccountDao.Properties.AccountID.eq(masterData.getAccountID()));
            Account account = accountQueryBuilder.unique();
            if (account != null) {
                Account.setText(account.getAccountName());
            }
        }
        if (masterData.getNoteTypeID() != null) {
            QueryBuilder<AMKind> queryBuilder = amKindDao.queryBuilder();
            queryBuilder.where(AMKindDao.Properties.Kind.eq(14), AMKindDao.Properties.ID.eq(masterData.getNoteTypeID()));
            AMKind amKind = queryBuilder.unique();
            if (amKind != null) {
                NoteType.setText(amKind.getName());
            }
        }
        if (masterData.getEmpID() != null) {
            QueryBuilder<Employee> employeeQueryBuilder = employeeDao.queryBuilder();
            employeeQueryBuilder.where(EmployeeDao.Properties.EmpID.eq(masterData.getEmpID()));
            Employee employee = employeeQueryBuilder.unique();
            if (employee != null) {
                EmpName.setText(employee.getEmpName());
            }
        }
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Trader trader = (Trader) data.getSerializableExtra("Data");
                TraderName.setText(trader.getTraderName());
                masterData.setTraderID(trader.getTraderID());
                ContactPhone.setText(trader.getTel1());
                BillTo.setText(trader.getShipTo());
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Employee employee = (Employee) data.getSerializableExtra("Data");
                EmpName.setText(employee.getEmpName());
                masterData.setEmpID(employee.getEmpID());
            }
        }
    }

}
