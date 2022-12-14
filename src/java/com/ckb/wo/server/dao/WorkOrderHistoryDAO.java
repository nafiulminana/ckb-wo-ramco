package com.ckb.wo.server.dao;

import com.ckb.wo.client.model.WorkOrderHistory;
import com.ckb.wo.client.model.WorkOrderHistoryExample;
import java.util.List;

public interface WorkOrderHistoryDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int countWorkOrderHistoryByExample(WorkOrderHistoryExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int deleteWorkOrderHistoryByExample(WorkOrderHistoryExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int deleteWorkOrderHistoryByPrimaryKey(Long tworkorderhistoryPk);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    Long insertWorkOrderHistory(WorkOrderHistory record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    Long insertWorkOrderHistorySelective(WorkOrderHistory record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    List<WorkOrderHistory> selectWorkOrderHistoryByExample(WorkOrderHistoryExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    WorkOrderHistory selectWorkOrderHistoryByPrimaryKey(Long tworkorderhistoryPk);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int updateWorkOrderHistoryByExampleSelective(WorkOrderHistory record, WorkOrderHistoryExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int updateWorkOrderHistoryByExample(WorkOrderHistory record, WorkOrderHistoryExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int updateWorkOrderHistoryByPrimaryKeySelective(WorkOrderHistory record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    int updateWorkOrderHistoryByPrimaryKey(WorkOrderHistory record);
}