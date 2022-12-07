package com.ckb.wo.server.service.impl;

import com.ckb.wo.server.service.IVendorServiceDataConverterService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ckb.wo.client.model.Currency;
import com.ckb.wo.client.model.CurrencyExample;
import com.ckb.wo.client.model.DeliveryTerm;
import com.ckb.wo.client.model.DeliveryTermExample;
import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.LocationExample;
import com.ckb.wo.client.model.Order;
import com.ckb.wo.client.model.OrderExample;
import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.ServiceModeDetailExample;
import com.ckb.wo.client.model.ServiceType;
import com.ckb.wo.client.model.ServiceTypeExample;
import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorExample;
import com.ckb.wo.client.model.VendorService;
import com.ckb.wo.server.dao.CurrencyDAO;
import com.ckb.wo.server.dao.DeliveryTermDAO;
import com.ckb.wo.server.dao.LocationDAO;
import com.ckb.wo.server.dao.OrderDAO;
import com.ckb.wo.server.dao.ServiceModeDetailDAO;
import com.ckb.wo.server.dao.ServiceTypeDAO;
import com.ckb.wo.server.dao.VendorDAO;
import com.ckb.wo.server.exception.ValidationException;
import com.ckb.wo.server.service.IVendorService;
import com.ckb.wo.server.service.constant.WOConstant;
import com.ckb.wo.server.service.util.VendorServiceLocalServiceUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;

public class VendorServiceDataConverterServiceImpl implements IVendorServiceDataConverterService {

//    private static final Log log = LogFactory.getLog("com.ckb.wo.server.service.impl.VendorServiceDataConverterServiceImpl");
    private static final Log log = LogFactory.getLog(VendorServiceDataConverterServiceImpl.class);
    private VendorDAO vendorDao;
    private ServiceTypeDAO servicetypeDao;
    private ServiceModeDetailDAO servicemodedetailDao;
    private DeliveryTermDAO deliverytermDao;
    private CurrencyDAO currencyDao;
    private OrderDAO orderDao;
    private LocationDAO locationDao;
    private IVendorService vendorService;
    private BigDecimal maxWeightTo = new BigDecimal(99999999L);
    private String DASH = "-";
    private BigDecimal kgBasisDivider = new BigDecimal(1000);

    public VendorServiceDataConverterServiceImpl(VendorDAO vendorDao, ServiceTypeDAO servicetypeDao,
            ServiceModeDetailDAO servicemodedetailDao, DeliveryTermDAO deliverytermDao, CurrencyDAO currencyDao,
            OrderDAO orderDao, LocationDAO locationDao, IVendorService vendorService) {
        this.vendorDao = vendorDao;
        this.servicetypeDao = servicetypeDao;
        this.servicemodedetailDao = servicemodedetailDao;
        this.deliverytermDao = deliverytermDao;
        this.currencyDao = currencyDao;
        this.orderDao = orderDao;
        this.locationDao = locationDao;
        this.vendorService = vendorService;
    }

    public void parseExcelFile(File file) {

        // TODO : this method will have argument for File / InputStream to read from
        //File file = new File("D:\\works\\ckb\\import_data\\rate_vendor_final.xls");
        //File logFile = new File("kacanggoreng.log");
        //FileOutputStream fos = new FileOutputStream(logFile,false);

        POIFSFileSystem fs = null;

        try {
            log.info("Importing vendor rate on " + new Date());
            fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFFormulaEvaluator hfEvaluator = new HSSFFormulaEvaluator(wb);

            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;

            int totalRow = sheet.getPhysicalNumberOfRows();
            int cols = 0;
            int tmp = 0;

            for (int i = 0; i < 10 || i < totalRow; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) {
                        cols = tmp;
                    }
                }
            }

