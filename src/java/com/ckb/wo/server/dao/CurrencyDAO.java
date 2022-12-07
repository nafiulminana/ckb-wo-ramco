package com.ckb.wo.server.dao;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyExample;
import java.util.List;

public interface CurrencyDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int countCurrencyByExample(CurrencyExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int deleteCurrencyByExample(CurrencyExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param tcurrencyPk
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int deleteCurrencyByPrimaryKey(Long tcurrencyPk);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    Long insertCurrency(Currency record);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    Long insertCurrencySelective(Currency record);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    List<Currency> selectCurrencyByExample(CurrencyExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param tcurrencyPk
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    Currency selectCurrencyByPrimaryKey(Long tcurrencyPk);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int updateCurrencyByExampleSelective(Currency record, CurrencyExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int updateCurrencyByExample(Currency record, CurrencyExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int updateCurrencyByPrimaryKeySelective(Currency record);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tcurrency
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Jul 07 13:29:19 SGT 2010
     */
    int updateCurrencyByPrimaryKey(Currency record);

    List<Currency> selectCurrencyAll();
}
