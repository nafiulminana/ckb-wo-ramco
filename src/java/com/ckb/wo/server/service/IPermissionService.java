package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.PermissionExample;
import com.ckb.wo.client.model.RolesPermissions;
import com.ckb.wo.client.model.RolesPermissionsExample;

public interface IPermissionService {

	public List<Permission> getPermission();

	public int countPermission();

	public List<Permission> getPermissionByExample(PermissionExample example);

	public int countPermissionByExample(PermissionExample example);

	public int deletePermission(Long permissionPk);

	public int deletePermissionByExample(PermissionExample example);

	public Object insertPermission(Permission permission);

	public int updatePermission(Permission permission);

	public Permission getPermissionByPrimaryKey(Long permissionPk);

	public boolean doesUserHavePermission(String employeeId, String actionId);

	public void insertPermissionsToRole(List<Long> permissions, Long trolePk);

	public void deletePermissionsFromRole(List<Long> permissions, Long trolePk);
        
    public boolean getUserCanCanceledWo(String employeeId);

}