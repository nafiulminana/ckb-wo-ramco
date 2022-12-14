package com.ckb.wo.client.model;

import java.io.Serializable;


public class ServiceType implements Serializable {

	@Override
	public String toString() {
		return "ServiceType [servicecode=" + servicecode + ", servicename="
				+ servicename + ", tservicetypePk=" + tservicetypePk + "]";
	}

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicetype.tservicetype_pk
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private Long tservicetypePk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicetype.servicecode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String servicecode;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicetype.servicename
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String servicename;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicetype.tservicetype_pk
	 * @return  the value of tservicetype.tservicetype_pk
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public Long getTservicetypePk() {
		return tservicetypePk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicetype.tservicetype_pk
	 * @param tservicetypePk  the value for tservicetype.tservicetype_pk
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setTservicetypePk(Long tservicetypePk) {
		this.tservicetypePk = tservicetypePk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicetype.servicecode
	 * @return  the value of tservicetype.servicecode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getServicecode() {
		return servicecode;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicetype.servicecode
	 * @param servicecode  the value for tservicetype.servicecode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode == null ? null : servicecode.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicetype.servicename
	 * @return  the value of tservicetype.servicename
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getServicename() {
		return servicename;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicetype.servicename
	 * @param servicename  the value for tservicetype.servicename
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename == null ? null : servicename.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof ServiceType)) {
			return false;
		}
		ServiceType other = (ServiceType) that;
		return this.getTservicetypePk() == null ? other == null : this
				.getTservicetypePk().equals(other.getTservicetypePk())
				&& this.getServicecode() == null ? other == null : this
				.getServicecode().equals(other.getServicecode())
				&& this.getServicename() == null ? other == null : this
				.getServicename().equals(other.getServicename());
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicetype
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		if (getTservicetypePk() != null) {
			hash *= getTservicetypePk().hashCode();
		}
		if (getServicecode() != null) {
			hash *= getServicecode().hashCode();
		}
		if (getServicename() != null) {
			hash *= getServicename().hashCode();
		}
		return hash;
	}
}