package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.FastUser;
import com.ckb.wo.client.model.FastUserExample;

public interface IFastService {

	public abstract List<FastUser> getFastUserByExample(FastUserExample example);

	public abstract int countFastUserByExample(FastUserExample example);

	public abstract List<FastUser> getFastUserByIds(List<String> employee_Ids);

	public abstract void importFastUserByIds(List<String> employee_Ids);

	public abstract void updateBoss(String bossEmployeeId,
			List<String> slaveEmployeeIds);

	public abstract void updateBoss(String bossEmployeeId,
			String slaveEmployeeId);

}