package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Amaran Sidhiq
 */
public class WOProcList extends HttpServlet {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WOProcList.class);

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
            String nomorwo = request.getParameter("wo");
            nomorwo = nomorwo != null ? (nomorwo.isEmpty() ? null : nomorwo) : nomorwo;
            int totalWO = 0;
            if (request.getParameter("t").equals("validate")) {
                totalWO = WorkOrderLocalServiceUtil.countWorkOrderForProcuremenUser(nomorwo);
            } else if (request.getParameter("t").equals("validated")) {
                totalWO = WorkOrderLocalServiceUtil.countWorkOrderValidatedByThisUser(ub.getEmployeeId());
            }
            int start = 0;
            int limit = 10;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (NumberFormatException e) {
            }

            List<WorkOrder> lWo = null;
            if (request.getParameter("t").equals("validate")) {
                String orderBy = "atworkorder.createddate desc";
                lWo = WorkOrderLocalServiceUtil.getWorkOrderForProcurementUser(nomorwo, start + "," + limit, orderBy);
            } else if (request.getParameter("t").equals("validated")) {
                lWo = WorkOrderLocalServiceUtil.getWorkOrderValidatedByThisUser(ub.getEmployeeId(), start + "," + limit, null);
            }
            Map map = new HashMap();
            map.put("total", totalWO);
            JSONArray data = new JSONArray();
            if (lWo != null && !lWo.isEmpty()) {
                for (WorkOrder o : lWo) {
                    o.setCreatorUser(UserLocalServiceUtil.getUserByPrimaryKey(o.getCreatedbyemployeeid()));
                    JSONObject obj = JSONObject.fromObject(o);
                    obj.put("type", (o.getServiceType() != null ? o.getServiceType().getServicename() : "") + " " + (o.getOrder() != null ? o.getOrder().getOrdername() : ""));
                    obj.put("charges", WorkOrderListener.getCharges(o.getWorkOrderServiceModeDetail()));
                    obj.put("action", "<a href='#' onclick='viewWo(" + o.getTworkorderPk() + "," + (o.getTservicetypeFk() == 2 ? true : false) + ")'>View</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='viewNotes(" + o.getTworkorderPk() + ")'>Notes</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"" + getServletContext().getContextPath() + "/list/workorder_history.jsp?wo=" + o.getTworkorderPk() + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>History</a>");
                    if (o.getWostatus().equals(WorkOrder.VALIDATION_STATUS)) {
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"../workorder/validate.do?wo=" + o.getTworkorderPk() + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>Validate</a>");
                    }
                    data.add(obj);
                }
            }
            map.put("data", data);
            JSONObject obj = JSONObject.fromObject(map);
            out.print(obj);
        } catch (Exception e) {
            log.error(e);
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
