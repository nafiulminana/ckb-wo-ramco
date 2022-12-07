package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorExample;
import com.ckb.wo.server.dao.VendorDAO;
import com.ckb.wo.server.service.IVendorService;
import com.ckb.wo.server.service.constant.WOConstant;

public class VendorServiceImpl extends GenericServiceImpl implements IVendorService {

    private VendorDAO vendorDao;

    public VendorServiceImpl(VendorDAO vendorDao) {
        super();
        this.vendorDao = vendorDao;
    }

    @Override
    public List<Vendor> getVendor() {
        VendorExample example = new VendorExample();
        example.setOrderByClause("vendorname ASC");
        return vendorDao.selectVendorByExample(example);
    }

    @Override
    public List<Vendor> getActiveVendor() {
        VendorExample example = new VendorExample();
        example.createCriteria().andIsactiveEqualTo(true);
        example.setOrderByClause("vendorname ASC");
        return vendorDao.selectVendorByExample(example);
    }

    @Override
    public int countVendor() {
        VendorExample example = new VendorExample();
        return vendorDao.countVendorByExample(example);
    }

    @Override
    public List<Vendor> getVendorByExample(VendorExample example) {
        return vendorDao.selectVendorByExample(example);
    }

    @Override
    public int countVendorByExample(VendorExample example) {

        return vendorDao.countVendorByExample(example);
    }

    @Override
    public int deleteVendor(Long vendorPk) {
        return vendorDao.deleteVendorByPrimaryKey(vendorPk);
    }

    @Override
    public Object insertVendor(Vendor vendor) {
        return vendorDao.insertVendor(vendor);
    }

    @Override
    public int updateVendor(Vendor vendor) {
        return vendorDao.updateVendorByPrimaryKey(vendor);
    }

    @Override
    public List<Vendor> getVendorByExample(VendorExample example, int pageNo,
            int rowPerPage) {
        example.setLimitClause(setOffset(pageNo, rowPerPage));
        return vendorDao.selectVendorByExample(example);
    }

    @Override
    public Vendor getVendoryByPrimaryKey(Long vendorPk) {
        return vendorDao.selectVendorByPrimaryKey(vendorPk);
    }

    @Override
    public int countActiveVendorByName(String vendorName) {
        return vendorDao.countVendorByExample(getVendorExampleActiveVendorByName(vendorName));
    }

    @Override
    public List<Vendor> getActiveVendorByName(String vendorName, String limitClause, String orderByClause) {
        VendorExample example = getVendorExampleActiveVendorByName(vendorName);
        example.setLimitClause(limitClause);
        example.setOrderByClause(orderByClause);
        return vendorDao.selectVendorByExample(example);
    }

    private VendorExample getVendorExampleActiveVendorByName(String vendorName) {
        VendorExample example = new VendorExample();
        VendorExample.Criteria criteria = example.createCriteria();
        criteria.andVendornameLike(WOConstant.PERCENT + vendorName + WOConstant.PERCENT);
        criteria.andIsactiveEqualTo(true);
        return example;
    }
    
    @Override
    public List<Vendor> getVendorByVendorCodeAndIsAfterEventEqualsTrue(String vendorCode) {
        return vendorDao.selectByVendorCodeAndIsAfterEventEqualsTrue(vendorCode);
    }

    @Override
    public boolean vendorIsAfterEven(Long vendorPk) {
        boolean result = false;
        Vendor vendor = vendorDao.selectVendorByPrimaryKey(vendorPk);
        if(vendor != null && vendor.getIsAfterEvent() > 0) result = true;
        return result;
    }
}
