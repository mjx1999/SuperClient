package com.twisty.superclient.view.salesBill;

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
import com.twisty.superclient.bean.PayMethod;
import com.twisty.superclient.bean.PayMethodDao;
import com.twisty.superclient.bean.SalesBillMasterData;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderDao;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.view.BaseFragment;
import com.twisty.superclient.view.filter.AMKindPop;
import com.twisty.superclient.view.filter.AccountPop;
import com.twisty.superclient.view.filter.EmployeeFilterActivity;
import com.twisty.superclient.view.filter.PayMethodPop;
import com.twisty.superclient.view.filter.TraderFilterActivity;

import org.joda.time.DateTime;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;


public class FragmentSalesBIllHeader extends BaseFragment implements View.OnClickListener {
    DateTime dateTime = new DateTime();
    private TextView  TraderName, BillDate, EmpName, Account, NoteType, PayMethod,BillKind;
    private EditText BillCode,BillTo, ContactPhone, PayAmt;
    private SalesBillMasterData salesBillMasterData = new SalesBillMasterData();
    private PayMethodDao payMethodDao;
    private AccountDao accountDao;
    private AMKindDao amKindDao;
    private TraderDao traderDao;
    private EmployeeDao employeeDao;

    public FragmentSalesBIllHeader() {
    }

    public static FragmentSalesBIllHeader newInstance() {
        return new FragmentSalesBIllHeader();
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
        View view = inflater.inflate(R.layout.fragment_sales_bill_header, container, false);
        BillKind = (TextView) view.findViewById(R.id.BillKindName);
        BillCode = (EditText) view.findViewById(R.id.BillCode);
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
                        String billDate = i + "-" + String.format("%02d",(i2+1)) + "-" + i3;
                        BillDate.setText(billDate);
                        salesBillMasterData.setBillDate(billDate);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear()-1, dateTime.getDayOfMonth());
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
                        salesBillMasterData.setAccountID(account.getAccountID());
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
                        salesBillMasterData.setNoteTypeID(amKind.getID());
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
                        salesBillMasterData.setPayMethodID(payMethod.getPayMethodID());
                    }
                });
                payMethodPop.showPopupWindow(view);
                break;
            case R.id.BillKindName:
                QueryBuilder<AMKind> billKindQueryBuilder = amKindDao.queryBuilder();
                billKindQueryBuilder.where(AMKindDao.Properties.Kind.eq(6));
                List<AMKind> billkinds = billKindQueryBuilder.list();
                log.i(billkinds.size());
                AMKindPop billKindPop = new AMKindPop(getActivity(), billkinds, new AMKindPop.onItemClickListener() {
                    @Override
                    public void onItemClick(AMKind amKind) {
                        BillKind.setText(amKind.getName());
                        salesBillMasterData.setBillKind(amKind.getID().intValue());
                    }
                });
                billKindPop.showPopupWindow(view);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public SalesBillMasterData getSalesBillMasterData() {
        salesBillMasterData.setBillState(0);
        if (salesBillMasterData.getBillID() == null || salesBillMasterData.getBillID() == 0)
            salesBillMasterData.setBillID(-1L);
        salesBillMasterData.setBillCode(BillCode.getText().toString());
        salesBillMasterData.setContactPhone(ContactPhone.getText().toString().trim());
        salesBillMasterData.setBillTo(BillTo.getText().toString().trim());
        salesBillMasterData.setShopID(SuperClient.getCurrentOperator().getShopID());
        salesBillMasterData.setBillDate(BillDate.getText().toString());
        salesBillMasterData.setOpID(SuperClient.getCurrentOperator().getOpID());
        salesBillMasterData.setAmount(123123.123);
        return salesBillMasterData;
    }

    public void setSalesBillMasterData(SalesBillMasterData salesBillMasterData) {
        if (salesBillMasterData != null) {

            this.salesBillMasterData = salesBillMasterData;
            BillCode.setText(salesBillMasterData.getBillCode());
            BillDate.setText(salesBillMasterData.getBillDate());
            if (salesBillMasterData.getTraderID() != null) {

                QueryBuilder<Trader> traderQueryBuilder = traderDao.queryBuilder();
                traderQueryBuilder.where(TraderDao.Properties.TraderID.eq(salesBillMasterData.getTraderID()));
                Trader trader = traderQueryBuilder.unique();
                if (trader != null) {
                    TraderName.setText(trader.getTraderName());
                    ContactPhone.setText(trader.getTel1());
                    BillTo.setText(trader.getShipTo());
                } else {
                    TraderName.setText("");
                    ContactPhone.setText("");
                    BillTo.setText("");
                }
            } else {
                TraderName.setText("");
                ContactPhone.setText("");
                BillTo.setText("");
            }
            if (salesBillMasterData.getPayMethodID() != null) {
                QueryBuilder<PayMethod> payMethodQueryBuilder = payMethodDao.queryBuilder();
                payMethodQueryBuilder.where(PayMethodDao.Properties.PayMethodID.eq(salesBillMasterData.getPayMethodID()));
                PayMethod payMethod = payMethodQueryBuilder.unique();
                if (payMethod != null) {
                    PayMethod.setText(payMethod.getPaymethodName());
                } else {
                    PayMethod.setText("");
                }
            } else {
                PayMethod.setText("");
            }
            if (salesBillMasterData.getPayAmt() != null)
                PayAmt.setText(salesBillMasterData.getPayAmt().toString());
            if (salesBillMasterData.getAccountID() != null) {
                QueryBuilder<Account> accountQueryBuilder = accountDao.queryBuilder();
                accountQueryBuilder.where(AccountDao.Properties.AccountID.eq(salesBillMasterData.getAccountID()));
                Account account = accountQueryBuilder.unique();
                if (account != null) {
                    Account.setText(account.getAccountName());
                } else {
                    Account.setText("");
                }
            } else {
                Account.setText("");
            }
            if (salesBillMasterData.getNoteTypeID() != null) {
                QueryBuilder<AMKind> queryBuilder = amKindDao.queryBuilder();
                queryBuilder.where(AMKindDao.Properties.Kind.eq(14), AMKindDao.Properties.ID.eq(salesBillMasterData.getNoteTypeID()));
                AMKind amKind = queryBuilder.unique();
                if (amKind != null) {
                    NoteType.setText(amKind.getName());
                } else {
                    NoteType.setText("");
                }
            } else {
                NoteType.setText("");
            }
            if (salesBillMasterData.getEmpID() != null) {
                QueryBuilder<Employee> employeeQueryBuilder = employeeDao.queryBuilder();
                employeeQueryBuilder.where(EmployeeDao.Properties.EmpID.eq(salesBillMasterData.getEmpID()));
                Employee employee = employeeQueryBuilder.unique();
                if (employee != null) {
                    EmpName.setText(employee.getEmpName());
                } else {
                    EmpName.setText("");
                }
            } else {
                EmpName.setText("");
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
                salesBillMasterData.setTraderID(trader.getTraderID());
                ContactPhone.setText(trader.getTel1());
                BillTo.setText(trader.getShipTo());
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Employee employee = (Employee) data.getSerializableExtra("Data");
                EmpName.setText(employee.getEmpName());
                salesBillMasterData.setEmpID(employee.getEmpID());
            }
        }
    }

}
