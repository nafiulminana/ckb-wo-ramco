package com.ckb.wo.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeExample;
import com.ckb.wo.server.dao.ServiceModeDAO;
import com.ckb.wo.server.service.IServiceModeService;

public class ServiceModeServiceImpl extends GenericServiceImpl implements IServiceModeService {
	public ServiceModeDAO servicemodeDao;
	
	
	public ServiceModeServiceImpl(ServiceModeDAO servicemodeDao) {
		super();
		this.servicemodeDao = servicemodeDao;
	}

	public List<ServiceMode> getServiceMode() {
		ServiceModeExample example = new ServiceModeExample();
		return servicemodeDao.selectServiceModeByExample(example);
	}
	
	public int countServiceMode(){
		ServiceModeExample example = new ServiceModeExample();
		return servicemodeDao.countServiceModeByExample(example);
	}
	
	
	public List<ServiceMode> getServiceModeByExample(ServiceModeExample example){
		return servicemodeDao.selectServiceModeByExample(example);
	}
	
	public int countServiceModeByExample(ServiceModeExample example){
		
		return servicemodeDao.countServiceModeByExample(example);
	}

	public int deleteServiceMode(Long servicemodePk){		
		return servicemodeDao.deleteServiceModeByPrimaryKey(servicemodePk);
	}
	
	public Object insertServiceMode(ServiceMode servicemode){
		return servicemodeDao.insertServiceMode(servicemode);
	}
	
	public int updateServiceMode(ServiceMode servicemode){
		return servicemodeDao.updateServiceModeByPrimaryKey(servicemode);		
	}
	
	public ServiceMode getServiceModeByPrimaryKey(Long servicemodePk){
		return servicemodeDao.selectServiceModeByPrimaryKey(servicemodePk);
	}

	public List<ServiceMode> getServiceModeByExample(
			ServiceModeExample example, int pageNo, int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return servicemodeDao.selectServiceModeByExample(example);
	}
	
}
