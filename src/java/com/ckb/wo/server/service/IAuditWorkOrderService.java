package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.AuditWorkOrder;
import com.ckb.wo.client.model.AuditWorkOrderExample;

public interface IAuditWorkOrderService {

	public List<AuditWorkOrder> getAuditWorkOrder();

	public int countAuditWorkOrder();

	public List<AuditWorkOrder> getAuditWorkOrderByExample(AuditWorkOrderExample example);

	public int countAuditWorkOrderByExample(AuditWorkOrderExample example);

	public int deleteAuditWorkOrder(Long auditworkorderPk);

	public int deleteAuditWorkOrderByExample(AuditWorkOrderExample example);

	public Object insertAuditWorkOrder(AuditWorkOrder auditworkorder);

	public int updateAuditWorkOrder(AuditWorkOrder auditworkorder);

	public AuditWorkOrder getAuditWorkOrderyByPrimaryKey(Long auditworkorderPk);

	public void insertPrintWorkOrder(String employeeId, Long tworkorderPk);

}
