package com.ckb.wo.server;

import com.ckb.wo.client.model.UserAccess;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.server.service.util.LoginLocalServiceUtil;
import com.shido.encryptor.MD5;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AmaranSidhiq
 */
public class ValidateLogin extends HttpServlet {

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
        response.setContentType("text/plain");
        //System.gc();
//        JDBCConnector connector = new JDBCConnector(getServletContext());

//        String driver = getServletContext().getInitParameter("JDBCDriver");
//        String location = getServletContext().getInitParameter("loginDBlocation");
//        String port = getServletContext().getInitParameter("loginDBport");
//        String user = getServletContext().getInitParameter("loginDBuser");
//        String password = getServletContext().getInitParameter("loginDBpass");
//        String databaseName = getServletContext().getInitParameter("loginDBname");
//        JDBCConnector connector = new JDBCConnector(driver, location, port, user, password, databaseName);
        UserBeans ub = new UserBeans();
//        String result = "";

        String messages = "";
        try {
            String userId = request.getParameter("txtUser").replace("\'", "\'\'");
            String MD5password = MD5.encrypt(request.getParameter("txtPassword").replace("\'", "\'\'"));
            String password = request.getParameter("txtPassword").replace("\'", "\'\'");

//            String strSQL = String.format("SELECT user_access.t_application_access.* ,"
//                    + " user_access.t_user.employee_id,"
//                    + " user_access.t_user.valid_to AS user_validto ,"
//                    + " CONCAT_WS(' ',employee.t_employee.first_name,employee.t_employee.middle_name,employee.t_employee.last_name) AS fullname ,"
//                    + " user_access.t_user.password,"
//                    + " user_access.t_application_access.user_level_id,"
//                    + " user_access.t_user.station_id"
//                    + " FROM"
//                    + " user_access.t_application_access"
//                    + " INNER JOIN user_access.t_user ON (t_application_access.user_id = t_user.user_id)"
//                    + " INNER JOIN employee.t_employee ON (employee.t_employee.employee_id = user_access.t_user.employee_id)"
//                    + " WHERE"
//                    + " user_access.t_user.valid_to >= NOW()"
//                    + " AND user_access.t_application_access.application_id = %s"
//                    + " AND user_access.t_user.user_id ='%s'"
//                    + " AND user_access.t_user.password ='%s'"
//                    + " ORDER BY employee.t_employee.first_name",
//                    //                    getServletContext().getInitParameter("applicationId"),
//                    49,
//                    userId,
//                    MD5password);
//
//            System.out.println(strSQL);
//            ResultSet rs = connector.getQuery(strSQL);
//            if (rs.next()) {
            UserAccess ua = LoginLocalServiceUtil.login(userId, password);
            if (ua != null) {
//                do {
                ub.setFullName(ua.getFullName());
                ub.setUserId(ua.getUserId());
                ub.setPassword(MD5password);
                ub.setEmployeeId(ua.getEmployeeId());
                ub.setLoginLevel(ua.getAccessPriv());
                ub.setStationId(ua.getStationId());
                ub.setLogon(true);
//                } while (rs.next());
//                rs.close();
//                rs = null;

            } else {
                messages = "Authorization failed!<br/><b>reason:</b><br/>Username or Password is invalid?";
            }
        } catch (Exception e) {
            ub.setLogon(false);
            messages = e.getMessage();
            logger.LoggerClass.logError(ValidateLogin.class, e);
        } finally {
            //out.print(result);
            ub.setErrMessage(messages);
            request.getSession().setAttribute("loginInfo", ub);
//            connector.closeConnection();
//            connector = null;

        }
        if (ub.isLogon()) {
//            //Addition Check Deleted Manifest
//            final JDBCConnector wcon = new JDBCConnector();
//            final JDBCConnector fcon = new JDBCConnector("fast");
//            String strSQL = "Select mw.*,p.full_name from t_manifest_wo mw"
//                    + " left join user_access.t_user p on mw.deleted_user_id=p.user_id where mw.employee_id='" + ub.getEmployeeId() + "' and (flag_manifest_changed='Y' or flag_manifest_deleted='Y')";
//            try {
//                fcon.getQuery(strSQL, new RowHandler() {
//                    public boolean rowFetched(ResultMap m) {
//                        try {
//                            String inStr = "update tworkorder set wostatus='EDIT', nexttlevel=(select tlevel.level_value from tlevel inner join tuser on tlevel.level_id=tuser.level_id where tuser.employee_id=createdbyemployeeid)\n"
//                                    + " where tworkorder_pk ='"+m.getString("employee_id")+"' and wostatus in ('"+WorkOrder.APPROVED_STATUS+"','"+WorkOrder.PRINTED_STATUS+"')";
//                            wcon.executeNonQuery(inStr);
//                            inStr = "insert into tworkorderflow(tworkorder_fk,employeeid,actiondate,actiondone,level_id,granted,onbehalfemployeeid,reason,oldwostatus)\n"
//                                    + " select tworkorder_fk,'9999',now(),1,level_id,0,employeeid,'FAST: Manifest changed by "+m.getString("full_name")+" after approved! Pleas Edit WO Document!','EDIT' from tworkorderflow where tworkorder_fk  ='"+m.getString("employee_id")+"'"
//                                    + " and oldwostatus='NEW';";
//                            wcon.executeNonQuery(inStr);
//                        } catch (Exception ex) {
//                            Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        return DISCARD_RESULT;
//                    }
//                });
//            } catch (Exception e) {
//                logger.LoggerClass.logError(ValidateLogin.class, e);
//            }
//            //Addition End
            response.sendRedirect("main.jsp");
        } else {
            response.sendRedirect("index.jsp");
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

    public static void main(String[] args) {
        /*JDBCConnector wcon = new JDBCConnector();
        JDBCConnector fcon = new JDBCConnector("fast");
        String strSQL = "Select * from t_manifest_wo where employee_id='5007' and (flag_manifest_changed='Y' or flag_manifest_deleted='Y')";
        final StringBuffer twopk = new StringBuffer();
        try {
            fcon.getQuery(strSQL, new RowHandler() {
                @Override
                public boolean rowFetched(ResultMap m) {
                    twopk.append("'").append(m.getString("tworkorder_pk")).append("',");
                    return DISCARD_RESULT;
                }
            });

            try {
                wcon.getQuery("Select tworkorder_pk,wostatus from tworkorder where tworkorder_pk in (" + twopk.substring(0, twopk.length() - 1) + ") and wostatus in ('" + WorkOrder.APPROVED_STATUS + "','" + WorkOrder.PRINTED_STATUS + "')", new RowHandler() {
                    @Override
                    public boolean rowFetched(ResultMap m) {
                        System.out.println(m.getString("tworkorder_pk") + "," + m.getString("wostatus"));
                        return DISCARD_RESULT;
                    }
                });
            } catch (SQLException ex) {
                Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            logger.LoggerClass.logError(ValidateLogin.class, e);
        }*/

    }
}
