package com.ckb.wo.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.LevelExample;
import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.Notes;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.WoDaManStatus;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderDA;
import com.ckb.wo.client.model.WorkOrderDAExample;
import com.ckb.wo.client.model.WorkOrderExample;
import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.client.model.WorkOrderFlowExample;
import com.ckb.wo.client.model.WorkOrderHistory;
import com.ckb.wo.client.model.WorkOrderHistoryExample;
import com.ckb.wo.client.model.WorkOrderManifest;
import com.ckb.wo.client.model.WorkOrderManifestExample;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.client.model.WorkOrderServiceModeDetailExample;
import com.ckb.wo.server.dao.LevelDAO;
import com.ckb.wo.server.dao.WorkOrderDADAO;
import com.ckb.wo.server.dao.WorkOrderDAO;
import com.ckb.wo.server.dao.WorkOrderHistoryDAO;
import com.ckb.wo.server.dao.WorkOrderManifestDAO;
import com.ckb.wo.server.dao.WorkOrderServiceModeDetailDAO;
import com.ckb.wo.server.dao.mda.MdaManifestDAO;
import com.ckb.wo.server.exception.ValidationException;
import com.ckb.wo.server.service.IUserService;
import com.ckb.wo.server.service.IWorkOrderFlowService;
import com.ckb.wo.server.service.IWorkOrderService;
import com.ckb.wo.server.service.WorkOrderMailService;
import com.ckb.wo.server.service.factory.BeanFactory;
import com.ckb.wo.server.service.util.LevelLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import com.shido.jdbc2orm.JDBCConnector;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import org.apache.log4j.Logger;
import static com.ckb.wo.server.service.impl.WorkOrderServiceImpl.LOG;
import com.ckb.wo.server.service.util.GlobalServiceUtil;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import java.sql.SQLException;

public class WorkOrderServiceImpl extends GenericServiceImpl implements IWorkOrderService {

    final static Logger LOG = Logger.getLogger(WorkOrderServiceImpl.class);

    private final WorkOrderDAO workorderDao;
    private WorkOrderDADAO workorderdaDao;
    private WorkOrderManifestDAO workordermanifestDao;
    private WorkOrderServiceModeDetailDAO workorderservicemodedetailDao;
    private LevelDAO levelDao;
    private WorkOrderMailService workOrderMailService;
    private IWorkOrderFlowService workorderflowService;
    private WorkOrderHistoryDAO workorderhistoryDao;
    private IUserService userService;
    private MdaManifestDAO mdamanifestDao;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final String WO = "WO";
    private static final String DASH = "-";
    private static final String ZEROONE = "001";
    private static final String ENDNUMBERFORMAT = "%03d";

    public WorkOrderServiceImpl(WorkOrderDAO workorderDao) {
        super();
        this.workorderDao = workorderDao;
    }

    @Override
    public List<WorkOrder> getWorkOrder() {
        WorkOrderExample example = new WorkOrderExample();
        return workorderDao.selectWorkOrderByExampleWithBLOBs(example);
    }

    @Override
    public int countWorkOrder() {
        WorkOrderExample example = new WorkOrderExample();
        return workorderDao.countWorkOrderByExample(example);
    }

    @Override
    public List<WorkOrder> getWorkOrderByExample(WorkOrderExample example) {
        if (example == null) {
            return null;
        }
        return workorderDao.selectWorkOrderByExampleWithBLOBs(example);
    }

    @Override
    public int countWorkOrderByExample(WorkOrderExample example) {
        if (example == null) {
            return 0;
        }
        return workorderDao.countWorkOrderByExample(example);
    }

    @Override
    public int deleteWorkOrder(Long workorderPk) {
        // delete tworkorder_da
        WorkOrderDAExample woDAexample = new WorkOrderDAExample();
        woDAexample.createCriteria().andTworkorderFkEqualTo(workorderPk);
        getWorkorderdaDao().deleteWorkOrderDAByExample(woDAexample);
        // delete from tworkorder_manifest
        WorkOrderManifestExample woManifestExample = new WorkOrderManifestExample();
        woManifestExample.createCriteria().andTworkorderFkEqualTo(workorderPk);
        getWorkordermanifestDao().deleteWorkOrderManifestByExample(woManifestExample);
        // delete from tworkorder_smodedetail
        WorkOrderServiceModeDetailExample wosmodedetailExample = new WorkOrderServiceModeDetailExample();
        wosmodedetailExample.createCriteria().andTworkorderFkEqualTo(workorderPk);
        getWorkorderservicemodedetailDao().deleteWorkOrderServiceModeDetailByExample(wosmodedetailExample);
        // delete from tworkorderhistory
        WorkOrderHistoryExample woHistoryExample = new WorkOrderHistoryExample();
        woHistoryExample.createCriteria().andTworkorderFkEqualTo(workorderPk);
        getWorkorderhistoryDao().deleteWorkOrderHistoryByExample(woHistoryExample);
        // delete from tworkorderflow
        WorkOrderFlowExample woflowExample = new WorkOrderFlowExample();
        woflowExample.createCriteria().andTworkorderFkEqualTo(workorderPk);
        getWorkorderflowService().deleteWorkOrderFlowByExample(woflowExample);
        // delete from tworkorder
        return workorderDao.deleteWorkOrderByPrimaryKey(workorderPk);
    }

    public void deleteWorkOrderByExample(WorkOrderExample example) {
        List<WorkOrder> listWO = workorderDao.selectWorkOrderByExampleWithoutBLOBs(example);
        for (WorkOrder wo : listWO) {
            workorderDao.deleteWorkOrderByPrimaryKey(wo.getTworkorderPk());
        }
    }

    private boolean isUserOnLeave(User user) {
        Date today = new Date();
        if (user.getGrantfrom() == null || user.getGrantto() == null) {
            return false;
        } else {
            int iFrom = today.compareTo(user.getGrantfrom());
            int iTo = today.compareTo(user.getGrantto());
            return iFrom >= 0 && iTo <= 0;
        }
    }
//Bug Fix Amount Double
//Date : 19-07-2017
//Perubahan Untuk mencegah double Amount
//Insert Method Prevention

