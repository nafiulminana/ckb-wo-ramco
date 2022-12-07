package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.WorkOrderHistory;
import com.ckb.wo.client.model.WorkOrderHistoryExample;
import com.ckb.wo.server.dao.WorkOrderHistoryDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class WorkOrderHistoryDAOImpl extends SqlMapClientDaoSupport implements WorkOrderHistoryDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public WorkOrderHistoryDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param example
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int countWorkOrderHistoryByExample(WorkOrderHistoryExample example) {
        Integer count = (Integer) getSqlMapClientTemplate()
                .queryForObject("tworkorderhistory.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param example
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int deleteWorkOrderHistoryByExample(WorkOrderHistoryExample example) {
        int rows = getSqlMapClientTemplate()
                .delete("tworkorderhistory.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param tworkorderhistoryPk
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int deleteWorkOrderHistoryByPrimaryKey(Long tworkorderhistoryPk) {
        WorkOrderHistory key = new WorkOrderHistory();
        key.setTworkorderhistoryPk(tworkorderhistoryPk);
        int rows = getSqlMapClientTemplate()
                .delete("tworkorderhistory.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param record
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public Long insertWorkOrderHistory(WorkOrderHistory record) {
        Object newKey = getSqlMapClientTemplate()
                .insert("tworkorderhistory.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param record
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public Long insertWorkOrderHistorySelective(WorkOrderHistory record) {
        Object newKey = getSqlMapClientTemplate()
                .insert("tworkorderhistory.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param example
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkOrderHistory> selectWorkOrderHistoryByExample(WorkOrderHistoryExample example) {
        List<WorkOrderHistory> list = getSqlMapClientTemplate()
                .queryForList("tworkorderhistory.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param tworkorderhistoryPk
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public WorkOrderHistory selectWorkOrderHistoryByPrimaryKey(Long tworkorderhistoryPk) {
        WorkOrderHistory key = new WorkOrderHistory();
        key.setTworkorderhistoryPk(tworkorderhistoryPk);
        WorkOrderHistory record = (WorkOrderHistory) getSqlMapClientTemplate()
                .queryForObject("tworkorderhistory.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int updateWorkOrderHistoryByExampleSelective(WorkOrderHistory record, WorkOrderHistoryExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate()
                .update("tworkorderhistory.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int updateWorkOrderHistoryByExample(WorkOrderHistory record, WorkOrderHistoryExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate()
                .update("tworkorderhistory.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param record
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int updateWorkOrderHistoryByPrimaryKeySelective(WorkOrderHistory record) {
        int rows = getSqlMapClientTemplate()
                .update("tworkorderhistory.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderhistory
     *
     * @param record
     * @return
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int updateWorkOrderHistoryByPrimaryKey(WorkOrderHistory record) {
        int rows = getSqlMapClientTemplate()
                .update("tworkorderhistory.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator. This class corresponds
     * to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private static class UpdateByExampleParms extends WorkOrderHistoryExample {

        private Object record;

        public UpdateByExampleParms(Object record, WorkOrderHistoryExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
