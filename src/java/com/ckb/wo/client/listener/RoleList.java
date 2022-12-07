/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Role;
import com.ckb.wo.client.model.RolesUsers;
import com.ckb.wo.client.model.User;
import com.ckb.wo.server.dao.ibatis.impl.RolesUsersDAOImpl;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import com.ckb.wo.server.service.util.RoleLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author Amaran Sidhiq
 */
public class RoleList extends HttpServlet {

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

            List<Role> roles = null;
            User user = null;
            String uid = request.getParameter("u");

            if (uid != null) {
                user = UserLocalServiceUtil.getUserByPrimaryKeyWithRoles(uid);
                roles = user.getRoles();
            } else {
                roles = RoleLocalServiceUtil.getRole();
            }

            Map m = new HashMap();
            JSONArray data = new JSONArray();
            if (roles != null || roles.size() > 0) {
                Iterator<Role> it = roles.iterator();
                while (it.hasNext()) {
                    Role r = it.next();
                    if(r.getTrolePk()==null)continue;
                    JSONObject obj = JSONObject.fromObject(r);
                    if(user!=null)
                    obj.put("rolename", obj.get("rolename") + " (<a href='#' onclick='removeRole(\"" + user.getEmployeeId() + "\"," + obj.getString("trolePk") + ")'>Remove</a>)");
                    data.add(obj);
                }
            }
            m.put("data", data);
            m.put("success", true);
//            m.put("success", data.size()>0);
            out.print(JSONObject.fromObject(m));
        }catch(Exception e){
            logger.LoggerClass.logError(RoleList.class, e);
            e.printStackTrace();
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
