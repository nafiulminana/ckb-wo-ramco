package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorExample;
import com.ckb.wo.server.service.IVendorService;

public class VendorLocalServiceUtil extends GenericServiceUtil {

    public static int countVendor() {
        return getVendorService().countVendor();
    }

    public static List<Vendor> getVendor() {
        return getVendorService().getVendor();
    }

    public static List<Vendor> getActiveVendor() {
        return getVendorService().getActiveVendor();
    }

    public static List<Vendor> getVendorByExample(VendorExample example) {
        return getVendorService().getVendorByExample(example);
    }

    public static int countVendorByExample(VendorExample example) {
        return getVendorService().countVendorByExample(example);
    }

    public static int deleteVendorByPrimaryKey(Long vendorPk) {
        return getVendorService().deleteVendor(vendorPk);
    }

    public static int updateVendor(Vendor vendor) {
        return getVendorService().updateVendor(vendor);
    }

    public static Long insertVendor(Vendor vendor) {
        return (Long) getVendorService().insertVendor(vendor);
    }

    public static Vendor getVendorByPrimaryKey(Long vendorPk) {
        return getVendorService().getVendoryByPrimaryKey(vendorPk);
    }

    public static int countActiveVendorByName(String vendorName) {
        return getVendorService().countActiveVendorByName(vendorName);
    }

    public static List<Vendor> getActiveVendorByName(String vendorName, String limitClause, String orderByClause) {
        return getVendorService().getActiveVendorByName(vendorName, limitClause, orderByClause);
    }

    public static List<Vendor> getVendorByVendorCodeAndIsAfterEventEqualsOne(String vendorCode) {
        return getVendorService().getVendorByVendorCodeAndIsAfterEventEqualsTrue(vendorCode);
    }
    public static boolean vendorIsAfterEven(Long vendorPk){
        return getVendorService().vendorIsAfterEven(vendorPk);
    }

    private static IVendorService getVendorService() {
        return (IVendorService) getBean("vendorService");
    }

}
