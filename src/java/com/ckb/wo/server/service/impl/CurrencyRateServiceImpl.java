package com.ckb.wo.server.service.impl;

import com.ckb.wo.client.model.CurrencyRate;
import com.ckb.wo.server.dao.masterparam.CurrencyRateDAO;
import com.ckb.wo.server.service.ICurrencyRateService;
import java.util.Map;

public class CurrencyRateServiceImpl extends GenericServiceImpl implements ICurrencyRateService {

    private CurrencyRateDAO currencyRateDAO;

    public CurrencyRateServiceImpl(CurrencyRateDAO currencyRateDAO) {
        this.currencyRateDAO = currencyRateDAO;
    }

    @Override
    public CurrencyRate getCurrencyRatesByDate(Map p) {
        return currencyRateDAO.getCurrencyRateByDate(p);
    }

}
