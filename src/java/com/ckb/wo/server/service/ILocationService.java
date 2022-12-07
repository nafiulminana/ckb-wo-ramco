package com.ckb.wo.server.service;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.LocationExample;

public interface ILocationService {
	
	public List<Location> getLocation();

	public int deleteLocation(Long LocationPk);

	public Object insertLocation(Location Location);

	public int updateLocation(Location Location);

	public int countLocation();

	public List<Location> getLocationByExample(LocationExample example);
	
	public List<Location> getLocationByExample(LocationExample example,int pageNo, int rowPerPage);

	public int countLocationByExample(LocationExample example);

	public Location getLocationByPrimaryKey(Long tlocationPk);
}
