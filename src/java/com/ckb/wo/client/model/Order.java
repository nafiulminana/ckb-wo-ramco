package com.ckb.wo.client.model;

import java.io.Serializable;


public class Order implements Serializable {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column torder.torder_pk
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private Long torderPk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column torder.ordercode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String ordercode;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column torder.ordername
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String ordername;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table torder
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column torder.torder_pk
	 * @return  the value of torder.torder_pk
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public Long getTorderPk() {
		return torderPk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column torder.torder_pk
	 * @param torderPk  the value for torder.torder_pk
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setTorderPk(Long torderPk) {
		this.torderPk = torderPk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column torder.ordercode
	 * @return  the value of torder.ordercode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getOrdercode() {
		return ordercode;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column torder.ordercode
	 * @param ordercode  the value for torder.ordercode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode == null ? null : ordercode.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column torder.ordername
	 * @return  the value of torder.ordername
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getOrdername() {
		return ordername;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column torder.ordername
	 * @param ordername  the value for torder.ordername
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setOrdername(String ordername) {
		this.ordername = ordername == null ? null : ordername.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table torder
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof Order)) {
			return false;
		}
		Order other = (Order) that;
		return this.getTorderPk() == null ? other == null : this.getTorderPk()
				.equals(other.getTorderPk())
				&& this.getOrdercode() == null ? other == null : this
				.getOrdercode().equals(other.getOrdercode())
				&& this.getOrdername() == null ? other == null : this
				.getOrdername().equals(other.getOrdername());
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table torder
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		if (getTorderPk() != null) {
			hash *= getTorderPk().hashCode();
		}
		if (getOrdercode() != null) {
			hash *= getOrdercode().hashCode();
		}
		if (getOrdername() != null) {
			hash *= getOrdername().hashCode();
		}
		return hash;
	}

	@Override
	public String toString() {
		return "Order [ordercode=" + ordercode + ", ordername=" + ordername
				+ ", torderPk=" + torderPk + "]";
	}
}