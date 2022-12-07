package com.ckb.wo.server.test;

import java.util.ArrayList;
import java.util.List;

import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;

public class TestPermission {

	public static void main(String[] args) {
		testInsertDeletePermissionFromRoles();
	}
	
	public static void testInsertDeletePermissionFromRoles(){
		List<Long> permissions = new ArrayList<Long>();
		permissions.add(1L);
		permissions.add(2L);
		permissions.add(3L);
		Long trolePk=1L;
		PermissionLocalServiceUtil.deletePermissionsFromRole(permissions, trolePk);
		PermissionLocalServiceUtil.insertPermissionsToRole(permissions, trolePk);
	}
}
