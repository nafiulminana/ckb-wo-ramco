/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.LocationExample;
import com.ckb.wo.server.service.constant.WOConstant;
import com.ckb.wo.server.service.util.LocationLocalServiceUtil;
import com.ckb.wo.server.service.util.PagingUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class LocationServlet extends GenericMasterTableServlet {
   
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
                String locationname = request.getParameter("locationname");
                if(locationname==null) locationname=WOConstant.EMPTY_STRING;
                String pageNumber = getPageNumber(request, response, "locationtable");
                int totalrecord = 0;
                LocationExample example = new LocationExample();
                example.createCriteria().andLocationnameLike(WOConstant.PERCENT+locationname+WOConstant.PERCENT);
                totalrecord = LocationLocalServiceUtil.countLocationByExample(example);
                String limitClause = null;
                limitClause = PagingUtil.getLimitClause(pageNumber);
                example.setLimitClause(limitClause);
                example.setOrderByClause("locationname asc");
                List<Location> location = LocationLocalServiceUtil.getLocationByExample(example);

                int recordToDisplay = 0;
                if(location!=null){
                    recordToDisplay = location.size();
                }
               request.setAttribute("pagesize", String.valueOf(WOConstant.RECORD_PER_PAGE));
               request.setAttribute("location", location);
               request.setAttribute("count", totalrecord);
               if(request.getParameter("origin")!=null){
                    request.setAttribute("type", "origin");
               }else if(request.getParameter("destination")!=null){
                    request.setAttribute("type", "destination");
               }
               forward(request, response, "/WEB-INF/jsp/location/locationpopup.jsp");
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
