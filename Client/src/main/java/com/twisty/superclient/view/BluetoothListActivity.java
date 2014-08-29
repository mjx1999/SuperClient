package com.twisty.superclient.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.SalesBillDetail1Data;
import com.twisty.superclient.bean.SalesBillMasterData;
import com.twisty.superclient.bean.SalesBillResp;
import com.twisty.superclient.bean.SalesOrderResp;
import com.twisty.superclient.bean.StockCheckResp;
import com.twisty.superclient.bean.SysParam;
import com.twisty.superclient.bean.SysParamDao;
import com.twisty.superclient.bean.TraderDao;
import com.twisty.superclient.bean.TransferResp;
import com.twisty.superclient.global.GlobalConstant;
import com.twisty.superclient.global.SuperClient;
import com.twisty.superclient.printer.BtSPP;
import com.twisty.superclient.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.greenrobot.dao.query.QueryBuilder;

public class BluetoothListActivity extends BaseActivity {
    public static BluetoothAdapter myBluetoothAdapter;
    private static String ErrorMessage;
    private SimpleAdapter m_adapter;
    private DaoSession session;
    private TraderDao traderDao;
    private SysParamDao sysParamDao;
    private String companyName = "";
    private SalesBillResp salesBillResp;
    private SalesOrderResp salesOrderResp;
    private TransferResp transferResp;
    private StockCheckResp stockCheckResp;

    private int billType;

    public static int zp_realtime_status(int timeout) {
        byte data[] = new byte[10];
        data[0] = 0x1f;
        data[1] = 0x00;
        data[2] = 0x06;
        data[3] = 0x00;
        data[4] = 0x07;
        data[5] = 0x14;
        data[6] = 0x18;
        data[7] = 0x23;
        data[8] = 0x25;
        data[9] = 0x32;
        BtSPP.SPPWrite(data, 10);
        byte readata[] = new byte[1];
        if (!BtSPP.SPPReadTimeout(readata, 1, timeout)) {
            return -1;
        }
        int status = readata[0];
        if ((status & 1) != 0) ErrorMessage = "打印机纸仓盖开";
        if ((status & 2) != 0) ErrorMessage = "打印机缺纸";
        if ((status & 4) != 0) ErrorMessage = "打印头过热";
        return status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_list);
        billType = getIntent().getIntExtra("BillType", -1);
        switch (billType) {
            case GlobalConstant.BILL_TYPE_SALES_BILL:
                salesBillResp = (SalesBillResp) getIntent().getSerializableExtra("com.twisty.superclient.Data");
                break;
            case GlobalConstant.BILL_TYPE_SALES_ORDER:

                break;
            case GlobalConstant.BILL_TYPE_STOCK_CHECK:

                break;
            case GlobalConstant.BILL_TYPE_TRANSFER:

                break;
        }
        session = SuperClient.getDaoSession(this);
        traderDao = session.getTraderDao();
        sysParamDao = session.getSysParamDao();

        QueryBuilder<SysParam> sysParamQueryBuilder = sysParamDao.queryBuilder();
        sysParamQueryBuilder.where(SysParamDao.Properties.ParamID.eq(202));
        SysParam sysParam = sysParamQueryBuilder.unique();
        if (sysParam != null) {
            companyName = sysParam.getDataValue();
        }


        final List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        if ((myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
            Toast.makeText(this, "没有找到蓝牙适配器", Toast.LENGTH_LONG).show();
        }

        if (!myBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 2);
        }

