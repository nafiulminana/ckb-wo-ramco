package com.ckb.wo.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.OriContainer;
import com.ckb.wo.client.model.OriContainerExample;
import com.ckb.wo.server.dao.OriContainerDAO;
import com.ckb.wo.server.service.IOriContainerService;

public class OriContainerServiceImpl implements IOriContainerService {
	public OriContainerDAO oricontainerDao;
	
	
	public OriContainerServiceImpl(OriContainerDAO oricontainerDao) {
		super();
		this.oricontainerDao = oricontainerDao;
	}

	public List<OriContainer> getOriContainer() {
		OriContainerExample example = new OriContainerExample();
		return oricontainerDao.selectOriContainerByExample(example);
	}
	
	public int countOriContainer(){
		OriContainerExample example = new OriContainerExample();
		return oricontainerDao.countOriContainerByExample(example);
	}
	
	
	public List<OriContainer> getOriContainerByExample(OriContainerExample example){
		return oricontainerDao.selectOriContainerByExample(example);
	}
	
	public int countOriContainerByExample(OriContainerExample example){
		
		return oricontainerDao.countOriContainerByExample(example);
	}

	public int deleteOriContainer(Long oricontainerPk){		
		return 0;
	}
	
	public Object insertOriContainer(OriContainer oricontainer){
		return null;
	}
	
	public int updateOriContainer(OriContainer oricontainer){
		return 0;		
	}

	public List<OriContainer> getOriContainerByExample(
			OriContainerExample example, int pageNo, int rowPerPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
