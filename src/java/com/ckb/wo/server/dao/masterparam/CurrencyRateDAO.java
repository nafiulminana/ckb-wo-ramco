package com.ckb.wo.server.dao.masterparam;

import com.ckb.wo.client.model.CurrencyRate;
import java.util.Map;

public interface CurrencyRateDAO {

    CurrencyRate getCurrencyRateByDate(Map p);
}
