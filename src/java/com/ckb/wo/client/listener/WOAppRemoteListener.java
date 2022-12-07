/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.report.ReportHelper;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Currency;
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
public class WOAppRemoteListener extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public final int OUTSTANDING = 0, DELEGATED = 1, CURRENCY = 2;
    public final String APPROVE = "approve";
    public final String EDIT = "edit";
    public final String CANCEL = "cancel";

    protected void returnObject(Object retObj, OutputStream out) throws IOException {
        ObjectOutputStream outputToOther = null;
        outputToOther = new ObjectOutputStream(out);
        outputToOther.writeObject(retObj);

        outputToOther.flush();
        outputToOther.close();


        outputToOther.close();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/octet-stream");
        try {
            ObjectInputStream inputFromOther = null;
            JSONObject obj = null;

            try {
                // get an input stream from the applet
                inputFromOther = new ObjectInputStream(request.getInputStream());
                // read the serialized student data from applet
                obj = (JSONObject) inputFromOther.readObject();
                Object retObj = null;
                inputFromOther.close();

                int start = 0, limit = 0, total = 0;
                if (obj.getString("action").equals("list")) {
                    start = obj.getInt("start");
                    limit = obj.getInt("limit");
                } else if (obj.getString("action").equals(APPROVE) || obj.getString("action").equals(EDIT) || obj.getString("action").equals(CANCEL)) {
                    boolean ret = false;
                    if (obj.getString("action").equals(APPROVE)) {
                        try {
                            WorkOrderLocalServiceUtil.approveWorkOrder(obj.getLong("workOrderPk"), null, obj.getString("employeeId"));
                            ret = true;
                        } catch (Exception e) {
                            logger.LoggerClass.logError(WOAppRemoteListener.class, e);
                        }
                    } else if (obj.getString("action").equals(EDIT)) {
                        try {
                            WorkOrderLocalServiceUtil.editWorkOrder(obj.getLong("workOrderPk"), obj.getString("reason"), obj.getString("employeeId"));
                            ret = true;
                        } catch (Exception e) {
                            logger.LoggerClass.logError(WOAppRemoteListener.class, e);
                        }
                    } else if (obj.getString("action").equals(CANCEL)) {
                        try {
                            BigDecimal cancellationfees = null;
                            try {
                                cancellationfees = new BigDecimal(obj.getString("cancellationfee"));
                            } catch (Exception e) {
                                logger.LoggerClass.logError(e);
                            }
                            WorkOrderLocalServiceUtil.cancelWorkOrder(obj.getLong("workOrderPk"), obj.getString("reason"), cancellationfees, obj.getLong("currencypk"), obj.getString("employeeId"));
                            ret = true;
                        } catch (Exception e) {
                            logger.LoggerClass.logError(WOAppRemoteListener.class, e);
                        }
                    }
                    returnObject(ret, response.getOutputStream());
                    return;
                }

                List list = null;
                try {
                    switch (obj.getInt("type")) {
                        case OUTSTANDING:
                            total = WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithoutGrant(obj.get("employeeId").toString());
                            if (obj.getString("action").equals("list")) {
                                list = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant(obj.get("employeeId").toString(), start + "," + (limit > total ? total : limit), null);
                            }
                            break;
                        case DELEGATED:
                            total = WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithGrant(obj.get("employeeId").toString());
                            if (obj.getString("action").equals("list")) {
                                list = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithGrant(obj.get("employeeId").toString(), start + "," + (limit > total ? total : limit), null);
                            }
                            break;
                        case CURRENCY:
                            total = CurrencyLocalServiceUtil.countCurrency();
                            if (obj.getString("action").equals("list")) {
                                list = CurrencyLocalServiceUtil.getCurrency();
                            }
                            break;
                    }

                } catch (Exception e) {
                    logger.LoggerClass.logError(WOAppRemoteListener.class, e);
                }

                if (obj.getString("action").equals("list")) {
                    JSONArray data = new JSONArray();
                    try {
                        if (list != null) {
                            if (obj.getInt("type") == CURRENCY) {
                                Iterator<Currency> itw = list.iterator();
                                while (itw.hasNext()) {
                                    data.add(JSONObject.fromObject(itw.next()));
                                }
                            } else {
                                Iterator<WorkOrder> itw = list.iterator();
                                while (itw.hasNext()) {
                                    WorkOrder o = itw.next();
                                    o.setCreatorUser(UserLocalServiceUtil.getUserByPrimaryKey(o.getCreatedbyemployeeid()));
                                    JSONObject json = JSONObject.fromObject(o);
                                    json.put("type", (o.getServiceType() != null ? o.getServiceType().getServicename() : "") + " " + (o.getOrder() != null ? o.getOrder().getOrdername() : ""));
                                    Long order = o.getTorderFk();
                                    order = order == null ? 0 : order;
                                    json.put("charges", order != 1 || o.getAdhoc() ? WorkOrderListener.getCharges(o.getWorkOrderServiceModeDetail()) : String.format("%s %,.2f", o.getCurrency().getCurrencycode(), o.getCharge()));
                                    json.put("sarana", ReportHelper.groupConcate(o.getWorkOrderServiceModeDetail(), o.getPpn(), o.getPph()));
                                    json.put("isValidateEnabled", (!o.getWostatus().equals(WorkOrder.APPROVED_STATUS) && order == 1 && !o.getAdhoc()));
                                    data.add(json);
                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.LoggerClass.logError(WOAppRemoteListener.class, e);
                    }
                    Map res = new HashMap();
                    res.put("total", total);
                    res.put("data", data);
                    res.put("prev", start - limit);
                    res.put("next", start + limit);
                    retObj = JSONObject.fromObject(res);
                } else if (obj.getString("action").endsWith("count")) {
                    retObj = total;
                }
                returnObject(retObj, response.getOutputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }

        } finally {
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
