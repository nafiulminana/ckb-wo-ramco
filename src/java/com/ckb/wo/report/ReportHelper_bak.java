/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.report;

import com.ckb.wo.client.listener.ServletContextInfo;
import com.ckb.wo.client.model.*;
import com.ckb.wo.server.service.util.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Amaran Sidhiq
 */
public class ReportHelper_bak
{

    public static void main(String[] args) {
        try {
            testprint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Shipment getShipment(Long da) {
//        String res=ServletContextInfo.getRealPath("/report");'
//        ShipmentExample example = new ShipmentExample();
//        example.createCriteria().andDaEqualTo(da);
//        List<Shipment> shipment = ManifestDALocalServiceUtil.getShipmentByExample(example);

//        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Result:" + shipment.get(0)));
//        if (shipment != null || !shipment.isEmpty()) {
//            return shipment.get(0);
//        }
        return ManifestDALocalServiceUtil.getShipmentByPrimaryKey(da);
    }

    public static String getGroupSubtotal(Long wopk) {
        String result = "";
        WorkOrder wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKey(wopk);
        List<WorkOrderServiceModeDetail> lwo = WorkOrderLocalServiceUtil.getWorkOrderSubTotal(wopk);
        if (lwo != null) {
            Iterator<WorkOrderServiceModeDetail> it = lwo.iterator();
            NumberFormat nf = getNf();
            nf.setGroupingUsed(true);
            while (it.hasNext()) {
                WorkOrderServiceModeDetail wosmd = it.next();
                result += wo.getCancellationfee() == null ? "" : "<strike>";
                result += wosmd.getCurrency().getCurrencycode() + " ";
//                result += String.format("%,.2f", wosmd.getSubtotal());
                result += nf.format(wosmd.getSubtotal());
                result += wo.getCancellationfee() == null ? "" : "</strike>";
                if (it.hasNext()) {
                    result += "<br/>";
                }
            }
            if (wo.getCancellationfee() != null) {
                result += "<br/>Cancellation Fee:<br/>";
                result += CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(wo.getCancellationtcurrencyfk()).getCurrencycode() + " " + nf.format(wo.getCancellationfee());
            }
        }
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("LWO Size:" + lwo.size() + "\nResult:" + result));
        return result;
    }

    public static String getManDaGroup(Collection wopk) {
        String result = "";
        if (wopk != null) {
            Iterator lst = wopk.iterator();
            if (lst != null) {
                while (lst.hasNext()) {
                    Object o = lst.next();
                    WorkOrderManifest m = null;
                    WorkOrderDA d = null;
                    if (o instanceof WorkOrderManifest) {
                        m = (WorkOrderManifest) o;
                        result += m.getManifestNo();
                    } else if (o instanceof WorkOrderDA) {
                        d = (WorkOrderDA) o;
                        result += d.getDa();
                    }
                    if (lst.hasNext()) {
                        result += "\n";
                    }
                }
                logger.LoggerClass.logDebug(ReportHelper.class, new Exception("LWO Size:" + wopk.size() + "\nResult:" + result));
            }
        }
        return result;
    }

    public static String getPath(String status) {
        String res = ServletContextInfo.getRealPath("/report");
//        String res = "J:/WorkOrderManagement/web/report";
        if (status.equalsIgnoreCase(WorkOrder.APPROVED_STATUS)
                || status.equalsIgnoreCase(WorkOrder.PRINTED_STATUS)
                || status.equalsIgnoreCase(WorkOrder.RECEIVED_STATUS)) {
            res += "/approved.png";
        } else if (status.equalsIgnoreCase(WorkOrder.CANCELLED_STATUS)
                || status.equalsIgnoreCase(WorkOrder.CANCEL_RECEIVED_STATUS)) {
            res += "/reject.png";
        } else {
            res = null;
        }
//        System.out.println(res);
        logger.LoggerClass.logError(new Exception("Path:" + res));
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Path:" + res));
        return res;
    }

    public static String getCreatorName(String empId) {
        String name = "";
        User user = UserLocalServiceUtil.getUserByPrimaryKey(empId);
        name = user.getFirstName() + (user.getMiddleName().isEmpty() ? " " : " " + user.getMiddleName() + " ") + user.getLastName();
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Creator:" + name));
        return name;
    }

    public static String getName(String status, Long workOrderPk) {
        if (status.equalsIgnoreCase(WorkOrder.APPROVED_STATUS) || status.equalsIgnoreCase(WorkOrder.PRINTED_STATUS) || status.equalsIgnoreCase(WorkOrder.RECEIVED_STATUS)) {
            return getApproveByName(workOrderPk);
        } else if (status.equalsIgnoreCase(WorkOrder.CANCELLED_STATUS) || status.equalsIgnoreCase(WorkOrder.CANCEL_RECEIVED_STATUS)) {
            return getCanceledByName(workOrderPk);
        }
        return null;
    }

    public static String getApproveByName(Long workorderPk) {
        String name = "";
        User user = UserLocalServiceUtil.getFinalUserApprovalWO(workorderPk);
        name = user.getFirstName() + (user.getMiddleName().isEmpty() ? " " : " " + user.getMiddleName() + " ") + user.getLastName() + (user.getLevel() != null ? "\n(" + user.getLevel().getDescription() + ")" : "");
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Approved By:" + name));
        return name;
    }

    public static String getCanceledByName(Long workorderPk) {
        String name = "";
        User user = UserLocalServiceUtil.getFinalUserCancellationWO(workorderPk);
        name = user.getFirstName() + (user.getMiddleName().isEmpty() ? " " : " " + user.getMiddleName() + " ") + user.getLastName() + (user.getLevel() != null ? "\n(" + user.getLevel().getDescription() + ")" : "");
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Approved By:" + name));
        return name;
    }

    public static String smodeConcate(Long wopk, BigDecimal ppn, BigDecimal pph) {
        WorkOrderServiceModeDetailExample ex=new WorkOrderServiceModeDetailExample();
        ex.createCriteria().andTworkorderFkEqualTo(wopk);
        return smodeConcate(WorkOrderServiceModeDetailLocalServiceUtil.getWorkOrderServiceModeDetailByExample(ex), ppn, pph);
    }
    public static String smodeConcate(Collection wsmd, BigDecimal ppn, BigDecimal pph) {
        String result = "";
        Iterator<WorkOrderServiceModeDetail> it = wsmd.iterator();
        while (it.hasNext()) {
            WorkOrderServiceModeDetail o=it.next();
            o.setServicemodeDetail(ServiceModeDetailLocalServiceUtil.getServiceModeDetailByPrimaryKey(o.getTservicemodedetailFk()));
            if (o.isDeleted()) {
                continue;
            }
            result += ServiceModeLocalServiceUtil.getServiceModeByPrimaryKey(o.getServicemodeDetail().getTservicemodeFk()).getSmodename()
                    + (o.getSmddetailname() != null && !o.getSmddetailname().isEmpty() ? "\n(" + o.getSmddetailname() + ")" : "")
                    + (o.getSmdremarks() != null && !o.getSmdremarks().isEmpty() ? "\n(" + o.getSmdremarks() + ")" : "");
            if (it.hasNext()) {
                result += "\n";
            }
//            System.out.println(result);
        }
        if (ppn != null || pph != null) {
            result += "<br/>Include :";
        }
        if (ppn != null) {
            result += String.format("\nPPN: %.2f", ppn) + "%";
        }
        if (pph != null) {
            result += String.format("\nPPH: %.2f", pph) + "%";
        }
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Result:" + result));
        return result;
    }
    static DecimalFormat nf = null;

    public static NumberFormat getNf() {
        if (nf == null) {
            nf = (DecimalFormat) NumberFormat.getCurrencyInstance();
            nf.applyPattern("###,##0.00##");
        }

        return nf;
    }

    public static String groupConcate(Collection wsmd, BigDecimal ppn, BigDecimal pph) {
        String result = "";
        Iterator<WorkOrderServiceModeDetail> it = wsmd.iterator();
        while (it.hasNext()) {
            WorkOrderServiceModeDetail o = it.next();
            if (o.isDeleted()) {
                continue;
            }
            result += o.getServicemodeDetail().getSmdname() + " x" + o.getQuantity() + (o.getSmdcharge() == null ? "" : "@" + o.getCurrency().getCurrencycode() + " " + getNf().format(o.getSmdcharge()))
                    + (o.getSmddetailname() != null && !o.getSmddetailname().isEmpty() ? "<br/>(" + o.getSmddetailname() + ")" : "")
                    + (o.getSmdremarks() != null && !o.getSmdremarks().isEmpty() ? "<br/>(" + o.getSmdremarks() + ")" : "");
            if (it.hasNext()) {
                result += ";<br/>";
            }
        }
        if (ppn != null || pph != null) {
            result += "<br/>Include :";
        }
        if (ppn != null) {
            result += String.format("<br/>PPN: %.2f", ppn) + "%";
        }
        if (pph != null) {
            result += String.format("<br/>PPH: %.2f", pph) + "%";
        }
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Result:" + result));
        return result;
    }

    public static String getRemark(Collection<WorkOrderServiceModeDetail> col) {
        String result = "";
        result += "<ul>";
        Iterator<WorkOrderServiceModeDetail> rem = col.iterator();
        while (rem.hasNext()) {
            WorkOrderServiceModeDetail o = rem.next();
            if (!o.getSmdremarks().trim().isEmpty()) {
                result += "<li>";
                result += o.getSmdremarks();
                result += "</li>";
            }
        }
        result += "</ul><br/>";
        logger.LoggerClass.logDebug(ReportHelper.class, new Exception("Result:" + result));
        return result;
    }

    public static void testprint() throws Exception {
        String path = "/home/shido/NetBeansProjects/WorkOrderManagement/web/report/";
//        String path = "j:/WorkOrderManagement/web/report/";
//        InputStream streamReport = JRLoader.getFileInputStream(path + "fin.jasper");
        InputStream streamReport = JRLoader.getFileInputStream(path + "WorkOrder.jasper");

        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        example.createCriteria().andTworkorderPkEqualTo(68682L);
        List<WorkOrder> list = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinToFast(example);
//        list.get(0).setLasttlevel(6);
//        list.get(0).setWostatus("PRINTED");
        JRDataSource datasource = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("SUBREPORT_DIR", path);
        map.put(JRParameter.REPORT_DATA_SOURCE, datasource);
        map.put(JRParameter.IS_IGNORE_PAGINATION, true);


        JasperPrint report = JasperFillManager.fillReport(streamReport, map);
        JasperViewer jv = new JasperViewer(report);
        jv.setTitle("Work Order Management");
        jv.setVisible(true);

//        JRExporter exporter = new net.sf.jasperreports.engine.export.JExcelApiExporter();
//
//        exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT,report);
//        exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM,new FileOutputStream("test.xls"));
//        exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
//        exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//        exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//        exporter.exportReport();
    }

    public static void toXls(OutputStream out) throws Exception {
//        String path = "j:/WorkOrderManagement/web/report/";
        String path = ServletContextInfo.getRealPath("/report") + "/";
        InputStream streamReport = JRLoader.getFileInputStream(path + "fin.jasper");

        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        ExtendedWorkOrderExample.ExtendedCriteria criteria=example.createCriteria();
        criteria.andCreateddateGreaterThanOrEqualTo(cal.getTime());
        criteria.setAndCustomSQL("atworkorder.wostatus IN ('" + WorkOrder.PRINTED_STATUS + "', '" + WorkOrder.APPROVED_STATUS + "') "
                + " OR (atworkorder.cancellationfee IS NOT NULL AND atworkorder.wostatus = '" + WorkOrder.CANCELLED_STATUS + "')");;

//        List<WorkOrder> list = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinForFinance(example);
                
//        System.out.println(WorkOrderLocalServiceUtil.countWorkOrderByExample(example));
//                example.setLimitClause("10");
                
        List<WorkOrder> list = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoin(example);
        JRDataSource datasource = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("SUBREPORT_DIR", path);
        map.put(JRParameter.REPORT_DATA_SOURCE, datasource);
        map.put(JRParameter.IS_IGNORE_PAGINATION, true);
        
        
        JasperPrint report = JasperFillManager.fillReport(streamReport, map);
        
//        JasperViewer jv = new JasperViewer(report);
//        jv.setTitle("Work Order Management");
//        jv.setVisible(true);

        JRExporter exporter = new net.sf.jasperreports.engine.export.JExcelApiExporter();

        exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, report);
        exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, out);
        exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
        exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.exportReport();
    }

    public static void print(OutputStream out, Long workorderPk, boolean type) throws Exception {
        
        String path = ServletContextInfo.getRealPath("/report") + "/";
        InputStream streamReport = JRLoader.getFileInputStream(path + "WorkOrder" + (type ? "Da" : "") + ".jasper");
//        InputStream streamReport = JRLoader.getFileInputStream("J:/WorkOrderManagement/web/report/WorkOrderDA.jasper");
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        example.createCriteria().andTworkorderPkEqualTo(workorderPk);
        List<WorkOrder> list = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinToFast(example);

        JRDataSource datasource = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("SUBREPORT_DIR", path);
        map.put(JRParameter.REPORT_DATA_SOURCE, datasource);
        if (type ? list.get(0).getWorkOrderDA().size() < 5 : list.get(0).getWorkOrderManifest().size() < 5) {
            map.put(JRParameter.IS_IGNORE_PAGINATION, true);
        }

        JasperPrint report = JasperFillManager.fillReport(streamReport, map);
        JRExporter exporter =
                new net.sf.jasperreports.engine.export.JRPdfExporter();
        exporter.setParameter(
                JRExporterParameter.OUTPUT_STREAM,
                out);
        exporter.setParameter(
                JRExporterParameter.JASPER_PRINT, report);
        exporter.exportReport();
    }

    public static void printHtml(OutputStream out, Long workorderPk, HttpSession session, boolean type) throws Exception {
        String path = ServletContextInfo.getRealPath("/report") + "/";
//        System.out.println(path);
        InputStream streamReport = JRLoader.getFileInputStream(path + "WorkOrder" + (type ? "Da" : "") + ".jasper");
        ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
        example.createCriteria().andTworkorderPkEqualTo(workorderPk);
        List<WorkOrder> list = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinToFast(example);
        JRDataSource datasource = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(JRParameter.REPORT_DATA_SOURCE, datasource);
//        if (type ? list.get(0).getWorkOrderDA().size() < 5 : list.get(0).getWorkOrderManifest().size() < 5) {
        map.put(JRParameter.IS_IGNORE_PAGINATION, true);
//        }
        map.put("SUBREPORT_DIR", path);

        JasperPrint report = JasperFillManager.fillReport(streamReport, map);
        JRExporter exporter =
                new net.sf.jasperreports.engine.export.JRHtmlExporter();
        session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, report);
        exporter.setParameter(
                JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true);
        exporter.setParameter(
                JRHtmlExporterParameter.IMAGES_URI,
                ServletContextInfo.getContextPath() + "/ImageServlet?rand=" + UUID.randomUUID() + "&image=");
        exporter.setParameter(
                JRExporterParameter.OUTPUT_STREAM,
                out);
        exporter.setParameter(
                JRExporterParameter.JASPER_PRINT, report);
        exporter.exportReport();
    }
}
