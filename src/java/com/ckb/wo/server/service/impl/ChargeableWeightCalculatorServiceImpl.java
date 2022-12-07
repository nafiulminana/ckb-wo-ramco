package com.ckb.wo.server.service.impl;

import java.math.BigDecimal;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.server.service.IChargeableWeightCalculatorService;
import com.ckb.wo.server.service.util.ServiceModeLocalServiceUtil;
import java.math.RoundingMode;

public class ChargeableWeightCalculatorServiceImpl implements IChargeableWeightCalculatorService {

    public static final String MODA_AIR = "AIR";
    public static final String MODA_ROAD = "ROAD";
    public static final String MODA_SEA = "SEA";
//	public static final BigDecimal AIR_DIVIDER = new BigDecimal("6000");
//	public static final BigDecimal ROAD_DIVIDER = new BigDecimal("4000");
    public static final BigDecimal AIR_DIVIDER = new BigDecimal("6");
    public static final BigDecimal ROAD_DIVIDER = new BigDecimal("4");
    public static final BigDecimal TON_DIVIDER = new BigDecimal("1000");

    @Override
    public BigDecimal calculateChargeableWeight(BigDecimal totalWeight, BigDecimal totalVolume, String moda) {
        BigDecimal converted;
        if (MODA_AIR.equalsIgnoreCase(moda)) {
            converted = totalVolume.divide(AIR_DIVIDER, 4, RoundingMode.HALF_EVEN);
            return getBiggestAmount(converted, totalWeight);
        } else if (MODA_ROAD.equalsIgnoreCase(moda)) {
            converted = totalVolume.divide(ROAD_DIVIDER, 4, RoundingMode.HALF_EVEN);
            return getBiggestAmount(converted, totalWeight);
        } else if (MODA_SEA.equalsIgnoreCase(moda)) {
//			converted = totalWeight.divide(TON_DIVIDER);
            converted = totalVolume.multiply(TON_DIVIDER);
            return getBiggestAmount(converted, totalVolume);
        }
        return null;
    }

    private BigDecimal getBiggestAmount(BigDecimal value1, BigDecimal value2) {
        int comparison = value1.compareTo(value2);
        if (comparison < 0) {
            return value2;
        } else {
            return value1;
        }
    }

    @Override
    public BigDecimal calculateChargeableWeight(BigDecimal totalWeight, BigDecimal totalVolume, Long tservicemode_fk) {
        ServiceMode smode = ServiceModeLocalServiceUtil.getServiceModeByPrimaryKey(tservicemode_fk);
        return calculateChargeableWeight(totalWeight, totalVolume, smode.getSmodecode());
    }
}
