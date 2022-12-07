package com.ckb.wo.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MdaManifestDetailExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	protected String limitClause;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public MdaManifestDetailExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public void setLimitClause(String limitClause) {
		this.limitClause = limitClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
	 */
	public String getLimitClause() {
		return limitClause;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table t_manifest_detail
	 * @ibatorgenerated  Tue Jul 06 16:43:03 SGT 2010
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

		public Criteria andDaIsNull() {
			addCriterion("da is null");
			return this;
		}

		public Criteria andDaIsNotNull() {
			addCriterion("da is not null");
			return this;
		}

		public Criteria andDaEqualTo(Long value) {
			addCriterion("da =", value, "da");
			return this;
		}

		public Criteria andDaNotEqualTo(Long value) {
			addCriterion("da <>", value, "da");
			return this;
		}

		public Criteria andDaGreaterThan(Long value) {
			addCriterion("da >", value, "da");
			return this;
		}

		public Criteria andDaGreaterThanOrEqualTo(Long value) {
			addCriterion("da >=", value, "da");
			return this;
		}

		public Criteria andDaLessThan(Long value) {
			addCriterion("da <", value, "da");
			return this;
		}

		public Criteria andDaLessThanOrEqualTo(Long value) {
			addCriterion("da <=", value, "da");
			return this;
		}

		public Criteria andDaIn(List<Long> values) {
			addCriterion("da in", values, "da");
			return this;
		}

		public Criteria andDaNotIn(List<Long> values) {
			addCriterion("da not in", values, "da");
			return this;
		}

		public Criteria andDaBetween(Long value1, Long value2) {
			addCriterion("da between", value1, value2, "da");
			return this;
		}

		public Criteria andDaNotBetween(Long value1, Long value2) {
			addCriterion("da not between", value1, value2, "da");
			return this;
		}

		public Criteria andManifestNoIsNull() {
			addCriterion("manifest_no is null");
			return this;
		}

		public Criteria andManifestNoIsNotNull() {
			addCriterion("manifest_no is not null");
			return this;
		}

		public Criteria andManifestNoEqualTo(Long value) {
			addCriterion("manifest_no =", value, "manifestNo");
			return this;
		}

		public Criteria andManifestNoNotEqualTo(Long value) {
			addCriterion("manifest_no <>", value, "manifestNo");
			return this;
		}

		public Criteria andManifestNoGreaterThan(Long value) {
			addCriterion("manifest_no >", value, "manifestNo");
			return this;
		}

		public Criteria andManifestNoGreaterThanOrEqualTo(Long value) {
			addCriterion("manifest_no >=", value, "manifestNo");
			return this;
		}

		public Criteria andManifestNoLessThan(Long value) {
			addCriterion("manifest_no <", value, "manifestNo");
			return this;
		}

		public Criteria andManifestNoLessThanOrEqualTo(Long value) {
			addCriterion("manifest_no <=", value, "manifestNo");
			return this;
		}

		public Criteria andManifestNoIn(List<Long> values) {
			addCriterion("manifest_no in", values, "manifestNo");
			return this;
		}

		public Criteria andManifestNoNotIn(List<Long> values) {
			addCriterion("manifest_no not in", values, "manifestNo");
			return this;
		}

		public Criteria andManifestNoBetween(Long value1, Long value2) {
			addCriterion("manifest_no between", value1, value2, "manifestNo");
			return this;
		}

		public Criteria andManifestNoNotBetween(Long value1, Long value2) {
			addCriterion("manifest_no not between", value1, value2,
					"manifestNo");
			return this;
		}

		public Criteria andCommentIsNull() {
			addCriterion("comment is null");
			return this;
		}

		public Criteria andCommentIsNotNull() {
			addCriterion("comment is not null");
			return this;
		}

		public Criteria andCommentEqualTo(String value) {
			addCriterion("comment =", value, "comment");
			return this;
		}

		public Criteria andCommentNotEqualTo(String value) {
			addCriterion("comment <>", value, "comment");
			return this;
		}

		public Criteria andCommentGreaterThan(String value) {
			addCriterion("comment >", value, "comment");
			return this;
		}

		public Criteria andCommentGreaterThanOrEqualTo(String value) {
			addCriterion("comment >=", value, "comment");
			return this;
		}

		public Criteria andCommentLessThan(String value) {
			addCriterion("comment <", value, "comment");
			return this;
		}

		public Criteria andCommentLessThanOrEqualTo(String value) {
			addCriterion("comment <=", value, "comment");
			return this;
		}

		public Criteria andCommentLike(String value) {
			addCriterion("comment like", value, "comment");
			return this;
		}

		public Criteria andCommentNotLike(String value) {
			addCriterion("comment not like", value, "comment");
			return this;
		}

		public Criteria andCommentIn(List<String> values) {
			addCriterion("comment in", values, "comment");
			return this;
		}

		public Criteria andCommentNotIn(List<String> values) {
			addCriterion("comment not in", values, "comment");
			return this;
		}

		public Criteria andCommentBetween(String value1, String value2) {
			addCriterion("comment between", value1, value2, "comment");
			return this;
		}

		public Criteria andCommentNotBetween(String value1, String value2) {
			addCriterion("comment not between", value1, value2, "comment");
			return this;
		}

		public Criteria andConsNoIsNull() {
			addCriterion("cons_no is null");
			return this;
		}

		public Criteria andConsNoIsNotNull() {
			addCriterion("cons_no is not null");
			return this;
		}

		public Criteria andConsNoEqualTo(Double value) {
			addCriterion("cons_no =", value, "consNo");
			return this;
		}

		public Criteria andConsNoNotEqualTo(Double value) {
			addCriterion("cons_no <>", value, "consNo");
			return this;
		}

		public Criteria andConsNoGreaterThan(Double value) {
			addCriterion("cons_no >", value, "consNo");
			return this;
		}

		public Criteria andConsNoGreaterThanOrEqualTo(Double value) {
			addCriterion("cons_no >=", value, "consNo");
			return this;
		}

		public Criteria andConsNoLessThan(Double value) {
			addCriterion("cons_no <", value, "consNo");
			return this;
		}

		public Criteria andConsNoLessThanOrEqualTo(Double value) {
			addCriterion("cons_no <=", value, "consNo");
			return this;
		}

		public Criteria andConsNoIn(List<Double> values) {
			addCriterion("cons_no in", values, "consNo");
			return this;
		}

		public Criteria andConsNoNotIn(List<Double> values) {
			addCriterion("cons_no not in", values, "consNo");
			return this;
		}

		public Criteria andConsNoBetween(Double value1, Double value2) {
			addCriterion("cons_no between", value1, value2, "consNo");
			return this;
		}

		public Criteria andConsNoNotBetween(Double value1, Double value2) {
			addCriterion("cons_no not between", value1, value2, "consNo");
			return this;
		}

		public Criteria andUserUpdatedIsNull() {
			addCriterion("user_updated is null");
			return this;
		}

		public Criteria andUserUpdatedIsNotNull() {
			addCriterion("user_updated is not null");
			return this;
		}

		public Criteria andUserUpdatedEqualTo(String value) {
			addCriterion("user_updated =", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedNotEqualTo(String value) {
			addCriterion("user_updated <>", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedGreaterThan(String value) {
			addCriterion("user_updated >", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedGreaterThanOrEqualTo(String value) {
			addCriterion("user_updated >=", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedLessThan(String value) {
			addCriterion("user_updated <", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedLessThanOrEqualTo(String value) {
			addCriterion("user_updated <=", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedLike(String value) {
			addCriterion("user_updated like", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedNotLike(String value) {
			addCriterion("user_updated not like", value, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedIn(List<String> values) {
			addCriterion("user_updated in", values, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedNotIn(List<String> values) {
			addCriterion("user_updated not in", values, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedBetween(String value1, String value2) {
			addCriterion("user_updated between", value1, value2, "userUpdated");
			return this;
		}

		public Criteria andUserUpdatedNotBetween(String value1, String value2) {
			addCriterion("user_updated not between", value1, value2,
					"userUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedIsNull() {
			addCriterion("datetime_updated is null");
			return this;
		}

		public Criteria andDatetimeUpdatedIsNotNull() {
			addCriterion("datetime_updated is not null");
			return this;
		}

		public Criteria andDatetimeUpdatedEqualTo(Date value) {
			addCriterion("datetime_updated =", value, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedNotEqualTo(Date value) {
			addCriterion("datetime_updated <>", value, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedGreaterThan(Date value) {
			addCriterion("datetime_updated >", value, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedGreaterThanOrEqualTo(Date value) {
			addCriterion("datetime_updated >=", value, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedLessThan(Date value) {
			addCriterion("datetime_updated <", value, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedLessThanOrEqualTo(Date value) {
			addCriterion("datetime_updated <=", value, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedIn(List<Date> values) {
			addCriterion("datetime_updated in", values, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedNotIn(List<Date> values) {
			addCriterion("datetime_updated not in", values, "datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedBetween(Date value1, Date value2) {
			addCriterion("datetime_updated between", value1, value2,
					"datetimeUpdated");
			return this;
		}

		public Criteria andDatetimeUpdatedNotBetween(Date value1, Date value2) {
			addCriterion("datetime_updated not between", value1, value2,
					"datetimeUpdated");
			return this;
		}
	}
}