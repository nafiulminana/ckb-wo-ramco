package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.WoDaManStatus;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderExample;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.server.exception.ValidationException;
import java.math.BigDecimal;

public interface IWorkOrderService {

    public WorkOrder getWorkOrderyByPrimaryKey(Long workorderPk);

    public List<WorkOrder> getWorkOrderByExample(WorkOrderExample example, int pageNo, int rowPerPage);

    public int updateWorkOrderWithChildren(WorkOrder workorder, String employeeId) throws SecurityException, ValidationException;

    public Object insertWorkOrder(WorkOrder workorder, String employeeId) throws SecurityException;

    public int deleteWorkOrder(Long workorderPk);

    public int countWorkOrderByExample(WorkOrderExample example);

    public List<WorkOrder> getWorkOrderByExample(WorkOrderExample example);

    public int countWorkOrder();

    public List<WorkOrder> getWorkOrder();

    public List<Level> getApprovalLevelForDropDown();

    public List<WorkOrder> getWorkOrderByExampleWithJoin(ExtendedWorkOrderExample example);

    public WorkOrder getWorkOrderyByPrimaryKeyWithJoin(Long workorderPk);

    public void approveWorkOrder(Long workOrderPk, String reason, String employeeId) throws SecurityException;

    public void printWorkOrder(Long workOrderPk, String employeeId) throws SecurityException;

    public String generateWorkOrderNumber();

    public List<WorkOrder> getWorkOrderToBeApprovedWithoutGrant(String employeeId, String limitClause, String orderByClause);

    public List<WorkOrder> getWorkOrderToBeApprovedWithGrant(String employeeId, String limitClause, String orderByClause);

    public void editWorkOrder(Long workOrderPk, String reason, String employeeId) throws SecurityException;

    public void cancelWorkOrder(Long workOrderPk, String reason, BigDecimal cancellationfee, Long tcurrencyPk, String employeeId) throws SecurityException;

    public void cancelWorkOrderAP(Long workOrderPk, String reason, BigDecimal cancellationfee, Long tcurrencyPk, String employeeId) throws SecurityException;

    public void revisiWorkOrder(WorkOrder workOrder, String reason, String employeeId) throws SecurityException;

    public List<WorkOrder> getWorkOrderByExampleWithJoinToFast(ExtendedWorkOrderExample example);

    public WorkOrder getWorkOrderyByPrimaryKeyWithJoinToFast(Long workorderPk);

    public int countWorkOrderToBeApprovedWithoutGrant(String employeeId);

    public int countWorkOrderToBeApprovedWithGrant(String employeeId);

    public int countWorkOrderForFinanceUser();

    public List<WorkOrder> getWorkOrderForFinanceUser(String limitClause, String orderByClause);

    public int countWorkOrderForProcuremenUser(String nomorwo);

    public List<WorkOrder> getWorkOrderForProcurementUser(String nomorwo, String limitClause, String orderByClause);

    public int countNewWorkOrderFromThisUser(String employeeId);

    public List<WorkOrder> getNewWorkOrderFromThisUser(String employeeId, String limitClause, String orderByClause);

    public int countAllWorkOrderFromThisUser(String employeeId);

    public List<WorkOrder> getAllWorkOrderFromThisUser(String employeeId, String limitClause, String orderByClause);

    public void updateAfterCostValidation(WorkOrder workorder, String employeeId);

    int countWorkOrderValidatedByThisUser(String employeeId);

    List<WorkOrder> getWorkOrderValidatedByThisUser(String employeeId, String limitClause, String orderByClause);

    public void receivedAPWorkOrder(Long workorderPk, String employeeId);

    public void receivedTreasuryWorkOrder(Long workorderPk, String employeeId);

    public String sendReminder(Long tworkorderPk);

    public int countWorkOrderApprovedByThisUser(String employeeId, String wonumber);

    public List<WorkOrder> getWorkOrderApprovedByThisUser(String employeeId, String wonumber, String limitClause, String orderByClause);

    public void sendCustomCostValidationMail(WorkOrder workOrder, String content);

    public void updateWorkOrderAfterChargeableWeightValidation(WorkOrder workOrder, String employeeId);

    public List<WorkOrder> getWorkOrderByExampleWithJoinForFinance(ExtendedWorkOrderExample example);

    public int countWorkOrderByExampleWithJoinForFinance(ExtendedWorkOrderExample example);

    public void insertWorkOrderFlowForDelegation(WorkOrder workOrder, String reason, User currentUser, String woStatus);

    public List<WorkOrderServiceModeDetail> getWorkOrderSubTotal(Long tworkorder_pk);

    int countWorkOrderUsingPODToBeApprovedWithGrant(String employeeId);

    int countWorkOrderUsingPODToBeApprovedWithoutGrant(String employeeId);

    List<WorkOrder> getWorkOrderUsingPODToBeApprovedWithGrant(String employeeId, String limitClause, String orderByClause);

    List<WorkOrder> getWorkOrderUsingPODToBeApprovedWithoutGrant(String employeeId, String limitClause, String orderByClause);

    List<WoDaManStatus> getWoDaManStatusByMan(List<String> reffNoList);

    List<WoDaManStatus> getWoDaManStatusByDa(List<String> reffNoList);

    List<WoDaManStatus> getWoDaManStatusByWo(List<String> reffNoList);
    
    public List<WorkOrder> getWorkOrderByExampleWithJoinWithoutDa(ExtendedWorkOrderExample example);
    
    public void cancelWorkOrderByAdmin(Long workOrderPk, String reason, String employeeId) throws SecurityException;
    
    public void printWorkOrderWithOutUpdate(Long workOrderPk, String employeeId) throws SecurityException;
}
