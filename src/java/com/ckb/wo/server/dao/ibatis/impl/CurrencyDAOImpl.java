package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyExample;
import com.ckb.wo.server.dao.CurrencyDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CurrencyDAOImpl extends SqlMapClientDaoSupport implements CurrencyDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    public CurrencyDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int countCurrencyByExample(CurrencyExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tcurrency.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int deleteCurrencyByExample(CurrencyExample example) {
        int rows = getSqlMapClientTemplate().delete("tcurrency.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param tcurrencyPk
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int deleteCurrencyByPrimaryKey(Long tcurrencyPk) {
        Currency key = new Currency();
        key.setTcurrencyPk(tcurrencyPk);
        int rows = getSqlMapClientTemplate().delete("tcurrency.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public Long insertCurrency(Currency record) {
        Object newKey = getSqlMapClientTemplate().insert("tcurrency.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public Long insertCurrencySelective(Currency record) {
        Object newKey = getSqlMapClientTemplate().insert("tcurrency.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Currency> selectCurrencyByExample(CurrencyExample example) {
        List<Currency> list = getSqlMapClientTemplate().queryForList("tcurrency.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param tcurrencyPk
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public Currency selectCurrencyByPrimaryKey(Long tcurrencyPk) {
        Currency key = new Currency();
        key.setTcurrencyPk(tcurrencyPk);
        Currency record = (Currency) getSqlMapClientTemplate().queryForObject("tcurrency.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int updateCurrencyByExampleSelective(Currency record, CurrencyExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tcurrency.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int updateCurrencyByExample(Currency record, CurrencyExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tcurrency.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int updateCurrencyByPrimaryKeySelective(Currency record) {
        int rows = getSqlMapClientTemplate().update("tcurrency.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    @Override
    public int updateCurrencyByPrimaryKey(Currency record) {
        int rows = getSqlMapClientTemplate().update("tcurrency.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<Currency> selectCurrencyAll() {
        return getSqlMapClientTemplate().queryForList("tcurrency.selectAll");
    }

    /**
     * This class was generated by Apache iBATIS ibator. This class corresponds
     * to the database table tcurrency
     *
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    private static class UpdateByExampleParms extends CurrencyExample {
        private Object record;
        public UpdateByExampleParms(Object record, CurrencyExample example) {
            super(example);
            this.record = record;
        }
        public Object getRecord() {
            return record;
        }
    }
}
