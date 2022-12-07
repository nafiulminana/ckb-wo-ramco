package com.ckb.wo.server.dao.masterparam.ibatis.impl;

import com.ckb.wo.client.model.CurrencyRate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ckb.wo.server.dao.masterparam.CurrencyRateDAO;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class CurrencyRateDAOImpl extends SqlMapClientDaoSupport implements CurrencyRateDAO {

    @Override
    public CurrencyRate getCurrencyRateByDate(Map p) { 
        if(p.get("currencyCode").equals("IDR")){ 
            CurrencyRate currencyIdr = new CurrencyRate();
            currencyIdr.setCurrencyTypeId("IDR");
            currencyIdr.setRateType(1);
            currencyIdr.setRates(new BigDecimal(1));
            currencyIdr.setRateDate(new java.util.Date());
            currencyIdr.setRateDateTo(new java.util.Date()); 
            return currencyIdr;
        } else {
            return (CurrencyRate) getSqlMapClientTemplate().queryForObject("t_currency_rates.selectCurrencyRateByDate", p);
        }      
    }

}
