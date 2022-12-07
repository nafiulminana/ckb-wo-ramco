package com.ckb.wo.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.OriVendorRate;
import com.ckb.wo.client.model.OriVendorRateExample;
import com.ckb.wo.server.dao.OriVendorRateDAO;
import com.ckb.wo.server.service.IOriVendorRateService;

public class OriVendorRateServiceImpl extends GenericServiceImpl implements IOriVendorRateService {
	public OriVendorRateDAO orivendorrateDao;
	
	
	public OriVendorRateServiceImpl(OriVendorRateDAO orivendorrateDao) {
		super();
		this.orivendorrateDao = orivendorrateDao;
	}

	public List<OriVendorRate> getOriVendorRate() {
		OriVendorRateExample example = new OriVendorRateExample();
		return orivendorrateDao.selectOriVendorRateByExample(example);
	}
	
	public int countOriVendorRate(){
		OriVendorRateExample example = new OriVendorRateExample();
		return orivendorrateDao.countOriVendorRateByExample(example);
	}
	
	
	public List<OriVendorRate> getOriVendorRateByExample(OriVendorRateExample example){
		return orivendorrateDao.selectOriVendorRateByExample(example);
	}
	
	public int countOriVendorRateByExample(OriVendorRateExample example){
		
		return orivendorrateDao.countOriVendorRateByExample(example);
	}

	public int deleteOriVendorRate(Long orivendorratePk) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<OriVendorRate> getOriVendorRateByExample(
			OriVendorRateExample example, int pageNo, int rowPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object insertOriVendorRate(OriVendorRate orivendorrate) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateOriVendorRate(OriVendorRate orivendorrate) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

	
}
