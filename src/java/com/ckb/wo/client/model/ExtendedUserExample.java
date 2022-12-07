package com.ckb.wo.client.model;

public class ExtendedUserExample extends UserExample {

    @Override
    protected Criteria createCriteriaInternal() {
        return new ExtendedCriteria();
    }

    public static class ExtendedCriteria extends UserExample.Criteria {

        public ExtendedCriteria andLevelValueEqualTo(Integer levelValue) {
            addCriterion("level_value=", levelValue, "level_value");
            return this;
        }

        public ExtendedCriteria andLevelValueGreaterThan(Integer levelValue) {
            addCriterion("level_value>", levelValue, "level_value");
            return this;
        }
    }
}
