package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.MdaManifestDetail;
import com.ckb.wo.client.model.MdaManifestDetailExample;
import com.ckb.wo.client.model.MdaManifestExample;
import com.ckb.wo.client.model.Shipment;
import com.ckb.wo.client.model.ShipmentExample;
import com.ckb.wo.client.model.WorkOrderDA;
import com.ckb.wo.server.dao.WorkOrderDADAO;
import com.ckb.wo.server.dao.WorkOrderDAO;
import com.ckb.wo.server.dao.WorkOrderManifestDAO;
import com.ckb.wo.server.dao.mda.MdaManifestDAO;
import com.ckb.wo.server.dao.mda.MdaManifestDetailDAO;
import com.ckb.wo.server.dao.mda.ShipmentDAO;
import com.ckb.wo.server.service.IManifestDAService;
import com.ckb.wo.server.service.factory.BeanFactory;
import com.ckb.wo.server.service.util.ManifestDALocalServiceUtil;
import java.util.ArrayList;
import java.util.Calendar;

public class ManifestDAServiceImpl extends GenericServiceImpl implements IManifestDAService {

    private final MdaManifestDAO mdamanifestDao;
    private final MdaManifestDetailDAO mdamanifestdetailDao;
    private ShipmentDAO shipmentDao;
    private static final String PERCENT = "%";
    private static final String COMPLETED = "COMPLETED";
    private static final String DRAFT = "DRAFT";
    private List<String> listValidManifestStatus = null;
    private final WorkOrderManifestDAO workordermanifestDao;
    private final WorkOrderDADAO workorderdaDao;
    private final WorkOrderDAO workorderDao;

