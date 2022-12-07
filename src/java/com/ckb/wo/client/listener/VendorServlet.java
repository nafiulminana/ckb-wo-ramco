/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.server.service.constant.WOConstant;
import com.ckb.wo.server.service.util.PagingUtil;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 *
 * @author Admin
 */
public class VendorServlet extends GenericMasterTableServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if(request.getParameter(WOConstant.ACT)!=null){
            if(request.getParameter(WOConstant.ACT).equalsIgnoreCase(WOConstant.POPUP)){
                String vendorname = request.getParameter("vendorname");
                if(vendorname==null) vendorname=WOConstant.EMPTY_STRING;
                String pageNumber = getPageNumber(request, response, "vendortable");
                
                int totalrecord = VendorLocalServiceUtil.countActiveVendorByName(vendorname);
                String limitClause = PagingUtil.getLimitClause(pageNumber);
                List<Vendor> vendor = VendorLocalServiceUtil.getActiveVendorByName(vendorname, limitClause, "vendorname asc");
                int recordToDisplay = 0;
                if(vendor!=null){
                    recordToDisplay = vendor.size();
                }
               request.setAttribute("pagesize", String.valueOf(WOConstant.RECORD_PER_PAGE));
               request.setAttribute("vendor", vendor);
               request.setAttribute("count", totalrecord);
               forward(request, response, "/WEB-INF/jsp/vendor/vendorpopup.jsp");
            }
        }else{
            
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