            VendorService vserv = null;
            // data start from row no 3, i = 3
            for (int i = 3; i < totalRow; i++) {
                vserv = new VendorService();
                row = sheet.getRow(i);

                try {
                    if (row != null) {
                        // column 0,1,2 ignored
                        System.out.println("");
                        System.out.print("row " + i);

                        /*
                        for (int c = 3; c < cols; c++) {
                        cell = row.getCell(c);

                        if (cell != null) {
                        System.out.println("|");
                        if(cell!=null){
                        System.out.print("index :"+c+", type:"+cell.getCellType());
                        }else{
                        System.out.print("cell with index "+c+" is null");
                        }

                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        System.out.print(cell.getDateCellValue());
                        } else {
                        System.out.print(cell.getNumericCellValue());
                        }
                        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        System.out.print(cell.getRichStringCellValue().toString());
                        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                        System.out.print(cell.getRichStringCellValue());
                        }
                        }
                        }
                         */
                        String cellValueString = null;
                        double cellValueDouble = 0d;
                        Date cellValueDate = null;

                        // process vendor
                        
                        cell = row.getCell(3);
                        try {
                            if (cell == null) {
                                setVendor(vserv, null);
                            } else {                                
                                if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                                    if (cell.getRichStringCellValue() != null) {
                                        setVendor(vserv, cell.getRichStringCellValue().toString());
                                    }
                                }
                                else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                                     String vendorCode = String.valueOf((int)cell.getNumericCellValue());
                                     setVendor(vserv, vendorCode);
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 4(Vendor) :" + e.getMessage());
                        }

                        // type of service
                        cell = row.getCell(4);
                        try {
                            if (cell == null) {
                                setServiceType(vserv, null);
                            } else {
                                if (cell.getRichStringCellValue() != null) {
                                    setServiceType(vserv, cell.getRichStringCellValue().toString());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 5(Service Type) :" + e.getMessage());
                        }
                        // origindetailaddrr
                        cell = row.getCell(5);
                        String originDetailAddr = null;
                        try {
                            if (cell == null) {
                                originDetailAddr = null;
                            } else {
                                if (cell.getRichStringCellValue() != null) {
                                    originDetailAddr = cell.getRichStringCellValue().toString();
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 6(originDetailAddress) :" + e.getMessage());
                        }


                        // origin city name
                        cell = row.getCell(6);
                        String originCityName = null;
                        try {
                            if (cell == null) {
                                originCityName = null;
                            } else {
                                if (cell.getRichStringCellValue() != null) {
                                    originCityName = cell.getRichStringCellValue().toString();
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 7(originCityName) :" + e.getMessage());
                        }
                        // origin station id / locationcode
                        cell = row.getCell(7);
                        String originStationId = null;
                        try {
                            if (cell != null) {
                                if (cell.getRichStringCellValue() != null) {
                                    originStationId = cell.getRichStringCellValue().toString();
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 7(origin station id/location code) :" + e.getMessage());
                        }

                        // process location
                        setOriginLocation(vserv, originStationId, originCityName, originDetailAddr);

                        String destCityName = null;
                        String destStationId = null;
                        String destDetailAddr = null;
                        // dest cityname
                        cell = row.getCell(8);
                        try {
                            if (cell != null) {
                                if (cell.getRichStringCellValue() != null) {
                                    destCityName = cell.getRichStringCellValue().toString();
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 9(dest cityname) :" + e.getMessage());
                        }
                        // dest stationId / locationcode
                        cell = row.getCell(9);
                        try {
                            if (cell != null) {
                                if (cell.getRichStringCellValue() != null) {
                                    destStationId = cell.getRichStringCellValue().toString();
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 10(dest station id/location code) :" + e.getMessage());
                        }

                        // dest detailAddr
                        cell = row.getCell(10);
                        try {
                            if (cell != null) {
                                if (cell.getRichStringCellValue() != null) {
                                    destDetailAddr = cell.getRichStringCellValue().toString();
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 11(dest detailAddress) :" + e.getMessage());
                        }

                        setDestinationLocation(vserv, destStationId, destCityName, destDetailAddr);
                        // service mode detail, service mode
                        cell = row.getCell(12);
                        try {
                            if (cell != null) {                                
                                if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                                    /*
                                    if (cell.getRichStringCellValue() != null) {
                                        setServiceModeDetail(vserv, cell.getRichStringCellValue().toString());
                                    }*/
                                    HSSFFormulaEvaluator.CellValue cellValue = hfEvaluator.evaluate(cell);
                                    if(cellValue!=null){
                                        String smdcode = null;
                                        if(cellValue.getCellType()== HSSFCell.CELL_TYPE_NUMERIC){
                                            smdcode = String.valueOf((int)cellValue.getNumberValue());
                                        }else if(cellValue.getCellType()== HSSFCell.CELL_TYPE_STRING){
                                            smdcode = cellValue.getStringValue();
                                        }
                                        
                                        setServiceModeDetail(vserv, smdcode);
                                    }
                                }
                                else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                                    String smodedetcode = String.valueOf((int)cell.getNumericCellValue());

                                    setServiceModeDetail(vserv, smodedetcode);
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 13(servicemodedetail) :" + e.getMessage());
                        }


                        //order
                        cell = row.getCell(13);
                        try {
                            if (cell != null) {
                                if (cell.getRichStringCellValue() != null) {
                                    setOrder(vserv, cell.getRichStringCellValue().toString());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 14(order) :" + e.getMessage());
                        }


                        // weight type
                        // if weight type = 2, then the data is in volume basis, which
                        // needs to be converted to kg basis
                        boolean isVolumeBasis = false;
                        cell = row.getCell(15);
                        try {
                            if (cell != null) {
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    int iWeightType = (int) cell.getNumericCellValue();
                                    if (iWeightType == 2) {
                                        isVolumeBasis = true;
                                    }
                                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 16(weight type) :" + e.getMessage());
                        }

                        // weight from
                        cell = row.getCell(16);
                        //checkCellType(cell, "weight from");
                        try {
                            if (cell == null) {
                                vserv.setWeightfrom(null);
                            } else {
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    BigDecimal bWeightFrom = new BigDecimal(cell.getNumericCellValue());
                                    if (isVolumeBasis) {
                                        bWeightFrom = bWeightFrom.divide(kgBasisDivider);
                                    }
                                    vserv.setWeightfrom(bWeightFrom);
                                }
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    BigDecimal bWeightFrom = new BigDecimal(cell.getRichStringCellValue().toString());
                                    if (isVolumeBasis) {
                                        bWeightFrom = bWeightFrom.divide(kgBasisDivider);
                                    }
                                    vserv.setWeightfrom(bWeightFrom);
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 17(weight from) :" + e.getMessage());
                        }
                        // weight to
                        cell = row.getCell(17);
                        //checkCellType(cell, "weight to");
                        // if weight to is -, then it's unlimited
                        try {
                            if (cell == null) {
                                vserv.setWeightto(null);
                            } else {
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    BigDecimal bWeightTo = new BigDecimal(cell.getNumericCellValue());
                                    if (isVolumeBasis) {
                                        bWeightTo = bWeightTo.divide(kgBasisDivider);
                                    }
                                    vserv.setWeightto(bWeightTo);
                                }
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    if (DASH.equalsIgnoreCase(cell.getRichStringCellValue().toString())) {
                                        vserv.setWeightto(maxWeightTo);
                                    } else {
                                        BigDecimal bWeightTo = new BigDecimal(cell.getRichStringCellValue().toString());
                                        if (isVolumeBasis) {
                                            bWeightTo = bWeightTo.divide(kgBasisDivider);
                                        }
                                        vserv.setWeightto(bWeightTo);
                                    }
                                }

                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 18(weight to) :" + e.getMessage());
                        }
                        // deliveryterm
                        cell = row.getCell(18);
                        try {
                            if (cell != null) {
                                try {

                                    if (cell.getRichStringCellValue() != null) {
                                        setDeliveryTerm(vserv, cell.getRichStringCellValue().toString());
                                    }
                                } catch (Exception e) {
                                    log.error("error processing delivery term: " + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 19(deliveryterm) :" + e.getMessage());
                        }
                        // currency
                        cell = row.getCell(19);
                        try {
                            if (cell != null) {
                                try {
                                    //checkCellType(cell, "currency");
                                    if (cell.getRichStringCellValue() != null) {
                                        setCurrency(vserv, cell.getRichStringCellValue().toString());
                                    }
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    log.error("error processing currency :" + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 20(currency) :" + e.getMessage());
                        }
                        // rate / charge
                        cell = row.getCell(20);
                        try {
                            if (cell != null) {
                                try {
                                    //checkCellType(cell, "rate");
                                    vserv.setRate(new BigDecimal(cell.getNumericCellValue()));
                                } catch (Exception e) {
                                    log.error("error processing rate :" + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 21(rate) :" + e.getMessage());
                        }
                        // agreement no
                        cell = row.getCell(21);
                        try {
                            if (cell != null) {
                                try {
                                    if (cell.getRichStringCellValue() != null) {
                                        vserv.setRefagreementno(cell.getRichStringCellValue().toString());
                                    }
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    log.error("error processing agreement no :" + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 22(agreement no) :" + e.getMessage());
                        }
                        // valid from
                        cell = row.getCell(22);
                        try {
                            if (cell != null) {
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    vserv.setValidfrom(cell.getDateCellValue());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 23(valid from) :" + e.getMessage());
                        }
                        // valid to
                        cell = row.getCell(23);
                        try {
                            if (cell != null) {
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    vserv.setValidto(cell.getDateCellValue());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 24(valid to) :" + e.getMessage());
                        }
                        // agreement date
                        cell = row.getCell(24);
                        try {
                            if (cell != null) {
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    vserv.setRefagreementdate(cell.getDateCellValue());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 25(agreement date) :" + e.getMessage());
                        }
                        // minimum weight
                        cell = row.getCell(25);
                        //checkCellType(cell, "weight to");
                        // if weight to is -, then it's unlimited
                        try {
                            if (cell == null) {
                                vserv.setMinimumweight(null);
                            } else {
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    BigDecimal minimumWeight = new BigDecimal(cell.getNumericCellValue());
                                    if (isVolumeBasis) {
                                        minimumWeight = minimumWeight.divide(kgBasisDivider);
                                    }
                                    vserv.setMinimumweight(minimumWeight);
                                }
                                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                    if (DASH.equalsIgnoreCase(cell.getRichStringCellValue().toString())) {
                                        vserv.setMinimumweight(null);
                                    } else {
                                        BigDecimal minimumWeight = new BigDecimal(cell.getRichStringCellValue().toString());
                                        if (isVolumeBasis) {
                                            minimumWeight = minimumWeight.divide(kgBasisDivider);
                                        }
                                        vserv.setMinimumweight(minimumWeight);
                                    }
                                }

                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 26(minimum weight) :" + e.getMessage());
                        }
                        
                        // agreement no
                        cell = row.getCell(26);
                        try {
                            if (cell != null) {
                                try {
                                    if (cell.getRichStringCellValue() != null) {
                                        vserv.setRemarks(cell.getRichStringCellValue().toString());
                                    }
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    log.error("error processing remarks :" + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            throw new ValidationException("error on cell no 22(remarks) :" + e.getMessage());
                        }
                        // TODO : validate vserv bean
                        // check if record already exist
                        // if both TRUE, then insert
                        // then write to log file
                        VendorServiceLocalServiceUtil.validateVendorService(vserv);
                        //
                        if (VendorServiceLocalServiceUtil.isVendorRateExists(vserv)) {
                            log.error("row no " + (i + 1) + " failed. Vendor rate already exists");
                        } else {
                            VendorServiceLocalServiceUtil.insertVendorService(vserv);
                            log.info("row no " + (i + 1) + " succesfully inserted ");
                        }
                    }
                } catch (Exception e) {
                    log.error("row no " + (i + 1) + " failed. Cause :" + e);
                }

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
    }

    private void setOrder(VendorService vserv, String orderCode) {
        if (orderCode != null) {
            orderCode = orderCode.trim();
        }
        OrderExample example = new OrderExample();
        example.createCriteria().andOrdercodeEqualTo(orderCode);
        // to handle CHARGEABLEWEIGHT or CHARGEABLE WEIGHT
        example.or(example.createCriteria().andOrdernameEqualTo(orderCode));

        List<Order> lOrder = orderDao.selectOrderByExample(example);
        if (lOrder != null && lOrder.size() > 0) {
            Order order = lOrder.get(0);
            vserv.setTorderFk(order.getTorderPk());
        } else {
            throw new ValidationException("Order type :" + orderCode + " not found. Data can't be processed");
        }
    }

    private void setOriginLocation(VendorService vserv, String stationId, String cityName, String detailAddress) {
        Location origin = findLocation(stationId, cityName, detailAddress);
        vserv.setOrigintlocationFk(origin.getTlocationPk());
    }

    private void setDestinationLocation(VendorService vserv, String stationId, String cityName, String detailAddress) {
        Location destination = findLocation(stationId, cityName, detailAddress);
        vserv.setDestinationtlocationFk(destination.getTlocationPk());
    }

    private Location findLocation(String stationId, String cityName, String detailAddress) {
        if (stationId == null || stationId.trim().length() == 0) {
            throw new ValidationException("stationId/cityId can't be empty");
        }
        if (cityName == null || cityName.trim().length() == 0) {
            throw new ValidationException("cityName can't be empty");
        }
        // trim all
        stationId = stationId.trim();
        cityName = cityName.trim();
        if (detailAddress != null) {
            detailAddress = detailAddress.trim();
        } else {
            // we set impossible for detailAddress to have NULL value
            detailAddress = "";
        }
        LocationExample example = new LocationExample();
        LocationExample.Criteria crit = example.createCriteria();
        crit.andLocationcodeEqualTo(stationId);
        crit.andCitynameEqualTo(cityName);
        crit.andDetailaddressEqualTo(detailAddress);

        List<Location> lLoc = locationDao.selectLocationByExample(example);
        if (lLoc != null && lLoc.size() > 0) {
            return lLoc.get(0);
        } else {

            /*
            Location location = new Location();
            location.setLocationcode(stationId);
            location.setCityname(cityName);
            location.setDetailaddress(detailAddress);
            if (detailAddress == null || detailAddress.trim().length() == 0) {
                location.setLocationname(cityName);
            } else {
                location.setLocationname(cityName + WOConstant.COMMA + detailAddress);
            }
            locationDao.insertLocation(location);
            return location;

             */
            throw new ValidationException("Location with stationId:"+stationId+",cityName:"+cityName+",detailAddress:"+detailAddress+" not found in master table.");
        }
    }

    private void setServiceModeDetail(VendorService vserv, String smdCode) {
        if (smdCode != null) {
            smdCode = smdCode.trim();
        }
        ServiceModeDetailExample example = new ServiceModeDetailExample();
        example.createCriteria().andSmdcodeEqualTo(smdCode);
        List<ServiceModeDetail> lsmdetail = servicemodedetailDao.selectServiceModeDetailByExample(example);
        if (lsmdetail == null || lsmdetail.size() == 0) {
            throw new ValidationException("container type :" + smdCode + " not found. Data can't be processed");
        } else {
            ServiceModeDetail smd = lsmdetail.get(0);
            vserv.setTservicemodedetailFk(smd.getTservicemodedetailPk());
            // set service mode
            vserv.setTservicemodeFk(smd.getTservicemodeFk());
        }
    }

    private void setServiceType(VendorService vserv, String serviceTypeCode) {
        ServiceTypeExample stexample = new ServiceTypeExample();
        stexample.createCriteria().andServicecodeEqualTo(serviceTypeCode);
        List<ServiceType> lstype = servicetypeDao.selectServiceTypeByExample(stexample);
        if (lstype == null || lstype.size() == 0) {
            throw new ValidationException("service type : " + serviceTypeCode + " not found. Data can't be processed");
        } else {
            ServiceType servicetype = lstype.get(0);
            vserv.setTservicetypeFk(servicetype.getTservicetypePk());
        }
    }

    private void setVendor(VendorService vserv, String vendorCode) {
        VendorExample example = new VendorExample();
        example.createCriteria().andVendorcodeEqualTo(vendorCode);
        List<Vendor> lvendor = vendorDao.selectVendorByExample(example);
        if (lvendor == null || lvendor.size() == 0) {
            throw new ValidationException("vendor with id:" + vendorCode + " not found.Data can't be processed");
        } else {
            Vendor vendor = lvendor.get(0);
            vserv.setTvendorFk(vendor.getTvendorPk());
        }
    }

    private void setDeliveryTerm(VendorService vserv, String dtCode) {
        DeliveryTermExample example = new DeliveryTermExample();
        example.createCriteria().andDtcodeEqualTo(dtCode);
        List<DeliveryTerm> ldterm = deliverytermDao.selectDeliveryTermByExample(example);
        if (ldterm == null || ldterm.size() == 0) {
            throw new ValidationException("deliveryterm with code : " + dtCode + " not found. Data can't be processed");
        } else {
            vserv.setTdeliverytermFk(ldterm.get(0).getTdeliverytermPk());
        }
    }

    private void setCurrency(VendorService vserv, String currencyCode) {
        if (currencyCode != null) {
            currencyCode = currencyCode.trim();
        }
        CurrencyExample example = new CurrencyExample();
        example.createCriteria().andCurrencycodeEqualTo(currencyCode);
        List<Currency> lcurrency = currencyDao.selectCurrencyByExample(example);
        if (lcurrency == null || lcurrency.size() == 0) {
            throw new ValidationException("currency with code :" + currencyCode + " not found. Data can't be processed");
        } else {
            Currency currency = lcurrency.get(0);
            vserv.setTcurrencyFk(currency.getTcurrencyPk());
        }
    }

    /*
    private void checkCellType(HSSFCell cell, String fieldName) {
    System.out.println("fieldname :" + fieldName + " celltype :" + cell.getCellType());
    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
    System.out.print("Numeric1 : " + cell.getNumericCellValue());
    if (HSSFDateUtil.isCellDateFormatted(cell)) {
    System.out.println("Numeric, dateFormatted :" + cell.getDateCellValue());
    } else {
    System.out.print("Numeric : " + cell.getNumericCellValue());
    }
    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
    System.out.print("String : " + cell.getRichStringCellValue().toString());
    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    System.out.print("Formula : " + cell.getRichStringCellValue());
    }

    } */
}
