package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Notes;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.service.util.NotesLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import com.shido.jdbc2orm.JDBCConnector;
import com.shido.jdbc2orm.ResultMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amaran Sidhiq
 */
public class ValidateListener extends HttpServlet {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ValidateListener.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
            if (ub != null) {
                boolean saved = false;
                WorkOrder wo = null;
                if (request.getParameter("validate") == null) {
                    if (wo == null) {
                        wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(new Long(request.getParameter("wo")));
                        JDBCConnector conn = new JDBCConnector();
                        ResultMap rss = conn.getSingleResult("SELECT remarks FROM `tt_workorder_remarks` WHERE tworkorderid_fk = '" + wo.getTworkorderPk() + "'");
                        if (rss != null) {
                            request.getSession().setAttribute("vRemarks", rss.getString("remarks"));
                        } else {
                            request.getSession().setAttribute("vRemarks", "");
                        }
                        request.getSession().setAttribute("vWo", wo);
                    }
                } else {
                    log.info("Validation Process");
                    wo = (WorkOrder) request.getSession().getAttribute("vWo");
                    wo.setValidationnote(request.getParameter("vNote"));
                    try {
                        JDBCConnector con = new JDBCConnector();
//                        int inst = con.executeNonQuery("INSERT INTO tnotes(tworkorder_fk, employeeid, note_type, notes, datetime_updated)\n"
//                                + "VALUES ('"+wo.getTworkorderPk()+"', '"+ub.getEmployeeId()+"', 'VALIDATION', '"+wo.getValidationnote()+"', NOW())");
                        if (wo.getValidationnote() == null || wo.getValidationnote().isEmpty()) {

                        } else {
                            //wo.setValidationnote(wo.getValidationnote().isEmpty() ? null : wo.getValidationnote());
                            Notes note = new Notes();
                            note.setNoteType("VALIDATION");
                            note.setEmployeeId(ub.getEmployeeId());
                            note.setNotes(wo.getValidationnote());
                            note.setTworkorderFk(wo.getTworkorderPk());
                            NotesLocalServiceUtil.saveNotes(note);
                        }
                    } catch (Exception e) {
                        log.error(e);
                    }
                    String[] charges = request.getParameterValues("wsmd");
                    int chi = 0;
                    for (int i = 0; i < wo.getWorkOrderServiceModeDetail().size(); i++) {
//                        System.out.println(charges[i]);
                        if (wo.getWorkOrderServiceModeDetail().get(i).isDeleted()) {
                            continue;
                        }
                        if (new BigDecimal(charges[chi]).compareTo(wo.getWorkOrderServiceModeDetail().get(i).getSmdcharge()) < 0) {
                            if (wo.getValidationnote() == null) {
                                out.print("Validation Notes Must Be Fill!");
                                saved = false;
                                break;
                            }
                        }
                        if (new BigDecimal(charges[chi]).compareTo(wo.getWorkOrderServiceModeDetail().get(i).getSmdcharge()) > 0) {
                            out.print("New Charge Must below Old Charge!");
                            saved = false;
                            break;
//                                RequestDispatcher rd = request.getRequestDispatcher("/workorder/validate.jsp");
//                                rd.include(request, response);
//                                rd.forward(request, response);
                            //   RequestDispatcher rd = this.getServletContext().getRequestDispatcher(path);
//                             throw new Exception("Select Currency!");
                        } else {
                            wo.getWorkOrderServiceModeDetail().get(i).setSmdcharge(new BigDecimal(charges[chi]));
                            saved = true;
                        }
                        chi++;
                    }
//                     throw new ValidationException(
//                    "invalid next user level. User may not mapped correctly to tlevel");
                    try {
//                        try {
//                            wo.setCharge(new BigDecimal(request.getParameter("hcharge")));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        String pph = request.getParameter("pph");
                        if (pph != null && !pph.isEmpty()) {
                            wo.setPph(new BigDecimal(request.getParameter("pph")));
                        } else {
                            wo.setPph(null);
                        }
                        String ppn = request.getParameter("ppn");
                        if (ppn != null && !ppn.isEmpty()) {
                            wo.setPpn(new BigDecimal(request.getParameter("ppn")));
                        } else {
                            wo.setPpn(null);
                        }
                        wo.setAgreementno(request.getParameter("agreementno"));
                        try {
                            wo.setAgreementdate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hagreementdate")));
                        } catch (ParseException e) {
                            log.error(e.toString());
                        }
                        wo.setDeliverynote(request.getParameter("deliveryNote"));
                        if (saved) {
                            try {
                                log.info("Update WO after validation");
                                WorkOrderLocalServiceUtil.updateWorkOrderAfterCostValidation(wo, ub.getEmployeeId());
                                saved = true;
                            } catch (Exception e) {
                                log.error(e);
                            }
                        }
                    } catch (Exception e) {
                        log.error(e);
                    }
                }

                request.setAttribute("saved", saved);
                RequestDispatcher rd = request.getRequestDispatcher("/workorder/validate.jsp");
                rd.include(request, response);
            } else {
                out.print("<script type='text/javascript'>");
                out.print("alert('Session invalid, relogin please!');");
                out.print("parent.location.href='" + getServletContext().getContextPath() + "/index.jsp'");
                out.print("</script>");
            }
        } catch (IOException | ClassCastException | NumberFormatException | SQLException | ServletException e) {
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
            throws ServletException, IOException {
        processRequest(request, response);
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
