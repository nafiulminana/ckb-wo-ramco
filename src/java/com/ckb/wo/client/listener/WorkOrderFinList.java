package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;

/**
 *
 * @author Amaran Sidhiq
 */
public class WorkOrderFinList extends HttpServlet {

    final static Logger log = Logger.getLogger(WorkOrderFinList.class);

    public boolean receive(Long wopk, boolean ap) {
        try {
            UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
            if (ap) {
                WorkOrderLocalServiceUtil.receivedAPWorkOrder(wopk, ub.getEmployeeId());
                log.info("AP Received");
            } else {
                WorkOrderLocalServiceUtil.receivedTreasuryWorkOrder(wopk, ub.getEmployeeId());
                log.info("Treasury Received");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean cancel(String o) {
        try {
            UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
            if (ub == null) {
                log.error("Cancelling WO, but No User");
                throw new Exception("No User");
            }
            boolean canCancel = PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.CANCEL_AP);
            if(!canCancel){
                log.error("Cancel WO AP do not have access");
                throw new Exception("Cancel WO AP do not have access");
            }
            
            JSONObject jsonObject = JSONObject.fromObject(o);
            log.info("Cancelling WO " + jsonObject.get("k") + " By AP");
            WorkOrderLocalServiceUtil.cancelWorkOrderAP(Long.valueOf(jsonObject.get("k").toString()), jsonObject.get("reason").toString(), null, null, ub.getEmployeeId());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
            if (ub == null) {
                return;
            }
            // can cancel received ap
            boolean canCancel = PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.CANCEL_AP);
            
            ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
            ExtendedWorkOrderExample.Criteria criteria = example.createCriteria();
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String search = null;
            if (request.getParameter("search") != null) {
                search = request.getParameter("search");
                if (search != null) {
                    criteria.andNomorwoLike("%" + search.replaceAll("\'", "\'\'") + "%");
                }
            }
            try {
                criteria.andCreateddateGreaterThanOrEqualTo(sdf.parse(request.getParameter("dateFrom")));
            } catch (ParseException e) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, Calendar.JANUARY);
                cal.set(Calendar.DATE, 1);
                criteria.andCreateddateGreaterThanOrEqualTo(cal.getTime());
            }
            try {
                criteria.andCreateddateLessThanOrEqualTo(sdf.parse(request.getParameter("dateTo")));
            } catch (ParseException e) {
            }
            boolean ap = PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.RECEIVE_AP);
            boolean treasury = PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.RECEIVE_TR);
            if (request.getParameter("t").equals(WorkOrder.APPROVED_STATUS)) {
                String sql = "";
                if (ap && treasury) {
                    sql = "atworkorder.wostatus IN ('" + WorkOrder.PRINTED_STATUS + "', '" + WorkOrder.APPROVED_STATUS + "') "
                            + " OR (atworkorder.cancellationfee IS NOT NULL AND atworkorder.wostatus = '" + WorkOrder.CANCELLED_STATUS + "')";
                    sql += "OR atworkorder.wostatus IN ('" + WorkOrder.RECEIVED_AP + "', '" + WorkOrder.CANCEL_RECEIVED_AP + "')";
                } else if (ap) {
                    sql = "atworkorder.wostatus IN ('" + WorkOrder.PRINTED_STATUS + "', '" + WorkOrder.APPROVED_STATUS + "') "
                            + " OR (atworkorder.cancellationfee IS NOT NULL AND atworkorder.wostatus = '" + WorkOrder.CANCELLED_STATUS + "')";
                } else {
                    sql = "atworkorder.wostatus IN ('" + WorkOrder.RECEIVED_AP + "', '" + WorkOrder.CANCEL_RECEIVED_AP + "')";
                }
                criteria.setAndCustomSQL(sql);
            } else if (request.getParameter("t").equals(WorkOrder.RECEIVED_STATUS)) {
                List wstat = new ArrayList();
                wstat.add(WorkOrder.RECEIVED_AP);
                wstat.add(WorkOrder.CANCEL_RECEIVED_AP);
                wstat.add(WorkOrder.RECEIVED_STATUS);
                wstat.add(WorkOrder.CANCEL_RECEIVED_STATUS);
                criteria.andWostatusIn(wstat);
            } else if (request.getParameter("t").equals(WorkOrder.CANCELLED_AP)) {
                List wstat = new ArrayList();
                wstat.add(WorkOrder.CANCELLED_AP);
                criteria.andWostatusIn(wstat);
            }
            int totalWO = 0;
            totalWO = WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
            int start = 0;
            int limit = 20;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (NumberFormatException e) {
            }
            example.setLimitClause(start + "," + limit);
            List<WorkOrder> lWo = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinWithoutDa(example);
            Map map = new HashMap();
            map.put("total", totalWO);
            JSONArray data = new JSONArray();
            Iterator<WorkOrder> itw = lWo.iterator();
            while (itw.hasNext()) {
                WorkOrder o = itw.next();
                JSONObject obj = JSONObject.fromObject(o);
                obj.put("type", (o.getServiceType() != null ? o.getServiceType().getServicename() : "") + " " + (o.getOrder() != null ? o.getOrder().getOrdername() : ""));
                Long order = o.getTorderFk();
                order = order == null ? 0 : order;
                boolean treasuryReceived = o.getWostatus().equalsIgnoreCase(WorkOrder.CANCEL_RECEIVED_AP) || o.getWostatus().equalsIgnoreCase(WorkOrder.RECEIVED_AP);
                obj.put("charges", o.getWostatus().equals(WorkOrder.CANCELLED_STATUS) ? String.format("%s %,.2f", CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(o.getCancellationtcurrencyfk()).getCurrencycode(), o.getCancellationfee()) : /*order != 1 || o.getAdhoc() ?*/ WorkOrderListener.getCharges(o.getWorkOrderServiceModeDetail())/* : String.format("%s %,.2f", o.getCurrency().getCurrencycode(), o.getCharge())*/);
                obj.put("action", "<a href='#' onclick='viewWo(" + o.getTworkorderPk() + "," + (o.getTservicetypeFk() == 2) + ")'>View</a>");
                obj.put("action", obj.get("action") + " | <a href='#' onclick='viewNotes(" + o.getTworkorderPk() + ")'>Notes</a>");
                obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"" + getServletContext().getContextPath() + "/list/workorder_history.jsp?wo=" + o.getTworkorderPk() + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>History</a>");
                if (!request.getParameter("t").equals(WorkOrder.RECEIVED_STATUS)) {
                    if (o.getWostatus().equals(WorkOrder.APPROVED_STATUS) || o.getWostatus().equals(WorkOrder.PRINTED_STATUS) || o.getWostatus().equals(WorkOrder.CANCELLED_STATUS) || o.getWostatus().equals(WorkOrder.RECEIVED_AP) || o.getWostatus().equals(WorkOrder.CANCEL_RECEIVED_AP)) {
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='receive(" + o.getTworkorderPk() + "," + !treasuryReceived + ")'>Receive " + (treasuryReceived ? "Treasury" : "AP") + "</a>");
                    }
                    if (o.getWostatus().equals(WorkOrder.APPROVED_STATUS) && order == 1 && !o.getAdhoc()) {
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='validate(" + o.getTworkorderPk() + ")'>Check Weight</a>");
                    }
                } else {
                    if(canCancel){
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='cancel(" + o.getTworkorderPk() + "," + !treasuryReceived + ")'>Cancel AP</a>");
                    }                    
                }
                data.add(obj);
            }
            map.put("data", data);
            JSONObject obj = JSONObject.fromObject(map);
            out.print(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
