package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.Division;
import com.ckb.wo.client.model.DivisionExample;
import com.ckb.wo.server.dao.DivisionDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DivisionDAOImpl extends SqlMapClientDaoSupport implements DivisionDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public DivisionDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int countDivisionByExample(DivisionExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("tdivision.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int deleteDivisionByExample(DivisionExample example) {
        int rows = getSqlMapClientTemplate().delete("tdivision.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int deleteDivisionByPrimaryKey(String divisionId) {
        Division key = new Division();
        key.setDivisionId(divisionId);
        int rows = getSqlMapClientTemplate().delete("tdivision.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void insertDivision(Division record) {
        getSqlMapClientTemplate().insert("tdivision.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void insertDivisionSelective(Division record) {
        getSqlMapClientTemplate().insert("tdivision.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    @SuppressWarnings("unchecked")
    public List<Division> selectDivisionByExample(DivisionExample example) {
        List<Division> list = getSqlMapClientTemplate().queryForList("tdivision.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public Division selectDivisionByPrimaryKey(String divisionId) {
        Division key = new Division();
        key.setDivisionId(divisionId);
        Division record = (Division) getSqlMapClientTemplate().queryForObject("tdivision.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateDivisionByExampleSelective(Division record, DivisionExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tdivision.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateDivisionByExample(Division record, DivisionExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tdivision.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateDivisionByPrimaryKeySelective(Division record) {
        int rows = getSqlMapClientTemplate().update("tdivision.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateDivisionByPrimaryKey(Division record) {
        int rows = getSqlMapClientTemplate().update("tdivision.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table tdivision
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    private static class UpdateByExampleParms extends DivisionExample {
        private Object record;

        public UpdateByExampleParms(Object record, DivisionExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}