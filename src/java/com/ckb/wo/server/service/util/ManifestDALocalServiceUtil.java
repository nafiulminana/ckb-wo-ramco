package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.MdaManifestDetail;
import com.ckb.wo.client.model.MdaManifestDetailExample;
import com.ckb.wo.client.model.MdaManifestExample;
import com.ckb.wo.client.model.Shipment;
import com.ckb.wo.server.service.IManifestDAService;

public class ManifestDALocalServiceUtil extends GenericServiceUtil {

    public static int countManifest() {
        return getManifestDAService().countManifest();
    }

    public static List<MdaManifest> getManifest() {
        return getManifestDAService().getManifest();
    }

    public static List<MdaManifest> getManifestByExample(MdaManifestExample example) {
        return getManifestDAService().getManifestByExample(example);
    }

    public static int countManifestByExample(MdaManifestExample example) {
        return getManifestDAService().countManifestByExample(example);
    }

    public static MdaManifest getManifestByPrimaryKey(Long manifestNo) {
        return getManifestDAService().getManifestByPrimaryKey(manifestNo);
    }

    public static int countDA() {
        return getManifestDAService().countManifestDetail();
    }

    public static List<MdaManifestDetail> getDA() {
        return getManifestDAService().getManifestDetail();
    }

    public static List<MdaManifestDetail> getDAByExample(MdaManifestDetailExample example) {
        return getManifestDAService().getManifestDetailByExample(example);
    }

    public static int countDAByExample(MdaManifestDetailExample example) {
        return getManifestDAService().countManifestDetailByExample(example);
    }

    public static MdaManifestDetail getDAByPrimaryKey(Long manifestNo, Long daNo) {
        return getManifestDAService().getManifestDetailByPrimaryKey(manifestNo, daNo);
    }

    public static ManifestWeightVolumeDetail getManifestWeightVolumeDetail(Long manifestNo) {
        return getManifestDAService().getManifestWeightVolumeDetail(manifestNo);
    }

    public static ManifestWeightVolumeDetail getPickupDeliveryWeightVolumeDetail(Long pickupDeliveryNo) {
        return getManifestDAService().getPickupDeliveryWeightVolumeDetail(pickupDeliveryNo);
    }

    public static List<MdaManifest> getManifestByManifestNoWithLike(String manifestNo, String limitClause) {
        return getManifestDAService().getManifestByManifestNoWithLike(manifestNo, limitClause);
    }

    public static int countManifestByManifestNoWithLike(String manifestNo) {
        return getManifestDAService().countManifestByManifestNoWithLike(manifestNo);
    }

    public static Shipment getShipmentNotPODByPrimaryKey(Long da) {
        return getManifestDAService().getShipmentNotPODByPrimaryKey(da);
    }

    public static Shipment getShipmentByPrimaryKey(Long da) {
        return getManifestDAService().getShipmentByPrimaryKey(da);
    }

    public static List<Shipment> getShipmentByDANoWithLike(String daNo, String limitClause, Long tvendor_fk, Long tlocation_fk) {
        return getShipmentByDANoWithLike(daNo, limitClause, tvendor_fk, tlocation_fk, false);
    }

    public static List<Shipment> getShipmentByDANoWithLike(String daNo, String limitClause, Long tvendor_fk, Long tlocation_fk, boolean isMPS) {
        return getManifestDAService().getShipmentByDANoWithLike(daNo, limitClause, tvendor_fk, tlocation_fk, isMPS);
    }

    public static int countShipmentByDANoWithLike(String daNo, boolean isMPS) {
        return getManifestDAService().countDAByDANoWithLike(daNo, isMPS);
    }

    public static boolean isManifestUsedInWO(Long manifestNo) {
        return getManifestDAService().isManifestUsedInWO(manifestNo);
    }

    public static boolean isDAUsedInWO(Long DANo, Long tvendor_fk, Long tlocation_fk) {
        return getManifestDAService().isDAUsedInWO(DANo, tvendor_fk, tlocation_fk);
    }

    private static IManifestDAService getManifestDAService() {
        return (IManifestDAService) getBean("manifestdaService");
    }
}
