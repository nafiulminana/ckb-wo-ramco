package com.ckb.wo.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeExample;
import com.ckb.wo.client.model.ServiceType;
import com.ckb.wo.client.model.ServiceTypeExample;
import com.ckb.wo.server.dao.ServiceTypeDAO;
import com.ckb.wo.server.service.IServiceTypeService;

public class ServiceTypeServiceImpl extends GenericServiceImpl implements IServiceTypeService {
	public ServiceTypeDAO servicetypeDao;
	
	
	public ServiceTypeServiceImpl(ServiceTypeDAO servicetypeDao) {
		super();
		this.servicetypeDao = servicetypeDao;
	}

	public List<ServiceType> getServiceType() {
		ServiceTypeExample example = new ServiceTypeExample();
		return servicetypeDao.selectServiceTypeByExample(example);
	}
	
	public int countServiceType(){
		ServiceTypeExample example = new ServiceTypeExample();
		return servicetypeDao.countServiceTypeByExample(example);
	}
	
	
	public List<ServiceType> getServiceTypeByExample(ServiceTypeExample example){
		return servicetypeDao.selectServiceTypeByExample(example);
	}
	
	public int countServiceTypeByExample(ServiceTypeExample example){
		
		return servicetypeDao.countServiceTypeByExample(example);
	}

	public int deleteServiceType(Long vendorPk){		
		return servicetypeDao.deleteServiceTypeByPrimaryKey(vendorPk);
	}
	
	public Object insertServiceType(ServiceType vendor){
		return servicetypeDao.insertServiceType(vendor);
	}
	
	public int updateServiceType(ServiceType vendor){
		return servicetypeDao.updateServiceTypeByPrimaryKey(vendor);		
	}

	public List<ServiceType> getServiceTypeByExample(
			ServiceTypeExample example, int pageNo, int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return servicetypeDao.selectServiceTypeByExample(example);
	}

        @Override
        public ServiceType getServiceTypeByPrimarykey(Long tserviceTypePk) {
            return servicetypeDao.selectServiceTypeByPrimaryKey(tserviceTypePk);
        }
	
}
