package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.Role;
import com.ckb.wo.client.model.RoleExample;
import com.ckb.wo.server.service.IRoleService;


public class RoleLocalServiceUtil extends GenericServiceUtil {

	public static int countRole(){
		return getRoleService().countRole();
	}

	public static List<Role> getRole(){
		return getRoleService().getRole();
	}

	public static List<Role> getRoleByExample(RoleExample example){
		return getRoleService().getRoleByExample(example);
	}

	public static int countRoleByExample(RoleExample example){
		return getRoleService().countRoleByExample(example);
	}

	public static int deleteRoleByPrimaryKey(Long rolePk){
		return getRoleService().deleteRole((Long)rolePk);
	}

	public static int updateRole(Role role){
		return getRoleService().updateRole(role);
	}

	public static Long insertRole(Role role){
		return (Long) getRoleService().insertRole(role);
	}

	public static Role getRoleByPrimaryKey(Long rolePk){
		return getRoleService().getRoleyByPrimaryKey(rolePk);
	}

	public static Role getRoleWithPermissionByPrimarykey(Long rolePk){
		return getRoleService().getRoleWithPermissionByPrimaryKey(rolePk);
	}
	
	private static IRoleService getRoleService(){
		return (IRoleService) getBean("roleService");
	}
}
