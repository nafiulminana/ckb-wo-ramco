package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyExample;
import com.ckb.wo.server.dao.CurrencyDAO;
import com.ckb.wo.server.service.ICurrencyService;

public class CurrencyServiceImpl extends GenericServiceImpl implements ICurrencyService {

    private final CurrencyDAO currencyDao;

    public CurrencyServiceImpl(CurrencyDAO currencyDao) {
        super();
        this.currencyDao = currencyDao;
    }

    @Override
    public List<Currency> getCurrency() {
        CurrencyExample example = new CurrencyExample();
        return currencyDao.selectCurrencyByExample(example);
    }

    @Override
    public int countCurrency() {
        CurrencyExample example = new CurrencyExample();
        return currencyDao.countCurrencyByExample(example);
    }

    @Override
    public List<Currency> getCurrencyByExample(CurrencyExample example) {
        return currencyDao.selectCurrencyByExample(example);
    }

    @Override
    public int countCurrencyByExample(CurrencyExample example) {
        return currencyDao.countCurrencyByExample(example);
    }

    @Override
    public int deleteCurrency(Long currencyPk) {
        return currencyDao.deleteCurrencyByPrimaryKey(currencyPk);
    }

    @Override
    public Object insertCurrency(Currency currency) {
        return currencyDao.insertCurrency(currency);
    }

    @Override
    public int updateCurrency(Currency currency) {
        return currencyDao.updateCurrencyByPrimaryKey(currency);
    }

    @Override
    public List<Currency> getCurrencyByExample(CurrencyExample example, int pageNo, int rowPerPage) {
        example.setLimitClause(setOffset(pageNo, rowPerPage));
        return currencyDao.selectCurrencyByExample(example);
    }

    @Override
    public Currency getCurrencyByPrimaryKey(Long currencyPk) {
        return currencyDao.selectCurrencyByPrimaryKey(currencyPk);
    }
}
