package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.AuditWorkOrder;
import com.ckb.wo.client.model.AuditWorkOrderExample;
import com.ckb.wo.server.dao.AuditWorkOrderDAO;
import com.ckb.wo.server.service.IAuditWorkOrderService;

public class AuditWorkOrderServiceImpl extends GenericServiceImpl implements IAuditWorkOrderService {

	private AuditWorkOrderDAO auditworkorderDao;
		
	public AuditWorkOrderServiceImpl(AuditWorkOrderDAO auditworkorderDao) {
		super();
		this.auditworkorderDao = auditworkorderDao;
	}

	public List<AuditWorkOrder> getAuditWorkOrder() {
		AuditWorkOrderExample example = new AuditWorkOrderExample();
		return auditworkorderDao.selectAuditWorkOrderByExample(example);
	}
	
	public int countAuditWorkOrder(){
		AuditWorkOrderExample example = new AuditWorkOrderExample();
		return auditworkorderDao.countAuditWorkOrderByExample(example);
	}
	
	
	public List<AuditWorkOrder> getAuditWorkOrderByExample(AuditWorkOrderExample example){
		return auditworkorderDao.selectAuditWorkOrderByExample(example);
	}
	
	public int countAuditWorkOrderByExample(AuditWorkOrderExample example){		
		return auditworkorderDao.countAuditWorkOrderByExample(example);
	}

	public int deleteAuditWorkOrder(Long auditworkorderPk){		
		return auditworkorderDao.deleteAuditWorkOrderByPrimaryKey(auditworkorderPk);
	}
	
	public int deleteAuditWorkOrderByExample(AuditWorkOrderExample example){
		return auditworkorderDao.deleteAuditWorkOrderByExample(example);
	}
	
	public Object insertAuditWorkOrder(AuditWorkOrder auditworkorder){
		return auditworkorderDao.insertAuditWorkOrder(auditworkorder);
	}
	
	public int updateAuditWorkOrder(AuditWorkOrder auditworkorder){
		return auditworkorderDao.updateAuditWorkOrderByPrimaryKey(auditworkorder);		
	}
	
	public AuditWorkOrder getAuditWorkOrderyByPrimaryKey(Long auditworkorderPk){
		return auditworkorderDao.selectAuditWorkOrderByPrimaryKey(auditworkorderPk);		
	}

    public void insertPrintWorkOrder(String employeeId, Long tworkorderPk){
    	  AuditWorkOrderExample example = new AuditWorkOrderExample();
    	  AuditWorkOrderExample.Criteria crit = example.createCriteria();
    	  crit.andTworkorderFkEqualTo(tworkorderPk);
    	  crit.andEmployeeIdEqualTo(employeeId);
    	  crit.andActionEqualTo(AuditWorkOrder.PRINT_ACTION);
    	
    	  List<AuditWorkOrder> lawo = auditworkorderDao.selectAuditWorkOrderByExample(example);
    	  if(lawo==null || lawo.size()==0){
    		  AuditWorkOrder awo = new AuditWorkOrder();
    		  awo.setAction(AuditWorkOrder.PRINT_ACTION);
    		  awo.setEmployeeId(employeeId);
    		  awo.setTotalaction(1);
    		  awo.setTworkorderFk(tworkorderPk);
    		  auditworkorderDao.insertAuditWorkOrder(awo);
    	  }else{
    		  int total = 0;    		  
    		  AuditWorkOrder awo = lawo.get(0);
    		  if(awo.getTotalaction()!=null){
    			  total = awo.getTotalaction().intValue()+1;
    		  }else{
    			  total=1;
    		  }
    		  awo.setTotalaction(total);
    		  auditworkorderDao.updateAuditWorkOrderByPrimaryKey(awo);
    	  }
    }     
}
