package com.ckb.wo.server.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.ExtendedVendorServiceExample;
import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.OrderExample;
import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExample;
import com.ckb.wo.server.dao.OrderDAO;
import com.ckb.wo.server.dao.ServiceModeDetailDAO;
import com.ckb.wo.server.dao.VendorServiceDAO;
import com.ckb.wo.server.exception.ValidationException;
import com.ckb.wo.server.service.IChargeableWeightCalculatorService;
import com.ckb.wo.server.service.IVendorServiceService;
import com.ckb.wo.server.service.constant.WOConstant;

/**
 * @author Admin
 *
 */
public class VendorServiceServiceImpl extends GenericServiceImpl implements IVendorServiceService {

    private final VendorServiceDAO vendorserviceDao;
    private final IChargeableWeightCalculatorService chargeableweightcalculatorService;
    private final OrderDAO orderDao;
    private final ServiceModeDetailDAO servicemodedetailDao;

    public VendorServiceServiceImpl(VendorServiceDAO vendorserviceDao, IChargeableWeightCalculatorService chargeableweightcalculatorService, OrderDAO orderDao, ServiceModeDetailDAO servicemodedetailDao) {
        this.vendorserviceDao = vendorserviceDao;
        this.chargeableweightcalculatorService = chargeableweightcalculatorService;
        this.orderDao = orderDao;
        this.servicemodedetailDao = servicemodedetailDao;
    }

    @Override
    public List<VendorService> getVendorService() {
        VendorServiceExample example = new VendorServiceExample();
        return vendorserviceDao.selectVendorServiceByExample(example);
    }

    @Override
    public int countVendorService() {
        VendorServiceExample example = new VendorServiceExample();
        return vendorserviceDao.countVendorServiceByExample(example);
    }

    @Override
    public List<VendorService> getVendorServiceByExample(VendorServiceExample example) {
        return vendorserviceDao.selectVendorServiceByExample(example);
    }

    @Override
    public List<VendorService> getVendorServiceByExampleWithJoin(VendorServiceExample example) {
        return vendorserviceDao.selectVendorServiceByExampleWithJoin(example);
    }

    @Override
    public VendorService getVendorServiceByPrimaryKey(Long vendorservicePk) {
        return vendorserviceDao.selectVendorServiceByPrimaryKey(vendorservicePk);
    }

    @Override
    public VendorService getVendorServiceByPrimaryKeyWithJoin(Long vendorservicePk) {
        return vendorserviceDao.selectVendorServiceByPrimaryKeyWithJoin(vendorservicePk);
    }

    @Override
    public int countVendorServiceByExample(VendorServiceExample example) {
        return vendorserviceDao.countVendorServiceByExample(example);
    }

    @Override
    public int deleteVendorService(Long vendorservicePk) {
        return vendorserviceDao.deleteVendorServiceByPrimaryKey(vendorservicePk);
    }

    @Override
    public Object insertVendorService(VendorService vendorservice) {
        return vendorserviceDao.insertVendorService(vendorservice);
    }

    @Override
    public int updateVendorService(VendorService vendorservice) {
        return vendorserviceDao.updateVendorServiceByPrimaryKey(vendorservice);
    }

    @Override
    public List<VendorService> getVendorServiceByExample(VendorServiceExample example, int pageNo, int rowPerPage) {
        example.setLimitClause(setOffset(pageNo, rowPerPage));
        return vendorserviceDao.selectVendorServiceByExample(example);
    }

    @Override
    public VendorService getVendorServiceyByPrimaryKey(Long vendorservicePk) {
        return vendorserviceDao.selectVendorServiceByPrimaryKey(vendorservicePk);
    }

    @Override
    public List<VendorService> getVendorServiceByVendorNameAndOtherRateAttributeWithJoin(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk, String limitClause, String orderByClause) {
        return vendorserviceDao.selectByVendorNameAndOtherRateAttributeWithJoin(WOConstant.PERCENT + vendorName + WOConstant.PERCENT, origintlocation_fk, destinationtlocation_fk, tservicemodedetail_fk, limitClause, orderByClause);
    }

