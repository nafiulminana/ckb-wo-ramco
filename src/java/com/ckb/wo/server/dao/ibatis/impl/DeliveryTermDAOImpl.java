package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.DeliveryTerm;
import com.ckb.wo.client.model.DeliveryTermExample;
import com.ckb.wo.server.dao.DeliveryTermDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DeliveryTermDAOImpl extends SqlMapClientDaoSupport implements DeliveryTermDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public DeliveryTermDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int countDeliveryTermByExample(DeliveryTermExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"tdeliveryterm.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int deleteDeliveryTermByExample(DeliveryTermExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"tdeliveryterm.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int deleteDeliveryTermByPrimaryKey(Long tdeliverytermPk) {
		DeliveryTerm key = new DeliveryTerm();
		key.setTdeliverytermPk(tdeliverytermPk);
		int rows = getSqlMapClientTemplate().delete(
				"tdeliveryterm.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public Long insertDeliveryTerm(DeliveryTerm record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"tdeliveryterm.ibatorgenerated_insert", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public Long insertDeliveryTermSelective(DeliveryTerm record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"tdeliveryterm.ibatorgenerated_insertSelective", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	@SuppressWarnings("unchecked")
	public List<DeliveryTerm> selectDeliveryTermByExample(
			DeliveryTermExample example) {
		List<DeliveryTerm> list = getSqlMapClientTemplate().queryForList(
				"tdeliveryterm.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public DeliveryTerm selectDeliveryTermByPrimaryKey(Long tdeliverytermPk) {
		DeliveryTerm key = new DeliveryTerm();
		key.setTdeliverytermPk(tdeliverytermPk);
		DeliveryTerm record = (DeliveryTerm) getSqlMapClientTemplate()
				.queryForObject(
						"tdeliveryterm.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int updateDeliveryTermByExampleSelective(DeliveryTerm record,
			DeliveryTermExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update(
						"tdeliveryterm.ibatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int updateDeliveryTermByExample(DeliveryTerm record,
			DeliveryTermExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"tdeliveryterm.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int updateDeliveryTermByPrimaryKeySelective(DeliveryTerm record) {
		int rows = getSqlMapClientTemplate().update(
				"tdeliveryterm.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public int updateDeliveryTermByPrimaryKey(DeliveryTerm record) {
		int rows = getSqlMapClientTemplate().update(
				"tdeliveryterm.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table tdeliveryterm
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	private static class UpdateByExampleParms extends DeliveryTermExample {
		private Object record;

		public UpdateByExampleParms(Object record, DeliveryTermExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}