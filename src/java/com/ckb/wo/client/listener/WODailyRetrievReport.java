/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Amaran Sidhiq
 */
public class WODailyRetrievReport extends HttpServlet {

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
            String loc = getServletContext().getRealPath("/report");
            File f = new File(loc);
            File[] files = f.listFiles(new FileFilter() {

                String acceptedExt[] = {".xls", "xlsx"};

                public boolean accept(File pathname) {
                    for (String ext : acceptedExt) {
                        if (pathname.getName().lastIndexOf(ext) >= 0) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            int start=0,limit=0;
            try {
                start=new Integer(request.getParameter("start"));
            } catch (Exception e) {
            }
            try {
                limit=new Integer(request.getParameter("limit"));
            } catch (Exception e) {
            }
            if(start+limit>files.length)limit=files.length-start;;
            JSONObject obj=new JSONObject();
            obj.put("total", files.length);
            JSONArray data = new JSONArray();
            
            for(int i=0;i<limit;i++){
                JSONObject o = new JSONObject();
                File fl=files[i+start];
                o.put("id", i+start);
                o.put("filename", fl.getName());
                o.put("size", fl.length()/1024);
                o.put("url",request.getContextPath()+"/report/"+ fl.getName());
                o.put("datemodified",new Date(fl.lastModified()).toString());
                data.add(o);
            }
            obj.put("data", data);
            obj.put("success", true);

            out.write(obj.toString());
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
