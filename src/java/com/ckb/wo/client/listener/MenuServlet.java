package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amaran Sidhiq
 */
public class MenuServlet extends HttpServlet {

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
            if (request.getParameter("wo") != null) {
                if (PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.CREATE_WO)) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/workorder/workorder.do'>Create Work Order</a><br/>");
                    out.print("<a href='" + getServletContext().getContextPath() + "/list/workorder.jsp'>View Work Order</a><br/>");
                }
                if (PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.VIEW_REPORT)) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/list/workorder_daily.jsp'>Daily Work Order</a><br/>");
                }
                if (com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.RECEIVE_AP)
                        || com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.RECEIVE_TR)) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/list/workorder_fin.jsp'>Receive Work Order</a><br/>");
                }
            } else if (request.getParameter("proc") != null) {
                if (UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId()) || PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.VALIDATE_WO)) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/list/workorder_proc.jsp'>Validate Work Order Charge</a><br/>");
                    if (PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE)) {
                        out.print("<a href='" + getServletContext().getContextPath() + "/vendor/vendor.jsp'>Manage Vendor</a><br/>");
                        out.print("<a href='" + getServletContext().getContextPath() + "/vendor/master_table.jsp'>Manage Master Data</a><br/>");
                    }
                }
                out.print("<a href='" + getServletContext().getContextPath() + "/vendor/vendorservice.jsp'>" + (PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE) ? "Manage " : "") + "Rate</a><br/>");
            } else if (request.getParameter("app") != null) {
                if (PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.APPROVE_WO)) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/list/workorder_app.jsp'>My Approval</a><br/>");
                }
            } else if (request.getParameter("adm") != null) {
                if (PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_USER)) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/list/manage_user.jsp'>User Administration</a><br/>");
                }
                if (PermissionLocalServiceUtil.getUserCanCanceledWo(ub.getEmployeeId())) {
                    out.print("<a href='" + getServletContext().getContextPath() + "/admin/cancel_wo.jsp'>Cancel WO By IT</a><br/>");
                }
//                out.print("<a href='#'>Grant Approval To</a><br/>");
            }
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
