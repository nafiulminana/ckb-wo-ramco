package com.ckb.wo.server.test;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ckb.wo.client.model.FastUserExample;
import com.ckb.wo.client.model.IbatisTest;
import com.ckb.wo.client.model.IbatisTestExample;
import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.MdaManifest;
import com.ckb.wo.client.model.MdaManifestExample;
import com.ckb.wo.client.model.ServiceModeDetailExample;
import com.ckb.wo.client.model.Shipment;
import com.ckb.wo.client.model.ShipmentExample;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserExample;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorExample;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderDA;
import com.ckb.wo.client.model.WorkOrderExample;
import com.ckb.wo.client.model.WorkOrderManifest;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.client.model.VendorExample.Criteria;
import com.ckb.wo.server.dao.IbatisTestDAO;
import com.ckb.wo.server.dao.mda.MdaManifestDAO;
import com.ckb.wo.server.exception.SecurityException;
import com.ckb.wo.server.service.WorkOrderMailService;
import com.ckb.wo.server.service.factory.BeanFactory;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.FastServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.ManifestDALocalServiceUtil;
import com.ckb.wo.server.service.util.ServiceModeDetailLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderFlowLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

public class TestIbatisNumber {

    public static void main(String[] ar) {
        //testWoToBeApproved();
        testEmail();
    }

    public static void testWOApprovedByThisUser(){

        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderApprovedByThisUser("wo1",null,null, "0,10", null);
        if(lwo!=null) System.out.println("size :"+lwo.size());
    }

    public static void testWoToBeApproved(){
        List<WorkOrder> lwo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant("wo1", null, null);
        //System.out.println(lwo.size());
        int count = WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithoutGrant("wo1");
        System.out.println(count + "="+lwo.size());
    }

    public static void testSMDetail(){
     ServiceModeDetailExample example = new ServiceModeDetailExample();
     example.createCriteria().andTservicemodeFkEqualTo(1l);
     ServiceModeDetailLocalServiceUtil.getServiceModeDetailByExample(example);
    }

//    public static Shipment getShipment(Long da) {
////        String res=ServletContextInfo.getRealPath("/report");'
//        ShipmentExample example = new ShipmentExample();
//        example.createCriteria().andDaEqualTo(da);
//        List<Shipment> shipment = ManifestDALocalServiceUtil.getShipmentByExample(example);
//        if (shipment != null || !shipment.isEmpty()) {
//            return shipment.get(0);
//        }
//        return null;
//    }

    public static String getPath(String status) {
//        String res=ServletContextInfo.getRealPath("/report");
        String res = "J:/WorkOrderManagement/web/report";
        if (status.equalsIgnoreCase(WorkOrder.APPROVED_STATUS) || status.equalsIgnoreCase(WorkOrder.RECEIVED_STATUS)) {
            res += "/approved.png";
        } else if (status.equalsIgnoreCase(WorkOrder.CANCELLED_STATUS)) {
            res += "/reject.png";
        } else {
            res = null;
        }

        return res;
    }

    public static String getCreatorName(String empId) {
        String name = "";
        User user = UserLocalServiceUtil.getUserByPrimaryKey(empId);
        name = user.getFirstName() + (user.getMiddleName().isEmpty() ? " " : " " + user.getMiddleName() + " ") + user.getLastName();
        return name;
    }

    public static String getApproveByName(Long workorderPk) {
        String name = "";
        User user = UserLocalServiceUtil.getFinalUserApprovalWO(workorderPk);
        name = user.getFirstName() + (user.getMiddleName().isEmpty() ? " " : " " + user.getMiddleName() + " ") + user.getLastName();
        return name;
    }

    public static String groupConcate(Collection wsmd) {
        String result = "";
        Iterator<WorkOrderServiceModeDetail> it = wsmd.iterator();
        boolean first = true;
        while (it.hasNext()) {
            WorkOrderServiceModeDetail o = it.next();
            result += (first ? "" : ",<br/>") + o.getServicemodeDetail().getSmdname() + " x" + o.getQuantity();
            first = false;
        }
        return result;
    }

