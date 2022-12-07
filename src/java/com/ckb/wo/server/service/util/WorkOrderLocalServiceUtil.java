package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.WoDaManStatus;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderExample;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.server.exception.SecurityException;
import com.ckb.wo.server.service.IWorkOrderService;
import java.math.BigDecimal;

public class WorkOrderLocalServiceUtil extends GenericServiceUtil {

    public static int countWorkOrder() {
        return getWorkOrderService().countWorkOrder();
    }

    public static List<WorkOrder> getWorkOrder() {
        return getWorkOrderService().getWorkOrder();
    }

    public static List<WorkOrder> getWorkOrderByExample(WorkOrderExample example) {
        return getWorkOrderService().getWorkOrderByExample(example);
    }

    public static int countWorkOrderByExample(WorkOrderExample example) {
        return getWorkOrderService().countWorkOrderByExample(example);
    }

    public static int deleteWorkOrderByPrimaryKey(Long workorderPk) {
        return getWorkOrderService().deleteWorkOrder(workorderPk);
    }

    public static Long insertWorkOrder(WorkOrder workorder, String employeeId) throws SecurityException {
        return (Long) getWorkOrderService().insertWorkOrder(workorder, employeeId);
    }

    public static void approveWorkOrder(Long workOrderPk, String reason, String employeeId) throws SecurityException {
        getWorkOrderService().approveWorkOrder(workOrderPk, reason, employeeId);
    }

    public static void printWorkOrder(Long workOrderPk, String employeeId) throws SecurityException {
        getWorkOrderService().printWorkOrder(workOrderPk, employeeId);
    }

    public static WorkOrder getWorkOrderByPrimaryKey(Long workorderPk) {
        return getWorkOrderService().getWorkOrderyByPrimaryKey(workorderPk);
    }

    public static WorkOrder getWorkOrderByPrimaryKeyWithJoin(Long workorderPk) {
        return getWorkOrderService().getWorkOrderyByPrimaryKeyWithJoin(workorderPk);
    }

    public static List<WorkOrder> getWorkOrderByExampleWithJoin(ExtendedWorkOrderExample example) {
        return getWorkOrderService().getWorkOrderByExampleWithJoin(example);
    }

    public static List<Level> getApprovalLevelForDropDown() {
        return getWorkOrderService().getApprovalLevelForDropDown();
    }

    public static String generateWorkOrderNumber() {
        return getWorkOrderService().generateWorkOrderNumber();
    }

    public static List<WorkOrder> getWorkOrderToBeApprovedWithoutGrant(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderToBeApprovedWithoutGrant(employeeId, limitClause, orderByClause);
    }

    public static List<WorkOrder> getWorkOrderToBeApprovedWithGrant(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderToBeApprovedWithGrant(employeeId, limitClause, orderByClause);
    }

    public static List<WorkOrder> getWorkOrderUsingPODToBeApprovedWithoutGrant(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderUsingPODToBeApprovedWithoutGrant(employeeId, limitClause, orderByClause);
    }

    public static List<WorkOrder> getWorkOrderUsingPODToBeApprovedWithGrant(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderUsingPODToBeApprovedWithGrant(employeeId, limitClause, orderByClause);
    }

    public static void editWorkOrder(Long workOrderPk, String reason, String employeeId) throws SecurityException {
        getWorkOrderService().editWorkOrder(workOrderPk, reason, employeeId);
    }

    public static void cancelWorkOrder(Long workOrderPk, String reason, BigDecimal cancellationfee, Long tcurrencyPk, String employeeId) throws SecurityException {
        getWorkOrderService().cancelWorkOrder(workOrderPk, reason, cancellationfee, tcurrencyPk, employeeId);
    }

    public static void cancelWorkOrderAP(Long workOrderPk, String reason, BigDecimal cancellationfee, Long tcurrencyPk, String employeeId) throws SecurityException {
        getWorkOrderService().cancelWorkOrderAP(workOrderPk, reason, cancellationfee, tcurrencyPk, employeeId);
    }

    /*
     * pembuat WO, setelah mengubah data WO, save dengan memanggil method ini
     */
    public static void updateWorkOrderWithChildren(WorkOrder workOrder, String employeeId) {
        getWorkOrderService().updateWorkOrderWithChildren(workOrder, employeeId);
    }

    public static WorkOrder getWorkOrderyByPrimaryKeyWithJoinToFast(Long workorderPk) {
        return getWorkOrderService().getWorkOrderyByPrimaryKeyWithJoinToFast(workorderPk);
    }

    public static List<WorkOrder> getWorkOrderByExampleWithJoinToFast(ExtendedWorkOrderExample example) {
        return getWorkOrderService().getWorkOrderByExampleWithJoinToFast(example);
    }

    public static int countWorkOrderUsingPODToBeApprovedWithoutGrant(String employeeId) {
        return getWorkOrderService().countWorkOrderUsingPODToBeApprovedWithoutGrant(employeeId);
    }

    public static int countWorkOrderUsingPODToBeApprovedWithGrant(String employeeId) {
        return getWorkOrderService().countWorkOrderUsingPODToBeApprovedWithGrant(employeeId);
    }

    public static int countWorkOrderToBeApprovedWithoutGrant(String employeeId) {
        return getWorkOrderService().countWorkOrderToBeApprovedWithoutGrant(employeeId);
    }

