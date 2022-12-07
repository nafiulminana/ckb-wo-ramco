package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.server.service.constant.WOConstant;
import com.ckb.wo.server.service.util.CurrencyLocalServiceUtil;
import com.ckb.wo.server.service.util.DeliveryTermLocalServiceUtil;
import com.ckb.wo.server.service.util.LocationLocalServiceUtil;
import com.ckb.wo.server.service.util.OrderLocalServiceUtil;
import com.ckb.wo.server.service.util.PagingUtil;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import com.ckb.wo.server.service.util.ServiceModeDetailLocalServiceUtil;
import com.ckb.wo.server.service.util.ServiceModeLocalServiceUtil;
import com.ckb.wo.server.service.util.ServiceTypeLocalServiceUtil;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class VendorServiceServlet extends GenericMasterTableServlet {

    private static final String LIST_JSP = "/WEB-INF/jsp/vendorservice/vendorservice.jsp";
    private static final String EDIT_JSP = "/WEB-INF/jsp/vendorservice/vendorserviceedit.jsp";
    private static final String ADD_JSP = "/WEB-INF/jsp/vendorservice/vendorserviceadd.jsp";
    private static final String LIST_JSP_READ_ONLY = "/WEB-INF/jsp/vendorservice/vendorservicereadonly.jsp";
    private static final String VENDORSERVICESERVLET = "vendorservice";

    final static Logger LOG = Logger.getLogger(VendorServiceServlet.class);

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

        UserBeans ub = (UserBeans) request.getSession().getAttribute("loginInfo");

        if (request.getParameter(WOConstant.ACT) == null) {

            String pageNumber = null;
            pageNumber = getPageNumber(request, response, "vendorservicetable");
            String orderBy = getOrderBy(request, response, "vendorservicetable");
            if (orderBy == null) {
                orderBy = "vendorname asc";
            }

            VendorService vservparam = new VendorService();
            Vendor vendorparam = new Vendor();
            vendorparam.setVendorname(request.getParameter("vendorname"));
            vservparam.setVendor(new Vendor());
            try {
                vservparam.setOrigintlocationFk(Long.parseLong(request.getParameter("origintlocationFk")));
            } catch (NumberFormatException e) {
            }
            try {
                vservparam.setDestinationtlocationFk(Long.parseLong(request.getParameter("destinationtlocationFk")));
            } catch (NumberFormatException e) {
            }
            try {
                vservparam.setTservicemodedetailFk(Long.parseLong(request.getParameter("tservicemodedetail_fk")));
            } catch (NumberFormatException e) {
            }

            String vendorname = request.getParameter("vendorname");
            if (vendorname == null) {
                vendorname = WOConstant.EMPTY_STRING;
            }
            String limitClauseBy = PagingUtil.getLimitClause(pageNumber);

            int count = VendorServiceLocalServiceUtil.countByVendorNameAndOtherRateAttribute(vendorname, vservparam.getOrigintlocationFk(), vservparam.getDestinationtlocationFk(), vservparam.getTservicemodedetailFk());
            List<VendorService> vendorService = VendorServiceLocalServiceUtil.getVendorServiceByVendorNameAndOtherRateAttributeWithJoin(vendorname, vservparam.getOrigintlocationFk(), vservparam.getDestinationtlocationFk(), vservparam.getTservicemodedetailFk(), limitClauseBy, orderBy);

            request.setAttribute("count", count);
            request.setAttribute("vendorservice", vendorService);
            request.setAttribute("pagesize", String.valueOf(WOConstant.RECORD_PER_PAGE));
            request.setAttribute("servicemodedetail", ServiceModeDetailLocalServiceUtil.getServiceModeDetail());
            request.setAttribute("vendorserviceparam", vservparam);
            request.setAttribute("location", LocationLocalServiceUtil.getLocation());
            boolean isManageRateAllowed = false;
            if (ub != null) {
//            boolean isManageRateAllowed = PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE);
                isManageRateAllowed = PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE);
            }
            if (isManageRateAllowed) {
                forward(request, response, LIST_JSP);
            } else {
//                if (vservparam.getOrigintlocationFk() == null || vservparam.getDestinationtlocationFk() == null) {
//                    request.setAttribute("count", 0);
//                    request.setAttribute("vendorservice", null);
//                }
                forward(request, response, LIST_JSP_READ_ONLY);
            }

        } else if (WOConstant.EDIT.equalsIgnoreCase(request.getParameter(WOConstant.ACT))) {
            LOG.info("Editing vendor service = " + request.getParameter("tvendorservicePk"));
            VendorService vendorService = null;
            try {
                vendorService = VendorServiceLocalServiceUtil.getVendorServiceByPrimaryKeyWithJoin(Long.parseLong(request.getParameter("tvendorservicePk")));
            } catch (Exception e) {
            }
            request.setAttribute("vendorservice", vendorService);
            fillOtherComboBox(request, response);
            forward(request, response, EDIT_JSP);
        } else if (WOConstant.ADD.equalsIgnoreCase(request.getParameter(WOConstant.ACT))) {
            fillOtherComboBox(request, response);
            forward(request, response, ADD_JSP);
        } else if (WOConstant.DELETE.equalsIgnoreCase(request.getParameter(WOConstant.DELETE))) {
            response.sendRedirect(VENDORSERVICESERVLET);
        } else if (WOConstant.SAVE.equalsIgnoreCase(WOConstant.SAVE)) {
            VendorService vendorService = fillBeanFromRequestParam(request, response);
            String errormessage = validate(vendorService);
            // save vendor
            if (request.getParameter("tvendorservicePk") == null) {
                LOG.info("Saving vendor service");
                // new WO
                if (errormessage.length() == 0) {
                    VendorServiceLocalServiceUtil.insertVendorService(vendorService);
                } else {
                    request.setAttribute("errormessage", LOG);
                    fillOtherComboBox(request, response);
                    forward(request, response, ADD_JSP);
                }
            } else {
                LOG.info("Updating vendor service");
                // update WO
                if (errormessage.length() == 0) {
                    VendorServiceLocalServiceUtil.updateVendorService(vendorService);
                } else {
                    request.setAttribute("errormessage", errormessage);
                    request.setAttribute("vendorservice", vendorService);
                    fillOtherComboBox(request, response);
                    forward(request, response, EDIT_JSP);
                }
            }
            response.sendRedirect(VENDORSERVICESERVLET + "?vendorname=" + vendorService.getVendor().getVendorname());
        }
    }

    private String validate(VendorService vendorService) {

        String openli = "<li>";
        String closeli = "</li>";
        StringBuilder sb = new StringBuilder();
        if (vendorService.getTvendorFk() == null) {
            sb.append(openli).append("vendor is required").append(closeli);
        }
        if (vendorService.getTservicetypeFk() == null) {
            sb.append(openli).append("service type is required").append(closeli);
        }
        if (vendorService.getOrigintlocationFk() == null) {
            sb.append(openli).append("origin is required").append(closeli);
        }
        if (vendorService.getDestinationtlocationFk() == null) {
            sb.append(openli).append("destination is required").append(closeli);
        }
        if (vendorService.getTservicemodeFk() == null) {
            sb.append(openli).append("servicemode is required").append(closeli);
        }
        if (vendorService.getRefagreementdate() == null) {
            sb.append(openli).append("agreement date is required").append(closeli);
        }
        if (vendorService.getRefagreementno() == null) {
            sb.append(openli).append("agreement no is required").append(closeli);
        }

        return sb.toString();
    }

    private VendorService fillBeanFromRequestParam(HttpServletRequest request, HttpServletResponse response) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        VendorService vserv = new VendorService();
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tvendorservicePk " + request.getParameter("tvendorservicePk"));
            }
            vserv.setTvendorservicePk(Long.parseLong(request.getParameter("tvendorservicePk")));
        } catch (NumberFormatException e) {
            // means null, new vendorservice
        }
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("destinationtlocationFk :" + request.getParameter("destinationtlocationFk"));
            }
            vserv.setDestinationtlocationFk(Long.parseLong(request.getParameter("destinationtlocationFk")));

        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("minimumweight :" + request.getParameter("minimumweight"));
            }
            vserv.setMinimumweight(new BigDecimal(request.getParameter("minimumweight")));
        } catch (Exception e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("origintlocationFk :" + request.getParameter("origintlocationFk"));
            }
            vserv.setOrigintlocationFk(Long.parseLong(request.getParameter("origintlocationFk")));
        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("rate :" + request.getParameter("rate"));
            }
            vserv.setRate(new BigDecimal(request.getParameter("rate")));
        } catch (Exception e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("refagreementdate :" + request.getParameter("refagreementdate"));
            }
            vserv.setRefagreementdate(sdf.parse(request.getParameter("refagreementdate")));
        } catch (ParseException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("refagreementno :" + request.getParameter("refagreementno"));
            }
            vserv.setRefagreementno(request.getParameter("refagreementno"));
        } catch (Exception e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tcurrencyFk :" + request.getParameter("tcurrencyFk"));
            }
            vserv.setTcurrencyFk(Long.parseLong(request.getParameter("tcurrencyFk")));
        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tdeliverytermFk :" + request.getParameter("tdeliverytermFk"));
            }
            vserv.setTdeliverytermFk(Long.parseLong(request.getParameter("tdeliverytermFk")));
        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("torderFk :" + request.getParameter("torderFk"));
            }
            vserv.setTorderFk(Long.parseLong(request.getParameter("torderFk")));
        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tservicemodeFk :" + request.getParameter("tservicemodeFk"));
            }
            vserv.setTservicemodeFk(Long.parseLong(request.getParameter("tservicemodeFk")));
        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tservicemodedetailFk :" + request.getParameter("tservicemodedetailFk"));
            }
            vserv.setTservicemodedetailFk(Long.parseLong(request.getParameter("tservicemodedetailFk")));
        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tservicetypeFk :" + request.getParameter("tservicetypeFk"));
            }
            vserv.setTservicetypeFk(Long.parseLong(request.getParameter("tservicetypeFk")));

        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("tvendorFk :" + request.getParameter("tvendorFk"));
            }
            vserv.setTvendorFk(Long.parseLong(request.getParameter("tvendorFk")));

        } catch (NumberFormatException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("validfrom :" + request.getParameter("validfrom"));
            }
            vserv.setValidfrom(sdf.parse(request.getParameter("validfrom")));

        } catch (ParseException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("validto :" + request.getParameter("validto"));
            }
            vserv.setValidto(sdf.parse(request.getParameter("validto")));

        } catch (ParseException e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("weightfrom :" + request.getParameter("weightfrom"));
            }
            vserv.setWeightfrom(new BigDecimal(request.getParameter("weightfrom")));

        } catch (Exception e) {
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("weightto :" + request.getParameter("weightto"));
            }
            vserv.setWeightto(new BigDecimal(request.getParameter("weightto")));
        } catch (Exception e) {
        }
        vserv.setRemarks(request.getParameter("remarks"));
        Vendor vendor = new Vendor();
        vendor.setVendorname(request.getParameter("vendorname"));
        vserv.setVendor(vendor);
        ServiceModeDetail smd = new ServiceModeDetail();
        smd.setSmdname(request.getParameter("smdcodename"));
        vserv.setServiceModeDetail(smd);
        Location origin = new Location();
        origin.setLocationname(request.getParameter("originlocationname"));
        vserv.setOriginLocation(origin);
        Location dest = new Location();
        dest.setLocationname(request.getParameter("destinationlocationname"));
        vserv.setDestinationLocation(dest);
        vserv.setEnabled(Boolean.parseBoolean(request.getParameter("enabled")));
        vserv.setIsFlatRate(false);
        return vserv;
    }

    private void fillOtherComboBox(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("servicetype", ServiceTypeLocalServiceUtil.getServiceType());
        request.setAttribute("servicemode", ServiceModeLocalServiceUtil.getServiceMode());
        request.setAttribute("order", OrderLocalServiceUtil.getOrder());
        request.setAttribute("deliveryterm", DeliveryTermLocalServiceUtil.getDeliveryTerm());
        request.setAttribute("currency", CurrencyLocalServiceUtil.getCurrency());
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