    public static void testPrint() throws Exception {
        InputStream streamReport = JRLoader.getFileInputStream("j:/WorkOrderManagement/web/report/WorkOrder.jasper");
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        example.createCriteria().andTworkorderPkEqualTo(3L);
        List<WorkOrder> list = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinToFast(example);

        JRDataSource datasource = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(JRParameter.REPORT_DATA_SOURCE, datasource);

        JasperPrint report = JasperFillManager.fillReport(streamReport, map);
        JasperViewer.viewReport(report);
    }

    public static void testOr() {
        WorkOrderExample example = new WorkOrderExample();
        List<String> status = new ArrayList<String>();
                status.add(WorkOrder.APPROVED_STATUS);
                status.add(WorkOrder.RECEIVED_STATUS);
        example.createCriteria().andNomorwoLike("%%").andWostatusNotIn(status);
        int count = WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
        System.out.println(count);
    }

    public static void testImportUser() {
        List<String> lempid = new ArrayList();
        lempid.add("6574");
        lempid.add("6333");
        FastServiceLocalServiceUtil.importFastUserByIds(lempid);
        //FastServiceLocalServiceUtil.importFastUserByIds("6333");
    }

    public static void testFilterWO() {


        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date yesterday = null;
        try {
            yesterday = sdf.parse("07072010");
        } catch (Exception e) {
            e.printStackTrace();
        }

        WorkOrderExample example = new WorkOrderExample();
        //criteria.andWostatusEqualTo(WorkOrder.INPROGRESS_STATUS);
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, -7);
        com.ckb.wo.client.model.WorkOrderExample.Criteria criteria = example.createCriteria();
        System.out.println(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()));
        criteria.andWostatusEqualTo(WorkOrder.INPROGRESS_STATUS);
        criteria.andCreateddateGreaterThan(cal.getTime());
