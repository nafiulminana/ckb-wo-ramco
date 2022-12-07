package com.ckb.wo.server.dao.mda.ibatis.impl;

import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.MdaManifestExample;
import com.ckb.wo.server.dao.mda.MdaManifestDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MdaManifestDAOImpl extends SqlMapClientDaoSupport implements MdaManifestDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    public MdaManifestDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param example
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    @Override
    public int countMdaManifestByExample(MdaManifestExample example) {
        Integer count = (Integer) getSqlMapClientTemplate()
                .queryForObject("t_manifest.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param example
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MdaManifest> selectMdaManifestByExampleWithBLOBs(MdaManifestExample example) {
        List<MdaManifest> list = getSqlMapClientTemplate()
                .queryForList("t_manifest.ibatorgenerated_selectByExampleWithBLOBs", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param example
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MdaManifest> selectMdaManifestByExampleWithoutBLOBs(MdaManifestExample example) {
        List<MdaManifest> list = getSqlMapClientTemplate()
                .queryForList("t_manifest.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_manifest
     *
     * @param manifestNo
     * @return
     * @ibatorgenerated Tue Jul 06 16:43:03 SGT 2010
     */
    @Override
    public MdaManifest selectMdaManifestByPrimaryKey(Long manifestNo) {
        MdaManifest key = new MdaManifest();
        key.setManifestNo(manifestNo);
        MdaManifest record = (MdaManifest) getSqlMapClientTemplate().queryForObject("t_manifest.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }
    
    @Override
    public MdaManifest selectPickupDeliveryByPrimaryKey(Long manifestNo) {
        MdaManifest key = new MdaManifest();
        key.setManifestNo(manifestNo);
        MdaManifest record = (MdaManifest) getSqlMapClientTemplate().queryForObject("t_manifest.selectPickupDeliveryByPrimaryKey", key);
        return record;
    }
    
    private static final String PERCENT = "%";

    @Override
    public ManifestWeightVolumeDetail selectManifestWeightVolumeDetail(Long manifestNo) {
        return (ManifestWeightVolumeDetail) getSqlMapClientTemplate()
                .queryForObject("t_manifest.manifestweightvolumedetail", manifestNo);
    }

    @Override
    public ManifestWeightVolumeDetail selectPickupDeliveryWeightVolumeDetail(Long pickupDeliveryNo) {
        return (ManifestWeightVolumeDetail) getSqlMapClientTemplate()
                .queryForObject("t_manifest.pickupdeliveryweightvolumedetail", pickupDeliveryNo);
    }

    @Override
    public List<MdaManifest> selectByManifestNoWithLike(MdaManifestExample example) {
        List<MdaManifest> list = getSqlMapClientTemplate()
                .queryForList("t_manifest.selectByManifestNoWithLike", example);
        return list;
    }
}
