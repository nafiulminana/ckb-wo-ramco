package com.ckb.wo.server.test;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.Notes;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.server.exception.SecurityException;
import java.util.List;

import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderExample;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.server.service.IUserService;
import com.ckb.wo.server.service.factory.BeanFactory;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestWorkOrder {

    public static void main(String[] args) {
        testapp();
    }

    public static void testNotes() {
        System.out.println(WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant("6650", null, null).size());
    }

    public static void testapp() {
        WorkOrder workOrderByPrimaryKey = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(1L);
        System.out.println(workOrderByPrimaryKey);
    }

    public static void testGetWorkOrderSubTotal() {

        List<WorkOrderServiceModeDetail> lwo = WorkOrderLocalServiceUtil.getWorkOrderSubTotal(1l);
        if (lwo != null) {
            for (WorkOrderServiceModeDetail wosmd : lwo) {
                System.out.println("currency :" + wosmd.getCurrency().getCurrencycode());
                System.out.println("subtotal :" + wosmd.getSubtotal());
            }
        }
    }

    public static void testAllWorkOrderJoin() {
//        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderApprovedByThisUser("wo2",null, null, "0,2", null);
//        if (lwo != null) {
//            System.out.println("getWorkOrderApprovedByThisUser result :" + lwo.size());
//        }
        List<WorkOrder> lwo = null;
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        example.createCriteria().andTworkorderPkEqualTo(108L);
        lwo = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoin(example);
        if (lwo != null) {
            System.out.println("size :" + lwo.size());
            System.out.println("getWorkOrderByExampleWithJoin result :");
            for (WorkOrder wo : lwo) {
                System.out.println("Order : " + wo.getOrder().getOrdername());
                System.out.println("Service type : " + wo.getServiceType().getServicename());
                List<WorkOrderServiceModeDetail> lsmd = wo.getWorkOrderServiceModeDetail();
                for (WorkOrderServiceModeDetail wosmd : lsmd) {
                    System.out.println("wosmd.currency :" + wosmd.getCurrency().getCurrencyname());
                }
            }
        }
    }

    public static void testWorkOrder() {
        int count = WorkOrderLocalServiceUtil.countWorkOrder();
        System.out.println(count);
    }

    public static void testGetWorkOrderApprovedByThisUser() {
        int count = WorkOrderLocalServiceUtil.countWorkOrderApprovedByThisUser("wo1", null);
        System.out.println("count :" + count);
        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderApprovedByThisUser("wo1", null, null, null, null);
        if (lwo != null) {
            System.out.println("size :" + lwo.size());
        }
    }

    public static void testListWOToBeApprovedWithoutGrant() {
        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant("wo1", null, null);
        if (lwo != null) {
            System.out.println(lwo.size());
        }
    }

    public static void testGetDelegatedWorkOrder() {

        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithGrant("wo2", null, null);
        if (lwo != null) {
            System.out.println(lwo.size());
        }
    }

    public static void testGetWorkOrderToBeApproved() {
        int count = WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithGrant("wo2");
        System.out.println("count :" + count);
        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithGrant("wo2", null, null);
        if (lwo != null) {
            System.out.println(lwo.size());
        }
    }
}
