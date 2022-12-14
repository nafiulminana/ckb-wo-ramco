package com.ckb.wo.client.model;

import java.io.Serializable;

public class RolesPermissions implements Serializable {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column trole_tpermission.trole_tpermission_pk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private Long troleTpermissionPk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column trole_tpermission.trole_fk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private Long troleFk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column trole_tpermission.tpermission_fk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private Long tpermissionFk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column trole_tpermission.trole_tpermission_pk
	 * @return  the value of trole_tpermission.trole_tpermission_pk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Long getTroleTpermissionPk() {
		return troleTpermissionPk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column trole_tpermission.trole_tpermission_pk
	 * @param troleTpermissionPk  the value for trole_tpermission.trole_tpermission_pk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setTroleTpermissionPk(Long troleTpermissionPk) {
		this.troleTpermissionPk = troleTpermissionPk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column trole_tpermission.trole_fk
	 * @return  the value of trole_tpermission.trole_fk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Long getTroleFk() {
		return troleFk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column trole_tpermission.trole_fk
	 * @param troleFk  the value for trole_tpermission.trole_fk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setTroleFk(Long troleFk) {
		this.troleFk = troleFk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column trole_tpermission.tpermission_fk
	 * @return  the value of trole_tpermission.tpermission_fk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Long getTpermissionFk() {
		return tpermissionFk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column trole_tpermission.tpermission_fk
	 * @param tpermissionFk  the value for trole_tpermission.tpermission_fk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setTpermissionFk(Long tpermissionFk) {
		this.tpermissionFk = tpermissionFk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof RolesPermissions)) {
			return false;
		}
		RolesPermissions other = (RolesPermissions) that;
		return this.getTroleTpermissionPk() == null ? other == null : this
				.getTroleTpermissionPk().equals(other.getTroleTpermissionPk())
				&& this.getTroleFk() == null ? other == null : this
				.getTroleFk().equals(other.getTroleFk())
				&& this.getTpermissionFk() == null ? other == null : this
				.getTpermissionFk().equals(other.getTpermissionFk());
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole_tpermission
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		if (getTroleTpermissionPk() != null) {
			hash *= getTroleTpermissionPk().hashCode();
		}
		if (getTroleFk() != null) {
			hash *= getTroleFk().hashCode();
		}
		if (getTpermissionFk() != null) {
			hash *= getTpermissionFk().hashCode();
		}
		return hash;
	}
}