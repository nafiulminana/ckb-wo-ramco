package com.ckb.wo.server.service.util;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.OrderExample;
import com.ckb.wo.server.service.IOrderService;

public class OrderLocalServiceUtil extends GenericServiceUtil {

	public static int countOrder(){
		return getOrderService().countOrder();
	}
	
	public static List<Order> getOrder(){
		return getOrderService().getOrder();
	}
	
	public static List<Order> getOrderByExample(OrderExample example){
		return getOrderService().getOrderByExample(example);
	}
	
	public static int countOrderByExample(OrderExample example){
		return getOrderService().countOrderByExample(example);
	}
	
	public static int deleteOrderByPrimaryKey(Long orderPk){
		return getOrderService().deleteOrder(orderPk);
	}
	
	public static int updateOrder(Order order){
		return getOrderService().updateOrder(order);
	}
	
        public static Order getOrderByPrimaryKey(Long orderFk){
		return getOrderService().getOrderByPrimayKey(orderFk);
	}
         
	private static IOrderService getOrderService(){
		return (IOrderService) getBean("orderService");
	}
}
