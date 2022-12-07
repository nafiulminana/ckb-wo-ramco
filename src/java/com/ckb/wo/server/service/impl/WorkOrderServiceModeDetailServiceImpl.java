package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.client.model.WorkOrderServiceModeDetailExample;
import com.ckb.wo.server.dao.WorkOrderServiceModeDetailDAO;
import com.ckb.wo.server.service.IWorkOrderServiceModeDetailService;

public class WorkOrderServiceModeDetailServiceImpl extends GenericServiceImpl implements IWorkOrderServiceModeDetailService {
	public WorkOrderServiceModeDetailDAO workorderservicemodedetailDao;
	
	
	public WorkOrderServiceModeDetailServiceImpl(WorkOrderServiceModeDetailDAO servicemodeDao) {
		super();
		this.workorderservicemodedetailDao = servicemodeDao;
	}

	public List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetail() {
		WorkOrderServiceModeDetailExample example = new WorkOrderServiceModeDetailExample();
		return workorderservicemodedetailDao.selectWorkOrderServiceModeDetailByExample(example);
	}
	
	public int countWorkOrderServiceModeDetail(){
		WorkOrderServiceModeDetailExample example = new WorkOrderServiceModeDetailExample();
		return workorderservicemodedetailDao.countWorkOrderServiceModeDetailByExample(example);
	}
	
	
	public List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example){
		return workorderservicemodedetailDao.selectWorkOrderServiceModeDetailByExample(example);
	}
	
	public int countWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example){
		
		return workorderservicemodedetailDao.countWorkOrderServiceModeDetailByExample(example);
	}

	public int deleteWorkOrderServiceModeDetail(Long woServiceModeDetailPk){		
		return workorderservicemodedetailDao.deleteWorkOrderServiceModeDetailByPrimaryKey(woServiceModeDetailPk);
	}
	
	public Object insertWorkOrderServiceModeDetail(WorkOrderServiceModeDetail woservicemodedetail){
		return workorderservicemodedetailDao.insertWorkOrderServiceModeDetail(woservicemodedetail);
	}
	
	public int updateWorkOrderServiceModeDetail(WorkOrderServiceModeDetail woservicemodeDetail){
		return workorderservicemodedetailDao.updateWorkOrderServiceModeDetailByPrimaryKey(woservicemodeDetail);		
	}
	
	public WorkOrderServiceModeDetail getWorkOrderServiceModeDetailByPrimaryKey(Long servicemodePk){
		return workorderservicemodedetailDao.selectWorkOrderServiceModeDetailByPrimaryKey(servicemodePk);
	}

	public List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetailByExample(
			WorkOrderServiceModeDetailExample example, int pageNo, int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return workorderservicemodedetailDao.selectWorkOrderServiceModeDetailByExample(example);
	}
	
}
