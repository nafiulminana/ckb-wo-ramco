package com.ckb.wo.client.model;



public class ExtendedWorkOrderFlowExample extends WorkOrderFlowExample{

	@Override
	protected Criteria createCriteriaInternal() {		
		return new ExtendedCriteria();
	}	
	
	public static class ExtendedCriteria extends WorkOrderFlowExample.Criteria{
		
		public Criteria andLevelValueGreaterThanOrEqualTo(int value) {
			addCriterion("level_value >=", value, "level_value");
			return this;
		}
	}
}
