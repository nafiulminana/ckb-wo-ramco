package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.ExtendedWorkOrderFlowExample;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.client.model.WorkOrderFlowExample;
import com.ckb.wo.server.dao.WorkOrderFlowDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class WorkOrderFlowDAOImpl extends SqlMapClientDaoSupport implements WorkOrderFlowDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public WorkOrderFlowDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int countWorkOrderFlowByExample(WorkOrderFlowExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tworkorderflow.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int deleteWorkOrderFlowByExample(WorkOrderFlowExample example) {
        int rows = getSqlMapClientTemplate().delete("tworkorderflow.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param tworkorderflowPk
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int deleteWorkOrderFlowByPrimaryKey(Long tworkorderflowPk) {
        WorkOrderFlow key = new WorkOrderFlow();
        key.setTworkorderflowPk(tworkorderflowPk);
        int rows = getSqlMapClientTemplate().delete("tworkorderflow.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public Long insertWorkOrderFlow(WorkOrderFlow record) {
        Object newKey = getSqlMapClientTemplate().insert("tworkorderflow.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public Long insertWorkOrderFlowSelective(WorkOrderFlow record) {
        Object newKey = getSqlMapClientTemplate().insert("tworkorderflow.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkOrderFlow> selectWorkOrderFlowByExample(WorkOrderFlowExample example) {
        List<WorkOrderFlow> list = getSqlMapClientTemplate().queryForList("tworkorderflow.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param tworkorderflowPk
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public WorkOrderFlow selectWorkOrderFlowByPrimaryKey(Long tworkorderflowPk) {
        WorkOrderFlow key = new WorkOrderFlow();
        key.setTworkorderflowPk(tworkorderflowPk);
        WorkOrderFlow record = (WorkOrderFlow) getSqlMapClientTemplate().queryForObject("tworkorderflow.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int updateWorkOrderFlowByExampleSelective(WorkOrderFlow record, WorkOrderFlowExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tworkorderflow.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int updateWorkOrderFlowByExample(WorkOrderFlow record, WorkOrderFlowExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tworkorderflow.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int updateWorkOrderFlowByPrimaryKeySelective(WorkOrderFlow record) {
        int rows = getSqlMapClientTemplate().update("tworkorderflow.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param record
     * @return
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int updateWorkOrderFlowByPrimaryKey(WorkOrderFlow record) {
        int rows = getSqlMapClientTemplate().update("tworkorderflow.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator. This class corresponds
     * to the database table tworkorderflow
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private static class UpdateByExampleParms extends WorkOrderFlowExample {

        private Object record;

        public UpdateByExampleParms(Object record, WorkOrderFlowExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

    // custom method
    @Override
    public List<Long> getWorkOrderValidatedByThisUser(String employeeId) {
        WorkOrderFlow woFlow = new WorkOrderFlow();
        woFlow.setEmployeeid(employeeId);
        woFlow.setOldwostatus(WorkOrder.VALIDATION_STATUS);
        return getSqlMapClientTemplate().queryForList("tworkorderflow.getWorkOrderValidatedByThisUser", woFlow);
    }

    @Override
    public List<WorkOrderFlow> selectApprovalHistoryForThisWorkOrder(Long workorderPk) {
        ExtendedWorkOrderFlowExample example = new ExtendedWorkOrderFlowExample();
        ExtendedWorkOrderFlowExample.ExtendedCriteria criteria = (ExtendedWorkOrderFlowExample.ExtendedCriteria) example.createCriteria();
        criteria.andTworkorderFkEqualTo(workorderPk);
        criteria.andLevelValueGreaterThanOrEqualTo(1);
        example.setOrderByClause("tworkorderflow_pk asc");
        return getSqlMapClientTemplate().queryForList("tworkorderflow.getApprovalHistoryForThisWorkOrder", example);
    }
}
