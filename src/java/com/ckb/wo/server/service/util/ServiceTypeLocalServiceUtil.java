package com.ckb.wo.server.service.util;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceType;
import com.ckb.wo.client.model.ServiceTypeExample;
import com.ckb.wo.server.service.IServiceTypeService;

public class ServiceTypeLocalServiceUtil extends GenericServiceUtil {

	public static int countServiceType(){
		return getServiceTypeService().countServiceType();
	}
	
	public static List<ServiceType> getServiceType(){
		return getServiceTypeService().getServiceType();
	}
	
	public static List<ServiceType> getServiceTypeByExample(ServiceTypeExample example){
		return getServiceTypeService().getServiceTypeByExample(example);
	}
	
	public static int countServiceTypeByExample(ServiceTypeExample example){
		return getServiceTypeService().countServiceTypeByExample(example);
	}
	
	public static int deleteServiceTypeByPrimaryKey(Long servicetypePk){
		return getServiceTypeService().deleteServiceType(servicetypePk);
	}
	
	public static int updateServiceType(ServiceType servicetype){
		return getServiceTypeService().updateServiceType(servicetype);
	}
        
        public static ServiceType getServiceTypeByPrimaryKey(Long serviceTypePk){
		return getServiceTypeService().getServiceTypeByPrimarykey(serviceTypePk);
	}
	
	private static IServiceTypeService getServiceTypeService(){
		return (IServiceTypeService) getBean("servicetypeService");
	}
}
