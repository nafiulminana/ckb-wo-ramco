package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.Role;
import com.ckb.wo.client.model.RoleExample;

public interface IRoleService {

	public List<Role> getRole();

	public int countRole();

	public List<Role> getRoleByExample(RoleExample example);

	public int countRoleByExample(RoleExample example);

	public int deleteRole(Long rolePk);

	public int deleteRoleByExample(RoleExample example);

	public Object insertRole(Role role);

	public int updateRole(Role role);

	public List<Role> getRoleByExample(RoleExample example, int pageNo,
			int rowPerPage);

	public Role getRoleyByPrimaryKey(Long rolePk);

	public Role getRoleWithPermissionByPrimaryKey(Long rolePk);

}