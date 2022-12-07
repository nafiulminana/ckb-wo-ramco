package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.OriContainer;
import com.ckb.wo.client.model.OriContainerExample;

public interface IOriContainerService {
	
	public List<OriContainer> getOriContainer();

	public int deleteOriContainer(Long vendorPk);

	public Object insertOriContainer(OriContainer vendor);

	public int updateOriContainer(OriContainer vendor);

	public int countOriContainer();

	public List<OriContainer> getOriContainerByExample(OriContainerExample example);
	
	public List<OriContainer> getOriContainerByExample(OriContainerExample example,int pageNo, int rowPerPage);

	public int countOriContainerByExample(OriContainerExample example);
}
