package com.twisty.superclient.util;

import android.content.Context;

import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TraderPrice;
import com.twisty.superclient.bean.TraderPriceDao;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.global.SuperClient;

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

    public Double getPrice(Trader trader, Long unitID) {
        if (trader != null && unitID != null) {
            QueryBuilder<TraderPrice> traderPriceQueryBuilder = traderPriceDao.queryBuilder();
            traderPriceQueryBuilder.where(TraderPriceDao.Properties.TraderID.eq(trader.getTraderID()), TraderPriceDao.Properties.UnitID.eq(unitID));
            TraderPrice traderPrice = traderPriceQueryBuilder.unique();
            if (traderPrice != null) {
                return traderPrice.getPrice();
            } else {
                Integer level = trader.getLev();
                QueryBuilder<Unit> unitQueryBuilder = unitDao.queryBuilder();
                unitQueryBuilder.where(UnitDao.Properties.UnitID.eq(unitID));
                Unit unit = unitQueryBuilder.unique();

                if (level != null && level != 0) {
                    switch (level) {
                        case 1:
                            if (unit.getLPrice1() == null || unit.getLPrice1().compareTo(0D) == 0)
                                return unit.getSPrice();
                            return unit.getLPrice1();
                        case 2:
                            if (unit.getLPrice2() == null || unit.getLPrice2().compareTo(0D) == 0)
                                return unit.getSPrice();

                            return unit.getLPrice2();
                        case 3:
                            if (unit.getLPrice3() == null || unit.getLPrice3().compareTo(0D) == 0)
                                return unit.getSPrice();
                            return unit.getLPrice3();
                        case 4:
                            if (unit.getLPrice4() == null || unit.getLPrice4().compareTo(0D) == 0)
                                return unit.getSPrice();
                            return unit.getLPrice4();
                        case 5:
                            if (unit.getLPrice5() == null || unit.getLPrice5().compareTo(0D) == 0)
                                return unit.getSPrice();
                            return unit.getLPrice5();
                    }
                } else {
                    return unit.getSPrice();
                }
            }
        }
        return (double) 0;
    }

//    public double getSPrice(Unit unit){
//        if(unit!=null&&unit.getUnitID()!=null){
//            QueryBuilder<Unit> unitQueryBuilder = unitDao.queryBuilder();
//            unitQueryBuilder.where(UnitDao.Properties.UnitID.eq(unit.getUnitID()));
//            Unit unit = unitQueryBuilder.unique();
//            return unit.getSPrice();
//        }
//        return 0;
//    }
}
