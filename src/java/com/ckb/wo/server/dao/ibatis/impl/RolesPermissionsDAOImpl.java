package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.client.model.RolesPermissions;
import com.ckb.wo.client.model.RolesPermissionsExample;
import com.ckb.wo.server.dao.RolesPermissionsDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RolesPermissionsDAOImpl extends SqlMapClientDaoSupport implements RolesPermissionsDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public RolesPermissionsDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int countRolesPermissionsByExample(RolesPermissionsExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"trole_tpermission.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int deleteRolesPermissionsByExample(RolesPermissionsExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"trole_tpermission.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int deleteRolesPermissionsByPrimaryKey(Long troleTpermissionPk) {
		RolesPermissions key = new RolesPermissions();
		key.setTroleTpermissionPk(troleTpermissionPk);
		int rows = getSqlMapClientTemplate().delete(
				"trole_tpermission.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Long insertRolesPermissions(RolesPermissions record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"trole_tpermission.ibatorgenerated_insert", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Long insertRolesPermissionsSelective(RolesPermissions record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"trole_tpermission.ibatorgenerated_insertSelective", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	@SuppressWarnings("unchecked")
	public List<RolesPermissions> selectRolesPermissionsByExample(
			RolesPermissionsExample example) {
		List<RolesPermissions> list = getSqlMapClientTemplate().queryForList(
				"trole_tpermission.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public RolesPermissions selectRolesPermissionsByPrimaryKey(
			Long troleTpermissionPk) {
		RolesPermissions key = new RolesPermissions();
		key.setTroleTpermissionPk(troleTpermissionPk);
		RolesPermissions record = (RolesPermissions) getSqlMapClientTemplate()
				.queryForObject(
						"trole_tpermission.ibatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int updateRolesPermissionsByExampleSelective(
			RolesPermissions record, RolesPermissionsExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"trole_tpermission.ibatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int updateRolesPermissionsByExample(RolesPermissions record,
			RolesPermissionsExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"trole_tpermission.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int updateRolesPermissionsByPrimaryKeySelective(
			RolesPermissions record) {
		int rows = getSqlMapClientTemplate()
				.update(
						"trole_tpermission.ibatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public int updateRolesPermissionsByPrimaryKey(RolesPermissions record) {
		int rows = getSqlMapClientTemplate().update(
				"trole_tpermission.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private static class UpdateByExampleParms extends RolesPermissionsExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				RolesPermissionsExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}