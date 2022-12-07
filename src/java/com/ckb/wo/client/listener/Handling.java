package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.Notes;
import com.ckb.wo.client.model.Shipment;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderDA;
import com.ckb.wo.server.exception.SecurityException;
import com.ckb.wo.server.service.util.LocationLocalServiceUtil;
import com.ckb.wo.server.service.util.ManifestDALocalServiceUtil;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import com.shido.jdbc2orm.JDBCConnector;
import com.shido.jdbc2orm.ResultMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ${user}
 */
public class Handling extends HttpServlet {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WorkOrderListener.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = "/workorder/workorder.do";
            try {
                WorkOrder wo = (WorkOrder) request.getSession().getAttribute("wo");
//                if(wo.getTservicetypeFk()==2))
                if (request.getParameter("daAdd") != null) {
                    log.info(ub.getUserId() + " adds DA");
                    Long daAdd = 0l;

                    if (request.getParameter("hven") == null || request.getParameter("hven").isEmpty()) {
                        throw new Exception("Please select a Vendor");
                    }
                    Long vendor = null;
                    try {
                        vendor = new Long(request.getParameter("hven"));
                        wo.setTvendorFk(vendor);
                        wo.setVendor(VendorLocalServiceUtil.getVendorByPrimaryKey(vendor));
                    } catch (NumberFormatException e) {
                        log.error(e.getMessage(), e);
                    }
                    
                    if (request.getParameter("hloc") == null || request.getParameter("hloc").isEmpty()) {
                        throw new Exception("Please select a Location");
                    }
                    Long location = null;
                    try {
                        location = new Long(request.getParameter("hloc"));
                        Location lc = LocationLocalServiceUtil.getLocationByPrimaryKey(location);
                        wo.setOrigintlocationFk(location);
                        wo.setOrigin(lc);
                        wo.setDestination(lc);
                        wo.setDestinationtlocationFk(location);
                        request.getSession().setAttribute("locationfrom", lc.getLocationname());
                    } catch (NumberFormatException e) {
                        log.error(e.getMessage(), e);
                    }

                    List<Shipment> shipments = (List<Shipment>) request.getSession().getAttribute("da");
                    if (shipments == null) {
                        shipments = new ArrayList<>();
                    }
                    String daMps = request.getParameter("damps");
                    if (daMps != null) {
                        daMps = daMps.trim();
                    }
                    List<String> daMpsAr = new ArrayList<>();
                    if (daMps.contains(",")) {
                        String[] daMpsTemp = daMps.split(",");
                        daMpsAr.addAll(Arrays.asList(daMpsTemp));
                    } else {
                        daMpsAr.add(daMps);
                    }

                    for (int daList = 0; daList < daMpsAr.size(); daList++) {
                        String daMPS = daMpsAr.get(daList);
                        boolean valid = true;
                        Shipment shipment = ManifestDALocalServiceUtil.getShipmentNotPODByPrimaryKey(new Long(daMPS));
                        if (shipment == null) {
                            shipment = ManifestDALocalServiceUtil.getShipmentByPrimaryKey(new Long(daMPS));
                            shipment.setPod(true);
                            if (shipment == null) {
                                throw new Exception("DA/MPS# Not Found!");
                            }
                            request.setAttribute("errorMessage", "WARNING: You use the DA/MPS# that have been POD!");
                        }
                        valid = !ManifestDALocalServiceUtil.isDAUsedInWO(shipment.getDa(), vendor, location);
                        JDBCConnector con = new JDBCConnector("fast");
                        ResultMap rs = con.getSingleResult("SELECT * FROM fast.`t_shipment` ts LEFT JOIN fast.`t_mps` tm ON ts.da = tm.da\n"
                                + "WHERE ts.created_date > '2017-05-24 00:00:00' AND ts.da = '" + shipment.getDa() + "' "
                                + "UNION ALL\n"
                                + "SELECT * FROM fast.`t_shipment` ts LEFT JOIN fast.`t_mps` tm ON ts.da = tm.da\n"
                                + "WHERE ts.created_date > '2017-05-24 00:00:00' AND tm.mps = '" + shipment.getDa() + "'");
                        boolean cutOffAlert = true;

                        if (rs == null) {
                            valid = false;
                            cutOffAlert = true;
                        } else {
                            cutOffAlert = false;
                        }
                        if (!valid) {
                            if (cutOffAlert) {
                                throw new Exception("WO Cannot be created due to cut-off data since 24 May 2017");
                            }
                            throw new Exception("DA/MPS# already used by the same vendor and location!");

                        }
                        for (int i = 0; i < shipments.size(); i++) {
                            if (shipments.get(i).getDa().equals(shipment.getDa())) {
                                valid = false;
                                break;
                            }
                        }

                        if (valid) {
                            BigDecimal totw, totvol;
                            totw = wo.getTotalweight();
                            totvol = wo.getTotalvolume();
                            totw = totw == null ? new BigDecimal(shipment.getTotWeight()) : totw.add(new BigDecimal(shipment.getTotWeight()));
                            totvol = totvol == null ? new BigDecimal(shipment.getTotDim()) : totvol.add(new BigDecimal(shipment.getTotDim()));
                            wo.setTotalweight(totw);
                            wo.setTotalvolume(totvol);
                            shipments.add(shipment);
                            request.getSession().setAttribute("da", shipments);
                        } else {
                            request.setAttribute("errorMessage", "Da Already Exist!");
                        }
                    }
                    path = "/workorder/workorder_handling.jsp";
                } else if (request.getParameter("remove") != null) {
                    List<Shipment> shipments = (List<Shipment>) request.getSession().getAttribute("da");
                    Shipment shipment = ManifestDALocalServiceUtil.getShipmentByPrimaryKey(new Long(request.getParameter("remove")));
                    for (int i = 0; i < shipments.size(); i++) {
                        if (shipments.get(i).getDa().equals(shipment.getDa())) {
                            BigDecimal totw, totvol;
                            totw = wo.getTotalweight();
                            totvol = wo.getTotalvolume();
                            totw = totw == null ? BigDecimal.ZERO : totw.subtract(new BigDecimal(shipment.getTotWeight()));
                            totvol = totvol == null ? BigDecimal.ZERO : totvol.subtract(new BigDecimal(shipment.getTotDim()));
                            wo.setTotalweight(totw);
                            wo.setTotalvolume(totvol);
                            shipments.remove(i);
                            request.getSession().setAttribute("da", shipments);
                            break;
                        }
                    }
                    path = "/workorder/workorder_handling.jsp";
                } else if (request.getParameter("handlingSave") != null) {
                    if (wo != null) {
                        try {
                            wo.setTvendorFk(new Long(request.getParameter("vendor")));
                        } catch (NumberFormatException e) {
                            throw new Exception("Vendor must be selected!");
                        }
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
                            wo.setOrigintlocationFk(new Long(request.getParameter("location")));
                            wo.setDestinationtlocationFk(new Long(request.getParameter("location")));
                        } catch (NumberFormatException e) {
                            throw new Exception("Select location first!");
                        }
                        
                        List<WorkOrderDA> wodas = new ArrayList<>();
                        List<Shipment> shipments = (List<Shipment>) request.getSession().getAttribute("da");
                        if (shipments == null || shipments.size() < 1) {
                            throw new Exception("DA/MPS to be handled is empty!");
                        }
                        
                        if (wo.getWorkOrderServiceModeDetail() != null) {
                            if (wo.getWorkOrderServiceModeDetail().isEmpty()) {
                                throw new Exception("Handling service is empty!");
                            }
                        } else {
                            throw new Exception("Handling service is empty!");
                        }
                        List<Notes> lNotes = new ArrayList();
                        for (int i = 0; i < shipments.size(); i++) {
                            WorkOrderDA woda = new WorkOrderDA();
                            woda.setDa(shipments.get(i).getDa());
                            woda.setPod(shipments.get(i).isPod());

                            if (woda.isPod()) {
                                Notes note = new Notes();
                                note.setNoteType("WARNING");
                                note.setNotes(String.format("DA# %d already POD!", woda.getDa()));
                                note.setEmployeeId(ub.getEmployeeId());
                                lNotes.add(note);
                            }
                            wodas.add(woda);
                        }
                        try {
                            wo.setMaxlevel(new Integer(request.getParameter("maxAppLvl")));
                        } catch (NumberFormatException e) {
                            throw new Exception("Last Approval level not selected!");
                        }
                        wo.setCharge(BigDecimal.ZERO);
                        wo.setWorkOrderDA(wodas);
                        wo.setDeliverynote(request.getParameter("deliveryNote"));
                        wo.setGooddescription(request.getParameter("goodsDesc"));

                        if (wo.getWostatus() != null) {
                            if (wo.getWostatus().equals(WorkOrder.EDIT_STATUS) || wo.getWostatus().equals(WorkOrder.REVISION_STATUS)) {
                                wo.setRevisionreason(request.getParameter("revisionreason"));
                                wo.setRevisionreasoneksternal(request.getParameter("revisionreasoneksternal"));
                                WorkOrderLocalServiceUtil.updateWorkOrderWithChildren(wo, ub.getEmployeeId());
                            }
                            request.setAttribute("errorMessage", "Work Order Saved!");
                        } else {
                            try {
                                WorkOrderLocalServiceUtil.insertWorkOrder(wo, ub.getEmployeeId());
                                request.setAttribute("errorMessage", "Work Order Saved!");
                            } catch (SecurityException e) {
                                request.setAttribute("errorMessage", e.getMessage());
                            }
                        }
                        for (Notes note : lNotes) {
                            note.setTworkorderFk(wo.getTworkorderPk());
                            NotesLocalServiceUtil.saveNotes(note);
                        }
                    }
                }
            } catch (Exception e) {
                logger.LoggerClass.logError(Handling.class, e);
                request.setAttribute("errorMessage", e.getMessage());
                path = "/workorder/workorder_handling.jsp";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
            rd.include(request, response);
        }
    }

    public static void main(String[] args) {
    }
    // <editor-fold defaultstate="collapsed" desc="generated code">

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