    @Override
    public Object insertWorkOrder(WorkOrder workorder, String employeeId) throws SecurityException {

//Initial Connector -- Bug Fix Amount Double
//        JDBCConnector ConCheckSmoDetail = new JDBCConnector();
        boolean adhoc = false;
        User user = getUserService().getUserByPrimaryKey(employeeId);
        User bossUser = getUserService().getUserByPrimaryKey(user.getBossemployeeid());
        if (bossUser == null) {
            throw new ValidationException("This user doesn't have a boss. Please assign a boss to this user so workflow can continue");
        }
        if (isUserOnLeave(bossUser)) {
            workorder.setIsgranted(true);
            bossUser = getNextAvailableBossForThisUser(user.getEmployeeId());
        } else {
            workorder.setIsgranted(false);
        }
        Integer currentUserLevel = userService.getUserLevelValueByEmployeeId(employeeId);
        Integer nextUserLevel = userService.getUserLevelValueByEmployeeId(user.getBossemployeeid());
        if (currentUserLevel == null) {
            throw new ValidationException("invalid user level. User may not mapped correctly to tlevel");
        }
        if (nextUserLevel == null) {
            throw new ValidationException("invalid next user level. User may not mapped correctly to tlevel");
        }

        workorder.setCreateddate(new Date());
        workorder.setCreatedbyemployeeid(employeeId);

        workorder.setLasttlevel(currentUserLevel);
        // nextlevel always lastlevel+1, regardless nextuser onleave or not
//		workorder.setNexttlevel(currentUserLevel.intValue() + 1);
        workorder.setNexttlevel(nextUserLevel);

        if (workorder.getMaxlevel() == null) {
            throw new ValidationException("Please specify approval type");
        }
        if (workorder.getWostatus() != null) {
            if (!workorder.getWostatus().equalsIgnoreCase("APPROVED")) {
                if (workorder.getAdhoc() != null && workorder.getAdhoc()) {
                    workorder.setWostatus(WorkOrder.VALIDATION_STATUS);
                    adhoc = true;
                } else {
//                workorder.setWostatus(WorkOrder.INPROGRESS_STATUS);
                    workorder.setWostatus(WorkOrder.APPROVED_STATUS);
                    adhoc = false;
                }
            } else if (workorder.getWostatus().equalsIgnoreCase("APPROVED") && (workorder.getAdhoc() == null || workorder.getAdhoc())) {
                workorder.setWostatus(WorkOrder.VALIDATION_STATUS);
                adhoc = true;
            } else {
                workorder.setMaxlevel(0);
                workorder.setNexttlevel(0);
            }
        } else {
            if (workorder.getAdhoc() != null && workorder.getAdhoc()) {
                workorder.setWostatus(WorkOrder.VALIDATION_STATUS);
                adhoc = true;
            } else {
//                workorder.setWostatus(WorkOrder.INPROGRESS_STATUS);
                workorder.setWostatus(WorkOrder.VALIDATION_STATUS);
                workorder.setMaxlevel(0);
                workorder.setNexttlevel(0);
                adhoc = false;
            }
        }

        workorder.setNomorwo(generateWorkOrderNumber());
        Object o = workorderDao.insertWorkOrder(workorder);
        if (workorder.getWorkOrderDA().size() > 0) {
            for (WorkOrderDA wda : workorder.getWorkOrderDA()) {
                wda.setTworkorderFk(workorder.getTworkorderPk());
                getWorkorderdaDao().insertWorkOrderDA(wda);
            }
        }
        if (workorder.getWorkOrderManifest().size() > 0) {
            HashMap<Long, WorkOrderManifest> hmapwoman = new HashMap<>(workorder.getWorkOrderManifest().size() * 2);
            for (WorkOrderManifest womanifest : workorder.getWorkOrderManifest()) {
                hmapwoman.put(womanifest.getManifestNo(), womanifest);
            }

            if (hmapwoman.size() > 0) {
                Collection<WorkOrderManifest> coll = hmapwoman.values();
                for (WorkOrderManifest woman : coll) {
                    woman.setTworkorderFk(workorder.getTworkorderPk());
                    getWorkordermanifestDao().insertWorkOrderManifest(
                            woman);
                }
            }
            hmapwoman = null;
        }
        if (workorder.getWorkOrderServiceModeDetail().size() > 0) {
            for (WorkOrderServiceModeDetail wosmdetail : workorder.getWorkOrderServiceModeDetail()) {
                wosmdetail.setTworkorderFk(workorder.getTworkorderPk());
                getWorkorderservicemodedetailDao().insertWorkOrderServiceModeDetail(wosmdetail);
                //Filter Prevention -- Bug Fix Amount Double
//                System.out.println("WO Fk:" + wosmdetail.getTworkorderFk() + " tservicemodedetail_fk:" + wosmdetail.getTservicemodedetailFk() + " Qty:" + wosmdetail.getQuantity() + " "
//                        + "Smd_Remarks:" + wosmdetail.getSmdremarks() + " Smd_Charge:" + wosmdetail.getSmdcharge() + " smdtcurrency_fk: " + wosmdetail.getSmdtcurrencyFk() + " smddetailname:" + wosmdetail.getSmddetailname() + " deleted:" + wosmdetail.isDeleted());
//
//
//                try {
//                    ResultMap rsCheck = ConCheckSmoDetail.getSingleResult("SELECT * FROM `tworkorder_smodedetail` tws WHERE  tws.`tworkorder_fk` = " + wosmdetail.getTworkorderFk() + " "
//                            + "AND tws.`tservicemodedetail_fk` = " + wosmdetail.getTservicemodedetailFk() + " "
//                            + "AND tws.`deleted` = 0");
//
//                    System.out.println("SELECT * FROM `tworkorder_smodedetail` tws WHERE  tws.`tworkorder_fk` = " + wosmdetail.getTworkorderFk() + " "
//                            + "AND tws.`tservicemodedetail_fk` = " + wosmdetail.getTservicemodedetailFk() + " "
//                            + "AND tws.`deleted` = 0");
//                    if (rsCheck == null) {
//                        getWorkorderservicemodedetailDao().insertWorkOrderServiceModeDetail(wosmdetail);
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(WorkOrderServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                }
            }
        }
        WorkOrderFlow workorderflow = new WorkOrderFlow();
        workorderflow.setActiondate(new Date());
        workorderflow.setActiondone(true);
        workorderflow.setEmployeeid(employeeId);
        workorderflow.setGranted(false);
        workorderflow.setLevelId(user.getLevelId());
        workorderflow.setTworkorderFk(workorder.getTworkorderPk());
        workorderflow.setOldwostatus(WorkOrder.NEW_STATUS);
        getWorkorderflowService().insertWorkOrderFlow(workorderflow);
        if (workorder.getWostatus() != null) {
            if (workorder.getWostatus().equalsIgnoreCase("APPROVED")) {
                workorderflow = new WorkOrderFlow();
                workorderflow.setActiondate(new Date());
                workorderflow.setActiondone(true);
                //workorderflow.setEmployeeid(employeeId);
                workorderflow.setEmployeeid("system");
                workorderflow.setGranted(false);
                //workorderflow.setLevelId(user.getLevelId());
                workorderflow.setLevelId("SYS");
                workorderflow.setTworkorderFk(workorder.getTworkorderPk());
                workorderflow.setOldwostatus(WorkOrder.APPROVED_STATUS);
                getWorkorderflowService().insertWorkOrderFlow(workorderflow);
                if (workorder.getWorkOrderDA().size() < 1) {
                    
                    if( ! GlobalServiceUtil.IS_DEVELOPMENT ){
                        JDBCConnector con = new JDBCConnector("fast");
                        String strSQL = "replace into t_manifest_wo(manifest_no,nomorwo,tworkorder_pk, employee_id,insda) values('%d','" + workorder.getNomorwo() + "','" + workorder.getTworkorderPk() + "','" + workorder.getCreatedbyemployeeid() + "',now())";
                        for (WorkOrderManifest wom : workorder.getWorkOrderManifest()) {
                            try {
                                con.executeNonQuery(String.format(strSQL, wom.getManifestNo()));
                                LOG.info(strSQL);
                            } catch (SQLException e) {
                                LOG.error(e.getMessage(), e);
                                LOG.error("Can not insert or update t_manifest_wo because " + e.getMessage());
                            }
                        }
                    }                    
                }
            }
        }
        LOG.info(user.getEmployeeId() + " " + workorder.toString());
        LOG.info(user.getEmployeeId() + " " + workorder.getNomorwo() + " " + workorderflow.toString());
        LOG.info("Work Order is inserted");
        // send email
        // check status first, adhoc or not
        //if (adhoc) {
        //getWorkOrderMailService().sendNotificationForCostValidation(workorder);
        //dimatikan sesuai permintaan
        //} else {
        //getWorkOrderMailService().sendNotificationForApproval(workorder, bossUser.getEmail());
        //}
        return o;
    }

    @Override
    public void insertWorkOrderFlowForDelegation(WorkOrder workOrder, String reason, User currentUser, String woStatus) {

        int currentLevel = getUserService().getUserLevelValueByEmployeeId(currentUser.getEmployeeId());
        int nextLevel = workOrder.getNexttlevel();
        WorkOrderFlow workorderflow = new WorkOrderFlow();
        workorderflow.setActiondate(new Date());
        workorderflow.setActiondone(true);
        workorderflow.setEmployeeid(currentUser.getEmployeeId());
        workorderflow.setGranted(workOrder.getIsgranted());
        workorderflow.setLevelId(currentUser.getLevelId());
        workorderflow.setTworkorderFk(workOrder.getTworkorderPk());
        workorderflow.setReason(reason);
        workorderflow.setOldwostatus(woStatus);

//        int lastlevel = workOrder.getLasttlevel();
//                int nextlevel = lastlevel + 1;
        int maxlevel = workOrder.getMaxlevel();
//		if( (lastlevel+1)==currentLevel || currentLevel < nextLevel){
        if (currentLevel <= nextLevel) {
            // it means there's no delegation
            workorderflow.setGranted(false);
            getWorkorderflowService().insertWorkOrderFlow(workorderflow);
        } else {
            // there's a delegation
            User grantorUser = getUserService().getNextUserForThisWorkOrder(workOrder.getTworkorderPk());
            for (int i = nextLevel; i <= maxlevel; i++) {
                workorderflow.setTworkorderflowPk(null);
                workorderflow.setEmployeeid(currentUser.getEmployeeId());
                if (i == currentLevel) {
                    workorderflow.setGranted(false);
                    workorderflow.setOnbehalfemployeeid(null);
                    // level on workorderflow is always the level of the grantor
                    workorderflow.setLevelId(currentUser.getLevelId());
                } else {
                    workorderflow.setGranted(true);
                    workorderflow.setOnbehalfemployeeid(grantorUser.getEmployeeId());
                    workorderflow.setLevelId(grantorUser.getLevelId());
                }

                if (WorkOrder.APPROVED_STATUS.equalsIgnoreCase(woStatus)) {
                    if (i == maxlevel) {
                        workorderflow.setOldwostatus(WorkOrder.APPROVED_STATUS);
                    } else {
                        workorderflow.setOldwostatus(WorkOrder.INPROGRESS_STATUS);
                    }
                }

                getWorkorderflowService().insertWorkOrderFlow(workorderflow);
                grantorUser = getUserService().getUserByPrimaryKeyWithJoin(grantorUser.getBossemployeeid());
                if (grantorUser == null) {
                    break;
                }
            }
        }
    }

