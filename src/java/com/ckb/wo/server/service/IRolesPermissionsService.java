package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.RolesPermissions;
import com.ckb.wo.client.model.RolesPermissionsExample;

public interface IRolesPermissionsService {

	public List<RolesPermissions> getRolesPermissions();

	public int countRolesPermissions();

	public List<RolesPermissions> getRolesPermissionsByExample(
			RolesPermissionsExample example);

	public int countRolesPermissionsByExample(RolesPermissionsExample example);

	public int deleteRolesPermissions(Long rolespermissionsPk);

	public int deleteRolesPermissionsByExample(RolesPermissionsExample example);

	public Object insertRolesPermissions(RolesPermissions rolespermissions);

	public int updateRolesPermissions(RolesPermissions rolespermissions);

	public RolesPermissions getRolesPermissionsyByPrimaryKey(
			Long rolespermissionsPk);

}