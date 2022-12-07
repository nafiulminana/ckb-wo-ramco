package com.ckb.wo.client.model;

import java.io.Serializable;

public class WoDaManStatus implements Serializable {

    private String wopk;
    private String wo;
    private String da;
    private String man;
    private String status;

    public String getWopk() {
        return wopk;
    }

    public void setWopk(String wopk) {
        this.wopk = wopk;
    }

    public String getWo() {
        return wo;
    }

    public void setWo(String wo) {
        this.wo = wo;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
