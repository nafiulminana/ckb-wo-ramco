/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Notes;
import com.ckb.wo.client.model.WorkOrderFlow;
import com.ckb.wo.report.ReportHelper;
import com.ckb.wo.server.service.util.LevelLocalServiceUtil;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderFlowLocalServiceUtil;
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
public class NotesListener extends HttpServlet {

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
            Long wo = new Long(request.getParameter("wo"));
            int totalNotes = NotesLocalServiceUtil.countNotes(wo);
            int start = 0;
            int limit = 10;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (Exception e) {
            }

            List<Notes> lNotes = NotesLocalServiceUtil.getNotesForWorkOrder(wo, start + "," + limit);

            Map map = new HashMap();
            map.put("total", totalNotes);
            JSONArray data = new JSONArray();
            Iterator<Notes> iNotes = lNotes.iterator();
            while (iNotes.hasNext()) {
                Notes o = iNotes.next();
                JSONObject obj = JSONObject.fromObject(o);
                obj.put("tnotesPk",o.getTnotesPk());
                obj.put("employeeid",ReportHelper.getCreatorName(o.getEmployeeId()));
                obj.put("notes",o.getNotes());
                obj.put("datetimeUpdated",o.getDatetimeUpdated().toString());
                data.add(obj);
            }
            map.put("data", data);

            JSONObject obj = JSONObject.fromObject(map);

            out.print(obj);
        }catch(Exception e){
            logger.LoggerClass.logError(NotesListener.class, e);
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
