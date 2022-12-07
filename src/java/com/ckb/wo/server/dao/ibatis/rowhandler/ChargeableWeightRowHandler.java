package com.ckb.wo.server.dao.ibatis.rowhandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ckb.wo.client.model.VendorServiceExt;
import com.ibatis.sqlmap.client.event.RowHandler;

public class ChargeableWeightRowHandler implements RowHandler {

    private BigDecimal weight;
    private List<VendorServiceExt> listVendorService = new ArrayList<>();

    @Override
    public void handleRow(Object arg0) {
//		VendorService vserv = (VendorService) arg0;
        VendorServiceExt vserv = (VendorServiceExt) arg0;
        try {
            weight = weight.compareTo(vserv.getMinimumweight()) > 0 ? weight : vserv.getMinimumweight();
        } catch (Exception e) {
            logger.LoggerClass.logError(e);
        }
        vserv.setWeight(weight);
        if (vserv.getIsFlatRate()) {
            vserv.setRate(vserv.getRate());
        } else {
            vserv.setRate(weight.multiply(vserv.getRate()));
        }
        listVendorService.add(vserv);
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public List<VendorServiceExt> getListVendorService() {
        return listVendorService;
    }

}
