package com.ckb.wo.server.dao.mda;

import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.MdaManifestExample;
import java.util.List;

public interface MdaManifestDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param example
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    int countMdaManifestByExample(MdaManifestExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param example
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    List<MdaManifest> selectMdaManifestByExampleWithBLOBs(MdaManifestExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param example
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    List<MdaManifest> selectMdaManifestByExampleWithoutBLOBs(MdaManifestExample example);

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param manifestNo
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    MdaManifest selectMdaManifestByPrimaryKey(Long manifestNo);
    
    MdaManifest selectPickupDeliveryByPrimaryKey(Long manifestNo);

    public ManifestWeightVolumeDetail selectManifestWeightVolumeDetail(Long manifestNo);

    public ManifestWeightVolumeDetail selectPickupDeliveryWeightVolumeDetail(Long pickupDeliveryNo);

    List<MdaManifest> selectByManifestNoWithLike(MdaManifestExample example);

}
