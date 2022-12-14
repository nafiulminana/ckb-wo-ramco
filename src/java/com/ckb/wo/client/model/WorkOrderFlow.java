package com.ckb.wo.client.model;

import java.io.Serializable;
import java.util.Date;

public class WorkOrderFlow implements Serializable {

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.tworkorderflow_pk
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private Long tworkorderflowPk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.tworkorder_fk
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private Long tworkorderFk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.employeeid
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private String employeeid;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.actiondate
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private Date actiondate;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.actiondone
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private Boolean actiondone;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.level_id
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private String levelId;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.granted
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private Boolean granted;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.onbehalfemployeeid
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private String onbehalfemployeeid;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.reason
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private String reason;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorderflow.oldwostatus
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private String oldwostatus;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database table tworkorderflow
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.tworkorderflow_pk
     *
     * @return the value of tworkorderflow.tworkorderflow_pk
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public Long getTworkorderflowPk() {
        return tworkorderflowPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.tworkorderflow_pk
     *
     * @param tworkorderflowPk the value for tworkorderflow.tworkorderflow_pk
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setTworkorderflowPk(Long tworkorderflowPk) {
        this.tworkorderflowPk = tworkorderflowPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.tworkorder_fk
     *
     * @return the value of tworkorderflow.tworkorder_fk
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public Long getTworkorderFk() {
        return tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.tworkorder_fk
     *
     * @param tworkorderFk the value for tworkorderflow.tworkorder_fk
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setTworkorderFk(Long tworkorderFk) {
        this.tworkorderFk = tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.employeeid
     *
     * @return the value of tworkorderflow.employeeid
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public String getEmployeeid() {
        return employeeid;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.employeeid
     *
     * @param employeeid the value for tworkorderflow.employeeid
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.actiondate
     *
     * @return the value of tworkorderflow.actiondate
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public Date getActiondate() {
        return actiondate;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.actiondate
     *
     * @param actiondate the value for tworkorderflow.actiondate
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setActiondate(Date actiondate) {
        this.actiondate = actiondate;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.actiondone
     *
     * @return the value of tworkorderflow.actiondone
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public Boolean getActiondone() {
        return actiondone;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.actiondone
     *
     * @param actiondone the value for tworkorderflow.actiondone
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setActiondone(Boolean actiondone) {
        this.actiondone = actiondone;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.level_id
     *
     * @return the value of tworkorderflow.level_id
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.level_id
     *
     * @param levelId the value for tworkorderflow.level_id
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.granted
     *
     * @return the value of tworkorderflow.granted
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public Boolean getGranted() {
        return granted;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.granted
     *
     * @param granted the value for tworkorderflow.granted
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.onbehalfemployeeid
     *
     * @return the value of tworkorderflow.onbehalfemployeeid
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public String getOnbehalfemployeeid() {
        return onbehalfemployeeid;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.onbehalfemployeeid
     *
     * @param onbehalfemployeeid the value for tworkorderflow.onbehalfemployeeid
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setOnbehalfemployeeid(String onbehalfemployeeid) {
        this.onbehalfemployeeid = onbehalfemployeeid == null ? null
                : onbehalfemployeeid.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.reason
     *
     * @return the value of tworkorderflow.reason
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.reason
     *
     * @param reason the value for tworkorderflow.reason
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorderflow.oldwostatus
     *
     * @return the value of tworkorderflow.oldwostatus
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public String getOldwostatus() {
        return oldwostatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorderflow.oldwostatus
     *
     * @param oldwostatus the value for tworkorderflow.oldwostatus
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    public void setOldwostatus(String oldwostatus) {
        this.oldwostatus = oldwostatus == null ? null : oldwostatus.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @param that
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof WorkOrderFlow)) {
            return false;
        }
        WorkOrderFlow other = (WorkOrderFlow) that;
        return this.getTworkorderflowPk() == null ? other == null : this
                .getTworkorderflowPk().equals(other.getTworkorderflowPk())
                && this.getTworkorderFk() == null ? other == null : this
                .getTworkorderFk().equals(other.getTworkorderFk())
                && this.getEmployeeid() == null ? other == null : this
                .getEmployeeid().equals(other.getEmployeeid())
                && this.getActiondate() == null ? other == null : this
                .getActiondate().equals(other.getActiondate())
                && this.getActiondone() == null ? other == null : this
                .getActiondone().equals(other.getActiondone())
                && this.getLevelId() == null ? other == null : this
                .getLevelId().equals(other.getLevelId())
                && this.getGranted() == null ? other == null : this
                .getGranted().equals(other.getGranted())
                && this.getOnbehalfemployeeid() == null ? other == null : this
                .getOnbehalfemployeeid().equals(other.getOnbehalfemployeeid())
                && this.getReason() == null ? other == null : this.getReason()
                .equals(other.getReason())
                && this.getOldwostatus() == null ? other == null : this
                .getOldwostatus().equals(other.getOldwostatus());
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorderflow
     *
     * @ibatorgenerated Mon Aug 16 17:24:26 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getTworkorderflowPk() != null) {
            hash *= getTworkorderflowPk().hashCode();
        }
        if (getTworkorderFk() != null) {
            hash *= getTworkorderFk().hashCode();
        }
        if (getEmployeeid() != null) {
            hash *= getEmployeeid().hashCode();
        }
        if (getActiondate() != null) {
            hash *= getActiondate().hashCode();
        }
        if (getActiondone() != null) {
            hash *= getActiondone().hashCode();
        }
        if (getLevelId() != null) {
            hash *= getLevelId().hashCode();
        }
        if (getGranted() != null) {
            hash *= getGranted().hashCode();
        }
        if (getOnbehalfemployeeid() != null) {
            hash *= getOnbehalfemployeeid().hashCode();
        }
        if (getReason() != null) {
            hash *= getReason().hashCode();
        }
        if (getOldwostatus() != null) {
            hash *= getOldwostatus().hashCode();
        }
        return hash;
    }

    private User user;
    private Level level;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "WorkOrderFlow{" + "tworkorderflowPk=" + tworkorderflowPk + ", tworkorderFk=" + tworkorderFk + ", employeeid=" + employeeid + ", actiondate=" + actiondate + ", actiondone=" + actiondone + ", levelId=" + levelId + ", granted=" + granted + ", onbehalfemployeeid=" + onbehalfemployeeid + ", reason=" + reason + ", oldwostatus=" + oldwostatus + ", user=" + user + ", level=" + level + '}';
    }
}
