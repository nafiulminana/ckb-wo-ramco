package com.ckb.wo.client.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class WorkOrderServiceModeDetail implements Serializable {

    private Boolean deleted=Boolean.FALSE;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.tworkorder_smodedetail_pk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private Long tworkorderSmodedetailPk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.tworkorder_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private Long tworkorderFk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.tservicemodedetail_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private Long tservicemodedetailFk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.quantity
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private Double quantity;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.smdcharge
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private BigDecimal smdcharge;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.smdremarks
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private String smdremarks;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tworkorder_smodedetail.smdtcurrency_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private Long smdtcurrencyFk;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tworkorder_smodedetail
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    private String smddetailname;

    public String getSmddetailname() {
        return smddetailname;
    }

    public void setSmddetailname(String smddetailname) {
        this.smddetailname = smddetailname;
    }
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.tworkorder_smodedetail_pk
     * @return  the value of tworkorder_smodedetail.tworkorder_smodedetail_pk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public Long getTworkorderSmodedetailPk() {
        return tworkorderSmodedetailPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.tworkorder_smodedetail_pk
     * @param tworkorderSmodedetailPk  the value for tworkorder_smodedetail.tworkorder_smodedetail_pk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setTworkorderSmodedetailPk(Long tworkorderSmodedetailPk) {
        this.tworkorderSmodedetailPk = tworkorderSmodedetailPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.tworkorder_fk
     * @return  the value of tworkorder_smodedetail.tworkorder_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public Long getTworkorderFk() {
        return tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.tworkorder_fk
     * @param tworkorderFk  the value for tworkorder_smodedetail.tworkorder_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setTworkorderFk(Long tworkorderFk) {
        this.tworkorderFk = tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.tservicemodedetail_fk
     * @return  the value of tworkorder_smodedetail.tservicemodedetail_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public Long getTservicemodedetailFk() {
        return tservicemodedetailFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.tservicemodedetail_fk
     * @param tservicemodedetailFk  the value for tworkorder_smodedetail.tservicemodedetail_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setTservicemodedetailFk(Long tservicemodedetailFk) {
        this.tservicemodedetailFk = tservicemodedetailFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.quantity
     * @return  the value of tworkorder_smodedetail.quantity
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.quantity
     * @param quantity  the value for tworkorder_smodedetail.quantity
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.smdcharge
     * @return  the value of tworkorder_smodedetail.smdcharge
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public BigDecimal getSmdcharge() {
        return smdcharge;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.smdcharge
     * @param smdcharge  the value for tworkorder_smodedetail.smdcharge
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setSmdcharge(BigDecimal smdcharge) {
        this.smdcharge = smdcharge;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.smdremarks
     * @return  the value of tworkorder_smodedetail.smdremarks
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public String getSmdremarks() {
        return smdremarks;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.smdremarks
     * @param smdremarks  the value for tworkorder_smodedetail.smdremarks
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setSmdremarks(String smdremarks) {
        this.smdremarks = smdremarks == null ? null : smdremarks.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tworkorder_smodedetail.smdtcurrency_fk
     * @return  the value of tworkorder_smodedetail.smdtcurrency_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public Long getSmdtcurrencyFk() {
        return smdtcurrencyFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tworkorder_smodedetail.smdtcurrency_fk
     * @param smdtcurrencyFk  the value for tworkorder_smodedetail.smdtcurrency_fk
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    public void setSmdtcurrencyFk(Long smdtcurrencyFk) {
        this.smdtcurrencyFk = smdtcurrencyFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tworkorder_smodedetail
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof WorkOrderServiceModeDetail)) {
            return false;
        }
        WorkOrderServiceModeDetail other = (WorkOrderServiceModeDetail) that;
        return this.getTworkorderSmodedetailPk() == null ? other == null : this.getTworkorderSmodedetailPk().equals(
                other.getTworkorderSmodedetailPk())
                && this.getTworkorderFk() == null ? other == null : this.getTworkorderFk().equals(other.getTworkorderFk())
                && this.getTservicemodedetailFk() == null ? other == null
                : this.getTservicemodedetailFk().equals(
                other.getTservicemodedetailFk())
                && this.getQuantity() == null ? other == null : this.getQuantity().equals(other.getQuantity())
                && this.getSmdcharge() == null ? other == null : this.getSmdcharge().equals(other.getSmdcharge())
                && this.getSmdremarks() == null ? other == null : this.getSmdremarks().equals(other.getSmdremarks())
                && this.getSmdtcurrencyFk() == null ? other == null
                : this.getSmdtcurrencyFk().equals(
                other.getSmdtcurrencyFk());
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tworkorder_smodedetail
     * @ibatorgenerated  Thu Aug 26 15:08:34 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getTworkorderSmodedetailPk() != null) {
            hash *= getTworkorderSmodedetailPk().hashCode();
        }
        if (getTworkorderFk() != null) {
            hash *= getTworkorderFk().hashCode();
        }
        if (getTservicemodedetailFk() != null) {
            hash *= getTservicemodedetailFk().hashCode();
        }
        if (getQuantity() != null) {
            hash *= getQuantity().hashCode();
        }
        if (getSmdcharge() != null) {
            hash *= getSmdcharge().hashCode();
        }
        if (getSmdremarks() != null) {
            hash *= getSmdremarks().hashCode();
        }
        if (getSmdtcurrencyFk() != null) {
            hash *= getSmdtcurrencyFk().hashCode();
        }
        return hash;
    }

    public WorkOrderServiceModeDetail(Double quantity,
            ServiceModeDetail servicemodeDetail) {
        super();
        this.quantity = quantity;
        this.servicemodeDetail = servicemodeDetail;
        this.tservicemodedetailFk = servicemodeDetail.getTservicemodedetailPk();
    }

    public WorkOrderServiceModeDetail() {
    }
    private ServiceModeDetail servicemodeDetail;
    private WorkOrder workOrder;
    private Currency currency;
    private BigDecimal subtotal;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public ServiceModeDetail getServicemodeDetail() {
        return servicemodeDetail;
    }

    public void setServicemodeDetail(ServiceModeDetail servicemodeDetail) {
        this.servicemodeDetail = servicemodeDetail;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    @Override
    public String toString() {
        return "WorkOrderServiceModeDetail{" + "deleted=" + deleted + ", tworkorderSmodedetailPk=" + tworkorderSmodedetailPk + ", tworkorderFk=" + tworkorderFk + ", tservicemodedetailFk=" + tservicemodedetailFk + ", quantity=" + quantity + ", smdcharge=" + smdcharge + ", smdremarks=" + smdremarks + ", smdtcurrencyFk=" + smdtcurrencyFk + ", smddetailname=" + smddetailname + ", servicemodeDetail=" + servicemodeDetail + ", workOrder=" + workOrder + ", currency=" + currency + ", subtotal=" + subtotal + '}';
    }
}