    @Override
    public void approveWorkOrder(Long workorderPk, String reason, String employeeId) throws SecurityException {
        System.out.println("approveWorkOrder");
        WorkOrder workOrder = getWorkOrderyByPrimaryKeyWithJoin(workorderPk);
        boolean isNextUserOnLeave = false;
        User currentUser = getUserService().getUserByPrimaryKeyWithJoin(employeeId);
        User nextUser = getUserService().getUserByPrimaryKeyWithJoin(currentUser.getBossemployeeid());
        boolean isDirector = false;

        isDirector = currentUser.getLevel().getLevelValue() >= LevelLocalServiceUtil.getLevelByPrimaryKey("DIR").getLevelValue();

        if (!isDirector) {
            isNextUserOnLeave = isUserOnLeave(nextUser);
            if (isNextUserOnLeave) {
                nextUser = getNextAvailableBossForThisUser(currentUser);
            }
        }
        boolean isFlowEnded = false;

        if (currentUser.getLevel().getLevelValue() >= workOrder.getMaxlevel()) {
            isFlowEnded = true;
        } else if (isDirector && isUserOnLeave(nextUser) && workOrder.getNexttlevel().compareTo(nextUser.getLevel().getLevelValue()) == 0) {
            isFlowEnded = true;
        }

        // insert to workflow
        String woStatus = null;
        if (isFlowEnded) {
            woStatus = WorkOrder.APPROVED_STATUS;
        } else {
            woStatus = WorkOrder.INPROGRESS_STATUS;
        }
        insertWorkOrderFlowForDelegation(workOrder, reason, currentUser, woStatus);
        // end of insert to workflow

        // set last level and next level
        workOrder.setLasttlevel(currentUser.getLevel().getLevelValue());
        if (isFlowEnded) {
            // end of flow
            workOrder.setWostatus(WorkOrder.APPROVED_STATUS);
        } else {
            if (nextUser == null) {
                throw new ValidationException("next user couldn't be found from the system. Assign appropriate user to the hierarchy");
            }
            workOrder.setWostatus(WorkOrder.INPROGRESS_STATUS);
//			workOrder.setNexttlevel(workOrder.getLasttlevel()+1);
            workOrder.setNexttlevel(nextUser.getLevel().getLevelValue());

            if (isNextUserOnLeave) {
                workOrder.setIsgranted(true);
            } else {
                workOrder.setIsgranted(false);
            }
        }
        updateWorkOrder(workOrder, employeeId);

        if (!isFlowEnded) {
            getWorkOrderMailService().sendNotificationForApproval(workOrder, nextUser.getEmail());
        } else {
            //Addition send WO flag to FAST
//            WorkOrder workOrder = getWorkOrderyByPrimaryKeyWithJoin(workorderPk);

            if (!workOrder.getServiceType().getServicename().equalsIgnoreCase("Handling") && ! GlobalServiceUtil.IS_DEVELOPMENT ) {
                JDBCConnector con = new JDBCConnector("fast");
                String strSQL = "replace into t_manifest_wo(manifest_no,nomorwo,tworkorder_pk, employee_id,insda) values('%d','" + workOrder.getNomorwo() + "','" + workOrder.getTworkorderPk() + "','" + workOrder.getCreatedbyemployeeid() + "',now())";
                for (WorkOrderManifest wom : workOrder.getWorkOrderManifest()) {
                    try {
                        con.executeNonQuery(String.format(strSQL, wom.getManifestNo()));
                        LOG.info(strSQL);
                    } catch (SQLException e) {
                        LOG.error(e.getMessage(), e);
                        LOG.error("Can not insert or update t_manifest_wo because " + e.getMessage());
                    }
                }
            }
            //end Addition
            //  kirim email ke si pembuat
            User creatorUser = getUserService().getUserByPrimaryKey(workOrder.getCreatedbyemployeeid());
            getWorkOrderMailService().sendNotificationWOHasBeenApproved(workOrder, creatorUser.getEmail());
        }
    }

    @Override
    public String generateWorkOrderNumber() {
        StringBuilder sb = new StringBuilder();
        String like = sdf.format(new Date());
        WorkOrderExample example = new WorkOrderExample();
        example.createCriteria().andNomorwoLike(like);
        example.setOrderByClause(" tworkorder_pk desc");
        List<WorkOrder> lwo = workorderDao.selectWorkOrderByExampleWithoutBLOBs(example);
        if (lwo != null && lwo.size() > 0) {
            WorkOrder wo = lwo.get(0);
            String wo_no = wo.getNomorwo();
            int pos = wo_no.indexOf(DASH, 4);
            if (pos == -1) {
                sb.append(WO).append(DASH).append(like).append(DASH).append(
                        ZEROONE);
            } else {
                String value = wo_no.substring(pos, wo_no.length());
                String endNumber = null;
                try {
                    Integer intWono = Integer.parseInt(value);
                    endNumber = String.format(ENDNUMBERFORMAT, intWono + 1);
                } catch (NumberFormatException e) {
                    endNumber = ZEROONE;
                }
                sb.append(WO).append(DASH).append(like).append(DASH).append(
                        endNumber);
            }
        } else {
            sb.append(WO).append(DASH).append(like).append(DASH).append(ZEROONE);
        }
        return sb.toString();
    }

    @Override
    public void editWorkOrder(Long workOrderPk, String reason, String employeeId)
            throws SecurityException {
        WorkOrder workOrder = getWorkOrderyByPrimaryKeyWithJoin(workOrderPk);
        User currentUser = getUserService().getUserByPrimaryKey(employeeId);

        // WorkOrderFlow
        insertWorkOrderFlowForDelegation(workOrder, reason, currentUser, WorkOrder.EDIT_STATUS);

        // ubah status ke edit
        workOrder.setWostatus(WorkOrder.EDIT_STATUS);
        // isi edit reason
        workOrder.setEditreason(reason);
        // counter edit ditambah 1
        if (workOrder.getEditcounter() != null) {
            workOrder.setEditcounter(workOrder.getEditcounter() + 1);
        } else {
            workOrder.setEditcounter(1);
        }
        // calculate lastlevel dan next level
        int lastlevel = getUserService().getUserLevelValueByEmployeeId(employeeId);
        int nextlevel = getUserService().getUserLevelValueByEmployeeId(workOrder.getCreatedbyemployeeid());
        workOrder.setLasttlevel(lastlevel);
        workOrder.setNexttlevel(nextlevel);
        //save workOrder
        updateWorkOrder(workOrder, employeeId);

        // kirim email ke user pembuat
        User userCreatorWO = getUserService().getUserByPrimaryKey(workOrder.getCreatedbyemployeeid());
        getWorkOrderMailService().sendNotificationForEdit(workOrder, userCreatorWO.getEmail());
    }

    // tidak dipanggil dr luar
    @Override
    public void revisiWorkOrder(WorkOrder workOrder, String reason, String employeeId) throws SecurityException {
        // di workorderflow, diset statusnya revisi

        //WorkOrder workOrder = getWorkOrderyByPrimaryKey(workOrderPk);
        User currentUser = getUserService().getUserByPrimaryKey(employeeId);
        User creatorUser = getUserService().getUserByPrimaryKey(workOrder.getCreatedbyemployeeid());
        // ubah status ke revisi
        WorkOrderFlow woflow = new WorkOrderFlow();
        woflow.setActiondate(new Date());
        woflow.setActiondone(true);
        woflow.setEmployeeid(employeeId);
        woflow.setGranted(false);
        woflow.setLevelId(currentUser.getLevelId());
        woflow.setOldwostatus(WorkOrder.REVISION_STATUS);
        woflow.setReason(workOrder.getRevisionreason());
        woflow.setTworkorderFk(workOrder.getTworkorderPk());
        getWorkorderflowService().insertWorkOrderFlow(woflow);
        // kirim email ke atasan si pembuat
        User bossCreatorUser = getNextAvailableBossForThisUser(creatorUser);
        getWorkOrderMailService().sendNotificationForRevision(workOrder, bossCreatorUser.getEmail());

    }

    @Override
    public void receivedAPWorkOrder(Long workorderPk, String employeeId) {
        LOG.info("Processing Received AP");
        User user = getUserService().getUserByPrimaryKey(employeeId);
        WorkOrder workOrder = getWorkOrderyByPrimaryKey(workorderPk);
        boolean cancelReceive = workOrder.getWostatus().equals(WorkOrder.CANCELLED_STATUS) && workOrder.getCancellationfee() != null;
        workOrder.setWostatus(cancelReceive ? WorkOrder.CANCEL_RECEIVED_AP : WorkOrder.RECEIVED_AP);
        updateWorkOrder(workOrder, employeeId);
        WorkOrderFlow woflow = new WorkOrderFlow();
        woflow.setActiondate(new Date());
        woflow.setActiondone(true);
        woflow.setEmployeeid(employeeId);
        woflow.setGranted(false);
        woflow.setLevelId(user.getLevelId());
        woflow.setOldwostatus(cancelReceive ? WorkOrder.CANCEL_RECEIVED_AP : WorkOrder.RECEIVED_AP);
        woflow.setTworkorderFk(workorderPk);
        getWorkorderflowService().insertWorkOrderFlow(woflow);
    }

    @Override
    public void receivedTreasuryWorkOrder(Long workorderPk, String employeeId) {
        User user = getUserService().getUserByPrimaryKey(employeeId);

        WorkOrder workOrder = getWorkOrderyByPrimaryKey(workorderPk);
        boolean cancelreceived = workOrder.getWostatus().equalsIgnoreCase(WorkOrder.CANCEL_RECEIVED_AP);
        workOrder.setWostatus(cancelreceived ? WorkOrder.CANCEL_RECEIVED_STATUS : WorkOrder.RECEIVED_STATUS);
        updateWorkOrder(workOrder, employeeId);

        WorkOrderFlow woflow = new WorkOrderFlow();
        woflow.setActiondate(new Date());
        woflow.setActiondone(true);
        woflow.setEmployeeid(employeeId);
        woflow.setGranted(false);
        woflow.setLevelId(user.getLevelId());

        woflow.setOldwostatus(cancelreceived ? WorkOrder.CANCEL_RECEIVED_STATUS : WorkOrder.RECEIVED_STATUS);
        woflow.setTworkorderFk(workorderPk);
        getWorkorderflowService().insertWorkOrderFlow(woflow);
    }

