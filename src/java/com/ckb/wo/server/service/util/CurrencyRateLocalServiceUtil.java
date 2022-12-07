package com.ckb.wo.server.service.util;

import com.ckb.wo.client.model.CurrencyRate;
import com.ckb.wo.server.service.ICurrencyRateService;
import static com.ckb.wo.server.service.util.GenericServiceUtil.getBean;
import java.util.Map;

public class CurrencyRateLocalServiceUtil extends GenericServiceUtil {

    public static CurrencyRate getCurrencyRatesByDate(Map p) {
        return getCurrencyRateService().getCurrencyRatesByDate(p);
    }

    private static ICurrencyRateService getCurrencyRateService() {
        return (ICurrencyRateService) getBean("currencyRateService");
    }
}
