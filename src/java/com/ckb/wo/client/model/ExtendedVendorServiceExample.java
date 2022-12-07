package com.ckb.wo.client.model;

public class ExtendedVendorServiceExample extends VendorServiceExample {

    @Override
    protected Criteria createCriteriaInternal() {
        return new ExtendedCriteria();
    }

    public static class ExtendedCriteria extends VendorServiceExample.Criteria {

        public ExtendedCriteria andVendorNameLike(String vendorName) {
            addCriterion("vendorname like", vendorName, "vendorname");
            return this;

        }

        public ExtendedCriteria andIsactiveEqualTo(Boolean value) {
            addCriterion("isactive =", value, "isactive");
            return this;
        }

        public ExtendedCriteria andVendorInactiveDateExpired() {
            addCriterion(" date(now()) not between inactivefrom and inactiveto");
            return this;
        }
    }
}
