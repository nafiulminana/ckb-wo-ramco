package com.ckb.wo.report;

import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author ckb
 */
public class downloadWO extends HttpServlet {

    final static Logger log = Logger.getLogger(downloadWO.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        System.gc();
        boolean type = false;
        Long wopk = new Long(request.getParameter("wo"));
        try {
            try {
                type = Boolean.parseBoolean(request.getParameter("t"));
            } catch (Exception e) {
                type = false;
            }
            
            String fromForm = "";
            try {
                fromForm = request.getParameter("sts") == null ? "" : request.getParameter("sts");
            } catch (Exception e) {
                fromForm = "";
            }
            
            if (request.getParameter("h") != null) {
                ReportHelper.printHtml(response.getOutputStream(), wopk, request.getSession(), type);
            } else if(fromForm.equalsIgnoreCase("listPrint")) {
                UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
                WorkOrderLocalServiceUtil.printWorkOrderWithOutUpdate(wopk, ub.getEmployeeId());
                ReportHelper.print(response.getOutputStream(), wopk, type);
            } else {
                UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
                if (request.getParameter("nwf") == null) {
                    WorkOrderLocalServiceUtil.printWorkOrder(wopk, ub.getEmployeeId());
                }
                ReportHelper.print(response.getOutputStream(), wopk, type);
            }
        } catch (ServletException err){
            ReportHelper.printCopy(response.getOutputStream(), wopk, type);
        } catch (Exception e) {
            ReportHelper.printCopy(response.getOutputStream(), wopk, type);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(downloadWO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(downloadWO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
