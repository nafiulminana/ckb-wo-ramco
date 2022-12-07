package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeExample;
import com.ckb.wo.client.model.ServiceType;
import com.ckb.wo.client.model.ServiceTypeExample;

public interface IServiceTypeService {
	
	public List<ServiceType> getServiceType();

	public int deleteServiceType(Long vendorPk);

	public Object insertServiceType(ServiceType vendor);

	public int updateServiceType(ServiceType vendor);

	public int countServiceType();

	public List<ServiceType> getServiceTypeByExample(ServiceTypeExample example);
	
	public List<ServiceType> getServiceTypeByExample(ServiceTypeExample example,int pageNo, int rowPerPage);

	public int countServiceTypeByExample(ServiceTypeExample example);
        
        public ServiceType getServiceTypeByPrimarykey(Long tserviceTypePk);
}
