package com.ckb.wo.server.dao.ibatis.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ckb.wo.client.model.ExtendedVendorServiceExample;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExample;
import com.ckb.wo.client.model.VendorServiceExt;
import com.ckb.wo.server.dao.VendorServiceDAO;
import com.ckb.wo.server.dao.ibatis.rowhandler.ChargeableWeightRowHandler;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;

public class VendorServiceDAOImpl extends SqlMapClientDaoSupport implements VendorServiceDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    public VendorServiceDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int countVendorServiceByExample(VendorServiceExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tvendorservice.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int deleteVendorServiceByExample(VendorServiceExample example) {
        int rows = getSqlMapClientTemplate().delete("tvendorservice.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param tvendorservicePk
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int deleteVendorServiceByPrimaryKey(Long tvendorservicePk) {
        VendorService key = new VendorService();
        key.setTvendorservicePk(tvendorservicePk);
        int rows = getSqlMapClientTemplate().delete("tvendorservice.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public Long insertVendorService(VendorService record) {
        Object newKey = getSqlMapClientTemplate().insert("tvendorservice.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public Long insertVendorServiceSelective(VendorService record) {
        Object newKey = getSqlMapClientTemplate().insert("tvendorservice.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param example
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<VendorService> selectVendorServiceByExample(VendorServiceExample example) {
        List<VendorService> list = getSqlMapClientTemplate().queryForList("tvendorservice.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param tvendorservicePk
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public VendorService selectVendorServiceByPrimaryKey(Long tvendorservicePk) {
        VendorService key = new VendorService();
        key.setTvendorservicePk(tvendorservicePk);
        VendorService record = (VendorService) getSqlMapClientTemplate().queryForObject("tvendorservice.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int updateVendorServiceByExampleSelective(VendorService record, VendorServiceExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tvendorservice.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param record
     * @param example
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int updateVendorServiceByExample(VendorService record, VendorServiceExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tvendorservice.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int updateVendorServiceByPrimaryKeySelective(VendorService record) {
        int rows = getSqlMapClientTemplate().update("tvendorservice.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tvendorservice
     *
     * @param record
     * @return
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    @Override
    public int updateVendorServiceByPrimaryKey(VendorService record) {
        int rows = getSqlMapClientTemplate().update("tvendorservice.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator. This class corresponds
     * to the database table tvendorservice
     *
     * @ibatorgenerated Wed Aug 11 08:16:42 SGT 2010
     */
    private static class UpdateByExampleParms extends VendorServiceExample {

        private Object record;

        public UpdateByExampleParms(Object record, VendorServiceExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VendorService> selectVendorServiceByExampleWithJoin(VendorServiceExample example) {
        List<VendorService> list = getSqlMapClientTemplate().queryForList("tvendorservice.selectByExampleWithJoin", example);
        return list;
    }

    @Override
    public VendorService selectVendorServiceByPrimaryKeyWithJoin(Long tvendorservicePk) {
        VendorService key = new VendorService();
        key.setTvendorservicePk(tvendorservicePk);
        VendorService record = (VendorService) getSqlMapClientTemplate().queryForObject("tvendorservice.selectByPrimaryKeyWithJoin", key);
        return record;
    }

    @Override
    public List<VendorService> selectByVendorNameAndOtherRateAttributeWithJoin(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk, String limitClause, String orderByClause) {
        ExtendedVendorServiceExample example = getExampleByVendorNameAndOtherRateAttribute(vendorName, origintlocation_fk, destinationtlocation_fk, tservicemodedetail_fk);
        example.setLimitClause(limitClause);
        example.setOrderByClause(orderByClause);
        List<VendorService> list = getSqlMapClientTemplate().queryForList("tvendorservice.selectByVendorNameAndOtherRateAttributeWithJoin", example);
        return list;
    }

    @Override
    public int countByVendorNameAndOtherRateAttribute(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk) {
        ExtendedVendorServiceExample example = getExampleByVendorNameAndOtherRateAttribute(vendorName, origintlocation_fk, destinationtlocation_fk, tservicemodedetail_fk);
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tvendorservice.countByVendorNameAndOtherRateAttribute", example);
        return count;
    }

    private ExtendedVendorServiceExample getExampleByVendorNameAndOtherRateAttribute(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk) {
        ExtendedVendorServiceExample example = new ExtendedVendorServiceExample();
        ExtendedVendorServiceExample.ExtendedCriteria crit = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit.andVendorNameLike(vendorName);
        crit.andEnabled(true);
        // only select active vendor
        crit.andIsactiveEqualTo(true);
        if (origintlocation_fk != null) {
            crit.andOrigintlocationFkEqualTo(origintlocation_fk);
        }
        if (destinationtlocation_fk != null) {
            crit.andDestinationtlocationFkEqualTo(destinationtlocation_fk);
        }
        if (tservicemodedetail_fk != null) {
            crit.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        }
        return example;
    }

    @Override
    public List<Vendor> selectVendorByRateAttribute(Long origintlocation_fk, Long desttlocation_fk, Date execDate, Long tservicemodedetail_fk, Long tdeliveryterm_fk) {
        VendorServiceExample example = new VendorServiceExample();
        VendorServiceExample.Criteria crit = example.createCriteria();
        crit.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit.andDestinationtlocationFkEqualTo(desttlocation_fk);
        crit.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit.andValidfromLessThanOrEqualTo(execDate);
        crit.andValidtoGreaterThanOrEqualTo(execDate);
        crit.andTorderFkEqualTo(1L);
        crit.andWeightfromIsNotNull();
        crit.andWeighttoIsNotNull();
        crit.andEnabled(true);
        crit.andTdeliverytermFkEqualTo(tdeliveryterm_fk);
        List<Vendor> list = getSqlMapClientTemplate().queryForList("tvendorservice.selectVendorByServiceAttribute", example);
        return list;
    }

    @Override
    public VendorService selectVendorServiceByAttribute(Long tvendorServiceId, Long tservicemode_fk, Date execDate, Long origintlocation_fk, Long desttlocation_fk, Long torder_fk, BigDecimal weight, Long tservicemodedetail_fk) {
        VendorService vserv = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKeyWithJoin(tvendorServiceId);
        ExtendedVendorServiceExample example = new ExtendedVendorServiceExample();
        ExtendedVendorServiceExample.ExtendedCriteria crit = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit.andTvendorFkEqualTo(vserv.getTvendorFk());
        crit.andTservicemodeFkEqualTo(tservicemode_fk);
        crit.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit.andDestinationtlocationFkEqualTo(desttlocation_fk);
        crit.andValidfromLessThanOrEqualTo(execDate);
        crit.andValidtoGreaterThanOrEqualTo(execDate);
        crit.andTorderFkEqualTo(torder_fk);
        crit.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit.andIsactiveEqualTo(true);
        //add by shido
        crit.andWeightfromLessThanOrEqualTo(weight);
        crit.andWeighttoGreaterThanOrEqualTo(weight);
        if (vserv.getRemarks() != null && !vserv.getRemarks().isEmpty()) {
            crit.andRemarksEqualTo(vserv.getRemarks());
        }
        crit.andEnabled(true);
        crit.andTdeliverytermFkEqualTo(vserv.getTdeliverytermFk());

        ExtendedVendorServiceExample.ExtendedCriteria crit2 = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit2.andTvendorFkEqualTo(vserv.getTvendorFk());
        crit2.andTservicemodeFkEqualTo(tservicemode_fk);
        crit2.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit2.andDestinationtlocationFkEqualTo(desttlocation_fk);
        crit2.andValidfromLessThanOrEqualTo(execDate);
        crit2.andValidtoGreaterThanOrEqualTo(execDate);
        crit2.andTorderFkEqualTo(torder_fk);
        crit2.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit2.andIsactiveEqualTo(false);
        crit2.andVendorInactiveDateExpired();
        //add by shido
        crit2.andWeightfromLessThanOrEqualTo(weight);
        crit2.andWeighttoGreaterThanOrEqualTo(weight);
        if (vserv.getRemarks() != null && !vserv.getRemarks().isEmpty()) {
            crit2.andRemarksEqualTo(vserv.getRemarks());
        }
        crit2.andEnabled(true);
        crit2.andTdeliverytermFkEqualTo(vserv.getTdeliverytermFk());
        example.or(crit2);

        ChargeableWeightRowHandler crh = new ChargeableWeightRowHandler();
        crh.setWeight(weight);
        getSqlMapClientTemplate().queryWithRowHandler("tvendorservice.selectByExampleWithJoin", example, crh);
        List<VendorServiceExt> lvserv = crh.getListVendorService();
        if (lvserv.size() > 0) {
            return lvserv.get(0);
        } else {
            return null;
        }
    }
}
