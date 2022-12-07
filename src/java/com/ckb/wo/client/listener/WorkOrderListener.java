package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyRate;
import com.ckb.wo.client.model.DeliveryTerm;
import com.ckb.wo.client.model.DeliveryTermExample;
import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.Notes;
import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.ServiceModeDetailExample;
import com.ckb.wo.client.model.ServiceModeExample;
import com.ckb.wo.client.model.Shipment;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExt;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderDA;
import com.ckb.wo.client.model.WorkOrderManifest;
import com.ckb.wo.client.model.WorkOrderServiceModeDetail;
import com.ckb.wo.server.exception.SecurityException;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.CurrencyRateLocalServiceUtil;
import com.ckb.wo.server.service.util.DeliveryTermLocalServiceUtil;
import com.ckb.wo.server.service.util.LocationLocalServiceUtil;
import com.ckb.wo.server.service.util.ManifestDALocalServiceUtil;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import com.ckb.wo.server.service.util.OrderLocalServiceUtil;
import com.ckb.wo.server.service.util.ServiceModeDetailLocalServiceUtil;
import com.ckb.wo.server.service.util.ServiceModeLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import com.shido.jdbc2orm.JDBCConnector;
import com.shido.jdbc2orm.ResultMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;

public class WorkOrderListener extends HttpServlet {

    final static Logger log = Logger.getLogger(WorkOrderListener.class);

    public WorkOrderListener() {
    }

