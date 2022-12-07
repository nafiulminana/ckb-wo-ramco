/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.ServiceModeDetailExample;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.server.service.constant.WOConstant;
import com.ckb.wo.server.service.util.PagingUtil;
import com.ckb.wo.server.service.util.ServiceModeDetailLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ServiceModeDetailServlet extends GenericMasterTableServlet {
   
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
                String smdcode = request.getParameter("smdcode");
                if(smdcode==null) smdcode=WOConstant.EMPTY_STRING;
                String pageNumber = getPageNumber(request, response, "servicemodedetailtable");
                ServiceModeDetailExample example = new ServiceModeDetailExample();
                example.createCriteria().andSmdcodeLike(WOConstant.PERCENT+smdcode+WOConstant.PERCENT);
                example.or(example.createCriteria().andSmdnameLike(WOConstant.PERCENT+smdcode+WOConstant.PERCENT));
                int totalrecord = ServiceModeDetailLocalServiceUtil.countServiceModeDetailByExample(example);
                String limitClause = PagingUtil.getLimitClause(pageNumber);
                example.setOrderByClause("smdcode asc");
                example.setLimitClause(limitClause);

                List<ServiceModeDetail> smdetail = ServiceModeDetailLocalServiceUtil.getServiceModeDetailByExample(example);
               request.setAttribute("pagesize", String.valueOf(WOConstant.RECORD_PER_PAGE));
               request.setAttribute("servicemodedetail", smdetail);
               request.setAttribute("count", totalrecord);
               forward(request, response, "/WEB-INF/jsp/smdetail/smdetailpopup.jsp");
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
