/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.model;

import java.io.Serializable;

/**
 *
 * @author Amaran Sidhiq
 */
public class UserBeans implements Serializable {

    private String fullName;
    private String userId;
    private String password;
    private String employeeId;
    private String loginLevel;
    private String stationId;
    private boolean logon = false;
    private String errMessage;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(String loginLevel) {
        this.loginLevel = loginLevel;
    }

    public boolean isLogon() {
        return logon;
    }

    public void setLogon(boolean logon) {
        this.logon = logon;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserBeans() {
    }

    public UserBeans(String fullName, String userId, String employeeId, String loginLevel, String stationId) {
        this.fullName = fullName;
        this.userId = userId;
        this.employeeId = employeeId;
        this.loginLevel = loginLevel;
        this.stationId = stationId;
    }
}