    public String getContainerList(Long mode) {//,Long selected) {
        StringBuilder result = new StringBuilder();
        try {
            ServiceModeDetailExample example = new ServiceModeDetailExample();
            example.createCriteria().andTservicemodeFkEqualTo(mode);
            List<ServiceModeDetail> smodedetail = ServiceModeDetailLocalServiceUtil.getServiceModeDetailByExample(example);

            Iterator<ServiceModeDetail> itSmd = smodedetail.iterator();

            result.append("<option value=\"---\">");
            result.append("Select Service");
            result.append("</option>");

            ServiceModeDetail smdb = (ServiceModeDetail) WebContextFactory.get().getSession().getAttribute("serviceModeDetail");
            while (itSmd.hasNext()) {
                ServiceModeDetail smd = itSmd.next();
                if (smdb == null) {
                    result.append("<option value=\"").append(smd.getTservicemodedetailPk()).append("\" >");
                } else {
//                    System.out.println(smd.getTservicemodedetailPk() + " = " + smdb.getTservicemodedetailPk() + "(" + smdb.getSmdname() + ") is " + smd.getTservicemodedetailPk().equals(smdb.getTservicemodedetailPk()));
                    result.append("<option value=\"").append(smd.getTservicemodedetailPk()).append("\" ").append(smd.getTservicemodedetailPk().equals(smdb.getTservicemodedetailPk()) ? "selected" : "").append(">");
                }
                result.append(smd.getSmdname());
                result.append("</option>\r\n");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result.toString();
    }

    public static String getCharges(List<WorkOrderServiceModeDetail> list) {
        log.info("Get Charges");
        String charges = "";
        Map<String, BigDecimal> chgs = new HashMap<>();
        for (WorkOrderServiceModeDetail o : list) {
            if (o.isDeleted()) {
                continue;
            }
            if (o.getCurrency() != null && o.getCurrency().getCurrencycode() != null && !o.getCurrency().getCurrencycode().isEmpty()) {
                if (o.getSmdcharge() != null && o.getQuantity() != null) {
                    String cc = o.getCurrency().getCurrencycode();
                    if (chgs.get(cc) == null) {
                        chgs.put(cc, o.getSmdcharge().multiply(new java.math.BigDecimal(o.getQuantity())));
                    } else {
                        chgs.put(cc, chgs.get(cc).add(o.getSmdcharge().multiply(new java.math.BigDecimal(o.getQuantity()))));
                    }
                } else {
                    log.info("Smdcharge or Quantity is not present");
                }
            } else {
                log.info("Currency is not present");
            }
        }
        for (String o : chgs.keySet()) {
            charges += o + " " + String.format("%,.2f", chgs.get(o)) + "<br/>";
        }
        log.info("Charges = " + charges);
        return charges;
    }

    public static String getChargesInternal(List<WorkOrderServiceModeDetail> list) {
        String charges = "";
        Map<String, java.math.BigDecimal> chgs = new HashMap<>();
        java.util.Iterator<com.ckb.wo.client.model.WorkOrderServiceModeDetail> chgl = list.iterator();
        while (chgl.hasNext()) {
            com.ckb.wo.client.model.WorkOrderServiceModeDetail o = chgl.next();
            if (o.isDeleted()) {
                continue;
            }
            try {
                if (chgs.get(o.getCurrency().getCurrencycode()) == null) {
                    chgs.put(o.getCurrency().getCurrencycode(), o.getSmdcharge().multiply(new java.math.BigDecimal(o.getQuantity())));
                } else {
                    chgs.put(o.getCurrency().getCurrencycode(), chgs.get(o.getCurrency().getCurrencycode()).add(o.getSmdcharge().multiply(new java.math.BigDecimal(o.getQuantity()))));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        java.util.Iterator<String> it = chgs.keySet().iterator();
        while (it.hasNext()) {
            String o = it.next();
//            charges += o + " " + String.format("%f", chgs.get(o));
            charges += chgs.get(o);
        }
        return charges;
    }

    public String getDeliveryTerms(Long mode) {//,Long selected) {
        StringBuilder result = new StringBuilder();
        try {
            DeliveryTermExample example = new DeliveryTermExample();
            example.createCriteria().andTservicemodeFkEqualTo(mode);
            List<DeliveryTerm> dTerms = DeliveryTermLocalServiceUtil.getDeliveryTermByExample(example);

            Iterator<DeliveryTerm> itTerms = dTerms.iterator();
            result.append("<option value=\"---\">");
            result.append("Select Delivery Terms");
            result.append("</option>\r\n");

            WorkOrder wo = (WorkOrder) WebContextFactory.get().getSession().getAttribute("wo");
            while (itTerms.hasNext()) {
                DeliveryTerm terms = itTerms.next();
                if (wo == null) {
                    result.append("<option value=\"").append(terms.getTdeliverytermPk()).append("\">");
                } else {
                    result.append("<option value=\"").append(terms.getTdeliverytermPk()).append("\" ").append(terms.getTdeliverytermPk().equals(wo.getTdeliverytermFk()) ? "selected" : "").append(">");
                }
                result.append(terms.getDtname());
                result.append("</option>\r\n");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result.toString();
    }

    public String getRateAggreement(Long vendorServicePk) {
        VendorService vs = null;
        try {
            vs = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vendorServicePk);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return vs.getRefagreementno() == null || vs.getRefagreementno().isEmpty() ? "---" : vs.getRefagreementno();
    }

    public String getRateAggreementDateString(Long vendorServicePk) {
        VendorService vs = null;
        try {
            vs = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vendorServicePk);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String result = "---";
        try {
            result = vs.getRefagreementdate() == null ? "---" : new SimpleDateFormat("dd MMMM yyyy").format(vs.getRefagreementdate());
        } catch (Exception e) {
        }
        return result;
    }

    public String approve(Long woPk, String reason) {
        try {
            UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
//            if (!WorkOrderAppList.validate(woPk).contains("Work Order is not valid!")) {
            WorkOrderLocalServiceUtil.approveWorkOrder(woPk, reason, ub.getEmployeeId());
//            } else {
//                return "Work Order cannot be approved! Charges is not valid, check validate wo for detail!";
//            }

        } catch (SecurityException e) {
            log.error(e.getMessage(), e);
            return "Approval Failed: " + e.getMessage();
        }
        return "Work Order Approved!";
    }

    public boolean edit(Long woPk, String reason) {
        try {
            if (reason.trim().isEmpty()) {
                return false;
            }
            UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
            WorkOrderLocalServiceUtil.editWorkOrder(woPk, reason, ub.getEmployeeId());
            Notes note = new Notes();
            note.setNoteType("EDIT");
            note.setEmployeeId(ub.getEmployeeId());
            note.setNotes(reason);
            note.setTworkorderFk(woPk);
            NotesLocalServiceUtil.saveNotes(note);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean cancel(Long woPk, String reason, String cancelationfee, Long tcurrencyFk) {
        log.info("Cancelling WO, wo_pk=" + woPk + ", Reason=" + reason + ", Cancelation_Fee=" + cancelationfee + ", Currency=" + tcurrencyFk);
        try {
            if (reason.trim().isEmpty()) {
                return false;
            }
            UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
            if (ub == null) {
                return false;
            }
            BigDecimal cancelationfees = null;
            try {
                cancelationfees = new BigDecimal(cancelationfee);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            WorkOrderLocalServiceUtil.cancelWorkOrder(woPk, reason, cancelationfees, tcurrencyFk, ub.getEmployeeId());
            
            final JDBCConnector fcon = new JDBCConnector("fast");
            fcon.executeNonQuery("delete from t_manifest_wo where tworkorder_pk='" + woPk + "'");

            Notes note = new Notes();
            note.setNoteType("CANCELED");
            note.setEmployeeId(ub.getEmployeeId());
            note.setNotes(reason);
            note.setTworkorderFk(woPk);
            NotesLocalServiceUtil.saveNotes(note);
        } catch (SecurityException | SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try {
                com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) request.getSession().getAttribute("loginInfo");
                if (ub != null) {
                    if (!ub.isLogon()) {
                        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                        return;
                    }
                } else {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                    return;
                }

                String path = "/workorder/workorder.jsp";
                System.gc();

                if (ServletFileUpload.isMultipartContent(request)) {
                    String errorMessage = "";
                    try {
                        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        for (FileItem item : multiparts) {
                            if (!item.isFormField()) {
                                String name = new File(item.getName()).getName();
                                log.info("Uploading file " + name);
                                //File file = new File("D:/CKB/upload" + File.separator + name);
                                File file = new File("/home/wo_uploaded_file" + File.separator + name);
                                item.write(file);
                                String readLine = "";
                                if (file.canRead()) {
                                    BufferedReader br = new BufferedReader(new FileReader(file));
                                    while ((readLine = br.readLine()) != null) {
                                        String r = handleManifestInput(request, response, readLine, ub, path);
                                        if (!r.isEmpty()) {
                                            errorMessage = errorMessage + r + "<br>";
                                        }
                                    }
                                    List<Level> levels = WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
                                    request.setAttribute("levels", levels);
                                    path = "/workorder/workorder_manifest.jsp";
                                }
                            }
                        }
                        request.setAttribute("errorMessage", errorMessage);
                    } catch (Exception e) {
                        request.setAttribute("errorMessage", e.getMessage());
                    }
                } else if (request.getParameter("woNext") != null || request.getParameter("handlingBack") != null || request.getParameter("regularvendorBack") != null || request.getParameter("adhocvendorBack") != null || request.getParameter("edit") != null || request.getParameter("rev") != null) {
                    log.info(ub.getUserId() + " Step 1");
                    int woType = 0;
                    WorkOrder wo = null;
                    try {
                        if (request.getParameter("edit") != null || request.getParameter("rev") != null) {
                            Long noWo = null;
                            if (request.getParameter("edit") != null) {
                                noWo = new Long(request.getParameter("edit"));
                            } else {
                                noWo = new Long(request.getParameter("rev"));
                            }
                            
                            wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(noWo);
                            if (wo != null) {
                                boolean val = wo.getWostatus().equals(WorkOrder.EDIT_STATUS);
                                val = val ? true : wo.getWostatus().equals(WorkOrder.APPROVED_STATUS);
                                val = val ? true : wo.getWostatus().equals(WorkOrder.PRINTED_STATUS);
                                if (!val) {
                                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                                    return;
                                } else if (wo.getWostatus().equals(WorkOrder.APPROVED_STATUS) || wo.getWostatus().equals(WorkOrder.PRINTED_STATUS)) {
                                    wo.setWostatus(WorkOrder.REVISION_STATUS);
//                                    request.setAttribute("errorMessage", wo.getWostatus());
                                }
                                try {
                                    if (wo.getTorderFk() == 1) {
                                        WorkOrderAppList.validate(wo, true);
                                    }
                                } catch (Exception e) {
                                }
                                List<WorkOrderServiceModeDetail> lwsmd = wo.getWorkOrderServiceModeDetail();
                                if (lwsmd.size() == 1 && wo.getTservicetypeFk() != 2) {
                                    //wo.setAdhoc(false);
                                    request.getSession().setAttribute("serviceModeDetail", lwsmd.get(0).getServicemodeDetail());
                                    request.getSession().setAttribute("fromRegular", true);
                                }
                                if (wo.getAdhoc()) {
                                    woType = 1;
                                }
                                if (wo.getTservicetypeFk() == 2) {
                                    woType = 2;
                                }
                                log.info(ub.getUserId() + " " + wo.toString());
                            }
                        } else {
                            wo = (WorkOrder) request.getSession().getAttribute("wo");
                        }
                        if (request.getParameter("woNext") != null) {
                            woType = new Integer(request.getParameter("wotype"));
                            if (wo == null) {
                                wo = new WorkOrder();
                            }
                        } else if (request.getParameter("regularvendorBack") != null) {
                            if (wo == null) {
                                response.sendRedirect(getServletContext().getContextPath() + "/workorder/workorder.jsp");
                                return;
                            }
                            if (wo.getAdhoc() != null && !wo.getAdhoc()) {
                                woType = 0;
                            } else {
                                woType = 1;
                            }
                        } else if (request.getParameter("adhocvendorBack") != null) {
                            if (wo == null) {
                                response.sendRedirect(getServletContext().getContextPath() + "/workorder/workorder.jsp");
                                return;
                            }
                            if (!wo.getAdhoc()) {
                                woType = 0;
                            } else {
                                woType = 1;
                            }
                        } else if (request.getParameter("handlingBack") != null) {
                            if (wo == null) {
                                response.sendRedirect(getServletContext().getContextPath() + "/workorder/workorder.jsp");
                                return;
                            }
                            woType = 2;
                        }
                    } catch (IOException | NumberFormatException e) {
                        log.error(e.getMessage(), e);
                    }

                    List<Location> location = LocationLocalServiceUtil.getLocation();
                    request.setAttribute("location", location);
                    List<Order> orders = OrderLocalServiceUtil.getOrder();
                    request.setAttribute("orders", orders);

                    switch (woType) {
                        case 0:
                        case 1:
                            ServiceModeExample exm = new ServiceModeExample();
                            exm.createCriteria().andTservicetypeFkEqualTo(1L);
                            List<ServiceMode> serviceMode = ServiceModeLocalServiceUtil.getServiceModeByExample(exm);
                            request.setAttribute("serviceMode", serviceMode);
                            List<DeliveryTerm> dTerms = DeliveryTermLocalServiceUtil.getDeliveryTerm();
                            request.setAttribute("deliveryTerm", dTerms);
                            request.setAttribute("locationfromdesc", getLocation(wo.getOrigintlocationFk()));
                            request.setAttribute("locationdestdesc", getLocation(wo.getDestinationtlocationFk()));
                            path = "/workorder/workorder_regular.jsp";
                            //wo.setAdhoc(false);
                            wo.setTservicetypeFk(1L);
                            break;
                        /*case 1:System.out.println("woType 1");
                            path = "/workorder/workorder_adhoc.jsp";
                            wo.setAdhoc(true);
                            wo.setTservicetypeFk(1L);
//                            wo.setTorderFk(1L);
                            request.getSession().setAttribute("locationfrom", getLocation(wo.getOrigintlocationFk()));
                            request.getSession().setAttribute("locationdest", getLocation(wo.getDestinationtlocationFk()));
                            break;*/
                        case 2:
                            wo.setAdhoc(true);
                            path = "/workorder/workorder_handling.jsp";
                            if (wo.getTworkorderPk() != null) {
                                Iterator<WorkOrderDA> das = wo.getWorkOrderDA().iterator();
                                List<Shipment> shipments = new ArrayList<>();
                                while (das.hasNext()) {
                                    WorkOrderDA woda = das.next();
                                    try {
                                        if (woda.getDa() == 100002461656L) {
                                            System.out.println("break");
                                        }
                                        Shipment shipment = ManifestDALocalServiceUtil.getShipmentByPrimaryKey(woda.getDa());
                                        shipment.setPod(woda.isPod());

                                        shipments.add(shipment);
                                        shipment = null;
                                    } catch (Exception e) {
                                        log.error(woda.getDa() + " Error getting DA Info");
                                    }
                                }
                                request.getSession().setAttribute("da", shipments);

                                System.gc();
                            }
                            wo.setTservicetypeFk(2L);
                            break;
                        default:
                            throw new ServerException("Unknown parameter passed: wotype=" + woType);
                    }
                    log.info(ub.getUserId() + " " + wo.toString());
                    request.getSession().setAttribute("wo", wo);
                } else if (request.getParameter("regularManifestBack") != null || request.getParameter("adhocManifestBack") != null) {
                    WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
                    if (wo != null) {
                        if (request.getParameter("adhocManifestBack") == null) {
                            ServiceModeDetail smd = (ServiceModeDetail) request.getSession().getAttribute("serviceModeDetail");
                            if (wo.getTorderFk() != 1) {
                                List<VendorService> vservices = VendorServiceLocalServiceUtil.getVendorServiceByAttributeWithTerm(wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getExecutiondate(), wo.getTorderFk(), wo.getTservicemodeFk(), smd.getTservicemodedetailPk(), wo.getTdeliverytermFk());
                                if (vservices.isEmpty()) {
                                    wo.setAdhoc(true);
                                    wo.setTvendorserviceFk(null);
                                    path = "/workorder/workorder.do?adhocvendorBack=true";
                                } else {
                                    Map<String, List> vendorGroup = new HashMap();
                                    for (VendorService o : vservices) {
                                        List lo = vendorGroup.get(o.getVendor().getVendorname());
                                        if (lo == null) {
                                            lo = new ArrayList();
                                        }
                                        lo.add(o);
                                        vendorGroup.put(o.getVendor().getVendorname(), lo);
                                    }
                                    request.setAttribute("vendors", vendorGroup);
                                    request.getSession().setAttribute("serviceModeDetail", smd);
                                    path = "/workorder/workorder_regular_vendor.jsp";
                                }
                            } else if (wo.getTorderFk() == 1) {
                                List<Vendor> vendors = VendorServiceLocalServiceUtil.getVendorByRateAttribute(wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getExecutiondate(), smd.getTservicemodedetailPk(), wo.getTdeliverytermFk());
                                request.setAttribute("vendors", vendors);
                                List<Currency> currency = CurrencyLocalServiceUtil.getCurrency();
                                request.setAttribute("currency", currency);
                                path = "/workorder/workorder_regular_vendor_chg.jsp";//
                            }
                        } else {
                            if (wo != null && !wo.getAdhoc()) {
                                wo.setAdhoc(true);
                                wo.setTvendorserviceFk(null);
                            }
                            if (request.getParameter("adhocManifestBack") != null) {
                                try {
                                    if (request.getSession().getAttribute("fromRegular") == null) {
                                        request.getSession().setAttribute("fromRegular", Boolean.valueOf(request.getParameter("adhocManifestBack")));
                                    }
                                } catch (Exception e) {
                                }
                            }

                            List<Vendor> vendors = VendorLocalServiceUtil.getActiveVendor();
                            request.setAttribute("vendors", vendors);
                            List<Currency> currency = CurrencyLocalServiceUtil.getCurrency();
                            request.setAttribute("currency", currency);
                            path = "/workorder/workorder_adhoc_vendor.jsp";
                        }
                        log.info(ub.getUserId() + " " + wo.toString());
                    } else {
                        path = "/workorder/workorder.jsp";
                    }
                } else if (request.getParameter("regularNext") != null || request.getParameter("adhocNext") != null) {
                    log.info("Step 2");
                    try {
                        WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
                        if (wo != null) {
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                wo.setExecutiondate(format.parse(request.getParameter("actionDate")));
                            } catch (ParseException e) {
                                try {
                                    SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
                                    wo.setExecutiondate(format.parse(request.getParameter("actionDate")));
                                } catch (ParseException ex) {
                                    throw new Exception("Action date is empty!");
                                }
                            }
                            
                            try {
                                wo.setOrigintlocationFk(new Long(request.getParameter("origin")));
                                wo.setDestinationtlocationFk(new Long(request.getParameter("destination")));
                            } catch (NumberFormatException e) {
                                throw new Exception("Origin and Destination must be selected!");
                            }
                            try {
                                wo.setTorderFk(new Long(request.getParameter("orderType")));
                            } catch (NumberFormatException e) {
                                wo.setTorderFk(2L);
                            }

                            log.info(ub.getUserId() + " " + wo.toString());

                            if (request.getParameter("adhocNext") == null) { //regular                                
                                try {
                                    wo.setTservicemodeFk(new Long(request.getParameter("serviceMode")));
                                } catch (NumberFormatException e) {
                                    throw new Exception("Transportation Mode must be selected!");
                                }
                                ServiceModeDetail smd = null;
                                try {
                                    smd = ServiceModeDetailLocalServiceUtil.getServiceModeDetailByPrimaryKey(new Long(request.getParameter("containerMode")));
                                    if (wo.getWostatus() == null) {
                                        WorkOrderServiceModeDetail wsmd = new WorkOrderServiceModeDetail(1d, smd);
                                        wsmd.setSmdremarks("");
                                        List<WorkOrderServiceModeDetail> lwsmd = new ArrayList<>();
                                        lwsmd.add(wsmd);
                                        wo.setWorkOrderServiceModeDetail(lwsmd);
                                    } else if (WorkOrder.EDIT_STATUS.equals(wo.getWostatus()) || WorkOrder.REVISION_STATUS.equals(wo.getWostatus())) {
                                        wo.getWorkOrderServiceModeDetail().get(0).setServicemodeDetail(smd);
                                        wo.getWorkOrderServiceModeDetail().get(0).setTservicemodedetailFk(smd.getTservicemodedetailPk());
                                    }
                                } catch (NumberFormatException e) {
                                    throw new Exception("Container Mode must be selected!");
                                }
                                try {
                                    wo.setTdeliverytermFk(new Long(request.getParameter("deliveryTerm")));
                                } catch (NumberFormatException e) {
                                    throw new Exception("Delivery term not selected!");
                                }
                                if (wo.getTorderFk() != 1) {
                                    List<VendorService> vservices = VendorServiceLocalServiceUtil.getVendorServiceByAttributeWithTerm(wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getExecutiondate(), wo.getTorderFk(), wo.getTservicemodeFk(), smd.getTservicemodedetailPk(), wo.getTdeliverytermFk());
                                    if (vservices.isEmpty()) {
                                        wo.setAdhoc(true);
                                        wo.setTvendorserviceFk(null);
                                        request.getSession().setAttribute("fromRegular", true);
                                        path = "/workorder/workorder.do?adhocManifestBack=true";
                                    } else {
                                        Map<String, List> vendorGroup = new HashMap();
                                        for (VendorService o : vservices) {
                                            List lo = vendorGroup.get(o.getVendor().getVendorname());
                                            if (lo == null) {
                                                lo = new ArrayList();
                                            }
                                            lo.add(o);
                                            vendorGroup.put(o.getVendor().getVendorname(), lo);
                                        }
                                        
                                        request.setAttribute("vendors", vendorGroup);
                                        request.getSession().setAttribute("serviceModeDetail", smd);
                                        path = "/workorder/workorder_regular_vendor.jsp";
                                    }
                                }
                                if (wo.getTorderFk() == 1) {
                                    List<Vendor> vendors = VendorServiceLocalServiceUtil.getVendorByRateAttribute(wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getExecutiondate(), smd.getTservicemodedetailPk(), wo.getTdeliverytermFk());
                                    if (vendors.isEmpty()) {
                                        wo.setAdhoc(true);
                                        wo.setTvendorserviceFk(null);
                                        WorkOrderServiceModeDetail wsmd = new WorkOrderServiceModeDetail(1d, smd);
                                        wsmd.setSmdremarks("");
                                        List<WorkOrderServiceModeDetail> lwsmd = new ArrayList<>();
                                        lwsmd.add(wsmd);
                                        wo.setWorkOrderServiceModeDetail(lwsmd);

                                        request.getSession().setAttribute("fromRegular", true);
                                        path = "/workorder/workorder.do?adhocManifestBack=true";
                                    } else {
                                        request.setAttribute("vendors", vendors);
                                        JDBCConnector conVend = new JDBCConnector();
                                        for (int i = 0; i < vendors.size(); i++) {
                                            ResultMap rsVend = conVend.getSingleResult("SELECT CONCAT_WS(' ', 'Weight Min', FORMAT(weightfrom, 2, 'de_DE'),' KG', ',', 'Rate', '@',currencycode, FORMAT(rate, 2, 'de_DE'),'/KG') rates FROM tvendorservice tv LEFT JOIN tcurrency tc ON tv.tcurrency_fk = tc.tcurrency_pk WHERE tvendorservice_pk =" + vendors.get(i).getTvendorPk());
                                            if (rsVend != null) {
                                                vendors.get(i).setAddress2(rsVend.getString("rates"));
                                            }
                                        }

                                        request.getSession().setAttribute("serviceModeDetail", smd);
                                        List<Currency> currency = CurrencyLocalServiceUtil.getCurrency();
                                        request.setAttribute("currency", currency);
                                        path = "/workorder/workorder_regular_vendor_chg.jsp";
                                    }
                                }
                                log.info(ub.getUserId() + " " + wo.toString());
                            } else { //adhoc                                
                                List<Vendor> vendors = VendorLocalServiceUtil.getActiveVendor();
                                request.setAttribute("vendors", vendors);
                                List<Currency> currency = CurrencyLocalServiceUtil.getCurrency();
                                request.setAttribute("currency", currency);
                                path = "/workorder/workorder_adhoc_vendor.jsp";
                            }
                            request.getSession().setAttribute("wo", wo);
                            request.getSession().setAttribute("locationfrom", getLocation(wo.getOrigintlocationFk()));
                            request.getSession().setAttribute("locationdest", getLocation(wo.getDestinationtlocationFk()));
                        } else {
                            path = "/workorder/workorder.jsp";
                        }
                        log.info(ub.getUserId() + " " + wo.toString());
                    } catch (Exception e) {
                        request.setAttribute("errorMessage", e.getMessage());
                        path = "/workorder/workorder.do?regularvendorBack=true";
                    }
                } else if (request.getParameter("regularvendorNext") != null || request.getParameter("adhocvendorNext") != null) {
                    log.info("Step 3");
                    WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
                    if (wo != null) {
                        try {
                            if (request.getParameter("adhocvendorNext") == null) { //regular
                                log.info("Step 3 - Reguler");
                                wo.setAdhoc(false);
                                Double qty = null;
                                try {
                                    qty = new Double(request.getParameter("qty"));
                                    if (qty.compareTo(0D) <= 0) {
                                        throw new Exception();
                                    }
                                    request.getSession().setAttribute("smdQty", qty);
                                } catch (Exception e) {
                                    throw new Exception("Qty must be greater than 0!");
                                }
                                String reasonover = request.getParameter("reasonover");
                                String reasonread = request.getParameter("reasonread");
                                if (reasonread != null) {
                                    if (reasonread.equalsIgnoreCase("Y")) {
                                        request.getSession().setAttribute("reasonover", reasonover);
                                        request.getSession().setAttribute("reasonread", reasonread);
                                    } else {
                                        request.getSession().setAttribute("reasonread", reasonread);
                                    }
                                } else {
                                    request.getSession().setAttribute("reasonread", reasonread);
                                }
                                if (wo.getTorderFk() != 1) { //charter
                                    ServiceModeDetail smd = (ServiceModeDetail) request.getSession().getAttribute("serviceModeDetail");
                                    String vendorrate = request.getParameter("vendorrate");
                                    if ("---".equals(vendorrate)) {
                                        throw new Exception(" Please, select a Vendor ");
                                    }
                                    Long vrateId = new Long(vendorrate);
                                    VendorService vendorService = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKeyWithJoin(vrateId);
                                    wo.setTvendorserviceFk(vendorService.getTvendorservicePk());
                                    wo.setTvendorFk(vendorService.getTvendorFk());
                                    wo.setVendor(vendorService.getVendor());
                                    request.getSession().setAttribute("vendorServiceId", vrateId);

                                    Currency cur = CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(vendorService.getTcurrencyFk());
                                    wo.setTcurrencyFk(cur.getTcurrencyPk());
                                    wo.setCurrency(cur);

                                    WorkOrderServiceModeDetail wsmd = new WorkOrderServiceModeDetail(qty, smd);
                                    wsmd.setSmdcharge(vendorService.getRate());
                                    wsmd.setSmdremarks("Regular Rate for " + smd.getSmdname());
                                    wsmd.setCurrency(cur);
                                    wsmd.setSmdtcurrencyFk(cur.getTcurrencyPk());

                                    wo.setCharge(vendorService.getRate().multiply(new BigDecimal(wsmd.getQuantity())));

                                    List<WorkOrderServiceModeDetail> lwsmd = new ArrayList<>();
                                    lwsmd.add(wsmd);
                                    wo.setWorkOrderServiceModeDetail(lwsmd);

                                    wo.setAgreementno(vendorService.getRefagreementno());
                                    wo.setAgreementdate(vendorService.getRefagreementdate());
                                    log.info(ub.getUserId() + " " + wo.toString());
                                } else if (wo.getTorderFk() == 1) { //weight
                                    String vendorrate = request.getParameter("vendorrate");
                                    if ("---".equals(vendorrate)) {
                                        throw new Exception(" Please, select a Vendor ");
                                    }
                                    Long vrateId = new Long(vendorrate);
                                    VendorService vendorService = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKeyWithJoin(vrateId);
                                    wo.setTvendorserviceFk(vrateId);
                                    wo.setTvendorFk(vendorService.getTvendorFk());
                                    wo.setVendor(vendorService.getVendor());
                                    request.getSession().setAttribute("vendorServiceId", vrateId);

                                    String currency = request.getParameter("currency");
                                    if ("---".equals(currency)) {
                                        throw new Exception(" Please, select a Currency ");
                                    }
                                    Currency c = CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(new Long(currency));
                                    wo.setTcurrencyFk(c.getTcurrencyPk());
                                    wo.setCurrency(c);

                                    wo.getWorkOrderServiceModeDetail().get(0).setCurrency(c);
                                    wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(c.getTcurrencyPk());

                                    wo.setAgreementno(vendorService.getRefagreementno());
                                    wo.setAgreementdate(vendorService.getRefagreementdate());

                                    log.info(ub.getUserId() + " " + wo.toString());

                                    wo.setCharge(BigDecimal.ZERO);
                                    if (WorkOrder.EDIT_STATUS.equals(wo.getWostatus()) || WorkOrder.REVISION_STATUS.equals(wo.getWostatus())) {
                                        if (!wo.getAdhoc()) {
                                            VendorServiceExt vService = (VendorServiceExt) VendorServiceLocalServiceUtil.getVendorServiceByAttributeForChargeableWeight((Long) request.getSession().getAttribute("vendorServiceId"), wo.getTservicemodeFk(), wo.getExecutiondate(), wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getTotalweight(), wo.getTotalvolume(), wo.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
                                            String detCharge;
                                            if (vService != null) {
                                                if (vService.getIsFlatRate()) {
                                                    detCharge = String.format("@%s %,.2f minimum charge, %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                                                } else {
                                                    detCharge = String.format("@%s %,.2f/Kg x %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                                                }
                                                wo.setCharge(vService.getRate());
                                                wo.getWorkOrderServiceModeDetail().get(0).setQuantity(1d); //for weight regular, quantity is 1
                                                wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(vService.getRate());
                                                wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(vService.getTcurrencyFk());
                                                wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("Regular rate for " + vService.getServiceModeDetail().getSmdname() + detCharge);
                                                log.info(ub.getUserId() + " " + wo.toString());
                                            } else {
                                                log.info("Vendor Rate is not found");
                                            }
                                        }
                                    }
                                }
                            } else { //adhoc
                                log.info("Step 3 - Adhoc");
                                String vendor = request.getParameter("vendor");
                                if (vendor == null || vendor.isEmpty() || "---".equals(vendor)) {
                                    throw new Exception(" Please, select a Vendor ");
                                }
                                wo.setTvendorFk(new Long(vendor));
                                Vendor vendorModel = (Vendor) VendorLocalServiceUtil.getVendorByPrimaryKey(new Long(vendor));
                                if (vendorModel == null) {
                                    throw new Exception(" Vendor is not found ");
                                }
                                wo.setVendor(vendorModel);
                                boolean fromRegular = false;
                                try {
                                    if (wo.getWostatus() != null) {
                                        if (wo.getWostatus().equals(WorkOrder.EDIT_STATUS) || wo.getWostatus().equals(WorkOrder.REVISION_STATUS)) {
                                            fromRegular = true;
                                        }
                                    } else {
                                        fromRegular = (Boolean) request.getSession().getAttribute("fromRegular");
                                    }
                                } catch (Exception e) {
                                    log.error(e.getMessage(), e);
                                }
                                if (fromRegular) {
                                    if (request.getParameter("charge") != null) {
                                        try {
                                            wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(new BigDecimal(request.getParameter("charge")));
                                            wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("");
                                            wo.setCharge(new BigDecimal(request.getParameter("charge")));
                                        } catch (Exception e) {
                                            throw new Exception(" Please, fill in a Service Charge ");
                                        }
                                    }
                                    if (request.getParameter("currency") != null) {
                                        try {
                                            Currency c = CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(new Long(request.getParameter("currency")));
                                            wo.setTcurrencyFk(c.getTcurrencyPk());
                                            wo.setCurrency(c);
                                            wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(c.getTcurrencyPk());
                                            wo.getWorkOrderServiceModeDetail().get(0).setCurrency(c);
                                        } catch (NumberFormatException e) {
                                            throw new Exception(" Please, select a Currency ");
                                        }
                                    }
                                }
                                if (wo.getWorkOrderServiceModeDetail() != null) {
                                    for (WorkOrderServiceModeDetail wsmd : wo.getWorkOrderServiceModeDetail().toArray(new WorkOrderServiceModeDetail[wo.getWorkOrderServiceModeDetail().size()])) {
                                        if (wsmd.getSmdcharge() == null) {
                                            throw new Exception(" Please, fill in Service charges ");
                                        }
                                    }
                                }
                                if (wo.getWorkOrderServiceModeDetail().size() == 1 && !fromRegular) {
                                    throw new Exception(" Cannot proceed with single service! Use create regular work order!");
                                } else if (wo.getWorkOrderServiceModeDetail().size() < 1) {
                                    throw new Exception(" Please, select container mode first ");
                                }
                                log.info(ub.getUserId() + " " + wo.toString());
                            }

                            String deptId = getRateByDept(ub.getEmployeeId());
                            String retApv = "";
                            try {
                                if (wo.getCharge().compareTo(BigDecimal.ZERO) == 1) {
                                    retApv = getLevelApproval(getRateByDept(ub.getEmployeeId()), wo.getCharge(), wo.getCurrency(), request);
                                } else {
                                    retApv = getLevelApproval(deptId, wo.getWorkOrderServiceModeDetail(), request);
                                }
                            } catch (Exception e) {
                                throw new Exception(e);
                            }
                            wo.setMaxlevel(Integer.valueOf(retApv));
                            request.getSession().setAttribute("levelapproval", retApv);

                            List<Level> levels = WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
                            request.setAttribute("levels", levels);
                            Iterator<WorkOrderManifest> it = wo.getWorkOrderManifest().iterator();

                            List<ManifestWeightVolumeDetail> man = null;
                            try {
                                if (wo.getWostatus().equals(WorkOrder.EDIT_STATUS) || wo.getWostatus().equals(WorkOrder.REVISION_STATUS)) {
                                    man = new ArrayList<>();
                                    while (it.hasNext()) {
                                        Long manNo = it.next().getManifestNo();
                                        ManifestWeightVolumeDetail manifest = ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(manNo);
                                        if (manifest == null) {
                                            manifest = ManifestDALocalServiceUtil.getPickupDeliveryWeightVolumeDetail(manNo);
                                            if (manifest == null) {
                                                throw new Exception("Manifest number is not found");
                                            }
                                        }
                                        man.add(manifest);
                                    }
                                }
                            } catch (NullPointerException e) {
                                if (request.getSession().getAttribute("manifests") != null) {
                                    man = (List<ManifestWeightVolumeDetail>) request.getSession().getAttribute("manifests");
                                }
                            }

                            request.getSession().setAttribute("manifests", man);
                            path = "/workorder/workorder_manifest.jsp";
                            log.info(ub.getUserId() + " " + wo.toString());
                            log.info(ub.getUserId() + " " + wo.getCurrency().toString());
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                            request.setAttribute("errorMessage", e.getMessage().substring(e.getMessage().indexOf(":") + 2, e.getMessage().length()));
                            path = "/workorder/workorder.do?" + (wo.getAdhoc() ? "adhoc" : "regular") + "ManifestBack=" + (request.getSession().getAttribute("fromRegular") == null ? "false" : request.getSession().getAttribute("fromRegular"));
                        }
                    } else {
                        path = "/workorder/workorder.jsp";
                    }
                } else if (request.getParameter("manRemove") != null) {
                    log.info("Remove Manifest");
                    WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
                    List<ManifestWeightVolumeDetail> manifests = (List<ManifestWeightVolumeDetail>) request.getSession().getAttribute("manifests");
                    if (manifests != null) {
                        for (int i = 0; i < manifests.size(); i++) {
                            if (manifests.get(i).getManifest_no().equals(new Long(request.getParameter("manRemove")))) {
                                BigDecimal totw, totvol;
                                totw = wo.getTotalweight();
                                totvol = wo.getTotalvolume();
                                totw = totw == null ? BigDecimal.ZERO : totw.subtract(manifests.get(i).getWeight());
                                totvol = totvol == null ? BigDecimal.ZERO : totvol.subtract(manifests.get(i).getVolume());
                                wo.setTotalweight(totw);
                                wo.setTotalvolume(totvol);
                                manifests.remove(i);
                                break;
                            }
                        }
                    }
                    if (wo.getTorderFk() == 1 && !wo.getAdhoc() && wo.getTotalweight().compareTo(BigDecimal.ZERO) > 1 && wo.getTotalvolume().compareTo(BigDecimal.ZERO) > 1) {
                        VendorServiceExt vService = null;
                        try {
                            vService = (VendorServiceExt) VendorServiceLocalServiceUtil.getVendorServiceByAttributeForChargeableWeight((Long) request.getSession().getAttribute("vendorServiceId"), wo.getTservicemodeFk(), wo.getExecutiondate(), wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getTotalweight(), wo.getTotalvolume(), wo.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
                            if (vService == null) {
                                throw new Exception();
                            }
                            String detCharge;
                            if (vService.getIsFlatRate()) {
                                detCharge = String.format("@%s %,.2f minimum charge, %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                            } else {
                                detCharge = String.format("@%s %,.2f/Kg x %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                            }
                            
                            wo.setCharge(vService.getRate());
                            wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(vService.getRate());
                            wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(vService.getTcurrencyFk());
                            wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("Regular rate for " + vService.getServiceModeDetail().getSmdname() + detCharge);

                            String deptId = getRateByDept(ub.getEmployeeId());
                            String retApv = "";
                            try {
                                if (wo.getCharge().compareTo(BigDecimal.ZERO) == 1) {
                                    retApv = getLevelApproval(getRateByDept(ub.getEmployeeId()), wo.getCharge(), wo.getCurrency(), request);
                                } else {
                                    retApv = getLevelApproval(deptId, wo.getWorkOrderServiceModeDetail(), request);
                                }
                            } catch (Exception e) {
                                throw new Exception(e);
                            }
                            request.getSession().setAttribute("levelapproval", retApv);
                        } catch (Exception e) {
                            request.setAttribute("errorMessage", "Chargeable Weight is out of range/rate not available, <a href='#' onclick=\"location.href='workorder.do?adhocManifestBack=true'\">click here</a> to convert into adhoc Work Order!");
                        }
                        try {
                            wo.setAgreementno(vService.getRefagreementno());
                        } catch (Exception e) {
                        }
                        try {
                            wo.setAgreementdate(vService.getRefagreementdate());
                        } catch (Exception e) {
                        }
                    }
                    request.getSession().setAttribute("manifests", manifests);
                    List<Level> levels = WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
                    request.setAttribute("levels", levels);
                    path = "/workorder/workorder_manifest.jsp";
                    log.info(ub.getUserId() + " " + wo.toString());
                } else if (request.getParameter("manAdd") != null) { //manifest-add button was clicked at step 4
                    /*
                    1. Get WO from session
                    2. Get List of Manifests from session
                    3. Get Manifest Number from request parameter
                    4. Get Manifest Data from table fast.t_manifest
                    5. If Not available, get Manifest Data from fast.t_pickup_delivery
                    6. If Not available, throw error "Manifest number is not found"
                    7. Check If WO can be created with this Manifest
                    8. If Can Not, throw error "WO is not required for this Manifest"
                    9. Check IF Manifest is already used in WO
                    10. IF Already Used, throw error "Manifest already exist!"
                    11. Check If Manifest Cut-Off
                    12. IF Cut-Off, throw error "WO Cannot be created due to cut-off data since 24 May 2017"
                     */
                    log.info("Add Manifest");
                    WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
                    if (wo != null) {
                        log.info(ub.getUserId() + " " + wo.toString());
                        String manNo = request.getParameter("manifest");
                        if (manNo != null && !manNo.isEmpty()) {
                            manNo = manNo.trim();
                        }
                        try {
                            ManifestWeightVolumeDetail manifest = ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(new Long(manNo));
                            if (manifest == null) {
                                manifest = ManifestDALocalServiceUtil.getPickupDeliveryWeightVolumeDetail(new Long(manNo));
                                if (manifest == null) {
                                    throw new Exception("Manifest number is not found");
                                }
                            }
                            log.info(ub.getUserId() + " " + manifest.toString());
                            switch (manifest.getCarrier_ownership_id()) {
                                case "COF":
                                case "CPND":
                                case "CDSLA":
                                    if (manifest.getModa().equals("ROAD")) {
                                        List<Vendor> vendors = VendorLocalServiceUtil.getVendorByVendorCodeAndIsAfterEventEqualsOne(manifest.getVendor_id());
                                        if (vendors == null || vendors.isEmpty()) {
                                            throw new Exception("WO is not required for this Manifest");
                                        }
                                    }
                            }
                            if (wo.getVendor().getVendorcode() != null) {
                                log.debug("vendor = " + wo.getVendor().getVendorcode());
                            } else {
                                log.debug("vendor = " + wo.getTvendorFk());
                            }
                            log.debug("vendor = " + manifest.getVendor_id());
                            if (!manifest.getVendor_id().equals(wo.getVendor().getVendorcode())) {
                                throw new Exception("Vendor in manifest is not match with the selected one");
                            } else {
                                log.debug("Vendor is match");
                            }
                            boolean isManifestAlreadyUsed = ManifestDALocalServiceUtil.isManifestUsedInWO(manifest.getManifest_no());
                            if (isManifestAlreadyUsed) {
                                throw new Exception("Manifest already exist!");
                            } else {
                                log.debug("Manifest is not used before");
                            }
                            List<ManifestWeightVolumeDetail> manifests = (List<ManifestWeightVolumeDetail>) request.getSession().getAttribute("manifests");
                            if (manifests != null) {
                                for (ManifestWeightVolumeDetail m : manifests) {
                                    if (m.equals(manifest)) {
                                        throw new Exception("Manifest already exist!");
                                    }
                                }
                            } else {
                                manifests = new ArrayList<>();
                            }
                            
                            JDBCConnector conman = new JDBCConnector();
                            ResultMap rsManExist = conman.getSingleResult("SELECT * FROM db_workorder.`tworkorder` tw left join  db_workorder.`tworkorder_manifest` tm "
                                    + "on tw.`tworkorder_pk` = tm.`tworkorder_fk` where tm.`manifest_no` = '" + manifest.getManifest_no() + "' and "
                                    + "tw.`wostatus` not in ('CANCELLED','CANCELLED_AP') ");
                            if (rsManExist != null) {
                                throw new Exception("Manifest already exist!");
                            }
                            JDBCConnector conbaru = new JDBCConnector("fast");
                            String ManFilterQuery = "SELECT * FROM fast.t_manifest WHERE manifest_no = '" + manifest.getManifest_no() + "' ";
                            if (!manifest.getManifest_no().toString().substring(0, 4).equalsIgnoreCase("9500")) {
                                ManFilterQuery += " AND  datetime_created >= '2017-05-24 00:00:00' ";
                                ManFilterQuery += "AND ship_date >= '2017-05-24 00:00:00' AND actual_ship_date >= '2017-05-24 00:00:00' AND eta >= '2017-05-24 00:00:00'";
                            }
                            ResultMap rs = conbaru.getSingleResult(ManFilterQuery);
                            if (rs == null) {
                                ManFilterQuery = "SELECT * FROM fast.t_pickup_delivery WHERE pickup_delivery_no = '" + manifest.getManifest_no() + "' ";
                                if (!manifest.getManifest_no().toString().substring(0, 4).equalsIgnoreCase("9500")) {
                                    ManFilterQuery += "AND etd >= '2017-05-24 00:00:00' AND pickup_delivery_date >= '2017-05-24 00:00:00' AND eta >= '2017-05-24 00:00:00'";
                                }
                                rs = conbaru.getSingleResult(ManFilterQuery);
                                if (rs == null) {
                                    throw new Exception("WO Cannot be created due to cut-off data since 24 May 2017");
                                }
                            } else {
                                log.debug("Manifest is after 2017-05-24");
                            }
                            BigDecimal totw, totvol;
                            totw = wo.getTotalweight();
                            totvol = wo.getTotalvolume();
                            totw = totw == null ? manifest.getWeight() : totw.add(manifest.getWeight());
                            totvol = totvol == null ? manifest.getVolume() : totvol.add(manifest.getVolume());
                            wo.setTotalweight(totw);
                            wo.setTotalvolume(totvol);
                            manifests.add(manifest);
                            log.info(ub.getUserId() + " " + wo.toString());
                            if (wo.getTorderFk() == 1 && !wo.getAdhoc()) {
                                VendorServiceExt vService = null;
                                try {
                                    vService = (VendorServiceExt) VendorServiceLocalServiceUtil.getVendorServiceByAttributeForChargeableWeight((Long) request.getSession().getAttribute("vendorServiceId"), wo.getTservicemodeFk(), wo.getExecutiondate(), wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getTotalweight(), wo.getTotalvolume(), wo.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
                                    if (vService == null) {
                                        throw new Exception();
                                    }
                                    String detCharge;
                                    if (vService.getIsFlatRate()) {
                                        detCharge = String.format("@%s %,.2f minimum charge, %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                                    } else {
                                        detCharge = String.format("@%s %,.2f/Kg x %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                                    }

                                    wo.setCharge(vService.getRate());
                                    wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(vService.getRate());
                                    wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(vService.getTcurrencyFk());
                                    wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("Regular rate for " + vService.getServiceModeDetail().getSmdname() + detCharge);
                                    log.info(ub.getUserId() + " " + wo.getWorkOrderServiceModeDetail().get(0).toString());
                                    String deptId = getRateByDept(ub.getEmployeeId());
                                    String retApv = "";
                                    try {
                                        if (wo.getCharge().compareTo(BigDecimal.ZERO) == 1) {
                                            retApv = getLevelApproval(getRateByDept(ub.getEmployeeId()), wo.getCharge(), wo.getCurrency(), request);
                                        } else {
                                            retApv = getLevelApproval(deptId, wo.getWorkOrderServiceModeDetail(), request);
                                        }
                                    } catch (Exception e) {
                                        throw new Exception(e);
                                    }
                                    wo.setMaxlevel(Integer.valueOf(retApv));
                                    request.getSession().setAttribute("levelapproval", retApv);
                                } catch (Exception e) {
                                    log.error(e.getMessage(), e);
                                    request.setAttribute("errorMessage", "Chargeable Weight is out of range/rate not available, <a href='#' onclick=\"location.href='workorder.do?adhocManifestBack=true'\">click here</a> to convert into adhoc Work Order!");
                                }
                                wo.setAgreementno(vService.getRefagreementno());
                                wo.setAgreementdate(vService.getRefagreementdate());
                            }
                            request.getSession().setAttribute("manifests", manifests);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                            request.setAttribute("errorMessage", e.getMessage());
                        }
                        List<Level> levels = WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
                        request.setAttribute("levels", levels);
                        path = "/workorder/workorder_manifest.jsp";
                        log.info(ub.getUserId() + " " + wo.toString());
                    }
                } else if (request.getParameter("regularSave") != null || request.getParameter("adhocSave") != null) {
                    WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
                    VendorServiceExt vService = null;
                    String remarks = (String) request.getSession().getAttribute("reasonover");
                    String reasonread = (String) request.getSession().getAttribute("reasonread");
                    String override = (String) request.getParameter("override");

                    if (wo != null) {
                        if (override != null) {
                            if (Float.parseFloat(override) > 0.0) {
                                vService = (VendorServiceExt) VendorServiceLocalServiceUtil.getVendorServiceByAttributeForChargeableWeight((Long) request.getSession().getAttribute("vendorServiceId"), wo.getTservicemodeFk(), wo.getExecutiondate(), wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), new BigDecimal(override), wo.getTotalvolume(), wo.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
                                if (vService == null) {
                                    throw new Exception();
                                }
                                String detCharge;
                                if (vService.getIsFlatRate()) {
                                    detCharge = String.format("@%s %,.2f minimum charge, %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                                } else {
                                    detCharge = String.format("@%s %,.2f/Kg x %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                                }

                                wo.setCharge(vService.getRate());
                                wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(vService.getRate());
                                wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(vService.getTcurrencyFk());
                                wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("Regular rate for " + vService.getServiceModeDetail().getSmdname() + detCharge);
                            }
                        }

                        try {
                            boolean isProcrument = false;
                            try {
                                ub = (UserBeans) request.getSession().getAttribute("loginInfo");
                                isProcrument = UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId());
                            } catch (Exception e) {
                            }

                            List<ManifestWeightVolumeDetail> manifests = (List<ManifestWeightVolumeDetail>) request.getSession().getAttribute("manifests");
                            if (manifests == null || manifests.isEmpty()) {
                                throw new Exception("At least 1 Manifest is required!");
                            }

                            List<WorkOrderManifest> workOrderManifests = new ArrayList<>();
                            for (ManifestWeightVolumeDetail manifestWeightVolumeDetail : manifests) {
                                WorkOrderManifest workOrderManifest = new WorkOrderManifest();
                                workOrderManifest.setManifestNo(manifestWeightVolumeDetail.getManifest_no());
                                workOrderManifests.add(workOrderManifest);
                            }
                            wo.setWorkOrderManifest(workOrderManifests);
                            /*if ((manifests != null && manifests.size() > 0) || isProcrument) {
                                Iterator<ManifestWeightVolumeDetail> it = manifests.iterator();
                                List<WorkOrderManifest> woms = new ArrayList<>();
                                while (it.hasNext()) {
                                    ManifestWeightVolumeDetail manifest = it.next();
                                    WorkOrderManifest wom = new WorkOrderManifest();
                                    wom.setManifestNo(manifest.getManifest_no());
                                    woms.add(wom);
                                }
                                wo.setWorkOrderManifest(woms);
                            } else {
                                throw new Exception("At least 1 Manifest is required!");
                            }*/
                            if (!wo.getAdhoc() && wo.getCharge().compareTo(BigDecimal.ONE) < 1 && wo.getTorderFk() == 1) {
                                throw new Exception("Charge is Zero");
                            }
                            try {
                                wo.setMaxlevel(new Integer(request.getParameter("lastAppLevelHide")));
                            } catch (NumberFormatException e) {
                                throw new Exception("Last Approval level not selected!");
                            }
                            try {
                                wo.setDeliverynote(request.getParameter("deliveryNote"));
                                wo.setGooddescription(request.getParameter("goodsDesc"));
                            } catch (Exception e) {
                            }
                            try {
                                if (wo.getWostatus() != null) {
                                    if (wo.getWostatus().equals(WorkOrder.EDIT_STATUS) || wo.getWostatus().equals(WorkOrder.REVISION_STATUS)) {
                                        wo.setRevisionreason(request.getParameter("revisionreason"));
                                        try {
                                            wo.setRevisionreason(wo.getRevisionreason().isEmpty() ? null : wo.getRevisionreason());
                                            if (wo.getWostatus().equals(WorkOrder.REVISION_STATUS)) {
                                                Notes note = new Notes();
                                                note.setNoteType("REVISION");
                                                note.setEmployeeId(ub.getEmployeeId());
                                                note.setNotes(wo.getRevisionreason());
                                                note.setTworkorderFk(wo.getTworkorderPk());
                                            }
                                        } catch (Exception e) {
                                        }
                                        String extReason = request.getParameter("revisionreasoneksternal");
                                        extReason = extReason != null && extReason.isEmpty() ? null : extReason;
                                        wo.setRevisionreasoneksternal(extReason);
                                        log.info(ub.getUserId() + " " + wo.toString());
                                        WorkOrderLocalServiceUtil.updateWorkOrderWithChildren(wo, ub.getEmployeeId());
                                        if (reasonread != null) {
                                            if (reasonread.equalsIgnoreCase("Y")) {
                                                JDBCConnector con = new JDBCConnector();
                                                int ret = con.executeNonQuery("UPDATE `tt_workorder_remarks` SET remarks = '" + remarks + "' WHERE tworkorderid_fk = '" + wo.getTworkorderPk() + "'");
                                            }
                                        }
                                    }
                                    request.setAttribute("errorMessage", "Work Order Saved!");
                                } else {
                                    try {
                                        if (reasonread != null && !wo.getAdhoc()) { //regular
                                            if (reasonread.equalsIgnoreCase("N")) {
                                                wo.setWostatus("APPROVED");
                                                wo.setMaxlevel(1);
                                                wo.setNexttlevel(1);
                                            }
                                            log.info(ub.getUserId() + " " + wo.toString());
                                            log.info(ub.getUserId() + " " + wo.getWorkOrderServiceModeDetail().toString());
                                            long woKey = WorkOrderLocalServiceUtil.insertWorkOrder(wo, ub.getEmployeeId());
                                            if (reasonread.equalsIgnoreCase("Y")) {
                                                JDBCConnector con = new JDBCConnector();
                                                int ret = con.executeNonQuery("INSERT INTO `tt_workorder_remarks` (tworkorderid_fk, remarks) VALUES('" + woKey + "', '" + remarks + "')");
                                            }
                                            request.setAttribute("errorMessage", "Work Order Saved!");
                                        } else {
                                            log.info(ub.getUserId() + " " + wo.toString());
                                            log.info(ub.getUserId() + " " + wo.getWorkOrderServiceModeDetail().toString());
                                            long woKey = WorkOrderLocalServiceUtil.insertWorkOrder(wo, ub.getEmployeeId());
                                        }
                                    } catch (Exception e) {
                                        log.error(e.getMessage(), e);
                                        request.setAttribute("errorMessage", e.getMessage());
                                    }
                                }
                            } catch (SQLException e) {
                                log.error(e.getMessage(), e);
                                throw new Exception("Saving Workorder failed! Please try again in a few momment.");
                            }
                            if (wo.getAdhoc()) {
                                path = "/workorder/workorder_msg.jsp";
                            }
                        } catch (Exception e) {
                            request.setAttribute("errorMessage", e.getMessage());
                            List<Level> levels = WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
                            request.setAttribute("levels", levels);
                            path = "/workorder/workorder_manifest.jsp";
                        }
                    }
                } else if (request.getParameter("womsgnext") != null) {
                    try {
                        WorkOrderLocalServiceUtil.sendCustomCostValidationMail(WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKey(new Long(request.getParameter("wo"))), request.getParameter("msg"));
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "Message failed to send!");
                    }
                }
                RequestDispatcher rd = this.getServletContext().getRequestDispatcher(path);
                rd.include(request, response);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } finally {
            out.close();
        }
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
         //classic iterator example
         for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
         Map.Entry<String, Integer> entry = it.next();
         sortedMap.put(entry.getKey(), entry.getValue());
         }*/
        return sortedMap;
    }

    private String getLocation(Long locId) {
        JDBCConnector con = new JDBCConnector();
        String ret = "";
        try {
            ResultMap rs = con.getSingleResult("SELECT locationname FROM tlocation WHERE tlocation_pk = '" + locId + "'");
            if (rs != null) {
                ret = rs.getString("locationname");
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return ret;
    }

    private String getLevelApproval(String type, BigDecimal amountwo, Currency currency, HttpServletRequest request) throws Exception {
        JDBCConnector con = new JDBCConnector();
        String ret = "";
        BigDecimal amount = amountwo;
        if (!"IDR".equals(currency.getCurrencycode())) {
            try {
                amount = convertToIDR(amountwo, currency);
            } catch (Exception e) {
                throw new Exception(e);
            }
            request.getSession().setAttribute("equivalentIDR", amount);
        }
        try {
            List<ResultMap> ls = con.getQuery("SELECT level_id, conditional_level, amount FROM `tr_rate_approval` WHERE type_rate = '" + type + "' ORDER BY amount");
            if (ls != null) {
                Iterator<ResultMap> itr = ls.iterator();
                while (itr.hasNext()) {
                    ResultMap rs = itr.next();
                    String cond = rs.getString("conditional_level");
                    BigDecimal amountc = new BigDecimal(rs.getString("amount"));
                    if (cond.equalsIgnoreCase("<")) {
                        if (Double.parseDouble(amount.toString()) < rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    } else if (cond.equalsIgnoreCase("<=")) {
                        if (Double.parseDouble(amount.toString()) <= rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    } else if (cond.equalsIgnoreCase(">")) {
                        if (Double.parseDouble(amount.toString()) > rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    } else if (cond.equalsIgnoreCase(">=")) {
                        if (Double.parseDouble(amount.toString()) >= rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    }
//                    if (cond.equalsIgnoreCase("<")) {
//                        if (amount < rs.getDouble("amount")) {
//                            ret = getLevel(rs.getString("level_id"));
//                            break;
//                        }
//                    } else if (cond.equalsIgnoreCase("<=")) {
//                        if (amount <= rs.getDouble("amount")) {
//                            ret = getLevel(rs.getString("level_id"));
//                            break;
//                        }
//                    } else if (cond.equalsIgnoreCase(">")) {
//                        if (amount <= rs.getDouble("amount")) {
//                            ret = getLevel(rs.getString("level_id"));
//                            break;
//                        }
//                    } else if (cond.equalsIgnoreCase(">=")) {
//                        if (amount <= rs.getDouble("amount")) {
//                            ret = getLevel(rs.getString("level_id"));
//                            break;
//                        }
//                    }
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }

        return ret;
    }

    public String getLevelApproval(String type, List<WorkOrderServiceModeDetail> wol, HttpServletRequest request) throws Exception {
        JDBCConnector con = new JDBCConnector();
        BigDecimal totalChargeIDR = BigDecimal.ZERO;
        try {
            for (WorkOrderServiceModeDetail wosmd : wol) {
                if (!wosmd.isDeleted()) {
                    if (wosmd.getCurrency() == null) {
                        throw new Exception("Currency must be set on service " + wosmd.getSmddetailname());
                    }
                    BigDecimal chargeEachService = BigDecimal.ZERO;
                    if (wosmd.getSmdcharge() != null) {
                        chargeEachService = wosmd.getSmdcharge().multiply(new BigDecimal(wosmd.getQuantity()));
                    }
                    totalChargeIDR = totalChargeIDR.add(convertToIDR(chargeEachService, wosmd.getCurrency()));
                }
            }
            //if (wol.get(0).getCurrency() != null) {
            //    amount = convertToIDR(new BigDecimal(getChargesInternal(wol)), wol.get(0).getCurrency()).toString();
            //}
        } catch (Exception e) {
            throw new Exception(e);
        }
        String amount = totalChargeIDR.toString();
        request.getSession().setAttribute("equivalentIDR", amount);
        String ret = "";
        try {
            List<ResultMap> ls = con.getQuery("SELECT level_id, conditional_level, amount FROM `tr_rate_approval` WHERE type_rate = '" + type + "' ORDER BY amount");
            if (ls != null) {
                Iterator<ResultMap> itr = ls.iterator();
                while (itr.hasNext()) {
                    ResultMap rs = itr.next();
                    String cond = rs.getString("conditional_level");
                    BigDecimal amountc = new BigDecimal(rs.getString("amount"));
                    if (cond.equalsIgnoreCase("<")) {
                        if (Double.parseDouble(amount) < rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    } else if (cond.equalsIgnoreCase("<=")) {
                        if (Double.parseDouble(amount) <= rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    } else if (cond.equalsIgnoreCase(">")) {
                        if (Double.parseDouble(amount) > rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    } else if (cond.equalsIgnoreCase(">=")) {
                        if (Double.parseDouble(amount) >= rs.getDouble("amount")) {
                            ret = getLevel(rs.getString("level_id"));
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return ret;
    }

    private String getLevel(String level_id) {
        String ret = "";
        JDBCConnector con = new JDBCConnector();
        try {
            ResultMap rs = con.getSingleResult("SELECT level_value FROM tlevel WHERE  level_id = '" + level_id + "'");
            if (rs != null) {
                ret = rs.getString("level_value");
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }

        return ret;
    }

    private String getDepartment(String employee_id) {
        JDBCConnector con = new JDBCConnector("fast");
        String ret = "";
        try {
            ResultMap rs = con.getSingleResult("SELECT department_id FROM employee.t_personel WHERE employee_id = '" + employee_id + "'");
            if (rs != null) {
                ret = rs.getString("department_id");
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return ret;
    }
    
    public String getRateByDept(String employee_id) {
        JDBCConnector con = new JDBCConnector();
        String ret = "";
        try {
            ResultMap rs = con.getSingleResult("SELECT dept_id FROM db_workorder.`t_project_dept` WHERE dept_id='" + getDepartment(employee_id) + "'");
            if (rs != null) {
                ret = "P";
            } else {
                ret = "R";
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return ret;
    }

    private BigDecimal convertToIDR(BigDecimal amount, Currency currency) throws Exception {
        Map<String, Object> p = new HashMap<>();
        p.put("date", new Date());
        p.put("currencyCode", currency.getCurrencycode());
        CurrencyRate currencyRate = CurrencyRateLocalServiceUtil.getCurrencyRatesByDate(p);
        if (currencyRate == null) {
            throw new Exception("Currency rate is not found");
        }
        BigDecimal amountIDR = amount.multiply(currencyRate.getRates());
        return amountIDR;
    }

    public String handleManifestInput(HttpServletRequest request, HttpServletResponse response, String manifestNo, UserBeans ub, String path) {
        /*
                    1. Get WO from session
                    2. Get List of Manifests from session
                    3. Get Manifest Number from request parameter
                    4. Get Manifest Data from table fast.t_manifest
                    5. If Not available, get Manifest Data from fast.t_pickup_delivery
                    6. If Not available, throw error "Manifest number is not found"
                    7. Check If WO can be created with this Manifest
                    8. If Can Not, throw error "WO is not required for this Manifest"
                    9. Check IF Manifest is already used in WO
                    10. IF Already Used, throw error "Manifest already exist!"
                    11. Check If Manifest Cut-Off
                    12. IF Cut-Off, throw error "WO Cannot be created due to cut-off data since 24 May 2017"
         */
        String errorMessage = "";
        WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
        if (wo != null) {
            List<ManifestWeightVolumeDetail> manifests = (List<ManifestWeightVolumeDetail>) request.getSession().getAttribute("manifests");
            if (manifests == null) {
                manifests = new ArrayList<>();
            }
            String manNo = manifestNo;
            if (manNo != null && !manNo.isEmpty()) {
                manNo = manNo.trim();
            }
            try {
                ManifestWeightVolumeDetail manifest = ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(new Long(manNo));
                if (manifest == null) {
                    manifest = ManifestDALocalServiceUtil.getPickupDeliveryWeightVolumeDetail(new Long(manNo));
                    if (manifest == null) {
                        throw new Exception("Manifest number is not found");
                    }
                }
                log.info(ub.getUserId() + " " + manifest.toString());
                switch (manifest.getCarrier_ownership_id()) {
                    case "COF":
                    case "CPND":
                    case "CDSLA":
                        if (manifest.getModa().equals("ROAD")) {
                            List<Vendor> vendors = VendorLocalServiceUtil.getVendorByVendorCodeAndIsAfterEventEqualsOne(manifest.getVendor_id());
                            if (vendors == null || vendors.isEmpty()) {
                                throw new Exception("WO is not required for Manifest No " + manifest.getManifest_no());
                            }
                        }
                }
                if (wo.getVendor().getVendorcode() != null) {
                    log.info("vendor = " + wo.getVendor().getVendorcode());
                } else {
                    log.info("vendor = " + wo.getTvendorFk());
                }
                log.info("vendor = " + manifest.getVendor_id());
                if (!manifest.getVendor_id().equals(wo.getVendor().getVendorcode())) {
                    throw new Exception("Vendor in manifest is not match with the selected one");
                }
                                
                boolean isManifestAlreadyUsed = ManifestDALocalServiceUtil.isManifestUsedInWO(manifest.getManifest_no());
                if (isManifestAlreadyUsed) {
                    throw new Exception("Manifest already exist!");
                }
                for (ManifestWeightVolumeDetail m : manifests) {
                    if (m.equals(manifest)) {
                        throw new Exception("Manifest already exist!");
                    }
                }
                JDBCConnector conman = new JDBCConnector();
                ResultMap rsManExist = conman.getSingleResult("SELECT * FROM db_workorder.`tworkorder` tw left join  db_workorder.`tworkorder_manifest` tm "
                        + "on tw.`tworkorder_pk` = tm.`tworkorder_fk` where tm.`manifest_no` = '" + manifest.getManifest_no() + "' and "
                        + "tw.`wostatus` not in ('CANCELLED','CANCELLED_AP') ");
                if (rsManExist != null) {
                    throw new Exception("Manifest already exist!");
                }
                JDBCConnector conbaru = new JDBCConnector("fast");
                String ManFilterQuery = "SELECT * FROM fast.t_manifest WHERE manifest_no = '" + manifest.getManifest_no() + "' ";
                if (!manifest.getManifest_no().toString().substring(0, 4).equalsIgnoreCase("9500")) {
                    ManFilterQuery += " AND  datetime_created >= '2017-05-24 00:00:00' ";
                    ManFilterQuery += "AND ship_date >= '2017-05-24 00:00:00' AND actual_ship_date >= '2017-05-24 00:00:00' AND eta >= '2017-05-24 00:00:00'";
                }
                log.info(ManFilterQuery);
                ResultMap rs = conbaru.getSingleResult(ManFilterQuery);
                if (rs == null) {
                    ManFilterQuery = "SELECT * FROM fast.t_pickup_delivery WHERE pickup_delivery_no = '" + manifest.getManifest_no() + "' ";
                    if (!manifest.getManifest_no().toString().substring(0, 4).equalsIgnoreCase("9500")) {
                        ManFilterQuery += "AND etd >= '2017-05-24 00:00:00' AND pickup_delivery_date >= '2017-05-24 00:00:00' AND eta >= '2017-05-24 00:00:00'";
                    }
                    log.info(ManFilterQuery);
                    rs = conbaru.getSingleResult(ManFilterQuery);
                    if (rs == null) {
                        throw new Exception("WO Cannot be created due to cut-off data since 24 May 2017");
                    }
                } else {
                    log.info(rs.get("manifest_no"));
                }
                BigDecimal totw, totvol;
                totw = wo.getTotalweight();
                totvol = wo.getTotalvolume();
                totw = totw == null ? manifest.getWeight() : totw.add(manifest.getWeight());
                totvol = totvol == null ? manifest.getVolume() : totvol.add(manifest.getVolume());
                wo.setTotalweight(totw);
                wo.setTotalvolume(totvol);
                manifests.add(manifest);
                log.info(ub.getUserId() + " " + wo.toString());
                if (wo.getTorderFk() == 1 && !wo.getAdhoc()) {
                    VendorServiceExt vService = null;
                    try {
                        vService = (VendorServiceExt) VendorServiceLocalServiceUtil.getVendorServiceByAttributeForChargeableWeight((Long) request.getSession().getAttribute("vendorServiceId"), wo.getTservicemodeFk(), wo.getExecutiondate(), wo.getOrigintlocationFk(), wo.getDestinationtlocationFk(), wo.getTotalweight(), wo.getTotalvolume(), wo.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
                        if (vService == null) {
                            throw new Exception();
                        }
                        String detCharge;
                        if (vService.getIsFlatRate()) {
                            detCharge = String.format("@%s %,.2f minimum charge, %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                        } else {
                            detCharge = String.format("@%s %,.2f/Kg x %,fKg", vService.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vService.getTvendorservicePk()).getRate(), vService.getWeight());
                        }

                        wo.setCharge(vService.getRate());
                        wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(vService.getRate());
                        wo.getWorkOrderServiceModeDetail().get(0).setSmdtcurrencyFk(vService.getTcurrencyFk());
                        wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("Regular rate for " + vService.getServiceModeDetail().getSmdname() + detCharge);
                        log.info(ub.getUserId() + " " + wo.getWorkOrderServiceModeDetail().get(0).toString());
                        String deptId = getRateByDept(ub.getEmployeeId());
                        String retApv = "";
                        try {
                            if (wo.getCharge().compareTo(BigDecimal.ZERO) == 1) {
                                retApv = getLevelApproval(getRateByDept(ub.getEmployeeId()), wo.getCharge(), wo.getCurrency(), request);
                            } else {
                                retApv = getLevelApproval(deptId, wo.getWorkOrderServiceModeDetail(), request);
                            }
                        } catch (Exception e) {
                            throw new Exception(e);
                        }
                        wo.setMaxlevel(Integer.valueOf(retApv));
                        request.getSession().setAttribute("levelapproval", retApv);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        request.setAttribute("errorMessage", "Chargeable Weight is out of range/rate not available, <a href='#' onclick=\"location.href='workorder.do?adhocManifestBack=true'\">click here</a> to convert into adhoc Work Order!");
                    }
                    wo.setAgreementno(vService.getRefagreementno());
                    wo.setAgreementdate(vService.getRefagreementdate());
                }
                request.getSession().setAttribute("manifests", manifests);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                errorMessage = e.getMessage();
            }
        }
        return errorMessage;
    }

    public static void main(String[] args) {
        //WorkOrder wo = WorkOrderLocalServiceUtil.getWorkOrderyByPrimaryKeyWithJoinToFast(56613L);
        //getCharges(wo.getWorkOrderServiceModeDetail());
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";

    }// </editor-fold>
}
