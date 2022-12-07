package com.ckb.wo.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	protected String limitClause;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public PermissionExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	protected PermissionExample(PermissionExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.limitClause = example.limitClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setLimitClause(String limitClause) {
		this.limitClause = limitClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public String getLimitClause() {
		return limitClause;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public static class Criteria {
		protected List<String> criteriaWithoutValue;
		protected List<Map<String, Object>> criteriaWithSingleValue;
		protected List<Map<String, Object>> criteriaWithListValue;
		protected List<Map<String, Object>> criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList<String>();
			criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
			criteriaWithListValue = new ArrayList<Map<String, Object>>();
			criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List<String> getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List<Map<String, Object>> getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List<Map<String, Object>> getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List<Map<String, Object>> getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition,
				List<? extends Object> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List<Object> list = new ArrayList<Object>();
			list.add(value1);
			list.add(value2);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andTpermissionPkIsNull() {
			addCriterion("tpermission_pk is null");
			return this;
		}

		public Criteria andTpermissionPkIsNotNull() {
			addCriterion("tpermission_pk is not null");
			return this;
		}

		public Criteria andTpermissionPkEqualTo(Long value) {
			addCriterion("tpermission_pk =", value, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkNotEqualTo(Long value) {
			addCriterion("tpermission_pk <>", value, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkGreaterThan(Long value) {
			addCriterion("tpermission_pk >", value, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkGreaterThanOrEqualTo(Long value) {
			addCriterion("tpermission_pk >=", value, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkLessThan(Long value) {
			addCriterion("tpermission_pk <", value, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkLessThanOrEqualTo(Long value) {
			addCriterion("tpermission_pk <=", value, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkIn(List<Long> values) {
			addCriterion("tpermission_pk in", values, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkNotIn(List<Long> values) {
			addCriterion("tpermission_pk not in", values, "tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkBetween(Long value1, Long value2) {
			addCriterion("tpermission_pk between", value1, value2,
					"tpermissionPk");
			return this;
		}

		public Criteria andTpermissionPkNotBetween(Long value1, Long value2) {
			addCriterion("tpermission_pk not between", value1, value2,
					"tpermissionPk");
			return this;
		}

		public Criteria andPermissioncodeIsNull() {
			addCriterion("permissioncode is null");
			return this;
		}

		public Criteria andPermissioncodeIsNotNull() {
			addCriterion("permissioncode is not null");
			return this;
		}

		public Criteria andPermissioncodeEqualTo(String value) {
			addCriterion("permissioncode =", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeNotEqualTo(String value) {
			addCriterion("permissioncode <>", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeGreaterThan(String value) {
			addCriterion("permissioncode >", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeGreaterThanOrEqualTo(String value) {
			addCriterion("permissioncode >=", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeLessThan(String value) {
			addCriterion("permissioncode <", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeLessThanOrEqualTo(String value) {
			addCriterion("permissioncode <=", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeLike(String value) {
			addCriterion("permissioncode like", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeNotLike(String value) {
			addCriterion("permissioncode not like", value, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeIn(List<String> values) {
			addCriterion("permissioncode in", values, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeNotIn(List<String> values) {
			addCriterion("permissioncode not in", values, "permissioncode");
			return this;
		}

		public Criteria andPermissioncodeBetween(String value1, String value2) {
			addCriterion("permissioncode between", value1, value2,
					"permissioncode");
			return this;
		}

		public Criteria andPermissioncodeNotBetween(String value1, String value2) {
			addCriterion("permissioncode not between", value1, value2,
					"permissioncode");
			return this;
		}

		public Criteria andPermissiondescIsNull() {
			addCriterion("permissiondesc is null");
			return this;
		}

		public Criteria andPermissiondescIsNotNull() {
			addCriterion("permissiondesc is not null");
			return this;
		}

		public Criteria andPermissiondescEqualTo(String value) {
			addCriterion("permissiondesc =", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescNotEqualTo(String value) {
			addCriterion("permissiondesc <>", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescGreaterThan(String value) {
			addCriterion("permissiondesc >", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescGreaterThanOrEqualTo(String value) {
			addCriterion("permissiondesc >=", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescLessThan(String value) {
			addCriterion("permissiondesc <", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescLessThanOrEqualTo(String value) {
			addCriterion("permissiondesc <=", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescLike(String value) {
			addCriterion("permissiondesc like", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescNotLike(String value) {
			addCriterion("permissiondesc not like", value, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescIn(List<String> values) {
			addCriterion("permissiondesc in", values, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescNotIn(List<String> values) {
			addCriterion("permissiondesc not in", values, "permissiondesc");
			return this;
		}

		public Criteria andPermissiondescBetween(String value1, String value2) {
			addCriterion("permissiondesc between", value1, value2,
					"permissiondesc");
			return this;
		}

		public Criteria andPermissiondescNotBetween(String value1, String value2) {
			addCriterion("permissiondesc not between", value1, value2,
					"permissiondesc");
			return this;
		}
	}
}