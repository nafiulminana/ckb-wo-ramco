package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.LocationExample;
import com.ckb.wo.server.dao.LocationDAO;
import com.ckb.wo.server.service.ILocationService;

public class LocationServiceImpl extends GenericServiceImpl implements ILocationService {

    private LocationDAO locationDao;

    public LocationServiceImpl(LocationDAO locationDao) {
        super();
        this.locationDao = locationDao;
    }

    public List<Location> getLocation() {
        LocationExample example = new LocationExample();
//        example.createCriteria().andEnabled(true);
        example.setOrderByClause("locationname asc");

        return locationDao.selectLocationByExample(example);
    }

    public int countLocation() {
        LocationExample example = new LocationExample();
//        example.createCriteria().andEnabled(true);

        return locationDao.countLocationByExample(example);
    }

//    public List<Location> getLocation() {
//        LocationExample example = new LocationExample();
//        example.setOrderByClause("locationname asc");
//        return locationDao.selectLocationByExample(example);
//    }
//
//    public int countLocation() {
//        LocationExample example = new LocationExample();
//        return locationDao.countLocationByExample(example);
//    }

    public List<Location> getLocationByExample(LocationExample example) {
        return locationDao.selectLocationByExample(example);
    }

    public int countLocationByExample(LocationExample example) {
        return locationDao.countLocationByExample(example);
    }

    public int deleteLocation(Long locationPk) {
        return locationDao.deleteLocationByPrimaryKey(locationPk);
    }

    public Object insertLocation(Location Location) {
        return locationDao.insertLocation(Location);
    }

    public int updateLocation(Location Location) {
        return locationDao.updateLocationByPrimaryKey(Location);
    }

    public List<Location> getLocationByExample(LocationExample example,
            int pageNo, int rowPerPage) {
        example.setLimitClause(setOffset(pageNo, rowPerPage));
        return locationDao.selectLocationByExample(example);
    }

    public Location getLocationByPrimaryKey(Long tlocationPk) {
        return locationDao.selectLocationByPrimaryKey(tlocationPk);
    }
}
