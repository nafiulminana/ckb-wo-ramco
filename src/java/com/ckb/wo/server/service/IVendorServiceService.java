package com.ckb.wo.server.service;

import com.ckb.wo.server.exception.ValidationException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExample;

public interface IVendorServiceService {

    public List<VendorService> getVendorService();

    public int countVendorService();

    public List<VendorService> getVendorServiceByExample(VendorServiceExample example);

    public int countVendorServiceByExample(VendorServiceExample example);

    public int deleteVendorService(Long vendorservicePk);

    public Object insertVendorService(VendorService vendorservice);

    public int updateVendorService(VendorService vendorservice);

    public List<VendorService> getVendorServiceByExample(VendorServiceExample example, int pageNo, int rowPerPage);

    public VendorService getVendorServiceyByPrimaryKey(Long vendorservicePk);

    public VendorService getVendorServiceByPrimaryKeyWithJoin(Long vendorservicePk);

    public VendorService getVendorServiceByPrimaryKey(Long vendorservicePk);

    public List<VendorService> getVendorServiceByExampleWithJoin(VendorServiceExample example);

    public List<VendorService> getVendorServiceByVendorNameAndOtherRateAttributeWithJoin(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk, String limitClause, String orderByClause);

    public int countByVendorNameAndOtherRateAttribute(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk);

    public List<Vendor> getVendorByRateAttribute(Long origintlocation_fk, Long desttlocation_fk, Date execDate, Long tservicemodedetail_fk, Long tdeliveryterm_fk);

    public VendorService getVendorServiceByAttributeForChargeableWeight(Long tvendorServiceId, Long tservicemode_fk, Date execDate, Long origintlocation_fk, Long desttlocation_fk, BigDecimal weight, BigDecimal volume, Long tservicemodedetail_fk);

    boolean isVendorRateExists(VendorService vservice);

    void validateVendorRate(VendorService vservice) throws ValidationException;

    public List<VendorService> getVendorServiceByAttribute(Long origintlocation_fk, Long destinationtlocation_fk, Date executionDate, Long torder_fk, Long tservicemode_fk, Long tservicemodedetail_fk);
    
    public List<VendorService> getVendorServiceByAttributeWithTerm(Long origintlocation_fk, Long destinationtlocation_fk, Date executionDate, Long torder_fk, Long tservicemode_fk, Long tservicemodedetail_fk, Long tdeliveryterm_fk);

}
