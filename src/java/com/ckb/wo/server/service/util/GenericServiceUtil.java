package com.ckb.wo.server.service.util;

import com.ckb.wo.server.service.factory.BeanFactory;

public class GenericServiceUtil {
	
	protected static Object getBean(String beanName){
		return BeanFactory.getBean(beanName);
	}
	
	protected String getLimitClause(int offset, int recordPerPage){
		if(offset<1){
			offset=1;
		}
		return ( substractOffset(offset) +","+recordPerPage);
	}
	
	private int substractOffset(int originalOffset){
		return originalOffset-1;
	}
}
