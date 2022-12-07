/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.model;

import java.util.Date;

/**
 *
 * @author Shido69
 */
public class RegularBean {
    Long origin,destination,transportationMode,containerMode,deliveryTerm;
    Date actionDate;

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Long getContainerMode() {
        return containerMode;
    }

    public void setContainerMode(Long containerMode) {
        this.containerMode = containerMode;
    }

    public Long getDeliveryTerm() {
        return deliveryTerm;
    }

    public void setDeliveryTerm(Long deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public Long getTransportationMode() {
        return transportationMode;
    }

    public void setTransportationMode(Long transportationMode) {
        this.transportationMode = transportationMode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegularBean other = (RegularBean) obj;
        if (this.origin != other.origin && (this.origin == null || !this.origin.equals(other.origin))) {
            return false;
        }
        if (this.destination != other.destination && (this.destination == null || !this.destination.equals(other.destination))) {
            return false;
        }
        if (this.transportationMode != other.transportationMode && (this.transportationMode == null || !this.transportationMode.equals(other.transportationMode))) {
            return false;
        }
        if (this.containerMode != other.containerMode && (this.containerMode == null || !this.containerMode.equals(other.containerMode))) {
            return false;
        }
        if (this.deliveryTerm != other.deliveryTerm && (this.deliveryTerm == null || !this.deliveryTerm.equals(other.deliveryTerm))) {
            return false;
        }
        if (this.actionDate != other.actionDate && (this.actionDate == null || !this.actionDate.equals(other.actionDate))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.origin != null ? this.origin.hashCode() : 0);
        hash = 41 * hash + (this.destination != null ? this.destination.hashCode() : 0);
        hash = 41 * hash + (this.transportationMode != null ? this.transportationMode.hashCode() : 0);
        hash = 41 * hash + (this.containerMode != null ? this.containerMode.hashCode() : 0);
        hash = 41 * hash + (this.deliveryTerm != null ? this.deliveryTerm.hashCode() : 0);
        hash = 41 * hash + (this.actionDate != null ? this.actionDate.hashCode() : 0);
        return hash;
    }

}