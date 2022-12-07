package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.OriVendorRate;
import com.ckb.wo.client.model.OriVendorRateExample;

public interface IOriVendorRateService {
	
	public List<OriVendorRate> getOriVendorRate();

	public int deleteOriVendorRate(Long orivendorratePk);

	public Object insertOriVendorRate(OriVendorRate orivendorrate);

	public int updateOriVendorRate(OriVendorRate orivendorrate);

	public int countOriVendorRate();

	public List<OriVendorRate> getOriVendorRateByExample(OriVendorRateExample example);
	
	public List<OriVendorRate> getOriVendorRateByExample(OriVendorRateExample example,int pageNo, int rowPerPage);

	public int countOriVendorRateByExample(OriVendorRateExample example);
}
