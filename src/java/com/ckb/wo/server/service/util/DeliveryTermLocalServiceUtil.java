package com.ckb.wo.server.service.util;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.DeliveryTerm;
import com.ckb.wo.client.model.DeliveryTermExample;
import com.ckb.wo.server.service.IDeliveryTermService;

public class DeliveryTermLocalServiceUtil extends GenericServiceUtil {

	public static int countDeliveryTerm(){
		return getDeliveryTermService().countDeliveryTerm();
	}
	
	public static List<DeliveryTerm> getDeliveryTerm(){
		return getDeliveryTermService().getDeliveryTerm();
	}
	
	public static List<DeliveryTerm> getDeliveryTermByExample(DeliveryTermExample example){
		return getDeliveryTermService().getDeliveryTermByExample(example);
	}
	
	public static DeliveryTerm getDeliveryTermByPrimaryKey(Long deliverytermPk){
		return getDeliveryTermService().getDeliveryTermByPrimaryKey(deliverytermPk);
	}
	
	public static int countDeliveryTermByExample(DeliveryTermExample example){
		return getDeliveryTermService().countDeliveryTermByExample(example);
	}
	
	public static int deleteDeliveryTermByPrimaryKey(Long deliverytermPk){
		return getDeliveryTermService().deleteDeliveryTerm(deliverytermPk);
	}
	
	public static int updateDeliveryTerm(DeliveryTerm deliveryterm){
		return getDeliveryTermService().updateDeliveryTerm(deliveryterm);
	}
	
	public static Long insertDeliveryTerm(DeliveryTerm deliveryterm){
		return (Long)getDeliveryTermService().insertDeliveryTerm(deliveryterm);
	}
	
	private static IDeliveryTermService getDeliveryTermService(){
		return (IDeliveryTermService) getBean("deliverytermService");
	}
}
