package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.ServiceModeDetailExample;
import com.ckb.wo.server.service.IServiceModeDetailService;

public class ServiceModeDetailLocalServiceUtil extends GenericServiceUtil {

	public static int countServiceModeDetail(){
		return getServiceModeDetailService().countServiceModeDetail();
	}
	
	public static List<ServiceModeDetail> getServiceModeDetail(){
		return getServiceModeDetailService().getServiceModeDetail();
	}
	
	public static List<ServiceModeDetail> getServiceModeDetailByExample(ServiceModeDetailExample example){
		return getServiceModeDetailService().getServiceModeDetailByExample(example);
	}
	
	public static int countServiceModeDetailByExample(ServiceModeDetailExample example){
		return getServiceModeDetailService().countServiceModeDetailByExample(example);
	}
	
	public static ServiceModeDetail getServiceModeDetailByPrimaryKey(Long servicemodedetailPk){
		return getServiceModeDetailService().getServiceModeDetailByPrimaryKey(servicemodedetailPk);
	}
	
	public static int deleteServiceModeDetailByPrimaryKey(Long servicemodedetailPk){
		return getServiceModeDetailService().deleteServiceModeDetail(servicemodedetailPk);
	}
	
	public static int updateServiceModeDetail(ServiceModeDetail servicemodedetail){
		return getServiceModeDetailService().updateServiceModeDetail(servicemodedetail);
	}
	
	private static IServiceModeDetailService getServiceModeDetailService(){
		return (IServiceModeDetailService) getBean("servicemodedetailService");
	}

	public static Long insertServiceModeDetail(ServiceModeDetail servicemodedetail) {
		return (Long) getServiceModeDetailService().insertServiceModeDetail(servicemodedetail);		
	}
}
