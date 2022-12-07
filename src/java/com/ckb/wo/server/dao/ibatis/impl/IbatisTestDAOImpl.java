package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.IbatisTest;
import com.ckb.wo.client.model.IbatisTestExample;
import com.ckb.wo.server.dao.IbatisTestDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IbatisTestDAOImpl extends SqlMapClientDaoSupport implements IbatisTestDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public IbatisTestDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int countIbatisTestByExample(IbatisTestExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ibatistest.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	public int deleteIbatisTestByExample(IbatisTestExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"ibatistest.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	public void insertIbatisTest(IbatisTest record) {
		getSqlMapClientTemplate().insert("ibatistest.ibatorgenerated_insert",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	public void insertIbatisTestSelective(IbatisTest record) {
		getSqlMapClientTemplate().insert(
				"ibatistest.ibatorgenerated_insertSelective", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	@SuppressWarnings("unchecked")
	public List<IbatisTest> selectIbatisTestByExample(IbatisTestExample example) {
		List<IbatisTest> list = getSqlMapClientTemplate().queryForList(
				"ibatistest.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	public int updateIbatisTestByExampleSelective(IbatisTest record,
			IbatisTestExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ibatistest.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	public int updateIbatisTestByExample(IbatisTest record,
			IbatisTestExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ibatistest.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table ibatistest
	 * @ibatorgenerated  Tue Jun 29 17:37:53 SGT 2010
	 */
	private static class UpdateByExampleParms extends IbatisTestExample {
		private Object record;

		public UpdateByExampleParms(Object record, IbatisTestExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}