//            example.createCriteria().andCreateddateGreaterThan(yesterday);
        int count = WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
        System.out.println(count);

    }

    public static void testJson() {
        Map map = new HashMap();
        map.put("total", 2000);
        map.put("page", 1);
        map.put("record", 5);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonBeanProcessor(WorkOrder.class, new JsonBeanProcessor() {

            public JSONObject processBean(Object o, JsonConfig jc) {
                if (!(o instanceof WorkOrder)) {
                    return new JSONObject(true);
                }

                WorkOrder wo = (WorkOrder) o;
                String action = "<a href=\\\"view.jsp?wo=" + wo.getTworkorderPk() + "&TB_iframe=1&width=700&height='+(getHeight()-90)+'&modal=1&title=Add New Vendor\\\" rel=\\\"seylightbox\\\">view</a>";
                JSONObject result = new JSONObject().element("action", action).element("workorder", wo.getNomorwo() != null ? wo.getNomorwo() : "").element("origin", wo.getOrigin().getLocationname()).element("destination", wo.getDestination().getLocationname()).element("executiondate", new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss").format(wo.getExecutiondate()));
                return result;
            }
        });

        map.put("rows", JSONArray.fromObject(WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoin(new ExtendedWorkOrderExample()), jsonConfig));

        JSONObject obj = JSONObject.fromObject(map);

        System.out.println(obj);
    }
    //6574
    //6333

    public static boolean testCompareDate() {

        SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
        Date today = new Date();
        String date1 = "03072010";
        String date2 = "09072010";
        Date grantFrom = null;
        Date grantTo = null;

        try {
            grantFrom = sd.parse(date1);
            grantTo = sd.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (grantFrom == null || grantTo == null) {
            return false;
        } else {
            int iFrom = today.compareTo(grantFrom);
            int iTo = today.compareTo(grantTo);
            if (iFrom >= 0 && iTo <= 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static void testWorkOrderJoin() {

        WorkOrder workOrder = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(1L);
        List<WorkOrderServiceModeDetail> lwo = workOrder.getWorkOrderServiceModeDetail();
        System.out.println(lwo.size());
        for (WorkOrderServiceModeDetail smd : lwo) {
            System.out.println(smd.getServicemodeDetail().getSmdname());
        }
    }

    public static void testGenerateWorkOrderNumber() {
        System.out.println(WorkOrderLocalServiceUtil.generateWorkOrderNumber());
    }

    public static void testDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(sdf.format(new Date()));
        System.out.println(String.format("%03d", 34));


        String value = "01234567";
        System.out.println("01234567".indexOf("3"));
        System.out.println(value.substring(3, value.length()));
    }

    public static void testWorkOrderWithJoin() {

        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoin(example);
    }

    public static void testWorkOrderFlow() {

        int count = WorkOrderFlowLocalServiceUtil.countWorkOrderFlow();
        System.out.println(count);
    }

    public static void testUser() {
        UserExample example = new UserExample();
        example.createCriteria().andEmployeeIdIsNotNull();
        UserLocalServiceUtil.getEmployeeIdByExample(example);
    }

    public static void testCurrency() {
        int total = CurrencyLocalServiceUtil.countCurrency();
        System.out.println(total);
    }

    public static void testHierarchy() {

        int level_value = 4;
        String employeeId = "256";
        List<String> bossIds = new ArrayList<String>();
        bossIds.add(employeeId);
        UserExample userExample = new UserExample();

        List<String> allEmployeeBelow = new ArrayList();

        for (int i = level_value - 1; i > 0; i--) {
            userExample.createCriteria().andBossemployeeidIn(bossIds);
            List<String> empIds = UserLocalServiceUtil.getEmployeeIdByExample(userExample);
            allEmployeeBelow.addAll(empIds);
            // update boss id
            bossIds.clear();
            bossIds.addAll(empIds);
            // update userExample
            userExample.clear();
            userExample.createCriteria().andBossemployeeidIn(empIds);
        }

        // allEmployeeBelow adalah semua karyawan di bawahnya

        // select WorkOrder yg dibuat bawahannya, tanpa granted
        WorkOrderExample woExample = new WorkOrderExample();
        woExample.createCriteria().andCreatedbyemployeeidIn(allEmployeeBelow);
        //andIsGranted(false);

        // select WorkOrder yg dibuat bawahannya, dgn granted
        WorkOrderExample woExample1 = new WorkOrderExample();
        woExample1.createCriteria().andCreatedbyemployeeidIn(allEmployeeBelow);
        // andIsGranted(true)

//		WorkOrderExample example = new WorkOrderExample();
//		example.createCriteria().andCreatedbyemployeeidIn(values);

    }

    public static void testInsertWorkOrder() {

        WorkOrder workorder = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKey(1l);
        workorder.setTworkorderPk(null);
        List<WorkOrderDA> listwoDa = new ArrayList<WorkOrderDA>();
        WorkOrderDA woda1 = new WorkOrderDA();
        woda1.setDa(100000753883l);
        listwoDa.add(woda1);
        // add to workorder
        workorder.setWorkOrderDA(listwoDa);

        // manifest no : 900000149475
        // da no : 100000753883
        List<WorkOrderManifest> listwoManifest = new ArrayList<WorkOrderManifest>();
        WorkOrderManifest woman1 = new WorkOrderManifest();
        woman1.setManifestNo(900000149475l);
        listwoManifest.add(woman1);
        workorder.setWorkOrderManifest(listwoManifest);

        // tworkorder_smodedetail
        WorkOrderServiceModeDetail wosmodeDetail = new WorkOrderServiceModeDetail();
        wosmodeDetail.setQuantity(-10D);
        wosmodeDetail.setTservicemodedetailFk(-1l);
        List<WorkOrderServiceModeDetail> listwosmodedetail = new ArrayList<WorkOrderServiceModeDetail>();
        listwosmodedetail.add(wosmodeDetail);

        workorder.setWorkOrderServiceModeDetail(listwosmodedetail);

        try {
            WorkOrderLocalServiceUtil.insertWorkOrder(workorder, "2016");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void testEmail() {

        WorkOrderMailService womail = (WorkOrderMailService) BeanFactory.getBean("workordermailService");
        WorkOrder wo = new WorkOrder();
        wo.setNomorwo("WO-20190410170806-001");
        womail.sendNotificationForApproval(wo, "giansarpratiknya@gmail.com,rifan.adimansyah@ckb.co.id,vivi.yolanda@ckb.co.id,bambang.kriswantara@ckb.co.id");
    }

    public static void testLevelDropDown() {
        List<Level> lvl = WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
        for (Level l : lvl) {

            System.out.println(l.getLevelId() + " " + l.getLevelValue());
        }
    }

    public static void testFindManifestNoWithLike() {
        String manifestError = "900000198358";

        String manifest_no = "900000149475";
        List<MdaManifest> lmda = ManifestDALocalServiceUtil.getManifestByManifestNoWithLike(manifestError, "0,10");
        for (MdaManifest mda : lmda) {
            System.out.println(mda.getManifestNo());
        }
    }

    public static void testWorkOrder() {
        int count = WorkOrderLocalServiceUtil.countWorkOrder();
        System.out.println(count);
    }

    public static void testManifestWeightVolume() {

        ManifestWeightVolumeDetail mwvd = ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(900000149475l);
        System.out.println(mwvd);
    }

    public static void testVendorServiceJoin() {

        VendorService vendorService = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKeyWithJoin(1l);
        System.out.println(vendorService.getRate());
        System.out.println(vendorService.getVendor());
        System.out.println(vendorService.getDestinationLocation());

    }

    public static void testMda() {
        MdaManifestExample mmexample = new MdaManifestExample();
        mmexample.setLimitClause("0,100");
        MdaManifestDAO mmdao = (MdaManifestDAO) BeanFactory.getBean("mdamanifestDao");
//            List<MdaManifest> lmm = mmdao.selectMdaManifestByExampleWithoutBLOBs(mmexample);
//            for (Iterator iterator = lmm.iterator(); iterator.hasNext();) {
//				MdaManifest mdaManifest = (MdaManifest) iterator.next();
//				System.out.println(mdaManifest.getManifestNo());
//			}
        MdaManifest mdamanifest = mmdao.selectMdaManifestByPrimaryKey(900000149475l);
        System.out.println(mdamanifest.getCarrierNo());

    }

    public static void testInsertVendor() {

        Vendor vendor = new Vendor();
        vendor.setPph(new BigDecimal("12"));
        vendor.setVendorname("testing vendor2");
        VendorLocalServiceUtil.insertVendor(vendor);
    }

    public static void doTest() {

        FastUserExample example = new FastUserExample();
        example.createCriteria().andEmployeeIdIsNotNull();
        int value = FastServiceLocalServiceUtil.countFastUserByExample(example);
        System.out.println(value);
    }

    public static void listVendor() {

        List<Vendor> lvendor = VendorLocalServiceUtil.getVendor();
        VendorExample example = new VendorExample();
        Criteria crit = example.createCriteria();
//		crit.andActiveIsNull();
        crit.andVendorownerLike("SG");
        List<Vendor> lvendorcrit = VendorLocalServiceUtil.getVendorByExample(example);

        //


    }

    public static void testInt() {

        IbatisTestDAO testDao = (IbatisTestDAO) BeanFactory.getBean("ibatistestDao");
        List<IbatisTest> ltest = testDao.selectIbatisTestByExample(null);
        IbatisTest test1 = ltest.get(0);
        System.out.println("big data type : " + test1.getBigtest().getClass().getName());
        System.out.println("int data type : " + test1.getInttest().getClass().getName());
        long lng = 4294967296L;
        Long Lng = new Long(lng);
        //test1.setInttest(Lng);
        test1.setBigtest(Lng);

        IbatisTestExample example = new IbatisTestExample();
        example.createCriteria().andBigtestIsNotNull();
        testDao.insertIbatisTest(test1);
    }
}
