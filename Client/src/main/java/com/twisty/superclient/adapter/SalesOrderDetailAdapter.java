package com.twisty.superclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.SalesOrderDetail1Data;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by twisty on 14-8-16.
 */
public class SalesOrderDetailAdapter extends BaseAdapter {
    private List<SalesOrderDetail1Data> data;
    private LayoutInflater inflater;
    private StoreDao storeDao;
    private GoodsDao goodsDao;
    private UnitDao unitDao;

    public SalesOrderDetailAdapter(Context context, ArrayList<SalesOrderDetail1Data> data) {
        this.data = data;
        DaoSession session = SuperClient.getDaoSession(context);
        storeDao = session.getStoreDao();
        goodsDao = session.getGoodsDao();
        unitDao = session.getUnitDao();
        inflater = LayoutInflater.from(context);
    }

    public List<SalesOrderDetail1Data> getData() {
        return data;
    }

    public void setData(List<SalesOrderDetail1Data> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data != null) return data.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (data != null) return data.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getBillID()==null?-1:data.get(position).getBillID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        SalesOrderDetail1Data salesBillDetail1Data = data.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sales_order_detail_item, null);
//            viewHolder.StoreCode = (TextView) convertView.findViewById(R.id.StoreCode);
//            viewHolder.StoreName = (TextView) convertView.findViewById(R.id.StoreName);
            viewHolder.GoodsCode = (TextView) convertView.findViewById(R.id.GoodsCode);
            viewHolder.GoodsName = (TextView) convertView.findViewById(R.id.GoodsName);
            viewHolder.Spec = (TextView) convertView.findViewById(R.id.Spec);
            viewHolder.Unit = (TextView) convertView.findViewById(R.id.Unit);
            viewHolder.Quantity = (TextView) convertView.findViewById(R.id.Quantity);
            viewHolder.OrigTaxPrice = (TextView) convertView.findViewById(R.id.OrigTaxPrice);
            viewHolder.Disc = (TextView) convertView.findViewById(R.id.Disc);
            viewHolder.TaxPrice = (TextView) convertView.findViewById(R.id.TaxPrice);
            viewHolder.UnitPrice = (TextView) convertView.findViewById(R.id.UnitPrice);
            viewHolder.TaxRate = (TextView) convertView.findViewById(R.id.TaxRate);
            viewHolder.TaxAmt = (TextView) convertView.findViewById(R.id.TaxAmt);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        if(salesBillDetail1Data.getStoreID()!=null){
//            QueryBuilder<Store> storeQueryBuilder = storeDao.queryBuilder();
//            storeQueryBuilder.where(StoreDao.Properties.StoreID.eq(salesBillDetail1Data.getStoreID()));
//            Store store = storeQueryBuilder.unique();
//            if(store!=null){
//                viewHolder.StoreCode.setText(store.getStoreCode());
//                viewHolder.StoreName.setText(store.getStoreName());
//            }
//        }
        if(salesBillDetail1Data.getGoodsID()!=null){
            QueryBuilder<Goods> goodsQueryBuilder = goodsDao.queryBuilder();
            goodsQueryBuilder.where(GoodsDao.Properties.GoodsID.eq(salesBillDetail1Data.getGoodsID()));
            Goods goods = goodsQueryBuilder.unique();
            if(goods!=null){
                viewHolder.GoodsCode.setText(goods.getGoodsCode());
                viewHolder.GoodsName.setText(goods.getGoodsName());
                viewHolder.Spec.setText(goods.getSpecs());
            }
        }

        if(salesBillDetail1Data.getUnitID()!=null){
            QueryBuilder<Unit> unitQueryBuilder = unitDao.queryBuilder();
            unitQueryBuilder.where(UnitDao.Properties.UnitID.eq(salesBillDetail1Data.getUnitID()));
            Unit unit = unitQueryBuilder.unique();
            if(unit!=null){
                viewHolder.Unit.setText(unit.getUnitName());
            }
        }

        viewHolder.Quantity.setText(salesBillDetail1Data.getQuantity()+"");
        viewHolder.OrigTaxPrice.setText(salesBillDetail1Data.getOrigPrice()+"");
        viewHolder.Disc.setText(salesBillDetail1Data.getDisc()+"");
        viewHolder.TaxRate.setText(salesBillDetail1Data.getTaxRate()+"");
        viewHolder.UnitPrice.setText(salesBillDetail1Data.getUnitPrice()+"");
        viewHolder.TaxRate.setText(salesBillDetail1Data.getTaxRate()+"");
        viewHolder.TaxAmt.setText(salesBillDetail1Data.getTaxAmt()+"");
        viewHolder.Amount.setText(salesBillDetail1Data.getAmount()+"");

        return convertView;
    }

    class ViewHolder {
        TextView /*StoreCode, StoreName,*/
                GoodsCode, GoodsName,
                Spec, Unit,

                Quantity,
                OrigTaxPrice, Disc,
                TaxPrice, UnitPrice,
                TaxRate, TaxAmt, Amount;
    }
}
