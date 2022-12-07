package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.FastUser;
import com.ckb.wo.client.model.FastUserExample;
import com.ckb.wo.server.service.IFastService;

public class FastServiceLocalServiceUtil extends GenericServiceUtil {

	public static List<FastUser> getFastUserByExample(FastUserExample fuexample){
		return getFastService().getFastUserByExample(fuexample);
	}
	
	public static int countFastUserByExample(FastUserExample example){
		return getFastService().countFastUserByExample(example);
	}
	
	public static void importFastUserByIds(List<String> employee_Ids){
		getFastService().importFastUserByIds(employee_Ids);
	}
	
	public static void updateBoss(String bossEmployeeId, String slaveEmployeeId){
		getFastService().updateBoss(bossEmployeeId, slaveEmployeeId);
	}
	
	public static void updateBoss(String bossEmployeeId, List<String> slaveEmployeeIds){
		getFastService().updateBoss(bossEmployeeId, slaveEmployeeIds);
	}
	
	
	private static IFastService getFastService(){
		return (IFastService) getBean("fastService");
	}

}
