package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Role;
import com.ckb.wo.client.model.RoleExample;
import com.ckb.wo.server.dao.RoleDAO;
import com.ckb.wo.server.service.IRoleService;

public class RoleServiceImpl extends GenericServiceImpl implements IRoleService {

	private RoleDAO roleDao;
	
	
	public RoleServiceImpl(RoleDAO roleDao) {
		super();
		this.roleDao = roleDao;
	}

	public List<Role> getRole() {
		RoleExample example = new RoleExample();
		return roleDao.selectRoleByExample(example);
	}
	
	public int countRole(){
		RoleExample example = new RoleExample();
		return roleDao.countRoleByExample(example);
	}
	
	
	public List<Role> getRoleByExample(RoleExample example){
		return roleDao.selectRoleByExample(example);
	}
	
	public int countRoleByExample(RoleExample example){
		
		return roleDao.countRoleByExample(example);
	}

	public int deleteRole(Long rolePk){		
		return roleDao.deleteRoleByPrimaryKey(rolePk);
	}
	
	public int deleteRoleByExample(RoleExample example){
		return roleDao.deleteRoleByExample(example);
	}
	
	public Object insertRole(Role role){
		return roleDao.insertRole(role);
	}
	
	public int updateRole(Role role){
		return roleDao.updateRoleByPrimaryKey(role);		
	}

	public List<Role> getRoleByExample(RoleExample example, int pageNo,
			int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return roleDao.selectRoleByExample(example);
	}
	
	public Role getRoleyByPrimaryKey(Long rolePk){
		return roleDao.selectRoleByPrimaryKey(rolePk);		
	}
	
	public Role getRoleWithPermissionByPrimaryKey(Long rolePk){		
		return roleDao.selectRoleWithPermissionByPrimaryKey(rolePk);
	}

        
}