    @Override
    public void cancelWorkOrder(Long workOrderPk, String reason, BigDecimal cancellationfee, Long tcurrencyPk, String employeeId) throws SecurityException {
        User currentUser = getUserService().getUserByPrimaryKey(employeeId);
        WorkOrder workOrder = getWorkOrderyByPrimaryKey(workOrderPk);
        workOrder.setCancellationfee(cancellationfee);
        workOrder.setCancellationtcurrencyfk(tcurrencyPk);
        workOrder.setWostatus(WorkOrder.CANCELLED_STATUS);
        workOrder.setCancelreason(reason);
        updateWorkOrder(workOrder, employeeId);
        //save to :workorderflow
        insertWorkOrderFlowForDelegation(workOrder, reason, currentUser, WorkOrder.CANCELLED_STATUS);

        // send email to creator
        User creatorUser = getUserService().getUserByPrimaryKey(workOrder.getCreatedbyemployeeid());
        getWorkOrderMailService().sendNotificationForCancel(workOrder, creatorUser.getEmail());
    }

    @Override
    public void cancelWorkOrderAP(Long workOrderPk, String reason, BigDecimal cancellationfee, Long tcurrencyPk, String employeeId) throws SecurityException {
        LOG.info("Entering Service to cancel WO by AP " + workOrderPk);
        User currentUser = getUserService().getUserByPrimaryKey(employeeId);
        WorkOrder workOrder = getWorkOrderyByPrimaryKey(workOrderPk);

        //update workorder status
        workOrder.setCancellationfee(cancellationfee);
        workOrder.setCancellationtcurrencyfk(tcurrencyPk);
        workOrder.setWostatus(WorkOrder.CANCELLED_AP);
        workOrder.setCancelreason(reason);
        updateWorkOrder(workOrder, employeeId);
        LOG.info("WO was updated");

        //insert into workorderflow
        insertWorkOrderFlowForDelegation(workOrder, reason, currentUser, WorkOrder.CANCELLED_AP);
        LOG.info("WO Flow was added");
        
        // delete data in db_workorder.tworkorder_da 
        WorkOrderDAExample woDAexample = new WorkOrderDAExample();
        woDAexample.createCriteria().andTworkorderFkEqualTo(workOrderPk);
        getWorkorderdaDao().deleteWorkOrderDAByExample(woDAexample);
        LOG.info("Work Order Da was Deleted");
        
        if( ! GlobalServiceUtil.IS_DEVELOPMENT ){
            try {
                //delete from t_manifest_wo
                final JDBCConnector fcon = new JDBCConnector("fast");
                fcon.executeNonQuery("delete from t_manifest_wo where tworkorder_pk='" + workOrderPk + "'");
                LOG.info("WO " + workOrderPk + " was deleted from t_manifest_wo");
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }       

        //insert into notes
        Notes note = new Notes();
        note.setNoteType("CANCELED");
        note.setEmployeeId(employeeId);
        note.setNotes(reason);
        note.setTworkorderFk(workOrderPk);
        NotesLocalServiceUtil.saveNotes(note);
        LOG.info("Note is saved");

        // send email to creator
        User creatorUser = getUserService().getUserByPrimaryKey(workOrder.getCreatedbyemployeeid());
        try {
            getWorkOrderMailService().sendNotificationForCancel(workOrder, creatorUser.getEmail());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            getWorkOrderMailService().sendNotificationForCancel(workOrder, currentUser.getEmail());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            getWorkOrderMailService().sendNotificationForCancel(workOrder, creatorUser.getBoss().getEmail());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public int updateWorkOrder(WorkOrder workOrder, String employeeId) {
        return workorderDao.updateWorkOrderByPrimaryKeyWithBLOBs(workOrder);
    }

    /*
     * This method is being used in revision, edit, and cost validation on
     * workorderlistener
     */
    @Override
    public int updateWorkOrderWithChildren(WorkOrder workorder, String employeeId) throws SecurityException, ValidationException {
        // ubah status ke validation/in_progress
        boolean isFromRevision = false;
        boolean isFromEdit = false;
        boolean isFromValidation = false;
        //Initial Connector -- Bug Fix Amount Double
//        JDBCConnector ConCheckSmoDetail = new JDBCConnector();
        WorkOrder oldWorkOrder = getWorkOrderyByPrimaryKeyWithJoin(workorder.getTworkorderPk());
        String changes = oldWorkOrder.getChanges(workorder);
        WorkOrderHistory woHistory = new WorkOrderHistory();
        woHistory.setAction(workorder.getWostatus());
        woHistory.setActionby(employeeId);
        woHistory.setActiondate(new Date());
        woHistory.setChanges(changes);
        woHistory.setTworkorderFk(workorder.getTworkorderPk());
        getWorkorderhistoryDao().insertWorkOrderHistory(woHistory);
        User currentUser = getUserService().getUserByPrimaryKeyWithJoin(employeeId);
        User nextUser = getUserService().getUserByPrimaryKeyWithJoin(oldWorkOrder.getWostatus().equals(WorkOrder.VALIDATION_STATUS) ? oldWorkOrder.getCreatedbyemployeeid() : currentUser.getBossemployeeid());

        if (WorkOrder.REVISION_STATUS.equalsIgnoreCase(workorder.getWostatus())) {
            isFromRevision = true;
            revisiWorkOrder(workorder, workorder.getRevisionreason(), employeeId);
        } else {
            if (WorkOrder.EDIT_STATUS.equalsIgnoreCase(workorder.getWostatus())) {
                isFromEdit = true;
            }
            if (WorkOrder.VALIDATION_STATUS.equalsIgnoreCase(workorder.getWostatus())) {
                isFromValidation = true;
            }
        }

        if (isFromRevision) {
            if (workorder.getRevisioncounter() == null) {
                workorder.setRevisioncounter(1);
            } else {
                workorder.setRevisioncounter(workorder.getRevisioncounter() + 1);
            }
        }

        if (isFromEdit || isFromRevision) {
//            workorder.setLasttlevel(getUserService().getUserLevelValueByEmployeeId(
//                    employeeId));
            workorder.setLasttlevel(currentUser.getLevel().getLevelValue());
//            workorder.setNexttlevel(workorder.getLasttlevel().intValue() + 1);
            workorder.setNexttlevel(nextUser.getLevel().getLevelValue());
        }

        // hapus tworkorder_da, tworkorder_manifest, and tworkorder_smodedetail
        if (workorder.isChanged(workorder.getWorkOrderDA(), oldWorkOrder.getWorkOrderDA())) {
            // delete workorderDA by example
            WorkOrderDAExample wodaExample = new WorkOrderDAExample();
            wodaExample.createCriteria().andTworkorderFkEqualTo(
                    workorder.getTworkorderPk());
            getWorkorderdaDao().deleteWorkOrderDAByExample(wodaExample);
            // lalu insert yang baru
            if (workorder.getWorkOrderDA() != null) {
                for (WorkOrderDA woda : workorder.getWorkOrderDA()) {
                    woda.setTworkorderFk(workorder.getTworkorderPk());
                    getWorkorderdaDao().insertWorkOrderDA(woda);
                }
            }
        }

        if (workorder.isChanged(workorder.getWorkOrderManifest(), oldWorkOrder.getWorkOrderManifest())) {
            WorkOrderManifestExample womanifestExample = new WorkOrderManifestExample();
            womanifestExample.createCriteria().andTworkorderFkEqualTo(workorder.getTworkorderPk());
            getWorkordermanifestDao().deleteWorkOrderManifestByExample(womanifestExample);
            // insert yang baru
            if (workorder.getWorkOrderManifest() != null) {

                HashMap<Long, WorkOrderManifest> hmapwoman = new HashMap<>(workorder.getWorkOrderManifest().size() * 2);
                for (WorkOrderManifest womanifest : workorder.getWorkOrderManifest()) {
                    hmapwoman.put(womanifest.getManifestNo(), womanifest);
                }

                if (hmapwoman.size() > 0) {
                    Collection<WorkOrderManifest> coll = hmapwoman.values();
                    for (WorkOrderManifest woman : coll) {
                        woman.setTworkorderFk(workorder.getTworkorderPk());
                        getWorkordermanifestDao().insertWorkOrderManifest(
                                woman);
                    }
                }
                hmapwoman = null;
            }
        }
        if (workorder.isChanged(workorder.getWorkOrderServiceModeDetail(), oldWorkOrder.getWorkOrderServiceModeDetail())) {
            WorkOrderServiceModeDetailExample wosmodeExample = new WorkOrderServiceModeDetailExample();
            wosmodeExample.createCriteria().andTworkorderFkEqualTo(workorder.getTworkorderPk());
            getWorkorderservicemodedetailDao().deleteWorkOrderServiceModeDetailByExample(wosmodeExample);
            // insert yang baru
            if (workorder.getWorkOrderServiceModeDetail() != null) {
                for (WorkOrderServiceModeDetail wosmodedetail : workorder.getWorkOrderServiceModeDetail()) {
                    wosmodedetail.setTworkorderFk(workorder.getTworkorderPk());
                    getWorkorderservicemodedetailDao().insertWorkOrderServiceModeDetail(wosmodedetail);
                    //Filter Prevention - Bug Fix Amount Double
//                    System.out.println("WO Fk:" + wosmodedetail.getTworkorderFk() + " tservicemodedetail_fk:" + wosmodedetail.getTservicemodedetailFk() + " Qty:" + wosmodedetail.getQuantity() + " "
//                            + "Smd_Remarks:" + wosmodedetail.getSmdremarks() + " Smd_Charge:" + wosmodedetail.getSmdcharge() + " smdtcurrency_fk: " + wosmodedetail.getSmdtcurrencyFk() + " smddetailname:" + wosmodedetail.getSmddetailname() + " deleted:" + wosmodedetail.isDeleted());
//                    try {
//                        ResultMap rsCheck = ConCheckSmoDetail.getSingleResult("SELECT * FROM `tworkorder_smodedetail` tws WHERE  tws.`tworkorder_fk` = " + wosmodedetail.getTworkorderFk() + " "
//                                + "AND tws.`tservicemodedetail_fk` = " + wosmodedetail.getTservicemodedetailFk() + " "
//                                + "AND tws.`deleted` = 0");
//                        System.out.println("SELECT * FROM `tworkorder_smodedetail` tws WHERE  tws.`tworkorder_fk` = " + wosmodedetail.getTworkorderFk() + " "
//                                + "AND tws.`tservicemodedetail_fk` = " + wosmodedetail.getTservicemodedetailFk() + " "
//                                + "AND tws.`deleted` = 0");
//                        if (rsCheck == null) {
//                            getWorkorderservicemodedetailDao().insertWorkOrderServiceModeDetail(wosmodedetail);
//                        }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(WorkOrderServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                    }

                }
            }
        }
        // check if is next user on leave
        User creatorUser = getUserService().getUserByPrimaryKey(workorder.getCreatedbyemployeeid());
//        User nextUser = getUserService().getUserByPrimaryKey(creatorUser.getBossemployeeid());
        nextUser = getUserService().getUserByPrimaryKey(creatorUser.getBossemployeeid());
        boolean isNextUserOnLeave = false;
        boolean isNeedValidation = false;

        if (nextUser != null) {
            if (isUserOnLeave(nextUser)) {
                isNextUserOnLeave = true;
                workorder.setIsgranted(isNextUserOnLeave);
            } else {
                isNextUserOnLeave = false;
                workorder.setIsgranted(isNextUserOnLeave);
            }
        }

        // ubah status
        if (workorder.getAdhoc() != null && workorder.getAdhoc() && (isFromRevision || isFromEdit)) { //adhoc
            isNeedValidation = true;
            workorder.setWostatus(WorkOrder.VALIDATION_STATUS);
        } else if (workorder.getAdhoc() != null && !workorder.getAdhoc() && workorder.getTvendorserviceFk() != null && (isFromRevision || isFromEdit)) { //regular            
            List<VendorService> vservices = VendorServiceLocalServiceUtil.getVendorServiceByAttribute(workorder.getOrigintlocationFk(), workorder.getDestinationtlocationFk(), workorder.getExecutiondate(), workorder.getTorderFk(), workorder.getTservicemodeFk(), workorder.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
            boolean isLowestRate = false;
            boolean vendorServiceExists = false;
            BigDecimal workorderRate = BigDecimal.ZERO;
            Collections.sort(vservices, new Comparator<VendorService>() {
                @Override
                public int compare(VendorService o1, VendorService o2) {
                    return o2.getRate().compareTo(o1.getRate());
                }
            });
            for (VendorService vendorService : vservices) {
                if (Objects.equals(vendorService.getTvendorservicePk(), workorder.getTvendorserviceFk())) {
                    vendorServiceExists = true;
                    workorderRate = vendorService.getRate();
                }
            }
            if (vendorServiceExists) {
                for (VendorService vendorService : vservices) {
                    isLowestRate = workorderRate.compareTo(vendorService.getRate()) <= 0;
                }
            }
            if (isLowestRate) {
                isNeedValidation = false;
                workorder.setWostatus(WorkOrder.APPROVED_STATUS);
                WorkOrderFlow workOrderFlow = new WorkOrderFlow();
                workOrderFlow.setActiondate(new Date());
                workOrderFlow.setActiondone(true);
                workOrderFlow.setEmployeeid("system");
                workOrderFlow.setGranted(false);
                workOrderFlow.setLevelId("SYS");
                workOrderFlow.setOldwostatus(WorkOrder.APPROVED_STATUS);
                workOrderFlow.setTworkorderFk(workorder.getTworkorderPk());
                getWorkorderflowService().insertWorkOrderFlow(workOrderFlow);
                LOG.info(employeeId + " " + workOrderFlow.toString());
                if (workorder.getWorkOrderDA().size() < 1 && ! GlobalServiceUtil.IS_DEVELOPMENT ) {
                    JDBCConnector con = new JDBCConnector("fast");
                    String strSQL = "replace into t_manifest_wo(manifest_no,nomorwo,tworkorder_pk, employee_id,insda) values('%d','" + workorder.getNomorwo() + "','" + workorder.getTworkorderPk() + "','" + workorder.getCreatedbyemployeeid() + "',now())";
                    for (WorkOrderManifest wom : workorder.getWorkOrderManifest()) {
                        try {
                            con.executeNonQuery(String.format(strSQL, wom.getManifestNo()));
                            LOG.info(strSQL);
                        } catch (SQLException e) {
                            LOG.error(e.getMessage(), e);
                            LOG.error("Can not insert or update t_manifest_wo because " + e.getMessage());
                        }
                    } 
                }
            } else {
                isNeedValidation = true;
                workorder.setWostatus(WorkOrder.VALIDATION_STATUS);
            }
        } else if (workorder.getAdhoc() != null && workorder.getAdhoc() && isFromValidation) {
            workorder.setWostatus(WorkOrder.INPROGRESS_STATUS);
            isNeedValidation = false;
        } else if (workorder.getAdhoc() != null && !workorder.getAdhoc() && isFromValidation) {
            isNeedValidation = false;
            workorder.setWostatus(WorkOrder.APPROVED_STATUS);
        }

        if (isFromValidation) {
            // insert workorderflow
            WorkOrderFlow workOrderFlow = new WorkOrderFlow();
            workOrderFlow.setActiondate(new Date());
            workOrderFlow.setActiondone(true);
            workOrderFlow.setEmployeeid(employeeId);
            workOrderFlow.setGranted(false);
            workOrderFlow.setLevelId(currentUser.getLevelId());
            workOrderFlow.setOnbehalfemployeeid(null);
            workOrderFlow.setOldwostatus(isFromValidation ? WorkOrder.VALIDATION_STATUS : WorkOrder.INPROGRESS_STATUS);
            workOrderFlow.setTworkorderFk(workorder.getTworkorderPk());
            getWorkorderflowService().insertWorkOrderFlow(workOrderFlow);
            LOG.info(employeeId + " " + workOrderFlow.toString());
            if (!workorder.getAdhoc()) {
                workOrderFlow = new WorkOrderFlow();
                workOrderFlow.setActiondate(new Date());
                workOrderFlow.setActiondone(true);
                workOrderFlow.setEmployeeid("system");
                workOrderFlow.setGranted(false);
                workOrderFlow.setLevelId("SYS");
                workOrderFlow.setOnbehalfemployeeid(null);
                workOrderFlow.setOldwostatus(WorkOrder.APPROVED_STATUS);
                workOrderFlow.setTworkorderFk(workorder.getTworkorderPk());
                getWorkorderflowService().insertWorkOrderFlow(workOrderFlow);
                LOG.info(employeeId + " " + workOrderFlow.toString());
                if (workorder.getWorkOrderDA().size() < 1 && ! GlobalServiceUtil.IS_DEVELOPMENT ) {
                    JDBCConnector con = new JDBCConnector("fast");
                    String strSQL = "replace into t_manifest_wo(manifest_no,nomorwo,tworkorder_pk, employee_id,insda) values('%d','" + workorder.getNomorwo() + "','" + workorder.getTworkorderPk() + "','" + workorder.getCreatedbyemployeeid() + "',now())";
                    for (WorkOrderManifest wom : workorder.getWorkOrderManifest()) {
                        try {
                            con.executeNonQuery(String.format(strSQL, wom.getManifestNo()));
                            LOG.info(strSQL);
                        } catch (SQLException e) {
                            LOG.error(e.getMessage(), e);
                            LOG.error("Can not insert or update t_manifest_wo because " + e.getMessage());
                        }
                    }
                }
            }
        }
        LOG.info(employeeId + " " + workorder.toString());
        // update workorder
        int count = workorderDao.updateWorkOrderByPrimaryKeyWithBLOBs(workorder);

        // kirim email
        //if (isNeedValidation) {
        //getWorkOrderMailService().sendNotificationForCostValidation(workorder);
        //} else {
        //nextUser = getNextAvailableBossForThisUser(creatorUser.getEmployeeId());
        //getWorkOrderMailService().sendNotificationForApproval(workorder, nextUser.getEmail());
        //}
        return count;
    }

    @Override
    public List<WorkOrder> getWorkOrderByExample(WorkOrderExample example, int pageNo, int rowPerPage) {
        example.setLimitClause(setOffset(pageNo, rowPerPage));
        return workorderDao.selectWorkOrderByExampleWithBLOBs(example);
    }

    @Override
    public List<WorkOrder> getWorkOrderByExampleWithJoin(ExtendedWorkOrderExample example) {
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    @Override
    public WorkOrder getWorkOrderyByPrimaryKeyWithJoin(Long workorderPk) {
        return workorderDao.selectWorkOrderByPrimaryKeyWithJoin(workorderPk);
    }

    @Override
    public List<WorkOrder> getWorkOrderByExampleWithJoinToFast(ExtendedWorkOrderExample example) {
        List<WorkOrder> lwo = workorderDao.selectWorkOrderByExampleWithJoin(example);
        if (lwo == null) {
            return null;
        }
        for (WorkOrder wo : lwo) {
            List<WorkOrderManifest> lwomanifest = wo.getWorkOrderManifest();
            if (lwomanifest != null) {
                for (WorkOrderManifest wom : lwomanifest) {
                    MdaManifest manifest = getMdamanifestDao().selectMdaManifestByPrimaryKey(wom.getManifestNo());
                    ManifestWeightVolumeDetail manifestWeight = getMdamanifestDao().selectManifestWeightVolumeDetail(wom.getManifestNo());
                    if (manifest == null) {
                        manifest = getMdamanifestDao().selectPickupDeliveryByPrimaryKey(wom.getManifestNo());
                        manifestWeight = getMdamanifestDao().selectPickupDeliveryWeightVolumeDetail(wom.getManifestNo());
                    }
                    wom.setManifest(manifest);
                    wom.setManifestweightvolumedetail(manifestWeight);
                }
            }
        }
        return lwo;
    }

    @Override
    public void updateAfterCostValidation(WorkOrder workorder, String employeeId) {
        //User userValidator = getUserService().getUserByPrimaryKey(employeeId);
        User nextUser = getUserService().getNextUserForThisWorkOrder(workorder.getTworkorderPk());
        LOG.info("Next User = " + nextUser.toString());
        if (isUserOnLeave(nextUser)) {
            workorder.setIsgranted(true);
            nextUser = getNextAvailableBossForThisUser(nextUser.getEmployeeId());
            LOG.info("Next Next User = " + nextUser.toString());
        }
        // update workorder
//        workorder.setWostatus(WorkOrder.INPROGRESS_STATUS);
        updateWorkOrderWithChildren(workorder, employeeId);
        //  insert to WorkOrderFlow
        /*if (workorder.getAdhoc()) {
            WorkOrderFlow woflow = new WorkOrderFlow();
            woflow.setActiondate(new Date());
            woflow.setActiondone(true);
            woflow.setEmployeeid(employeeId);
            woflow.setGranted(false);
            woflow.setLevelId(userValidator.getLevelId());
            woflow.setOldwostatus(WorkOrder.VALIDATION_STATUS);
            woflow.setTworkorderFk(workorder.getTworkorderPk());
            getWorkorderflowService().insertWorkOrderFlow(woflow);
        }*/

        //  send email to bos
        //getWorkOrderMailService().sendNotificationForApproval(workorder, nextUser.getEmail());
    }

    @Override
    public WorkOrder getWorkOrderyByPrimaryKeyWithJoinToFast(Long workorderPk) {
        return workorderDao.selectWorkOrderByPrimaryKeyWithJoinToFast(workorderPk);
    }

    @Override
    public WorkOrder getWorkOrderyByPrimaryKey(Long workorderPk) {
        return workorderDao.selectWorkOrderByPrimaryKey(workorderPk);
    }

    public WorkOrderDADAO getWorkorderdaDao() {
        if (workorderdaDao == null) {
            workorderdaDao = (WorkOrderDADAO) BeanFactory.getBean("workorderdaDao");
        }
        return workorderdaDao;
    }

    public void setWorkorderdaDao(WorkOrderDADAO workorderdaDao) {
        this.workorderdaDao = workorderdaDao;
    }

    public WorkOrderManifestDAO getWorkordermanifestDao() {
        if (workordermanifestDao == null) {
            workordermanifestDao = (WorkOrderManifestDAO) BeanFactory.getBean("workordermanifestDao");
        }
        return workordermanifestDao;
    }

    public void setWorkordermanifestDao(
            WorkOrderManifestDAO workordermanifestDao) {
        this.workordermanifestDao = workordermanifestDao;
    }

    public WorkOrderServiceModeDetailDAO getWorkorderservicemodedetailDao() {
        if (workorderservicemodedetailDao == null) {
            workorderservicemodedetailDao = (WorkOrderServiceModeDetailDAO) BeanFactory.getBean("workorderservicemodedetailDao");
        }
        return workorderservicemodedetailDao;
    }

    public void setWorkorderservicemodedetailDao(
            WorkOrderServiceModeDetailDAO workorderservicemodedetailDao) {
        this.workorderservicemodedetailDao = workorderservicemodedetailDao;
    }

    @Override
    public List<Level> getApprovalLevelForDropDown() {
        LevelExample example = new LevelExample();
        example.createCriteria().andLevelValueGreaterThan(1);
        example.setOrderByClause("level_value asc");
        return getLevelDao().selectLevelByExample(example);
    }

    @Override
    public List<WorkOrder> getWorkOrderUsingPODToBeApprovedWithoutGrant(
            String employeeId, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedByThisUserWithoutGrant(
                employeeId, limitClause, orderByClause, true);
        if (example == null) {
            return null;
        }
        return getWorkOrderByExampleWithJoin(example);
    }

    @Override
    public List<WorkOrder> getWorkOrderToBeApprovedWithoutGrant(
            String employeeId, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedByThisUserWithoutGrant(
                employeeId, limitClause, orderByClause, false);
        if (example == null) {
            return null;
        }
        return getWorkOrderByExampleWithJoin(example);
    }

    @Override
    public int countWorkOrderUsingPODToBeApprovedWithGrant(String employeeId) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedWithGrantForThisUser(employeeId, null, null, true);
        if (example == null) {
            return 0;
        }
        return workorderDao.extendedCountWorkOrderByExample(example);
    }

    @Override
    public int countWorkOrderToBeApprovedWithGrant(String employeeId) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedWithGrantForThisUser(employeeId, null, null, false);
        if (example == null) {
            return 0;
        }
        return workorderDao.extendedCountWorkOrderByExample(example);
    }

    private ExtendedWorkOrderExample getExampleForWorkOrderToBeApprovedByThisUserWithoutGrant(
            String employeeId, String limitClause,
            String orderByClause, boolean pod) {

        List<String> allStaffBelowThisUser = getUserService().getAllStaffForThisUser(employeeId);
        Integer levelValue = getUserService().getUserLevelValueByEmployeeId(
                employeeId);
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        if (allStaffBelowThisUser == null || allStaffBelowThisUser.isEmpty()) {
            return null;
        }
        com.ckb.wo.client.model.ExtendedWorkOrderExample.ExtendedCriteria criteria = example.createCriteria();
        criteria.andCreatedbyemployeeidIn(allStaffBelowThisUser);
        criteria.andWostatusEqualTo(WorkOrder.INPROGRESS_STATUS);
        criteria.andNexttlevelEqualTo(levelValue);

        if (pod) {
            criteria.andDaIsPOD();
        } else {
            criteria.andDaNotPOD();
        }

        if (limitClause != null) {
            example.setLimitClause(limitClause);
        }
        if (orderByClause != null) {
            example.setOrderByClause(orderByClause);
        }
        return example;
    }

    @Override
    public int countWorkOrderForFinanceUser() {
        return workorderDao.countWorkOrderByExample(getWorkOrderExampleForFinanceUser());
    }

    @Override
    public List<WorkOrder> getWorkOrderForFinanceUser(String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getWorkOrderExampleForFinanceUser();
        example.setLimitClause(limitClause);
        example.setOrderByClause(orderByClause);
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    @Override
    public int countWorkOrderForProcuremenUser(String nomorwo) {
        return workorderDao.countWorkOrderByExample(getWorkOrderExampleForProcurementUser(nomorwo));
    }

    @Override
    public List<WorkOrder> getWorkOrderForProcurementUser(String nomorwo, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getWorkOrderExampleForProcurementUser(nomorwo);
        example.setLimitClause(limitClause);
        example.setOrderByClause(orderByClause);
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    private ExtendedWorkOrderExample getWorkOrderExampleForProcurementUser(String nomorwo) {
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        List<String> lwoStatus = new ArrayList<>();
        lwoStatus.add(WorkOrder.VALIDATION_STATUS);
        example.createCriteria().andWostatusIn(lwoStatus).andNomorwoLike(nomorwo == null ? "%" : nomorwo);
        return example;
    }

    private ExtendedWorkOrderExample getWorkOrderExampleForFinanceUser() {
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
//        lwoStatus.add(WorkOrder.RECEIVED_STATUS);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        example.createCriteria().andCreateddateGreaterThanOrEqualTo(cal.getTime()).setAndCustomSQL("atworkorder.wostatus IN ('" + WorkOrder.PRINTED_STATUS + "', '" + WorkOrder.APPROVED_STATUS + "') "
                + " OR (atworkorder.cancellationfee IS NOT NULL AND atworkorder.wostatus = '" + WorkOrder.CANCELLED_STATUS + "')");

        return example;
    }

    @Override
    public int countNewWorkOrderFromThisUser(String employeeId) {
        return workorderDao.countWorkOrderByExample(getWorkOrderExampleForNewWorkOrder(employeeId));
    }

    @Override
    public List<WorkOrder> getNewWorkOrderFromThisUser(String employeeId, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getWorkOrderExampleForNewWorkOrder(employeeId);
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    private ExtendedWorkOrderExample getWorkOrderExampleForNewWorkOrder(String employeeId) {
        Calendar today = Calendar.getInstance();
        Calendar oneWeekAgo = Calendar.getInstance();
        oneWeekAgo.add(Calendar.DAY_OF_MONTH, -7);
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        ExtendedWorkOrderExample.ExtendedCriteria criteria = example.createCriteria();
        criteria.andCreatedbyemployeeidEqualTo(employeeId);
        criteria.andWostatusNotEqualTo(WorkOrder.RECEIVED_STATUS);
        criteria.andCreateddateBetween(oneWeekAgo.getTime(), today.getTime());
        return example;
    }

    @Override
    public int countAllWorkOrderFromThisUser(String employeeId) {
        return workorderDao.countWorkOrderByExample(getWorkOrderExampleAllWorkOrderFromThisUser(employeeId));
    }

    @Override
    public List<WorkOrder> getAllWorkOrderFromThisUser(String employeeId, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getWorkOrderExampleAllWorkOrderFromThisUser(employeeId);
        example.setLimitClause(limitClause);
        example.setOrderByClause(orderByClause);
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    private ExtendedWorkOrderExample getWorkOrderExampleAllWorkOrderFromThisUser(String employeeId) {
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        ExtendedWorkOrderExample.ExtendedCriteria criteria = example.createCriteria();
        criteria.andCreatedbyemployeeidEqualTo(employeeId);
        criteria.andWostatusNotEqualTo(WorkOrder.RECEIVED_STATUS);
        return example;
    }

    @Override
    public int countWorkOrderValidatedByThisUser(String employeeId) {
//            WorkOrderExample example = getWorkOrderExampleValidatedByThisUser(employeeId);
//            if(example==null) return 0;
//            return workorderDao.countWorkOrderByExample(example);
        return workorderDao.countWorkOrderValidatedByThisUser(employeeId);
    }

    @Override
    public List<WorkOrder> getWorkOrderValidatedByThisUser(String employeeId, String limitClause, String orderByClause) {
//            WorkOrderExample example = getWorkOrderExampleValidatedByThisUser(employeeId);
//            if(example==null) return new ArrayList();
//            example.setLimitClause(limitClause);
//            example.setOrderByClause(orderByClause);
//            return workorderDao.selectWorkOrderByExampleWithJoin(example);
        return workorderDao.selectWorkOrderValidatedByThisUser(employeeId, orderByClause, limitClause);
    }

    private WorkOrderExample getWorkOrderExampleValidatedByThisUser(String employeeId) {
        // Work
        List<Long> lworkorderPk = getWorkorderflowService().getWorkOrderValidatedByThisUser(employeeId);
        if (lworkorderPk == null || lworkorderPk.isEmpty()) {
            return null;
        }
        WorkOrderExample example = new WorkOrderExample();
        example.createCriteria().andTworkorderPkIn(lworkorderPk);
        return example;
    }

    private User getNextAvailableBossForThisUser(User currentUser) {

        if (currentUser.getBossemployeeid() == null) {
            return currentUser;
        }

        User nextUser = getUserService().getUserByPrimaryKeyWithJoin(currentUser.getBossemployeeid());
        while (true) {
            // if this user doesn't have boss anymore, then he will be the last user,
            // we don't care even if he's on leave
            if (nextUser.getBossemployeeid() == null || nextUser.getBossemployeeid().length() == 0) {
                break;
            }
            if (isUserOnLeave(nextUser)) {
                User tempUser = null;
                tempUser = getUserService().getUserByPrimaryKeyWithJoin(nextUser.getBossemployeeid());
                if (tempUser == null) {
                    break;
                } else {
                    nextUser = tempUser;
                }
            } else {
                break;
            }
        }
        return nextUser;
    }

    private User getNextAvailableBossForThisUser(String employeeId) {
        User currentUser = getUserService().getUserByPrimaryKey(employeeId);
        return getNextAvailableBossForThisUser(currentUser);
    }

    @Override
    public String sendReminder(Long tworkorderPk) {
        WorkOrder workOrder = getWorkOrderyByPrimaryKeyWithJoin(tworkorderPk);
        if (workOrder != null) {
            if ("IN_PROGRESS".equals(workOrder.getWostatus())) {
                User nextUser = getUserService().getNextUserForThisWorkOrder(tworkorderPk);
                if (isUserOnLeave(nextUser)) {
                    nextUser = getUserService().getUserByPrimaryKey(nextUser.getBossemployeeid());
                }
                LOG.info("Sending a Reminder for " + tworkorderPk + " to " + nextUser.getEmployeeId());
                getWorkOrderMailService().sendNotificationForReminder(workOrder, nextUser.getEmail());
                return "Reminder for " + workOrder.getNomorwo() + " has been sent to " + nextUser.getFirstName() + " " + nextUser.getMiddleName() + " " + nextUser.getLastName();
            } else {
                return "No Reminder was sent because The Work Order is not in progress";
            }
        } else {
            return "No Reminder was sent because The Work Order can not be found";
        }
    }

    @Override
    public int countWorkOrderApprovedByThisUser(String employeeId, String wonumber) {
        return workorderDao.countWorkOrderApprovedByThisUser(employeeId, wonumber);
    }

    @Override
    public List<WorkOrder> getWorkOrderApprovedByThisUser(String employeeId, String wonumber, String limitClause, String orderByClause) {
        return workorderDao.selectWorkOrderApprovedByThisUser(employeeId, wonumber, limitClause, orderByClause);
    }

    @Override
    public void sendCustomCostValidationMail(WorkOrder workOrder, String content) {
        getWorkOrderMailService().sendCustomCostValidationMail(workOrder, content);
    }

    @Override
    public void updateWorkOrderAfterChargeableWeightValidation(WorkOrder workorder, String employeeId) {

        WorkOrder oldWorkOrder = getWorkOrderyByPrimaryKeyWithJoin(workorder.getTworkorderPk());

        if (workorder.isChanged(workorder.getWorkOrderDA(), oldWorkOrder.getWorkOrderDA())) {
            // delete workorderDA by example
            WorkOrderDAExample wodaExample = new WorkOrderDAExample();
            wodaExample.createCriteria().andTworkorderFkEqualTo(
                    workorder.getTworkorderPk());
            getWorkorderdaDao().deleteWorkOrderDAByExample(wodaExample);
            // lalu insert yang baru
            if (workorder.getWorkOrderDA() != null) {
                for (WorkOrderDA woda : workorder.getWorkOrderDA()) {
                    woda.setTworkorderFk(workorder.getTworkorderPk());
                    getWorkorderdaDao().insertWorkOrderDA(woda);
                }
            }
        }
        if (workorder.isChanged(workorder.getWorkOrderManifest(), oldWorkOrder.getWorkOrderManifest())) {
            WorkOrderManifestExample womanifestExample = new WorkOrderManifestExample();
            womanifestExample.createCriteria().andTworkorderFkEqualTo(workorder.getTworkorderPk());
            getWorkordermanifestDao().deleteWorkOrderManifestByExample(womanifestExample);
            // insert yang baru
            if (workorder.getWorkOrderManifest() != null) {
                for (WorkOrderManifest womanifest : workorder.getWorkOrderManifest()) {
                    womanifest.setTworkorderFk(workorder.getTworkorderPk());
                    getWorkordermanifestDao().insertWorkOrderManifest(womanifest);
                }
            }
        }
        if (workorder.isChanged(workorder.getWorkOrderServiceModeDetail(), oldWorkOrder.getWorkOrderServiceModeDetail())) {
            WorkOrderServiceModeDetailExample wosmodeExample = new WorkOrderServiceModeDetailExample();
            wosmodeExample.createCriteria().andTworkorderFkEqualTo(
                    workorder.getTworkorderPk());
            getWorkorderservicemodedetailDao().deleteWorkOrderServiceModeDetailByExample(wosmodeExample);
            // insert yang baru
            if (workorder.getWorkOrderServiceModeDetail() != null) {
                for (WorkOrderServiceModeDetail wosmodedetail : workorder.getWorkOrderServiceModeDetail()) {
                    wosmodedetail.setTworkorderFk(workorder.getTworkorderPk());
                    getWorkorderservicemodedetailDao().insertWorkOrderServiceModeDetail(wosmodedetail);
                }
            }
        }
        workorderDao.updateWorkOrderByPrimaryKeyWithoutBLOBs(workorder);
    }

    @Override
    public List<WorkOrder> getWorkOrderByExampleWithJoinForFinance(ExtendedWorkOrderExample example) {
        return workorderDao.selectByExampleWithJoinForFinance(example);
    }

    @Override
    public int countWorkOrderByExampleWithJoinForFinance(ExtendedWorkOrderExample example) {
        return workorderDao.countByExampleWithJoinForFinance(example);
    }

    @Override
    public int countWorkOrderToBeApprovedWithoutGrant(String employeeId) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedByThisUserWithoutGrant(employeeId, null, null, false);
        if (example == null) {
            return 0;
        }
        return workorderDao.extendedCountWorkOrderByExample(example);
    }

    @Override
    public int countWorkOrderUsingPODToBeApprovedWithoutGrant(String employeeId) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedByThisUserWithoutGrant(employeeId, null, null, true);

        if (example == null) {
            return 0;
        }
        return workorderDao.extendedCountWorkOrderByExample(example);
    }

    @Override
    public List<WorkOrder> getWorkOrderUsingPODToBeApprovedWithGrant(String employeeId, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedWithGrantForThisUser(employeeId, limitClause, orderByClause, true);
        if (example == null) {
            return null;
        }
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    @Override
    public List<WorkOrder> getWorkOrderToBeApprovedWithGrant(String employeeId, String limitClause, String orderByClause) {
        ExtendedWorkOrderExample example = getExampleForWorkOrderToBeApprovedWithGrantForThisUser(employeeId, limitClause, orderByClause, false);
        if (example == null) {
            return null;
        }
        return workorderDao.selectWorkOrderByExampleWithJoin(example);
    }

    private ExtendedWorkOrderExample getExampleForWorkOrderToBeApprovedWithGrantForThisUser(String employeeId, String limitClause, String orderByClause, boolean pod) {
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
//        List<String> excludedWOStatus = new ArrayList<String>();
//        excludedWOStatus.add(WorkOrder.APPROVED_STATUS);
//        excludedWOStatus.add(WorkOrder.CANCELLED_STATUS);
//        excludedWOStatus.add(WorkOrder.VALIDATION_STATUS);
//        excludedWOStatus.add(WorkOrder.RECEIVED_STATUS);

        List<Long> woPks = new ArrayList<>();
        User current = UserLocalServiceUtil.getUserByPrimaryKeyWithJoin(employeeId);
        User nextUser = UserLocalServiceUtil.getUserByPrimaryKeyWithJoin(current.getBossemployeeid());

        List<String> allLeaveUser = getUserService().getAllLeaveUser(employeeId);
        if (nextUser.getLevel().getLevelId().equalsIgnoreCase("DIR") && isUserOnLeave(nextUser)) {
            allLeaveUser.add(nextUser.getEmployeeId());
        }
        for (int i = 0; i < allLeaveUser.size(); i++) {
            String onLeaveEmployeeId = allLeaveUser.get(i);
//            selectOnlyTWorkorderPkByExampleWithJoin

            List<String> allStaffBelowThisUser = getUserService().getAllStaffForThisUser(onLeaveEmployeeId);
            Integer levelValue = getUserService().getUserLevelValueByEmployeeId(onLeaveEmployeeId);
            ExtendedWorkOrderExample examplePk = new ExtendedWorkOrderExample();
            if (allStaffBelowThisUser == null || allStaffBelowThisUser.isEmpty()) {
                return null;
            }
//            com.ckb.wo.client.model.WorkOrderExample.Criteria criteria = examplePk.createCriteria();
            com.ckb.wo.client.model.ExtendedWorkOrderExample.ExtendedCriteria criteria = examplePk.createCriteria();

            criteria.andCreatedbyemployeeidIn(allStaffBelowThisUser);
            criteria.andWostatusEqualTo(WorkOrder.INPROGRESS_STATUS);
            criteria.andNexttlevelEqualTo(levelValue);
            if (pod) {
                criteria.andDaIsPOD();
            } else {
                criteria.andDaNotPOD();
            }

            List<Long> tmpPks = workorderDao.selectWorkOrderPKOnlyByExample(examplePk);
            woPks.addAll(tmpPks);
        }
        ExtendedWorkOrderExample.Criteria crit = woPks.isEmpty() ? null : example.createCriteria().andTworkorderPkIn(woPks);

//-------------edit by shido----------------------
//        int thisuser_level = getUserService().getUserLevelValueByEmployeeId(employeeId);
//        List<String> creatorUserForOnLeaveUserOneLevelBelow = getUserService().getCreatorUserForOnLeaveUserBelowOneLevel(employeeId);
//        List<String> creatorUserForOnLeaveUserTwoLevelBelow = getUserService().getCreatorUserForOnLeaveUserBelowTwoLevel(employeeId);
//
//        WorkOrderExample.Criteria crit = example.createCriteria();
//
//        if (creatorUserForOnLeaveUserOneLevelBelow != null && creatorUserForOnLeaveUserOneLevelBelow.size() > 0) {
//            crit.andNexttlevelEqualTo(thisuser_level - 1);
//            crit.andWostatusNotIn(excludedWOStatus);
//            crit.andCreatedbyemployeeidIn(creatorUserForOnLeaveUserOneLevelBelow);
//        } else {
//            crit = null;
//        }
//
//        WorkOrderExample.Criteria crit2 = example.createCriteria();
//        if (creatorUserForOnLeaveUserTwoLevelBelow != null && creatorUserForOnLeaveUserTwoLevelBelow.size() > 0) {
//            crit2.andCreatedbyemployeeidIn(creatorUserForOnLeaveUserTwoLevelBelow);
//            crit2.andNexttlevelEqualTo(thisuser_level - 2);
//            crit2.andWostatusNotIn(excludedWOStatus);
//        } else {
//            crit2 = null;
//        }
//------------------------end edited---------------------------
        if (crit == null) {
            example = null;
        }
        return example;

    }

    @Override
    public List<WorkOrderServiceModeDetail> getWorkOrderSubTotal(Long tworkorder_pk) {
        return getWorkorderservicemodedetailDao().selectForSubTotal(tworkorder_pk);
    }

    public LevelDAO getLevelDao() {
        if (levelDao == null) {
            levelDao = (LevelDAO) BeanFactory.getBean("levelDao");
        }
        return levelDao;
    }

    private WorkOrderMailService getWorkOrderMailService() {
        if (workOrderMailService == null) {
            workOrderMailService = (WorkOrderMailService) BeanFactory.getBean("workordermailService");
        }
        return workOrderMailService;
    }

    private IWorkOrderFlowService getWorkorderflowService() {
        if (workorderflowService == null) {
            workorderflowService = (IWorkOrderFlowService) BeanFactory.getBean("workorderflowService");
        }
        return workorderflowService;
    }

    public IUserService getUserService() {
        if (userService == null) {
            userService = (IUserService) BeanFactory.getBean("userService");
        }
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public WorkOrderHistoryDAO getWorkorderhistoryDao() {
        if (workorderhistoryDao == null) {
            workorderhistoryDao = (WorkOrderHistoryDAO) BeanFactory.getBean("workorderhistoryDao");
        }
        return workorderhistoryDao;
    }

    public void setWorkorderhistoryDao(WorkOrderHistoryDAO workorderhistoryDao) {
        this.workorderhistoryDao = workorderhistoryDao;
    }

    public MdaManifestDAO getMdamanifestDao() {
        if (mdamanifestDao == null) {
            mdamanifestDao = (MdaManifestDAO) BeanFactory.getBean("mdamanifestDao");
        }
        return mdamanifestDao;
    }

    public void setMdamanifestDao(MdaManifestDAO mdamanifestDao) {
        this.mdamanifestDao = mdamanifestDao;
    }

    @Override
    public void printWorkOrder(Long workOrderPk, String employeeId) throws SecurityException {
        WorkOrder wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKey(workOrderPk);
        if (wo == null) {
            throw new SecurityException("Printing non exist document");
        }
        if (!wo.getWostatus().equals(WorkOrder.APPROVED_STATUS) || wo.getWostatus().equals(WorkOrder.PRINTED_STATUS)) {
            throw new SecurityException("Cannot print document in " + wo.getWostatus() + " status!");
        }
        // can print and insert log only for user created
        if(!wo.getCreatedbyemployeeid().equalsIgnoreCase(employeeId)){
            throw new SecurityException("You Can Not Print this wo");
        }
        wo.setWostatus(WorkOrder.PRINTED_STATUS);
        updateWorkOrder(wo, employeeId);
        insertWorkOrderFlowForDelegation(wo, null, UserLocalServiceUtil.getUserByPrimaryKey(employeeId), WorkOrder.PRINTED_STATUS);
    }

    @Override
    public List<WoDaManStatus> getWoDaManStatusByMan(List<String> reffNoList) {
        return workorderDao.selectWoDaManStatusByMan(reffNoList);
    }

    @Override
    public List<WoDaManStatus> getWoDaManStatusByDa(List<String> reffNoList) {
        return workorderDao.selectWoDaManStatusByDa(reffNoList);
    }

    @Override
    public List<WoDaManStatus> getWoDaManStatusByWo(List<String> reffNoList) {
        return workorderDao.selectWoDaManStatusByWo(reffNoList);
    }

    @Override
    public List<WorkOrder> getWorkOrderByExampleWithJoinWithoutDa(ExtendedWorkOrderExample example) {
        return workorderDao.selectWorkOrderByExampleWithJoinWithoutDa(example); 
    }

    @Override
    public void cancelWorkOrderByAdmin(Long workOrderPk, String reason, String employeeId) throws SecurityException {
       User currentUser = getUserService().getUserByPrimaryKey(employeeId);
        WorkOrder workOrder = getWorkOrderyByPrimaryKey(workOrderPk); 
        workOrder.setWostatus(WorkOrder.CANCELLED_STATUS);
        workOrder.setCancelreason(reason);
        updateWorkOrder(workOrder, employeeId);
        
        //save to :workorderflow
        insertWorkOrderFlowForDelegation(workOrder, reason, currentUser, WorkOrder.CANCELLED_STATUS);

        // send email to creator
        User creatorUser = getUserService().getUserByPrimaryKey(workOrder.getCreatedbyemployeeid());
        getWorkOrderMailService().sendNotificationForCancel(workOrder, creatorUser.getEmail());
    }

    @Override
    public void printWorkOrderWithOutUpdate(Long workOrderPk, String employeeId) throws SecurityException {
        WorkOrder wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKey(workOrderPk);
        // can print and insert log only for user created
        if(!wo.getCreatedbyemployeeid().equalsIgnoreCase(employeeId)){
            throw new SecurityException("You Can Not Print this wo");
        } 
        insertWorkOrderFlowForDelegation(wo, null, UserLocalServiceUtil.getUserByPrimaryKey(employeeId), WorkOrder.PRINTED_STATUS);
    }
}
