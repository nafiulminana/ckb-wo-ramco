package com.ckb.wo.client.model;

import java.io.Serializable;
import java.util.Date;

public class WorkOrderHistory implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tworkorderhistory.tworkorderhistory_pk
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private Long tworkorderhistoryPk;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tworkorderhistory.action
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private String action;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tworkorderhistory.actiondate
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private Date actiondate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tworkorderhistory.actionby
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private String actionby;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tworkorderhistory.changes
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private String changes;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tworkorderhistory.tworkorder_fk
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private Long tworkorderFk;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tworkorderhistory.tworkorderhistory_pk
     *
     * @return the value of tworkorderhistory.tworkorderhistory_pk
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public Long getTworkorderhistoryPk() {
        return tworkorderhistoryPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tworkorderhistory.tworkorderhistory_pk
     *
     * @param tworkorderhistoryPk the value for tworkorderhistory.tworkorderhistory_pk
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public void setTworkorderhistoryPk(Long tworkorderhistoryPk) {
        this.tworkorderhistoryPk = tworkorderhistoryPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tworkorderhistory.action
     *
     * @return the value of tworkorderhistory.action
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tworkorderhistory.action
     *
     * @param action the value for tworkorderhistory.action
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tworkorderhistory.actiondate
     *
     * @return the value of tworkorderhistory.actiondate
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public Date getActiondate() {
        return actiondate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tworkorderhistory.actiondate
     *
     * @param actiondate the value for tworkorderhistory.actiondate
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public void setActiondate(Date actiondate) {
        this.actiondate = actiondate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tworkorderhistory.actionby
     *
     * @return the value of tworkorderhistory.actionby
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public String getActionby() {
        return actionby;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tworkorderhistory.actionby
     *
     * @param actionby the value for tworkorderhistory.actionby
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public void setActionby(String actionby) {
        this.actionby = actionby == null ? null : actionby.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tworkorderhistory.changes
     *
     * @return the value of tworkorderhistory.changes
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public String getChanges() {
        return changes;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tworkorderhistory.changes
     *
     * @param changes the value for tworkorderhistory.changes
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public void setChanges(String changes) {
        this.changes = changes == null ? null : changes.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tworkorderhistory.tworkorder_fk
     *
     * @return the value of tworkorderhistory.tworkorder_fk
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public Long getTworkorderFk() {
        return tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tworkorderhistory.tworkorder_fk
     *
     * @param tworkorderFk the value for tworkorderhistory.tworkorder_fk
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    public void setTworkorderFk(Long tworkorderFk) {
        this.tworkorderFk = tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof WorkOrderHistory)) {
            return false;
        }
        WorkOrderHistory other = (WorkOrderHistory) that;
        return this.getTworkorderhistoryPk() == null ? other == null : this.getTworkorderhistoryPk().equals(other.getTworkorderhistoryPk())
            && this.getAction() == null ? other == null : this.getAction().equals(other.getAction())
            && this.getActiondate() == null ? other == null : this.getActiondate().equals(other.getActiondate())
            && this.getActionby() == null ? other == null : this.getActionby().equals(other.getActionby())
            && this.getChanges() == null ? other == null : this.getChanges().equals(other.getChanges())
            && this.getTworkorderFk() == null ? other == null : this.getTworkorderFk().equals(other.getTworkorderFk());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tworkorderhistory
     *
     * @ibatorgenerated Fri Jul 09 20:11:52 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getTworkorderhistoryPk() != null) {
            hash *= getTworkorderhistoryPk().hashCode();
        }
        if (getAction() != null) {
            hash *= getAction().hashCode();
        }
        if (getActiondate() != null) {
            hash *= getActiondate().hashCode();
        }
        if (getActionby() != null) {
            hash *= getActionby().hashCode();
        }
        if (getChanges() != null) {
            hash *= getChanges().hashCode();
        }
        if (getTworkorderFk() != null) {
            hash *= getTworkorderFk().hashCode();
        }
        return hash;
    }
}