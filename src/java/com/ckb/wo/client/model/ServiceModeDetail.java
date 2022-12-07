package com.ckb.wo.client.model;

import java.io.Serializable;


public class ServiceModeDetail implements Serializable {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicemodedetail.tservicemodedetail_pk
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	private Long tservicemodedetailPk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicemodedetail.smdcode
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	private String smdcode;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicemodedetail.smdname
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	private String smdname;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tservicemodedetail.smdtservicemode_fk
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	private Long tservicemodeFk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tservicemodedetail
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicemodedetail.tservicemodedetail_pk
	 * @return  the value of tservicemodedetail.tservicemodedetail_pk
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public Long getTservicemodedetailPk() {
		return tservicemodedetailPk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicemodedetail.tservicemodedetail_pk
	 * @param tservicemodedetailPk  the value for tservicemodedetail.tservicemodedetail_pk
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public void setTservicemodedetailPk(Long tservicemodedetailPk) {
		this.tservicemodedetailPk = tservicemodedetailPk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicemodedetail.smdcode
	 * @return  the value of tservicemodedetail.smdcode
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public String getSmdcode() {
		return smdcode;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicemodedetail.smdcode
	 * @param smdcode  the value for tservicemodedetail.smdcode
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public void setSmdcode(String smdcode) {
		this.smdcode = smdcode == null ? null : smdcode.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicemodedetail.smdname
	 * @return  the value of tservicemodedetail.smdname
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public String getSmdname() {
		return smdname;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicemodedetail.smdname
	 * @param smdname  the value for tservicemodedetail.smdname
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public void setSmdname(String smdname) {
		this.smdname = smdname == null ? null : smdname.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tservicemodedetail.smdtservicemode_fk
	 * @return  the value of tservicemodedetail.smdtservicemode_fk
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public Long getTservicemodeFk() {
		return tservicemodeFk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tservicemodedetail.smdtservicemode_fk
	 * @param tservicemodeFk  the value for tservicemodedetail.smdtservicemode_fk
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	public void setTservicemodeFk(Long tservicemodeFk) {
		this.tservicemodeFk = tservicemodeFk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemodedetail
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof ServiceModeDetail)) {
			return false;
		}
		ServiceModeDetail other = (ServiceModeDetail) that;
		return this.getTservicemodedetailPk() == null ? other == null : this
				.getTservicemodedetailPk().equals(
						other.getTservicemodedetailPk())
				&& this.getSmdcode() == null ? other == null : this
				.getSmdcode().equals(other.getSmdcode())
				&& this.getSmdname() == null ? other == null : this
				.getSmdname().equals(other.getSmdname())
				&& this.getTservicemodeFk() == null ? other == null : this
				.getTservicemodeFk().equals(other.getTservicemodeFk());
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tservicemodedetail
	 * @ibatorgenerated  Fri Jul 16 21:07:58 SGT 2010
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		if (getTservicemodedetailPk() != null) {
			hash *= getTservicemodedetailPk().hashCode();
		}
		if (getSmdcode() != null) {
			hash *= getSmdcode().hashCode();
		}
		if (getSmdname() != null) {
			hash *= getSmdname().hashCode();
		}
		if (getTservicemodeFk() != null) {
			hash *= getTservicemodeFk().hashCode();
		}
		return hash;
	}

	@Override
	public String toString() {
		return "ServiceModeDetail [smdcode=" + smdcode + ", smdname=" + smdname
				+ ", tservicemodeFk=" + tservicemodeFk
				+ ", tservicemodedetailPk=" + tservicemodedetailPk + "]";
	}
}