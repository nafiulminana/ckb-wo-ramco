package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.RolesUsers;
import com.ckb.wo.client.model.RolesUsersExample;
import com.ckb.wo.server.dao.RolesUsersDAO;
import com.ckb.wo.server.service.IRolesUsersService;

public class RolesUsersServiceImpl extends GenericServiceImpl implements IRolesUsersService {

	private RolesUsersDAO rolesusersDao;
	
	
	public RolesUsersServiceImpl(RolesUsersDAO rolesusersDao) {
		super();
		this.rolesusersDao = rolesusersDao;
	}

	public List<RolesUsers> getRolesUsers() {
		RolesUsersExample example = new RolesUsersExample();
		return rolesusersDao.selectRolesUsersByExample(example);
	}
	
	public int countRolesUsers(){
		RolesUsersExample example = new RolesUsersExample();
		return rolesusersDao.countRolesUsersByExample(example);
	}
	
	
	public List<RolesUsers> getRolesUsersByExample(RolesUsersExample example){
		return rolesusersDao.selectRolesUsersByExample(example);
	}
	
	public int countRolesUsersByExample(RolesUsersExample example){
		
		return rolesusersDao.countRolesUsersByExample(example);
	}

	public int deleteRolesUsers(Long rolesusersPk){		
		return rolesusersDao.deleteRolesUsersByPrimaryKey(rolesusersPk);
	}
	
	public int deleteRolesUsersByExample(RolesUsersExample example){
		return rolesusersDao.deleteRolesUsersByExample(example);
	}
	
	public Object insertRolesUsers(RolesUsers rolesusers){
		return rolesusersDao.insertRolesUsers(rolesusers);
	}
	
	public int updateRolesUsers(RolesUsers rolesusers){
		return rolesusersDao.updateRolesUsersByPrimaryKey(rolesusers);		
	}

	
	public RolesUsers getRolesUsersyByPrimaryKey(Long rolesusersPk){
		return rolesusersDao.selectRolesUsersByPrimaryKey(rolesusersPk);		
	}

        
}
