package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.client.model.WorkOrderFlowExample;
import com.ckb.wo.server.service.IWorkOrderFlowService;

public class WorkOrderFlowLocalServiceUtil extends GenericServiceUtil {

	public static int countWorkOrderFlow(){
		return getWorkOrderFlowService().countWorkOrderFlow();
	}
	
	public static List<WorkOrderFlow> getWorkOrderFlow(){
		return getWorkOrderFlowService().getWorkOrderFlow();
	}
	
	public static List<WorkOrderFlow> getWorkOrderFlowByExample(WorkOrderFlowExample example){
		return getWorkOrderFlowService().getWorkOrderFlowByExample(example);
	}
	
	public static int countWorkOrderFlowByExample(WorkOrderFlowExample example){
		return getWorkOrderFlowService().countWorkOrderFlowByExample(example);
	}
	
	public static int deleteWorkOrderFlowByPrimaryKey(Long workorderflowPk){
		return getWorkOrderFlowService().deleteWorkOrderFlow((Long)workorderflowPk);
	}
	
	public static int updateWorkOrderFlow(WorkOrderFlow workorderflow){
		return getWorkOrderFlowService().updateWorkOrderFlow(workorderflow);
	}
	
	public static Long insertWorkOrderFlow(WorkOrderFlow workorderflow){
		return (Long) getWorkOrderFlowService().insertWorkOrderFlow(workorderflow);	
	}
	
	public static WorkOrderFlow getWorkOrderFlowByPrimaryKey(Long workorderflowPk){
		return getWorkOrderFlowService().getWorkOrderFlowyByPrimaryKey(workorderflowPk);
	}

        public static WorkOrderFlow getLatestWorkOrderFlowByWorkOrderPk(Long workorderPk){
            return getWorkOrderFlowService().getLatestWorkOrderFlowByWorkOrderPk(workorderPk);
        }

        public static int countWorkOrderFlowByWorkOrder(Long workorderPk){
            return getWorkOrderFlowService().countWorkOrderFlowByWorkOrder(workorderPk);
        }

        public static List<WorkOrderFlow> getWorkOrderFlowByWorkOrder(Long workorderPk, String limitClause, String orderByClause){
            return getWorkOrderFlowService().getWorkOrderFlowByWorkOrder(workorderPk, limitClause, orderByClause);
        }

        public static List<WorkOrderFlow> getApprovalHistoryForThisWorkOrder(Long workorderPk){
            return getWorkOrderFlowService().getApprovalHistoryForThisWorkOrder(workorderPk);
        }
    
	private static IWorkOrderFlowService getWorkOrderFlowService(){
		return (IWorkOrderFlowService) getBean("workorderflowService");
	}
}
