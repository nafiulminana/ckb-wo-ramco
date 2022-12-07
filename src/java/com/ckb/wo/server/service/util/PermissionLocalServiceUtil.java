package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.PermissionExample;
import com.ckb.wo.server.service.IPermissionService;

public class PermissionLocalServiceUtil extends GenericServiceUtil {

	public static int countPermission(){
		return getPermissionService().countPermission();
	}
	
	public static List<Permission> getPermission(){
		return getPermissionService().getPermission();
	}
	
	public static List<Permission> getPermissionByExample(PermissionExample example){
		return getPermissionService().getPermissionByExample(example);
	}
	
	public static int countPermissionByExample(PermissionExample example){
		return getPermissionService().countPermissionByExample(example);
	}
	
	public static int deletePermissionByPrimaryKey(Long permissionPk){
		return getPermissionService().deletePermission((Long)permissionPk);
	}
	
	public static int updatePermission(Permission permission){
		return getPermissionService().updatePermission(permission);
	}
	
	public static Long insertPermission(Permission permission){
		return (Long) getPermissionService().insertPermission(permission);	
	}
	
	public static Permission getPermissionByPrimaryKey(Long permissionPk){
		return getPermissionService().getPermissionByPrimaryKey(permissionPk);
	}
	
	public static boolean doesUserHavePermission(String employeeId, String actionId){
		return getPermissionService().doesUserHavePermission(employeeId, actionId);
	}
	
	public static void insertPermissionsToRole(List<Long> permissions,Long trolePk){
		getPermissionService().insertPermissionsToRole(permissions, trolePk);
	}
	
	public static void deletePermissionsFromRole(List<Long> permissions, Long trolePk){
		getPermissionService().deletePermissionsFromRole(permissions, trolePk);
	}
        
    public static boolean getUserCanCanceledWo(String employeeId){
		return getPermissionService().getUserCanCanceledWo(employeeId);
	}
	
	private static IPermissionService getPermissionService(){
		return (IPermissionService) getBean("permissionService");
	}
}
