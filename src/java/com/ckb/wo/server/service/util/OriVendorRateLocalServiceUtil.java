package com.ckb.wo.server.service.util;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.OriVendorRate;
import com.ckb.wo.client.model.OriVendorRateExample;
import com.ckb.wo.server.service.IOriVendorRateService;

public class OriVendorRateLocalServiceUtil extends GenericServiceUtil {

	public static int countOriVendorRate(){
		return getOriVendorRateService().countOriVendorRate();
	}
	
	public static List<OriVendorRate> getOriVendorRate(){
		return getOriVendorRateService().getOriVendorRate();
	}
	
	public static List<OriVendorRate> getOriVendorRateByExample(OriVendorRateExample example){
		return getOriVendorRateService().getOriVendorRateByExample(example);
	}
	
	public static int countOriVendorRateByExample(OriVendorRateExample example){
		return getOriVendorRateService().countOriVendorRateByExample(example);
	}
	
	
	private static IOriVendorRateService getOriVendorRateService(){
		return (IOriVendorRateService) getBean("orivendorrateService");
	}
}
