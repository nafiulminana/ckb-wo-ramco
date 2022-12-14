package com.ckb.wo.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceModeExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	protected String limitClause;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public ServiceModeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	protected ServiceModeExample(ServiceModeExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.limitClause = example.limitClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setLimitClause(String limitClause) {
		this.limitClause = limitClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getLimitClause() {
		return limitClause;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table tservicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
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

		public Criteria andTservicemodePkIsNull() {
			addCriterion("tservicemode_pk is null");
			return this;
		}

		public Criteria andTservicemodePkIsNotNull() {
			addCriterion("tservicemode_pk is not null");
			return this;
		}

		public Criteria andTservicemodePkEqualTo(Long value) {
			addCriterion("tservicemode_pk =", value, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkNotEqualTo(Long value) {
			addCriterion("tservicemode_pk <>", value, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkGreaterThan(Long value) {
			addCriterion("tservicemode_pk >", value, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkGreaterThanOrEqualTo(Long value) {
			addCriterion("tservicemode_pk >=", value, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkLessThan(Long value) {
			addCriterion("tservicemode_pk <", value, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkLessThanOrEqualTo(Long value) {
			addCriterion("tservicemode_pk <=", value, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkIn(List<Long> values) {
			addCriterion("tservicemode_pk in", values, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkNotIn(List<Long> values) {
			addCriterion("tservicemode_pk not in", values, "tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkBetween(Long value1, Long value2) {
			addCriterion("tservicemode_pk between", value1, value2,
					"tservicemodePk");
			return this;
		}

		public Criteria andTservicemodePkNotBetween(Long value1, Long value2) {
			addCriterion("tservicemode_pk not between", value1, value2,
					"tservicemodePk");
			return this;
		}

		public Criteria andSmodecodeIsNull() {
			addCriterion("smodecode is null");
			return this;
		}

		public Criteria andSmodecodeIsNotNull() {
			addCriterion("smodecode is not null");
			return this;
		}

		public Criteria andSmodecodeEqualTo(String value) {
			addCriterion("smodecode =", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeNotEqualTo(String value) {
			addCriterion("smodecode <>", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeGreaterThan(String value) {
			addCriterion("smodecode >", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeGreaterThanOrEqualTo(String value) {
			addCriterion("smodecode >=", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeLessThan(String value) {
			addCriterion("smodecode <", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeLessThanOrEqualTo(String value) {
			addCriterion("smodecode <=", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeLike(String value) {
			addCriterion("smodecode like", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeNotLike(String value) {
			addCriterion("smodecode not like", value, "smodecode");
			return this;
		}

		public Criteria andSmodecodeIn(List<String> values) {
			addCriterion("smodecode in", values, "smodecode");
			return this;
		}

		public Criteria andSmodecodeNotIn(List<String> values) {
			addCriterion("smodecode not in", values, "smodecode");
			return this;
		}

		public Criteria andSmodecodeBetween(String value1, String value2) {
			addCriterion("smodecode between", value1, value2, "smodecode");
			return this;
		}

		public Criteria andSmodecodeNotBetween(String value1, String value2) {
			addCriterion("smodecode not between", value1, value2, "smodecode");
			return this;
		}

		public Criteria andSmodenameIsNull() {
			addCriterion("smodename is null");
			return this;
		}

		public Criteria andSmodenameIsNotNull() {
			addCriterion("smodename is not null");
			return this;
		}

		public Criteria andSmodenameEqualTo(String value) {
			addCriterion("smodename =", value, "smodename");
			return this;
		}

		public Criteria andSmodenameNotEqualTo(String value) {
			addCriterion("smodename <>", value, "smodename");
			return this;
		}

		public Criteria andSmodenameGreaterThan(String value) {
			addCriterion("smodename >", value, "smodename");
			return this;
		}

		public Criteria andSmodenameGreaterThanOrEqualTo(String value) {
			addCriterion("smodename >=", value, "smodename");
			return this;
		}

		public Criteria andSmodenameLessThan(String value) {
			addCriterion("smodename <", value, "smodename");
			return this;
		}

		public Criteria andSmodenameLessThanOrEqualTo(String value) {
			addCriterion("smodename <=", value, "smodename");
			return this;
		}

		public Criteria andSmodenameLike(String value) {
			addCriterion("smodename like", value, "smodename");
			return this;
		}

		public Criteria andSmodenameNotLike(String value) {
			addCriterion("smodename not like", value, "smodename");
			return this;
		}

		public Criteria andSmodenameIn(List<String> values) {
			addCriterion("smodename in", values, "smodename");
			return this;
		}

		public Criteria andSmodenameNotIn(List<String> values) {
			addCriterion("smodename not in", values, "smodename");
			return this;
		}

		public Criteria andSmodenameBetween(String value1, String value2) {
			addCriterion("smodename between", value1, value2, "smodename");
			return this;
		}

		public Criteria andSmodenameNotBetween(String value1, String value2) {
			addCriterion("smodename not between", value1, value2, "smodename");
			return this;
		}

		public Criteria andTservicetypeFkIsNull() {
			addCriterion("tservicetype_fk is null");
			return this;
		}

		public Criteria andTservicetypeFkIsNotNull() {
			addCriterion("tservicetype_fk is not null");
			return this;
		}

		public Criteria andTservicetypeFkEqualTo(Long value) {
			addCriterion("tservicetype_fk =", value, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkNotEqualTo(Long value) {
			addCriterion("tservicetype_fk <>", value, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkGreaterThan(Long value) {
			addCriterion("tservicetype_fk >", value, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkGreaterThanOrEqualTo(Long value) {
			addCriterion("tservicetype_fk >=", value, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkLessThan(Long value) {
			addCriterion("tservicetype_fk <", value, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkLessThanOrEqualTo(Long value) {
			addCriterion("tservicetype_fk <=", value, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkIn(List<Long> values) {
			addCriterion("tservicetype_fk in", values, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkNotIn(List<Long> values) {
			addCriterion("tservicetype_fk not in", values, "tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkBetween(Long value1, Long value2) {
			addCriterion("tservicetype_fk between", value1, value2,
					"tservicetypeFk");
			return this;
		}

		public Criteria andTservicetypeFkNotBetween(Long value1, Long value2) {
			addCriterion("tservicetype_fk not between", value1, value2,
					"tservicetypeFk");
			return this;
		}
	}
}