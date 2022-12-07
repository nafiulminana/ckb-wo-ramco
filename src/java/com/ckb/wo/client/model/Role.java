package com.ckb.wo.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column trole.trole_pk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private Long trolePk;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column trole.rolename
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private String rolename;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table trole
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Permission> permission = new ArrayList<Permission>();

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column trole.trole_pk
	 * @return  the value of trole.trole_pk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public Long getTrolePk() {
		return trolePk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column trole.trole_pk
	 * @param trolePk  the value for trole.trole_pk
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setTrolePk(Long trolePk) {
		this.trolePk = trolePk;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column trole.rolename
	 * @return  the value of trole.rolename
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column trole.rolename
	 * @param rolename  the value for trole.rolename
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename == null ? null : rolename.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof Role)) {
			return false;
		}
		Role other = (Role) that;
		return this.getTrolePk() == null ? other == null : this.getTrolePk()
				.equals(other.getTrolePk())
				&& this.getRolename() == null ? other == null : this
				.getRolename().equals(other.getRolename());
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table trole
	 * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		if (getTrolePk() != null) {
			hash *= getTrolePk().hashCode();
		}
		if (getRolename() != null) {
			hash *= getRolename().hashCode();
		}
		return hash;
	}
}