    public ManifestDAServiceImpl(MdaManifestDAO mdamanifestDao, MdaManifestDetailDAO mdamanifestdetailDao, WorkOrderManifestDAO workordermanifestDao, WorkOrderDADAO workorderdaDao, WorkOrderDAO workorderDao) {
        this.mdamanifestDao = mdamanifestDao;
        this.mdamanifestdetailDao = mdamanifestdetailDao;
        this.workordermanifestDao = workordermanifestDao;
        this.workorderdaDao = workorderdaDao;
        this.workorderDao = workorderDao;
        listValidManifestStatus = new ArrayList<>();
        listValidManifestStatus.add(DRAFT);
        listValidManifestStatus.add(COMPLETED);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#getManifest()
     */
    @Override
    public List<MdaManifest> getManifest() {
        MdaManifestExample example = new MdaManifestExample();
        return mdamanifestDao.selectMdaManifestByExampleWithoutBLOBs(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#countManifest()
     */
    @Override
    public int countManifest() {
        MdaManifestExample example = new MdaManifestExample();
        return mdamanifestDao.countMdaManifestByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#getManifestByExample(com.ckb.wo.client.model.MdaManifestExample)
     */
    @Override
    public List<MdaManifest> getManifestByExample(MdaManifestExample example) {
        return mdamanifestDao.selectMdaManifestByExampleWithoutBLOBs(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#countManifestByExample(com.ckb.wo.client.model.MdaManifestExample)
     */
    @Override
    public int countManifestByExample(MdaManifestExample example) {
        return mdamanifestDao.countMdaManifestByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#getManifestByPrimaryKey(java.lang.Long)
     */
    @Override
    public MdaManifest getManifestByPrimaryKey(Long manifestNo) {
        return mdamanifestDao.selectMdaManifestByPrimaryKey(manifestNo);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#getManifestDetail()
     */
    @Override
    public List<MdaManifestDetail> getManifestDetail() {
        MdaManifestDetailExample example = new MdaManifestDetailExample();
        return mdamanifestdetailDao.selectMdaManifestDetailByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#countManifestDetail()
     */
    @Override
    public int countManifestDetail() {
        MdaManifestDetailExample example = new MdaManifestDetailExample();
        return mdamanifestdetailDao.countMdaManifestDetailByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#getManifestDetailByExample(com.ckb.wo.client.model.MdaManifestDetailExample)
     */
    @Override
    public List<MdaManifestDetail> getManifestDetailByExample(MdaManifestDetailExample example) {
        return mdamanifestdetailDao.selectMdaManifestDetailByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#countManifestDetailByExample(com.ckb.wo.client.model.MdaManifestDetailExample)
     */
    @Override
    public int countManifestDetailByExample(MdaManifestDetailExample example) {
        return mdamanifestdetailDao.countMdaManifestDetailByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ckb.wo.server.service.impl.IManifestDAService#getManifestDetailByPrimaryKey(java.lang.Long, java.lang.Long)
     */
    @Override
    public MdaManifestDetail getManifestDetailByPrimaryKey(Long manifestNo, Long daNo) {
        return mdamanifestdetailDao.selectMdaManifestDetailByPrimaryKey(manifestNo, daNo);
    }

    @Override
    public ManifestWeightVolumeDetail getManifestWeightVolumeDetail(Long manifestNo) {
        return mdamanifestDao.selectManifestWeightVolumeDetail(manifestNo);
    }

    @Override
    public ManifestWeightVolumeDetail getPickupDeliveryWeightVolumeDetail(Long pickupDeliveryNo) {
        return mdamanifestDao.selectPickupDeliveryWeightVolumeDetail(pickupDeliveryNo);
    }

    @Override
    public List<MdaManifest> getManifestByManifestNoWithLike(String manifestNo, String limitClause) {
        MdaManifestExample example = createCriteriaManifestNoLike(manifestNo);
        example.setLimitClause(limitClause);

        // change to filter MdaManifest that already used in WorkOrder
        List<MdaManifest> lmdaMan = mdamanifestDao.selectByManifestNoWithLike(example);
//        if (lmdaMan == null || lmdaMan.isEmpty()) {
//            return lmdaMan;
//        } else {
//            List<Long> newList = new ArrayList<Long>();
        for (MdaManifest mdaman : lmdaMan) {
//                newList.add(mdaman.getManifestNo());
            mdaman.setAlreadyUsedInWO(ManifestDALocalServiceUtil.isManifestUsedInWO(mdaman.getManifestNo()));
        }
//            ExtendedWorkOrderExample woex = new ExtendedWorkOrderExample();
//            woex.createCriteria().andWostatusNotEqualTo(WorkOrder.CANCELLED_STATUS);
//            List<Long> cancelledWO = workorderDao.selectWorkOrderPKOnlyByExample(woex);
//
//            WorkOrderManifestExample womanexample = new WorkOrderManifestExample();
//            WorkOrderManifestExample.Criteria crit = womanexample.createCriteria();
//            crit.andManifestNoIn(newList);
//            if (cancelledWO != null && !cancelledWO.isEmpty()) {
//                crit.andTworkorderFkIn(cancelledWO);
//            }
//
//            List<WorkOrderManifest> lwoman = workordermanifestDao.selectWorkOrderManifestByExample(womanexample);
//            if (lwoman != null) {
//                for (WorkOrderManifest woman : lwoman) {
//                    for (MdaManifest mdaman : lmdaMan) {
//                        if (mdaman.getManifestNo() != null) {
//                            if (mdaman.getManifestNo().equals(woman.getManifestNo())) {
//                                mdaman.setAlreadyUsedInWO(true);
//                            }
//                        }
//                    }
//                }
//                return lmdaMan;
//            } else {
//                return lmdaMan;
//            }
//        }
        return lmdaMan;
    }

    @Override
    public int countManifestByManifestNoWithLike(String manifestNo) {
        return mdamanifestDao.countMdaManifestByExample(createCriteriaManifestNoLike(manifestNo));
    }

    @Override
    public List<Shipment> getShipmentByDANoWithLike(String daNo, String limitClause, Long tvendor_fk, Long tlocation_fk) {
        return getShipmentByDANoWithLike(daNo, limitClause, tvendor_fk, tlocation_fk, false);
    }

    @Override
    public List<Shipment> getShipmentByDANoWithLike(String daNo, String limitClause, Long tvendor_fk, Long tlocation_fk, boolean isMPS) {
//        ShipmentExample example = createCriteriaDANoLike(daNo);
//        example.setLimitClause(limitClause);
        List<Shipment> lShipment = getShipmentDao().selectShipmentByDAMPSNoWithLike(daNo, limitClause, null, isMPS);

        if (lShipment == null || lShipment.isEmpty()) {
            return lShipment;
        } else {
            List<Long> listDAPk = new ArrayList<>();
            for (Shipment sp : lShipment) {
                if (sp.getDa() != null) {
                    listDAPk.add(sp.getDa());
                }
            }
            // now we get the list, check with tworkorder_da table
            List<WorkOrderDA> lwoDA = workorderdaDao.selectAlreadyUsedDA(listDAPk, tvendor_fk, tlocation_fk);
            if (lwoDA == null || lwoDA.isEmpty()) {
                return lShipment;
            } else {
                for (WorkOrderDA woDA : lwoDA) {
                    for (Shipment shipment : lShipment) {
                        if (woDA.getDa().equals(shipment.getDa())) {
                            shipment.setAlreadyUsedInWO(true);
                        }
                    }
                }
                return lShipment;
            }
        }
    }

    @Override
    public int countDAByDANoWithLike(String daNo, boolean isMPS) {
        return getShipmentDao().countShipmentByExample(daNo, isMPS);
//        return getShipmentDao().countShipmentByExample(createCriteriaDANoLike(daNo));
    }

    private ShipmentExample createCriteriaDANoLike(String daNo) {
        ShipmentExample example = new ShipmentExample();
        example.createChildCriteria().andDANoLike(addPercentToParameter(daNo));
        return example;
    }

    @Override
    public Shipment getShipmentNotPODByPrimaryKey(Long da) {
        return getShipmentDao().selectShipmentNotPODByPrimaryKey(da);
    }

    @Override
    public Shipment getShipmentByPrimaryKey(Long da) {
        return getShipmentDao().selectShipmentByPrimaryKey(da);
    }

//    public List<Shipment> getShipmentByExample(ShipmentExample example) {
//        return getShipmentDao().selectShipmentByExampleWithoutBLOBs(example);
//    }
    private MdaManifestExample createCriteriaManifestNoLike(String manifestNo) {
        MdaManifestExample example = new MdaManifestExample();
        Calendar d = Calendar.getInstance();
        d.add(Calendar.MONTH, -3);
        example.createChildCriteria().andManifestNoLike(addPercentToParameter(manifestNo)).andManifestStatusIn(listValidManifestStatus).andShipDateGreaterThan(d.getTime()).andVendorOnly();
        return example;
    }

    @Override
    public boolean isManifestUsedInWO(Long manifestNo) {
//        ExtendedWorkOrderExample woex = new ExtendedWorkOrderExample();
//        List<String> list = new ArrayList();
//        list.add(WorkOrder.CANCELLED_STATUS);
//        list.add(WorkOrder.CANCEL_RECEIVED_STATUS);

//        woex.createCriteria().andWostatusNotIn(list);
//        List<Long> cancelledWO = workorderDao.selectWorkOrderPKOnlyByExample(woex);
//        WorkOrderManifestExample example = new WorkOrderManifestExample();
//        WorkOrderManifestExample.Criteria crit = example.createCriteria();
//        crit.andManifestNoEqualTo(manifestNo);
//        if (cancelledWO != null && !cancelledWO.isEmpty()) {
//            crit.andTworkorderFkIn(cancelledWO);
//        }
//        List<WorkOrderManifest> lmanifest = workordermanifestDao.selectWorkOrderManifestByExample(example);
//        if (lmanifest == null || lmanifest.isEmpty()) {
        int count = workordermanifestDao.countUsedManifest(manifestNo);
        return count > 0;
    }

    @Override
    public boolean isDAUsedInWO(Long DANo, Long tvendor_fk, Long tlocation_fk) {
//        ExtendedWorkOrderDAExample example = new ExtendedWorkOrderDAExample();
//        ExtendedWorkOrderDAExample.ExtendedCriteria crit1 = (ExtendedWorkOrderDAExample.ExtendedCriteria) example.createCriteria();
//        crit1.andDaEqualTo(DANo);
//        crit1.andTVendorFkEqualTo(tvendor_fk);
//        crit1.andTLocationFkEqualTo(tlocation_fk);
        List<Long> lnoDA = new ArrayList<>();
        lnoDA.add(DANo);

        List<WorkOrderDA> lwoda = workorderdaDao.selectAlreadyUsedDA(lnoDA, tvendor_fk, tlocation_fk);
        return !(lwoda == null || lwoda.isEmpty());
    }

    private String addPercentToParameter(String parameter) {
        return PERCENT + parameter + PERCENT;
    }

    public ShipmentDAO getShipmentDao() {
        if (shipmentDao == null) {
            shipmentDao = (ShipmentDAO) BeanFactory.getBean("shipmentDao");
        }
        return shipmentDao;
    }

    public void setShipmentDao(ShipmentDAO shipmentDao) {
        this.shipmentDao = shipmentDao;
    }
}
