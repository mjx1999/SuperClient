package com.twisty.superclient.bean;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.Area;
import com.twisty.superclient.bean.Department;
import com.twisty.superclient.bean.Employee;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.Trader;
import com.twisty.superclient.bean.TradeType;
import com.twisty.superclient.bean.IoType;
import com.twisty.superclient.bean.Store;
import com.twisty.superclient.bean.GDType;
import com.twisty.superclient.bean.Goods;
import com.twisty.superclient.bean.Unit;
import com.twisty.superclient.bean.TraderPrice;
import com.twisty.superclient.bean.GoodsPicture;
import com.twisty.superclient.bean.OnHand;
import com.twisty.superclient.bean.Account;
import com.twisty.superclient.bean.PayMethod;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.MasterData;
import com.twisty.superclient.bean.Detail1Data;

import com.twisty.superclient.bean.AccsetDao;
import com.twisty.superclient.bean.AreaDao;
import com.twisty.superclient.bean.DepartmentDao;
import com.twisty.superclient.bean.EmployeeDao;
import com.twisty.superclient.bean.OperatorDao;
import com.twisty.superclient.bean.TraderDao;
import com.twisty.superclient.bean.TradeTypeDao;
import com.twisty.superclient.bean.IoTypeDao;
import com.twisty.superclient.bean.StoreDao;
import com.twisty.superclient.bean.GDTypeDao;
import com.twisty.superclient.bean.GoodsDao;
import com.twisty.superclient.bean.UnitDao;
import com.twisty.superclient.bean.TraderPriceDao;
import com.twisty.superclient.bean.GoodsPictureDao;
import com.twisty.superclient.bean.OnHandDao;
import com.twisty.superclient.bean.AccountDao;
import com.twisty.superclient.bean.PayMethodDao;
import com.twisty.superclient.bean.AMKindDao;
import com.twisty.superclient.bean.MasterDataDao;
import com.twisty.superclient.bean.Detail1DataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig accsetDaoConfig;
    private final DaoConfig areaDaoConfig;
    private final DaoConfig departmentDaoConfig;
    private final DaoConfig employeeDaoConfig;
    private final DaoConfig operatorDaoConfig;
    private final DaoConfig traderDaoConfig;
    private final DaoConfig tradeTypeDaoConfig;
    private final DaoConfig ioTypeDaoConfig;
    private final DaoConfig storeDaoConfig;
    private final DaoConfig gDTypeDaoConfig;
    private final DaoConfig goodsDaoConfig;
    private final DaoConfig unitDaoConfig;
    private final DaoConfig traderPriceDaoConfig;
    private final DaoConfig goodsPictureDaoConfig;
    private final DaoConfig onHandDaoConfig;
    private final DaoConfig accountDaoConfig;
    private final DaoConfig payMethodDaoConfig;
    private final DaoConfig aMKindDaoConfig;
    private final DaoConfig masterDataDaoConfig;
    private final DaoConfig detail1DataDaoConfig;

    private final AccsetDao accsetDao;
    private final AreaDao areaDao;
    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;
    private final OperatorDao operatorDao;
    private final TraderDao traderDao;
    private final TradeTypeDao tradeTypeDao;
    private final IoTypeDao ioTypeDao;
    private final StoreDao storeDao;
    private final GDTypeDao gDTypeDao;
    private final GoodsDao goodsDao;
    private final UnitDao unitDao;
    private final TraderPriceDao traderPriceDao;
    private final GoodsPictureDao goodsPictureDao;
    private final OnHandDao onHandDao;
    private final AccountDao accountDao;
    private final PayMethodDao payMethodDao;
    private final AMKindDao aMKindDao;
    private final MasterDataDao masterDataDao;
    private final Detail1DataDao detail1DataDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        accsetDaoConfig = daoConfigMap.get(AccsetDao.class).clone();
        accsetDaoConfig.initIdentityScope(type);

        areaDaoConfig = daoConfigMap.get(AreaDao.class).clone();
        areaDaoConfig.initIdentityScope(type);

        departmentDaoConfig = daoConfigMap.get(DepartmentDao.class).clone();
        departmentDaoConfig.initIdentityScope(type);

        employeeDaoConfig = daoConfigMap.get(EmployeeDao.class).clone();
        employeeDaoConfig.initIdentityScope(type);

        operatorDaoConfig = daoConfigMap.get(OperatorDao.class).clone();
        operatorDaoConfig.initIdentityScope(type);

        traderDaoConfig = daoConfigMap.get(TraderDao.class).clone();
        traderDaoConfig.initIdentityScope(type);

        tradeTypeDaoConfig = daoConfigMap.get(TradeTypeDao.class).clone();
        tradeTypeDaoConfig.initIdentityScope(type);

        ioTypeDaoConfig = daoConfigMap.get(IoTypeDao.class).clone();
        ioTypeDaoConfig.initIdentityScope(type);

        storeDaoConfig = daoConfigMap.get(StoreDao.class).clone();
        storeDaoConfig.initIdentityScope(type);

        gDTypeDaoConfig = daoConfigMap.get(GDTypeDao.class).clone();
        gDTypeDaoConfig.initIdentityScope(type);

        goodsDaoConfig = daoConfigMap.get(GoodsDao.class).clone();
        goodsDaoConfig.initIdentityScope(type);

        unitDaoConfig = daoConfigMap.get(UnitDao.class).clone();
        unitDaoConfig.initIdentityScope(type);

        traderPriceDaoConfig = daoConfigMap.get(TraderPriceDao.class).clone();
        traderPriceDaoConfig.initIdentityScope(type);

        goodsPictureDaoConfig = daoConfigMap.get(GoodsPictureDao.class).clone();
        goodsPictureDaoConfig.initIdentityScope(type);

        onHandDaoConfig = daoConfigMap.get(OnHandDao.class).clone();
        onHandDaoConfig.initIdentityScope(type);

        accountDaoConfig = daoConfigMap.get(AccountDao.class).clone();
        accountDaoConfig.initIdentityScope(type);

        payMethodDaoConfig = daoConfigMap.get(PayMethodDao.class).clone();
        payMethodDaoConfig.initIdentityScope(type);

        aMKindDaoConfig = daoConfigMap.get(AMKindDao.class).clone();
        aMKindDaoConfig.initIdentityScope(type);

        masterDataDaoConfig = daoConfigMap.get(MasterDataDao.class).clone();
        masterDataDaoConfig.initIdentityScope(type);

        detail1DataDaoConfig = daoConfigMap.get(Detail1DataDao.class).clone();
        detail1DataDaoConfig.initIdentityScope(type);

        accsetDao = new AccsetDao(accsetDaoConfig, this);
        areaDao = new AreaDao(areaDaoConfig, this);
        departmentDao = new DepartmentDao(departmentDaoConfig, this);
        employeeDao = new EmployeeDao(employeeDaoConfig, this);
        operatorDao = new OperatorDao(operatorDaoConfig, this);
        traderDao = new TraderDao(traderDaoConfig, this);
        tradeTypeDao = new TradeTypeDao(tradeTypeDaoConfig, this);
        ioTypeDao = new IoTypeDao(ioTypeDaoConfig, this);
        storeDao = new StoreDao(storeDaoConfig, this);
        gDTypeDao = new GDTypeDao(gDTypeDaoConfig, this);
        goodsDao = new GoodsDao(goodsDaoConfig, this);
        unitDao = new UnitDao(unitDaoConfig, this);
        traderPriceDao = new TraderPriceDao(traderPriceDaoConfig, this);
        goodsPictureDao = new GoodsPictureDao(goodsPictureDaoConfig, this);
        onHandDao = new OnHandDao(onHandDaoConfig, this);
        accountDao = new AccountDao(accountDaoConfig, this);
        payMethodDao = new PayMethodDao(payMethodDaoConfig, this);
        aMKindDao = new AMKindDao(aMKindDaoConfig, this);
        masterDataDao = new MasterDataDao(masterDataDaoConfig, this);
        detail1DataDao = new Detail1DataDao(detail1DataDaoConfig, this);

        registerDao(Accset.class, accsetDao);
        registerDao(Area.class, areaDao);
        registerDao(Department.class, departmentDao);
        registerDao(Employee.class, employeeDao);
        registerDao(Operator.class, operatorDao);
        registerDao(Trader.class, traderDao);
        registerDao(TradeType.class, tradeTypeDao);
        registerDao(IoType.class, ioTypeDao);
        registerDao(Store.class, storeDao);
        registerDao(GDType.class, gDTypeDao);
        registerDao(Goods.class, goodsDao);
        registerDao(Unit.class, unitDao);
        registerDao(TraderPrice.class, traderPriceDao);
        registerDao(GoodsPicture.class, goodsPictureDao);
        registerDao(OnHand.class, onHandDao);
        registerDao(Account.class, accountDao);
        registerDao(PayMethod.class, payMethodDao);
        registerDao(AMKind.class, aMKindDao);
        registerDao(MasterData.class, masterDataDao);
        registerDao(Detail1Data.class, detail1DataDao);
    }
    
    public void clear() {
        accsetDaoConfig.getIdentityScope().clear();
        areaDaoConfig.getIdentityScope().clear();
        departmentDaoConfig.getIdentityScope().clear();
        employeeDaoConfig.getIdentityScope().clear();
        operatorDaoConfig.getIdentityScope().clear();
        traderDaoConfig.getIdentityScope().clear();
        tradeTypeDaoConfig.getIdentityScope().clear();
        ioTypeDaoConfig.getIdentityScope().clear();
        storeDaoConfig.getIdentityScope().clear();
        gDTypeDaoConfig.getIdentityScope().clear();
        goodsDaoConfig.getIdentityScope().clear();
        unitDaoConfig.getIdentityScope().clear();
        traderPriceDaoConfig.getIdentityScope().clear();
        goodsPictureDaoConfig.getIdentityScope().clear();
        onHandDaoConfig.getIdentityScope().clear();
        accountDaoConfig.getIdentityScope().clear();
        payMethodDaoConfig.getIdentityScope().clear();
        aMKindDaoConfig.getIdentityScope().clear();
        masterDataDaoConfig.getIdentityScope().clear();
        detail1DataDaoConfig.getIdentityScope().clear();
    }

    public AccsetDao getAccsetDao() {
        return accsetDao;
    }

    public AreaDao getAreaDao() {
        return areaDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public OperatorDao getOperatorDao() {
        return operatorDao;
    }

    public TraderDao getTraderDao() {
        return traderDao;
    }

    public TradeTypeDao getTradeTypeDao() {
        return tradeTypeDao;
    }

    public IoTypeDao getIoTypeDao() {
        return ioTypeDao;
    }

    public StoreDao getStoreDao() {
        return storeDao;
    }

    public GDTypeDao getGDTypeDao() {
        return gDTypeDao;
    }

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public UnitDao getUnitDao() {
        return unitDao;
    }

    public TraderPriceDao getTraderPriceDao() {
        return traderPriceDao;
    }

    public GoodsPictureDao getGoodsPictureDao() {
        return goodsPictureDao;
    }

    public OnHandDao getOnHandDao() {
        return onHandDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public PayMethodDao getPayMethodDao() {
        return payMethodDao;
    }

    public AMKindDao getAMKindDao() {
        return aMKindDao;
    }

    public MasterDataDao getMasterDataDao() {
        return masterDataDao;
    }

    public Detail1DataDao getDetail1DataDao() {
        return detail1DataDao;
    }

}
