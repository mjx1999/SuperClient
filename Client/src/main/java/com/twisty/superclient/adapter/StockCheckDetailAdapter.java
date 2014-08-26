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
import com.twisty.superclient.bean.StockCheckDetail1Data;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;

import java.util.ArrayList;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by twisty on 14-8-26.
 */
public class StockCheckDetailAdapter extends BaseAdapter {

    private ArrayList<StockCheckDetail1Data> data;
    private LayoutInflater inflater;
    private StoreDao storeDao;
    private GoodsDao goodsDao;
    private UnitDao unitDao;

    public StockCheckDetailAdapter(Context context, ArrayList<StockCheckDetail1Data> data) {
        this.data = data;
        DaoSession session = SuperClient.getDaoSession(context);
        storeDao = session.getStoreDao();
        goodsDao = session.getGoodsDao();
        unitDao = session.getUnitDao();
        inflater = LayoutInflater.from(context);
    }

    public ArrayList<StockCheckDetail1Data> getData() {
        return data;
    }

    public void setData(ArrayList<StockCheckDetail1Data> data) {
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
        StockCheckDetail1Data stockCheckDetail1Data = data.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.stock_check_detail_item, null);
            viewHolder.GoodsCode = (TextView) convertView.findViewById(R.id.GoodsCode);
            viewHolder.GoodsName = (TextView) convertView.findViewById(R.id.GoodsName);
            viewHolder.Spec = (TextView) convertView.findViewById(R.id.Spec);
            viewHolder.Unit = (TextView) convertView.findViewById(R.id.Unit);
            viewHolder.Quantity = (TextView) convertView.findViewById(R.id.Quantity);
            viewHolder.UnitPrice = (TextView) convertView.findViewById(R.id.UnitPrice);
            viewHolder.Amount = (TextView) convertView.findViewById(R.id.Amount);
            viewHolder.AccQty = (TextView) convertView.findViewById(R.id.AccQty);
            viewHolder.UnitRealQty = (TextView) convertView.findViewById(R.id.UnitRealQty);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(stockCheckDetail1Data.getGoodsID()!=null){
            QueryBuilder<Goods> goodsQueryBuilder = goodsDao.queryBuilder();
            goodsQueryBuilder.where(GoodsDao.Properties.GoodsID.eq(stockCheckDetail1Data.getGoodsID()));
            Goods goods = goodsQueryBuilder.unique();
            if(goods!=null){
                viewHolder.GoodsCode.setText(goods.getGoodsCode());
                viewHolder.GoodsName.setText(goods.getGoodsName());
                viewHolder.Spec.setText(goods.getSpecs());
            }
        }

        if(stockCheckDetail1Data.getUnitID()!=null){
            QueryBuilder<Unit> unitQueryBuilder = unitDao.queryBuilder();
            unitQueryBuilder.where(UnitDao.Properties.UnitID.eq(stockCheckDetail1Data.getUnitID()));
            Unit unit = unitQueryBuilder.unique();
            if(unit!=null){
                viewHolder.Unit.setText(unit.getUnitName());
            }
        }

        viewHolder.UnitPrice.setText(stockCheckDetail1Data.getUnitPrice()+"");
        viewHolder.Amount.setText(stockCheckDetail1Data.getAmount()+"");
        viewHolder.AccQty.setText(stockCheckDetail1Data.getAccQty()+"");
        viewHolder.UnitRealQty.setText(stockCheckDetail1Data.getUnitRealQty()+"");
        viewHolder.Quantity.setText((stockCheckDetail1Data.getUnitRealQty()-stockCheckDetail1Data.getAccQty())+"");
        return convertView;
    }

    class ViewHolder {
        TextView
                GoodsCode, GoodsName,
                Spec, Unit,Quantity,
                AccQty,UnitRealQty,
                UnitPrice,Amount;
    }
}
