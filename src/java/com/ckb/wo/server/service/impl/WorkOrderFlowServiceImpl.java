package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.client.model.WorkOrderFlowExample;
import com.ckb.wo.server.dao.WorkOrderFlowDAO;
import com.ckb.wo.server.service.IWorkOrderFlowService;

public class WorkOrderFlowServiceImpl extends GenericServiceImpl implements IWorkOrderFlowService {

	private WorkOrderFlowDAO workorderflowDao;
	
	
	public WorkOrderFlowServiceImpl(WorkOrderFlowDAO workorderflowDao) {
		super();
		this.workorderflowDao = workorderflowDao;
	}

	public List<WorkOrderFlow> getWorkOrderFlow() {
		WorkOrderFlowExample example = new WorkOrderFlowExample();
		return workorderflowDao.selectWorkOrderFlowByExample(example);
	}
	
	public int countWorkOrderFlow(){
		WorkOrderFlowExample example = new WorkOrderFlowExample();
		return workorderflowDao.countWorkOrderFlowByExample(example);
	}
	
	
	public List<WorkOrderFlow> getWorkOrderFlowByExample(WorkOrderFlowExample example){
		return workorderflowDao.selectWorkOrderFlowByExample(example);
	}
	
	public int countWorkOrderFlowByExample(WorkOrderFlowExample example){
		
		return workorderflowDao.countWorkOrderFlowByExample(example);
	}

	public int deleteWorkOrderFlow(Long workorderflowPk){		
		return workorderflowDao.deleteWorkOrderFlowByPrimaryKey(workorderflowPk);
	}
	
	public int deleteWorkOrderFlowByExample(WorkOrderFlowExample example){
		return workorderflowDao.deleteWorkOrderFlowByExample(example);
	}
	
	public Object insertWorkOrderFlow(WorkOrderFlow workorderflow){
		return workorderflowDao.insertWorkOrderFlow(workorderflow);
	}
	
	public int updateWorkOrderFlow(WorkOrderFlow workorderflow){
		return workorderflowDao.updateWorkOrderFlowByPrimaryKey(workorderflow);		
	}

	public List<WorkOrderFlow> getWorkOrderFlowByExample(WorkOrderFlowExample example, int pageNo,
			int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return workorderflowDao.selectWorkOrderFlowByExample(example);
	}
	
	public WorkOrderFlow getWorkOrderFlowyByPrimaryKey(Long workorderflowPk){
		return workorderflowDao.selectWorkOrderFlowByPrimaryKey(workorderflowPk);		
	}

            public WorkOrderFlow getLatestWorkOrderFlowByWorkOrderPk(Long tworkorderFk) {
        WorkOrderFlowExample example = new WorkOrderFlowExample();
        example.createCriteria().andTworkorderFkEqualTo(tworkorderFk);
        example.setOrderByClause("tworkorderflow_pk desc");
        List<WorkOrderFlow> lwo = workorderflowDao.selectWorkOrderFlowByExample(example);
        if (lwo != null && lwo.size() > 0) {
            return lwo.get(0);
        } else {
            return null;
        }
    }

    public List<WorkOrderFlow> getWorkOrderFlowByWorkOrder(Long tworkorderFk, String limitClause, String orderByClause){
        WorkOrderFlowExample example = getWorkOrderFlowExampleByWorkOrder(tworkorderFk);
        if(limitClause!=null && limitClause.length()>0){
            example.setLimitClause(limitClause);
        }
        if(orderByClause!=null && orderByClause.length()>0){
            example.setOrderByClause(orderByClause);
        }
        return workorderflowDao.selectWorkOrderFlowByExample(example);
    }

    public int countWorkOrderFlowByWorkOrder(Long tworkorderFk){
        return workorderflowDao.countWorkOrderFlowByExample(getWorkOrderFlowExampleByWorkOrder(tworkorderFk));
    }

    private WorkOrderFlowExample getWorkOrderFlowExampleByWorkOrder(Long tworkorderFk){
        WorkOrderFlowExample example = new WorkOrderFlowExample();
        example.createCriteria().andTworkorderFkEqualTo(tworkorderFk);
        return example;
    }
            
    public List<Long> getWorkOrderValidatedByThisUser(String employeeId){    		
    	return workorderflowDao.getWorkOrderValidatedByThisUser(employeeId);
    }        
    
    public List<WorkOrderFlow> getApprovalHistoryForThisWorkOrder(Long workorderPk){
    	return workorderflowDao.selectApprovalHistoryForThisWorkOrder(workorderPk);
    }
}
