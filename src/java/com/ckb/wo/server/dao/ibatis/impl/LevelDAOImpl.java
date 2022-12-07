package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.LevelExample;
import com.ckb.wo.server.dao.LevelDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class LevelDAOImpl extends SqlMapClientDaoSupport implements LevelDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public LevelDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int countLevelByExample(LevelExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("tlevel.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int deleteLevelByExample(LevelExample example) {
        int rows = getSqlMapClientTemplate().delete("tlevel.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int deleteLevelByPrimaryKey(String levelId) {
        Level key = new Level();
        key.setLevelId(levelId);
        int rows = getSqlMapClientTemplate().delete("tlevel.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void insertLevel(Level record) {
        getSqlMapClientTemplate().insert("tlevel.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void insertLevelSelective(Level record) {
        getSqlMapClientTemplate().insert("tlevel.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    @SuppressWarnings("unchecked")
    public List<Level> selectLevelByExample(LevelExample example) {
        List<Level> list = getSqlMapClientTemplate().queryForList("tlevel.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public Level selectLevelByPrimaryKey(String levelId) {
        Level key = new Level();
        key.setLevelId(levelId);
        Level record = (Level) getSqlMapClientTemplate().queryForObject("tlevel.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateLevelByExampleSelective(Level record, LevelExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tlevel.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateLevelByExample(Level record, LevelExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tlevel.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateLevelByPrimaryKeySelective(Level record) {
        int rows = getSqlMapClientTemplate().update("tlevel.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public int updateLevelByPrimaryKey(Level record) {
        int rows = getSqlMapClientTemplate().update("tlevel.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table tlevel
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    private static class UpdateByExampleParms extends LevelExample {
        private Object record;

        public UpdateByExampleParms(Object record, LevelExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    public List<Level> selectApprovalTypeWithContent(int maxLevel){    	
    	LevelExample example = new LevelExample();
    	example.createCriteria().andLevelValueBetween(1, maxLevel);
    	example.setOrderByClause("level_value asc");
    	return selectLevelByExample(example);
    } 
}