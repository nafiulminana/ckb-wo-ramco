package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyExample;
import com.ckb.wo.server.service.ICurrencyService;

public class CurrencyLocalServiceUtil extends GenericServiceUtil {

	public static int countCurrency(){
		return getCurrencyService().countCurrency();
	}
	
	public static List<Currency> getCurrency(){
		return getCurrencyService().getCurrency();
	}
	
	public static List<Currency> getCurrencyByExample(CurrencyExample example){
		return getCurrencyService().getCurrencyByExample(example);
	}
	
	public static int countCurrencyByExample(CurrencyExample example){
		return getCurrencyService().countCurrencyByExample(example);
	}
	
	public static int deleteCurrencyByPrimaryKey(Long currencyPk){
		return getCurrencyService().deleteCurrency((Long)currencyPk);
	}
	
	public static int updateCurrency(Currency currency){
		return getCurrencyService().updateCurrency(currency);
	}
	
	public static Long insertCurrency(Currency currency){
		return (Long) getCurrencyService().insertCurrency(currency);	
	}
	
	public static Currency getCurrencyByPrimaryKey(Long currencyPk){
		return getCurrencyService().getCurrencyByPrimaryKey(currencyPk);
	}
	
	private static ICurrencyService getCurrencyService(){
		return (ICurrencyService) getBean("currencyService");
	}
}
