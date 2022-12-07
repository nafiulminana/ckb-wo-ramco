/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.model;

/**
 *
 * @author Admin
 */
public class ExtendedWorkOrderDAExample extends WorkOrderDAExample{

	@Override
	protected Criteria createCriteriaInternal() {
		return new ExtendedCriteria();
	}

	public static class ExtendedCriteria extends WorkOrderDAExample.Criteria{
                public ExtendedCriteria excludeCanceledWO(){
                    addCriterion("wostatus <>","CANCELLED","wostatus");
                    return this;
                }

		public ExtendedCriteria andTVendorFkEqualTo(Long tvendor_fk){
			addCriterion("tvendor_fk =",tvendor_fk,"tvendor_fk");
			return this;
		}

                public ExtendedCriteria andTLocationFkEqualTo(Long tlocation_fk){
			addCriterion("origintlocation_fk =",tlocation_fk,"origintlocation_fk");
			return this;
		}

	}

}
