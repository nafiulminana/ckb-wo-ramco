package com.ckb.wo.server.service;

import java.math.BigDecimal;

public interface IChargeableWeightCalculatorService {

	public abstract BigDecimal calculateChargeableWeight(
			BigDecimal totalWeight, BigDecimal totalVolume, String moda);

	public abstract BigDecimal calculateChargeableWeight(
			BigDecimal totalWeight, BigDecimal totalVolume, Long tservicemode_fk);

}