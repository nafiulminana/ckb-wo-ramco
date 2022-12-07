package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.ServiceType;
import com.ckb.wo.client.model.ServiceTypeExample;
import com.ckb.wo.server.dao.ServiceTypeDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ServiceTypeDAOImpl extends SqlMapClientDaoSupport implements ServiceTypeDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public ServiceTypeDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int countServiceTypeByExample(ServiceTypeExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tservicetype.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int deleteServiceTypeByExample(ServiceTypeExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"tservicetype.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int deleteServiceTypeByPrimaryKey(Long tservicetypePk) {
		ServiceType key = new ServiceType();
		key.setTservicetypePk(tservicetypePk);
		int rows = getSqlMapClientTemplate().delete(
				"tservicetype.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public Long insertServiceType(ServiceType record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"tservicetype.ibatorgenerated_insert", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public Long insertServiceTypeSelective(ServiceType record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"tservicetype.ibatorgenerated_insertSelective", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceType> selectServiceTypeByExample(
			ServiceTypeExample example) {
		List<ServiceType> list = getSqlMapClientTemplate().queryForList(
				"tservicetype.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public ServiceType selectServiceTypeByPrimaryKey(Long tservicetypePk) {
		ServiceType key = new ServiceType();
		key.setTservicetypePk(tservicetypePk);
		ServiceType record = (ServiceType) getSqlMapClientTemplate()
				.queryForObject(
						"tservicetype.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int updateServiceTypeByExampleSelective(ServiceType record,
			ServiceTypeExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"tservicetype.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int updateServiceTypeByExample(ServiceType record,
			ServiceTypeExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"tservicetype.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int updateServiceTypeByPrimaryKeySelective(ServiceType record) {
		int rows = getSqlMapClientTemplate().update(
				"tservicetype.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public int updateServiceTypeByPrimaryKey(ServiceType record) {
		int rows = getSqlMapClientTemplate().update(
				"tservicetype.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private static class UpdateByExampleParms extends ServiceTypeExample {
		private Object record;

		public UpdateByExampleParms(Object record, ServiceTypeExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}