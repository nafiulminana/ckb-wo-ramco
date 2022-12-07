package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyExample;

public interface ICurrencyService {

	public List<Currency> getCurrency();

	public int countCurrency();

	public List<Currency> getCurrencyByExample(CurrencyExample example);

	public int countCurrencyByExample(CurrencyExample example);

	public int deleteCurrency(Long currencyPk);

	public Object insertCurrency(Currency currency);

	public int updateCurrency(Currency currency);

	public List<Currency> getCurrencyByExample(CurrencyExample example,
			int pageNo, int rowPerPage);

	public Currency getCurrencyByPrimaryKey(Long currencyPk);

}