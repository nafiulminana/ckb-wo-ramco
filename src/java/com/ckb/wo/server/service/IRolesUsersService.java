package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.RolesUsers;
import com.ckb.wo.client.model.RolesUsersExample;

public interface IRolesUsersService {

	public List<RolesUsers> getRolesUsers();

	public int countRolesUsers();

	public List<RolesUsers> getRolesUsersByExample(RolesUsersExample example);

	public int countRolesUsersByExample(RolesUsersExample example);

	public int deleteRolesUsers(Long rolesusersPk);

	public int deleteRolesUsersByExample(RolesUsersExample example);

	public Object insertRolesUsers(RolesUsers rolesusers);

	public int updateRolesUsers(RolesUsers rolesusers);

	public RolesUsers getRolesUsersyByPrimaryKey(Long rolesusersPk);

}