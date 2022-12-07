package com.ckb.wo.server.service.util;

import java.io.Serializable;
import java.util.List;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.LocationExample;
import com.ckb.wo.server.service.ILocationService;

public class LocationLocalServiceUtil extends GenericServiceUtil {

	public static int countLocation(){
		return getLocationService().countLocation();
	}
	
	public static List<Location> getLocation(){
		return getLocationService().getLocation();
	}
	
	public static List<Location> getLocationByExample(LocationExample example){
		return getLocationService().getLocationByExample(example);
	}
	
	public static Location getLocationByPrimaryKey(Long locationPk){
		return getLocationService().getLocationByPrimaryKey(locationPk);
	}
	
	public static int countLocationByExample(LocationExample example){
		return getLocationService().countLocationByExample(example);
	}
	
	public static int deleteLocationByPrimaryKey(Long locationPk){
		return getLocationService().deleteLocation(locationPk);
	}
	
	public static Long insertLocation(Location location){
		return (Long) getLocationService().insertLocation(location);
	}
        
        public static int updateLocation(Location location){
		return getLocationService().updateLocation(location);
	}
	
	private static ILocationService getLocationService(){
		return (ILocationService) getBean("locationService");
	}
}
