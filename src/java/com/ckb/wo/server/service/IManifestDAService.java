package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.MdaManifestDetail;
import com.ckb.wo.client.model.MdaManifestDetailExample;
import com.ckb.wo.client.model.MdaManifestExample;
import com.ckb.wo.client.model.Shipment;

public interface IManifestDAService {

    public Shipment getShipmentNotPODByPrimaryKey(Long da);

    public Shipment getShipmentByPrimaryKey(Long da);

    public abstract List<MdaManifest> getManifest();

    public abstract int countManifest();

    public abstract List<MdaManifest> getManifestByExample(MdaManifestExample example);

    public abstract int countManifestByExample(MdaManifestExample example);

    public abstract MdaManifest getManifestByPrimaryKey(Long manifestNo);

    public abstract List<MdaManifestDetail> getManifestDetail();

    public abstract int countManifestDetail();

    public abstract List<MdaManifestDetail> getManifestDetailByExample(MdaManifestDetailExample example);

    public abstract int countManifestDetailByExample(MdaManifestDetailExample example);

    public abstract MdaManifestDetail getManifestDetailByPrimaryKey(Long manifestNo, Long daNo);

    public ManifestWeightVolumeDetail getManifestWeightVolumeDetail(Long manifestNo);

    public ManifestWeightVolumeDetail getPickupDeliveryWeightVolumeDetail(Long pickupDeliveryNo);

    public List<MdaManifest> getManifestByManifestNoWithLike(String manifestNo, String limitClause);

    public int countManifestByManifestNoWithLike(String manifestNo);

//    public List<Shipment> getShipmentByExample(ShipmentExample example);
    public List<Shipment> getShipmentByDANoWithLike(String daNo, String limitClause, Long tvendor_fk, Long tlocation_fk);

    public List<Shipment> getShipmentByDANoWithLike(String daNo, String limitClause, Long tvendor_fk, Long tlocation_fk, boolean isMPS);

    public int countDAByDANoWithLike(String daNo, boolean isMPS);

    boolean isDAUsedInWO(Long DANo, Long tvendor_fk, Long tlocation_fk);

    boolean isManifestUsedInWO(Long manifestNo);

}
