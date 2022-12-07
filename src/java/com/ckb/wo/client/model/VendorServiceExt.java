package com.ckb.wo.client.model;

import java.math.BigDecimal;

/**
 *
 * @author Amaran Sidhiq
 */
public class VendorServiceExt extends VendorService {

    BigDecimal weight;
    private Boolean isValidData;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Boolean getIsValidData() {
        return isValidData;
    }

    public void setIsValidData(Boolean isValidData) {
        this.isValidData = isValidData;
    } 
}