    public static int countWorkOrderToBeApprovedWithGrant(String employeeId) {
        return getWorkOrderService().countWorkOrderToBeApprovedWithGrant(employeeId);
    }

    public static int countWorkOrderForProcuremenUser(String nomorwo) {
        return getWorkOrderService().countWorkOrderForProcuremenUser(nomorwo);
    }

    public static List<WorkOrder> getWorkOrderForProcurementUser(String nomorwo, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderForProcurementUser(nomorwo, limitClause, orderByClause);
    }

    public static int countWorkOrderForFinanceUser() {
        return getWorkOrderService().countWorkOrderForFinanceUser();
    }

    public static List<WorkOrder> getWorkOrderForFinanceUser(String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderForFinanceUser(limitClause, orderByClause);
    }

    public static int countNewWorkOrderFromThisUser(String employeeId) {
        return getWorkOrderService().countNewWorkOrderFromThisUser(employeeId);
    }

    public static List<WorkOrder> getNewWorkOrderFromThisUser(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getNewWorkOrderFromThisUser(employeeId, limitClause, orderByClause);
    }

    public static int countAllWorkOrderFromThisUser(String employeeId) {
        return getWorkOrderService().countAllWorkOrderFromThisUser(employeeId);
    }

    public static List<WorkOrder> getAllWorkOrderFromThisUser(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getAllWorkOrderFromThisUser(employeeId, limitClause, orderByClause);
    }

    public static void updateWorkOrderAfterCostValidation(WorkOrder workorder, String employeeId) {
        getWorkOrderService().updateAfterCostValidation(workorder, employeeId);
    }

    public static int countWorkOrderValidatedByThisUser(String employeeId) {
        return getWorkOrderService().countWorkOrderValidatedByThisUser(employeeId);
    }

    public static List<WorkOrder> getWorkOrderValidatedByThisUser(String employeeId, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderValidatedByThisUser(employeeId, limitClause, orderByClause);
    }

    public static void receivedAPWorkOrder(Long workorderPk, String employeeId) {
        getWorkOrderService().receivedAPWorkOrder(workorderPk, employeeId);
    }

    public static void receivedTreasuryWorkOrder(Long workorderPk, String employeeId) {
        getWorkOrderService().receivedTreasuryWorkOrder(workorderPk, employeeId);
    }

    public static String sendReminder(Long tworkorderPk) {
        return getWorkOrderService().sendReminder(tworkorderPk);
    }

    public static int countWorkOrderApprovedByThisUser(String employeeId, String wonumber) {
        return getWorkOrderService().countWorkOrderApprovedByThisUser(employeeId, wonumber);
    }

    public static List<WorkOrder> getWorkOrderApprovedByThisUser(String employeeId, String wonumber, String wostatus, String limitClause, String orderByClause) {
        return getWorkOrderService().getWorkOrderApprovedByThisUser(employeeId, wonumber, limitClause, orderByClause);
    }

    public static void sendCustomCostValidationMail(WorkOrder workOrder, String content) {
        getWorkOrderService().sendCustomCostValidationMail(workOrder, content);
    }

    public static void updateWorkOrderAfterChargeableWeightValidation(WorkOrder workOrder, String employeeId) {
        getWorkOrderService().updateWorkOrderAfterChargeableWeightValidation(workOrder, employeeId);
    }

    public static int countWorkOrderByExampleWithJoinForFinance(ExtendedWorkOrderExample example) {
        return getWorkOrderService().countWorkOrderByExampleWithJoinForFinance(example);
    }

    public static List<WorkOrder> getWorkOrderByExampleWithJoinForFinance(ExtendedWorkOrderExample example) {
        return getWorkOrderService().getWorkOrderByExampleWithJoinForFinance(example);
    }

    public static List<WorkOrderServiceModeDetail> getWorkOrderSubTotal(Long tworkorder_pk) {
        return getWorkOrderService().getWorkOrderSubTotal(tworkorder_pk);
    }

    public static List<WoDaManStatus> getWoDaManStatusByMan(List<String> reffNoList) {
        return getWorkOrderService().getWoDaManStatusByMan(reffNoList);
    }

    public static List<WoDaManStatus> getWoDaManStatusByDa(List<String> reffNoList) {
        return getWorkOrderService().getWoDaManStatusByDa(reffNoList);
    }

    public static List<WoDaManStatus> getWoDaManStatusByWo(List<String> reffNoList) {
        return getWorkOrderService().getWoDaManStatusByWo(reffNoList);
    }

    private static IWorkOrderService getWorkOrderService() {
        return (IWorkOrderService) getBean("workorderService");
    }
    
    public static List<WorkOrder> getWorkOrderByExampleWithJoinWithoutDa(ExtendedWorkOrderExample example) {
        return getWorkOrderService().getWorkOrderByExampleWithJoinWithoutDa(example);
    }
    
    public static void cancelWorkOrderByAdmin(Long workOrderPk, String reason, String employeeId) throws SecurityException {
        getWorkOrderService().cancelWorkOrderByAdmin(workOrderPk, reason, employeeId);
    }
    
    public static void printWorkOrderWithOutUpdate(Long workOrderPk, String employeeId) throws SecurityException {
        getWorkOrderService().printWorkOrderWithOutUpdate(workOrderPk, employeeId);
    }
}
