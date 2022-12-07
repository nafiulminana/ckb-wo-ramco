package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.OrderExample;
import com.ckb.wo.server.dao.OrderDAO;
import com.ckb.wo.server.service.IOrderService;

public class OrderServiceImpl extends GenericServiceImpl implements IOrderService {
	public OrderDAO orderDao;
	
	
	public OrderServiceImpl(OrderDAO orderDao) {
		super();
		this.orderDao = orderDao;
	}

	public List<Order> getOrder() {
		OrderExample example = new OrderExample();
		return orderDao.selectOrderByExample(example);
	}
	
	public int countOrder(){
		OrderExample example = new OrderExample();
		return orderDao.countOrderByExample(example);
	}
	
	
	public List<Order> getOrderByExample(OrderExample example){
		return orderDao.selectOrderByExample(example);
	}
	
	public int countOrderByExample(OrderExample example){
		
		return orderDao.countOrderByExample(example);
	}

	public int deleteOrder(Long orderPk){
		return orderDao.deleteOrderByPrimaryKey(orderPk);
	}
	
	public Object insertOrder(Order order){
		return orderDao.insertOrder(order);
	}
	
	public int updateOrder(Order order){
		return orderDao.updateOrderByPrimaryKey(order);		
	}

	public List<Order> getOrderByExample(OrderExample example, int pageNo,
			int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return orderDao.selectOrderByExample(example);
	}
	
        @Override
        public Order getOrderByPrimayKey(Long orderPk) {
            return orderDao.selectOrderByPrimaryKey(orderPk);
        }
}
