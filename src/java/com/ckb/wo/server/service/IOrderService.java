package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.OrderExample;

public interface IOrderService {
	
	public List<Order> getOrder();

	public int deleteOrder(Long orderPk);

	public Object insertOrder(Order order);

	public int updateOrder(Order order);

	public int countOrder();

	public List<Order> getOrderByExample(OrderExample example);
	
	public List<Order> getOrderByExample(OrderExample example,int pageNo, int rowPerPage);

	public int countOrderByExample(OrderExample example);
        
        public Order getOrderByPrimayKey(Long orderPk);
}
