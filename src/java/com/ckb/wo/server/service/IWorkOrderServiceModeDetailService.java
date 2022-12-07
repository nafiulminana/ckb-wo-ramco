package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeExample;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.client.model.WorkOrderServiceModeDetailExample;

public interface IWorkOrderServiceModeDetailService {
	
	public List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetail();

	public int deleteWorkOrderServiceModeDetail(Long woserviceModeDetailPk);

	public Object insertWorkOrderServiceModeDetail(WorkOrderServiceModeDetail servicemodedetail);

	public int updateWorkOrderServiceModeDetail(WorkOrderServiceModeDetail servicemode);

	public int countWorkOrderServiceModeDetail();

	public List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example);
	
	public List<WorkOrderServiceModeDetail> getWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example,int pageNo, int rowPerPage);

	public int countWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example);

	public WorkOrderServiceModeDetail getWorkOrderServiceModeDetailByPrimaryKey(Long woservicemodeDetailPk);
}
