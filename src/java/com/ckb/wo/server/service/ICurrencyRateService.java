package com.ckb.wo.server.service;

import com.ckb.wo.client.model.CurrencyRate;
import java.util.Map;

public interface ICurrencyRateService {

    CurrencyRate getCurrencyRatesByDate(Map p);
}
