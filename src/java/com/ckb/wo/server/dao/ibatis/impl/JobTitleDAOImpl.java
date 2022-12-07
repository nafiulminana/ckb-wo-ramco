package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.JobTitle;
import com.ckb.wo.client.model.JobTitleExample;
import com.ckb.wo.server.dao.JobTitleDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class JobTitleDAOImpl extends SqlMapClientDaoSupport implements JobTitleDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public JobTitleDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int countJobTitleByExample(JobTitleExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("tjobtitle.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int deleteJobTitleByExample(JobTitleExample example) {
        int rows = getSqlMapClientTemplate().delete("tjobtitle.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int deleteJobTitleByPrimaryKey(String jobTitleId) {
        JobTitle key = new JobTitle();
        key.setJobTitleId(jobTitleId);
        int rows = getSqlMapClientTemplate().delete("tjobtitle.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void insertJobTitle(JobTitle record) {
        getSqlMapClientTemplate().insert("tjobtitle.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void insertJobTitleSelective(JobTitle record) {
        getSqlMapClientTemplate().insert("tjobtitle.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    @SuppressWarnings("unchecked")
    public List<JobTitle> selectJobTitleByExample(JobTitleExample example) {
        List<JobTitle> list = getSqlMapClientTemplate().queryForList("tjobtitle.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public JobTitle selectJobTitleByPrimaryKey(String jobTitleId) {
        JobTitle key = new JobTitle();
        key.setJobTitleId(jobTitleId);
        JobTitle record = (JobTitle) getSqlMapClientTemplate().queryForObject("tjobtitle.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateJobTitleByExampleSelective(JobTitle record, JobTitleExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tjobtitle.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateJobTitleByExample(JobTitle record, JobTitleExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tjobtitle.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateJobTitleByPrimaryKeySelective(JobTitle record) {
        int rows = getSqlMapClientTemplate().update("tjobtitle.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateJobTitleByPrimaryKey(JobTitle record) {
        int rows = getSqlMapClientTemplate().update("tjobtitle.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table tjobtitle
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    private static class UpdateByExampleParms extends JobTitleExample {
        private Object record;

        public UpdateByExampleParms(Object record, JobTitleExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}