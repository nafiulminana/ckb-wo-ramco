package com.ckb.wo.client.model;

import java.io.Serializable;

public class AuditWorkOrder implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tauditwo.tauditwo_pk
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    private Long tauditwoPk;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tauditwo.tworkorder_fk
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    private Long tworkorderFk;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tauditwo.employee_id
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    private String employeeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tauditwo.action
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    private String action;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tauditwo.totalaction
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    private Integer totalaction;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table tauditwo
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tauditwo.tauditwo_pk
     *
     * @return the value of tauditwo.tauditwo_pk
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public Long getTauditwoPk() {
        return tauditwoPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tauditwo.tauditwo_pk
     *
     * @param tauditwoPk the value for tauditwo.tauditwo_pk
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public void setTauditwoPk(Long tauditwoPk) {
        this.tauditwoPk = tauditwoPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tauditwo.tworkorder_fk
     *
     * @return the value of tauditwo.tworkorder_fk
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public Long getTworkorderFk() {
        return tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tauditwo.tworkorder_fk
     *
     * @param tworkorderFk the value for tauditwo.tworkorder_fk
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public void setTworkorderFk(Long tworkorderFk) {
        this.tworkorderFk = tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tauditwo.employee_id
     *
     * @return the value of tauditwo.employee_id
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tauditwo.employee_id
     *
     * @param employeeId the value for tauditwo.employee_id
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tauditwo.action
     *
     * @return the value of tauditwo.action
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tauditwo.action
     *
     * @param action the value for tauditwo.action
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tauditwo.totalaction
     *
     * @return the value of tauditwo.totalaction
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public Integer getTotalaction() {
        return totalaction;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tauditwo.totalaction
     *
     * @param totalaction the value for tauditwo.totalaction
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    public void setTotalaction(Integer totalaction) {
        this.totalaction = totalaction;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tauditwo
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof AuditWorkOrder)) {
            return false;
        }
        AuditWorkOrder other = (AuditWorkOrder) that;
        return this.getTauditwoPk() == null ? other == null : this.getTauditwoPk().equals(other.getTauditwoPk())
            && this.getTworkorderFk() == null ? other == null : this.getTworkorderFk().equals(other.getTworkorderFk())
            && this.getEmployeeId() == null ? other == null : this.getEmployeeId().equals(other.getEmployeeId())
            && this.getAction() == null ? other == null : this.getAction().equals(other.getAction())
            && this.getTotalaction() == null ? other == null : this.getTotalaction().equals(other.getTotalaction());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tauditwo
     *
     * @ibatorgenerated Tue Jul 20 19:21:03 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getTauditwoPk() != null) {
            hash *= getTauditwoPk().hashCode();
        }
        if (getTworkorderFk() != null) {
            hash *= getTworkorderFk().hashCode();
        }
        if (getEmployeeId() != null) {
            hash *= getEmployeeId().hashCode();
        }
        if (getAction() != null) {
            hash *= getAction().hashCode();
        }
        if (getTotalaction() != null) {
            hash *= getTotalaction().hashCode();
        }
        return hash;
    }
    
    public static final String PRINT_ACTION="PRINT";
}