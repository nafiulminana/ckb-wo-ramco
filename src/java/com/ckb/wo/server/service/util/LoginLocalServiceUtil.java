package com.ckb.wo.server.service.util;

import com.ckb.wo.client.model.UserAccess;
import com.ckb.wo.server.service.ILoginService;

public class LoginLocalServiceUtil extends GenericServiceUtil {

	private static ILoginService getLoginService(){
		return (ILoginService) getBean("loginService");
	}
	
	public static UserAccess login(String userId, String password){
		return getLoginService().login(userId, password);		
	}
}
