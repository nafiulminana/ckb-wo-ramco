package com.ckb.wo.client.listener;

import com.ckb.wo.server.service.util.*;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.client.model.VendorServiceExample;
import com.ckb.wo.client.model.Permission;
import com.ckb.wo.client.model.UserBeans;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorServiceExt;
import com.ckb.wo.server.service.util.PermissionLocalServiceUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class VendorServiceFileUploadRateServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger("VendorServiceFileUploadRateServlet");
    private static final String SESSION_NAME = "uploadVendorService";
    private static final String SESSION_MESSAGE = "message";
    private static final String SESSION_SUCCESS = "success";

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
        List<VendorServiceExt> vendorSerives = new ArrayList<>();
        List<String> lVendorPk = new ArrayList<String>();
        String message = null;
        Date today = new Date();
        session.setAttribute(SESSION_SUCCESS, null);
        if (request.getParameter("template") != null && !request.getParameter("template").isEmpty()) {
            try {
                if (!PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), Permission.MANAGE_RATE)) {
                    throw new SecurityException("user doesn't have permission to upload rate");
                } 

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=templateRate.xls");

                String logFileLocation = "/home/wo_uploaded_file/templateRate.xls";
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

            } catch (SecurityException | IOException e) {
                message = e.getMessage();
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }  
            return;
        }
        
        /**
         * Validation :
         * 1. 1 File 1 Vendor Id => error all file
         * 2. Vendor Id Only on DB => error all file
         * 3. Valid to > valid from && valid to > current date => set read row and not save to db
         * 4. Currency, Rate, Service Name, Service Mode, Service Mode Detail, Order, Delivery Term, Origin, destination != null or 0 => set red row and not save to db.
         * 5. File Only on .xls atau .xlsx
         */
        
        // if data uploaded
        if(isMultipart) {
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
                
                session.setAttribute(SESSION_NAME, null);
                
                // handling file and log
                File file = null;
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                servletFileUpload.setFileSizeMax(5242880);
                List<FileItem> lFileItem = servletFileUpload.parseRequest(request);
                if (lFileItem != null) {
                    for (FileItem fileItem : lFileItem) {
                        file = new File(fileItem.getName());
                        
                        // file not in .xlsx
                        if( fileItem.getName().contains(".xlsx")){
                            throw new Exception("File must be in .xls");
                        }
                        
                        if( ! fileItem.getName().contains(".xls") ){
                            throw new Exception("File must be in .xls");
                        }
                        
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
                 
                // save data from file to session
                if(file != null){
                    Workbook wrk1 = Workbook.getWorkbook(file.getAbsoluteFile());
                    Sheet sheet1 = wrk1.getSheet(0);
                    int rows = sheet1.getRows();
                    
                    for (int i = 1; i < rows; i++) { 
                        String tVendorFk = sheet1.getCell(0, i).getContents();
                        if(tVendorFk != null && ! tVendorFk.equalsIgnoreCase("")){
                            try {
                                VendorServiceExt item = new VendorServiceExt(); 
                                if( ! lVendorPk.contains(tVendorFk)){
                                    lVendorPk.add(tVendorFk);
                                }                               
                                
                                item.setTvendorFk(Long.parseLong(sheet1.getCell(0, i).getContents()));
                                Vendor dataVendor = VendorLocalServiceUtil.getVendorByPrimaryKey(item.getTvendorFk());
                                item.setVendor(dataVendor);
                                
                                item.setTservicetypeFk(sheet1.getCell(1, i).getContents() != null && ! sheet1.getCell(1, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(1, i).getContents()) : null); 
                                if (item.getTservicetypeFk() != null ) item.setServiceType(ServiceTypeLocalServiceUtil.getServiceTypeByPrimaryKey(item.getTservicetypeFk()));
                                
                                item.setTservicemodeFk(sheet1.getCell(2, i).getContents() != null && ! sheet1.getCell(2, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(2, i).getContents()) : null); 
                                if (item.getTservicemodeFk() != null ) item.setServiceMode(ServiceModeLocalServiceUtil.getServiceModeByPrimaryKey(item.getTservicemodeFk()));
                                
                                item.setTservicemodedetailFk(sheet1.getCell(3, i).getContents() != null && ! sheet1.getCell(3, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(3, i).getContents()) : null); 
                                if (item.getTservicemodedetailFk() != null ) item.setServiceModeDetail(ServiceModeDetailLocalServiceUtil.getServiceModeDetailByPrimaryKey(item.getTservicemodedetailFk()));
                                
                                item.setTorderFk(sheet1.getCell(4, i).getContents() != null && ! sheet1.getCell(4, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(4, i).getContents()) : null); 
                                if (item.getTorderFk() != null ) item.setOrder(OrderLocalServiceUtil.getOrderByPrimaryKey(item.getTorderFk()));
                                
                                item.setTdeliverytermFk(sheet1.getCell(5, i).getContents() != null && ! sheet1.getCell(5, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(5, i).getContents()) : null); 
                                if (item.getTdeliverytermFk() != null ) item.setDeliveryTerm(DeliveryTermLocalServiceUtil.getDeliveryTermByPrimaryKey(item.getTdeliverytermFk()));
                                
                                item.setOrigintlocationFk(sheet1.getCell(6, i).getContents() != null && ! sheet1.getCell(6, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(6, i).getContents()) : 0); 
                                if (item.getOrigintlocationFk() != null ) item.setOriginLocation(LocationLocalServiceUtil.getLocationByPrimaryKey(item.getOrigintlocationFk()));
                                
                                item.setDestinationtlocationFk(sheet1.getCell(7, i).getContents() != null && ! sheet1.getCell(7, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(7, i).getContents()) : null); 
                                if (item.getDestinationtlocationFk() != null ) item.setDestinationLocation(LocationLocalServiceUtil.getLocationByPrimaryKey(item.getDestinationtlocationFk()));
                                
                                item.setRate( sheet1.getCell(8, i).getContents() != null && ! sheet1.getCell(8, i).getContents().equals("") ? new BigDecimal(sheet1.getCell(8, i).getContents().replaceAll(",", "")) : null); 
                                
                                if(sheet1.getCell(9, i).getContents() != null && sheet1.getCell(9, i).getContents() != ""){
                                    DateCell validfrom = (DateCell) sheet1.getCell(9, i);
                                    item.setValidfrom(validfrom.getDate());
                                }
                                 
                                if(sheet1.getCell(10, i).getContents() != null && sheet1.getCell(10, i).getContents() != ""){
                                    DateCell validTo = (DateCell) sheet1.getCell(10, i);
                                    item.setValidto(validTo.getDate()); 
                                }
                                
                                
                                item.setRefagreementno(sheet1.getCell(7, i).getContents() != null && ! sheet1.getCell(7, i).getContents().equals("") ? sheet1.getCell(11, i).getContents() : "");
                                
                                if(sheet1.getCell(12, i).getContents() != null && sheet1.getCell(12, i).getContents() != ""){
                                    DateCell refagrementDate = (DateCell) sheet1.getCell(12, i);
                                    item.setRefagreementdate(refagrementDate.getDate());   
                                } 
                                
                                item.setTcurrencyFk(sheet1.getCell(13, i).getContents() != null && ! sheet1.getCell(13, i).getContents().equals("") ? Long.parseLong(sheet1.getCell(13, i).getContents()) : null);
                                if (item.getTcurrencyFk() != null ) item.setCurrency(CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(item.getTcurrencyFk()));
                                
                                item.setWeightfrom(sheet1.getCell(14, i).getContents() != null && ! sheet1.getCell(14, i).getContents().equals("") ? new BigDecimal(sheet1.getCell(14, i).getContents()) : null);
                                item.setWeightto(sheet1.getCell(15, i).getContents() != null && ! sheet1.getCell(15, i).getContents().equals("") ? new BigDecimal(sheet1.getCell(15, i).getContents()) : null);
                                item.setMinimumweight(sheet1.getCell(16, i).getContents() != null && ! sheet1.getCell(16, i).getContents().equals("") ? new BigDecimal(sheet1.getCell(16, i).getContents()) : null);
                                item.setRemarks(sheet1.getCell(17, i).getContents() != null && ! sheet1.getCell(17, i).getContents().equals("") ? sheet1.getCell(17, i).getContents() : "");
                                item.setIsFlatRate(sheet1.getCell(18, i).getContents() != null && ! sheet1.getCell(18, i).getContents().equals("") ? Boolean.valueOf(sheet1.getCell(18, i).getContents()) : null);
                                item.setIsValidData(true);
                                
                                // validation data
                                if(dataVendor == null){
                                    session.setAttribute(SESSION_NAME, null);
                                    throw new Exception("Vendor Id "+item.getTvendorFk()+" Not Exist In database!!");
                                }
                                
                                if(lVendorPk.size() > 1){
                                    session.setAttribute(SESSION_NAME, null);
                                    String vendorId = "";
                                    for(String val : lVendorPk) {
                                        vendorId += val + ",";
                                    }
                                    throw new Exception("Vendor Id Count > 1 is :" + vendorId);
                                }
                               
                                if(item.getValidto() != null && item.getValidfrom() != null && (item.getValidto().compareTo(item.getValidfrom()) <= 0 || item.getValidto().compareTo(today) <= 0)){
                                    item.setIsValidData(false);
                                }
                                
                                if(item.getCurrency() == null || item.getRate() == null || item.getTservicetypeFk() == null ||
                                    item.getTservicemodeFk() == null || item.getTservicemodedetailFk() == null || item.getTorderFk() == null ||
                                    item.getOrigintlocationFk() == null || item.getDestinationtlocationFk() == null ||
                                    item.getDeliveryTerm() == null )
                                {
                                    item.setIsValidData(false);
                                }
                                
                                vendorSerives.add(item); 
                            } catch (Exception e) { 
                                throw e;
                            } 
                        }
                    }
                    session.setAttribute(SESSION_NAME, vendorSerives);
                }
            } catch (Exception e) {
                message = e.getMessage();
                LOG.error(e.getMessage(), e);
            } 
        }
        
        if(request.getParameter("SaveData") != null){
            vendorSerives  = (List<VendorServiceExt>) session.getAttribute(SESSION_NAME);
            if(vendorSerives != null){
                String messageSuccess = "";
                int countAll = vendorSerives.size();
                int countSave = 0, countNotSave = 0;
                for(VendorServiceExt val : vendorSerives){
                    if(val.getIsValidData()){
                        // if exist update is_enabled to false
                        VendorServiceExample example =  new VendorServiceExample();
                        VendorServiceExample.Criteria criteria = example.createCriteria();
                        criteria.andTvendorFkEqualTo(val.getTvendorFk())
                                .andTservicetypeFkEqualTo(val.getTservicetypeFk())
                                .andTservicemodeFkEqualTo(val.getTservicemodeFk())
                                .andTservicemodedetailFkEqualTo(val.getTservicemodedetailFk())
                                .andTorderFkEqualTo(val.getTorderFk())
                                .andTdeliverytermFkEqualTo(val.getTdeliverytermFk())
                                .andOrigintlocationFkEqualTo(val.getOrigintlocationFk())
                                .andDestinationtlocationFkEqualTo(val.getDestinationtlocationFk())
                                .andRemarksLike(val.getRemarks())
                                .andRateEqualTo(val.getRate())
                                .andEnabled(true);
                        List<VendorService> lVenService = VendorServiceLocalServiceUtil.getVendorServiceByExample(example);
                        for(VendorService valDb : lVenService){
                            valDb.setEnabled(false);
                            VendorServiceLocalServiceUtil.updateVendorService(valDb);
                        }

                        // insert data
                        VendorServiceLocalServiceUtil.insertVendorService(val);
                        countSave++;
                    } else {
                        countNotSave++;
                    }
                }
                session.setAttribute(SESSION_NAME, null);
                session.setAttribute(SESSION_MESSAGE, null);
                if(countAll > 0) {
                    session.setAttribute(SESSION_SUCCESS, "Save data success. <br/>Count All Data : " + countAll + " <br/>Save To Database : " + countSave + " <br/>Not Save : " + countNotSave);
                }
            } 
        }
        
        session.setAttribute(SESSION_MESSAGE, message);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vendorservice/vendorserviceuploadratebatch.jsp");
        rd.forward(request, response);
    }
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

//    public static void main(String[] args) {
//        File file = new File("c:/convert.xls");
//        IVendorServiceDataConverterService dataconverter = (IVendorServiceDataConverterService) BeanFactory.getBean("vendorservicedataconverterService");
//        dataconverter.parseExcelFile(file);
//    }
}
