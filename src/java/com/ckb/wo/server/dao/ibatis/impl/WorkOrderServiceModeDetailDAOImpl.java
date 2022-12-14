package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.client.model.WorkOrderServiceModeDetailExample;
import com.ckb.wo.server.dao.WorkOrderServiceModeDetailDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class WorkOrderServiceModeDetailDAOImpl extends SqlMapClientDaoSupport implements WorkOrderServiceModeDetailDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    public WorkOrderServiceModeDetailDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param example
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int countWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example) {
        Integer count = (Integer) getSqlMapClientTemplate()
                .queryForObject("tworkorder_smodedetail.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param example
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int deleteWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example) {
        int rows = getSqlMapClientTemplate()
                .delete("tworkorder_smodedetail.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param tworkorderSmodedetailPk
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int deleteWorkOrderServiceModeDetailByPrimaryKey(Long tworkorderSmodedetailPk) {
        WorkOrderServiceModeDetail key = new WorkOrderServiceModeDetail();
        key.setTworkorderSmodedetailPk(tworkorderSmodedetailPk);
        int rows = getSqlMapClientTemplate()
                .delete("tworkorder_smodedetail.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param record
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public Long insertWorkOrderServiceModeDetail(WorkOrderServiceModeDetail record) {
        Object newKey = getSqlMapClientTemplate()
                .insert("tworkorder_smodedetail.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param record
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public Long insertWorkOrderServiceModeDetailSelective(WorkOrderServiceModeDetail record) {
        Object newKey = getSqlMapClientTemplate()
                .insert("tworkorder_smodedetail.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param example
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkOrderServiceModeDetail> selectWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetailExample example) {
        List<WorkOrderServiceModeDetail> list = getSqlMapClientTemplate()
                .queryForList("tworkorder_smodedetail.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param tworkorderSmodedetailPk
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public WorkOrderServiceModeDetail selectWorkOrderServiceModeDetailByPrimaryKey(Long tworkorderSmodedetailPk) {
        WorkOrderServiceModeDetail key = new WorkOrderServiceModeDetail();
        key.setTworkorderSmodedetailPk(tworkorderSmodedetailPk);
        WorkOrderServiceModeDetail record = (WorkOrderServiceModeDetail) getSqlMapClientTemplate()
                .queryForObject("tworkorder_smodedetail.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int updateWorkOrderServiceModeDetailByExampleSelective(WorkOrderServiceModeDetail record, WorkOrderServiceModeDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate()
                .update("tworkorder_smodedetail.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int updateWorkOrderServiceModeDetailByExample(WorkOrderServiceModeDetail record, WorkOrderServiceModeDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate()
                .update("tworkorder_smodedetail.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param record
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int updateWorkOrderServiceModeDetailByPrimaryKeySelective(WorkOrderServiceModeDetail record) {
        int rows = getSqlMapClientTemplate()
                .update("tworkorder_smodedetail.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_smodedetail
     *
     * @param record
     * @return
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int updateWorkOrderServiceModeDetailByPrimaryKey(WorkOrderServiceModeDetail record) {
        int rows = getSqlMapClientTemplate()
                .update("tworkorder_smodedetail.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator. This class corresponds
     * to the database table tworkorder_smodedetail
     *
     * @ibatorgenerated Thu Aug 26 15:08:34 SGT 2010
     */
    private static class UpdateByExampleParms extends
            WorkOrderServiceModeDetailExample {

        private Object record;

        public UpdateByExampleParms(Object record,
                WorkOrderServiceModeDetailExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

    @Override
    public List<WorkOrderServiceModeDetail> selectForSubTotal(Long tworkorder_fk) {
        return getSqlMapClientTemplate()
                .queryForList("tworkorder_smodedetail.selectForSubTotal", tworkorder_fk);
    }
}
