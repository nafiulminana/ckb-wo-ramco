package com.ckb.wo.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.ServiceModeDetailExample;
import com.ckb.wo.server.dao.ServiceModeDetailDAO;
import com.ckb.wo.server.service.IServiceModeDetailService;

public class ServiceModeDetailServiceImpl extends GenericServiceImpl implements IServiceModeDetailService {
	public ServiceModeDetailDAO servicemodedetailDao;
	
	
	public ServiceModeDetailServiceImpl(ServiceModeDetailDAO servicemodedetailDao) {
		super();
		this.servicemodedetailDao = servicemodedetailDao;
	}

	public List<ServiceModeDetail> getServiceModeDetail() {
		ServiceModeDetailExample example = new ServiceModeDetailExample();
		return servicemodedetailDao.selectServiceModeDetailByExample(example);
	}
	
	public int countServiceModeDetail(){
		ServiceModeDetailExample example = new ServiceModeDetailExample();
		return servicemodedetailDao.countServiceModeDetailByExample(example);
	}
	
	
	public List<ServiceModeDetail> getServiceModeDetailByExample(ServiceModeDetailExample example){
		return servicemodedetailDao.selectServiceModeDetailByExample(example);
	}
	
	public int countServiceModeDetailByExample(ServiceModeDetailExample example){
		
		return servicemodedetailDao.countServiceModeDetailByExample(example);
	}

	public int deleteServiceModeDetail(Long servicemodedetailPk){		
		return servicemodedetailDao.deleteServiceModeDetailByPrimaryKey(servicemodedetailPk);
	}
	
	public Object insertServiceModeDetail(ServiceModeDetail servicemodedetail){
		return servicemodedetailDao.insertServiceModeDetail(servicemodedetail);
	}
	
	public int updateServiceModeDetail(ServiceModeDetail servicemodedetail){
		return servicemodedetailDao.updateServiceModeDetailByPrimaryKey(servicemodedetail);		
	}

	public List<ServiceModeDetail> getServiceModeDetailByExample(
			ServiceModeDetailExample example, int pageNo, int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return servicemodedetailDao.selectServiceModeDetailByExample(example);
	}
	
	public ServiceModeDetail getServiceModeDetailByPrimaryKey(Long servicemodedetailPk){
		return servicemodedetailDao.selectServiceModeDetailByPrimaryKey(servicemodedetailPk);
	}
}
