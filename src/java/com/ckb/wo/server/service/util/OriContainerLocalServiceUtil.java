package com.ckb.wo.server.service.util;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.OriContainer;
import com.ckb.wo.client.model.OriContainerExample;
import com.ckb.wo.server.service.IOriContainerService;

public class OriContainerLocalServiceUtil extends GenericServiceUtil {

	public static int countOriContainer(){
		return getOriContainerService().countOriContainer();
	}
	
	public static List<OriContainer> getOriContainer(){
		return getOriContainerService().getOriContainer();
	}
	
	public static List<OriContainer> getOriContainerByExample(OriContainerExample example){
		return getOriContainerService().getOriContainerByExample(example);
	}
	
	public static int countOriContainerByExample(OriContainerExample example){
		return getOriContainerService().countOriContainerByExample(example);
	}
	
	public static int deleteOriContainerByPrimaryKey(Long oricontainerPk){
		return getOriContainerService().deleteOriContainer(oricontainerPk);
	}
	
	public static int updateOriContainer(OriContainer oricontainer){
		return getOriContainerService().updateOriContainer(oricontainer);
	}
	
	private static IOriContainerService getOriContainerService(){
		return (IOriContainerService) getBean("oricontainerService");
	}
}
