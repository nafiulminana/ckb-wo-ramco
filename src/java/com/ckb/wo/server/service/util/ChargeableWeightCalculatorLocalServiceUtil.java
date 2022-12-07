package com.ckb.wo.server.service.util;

import java.math.BigDecimal;

import com.ckb.wo.server.service.IChargeableWeightCalculatorService;
import com.ckb.wo.server.service.factory.BeanFactory;

public class ChargeableWeightCalculatorLocalServiceUtil extends
		GenericServiceUtil {

	
	public static BigDecimal calculateChargeableWeight(BigDecimal totalWeight, BigDecimal totalVolume, String moda){
		return getChargeableWeightCalculatorService().calculateChargeableWeight(totalWeight, totalVolume, moda);
	}
	
	public static BigDecimal calculateChargeableWeight(BigDecimal totalWeight, BigDecimal totalVolume, Long tservicemode_fk){
		return getChargeableWeightCalculatorService().calculateChargeableWeight(totalWeight, totalVolume, tservicemode_fk);
	}	
	public static IChargeableWeightCalculatorService getChargeableWeightCalculatorService(){
		return (IChargeableWeightCalculatorService) BeanFactory.getBean("chargeableweightcalculatorService");
	}
}