    @Override
    public int countByVendorNameAndOtherRateAttribute(String vendorName, Long origintlocation_fk, Long destinationtlocation_fk, Long tservicemodedetail_fk) {
        return vendorserviceDao.countByVendorNameAndOtherRateAttribute(WOConstant.PERCENT + vendorName + WOConstant.PERCENT, origintlocation_fk, destinationtlocation_fk, tservicemodedetail_fk);
    }

    @Override
    public List<Vendor> getVendorByRateAttribute(Long origintlocation_fk, Long desttlocation_fk, Date execDate, Long tservicemodedetail_fk, Long tdeliveryterm_fk) {
        return vendorserviceDao.selectVendorByRateAttribute(origintlocation_fk, desttlocation_fk, execDate, tservicemodedetail_fk, tdeliveryterm_fk);
    }

    @Override
    public VendorService getVendorServiceByAttributeForChargeableWeight(Long tvendorServiceId, Long tservicemode_fk, Date execDate, Long origintlocation_fk, Long desttlocation_fk, BigDecimal weight, BigDecimal volume, Long tservicemodedetail_fk) {
        BigDecimal convertedWeight = BigDecimal.ZERO;
        Long torder_fk = null;
        OrderExample example = new OrderExample();
        example.createCriteria().andOrdercodeEqualTo("CHARGEABLEWEIGHT");
        List<Order> lorder = orderDao.selectOrderByExample(example);
        if (lorder != null && lorder.size() > 0) {
            torder_fk = lorder.get(0).getTorderPk();
        } else {
            throw new ValidationException("table torder doesn't have entry with ordercode : CHARGEABLEWEIGHT");
        }
        convertedWeight = chargeableweightcalculatorService.calculateChargeableWeight(weight, volume, tservicemode_fk);

        return vendorserviceDao.selectVendorServiceByAttribute(tvendorServiceId, tservicemode_fk, execDate, origintlocation_fk, desttlocation_fk, torder_fk, convertedWeight, tservicemodedetail_fk);
    }

    private VendorServiceExample.Criteria createBasicCriteriaForDuplicate(VendorServiceExample example, VendorService vservice) {
        VendorServiceExample.Criteria crit = example.createCriteria();
        crit.andTvendorFkEqualTo(vservice.getTvendorFk());
        crit.andOrigintlocationFkEqualTo(vservice.getOrigintlocationFk());
        crit.andDestinationtlocationFkEqualTo(vservice.getDestinationtlocationFk());
        crit.andRateEqualTo(vservice.getRate());
        crit.andTservicemodeFkEqualTo(vservice.getTservicemodeFk());
        crit.andTservicemodedetailFkEqualTo(vservice.getTservicemodedetailFk());
        if (vservice.getRefagreementno() != null && vservice.getRefagreementno().length() > 0) {
            crit.andRefagreementnoEqualTo(vservice.getRefagreementno());
        }
        crit.andRemarksEqualTo(vservice.getRemarks());
        return crit;
    }

