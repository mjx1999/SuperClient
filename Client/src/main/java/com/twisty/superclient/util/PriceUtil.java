package com.twisty.superclient.util;

import android.content.Context;

import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderPrice;
import com.twisty.superclient.bean.TraderPriceDao;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by twisty on 14-8-28.
 */
public class PriceUtil {
    private TraderPriceDao traderPriceDao;
    private UnitDao unitDao;

    public PriceUtil(Context context) {
        traderPriceDao = SuperClient.getDaoSession(context).getTraderPriceDao();
        unitDao = SuperClient.getDaoSession(context).getUnitDao();
    }

    public double getUnitPrice(Trader trader, Long unitID, Goods goods) {
        if (trader != null && unitID != null) {
            QueryBuilder<TraderPrice> traderPriceQueryBuilder = traderPriceDao.queryBuilder();
            traderPriceQueryBuilder.where(TraderPriceDao.Properties.TraderID.eq(trader.getTraderID()), TraderPriceDao.Properties.GoodsID.eq(goods.getGoodsID()));
            List<TraderPrice> traderPrices = traderPriceQueryBuilder.list();
            if (traderPrices != null && traderPrices.size() > 0) {
                for (TraderPrice traderPrice : traderPrices) {

                }
//                return traderPrice.getPrice();
            } else {
                int level = trader.getLev();
                QueryBuilder<Unit> unitQueryBuilder = unitDao.queryBuilder();
                unitQueryBuilder.where(UnitDao.Properties.UnitID.eq(unitID));
                Unit unit = unitQueryBuilder.unique();

                if (level != 0) {
                    switch (level) {
                        case 1:
                            return unit.getLPrice1();
                        case 2:
                            return unit.getLPrice2();
                        case 3:
                            return unit.getLPrice3();
                        case 4:
                            return unit.getLPrice4();
                        case 5:
                            return unit.getLPrice5();
                        default:
                            return 0;

                    }
                } else {
                    return unit.getSPrice();
                }
            }
        }
        return 0;
    }
}
