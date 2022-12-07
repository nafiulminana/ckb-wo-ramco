package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.client.model.WorkOrderServiceModeDetailExample;
import com.ckb.wo.server.service.IWorkOrderServiceModeDetailService;

public class WorkOrderServiceModeDetailLocalServiceUtil extends GenericServiceUtil {

	public static int countWorkOrderServiceModeDetail(){
		return getWorkOrderServiceModeDetailService().countWorkOrderServiceModeDetail();
	}
	
	public static List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetail(){
		return getWorkOrderServiceModeDetailService().getWorkOrderServiceModeDetail();
	}
	
	public static List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example){
		return getWorkOrderServiceModeDetailService().getWorkOrderServiceModeDetailByExample(example);
	}
	
	public static WorkOrderServiceModeDetail getWorkOrderServiceModeDetailByPrimaryKey(Long servicemodedetailPk){
		return getWorkOrderServiceModeDetailService().getWorkOrderServiceModeDetailByPrimaryKey(servicemodedetailPk);
	}
	
	public static int countWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example){
		return getWorkOrderServiceModeDetailService().countWorkOrderServiceModeDetailByExample(example);
	}
	
	public static int deleteWorkOrderServiceModeDetailByPrimaryKey(Long servicemodedetailPk){
		return getWorkOrderServiceModeDetailService().deleteWorkOrderServiceModeDetail(servicemodedetailPk);
	}
	
	public static int updateWorkOrderServiceModeDetail(WorkOrderServiceModeDetail woservicemodedetail){
		return getWorkOrderServiceModeDetailService().updateWorkOrderServiceModeDetail(woservicemodedetail);
	}
	
	private static IWorkOrderServiceModeDetailService getWorkOrderServiceModeDetailService(){
		return (IWorkOrderServiceModeDetailService) getBean("workorderservicemodedetailService");
	}

	public static Long insertWorkOrderServiceModeDetail(WorkOrderServiceModeDetail woservicemodedetail) {
		return (Long) getWorkOrderServiceModeDetailService().insertWorkOrderServiceModeDetail(woservicemodedetail);
	}
}
