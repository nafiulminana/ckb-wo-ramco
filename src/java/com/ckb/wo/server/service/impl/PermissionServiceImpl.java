package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.PermissionExample;
import com.ckb.wo.client.model.RolesPermissions;
import com.ckb.wo.client.model.RolesPermissionsExample;
import com.ckb.wo.server.dao.PermissionDAO;
import com.ckb.wo.server.dao.RolesPermissionsDAO;
import com.ckb.wo.server.service.IPermissionService;
import com.ckb.wo.server.service.factory.BeanFactory;

public class PermissionServiceImpl extends GenericServiceImpl implements IPermissionService {

	private PermissionDAO permissionDao;
	private RolesPermissionsDAO rolespermissionsDao;
	
	public RolesPermissionsDAO getRolespermissionsDao() {
		if(rolespermissionsDao==null){
			rolespermissionsDao=(RolesPermissionsDAO) BeanFactory.getBean("rolespermissionsDao");
		}
		return rolespermissionsDao;
	}

	public void setRolespermissionsDao(RolesPermissionsDAO rolespermissionsDao) {
		this.rolespermissionsDao = rolespermissionsDao;
	}

	public PermissionServiceImpl(PermissionDAO permissionDao) {
		super();
		this.permissionDao = permissionDao;
	}

	public List<Permission> getPermission() {
		PermissionExample example = new PermissionExample();
		return permissionDao.selectPermissionByExample(example);
	}
	
	public int countPermission(){
		PermissionExample example = new PermissionExample();
		return permissionDao.countPermissionByExample(example);
	}
	
	
	public List<Permission> getPermissionByExample(PermissionExample example){
		return permissionDao.selectPermissionByExample(example);
	}
	
	public int countPermissionByExample(PermissionExample example){
		
		return permissionDao.countPermissionByExample(example);
	}

	public int deletePermission(Long permissionPk){		
		return permissionDao.deletePermissionByPrimaryKey(permissionPk);
	}
	
	public int deletePermissionByExample(PermissionExample example){
		return permissionDao.deletePermissionByExample(example);
	}
	
	public Object insertPermission(Permission permission){
		return permissionDao.insertPermission(permission);
	}
	
	public int updatePermission(Permission permission){
		return permissionDao.updatePermissionByPrimaryKey(permission);		
	}

	
	public Permission getPermissionByPrimaryKey(Long permissionPk){
		return permissionDao.selectPermissionByPrimaryKey(permissionPk);		
	}

	public boolean doesUserHavePermission(String employeeId, String actionId){
		Integer total = permissionDao.doesUserHavePermission(employeeId, actionId);
		if(total==null || total.intValue()==0){
			return false;
		}else{
			return true;		
		}		
	}
	
	public void insertPermissionsToRole(List<Long> permissions, Long trolePk){
		if(permissions!=null){
			for(Long tpermission_pk : permissions ){
				RolesPermissionsExample example = new RolesPermissionsExample();
				example.createCriteria().andTpermissionFkEqualTo(tpermission_pk).andTroleFkEqualTo(trolePk);				
				int count = getRolespermissionsDao().countRolesPermissionsByExample(example);
				if(count==0){
					RolesPermissions rp = new RolesPermissions();
					rp.setTpermissionFk(tpermission_pk);
					rp.setTroleFk(trolePk);
					getRolespermissionsDao().insertRolesPermissions(rp);					
				}				
			}
		}		
	}
	
	public void deletePermissionsFromRole(List<Long> permissions, Long trolePk){
		if(permissions!=null){
			for(Long tpermission_fk : permissions){
				RolesPermissionsExample example = new RolesPermissionsExample();
				example.createCriteria().andTpermissionFkEqualTo(tpermission_fk).andTroleFkEqualTo(trolePk);
				getRolespermissionsDao().deleteRolesPermissionsByExample(example);
			}			
		}		
	}

    @Override
    public boolean getUserCanCanceledWo(String employeeId) {
        // set user can only canceled wo after approved
        boolean result = false;
        if(employeeId.equalsIgnoreCase("vyp0017") || employeeId.equalsIgnoreCase("bkr0016")) result = true;
        return result;
    }
}