        Set<BluetoothDevice> pairedDevices = myBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() <= 0) {
            CommonUtil.showToastInfo(this, "没有已配对的蓝牙打印机", null);
        }
        for (BluetoothDevice device : pairedDevices) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("DeviceName", device.getName());
            map.put("BDAddress", device.getAddress());
            list.add(map);
        }


        final ListView listView = (ListView) findViewById(R.id.btlistview);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView) parent).setItemChecked(position, true);
            }
        });
        m_adapter = new SimpleAdapter(this, list,
                R.layout.bluetooth_device,
                new String[]{"DeviceName", "BDAddress"},
                new int[]{R.id.text1, R.id.text2}
        );
        listView.setAdapter(m_adapter);

        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 打印
                String bdAddress = ((Map<String, String>) m_adapter.getItem(listView.getCheckedItemPosition())).get("BDAddress");
                BluetoothDevice myDevice = myBluetoothAdapter.getRemoteDevice(bdAddress);
                log.i(myDevice);
                if (!BtSPP.OpenPrinter(bdAddress)) {
                    Toast.makeText(BluetoothListActivity.this, BtSPP.ErrorMessage, Toast.LENGTH_LONG).show();
                } else {
                    try {

                        if (billType != -1) {
                            switch (billType) {
                                case GlobalConstant.BILL_TYPE_SALES_BILL:
                                    SalesBillMasterData salesBillMasterData = salesBillResp.getMasterData();
                                    ArrayList<SalesBillDetail1Data> salesBillDetail1Datas = salesBillResp.getDetail1Data();


                                    BtSPP.SPPWrite(new byte[]{0x1B, 0x40});        //打印机复位
                                    BtSPP.SPPWrite("\n".getBytes("GBK"));
                                    BtSPP.SPPWrite(new byte[]{0x1B, 0x33, 0x02});    //设置行间距为0
                                    BtSPP.SPPWrite(new byte[]{0x1B, 0x61, 0x01});//居中
                                    BtSPP.SPPWrite((companyName + "\n").getBytes("GBK"));//居中
                                    BtSPP.SPPWrite(new byte[]{0x1B, 0x61, 0x00});//左对齐
                                    BtSPP.SPPWrite(("日期:" + salesBillMasterData.getBillDate() + "\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("单号:" + salesBillMasterData.getBillCode() + "\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("客户:" + salesBillMasterData.getTraderName() + "\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("开单人:" + salesBillMasterData.getOpName() + "\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("=======================================\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("序号 品名     规格 \n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("       数量   单位     单价     金额\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("=======================================\n").getBytes("GBK"));
                                    for (int i = 0; i < salesBillDetail1Datas.size(); i++) {
                                        SalesBillDetail1Data detail1Data = salesBillDetail1Datas.get(i);
                                        BtSPP.SPPWrite(((i + 1) + "").getBytes("GBK"));
                                        BtSPP.SPPWrite(("   " + detail1Data.getGoodsName() + "").getBytes("GBK"));
                                        BtSPP.SPPWrite(("   " + detail1Data.getSpecs() + "\n").getBytes("GBK"));
                                        BtSPP.SPPWrite(("       " + detail1Data.getQuantity() + "   ").getBytes("GBK"));
                                        BtSPP.SPPWrite((detail1Data.getUnitName() + "  ").getBytes("GBK"));
                                        BtSPP.SPPWrite((detail1Data.getUnitPrice() + "  ").getBytes("GBK"));
                                        BtSPP.SPPWrite((detail1Data.getAmount() + "\n").getBytes("GBK"));
                                    }
                                    BtSPP.SPPWrite(("        ===============================\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("                 合计    " + salesBillMasterData.getAmount() + "\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("             开单收款    " + salesBillMasterData.getPayAmt() + "\n").getBytes("GBK"));
                                    BtSPP.SPPWrite(("               未收款    " + (salesBillMasterData.getAmount() - salesBillMasterData.getPayAmt()) + "\n").getBytes("GBK"));
                                    break;
                                case GlobalConstant.BILL_TYPE_SALES_ORDER:

                                    break;
                                case GlobalConstant.BILL_TYPE_STOCK_CHECK:

                                    break;
                                case GlobalConstant.BILL_TYPE_TRANSFER:

                                    break;
                            }
                        }

//                        BtSPP.SPPWrite(new byte[]{0x1B, 0x44, 0x02, 0x12, (byte) 0xff});        //设置12点水平制表位，因为最大为8，所有后面4个无效


//                            BtSPP.SPPWrite(new byte[]{0x09});        //选择第一个制表位打印
//                            BtSPP.SPPWrite((goodses.get(i).getGoodsName()).getBytes("GBK"));
//                            BtSPP.SPPWrite(new byte[]{0x09});        //选择第一个制表位打印
//                            BtSPP.SPPWrite((goodses.get(i).getSpecs()+"\n").getBytes("GBK"));
//                        BtSPP.SPPFlush();
//                        BtSPP.SPPClose();
                        BtSPP.SPPWrite(String.format("\n\n").getBytes("GBK"));
                        if (zp_realtime_status(5000) > 0)
                            CommonUtil.showToastError(BluetoothListActivity.this, ErrorMessage, null);


                    } catch (Exception e) {
                        CommonUtil.showToastError(BluetoothListActivity.this, "打印失败", null);
                    } finally {
                        BtSPP.SPPClose();
                    }
                }
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
