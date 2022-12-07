package com.ckb.wo.client.model;

import java.io.Serializable;

public class Permission implements Serializable {

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tpermission.tpermission_pk
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    private Long tpermissionPk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tpermission.permissioncode
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    private String permissioncode;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tpermission.permissiondesc
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    private String permissiondesc;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tpermission
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tpermission.tpermission_pk
     * @return  the value of tpermission.tpermission_pk
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    public Long getTpermissionPk() {
        return tpermissionPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tpermission.tpermission_pk
     * @param tpermissionPk  the value for tpermission.tpermission_pk
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    public void setTpermissionPk(Long tpermissionPk) {
        this.tpermissionPk = tpermissionPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tpermission.permissioncode
     * @return  the value of tpermission.permissioncode
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    public String getPermissioncode() {
        return permissioncode;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tpermission.permissioncode
     * @param permissioncode  the value for tpermission.permissioncode
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    public void setPermissioncode(String permissioncode) {
        this.permissioncode = permissioncode == null ? null : permissioncode.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tpermission.permissiondesc
     * @return  the value of tpermission.permissiondesc
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    public String getPermissiondesc() {
        return permissiondesc;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tpermission.permissiondesc
     * @param permissiondesc  the value for tpermission.permissiondesc
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    public void setPermissiondesc(String permissiondesc) {
        this.permissiondesc = permissiondesc == null ? null : permissiondesc.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) that;
        return this.getTpermissionPk() == null ? other == null : this.getTpermissionPk().equals(other.getTpermissionPk())
                && this.getPermissioncode() == null ? other == null : this.getPermissioncode().equals(other.getPermissioncode())
                && this.getPermissiondesc() == null ? other == null : this.getPermissiondesc().equals(other.getPermissiondesc());
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tpermission
     * @ibatorgenerated  Fri Jul 16 14:31:56 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getTpermissionPk() != null) {
            hash *= getTpermissionPk().hashCode();
        }
        if (getPermissioncode() != null) {
            hash *= getPermissioncode().hashCode();
        }
        if (getPermissiondesc() != null) {
            hash *= getPermissiondesc().hashCode();
        }
        return hash;
    }
    public static final String CREATE_WO = "CREATE_WO";
    public static final String CANCEL_WO = "CANCEL_WO";
    public static final String REVISE_WO = "REVISE_WO";
    public static final String PRINT_WO = "PRINT_WO";
    public static final String APPROVE_WO = "APPROVE_WO";
    public static final String RECEIVE_AP = "RECEIVE_AP";
    public static final String RECEIVE_TR = "RECEIVE_TR";
    public static final String MANAGE_RATE = "MANAGE_RATE";
    public static final String VALIDATE_WO = "VALIDATE_WO";
    public static final String MANAGE_USER = "MANAGE_USER";
    public static final String VIEW_REPORT = "VIEW_REPORT";
    public static final String CANCEL_AP = "CANCEL_AP";
}