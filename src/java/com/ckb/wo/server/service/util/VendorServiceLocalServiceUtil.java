package com.ckb.wo.server.service.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExample;
import com.ckb.wo.server.service.IVendorServiceService;

public class VendorServiceLocalServiceUtil extends GenericServiceUtil {

    public static int countVendorService() {
        return getVendorServiceService().countVendorService();
    }

    public static List<VendorService> getVendorService() {
        return getVendorServiceService().getVendorService();
    }

    public static List<VendorService> getVendorServiceByExample(VendorServiceExample example) {
        return getVendorServiceService().getVendorServiceByExample(example);
    }

    public static List<VendorService> getVendorServiceByExampleWithJoin(VendorServiceExample example) {
        return getVendorServiceService().getVendorServiceByExampleWithJoin(example);
    }

    public static int countVendorServiceByExample(VendorServiceExample example) {
        return getVendorServiceService().countVendorServiceByExample(example);
    }

    public static int deleteVendorServiceByPrimaryKey(Long vendorservicePk) {
        return getVendorServiceService().deleteVendorService(vendorservicePk);
    }

    public static int updateVendorService(VendorService vendorservice) {
        return getVendorServiceService().updateVendorService(vendorservice);
    }

    public static Long insertVendorService(VendorService vendorservice) {
        return (Long) getVendorServiceService().insertVendorService(vendorservice);
    }

    public static VendorService getVendorServiceByPrimaryKey(Long vendorservicePk) {
        return getVendorServiceService().getVendorServiceyByPrimaryKey(vendorservicePk);
    }

    public static VendorService getVendorServiceByPrimaryKeyWithJoin(Long vendorservicePk) {
        return getVendorServiceService().getVendorServiceByPrimaryKeyWithJoin(vendorservicePk);
    }

    public static List<VendorService> getVendorServiceByVendorNameAndOtherRateAttributeWithJoin(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk, String limitClause, String orderByClause) {
        return getVendorServiceService().getVendorServiceByVendorNameAndOtherRateAttributeWithJoin(vendorName, origintlocation_fk, destinationtlocation_fk, tservicemodedetail_fk, limitClause, orderByClause);
    }

    public static int countByVendorNameAndOtherRateAttribute(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk) {
        return getVendorServiceService().countByVendorNameAndOtherRateAttribute(vendorName, origintlocation_fk, destinationtlocation_fk, tservicemodedetail_fk);
    }

    public static List<Vendor> getVendorByRateAttribute(Long origintlocation_fk, Long desttlocation_fk, Date execDate, Long tservicemodedetail_fk, Long tdeliveryterm_fk) {
        return getVendorServiceService().getVendorByRateAttribute(origintlocation_fk, desttlocation_fk, execDate, tservicemodedetail_fk, tdeliveryterm_fk);
    }

    public static VendorService getVendorServiceByAttributeForChargeableWeight(Long tvendor_fk, Long tservicemode_fk, Date execDate, Long origintlocation_fk, Long desttlocation_fk, BigDecimal weight, BigDecimal volume, Long tservicemodedetail_fk) {
        return getVendorServiceService().getVendorServiceByAttributeForChargeableWeight(tvendor_fk, tservicemode_fk, execDate, origintlocation_fk, desttlocation_fk, weight, volume, tservicemodedetail_fk);
    }

    public static boolean isVendorRateExists(VendorService vservice) {
        return getVendorServiceService().isVendorRateExists(vservice);
    }

    public static void validateVendorService(VendorService vservice) {
        getVendorServiceService().validateVendorRate(vservice);
    }

    public static List<VendorService> getVendorServiceByAttribute(Long origintlocation_fk, Long destinationtlocation_fk, Date executionDate, Long torder_fk, Long tservicemode_fk, Long tservicemodedetail_fk) {
        return getVendorServiceService().getVendorServiceByAttribute(origintlocation_fk, destinationtlocation_fk, executionDate, torder_fk, tservicemode_fk, tservicemodedetail_fk);
    }
    
    public static List<VendorService> getVendorServiceByAttributeWithTerm(Long origintlocation_fk, Long destinationtlocation_fk, Date executionDate, Long torder_fk, Long tservicemode_fk, Long tservicemodedetail_fk, Long tdeliveryterm_fk) {
        return getVendorServiceService().getVendorServiceByAttributeWithTerm(origintlocation_fk, destinationtlocation_fk, executionDate, torder_fk, tservicemode_fk, tservicemodedetail_fk, tdeliveryterm_fk);
    }

    private static IVendorServiceService getVendorServiceService() {
        return (IVendorServiceService) getBean("vendorserviceService");
    }
}
