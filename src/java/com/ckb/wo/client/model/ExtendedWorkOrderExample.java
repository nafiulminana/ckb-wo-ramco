package com.ckb.wo.client.model;

public class ExtendedWorkOrderExample extends WorkOrderExample {

    @Override
    public ExtendedCriteria createCriteria() {
        ExtendedCriteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    @Override
    protected ExtendedCriteria createCriteriaInternal() {
        return new ExtendedCriteria();
    }

    public static class ExtendedCriteria extends WorkOrderExample.Criteria {

        public ExtendedCriteria andManifestNoLike(String manifestNo) {
            addCriterion("cast(manifest_no as char) like ", manifestNo, "manifest_no");
            return this;
        }

        public ExtendedCriteria andDaLike(String daNo) {
            addCriterion("cast(da as char) like ", daNo, "da");
            return this;
        }

        public ExtendedCriteria andDaIsPOD() {
            addCriterion("is_pod = 1");
            return this;
        }

        public ExtendedCriteria andDaNotPOD() {
            addCriterion("(is_pod = 0 or is_pod is null)");
            return this;
        }

        public ExtendedCriteria andOldwostatusEqualTo(String value) {
            addCriterion("oldwostatus =", value, "oldwostatus");
            return this;
        }
    }
}
