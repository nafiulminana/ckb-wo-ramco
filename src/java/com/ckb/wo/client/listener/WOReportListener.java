/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.server.service.util.LocationLocalServiceUtil;
import com.ckb.wo.server.service.util.OrderLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
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
public class WOReportListener extends HttpServlet {
   
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
            String rparam=null;
            if((rparam=request.getParameter("r"))!=null){
                if(rparam.equalsIgnoreCase("location")){
                    List<Location> lloc = LocationLocalServiceUtil.getLocation();
                    Map map = new HashMap();
                    map.put("data", lloc);
                    map.put("success", true);
                    JSONObject o = JSONObject.fromObject(map);
                    out.println(o);
                }else if(rparam.equalsIgnoreCase("vendor")){
                    List<Vendor> vndr = VendorLocalServiceUtil.getActiveVendor();
                    JSONArray data = new JSONArray();
                    for(Vendor o:vndr.toArray(new Vendor[vndr.size()])){
                        JSONObject obj = new JSONObject();
                        obj.put("vendorpk", o.getTvendorPk());
                        obj.put("vendorname", o.getVendorname());
                        data.add(obj);
                    }
                    Map map = new HashMap();
                    map.put("data", data);
                    map.put("success", true);
                    JSONObject o = JSONObject.fromObject(map);
                    out.println(o);
                }else if(rparam.equalsIgnoreCase("creator")){
                    List<User> user = UserLocalServiceUtil.getUserAlreadyCreateWO();
                    JSONArray data = new JSONArray();
                    for(User o:user.toArray(new User[user.size()])){
                        JSONObject obj = new JSONObject();
                        obj.put("employee_id", o.getEmployeeId());
                        obj.put("fullname", o.getFirstName().concat(" ".concat(o.getMiddleName()!=null?o.getMiddleName().concat(" "):"").concat(o.getLastName())));
                        data.add(obj);
                    }
                    Map map = new HashMap();
                    map.put("data", data);
                    map.put("success", true);
                    JSONObject o = JSONObject.fromObject(map);
                    out.println(o);
                }else if(rparam.equalsIgnoreCase("wtype")){
                    List<Order> ord = OrderLocalServiceUtil.getOrder();
                    Map map = new HashMap();
                    map.put("data", ord);
                    map.put("success", true);
                    JSONObject o = JSONObject.fromObject(map);
                    out.println(o);
                }else if(rparam.equalsIgnoreCase("process")){

                }
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
