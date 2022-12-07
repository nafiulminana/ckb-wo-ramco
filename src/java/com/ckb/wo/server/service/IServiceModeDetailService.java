package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.ServiceModeDetailExample;

public interface IServiceModeDetailService {
	
	public List<ServiceModeDetail> getServiceModeDetail();

	public int deleteServiceModeDetail(Long servicemodedetailPk);

	public Object insertServiceModeDetail(ServiceModeDetail servicemodedetail);

	public int updateServiceModeDetail(ServiceModeDetail servicemodedetail);

	public int countServiceModeDetail();

	public List<ServiceModeDetail> getServiceModeDetailByExample(ServiceModeDetailExample example);
	
	public List<ServiceModeDetail> getServiceModeDetailByExample(ServiceModeDetailExample example,int pageNo, int rowPerPage);

	public int countServiceModeDetailByExample(ServiceModeDetailExample example);

	public ServiceModeDetail getServiceModeDetailByPrimaryKey(Long servicemodedetailPk);
}
