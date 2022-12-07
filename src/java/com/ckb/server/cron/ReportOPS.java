package com.ckb.server.cron;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderFlowLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import jxl.*;
import jxl.Workbook;
import jxl.write.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;
import jxl.write.DateTime;

/**
 *
 * @author arya
 */
public class ReportOPS {

    /**
     * @param args the command line arguments
     */
    Connection conn = null;
    Statement cmd = null;
    public static final int MAXROW = 54;

    public static void main(String[] args) {

        try {
            String strPathName = "";
//            String strPathName = ServletContextInfo.getRealPath("/report") + "/";

            //HERE FOR INISIATE XLS FILE
            String getDate = dateNow("ddMMMyy");
            String getTime = dateNow("h_mma");
            String fileName = strPathName + "Report_" + getDate + "_" + getTime + "_file.xls";
            WorkbookSettings ws = new WorkbookSettings();
            ws.setUseTemporaryFileDuringWrite(true);
            ws.setLocale(new Locale("en", "EN"));
//            System.out.println(fileName);
            File os = new File(fileName);
            if (!os.exists()) {
                os.createNewFile();
            }

//            WritableWorkbook workBook = Workbook.createWorkbook(os, Workbook.getWorkbook(ReportOPS.class.getResourceAsStream("/ots-tmp.xls")), ws);
            WritableWorkbook workBook = Workbook.createWorkbook(os, ws);

            //WritableWorkbook workBook = Workbook.createWorkbook(new File(fileName), ws);
            workBook.createSheet("Sheet1", 0);
            WritableSheet sheet1 = workBook.getSheet(0);
            ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
//            example.setLimitClause("1000,20");
            ExtendedWorkOrderExample.Criteria crit = example.createCriteria();
            List<String> list = new ArrayList<String>();
            if (args != null) {
                if (args.length > 0) {
                    for (int i = 0; i < args.length; i++) {
                        list.add(args[i]);
                    }
                    crit.andWostatusIn(list);
                }
            }

            writeDataSheet(sheet1, fileName, example);
            workBook.close();
            //=============================
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String dateNow(String getDateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat(getDateFormat);
        return simpleDate.format(cal.getTime());
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private static void writeDataSheet(WritableSheet s, String getFileName, ExtendedWorkOrderExample example) throws WriteException {

        try {
            int row = 0;

//            cmd = conn.createStatement();
//            rsData = cmd.executeQuery(sqlGetDb);

            System.out.println("generating Data to Excel ....(" + getFileName + ")");

            Label lbl = null;
            jxl.write.Number num = null;
            jxl.write.DateTime date = null;

            int i = 1;
            WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat hdrfmt = new WritableCellFormat(arial10font);
            hdrfmt.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            hdrfmt.setBackground(jxl.format.Colour.GRAY_25);

            DateFormat customDateFormat = new DateFormat("dd MMM yyyy hh:mm:ss");

            WritableCellFormat fmt = new WritableCellFormat(customDateFormat);

            int numOfInprogress = 0;

            int count = WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
            List<WorkOrder> lWo = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoin(example);
            Iterator<WorkOrder> iWo = lWo.iterator();

            lbl = new Label(0, row, "", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(1, row, "WO#", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(2, row, "NEW/EDIT/REVISE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(3, row, "DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(4, row, "ADHOC RATE VALIDATION", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(5, row, "DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(6, row, "APPROVED BY", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(7, row, "DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(8, row, "RECEIVED BY", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(9, row, "DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(10, row, "CANCEL RECEIVED BY", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(11, row, "DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(12, row, "CANCELLED BY", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(13, row, "DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(14, row, "ORIGIN", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(15, row, "DESTINATION", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(16, row, "VENDOR NAME", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(17, row, "WO TYPE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(18, row, "SERVICE NAME", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(19, row, "EXECUTION DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(20, row, "SERVER DATE", hdrfmt);
            s.addCell(lbl);
            lbl = new Label(21, row, "RATE TYPE", hdrfmt);
            s.addCell(lbl);
            row++;
            while (iWo.hasNext()) {
                WorkOrder wo = iWo.next();
                System.out.println(wo.getTworkorderPk());
                s.insertRow(row);

                num = new jxl.write.Number(0, row, i++);
                s.addCell(num);

                lbl = new Label(1, row, wo.getNomorwo());
                s.addCell(lbl);

                lbl = new Label(14 + numOfInprogress * 2, row, wo.getOrigin().getLocationname());
                s.addCell(lbl);
                lbl = new Label(14 + numOfInprogress * 2 + 1, row, wo.getDestination().getLocationname());
                s.addCell(lbl);
                lbl = new Label(14 + numOfInprogress * 2 + 2, row, wo.getVendor().getVendorname());
                s.addCell(lbl);
                lbl = new Label(14 + numOfInprogress * 2 + 3, row, wo.getOrder().getOrdername());
                s.addCell(lbl);
                lbl = new Label(14 + numOfInprogress * 2 + 4, row, wo.getServiceType().getServicename());
                s.addCell(lbl);
                date = new DateTime(14 + numOfInprogress * 2 + 5, row, wo.getExecutiondate(), fmt);
                s.addCell(date);
                date = new DateTime(14 + numOfInprogress * 2 + 6, row, wo.getCreateddate(), fmt);
                s.addCell(date);
                lbl = new Label(14 + numOfInprogress * 2 + 7, row, wo.getAdhoc() ? "Adhoc" : "Regular");
                s.addCell(lbl);
                //flow start
                List<WorkOrderFlow> lflow = WorkOrderFlowLocalServiceUtil.getWorkOrderFlowByWorkOrder(wo.getTworkorderPk(), null, "tworkorderflow_pk ASC");
                Iterator<WorkOrderFlow> iflow = lflow.iterator();
                int x = 0, y = 0;
                while (iflow.hasNext()) {
                    try {
                        String action = "";
                        WorkOrderFlow wof = iflow.next();
                        User usr = UserLocalServiceUtil.getUserByPrimaryKey(wof.getEmployeeid());
                        String name = "";
                        name = usr.getFirstName();
                        name += usr.getMiddleName().isEmpty() ? "" : " " + usr.getMiddleName();
                        name += usr.getLastName().isEmpty() ? "" : " " + usr.getLastName();

                        if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.NEW_STATUS)
                                || wof.getOldwostatus().equalsIgnoreCase(WorkOrder.EDIT_STATUS)
                                || wof.getOldwostatus().equalsIgnoreCase(WorkOrder.REVISION_STATUS)) {
                            x = 0;
                            y = 0;
                            if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.EDIT_STATUS)
                                    || wof.getOldwostatus().equalsIgnoreCase(WorkOrder.REVISION_STATUS)) {
                                row++;
                                action = wof.getOldwostatus() + "-";
                                lbl = new Label(1, row, wo.getNomorwo());
                                s.addCell(lbl);
                            }
                        } else if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.VALIDATION_STATUS)) {
                            x = 1;
                        } else if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.INPROGRESS_STATUS)) {
                            if (UserLocalServiceUtil.isProcurementUser(usr.getEmployeeId())) {
                                if (iflow.hasNext()) {
                                    continue;
                                } else {
                                    break;
                                }
                            }
                            x = 2 + y;
                            y++;
                            if (y > numOfInprogress) {
                                numOfInprogress = y;
                                s.insertColumn(y * 2 + 4);
                                s.insertColumn(y * 2 + 5);
                                lbl = new Label(y * 2 + 4, 0, "STEP-" + y, hdrfmt);
                                s.addCell(lbl);
                                lbl = new Label(y * 2 + 5, 0, "DATE", hdrfmt);
                                s.addCell(lbl);

                            }

                        } else if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.APPROVED_STATUS)) {
                            x = 2 + numOfInprogress;
                        } else if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.RECEIVED_STATUS)) {
                            x = 2 + numOfInprogress + 1;
                        } else if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.CANCEL_RECEIVED_STATUS)) {
                            x = 2 + numOfInprogress + 2;
                        } else if (wof.getOldwostatus().equalsIgnoreCase(WorkOrder.CANCELLED_STATUS)) {
                            x = 2 + numOfInprogress + 3;
                        } else {
                            if (iflow.hasNext()) {
                                continue;
                            } else {
                                break;
                            }
                        }

                        lbl = new Label(2 + (x * 2), row, action + name);
                        s.addCell(lbl);

                        date = new DateTime(3 + (2 * x), row, wof.getActiondate(), fmt);
                        x++;
                        s.addCell(date);
                    } catch (Exception e) {
                        System.out.println("Error");
                        System.out.println(wo.getTworkorderPk());
                    }
                }

                //flow end

                row++;
            }

//            strSQL = " SELECT m.description description, 0 as qty, m.length length, m.width width, m.height height, m.volume volume, m.weight weight, m.mps da, concat_ws('-',s.origin,s.destination) destination, concat('Refer to DA#',m.da) remarks"
//                    + " from `fast`.t_mps m left join `fast`.t_shippment s on m.da=d.da"
//                    + " where m.mps in (%s)";
//            strSQL = String.format(strSQL, smpsList);
//            System.out.println(strSQL);

            System.out.println(getFileName + " Generated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
