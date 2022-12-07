package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeExample;

public interface IServiceModeService {
	
	public List<ServiceMode> getServiceMode();

	public int deleteServiceMode(Long servicemodePk);

	public Object insertServiceMode(ServiceMode servicemode);

	public int updateServiceMode(ServiceMode servicemode);

	public int countServiceMode();

	public List<ServiceMode> getServiceModeByExample(ServiceModeExample example);
	
	public List<ServiceMode> getServiceModeByExample(ServiceModeExample example,int pageNo, int rowPerPage);

	public int countServiceModeByExample(ServiceModeExample example);

	public ServiceMode getServiceModeByPrimaryKey(Long servicemodePk);
}
