/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExample;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
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
import net.sf.json.JsonConfig;

/**
 *
 * @author Amaran Sidhiq
 */
public class VendorServiceList extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try {
                int type = new Integer(request.getParameter("t"));
                VendorServiceExample exm = new VendorServiceExample();
                VendorServiceExample.Criteria crit = exm.createCriteria();
                switch (type) {
                    case 1:
                        break;
                    case 2:
                        break;
                }
                int totalService = VendorServiceLocalServiceUtil.countVendorServiceByExample(exm);
                int start = 0;
                int limit = 10;
                try {
                    start = new Integer(request.getParameter("start"));
                    limit = new Integer(request.getParameter("limit"));
                } catch (Exception e) {
                }
                exm.setLimitClause(start + "," + limit);

                List<VendorService> lWo = VendorServiceLocalServiceUtil.getVendorServiceByExampleWithJoin(exm);
                Map map = new HashMap();
                map.put("total", totalService);

                JsonConfig jsonConfig = new JsonConfig();

                map.put("data", JSONArray.fromObject(lWo, jsonConfig));

                JSONObject obj = JSONObject.fromObject(map);

                out.print(obj);
            } catch (Exception e) {
            }
        } finally {
            out.close();
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
