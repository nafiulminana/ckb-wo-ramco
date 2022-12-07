package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.ManifestWeightVolumeDetail;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.VendorServiceExt;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderManifest;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.ManifestDALocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
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

public class WorkOrderAppList extends HttpServlet {

    final static Logger LOG = Logger.getLogger(WorkOrderAppList.class);

    public String sendReminder(Long twopk) {
        try {
            return WorkOrderLocalServiceUtil.sendReminder(twopk);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return e.getMessage();
        }
    }

    public static String validate(Long pk) {
        WorkOrder wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(pk);
        return validate(wo, false);
    }

    public static String validate(WorkOrder wo, boolean update) {
        String result = "";
        BigDecimal vTotVol = BigDecimal.ZERO;
        BigDecimal vTotWeight = BigDecimal.ZERO;
        Iterator<WorkOrderManifest> it = wo.getWorkOrderManifest().iterator();
        while (it.hasNext()) {
            WorkOrderManifest wman = it.next();
            ManifestWeightVolumeDetail mwv = ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(wman.getManifestNo());
            vTotVol = vTotVol.add(mwv.getVolume());
            vTotWeight = vTotWeight.add(mwv.getWeight());
        }
        boolean valid = true;
        valid = vTotVol.compareTo(wo.getTotalvolume()) == 0 && vTotWeight.compareTo(wo.getTotalweight()) == 0;
        if (!valid) {
            result = "Work Order is not valid!\n";
            result += "Changes:\n";
            result += "Total Weight: " + String.format("%.3f", wo.getTotalweight()) + " -> " + String.format("%.3f", vTotWeight) + "\n";
            result += "Total Volume: " + String.format("%.3f", wo.getTotalvolume()) + " -> " + String.format("%.3f", vTotVol) + "\n";
//            wo.setTotalvolume(vTotVol);
//            wo.setTotalweight(vTotWeight);
            try {
                VendorServiceExt vs = (VendorServiceExt) VendorServiceLocalServiceUtil.getVendorServiceByAttributeForChargeableWeight(
                        wo.getTvendorFk(),
                        wo.getTservicemodeFk(),
                        wo.getExecutiondate(),
                        wo.getOrigintlocationFk(),
                        wo.getDestinationtlocationFk(),
                        vTotWeight,//wo.getTotalweight(),
                        vTotVol,//wo.getTotalvolume(),
                        wo.getWorkOrderServiceModeDetail().get(0).getTservicemodedetailFk());
                if (vs != null) {
                    result += "Charge: " + String.format("%.2f", wo.getCharge()) + " -> ";
//                    wo.setCharge(vs.getRate());

                    //String detCharge = String.format("@%s %,.2f/Kg x %,fKg", vs.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vs.getTvendorservicePk()).getRate(), vs.getWeight());
                    String detCharge;
                    if (vs.getIsFlatRate()) {
                        detCharge = String.format("@%s %,.2f minimum charge, %,fKg", vs.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vs.getTvendorservicePk()).getRate(), vs.getWeight());
                    } else {
                        detCharge = String.format("@%s %,.2f/Kg x %,fKg", vs.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vs.getTvendorservicePk()).getRate(), vs.getWeight());
                    }
//
//                    String detCharge = String.format("@%s %,.2f/Kg", vs.getCurrency().getCurrencycode(), VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKey(vs.getTvendorservicePk()).getRate());
                    result += String.format("%.3f (%s)", vs.getRate(), detCharge) + "\n";
                    if (update) {
                        wo.getWorkOrderServiceModeDetail().get(0).setSmdcharge(vs.getRate());
                        wo.getWorkOrderServiceModeDetail().get(0).setSmdremarks("Regular rate for " + vs.getServiceModeDetail().getSmdname() + detCharge);
//                    WorkOrderLocalServiceUtil.updateWorkOrderAfterChargeableWeightValidation(wo, ((UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo")).getEmployeeId());
                    }
                } else {
                    result = "Error validating rate! chargeable weight is out of range!";
                }
            } catch (Exception e) {
                logger.LoggerClass.logError(WorkOrderAppList.class, e);
                result = e.getMessage();
            }
        } else {
            result = "Work Order already valid! No changes needed!";
        }

        return result;
    }

    public JSONObject getWO(Long pk) {
        WorkOrder wo = WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(pk);
        User user = wo.getCreatorUser();
        Map<String, Object> result = new HashMap<>();
        result.put("nomorWo", wo.getNomorwo());
        result.put("creatorName", user.getFirstName() + (user.getMiddleName() != null ? " " + user.getMiddleName() : "") + (user.getLastName() != null ? " " + user.getLastName() : ""));
        return JSONObject.fromObject(result);
    }

    public JSONArray getCurrency() {
        UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
        if (ub == null) {
            return JSONArray.fromObject("{\"errorMessage\": \"No Authorization\"}");
        }
        List<Currency> currencies = CurrencyLocalServiceUtil.getCurrency();
        return JSONArray.fromObject(currencies);
    }

    public boolean setGrant(Date from, Date to) {
        UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
        if (ub == null) {
            return false;
        }
        User u = UserLocalServiceUtil.getUserByPrimaryKey(ub.getEmployeeId());
        if (u == null) {
            return false;
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            u.setGrantfrom(from);
            u.setGrantto(to);
        } catch (Exception e) {
            return false;
        }
        UserLocalServiceUtil.updateUser(u);
        return true;
    }

    public String getGrant() {
        UserBeans ub = (UserBeans) WebContextFactory.get().getSession().getAttribute("loginInfo");
        if (ub == null) {
            return "{from:'',to:''}";
        }
        User u = UserLocalServiceUtil.getUserByPrimaryKey(ub.getEmployeeId());
        return String.format("{from:'%tF', to:'%tF'}", u.getGrantfrom(), u.getGrantto());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");
            if (ub == null) {
                return;
            }

            int totalWO = 0;
            String wostatus = null, search = null;
            if (request.getParameter("wo") != null) {
                search = request.getParameter("wo");
                search = search.isEmpty() ? null : search;
            }
            if (request.getParameter("st") != null) {
                wostatus = request.getParameter("st");
                wostatus = wostatus.isEmpty() ? null : wostatus;
            }

            switch (request.getParameter("t")) {
                case "oapp":
                    // totalWO = WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithoutGrant(ub.getEmployeeId());
                    totalWO = 1000;
                    break;
                case "oappg":
                    totalWO = WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithGrant(ub.getEmployeeId());
                    break;
                case "podapp":
                    totalWO = WorkOrderLocalServiceUtil.countWorkOrderUsingPODToBeApprovedWithGrant(ub.getEmployeeId());
                    break;
                case "podoappg":
                    totalWO = WorkOrderLocalServiceUtil.countWorkOrderUsingPODToBeApprovedWithGrant(ub.getEmployeeId());
                    break;
                case "appd":
                    totalWO = WorkOrderLocalServiceUtil.countWorkOrderApprovedByThisUser(ub.getEmployeeId(), search);
                    break;
                default:
                    break;
            }
            int start = 0;
            int limit = 10;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
            List<WorkOrder> lWo = null;
            switch (request.getParameter("t")) {
                case "oapp":
                    lWo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant(ub.getEmployeeId(), start + "," + limit, null);
//                lWo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant(ub.getEmployeeId(), null, null);
                    break;
                case "oappg":
                    lWo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithGrant(ub.getEmployeeId(), start + "," + limit, null);
//                lWo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithGrant(ub.getEmployeeId(), null, null);
                    break;
                case "podoapp":
                    lWo = WorkOrderLocalServiceUtil.getWorkOrderUsingPODToBeApprovedWithoutGrant(ub.getEmployeeId(), start + "," + limit, null);
//                lWo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithoutGrant(ub.getEmployeeId(), null, null);
                    break;
                case "podoappg":
                    lWo = WorkOrderLocalServiceUtil.getWorkOrderUsingPODToBeApprovedWithGrant(ub.getEmployeeId(), start + "," + limit, null);
//                lWo = WorkOrderLocalServiceUtil.getWorkOrderToBeApprovedWithGrant(ub.getEmployeeId(), null, null);
                    break;
                case "appd":
                    lWo = WorkOrderLocalServiceUtil.getWorkOrderApprovedByThisUser(ub.getEmployeeId(), search, wostatus, start + "," + limit, null);
//                lWo = WorkOrderLocalServiceUtil.getWorkOrderApprovedByThisUser(ub.getEmployeeId(), null, null);
                    break;
                default:
                    break;
            }
            Map map = new HashMap();
            map.put("total", totalWO);
            JSONArray data = new JSONArray();
            if (lWo != null) {
                Iterator<WorkOrder> itw = lWo.iterator();
                while (itw.hasNext()) {
                    WorkOrder o = itw.next();
                    JSONObject obj = JSONObject.fromObject(o);
                    obj.put("type", (o.getServiceType() != null ? o.getServiceType().getServicename() : "") + " " + (o.getOrder() != null ? o.getOrder().getOrdername() : ""));
                    Long order = o.getTorderFk();
                    order = order == null ? 0 : order;
                    obj.put("charges", /*order != 1 || o.getAdhoc() ?*/ WorkOrderListener.getCharges(o.getWorkOrderServiceModeDetail())/* : String.format("%s %,.2f", o.getCurrency().getCurrencycode(), o.getCharge())*/);
                    obj.put("action", "<a href='#' onclick='viewWo(" + o.getTworkorderPk() + "," + (o.getTservicetypeFk() == 2) + ")'>View</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='viewNotes(" + o.getTworkorderPk() + ")'>Notes</a>");
                    obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"" + getServletContext().getContextPath() + "/list/workorder_history.jsp?wo=" + o.getTworkorderPk() + "&t=" + (o.getTservicetypeFk() == 2) + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>History</a>");
                    if (o.getWostatus().equals(WorkOrder.APPROVED_STATUS)) {
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='SeyLightbox.display(\"../list/print.do?wo=" + o.getTworkorderPk() + "&t=" + (o.getTservicetypeFk() == 2) + "&TB_iframe=1&width=700&height=\"+(GetHeight()-90))'>Print</a>");
                    }
                    if (!o.getWostatus().equals(WorkOrder.APPROVED_STATUS) && order == 1 && !o.getAdhoc()) {
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='validate(" + o.getTworkorderPk() + ")'>Validate</a>");
                    }
                    if (!o.getWostatus().equals(WorkOrder.RECEIVED_STATUS) || !o.getWostatus().equals(WorkOrder.CANCELLED_STATUS)) {
                        obj.put("action", obj.get("action") + " | <a href='#' onclick='cancel(" + o.getTworkorderPk() + ")'>Cancel</a>");
                    }
                    data.add(obj);
                }
            }
            map.put("data", data);
            JSONObject obj = JSONObject.fromObject(map);
            out.print(obj);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
