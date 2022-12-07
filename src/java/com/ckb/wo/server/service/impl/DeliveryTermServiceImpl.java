package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.DeliveryTerm;
import com.ckb.wo.client.model.DeliveryTermExample;
import com.ckb.wo.server.dao.DeliveryTermDAO;
import com.ckb.wo.server.service.IDeliveryTermService;

public class DeliveryTermServiceImpl extends GenericServiceImpl implements IDeliveryTermService  {
	public DeliveryTermDAO deliverytermDao;
	
	
	public DeliveryTermServiceImpl(DeliveryTermDAO deliverytermDao) {
		super();
		this.deliverytermDao = deliverytermDao;
	}

	public List<DeliveryTerm> getDeliveryTerm() {
		DeliveryTermExample example = new DeliveryTermExample();
		return deliverytermDao.selectDeliveryTermByExample(example);
	}
	
	public int countDeliveryTerm(){
		DeliveryTermExample example = new DeliveryTermExample();
		return deliverytermDao.countDeliveryTermByExample(example);
	}
	
	
	public List<DeliveryTerm> getDeliveryTermByExample(DeliveryTermExample example){
		return deliverytermDao.selectDeliveryTermByExample(example);
	}
	
	public int countDeliveryTermByExample(DeliveryTermExample example){
		
		return deliverytermDao.countDeliveryTermByExample(example);
	}

	public int deleteDeliveryTerm(Long deliverytermPk){		
		return deliverytermDao.deleteDeliveryTermByPrimaryKey(deliverytermPk);
	}
	
	public Object insertDeliveryTerm(DeliveryTerm deliveryterm){
		return deliverytermDao.insertDeliveryTerm(deliveryterm);
	}
	
	public int updateDeliveryTerm(DeliveryTerm deliveryterm){
		return deliverytermDao.updateDeliveryTermByPrimaryKey(deliveryterm);		
	}

	
	
	public List<DeliveryTerm> getDeliveryTermByExample(
			DeliveryTermExample example, int pageNo, int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return deliverytermDao.selectDeliveryTermByExample(example);
	}

	public DeliveryTerm getDeliveryTermByPrimaryKey(Long deliverytermPk) {
		return deliverytermDao.selectDeliveryTermByPrimaryKey(deliverytermPk);
	}
	
}
