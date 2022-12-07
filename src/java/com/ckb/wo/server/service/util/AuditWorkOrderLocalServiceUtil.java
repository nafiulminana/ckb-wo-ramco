package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.AuditWorkOrder;
import com.ckb.wo.client.model.AuditWorkOrderExample;
import com.ckb.wo.server.service.IAuditWorkOrderService;

public class AuditWorkOrderLocalServiceUtil extends GenericServiceUtil {

	public static int countAuditWorkOrder(){
		return getAuditWorkOrderService().countAuditWorkOrder();
	}
	
	public static List<AuditWorkOrder> getAuditWorkOrder(){
		return getAuditWorkOrderService().getAuditWorkOrder();
	}
	
	public static List<AuditWorkOrder> getAuditWorkOrderByExample(AuditWorkOrderExample example){
		return getAuditWorkOrderService().getAuditWorkOrderByExample(example);
	}
	
	public static int countAuditWorkOrderByExample(AuditWorkOrderExample example){
		return getAuditWorkOrderService().countAuditWorkOrderByExample(example);
	}
	
	public static int deleteAuditWorkOrderByPrimaryKey(Long auditworkorderPk){
		return getAuditWorkOrderService().deleteAuditWorkOrder(auditworkorderPk);
	}
	
	public static int updateAuditWorkOrder(AuditWorkOrder auditworkorder){
		return getAuditWorkOrderService().updateAuditWorkOrder(auditworkorder);
	}
	
	public static void insertAuditWorkOrder(AuditWorkOrder auditworkorder){
		getAuditWorkOrderService().insertAuditWorkOrder(auditworkorder);	
	}
	
	public static AuditWorkOrder getAuditWorkOrderByPrimaryKey(Long auditworkorderPk){
		return getAuditWorkOrderService().getAuditWorkOrderyByPrimaryKey(auditworkorderPk);
	}
	
	public static void insertPrintWorkOrder(String employeeId,Long tworkorderPk){
		getAuditWorkOrderService().insertPrintWorkOrder(employeeId, tworkorderPk);
	}
	
	private static IAuditWorkOrderService getAuditWorkOrderService(){
		return (IAuditWorkOrderService) getBean("auditworkorderService");
	}
}
