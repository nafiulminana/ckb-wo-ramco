/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;
  
import com.ckb.wo.client.model.ExtendedWorkOrderExample;
import com.ckb.wo.client.model.Notes; 
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.GlobalServiceUtil;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import com.shido.jdbc2orm.JDBCConnector;
import java.io.IOException;
import java.io.PrintWriter; 
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;

/**
 *
 * @author nafiu
 */
public class AdminListener extends HttpServlet{
    
    final static Logger log = Logger.getLogger(AdminListener.class);
    
    public boolean cancel(Long woPk, String reason) {
        log.info("Cancelling WO, wo_pk=" + woPk + ", Reason=" + reason );
        try {
            
            if (reason.trim().isEmpty()) {
                return false;
            }
            UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
            if (ub == null) {
                return false;
            }
            
            // check for only can access
            boolean userCanAcces = PermissionLocalServiceUtil.getUserCanCanceledWo(ub.getEmployeeId());
            if(! userCanAcces ){
                return false;
            }
             
            WorkOrderLocalServiceUtil.cancelWorkOrderByAdmin(woPk, reason, ub.getEmployeeId());
            
            if( ! GlobalServiceUtil.IS_DEVELOPMENT ){
                final JDBCConnector fcon = new JDBCConnector("fast");
                fcon.executeNonQuery("delete from t_manifest_wo where tworkorder_pk='" + woPk + "'");
            }            

            Notes note = new Notes();
            note.setNoteType("CANCELED");
            note.setEmployeeId(ub.getEmployeeId());
            note.setNotes(reason);
            note.setTworkorderFk(woPk);
            NotesLocalServiceUtil.saveNotes(note);
            
        } catch (com.ckb.wo.server.exception.SecurityException | SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
            if (ub == null) {
                return;
            }
            // check for only can access
            boolean userCanAcces = PermissionLocalServiceUtil.getUserCanCanceledWo(ub.getEmployeeId());
            if(! userCanAcces){
                return;
            }
            
            ExtendedWorkOrderExample example = new ExtendedWorkOrderExample();
            ExtendedWorkOrderExample.Criteria criteria = example.createCriteria();
            
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String search = null;
            if (request.getParameter("search") != null) {
                search = request.getParameter("search");
                if (search != null) {
                    criteria.andNomorwoLike("%" + search.replaceAll("\'", "\'\'") + "%");
                }
            }
            
            try {
                criteria.andCreateddateGreaterThanOrEqualTo(sdf.parse(request.getParameter("dateFrom")));
            } catch (ParseException e) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, Calendar.JANUARY);
                cal.set(Calendar.DATE, 1);
                criteria.andCreateddateGreaterThanOrEqualTo(cal.getTime());
            }
            
            try {
                criteria.andCreateddateLessThanOrEqualTo(sdf.parse(request.getParameter("dateTo")));
            } catch (ParseException e) {
            }
            
            List wstat = new ArrayList();
            wstat.add(WorkOrder.APPROVED_STATUS); 
            wstat.add(WorkOrder.PRINTED_STATUS); 
            criteria.andWostatusIn(wstat); 
            
            int totalWO = 0;
            totalWO = WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
            
            int start = 0;
            int limit = 20;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (NumberFormatException e) {
            }
            example.setLimitClause(start + "," + limit);
            
            List<WorkOrder> lWo = WorkOrderLocalServiceUtil.getWorkOrderByExampleWithJoinWithoutDa(example);
            Map map = new HashMap();
            map.put("total", totalWO);
            
            JSONArray data = new JSONArray();
            if(lWo != null){
                Iterator<WorkOrder> itw = lWo.iterator();
                while (itw.hasNext()) {
                    WorkOrder o = itw.next();
                    JSONObject obj = JSONObject.fromObject(o);
                    obj.put("type", (o.getServiceType() != null ? o.getServiceType().getServicename() : "") + " " + (o.getOrder() != null ? o.getOrder().getOrdername() : ""));
                    Long order = o.getTorderFk();
                    order = order == null ? 0 : order;

                    obj.put("charges", o.getWostatus().equals(WorkOrder.CANCELLED_STATUS) ? String.format("%s %,.2f", CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(o.getCancellationtcurrencyfk()).getCurrencycode(), o.getCancellationfee()) :  WorkOrderListener.getCharges(o.getWorkOrderServiceModeDetail()) );
                    obj.put("action", "<a href='#' onclick='viewWo(" + o.getTworkorderPk() + "," + (o.getTservicetypeFk() == 2) + ")'>View</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='viewNotes(" + o.getTworkorderPk() + ")'>Notes</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"" + getServletContext().getContextPath() + "/list/workorder_history.jsp?wo=" + o.getTworkorderPk() + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>History</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='cancel(" + o.getTworkorderPk() + ")'>Cancel Wo</a>"); 
                    data.add(obj);
                }
            } 
            
            map.put("data", data);
            JSONObject obj = JSONObject.fromObject(map);
            out.print(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
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
            throws ServletException,
            IOException {
        processRequest(request, response);
    }

    
}
