/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.model;

import java.util.Date;

/**
 *
 * @author Amaran Sidhiq
 */
public class Notes {
    Long tworkorderFk,tnotesPk;
    String employeeId;
    String notes;
    Date datetimeUpdated=new Date();
    String noteType,subject;
    User user;

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public Date getDatetimeUpdated() {
        return datetimeUpdated;
    }

    public void setDatetimeUpdated(Date datetimeUpdated) {
        this.datetimeUpdated = datetimeUpdated;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getTnotesPk() {
        return tnotesPk;
    }

    public void setTnotesPk(Long tnotesPk) {
        this.tnotesPk = tnotesPk;
    }

    public Long getTworkorderFk() {
        return tworkorderFk;
    }

    public void setTworkorderFk(Long tworkorderFk) {
        this.tworkorderFk = tworkorderFk;
    }
}
