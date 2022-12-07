package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.client.model.WorkOrderFlowExample;

public interface IWorkOrderFlowService {

	public List<WorkOrderFlow> getWorkOrderFlow();

	public int countWorkOrderFlow();

	public List<WorkOrderFlow> getWorkOrderFlowByExample(
			WorkOrderFlowExample example);

	public int countWorkOrderFlowByExample(WorkOrderFlowExample example);

	public int deleteWorkOrderFlow(Long workorderflowPk);

	public Object insertWorkOrderFlow(WorkOrderFlow workorderflow);

	public int updateWorkOrderFlow(WorkOrderFlow workorderflow);

	public List<WorkOrderFlow> getWorkOrderFlowByExample(
			WorkOrderFlowExample example, int pageNo, int rowPerPage);

	public WorkOrderFlow getWorkOrderFlowyByPrimaryKey(Long workorderflowPk);

	public int deleteWorkOrderFlowByExample(WorkOrderFlowExample example);

    WorkOrderFlow getLatestWorkOrderFlowByWorkOrderPk(Long tworkorderFk);

	public List<Long> getWorkOrderValidatedByThisUser(String employeeId);

    int countWorkOrderFlowByWorkOrder(Long tworkorderFk);

    List<WorkOrderFlow> getWorkOrderFlowByWorkOrder(Long tworkorderFk, String limitClause, String orderByClause);

	public List<WorkOrderFlow> getApprovalHistoryForThisWorkOrder(Long workorderPk);

}