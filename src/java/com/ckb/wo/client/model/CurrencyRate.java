package com.ckb.wo.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CurrencyRate implements Serializable {

    private String currencyTypeId;
    private Date rateDate;
    private BigDecimal rates;
    private Integer rateType;
    private Date rateDateTo;

    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public BigDecimal getRates() {
        return rates;
    }

    public void setRates(BigDecimal rates) {
        this.rates = rates;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Date getRateDateTo() {
        return rateDateTo;
    }

    public void setRateDateTo(Date rateDateTo) {
        this.rateDateTo = rateDateTo;
    }

}
