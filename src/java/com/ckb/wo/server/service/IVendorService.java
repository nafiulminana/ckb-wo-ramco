package com.ckb.wo.server.service;

import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import java.util.List;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorExample;
import com.ckb.wo.client.model.WorkOrder;

public interface IVendorService {

    public List<Vendor> getActiveVendor();

    public List<Vendor> getVendor();

    public int deleteVendor(Long vendorPk);

    public Object insertVendor(Vendor vendor);

    public int updateVendor(Vendor vendor);

    public int countVendor();

    public List<Vendor> getVendorByExample(VendorExample example);

    public List<Vendor> getVendorByExample(VendorExample example, int pageNo, int rowPerPage);

    public int countVendorByExample(VendorExample example);

    public Vendor getVendoryByPrimaryKey(Long vendorPk);

    int countActiveVendorByName(String vendorName);

    List<Vendor> getActiveVendorByName(String vendorName, String limitClause, String orderByClause);

    List<Vendor> getVendorByVendorCodeAndIsAfterEventEqualsTrue(String vendorCode);
    
    boolean vendorIsAfterEven(Long vendorPk);
    

}
