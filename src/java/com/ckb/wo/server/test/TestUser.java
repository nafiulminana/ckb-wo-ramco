package com.ckb.wo.server.test;

import com.ckb.wo.client.model.User;
import java.util.ArrayList;
import java.util.List;

import com.ckb.wo.server.service.util.UserLocalServiceUtil;

public class TestUser {

	public static void main(String[] args) {
//		testInsertDeleteUsersFromRoles();
            List<User> r = UserLocalServiceUtil.getUserAlreadyCreateWO();
            System.out.println("isi list: "+r.size());
	}
	
//	public static void testInsertDeleteUsersFromRoles(){
//		List<Long> roles = new ArrayList<Long>();
//		roles.add(1L);
//		UserLocalServiceUtil.deleteUserFromRoles("wo6", roles);
//		roles.add(2L);
//		UserLocalServiceUtil.insertUserToRoles("wo6", roles);
//	}
}
