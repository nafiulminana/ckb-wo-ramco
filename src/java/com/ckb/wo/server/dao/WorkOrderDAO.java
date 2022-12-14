package com.ckb.wo.server.dao;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.WoDaManStatus;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderExample;
import java.util.List;

public interface WorkOrderDAO {

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int extendedCountWorkOrderByExample(ExtendedWorkOrderExample example);

    int countWorkOrderByExample(WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int deleteWorkOrderByExample(WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param tworkorderPk
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int deleteWorkOrderByPrimaryKey(Long tworkorderPk);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    Long insertWorkOrder(WorkOrder record);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    Long insertWorkOrderSelective(WorkOrder record);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    List<WorkOrder> selectWorkOrderByExampleWithBLOBs(WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    List<WorkOrder> selectWorkOrderByExampleWithoutBLOBs(WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param tworkorderPk
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    WorkOrder selectWorkOrderByPrimaryKey(Long tworkorderPk);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int updateWorkOrderByExampleSelective(WorkOrder record, WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int updateWorkOrderByExampleWithBLOBs(WorkOrder record, WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int updateWorkOrderByExampleWithoutBLOBs(WorkOrder record, WorkOrderExample example);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int updateWorkOrderByPrimaryKeySelective(WorkOrder record);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int updateWorkOrderByPrimaryKeyWithBLOBs(WorkOrder record);

    /**
     * This method was generated by Apache iBATIS ibator.This method corresponds
     * to the database table tworkorder
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 02 20:50:40 SGT 2010
     */
    int updateWorkOrderByPrimaryKeyWithoutBLOBs(WorkOrder record);

    public List<Long> selectWorkOrderPKOnlyByExample(ExtendedWorkOrderExample example);

    public List<WorkOrder> selectWorkOrderByExampleWithJoin(ExtendedWorkOrderExample example);

    public WorkOrder selectWorkOrderByPrimaryKeyWithJoin(Long tworkorderPk);

    public List<WorkOrder> selectWorkOrderByExampleWithJoinToFast(WorkOrderExample example);

    public WorkOrder selectWorkOrderByPrimaryKeyWithJoinToFast(Long tworkorderPk);

    public List<WorkOrder> selectWorkOrderApprovedByThisUser(String employeeId, String wonumber, String limitClause, String orderByClause);

    public Integer countWorkOrderApprovedByThisUser(String employeeId, String wonumber);

    public Integer countWorkOrderValidatedByThisUser(String employeeId);

    public List<WorkOrder> selectWorkOrderValidatedByThisUser(String employeeId, String orderByClause, String limitClause);

    public List<WorkOrder> selectByExampleWithJoinForFinance(ExtendedWorkOrderExample example);

    public Integer countByExampleWithJoinForFinance(ExtendedWorkOrderExample example);

    List<WoDaManStatus> selectWoDaManStatusByMan(List<String> reffNoList);
    
    List<WoDaManStatus> selectWoDaManStatusByDa(List<String> reffNoList);

    List<WoDaManStatus> selectWoDaManStatusByWo(List<String> reffNoList);
    
    public List<WorkOrder> selectWorkOrderByExampleWithJoinWithoutDa(ExtendedWorkOrderExample example);
}
