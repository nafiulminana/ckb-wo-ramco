package com.ckb.wo.client.listener;

import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CheckingServiceServlet extends GenericMasterTableServlet {

    private static final String LIST_JSP = "/WEB-INF/jsp/checkingservice/checkingservice.jsp";

    final static Logger LOG = Logger.getLogger(CheckingServiceServlet.class);

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
        LOG.info("Checking Service Servlet");
        String searchType = request.getParameter("searchType");
        LOG.info(searchType);
        String reffNo = request.getParameter("reffNo");
        LOG.info(reffNo);
        if (reffNo != null && !reffNo.isEmpty()) {
            String[] reffNoArray = reffNo.split(",");
            List<String> reffNoList = new ArrayList<>();
            for (String reffNoStr : reffNoArray) {
                reffNoList.add(reffNoStr.trim());
            }
            if (!reffNoList.isEmpty()) {
                switch (searchType) {
                    case "MAN":
                        request.setAttribute("woDaManStatuses", WorkOrderLocalServiceUtil.getWoDaManStatusByMan(reffNoList));
                        request.setAttribute("t", "false");
                        break;
                    case "DA":
                        request.setAttribute("woDaManStatuses", WorkOrderLocalServiceUtil.getWoDaManStatusByDa(reffNoList));
                        request.setAttribute("t", "true");
                        break;
                    case "WO":
                        request.setAttribute("woDaManStatuses", WorkOrderLocalServiceUtil.getWoDaManStatusByWo(reffNoList));
                        request.setAttribute("t", "false");
                        break;
                }
            } else {
                request.setAttribute("woDaManStatuses", new ArrayList<>());
            }
        }
        forward(request, response, LIST_JSP);
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

}
