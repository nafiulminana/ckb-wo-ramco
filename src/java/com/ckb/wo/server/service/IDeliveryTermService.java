package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.DeliveryTerm;
import com.ckb.wo.client.model.DeliveryTermExample;

public interface IDeliveryTermService {
	
	public List<DeliveryTerm> getDeliveryTerm();

	public int deleteDeliveryTerm(Long deliverytermPk);

	public Object insertDeliveryTerm(DeliveryTerm deliveryterm);

	public int updateDeliveryTerm(DeliveryTerm deliveryterm);

	public int countDeliveryTerm();

	public List<DeliveryTerm> getDeliveryTermByExample(DeliveryTermExample example);
	
	public List<DeliveryTerm> getDeliveryTermByExample(DeliveryTermExample example,int pageNo, int rowPerPage);

	public int countDeliveryTermByExample(DeliveryTermExample example);

	public DeliveryTerm getDeliveryTermByPrimaryKey(Long deliverytermPk);
}
