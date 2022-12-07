package com.ckb.wo.server.dao.fast.ibatis.impl;

import com.ckb.wo.client.model.FastUser;
import com.ckb.wo.client.model.FastUserExample;
import com.ckb.wo.server.dao.fast.FastUserDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class FastUserDAOImpl extends SqlMapClientDaoSupport implements FastUserDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public FastUserDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int countFastUserByExample(FastUserExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("t_personel.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int deleteFastUserByExample(FastUserExample example) {
        int rows = getSqlMapClientTemplate().delete("t_personel.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int deleteFastUserByPrimaryKey(String employeeId) {
        FastUser key = new FastUser();
        key.setEmployeeId(employeeId);
        int rows = getSqlMapClientTemplate().delete("t_personel.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public void insertFastUser(FastUser record) {
        getSqlMapClientTemplate().insert("t_personel.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public void insertFastUserSelective(FastUser record) {
        getSqlMapClientTemplate().insert("t_personel.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    @SuppressWarnings("unchecked")
    public List<FastUser> selectFastUserByExample(FastUserExample example) {
        List<FastUser> list = getSqlMapClientTemplate().queryForList("t_personel.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public FastUser selectFastUserByPrimaryKey(String employeeId) {
        FastUser key = new FastUser();
        key.setEmployeeId(employeeId);
        FastUser record = (FastUser) getSqlMapClientTemplate().queryForObject("t_personel.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int updateFastUserByExampleSelective(FastUser record, FastUserExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_personel.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int updateFastUserByExample(FastUser record, FastUserExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_personel.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int updateFastUserByPrimaryKeySelective(FastUser record) {
        int rows = getSqlMapClientTemplate().update("t_personel.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public int updateFastUserByPrimaryKey(FastUser record) {
        int rows = getSqlMapClientTemplate().update("t_personel.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table t_personel
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    private static class UpdateByExampleParms extends FastUserExample {
        private Object record;

        public UpdateByExampleParms(Object record, FastUserExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}