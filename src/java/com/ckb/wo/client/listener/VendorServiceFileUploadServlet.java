package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.server.exception.SecurityException;
import com.ckb.wo.server.service.IVendorServiceDataConverterService;
import com.ckb.wo.server.service.factory.BeanFactory;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class VendorServiceFileUploadServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger("VendorServiceFileUploadServlet");

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

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        HttpSession session = request.getSession(false);

        UserBeans ub = (UserBeans) session.getAttribute("loginInfo");

        if (isMultipart) {
            try {
                if (session == null) {
                    throw new RuntimeException("session has expired. Please login again");
                }

                if (ub == null) {
                    throw new RuntimeException("Request doesn't have login info. Please login again");
                }
                if (!PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE)) {
                    throw new com.ckb.wo.server.exception.SecurityException("user doesn't have permission to upload rate");
                }

                File file = null;
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                servletFileUpload.setFileSizeMax(5242880);
                List<FileItem> lFileItem = servletFileUpload.parseRequest(request);
                if (lFileItem != null) {
                    for (FileItem fileItem : lFileItem) {
                        file = new File(fileItem.getName());
                        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                            fileOutputStream.write(fileItem.get());
                        }
                    }
                }

                Properties prop = new Properties();
                URL url = this.getClass().getClassLoader().getResource("log4j.properties");
                prop.load(new FileInputStream(new File(url.getFile())));
                String logFileLocation = prop.getProperty("log4j.appender.FILE.File");
                File logFile = new File(logFileLocation);
                if (logFile.exists()) {
                    if (logFile.delete()) {
                        logFile.createNewFile();
                    }
                }

                if (file != null) {
                    IVendorServiceDataConverterService dataconverter = (IVendorServiceDataConverterService) BeanFactory.getBean("vendorservicedataconverterService");
                    dataconverter.parseExcelFile(file);
                }
                response.sendRedirect("vendorservicefileuploadservlet");

            } catch (SecurityException | IOException | RuntimeException | FileUploadException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }

        } else {

            if (request.getParameter("logfile") != null && !request.getParameter("logfile").isEmpty()) {

                try {
                    if (!PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE)) {
                        throw new com.ckb.wo.server.exception.SecurityException("user doesn't have permission to upload rate");
                    } else {
                        response.setContentType("application/octet-stream");
                        response.setHeader("Content-Disposition", "attachment;filename=konversi.log");

                        Properties prop = new Properties();
                        URL url = this.getClass().getClassLoader().getResource("log4j.properties");
                        prop.load(new FileInputStream(new File(url.getFile())));
                        String logFileLocation = prop.getProperty("log4j.appender.FILE.File");
                        File logFile = new File(logFileLocation);
                        ServletOutputStream out;
                        byte[] outputByte;
                        try (FileInputStream fin = new FileInputStream(logFile)) {
                            out = response.getOutputStream();
                            int fileLength = (int) logFile.length();
                            outputByte = new byte[fileLength];
                            while (fin.read(outputByte, 0, fileLength) != -1) {
                                out.write(outputByte, 0, fileLength);
                            }
                        }
                        out.flush();
                        out.close();
                        if (outputByte != null) {
                            outputByte = null;
                        }
                    }
                } catch (SecurityException | IOException e) {
                    LOG.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vendorservice/vendorserviceuploadrate.jsp");
                rd.forward(request, response);
            }
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
        File file = new File("c:/convert.xls");
        IVendorServiceDataConverterService dataconverter = (IVendorServiceDataConverterService) BeanFactory.getBean("vendorservicedataconverterService");
        dataconverter.parseExcelFile(file);
    }
}
