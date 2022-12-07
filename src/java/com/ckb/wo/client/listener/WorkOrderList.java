package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 *
 * @author Amaran Sidhiq
 */
public class WorkOrderList extends HttpServlet {

    final static Logger LOG = Logger.getLogger(WorkOrderList.class);

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
            if (ub == null) {
                return;
            }

            ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
            String search = null;
            com.ckb.wo.client.model.ExtendedWorkOrderExample.Criteria criteria = example.createCriteria();
            criteria.andCreatedbyemployeeidEqualTo(ub.getEmployeeId());

            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (request.getParameter("search") != null) {
                search = request.getParameter("search");
                if (search != null) {
                    criteria.andNomorwoLike("%" + search.replaceAll("\'", "\'\'") + "%");
                }
            }

            if (request.getParameter("t") != null) {
                List<String> status = new ArrayList<>();
                status.add(WorkOrder.CANCELLED_STATUS);
                status.add(WorkOrder.CANCEL_RECEIVED_STATUS);
                status.add(WorkOrder.APPROVED_STATUS);
                status.add(WorkOrder.PRINTED_STATUS);
                status.add(WorkOrder.RECEIVED_STATUS);
                status.add(WorkOrder.RECEIVED_AP);
                status.add(WorkOrder.CANCEL_RECEIVED_AP);
                if (request.getParameter("t").equals("new")) {
                    criteria.andWostatusNotIn(status);
                    Calendar cal = Calendar.getInstance();
                    cal.roll(Calendar.DATE, -1);
                    criteria.andCreateddateGreaterThan(cal.getTime());
                } else {
                    if (request.getParameter("t").equals("action")) {
                        status = new ArrayList<>();
                        status.add(WorkOrder.EDIT_STATUS);
                        criteria.andWostatusIn(status);
                    } else if (request.getParameter("t").equals("ongoing")) {
                        criteria.andWostatusNotIn(status);
                    } else if (request.getParameter("t").equals(WorkOrder.CANCELLED_STATUS)) {
                        status = new ArrayList<>();
                        status.add(WorkOrder.CANCELLED_STATUS);
                        status.add(WorkOrder.CANCEL_RECEIVED_STATUS);
                        criteria.andWostatusIn(status);
                    } else if (request.getParameter("t").isEmpty()) {

                    } else {
                        criteria.andWostatusEqualTo(request.getParameter("t"));
                    }
                    try {
                        criteria.andCreateddateGreaterThanOrEqualTo(sdf.parse(request.getParameter("dateFrom")));
                    } catch (ParseException e) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.MONTH, Calendar.JANUARY);
                        cal.set(Calendar.DATE, 1);
                        cal.add(Calendar.YEAR, -1);
                        criteria.andCreateddateGreaterThanOrEqualTo(cal.getTime());
                    }
                    try {
                        criteria.andCreateddateLessThanOrEqualTo(sdf.parse(request.getParameter("dateTo")));
                    } catch (ParseException e) {
                    }
                }
            }

            int totalWO = WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
            int start = 0;
            int limit = 10;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (NumberFormatException e) {
            }
            example.setLimitClause(start + "," + limit);
            List<WorkOrder> lWo = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoin(example);
            Map map = new HashMap();
            map.put("total", totalWO);
            JSONArray data = new JSONArray();
            if (lWo != null) {
                for (WorkOrder o : lWo) {
                    try {
                        JSONObject obj = JSONObject.fromObject(o);
                        obj.put("type", (o.getServiceType() != null ? o.getServiceType().getServicename() : "") + " " + (o.getOrder() != null ? o.getOrder().getOrdername() : ""));
                        Long order = o.getTorderFk();
                        order = order == null ? 0 : order;
                        obj.put("charges", order != 1 || o.getAdhoc() ? WorkOrderListener.getCharges(o.getWorkOrderServiceModeDetail()) : String.format("%s %,.2f", o.getCurrency().getCurrencycode(), o.getCharge()));
                        obj.put("action", "<a href='#' onclick='viewWo(" + o.getTworkorderPk() + "," + (o.getTservicetypeFk() == 2) + ")'>View</a>");
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='viewNotes(" + o.getTworkorderPk() + ")'>Notes</a>");
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"" + getServletContext().getContextPath() + "/list/workorder_history.jsp?wo=" + o.getTworkorderPk() + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>History</a>");
                        if (o.getWostatus().equals(WorkOrder.EDIT_STATUS)) {
                            obj.put("action", obj.get("action") + " | <a href='#' onclick='document.location.href=\"../workorder/workorder.do?edit=" + o.getTworkorderPk() + "\"'>Edit</a>");
                        }
                        if (o.getNextApprovalName().equalsIgnoreCase("PROCRUEMENT/EXPEDITOR")) {
                            obj.put("nextApprovalName", "PROCUREMENT/EXPEDITOR");
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.roll(Calendar.HOUR, -1);
                        if (o.getWostatus().equals(WorkOrder.INPROGRESS_STATUS)) {
                            if (cal.getTime().after(o.getCreateddate())) {
                                obj.put("action", obj.get("action") + " | <a href='#' onclick='sendReminder(" + o.getTworkorderPk() + ")'>Reminder</a>");
                            }
                        }
                        if (o.getWostatus().equals(WorkOrder.APPROVED_STATUS)) {
                            obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"../list/print.do?wo=" + o.getTworkorderPk() + "&t=" + (o.getTservicetypeFk() == 2) + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>Print</a>");
                        }
                        if (o.getWostatus().equals(WorkOrder.PRINTED_STATUS)) {
                            obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"../list/print.do?wo=" + o.getTworkorderPk() + "&t=" + (o.getTservicetypeFk() == 2) + "&sts=listPrint&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>Print</a>");
                        }
                        if (o.getWostatus().equals(WorkOrder.APPROVED_STATUS) || o.getWostatus().equals(WorkOrder.PRINTED_STATUS)) {
                            obj.put("action", obj.get("action") + " | <a href='#' onclick='document.location.href=\"../workorder/workorder.do?rev=" + o.getTworkorderPk() + "\"'>Revision</a>");
                        }

                        data.add(obj);
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
            map.put("data", data);
            JSONObject obj = JSONObject.fromObject(map);
            out.print(obj);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
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
