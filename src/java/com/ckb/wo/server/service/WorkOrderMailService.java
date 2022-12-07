package com.ckb.wo.server.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;

import com.ckb.wo.client.model.WorkOrder;

public interface WorkOrderMailService {

    public JavaMailSender getMailSender();

    public void setMailSender(JavaMailSender mailSender);

    public VelocityEngine getVelocityEngine();

    public void setVelocityEngine(VelocityEngine velocityEngine);

    public void sendNotificationForCostValidation(WorkOrder workOrder);

    public void sendNotificationForEdit(WorkOrder workOrder, String recipient);

    public void sendNotificationForApproval(WorkOrder workOrder, String recipient);

    public String getSender();

    public void setSender(String sender);

    public String getCostValidationTemplate();

    public void setCostValidationTemplate(String costValidationTemplate);

    public String getEditTemplate();

    public void setEditTemplate(String editTemplate);

    public String getApprovalTemplate();

    public void setApprovalTemplate(String approvalTemplate);

    public String getCostValidationTitle();

    public void setCostValidationTitle(String costValidationTitle);

    public String getEditTitle();

    public void setEditTitle(String editTitle);

    public String getApprovalTitle();

    public void setApprovalTitle(String approvalTitle);

    public String getProcEmailAddress();

    public void setProcEmailAddress(String procEmailAddress);

    public void sendNotificationForCancel(WorkOrder workOrder, String recipient);

    public void sendNotificationForRevision(WorkOrder workOrder, String recipient);

    public void sendNotificationForReminder(WorkOrder workOrder, String recipient);

    public void sendNotificationWOHasBeenApproved(WorkOrder workOrder, String recipient);

    public void sendCustomCostValidationMail(WorkOrder workOrder, String content);

}
