package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.RolesPermissions;
import com.ckb.wo.client.model.RolesPermissionsExample;
import com.ckb.wo.server.dao.RolesPermissionsDAO;
import com.ckb.wo.server.service.IRolesPermissionsService;

public class RolesPermissionsServiceImpl extends GenericServiceImpl implements IRolesPermissionsService {

	private RolesPermissionsDAO rolespermissionsDao;
	
	
	public RolesPermissionsServiceImpl(RolesPermissionsDAO rolespermissionsDao) {
		super();
		this.rolespermissionsDao = rolespermissionsDao;
	}

	public List<RolesPermissions> getRolesPermissions() {
		RolesPermissionsExample example = new RolesPermissionsExample();
		return rolespermissionsDao.selectRolesPermissionsByExample(example);
	}
	
	public int countRolesPermissions(){
		RolesPermissionsExample example = new RolesPermissionsExample();
		return rolespermissionsDao.countRolesPermissionsByExample(example);
	}
	
	
	public List<RolesPermissions> getRolesPermissionsByExample(RolesPermissionsExample example){
		return rolespermissionsDao.selectRolesPermissionsByExample(example);
	}
	
	public int countRolesPermissionsByExample(RolesPermissionsExample example){
		
		return rolespermissionsDao.countRolesPermissionsByExample(example);
	}

	public int deleteRolesPermissions(Long rolespermissionsPk){		
		return rolespermissionsDao.deleteRolesPermissionsByPrimaryKey(rolespermissionsPk);
	}
	
	public int deleteRolesPermissionsByExample(RolesPermissionsExample example){
		return rolespermissionsDao.deleteRolesPermissionsByExample(example);
	}
	
	public Object insertRolesPermissions(RolesPermissions rolespermissions){
		return rolespermissionsDao.insertRolesPermissions(rolespermissions);
	}
	
	public int updateRolesPermissions(RolesPermissions rolespermissions){
		return rolespermissionsDao.updateRolesPermissionsByPrimaryKey(rolespermissions);		
	}

	
	public RolesPermissions getRolesPermissionsyByPrimaryKey(Long rolespermissionsPk){
		return rolespermissionsDao.selectRolesPermissionsByPrimaryKey(rolespermissionsPk);		
	}

        
}