    /*
     * this method shows how to use conjunction OR inside and : where
     * (conditionA equals A and condition B equals B ... and ( condition x
     * equals x OR condition y equals y ) ) -> becomes : where (conditionA
     * equals A and conditionB equals B... and conditionX equals x ) OR
     * (conditionA equals A and conditionB equals B... and conditionY equals Y)
     */
    @Override
    public boolean isVendorRateExists(VendorService vservice) {

        VendorServiceExample example = new VendorServiceExample();
        // for kilo basis, need to  check weight from and weight to as well
        boolean isKiloBasis = false;

        ServiceModeDetail smDetail = servicemodedetailDao.selectServiceModeDetailByPrimaryKey(vservice.getTservicemodedetailFk());
        if (smDetail != null) {
            if ("KGS".equalsIgnoreCase(smDetail.getSmdcode())) {
                isKiloBasis = true;
            }
        }
        VendorServiceExample.Criteria crit1 = createBasicCriteriaForDuplicate(example, vservice);
//        crit1.andValidfromBetween(vservice.getValidfrom(), vservice.getValidto());
        crit1.andValidfromEqualTo(vservice.getValidfrom());

        if (isKiloBasis) {
            crit1.andWeightfromBetween(vservice.getWeightfrom(), vservice.getWeightto());
            VendorServiceExample.Criteria crit3 = createBasicCriteriaForDuplicate(example, vservice);
//            crit3.andValidfromBetween(vservice.getValidfrom(), vservice.getValidto());
            crit3.andValidfromEqualTo(vservice.getValidfrom());
            crit3.andWeighttoBetween(vservice.getWeightfrom(), vservice.getWeightto());

            example.or(crit3);
        }

        VendorServiceExample.Criteria crit2 = createBasicCriteriaForDuplicate(example, vservice);
//        crit2.andValidtoBetween(vservice.getValidfrom(), vservice.getValidto());
        crit2.andValidtoEqualTo(vservice.getValidto());

        if (isKiloBasis) {
            crit2.andWeightfromBetween(vservice.getWeightfrom(), vservice.getWeightto());

            VendorServiceExample.Criteria crit4 = createBasicCriteriaForDuplicate(example, vservice);
//            crit4.andValidtoBetween(vservice.getValidfrom(), vservice.getValidto());
            crit4.andValidtoEqualTo(vservice.getValidto());
            crit4.andWeighttoBetween(vservice.getWeightfrom(), vservice.getWeightto());

            example.or(crit4);
        }

        example.or(crit2);

        List<VendorService> lvserv = vendorserviceDao.selectVendorServiceByExample(example);

        if (vservice.getTvendorservicePk() == null) {
            // check for unexistence vendorservice (insert new )
            if (lvserv != null && lvserv.size() > 0) {
                return true;
            }
        } else {
            // check for existing vendor service ( edit / update )
            if (lvserv != null) {
                if (lvserv.size() > 0) {
                    if (lvserv.size() == 1) {
                        VendorService vserv = lvserv.get(0);
                        return !vserv.getTvendorservicePk().equals(vservice.getTvendorservicePk());
                    } else {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void validateVendorRate(VendorService vservice) throws ValidationException {
        boolean isValid = true;
        StringBuilder sb = new StringBuilder();
        if (vservice.getTvendorFk() == null) {
            sb.append("vendor,");
            isValid = false;
        }
        if (vservice.getTservicetypeFk() == null) {
            sb.append("service type,");
            isValid = false;
        }
        if (vservice.getTservicemodeFk() == null) {
            sb.append("service mode,");
            isValid = false;
        }
        if (vservice.getTservicemodedetailFk() == null) {
            sb.append("service mode detail,");
            isValid = false;
        }
        if (vservice.getTorderFk() == null) {
            sb.append("order,");
            isValid = false;
        }
        if (vservice.getTdeliverytermFk() == null) {
            sb.append("delivery term,");
            isValid = false;
        }
        if (vservice.getOrigintlocationFk() == null) {
            sb.append("origin,");
            isValid = false;
        }
        if (vservice.getDestinationtlocationFk() == null) {
            sb.append("destination,");
            isValid = false;
        }
        if (vservice.getTcurrencyFk() == null) {
            sb.append("currency,");
            isValid = false;
        }
        if (vservice.getRate() == null) {
            sb.append("rate");
            isValid = false;
        }
        if (vservice.getValidfrom() == null) {
            sb.append("valid from,");
            isValid = false;
        }
        if (vservice.getValidto() == null) {
            sb.append("valid to,");
            isValid = false;
        }

        if (vservice.getRefagreementno() == null
                || vservice.getRefagreementno().length() == 0) {
            sb.append("Ref agreement no.,");
            isValid = false;
        }

        if (vservice.getRefagreementdate() == null
                || vservice.getRefagreementno().length() == 0) {
            sb.append("Ref agreement date.,");
            isValid = false;
        }

        if (!isValid) {
            sb.append(" is(are) mandatory");
            throw new ValidationException(sb.toString());
        }
    }

    @Override
    public List<VendorService> getVendorServiceByAttribute(Long origintlocation_fk, Long destinationtlocation_fk, Date executionDate, Long torder_fk, Long tservicemode_fk, Long tservicemodedetail_fk) {

        ExtendedVendorServiceExample example = new ExtendedVendorServiceExample();
        ExtendedVendorServiceExample.ExtendedCriteria crit1 = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit1.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit1.andDestinationtlocationFkEqualTo(destinationtlocation_fk);
        crit1.andValidfromLessThan(executionDate);
        crit1.andValidtoGreaterThanOrEqualTo(executionDate);
        crit1.andTorderFkEqualTo(torder_fk);
        crit1.andTservicemodeFkEqualTo(tservicemode_fk);
        crit1.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit1.andIsactiveEqualTo(true);
        crit1.andEnabled(true);

        ExtendedVendorServiceExample.ExtendedCriteria crit2 = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit2.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit2.andDestinationtlocationFkEqualTo(destinationtlocation_fk);
        crit2.andValidfromLessThan(executionDate);
        crit2.andValidtoGreaterThanOrEqualTo(executionDate);
        crit2.andTorderFkEqualTo(torder_fk);
        crit2.andTservicemodeFkEqualTo(tservicemode_fk);
        crit2.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit2.andIsactiveEqualTo(false);
        crit2.andVendorInactiveDateExpired();
        crit2.andEnabled(true);
        example.or(crit2);
        example.setOrderByClause("vendorname,rate ASC");
        return vendorserviceDao.selectVendorServiceByExampleWithJoin(example);

    }

    @Override
    public List<VendorService> getVendorServiceByAttributeWithTerm(Long origintlocation_fk, Long destinationtlocation_fk, Date executionDate, Long torder_fk, Long tservicemode_fk, Long tservicemodedetail_fk, Long tdeliveryterm_fk) {
        ExtendedVendorServiceExample example = new ExtendedVendorServiceExample();
        ExtendedVendorServiceExample.ExtendedCriteria crit1 = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit1.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit1.andDestinationtlocationFkEqualTo(destinationtlocation_fk);
        crit1.andValidfromLessThan(executionDate);
        crit1.andValidtoGreaterThanOrEqualTo(executionDate);
        crit1.andTorderFkEqualTo(torder_fk);
        crit1.andTservicemodeFkEqualTo(tservicemode_fk);
        crit1.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit1.andIsactiveEqualTo(true);
        crit1.andEnabled(true);
        crit1.andTdeliverytermFkEqualTo(tdeliveryterm_fk);

        ExtendedVendorServiceExample.ExtendedCriteria crit2 = (ExtendedVendorServiceExample.ExtendedCriteria) example.createCriteria();
        crit2.andOrigintlocationFkEqualTo(origintlocation_fk);
        crit2.andDestinationtlocationFkEqualTo(destinationtlocation_fk);
        crit2.andValidfromLessThan(executionDate);
        crit2.andValidtoGreaterThanOrEqualTo(executionDate);
        crit2.andTorderFkEqualTo(torder_fk);
        crit2.andTservicemodeFkEqualTo(tservicemode_fk);
        crit2.andTservicemodedetailFkEqualTo(tservicemodedetail_fk);
        crit2.andIsactiveEqualTo(false);
        crit2.andVendorInactiveDateExpired();
        crit2.andEnabled(true);
        crit1.andTdeliverytermFkEqualTo(tdeliveryterm_fk);
        example.or(crit2);
        example.setOrderByClause("vendorname,rate ASC");
        return vendorserviceDao.selectVendorServiceByExampleWithJoin(example);
    }
}
