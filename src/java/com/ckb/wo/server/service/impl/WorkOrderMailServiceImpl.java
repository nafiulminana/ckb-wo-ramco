package com.ckb.wo.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.dao.LevelDAO;
import com.ckb.wo.server.dao.UserDAO;
import com.ckb.wo.server.dao.WorkOrderFlowDAO;
import com.ckb.wo.server.service.WorkOrderMailService;
import org.springframework.mail.MailException;

public class WorkOrderMailServiceImpl implements WorkOrderMailService {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WorkOrderMailServiceImpl.class);

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private String sender;
    private String costValidationTemplate;
    private String editTemplate;
    private String approvalTemplate;
    private String costValidationTitle;
    private String editTitle;
    private String approvalTitle;
    private String customCostValidationTitle;
    private String procEmailAddress;
    private String cancelTemplate;
    private String cancelTitle;
    private String revisionTemplate;
    private String revisionTitle;
    private String reminderTemplate;
    private String reminderTitle;
    private String hasBeenApprovedTitle;
    private String hasBeenApprovedTemplate;
    private WorkOrderFlowDAO workorderflowDao;
    private LevelDAO levelDao;
    private UserDAO userDao;

    @Override
    public JavaMailSender getMailSender() {
        return mailSender;
    }

    @Override
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    @Override
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    private void sendEmail(final String recipient, final String subject, final String content) {
        if (recipient == null || recipient.isEmpty()) {
            log.info("No email is going to send because no recipient");
        } else {
            log.info("Sending Email to " + recipient + ", with subject " + subject + ", and content " + content);
            final JavaMailSender send = this.mailSender;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MimeMessagePreparator preparator = new MimeMessagePreparator() {
                            @Override
                            public void prepare(MimeMessage mimeMessage) throws Exception {
                                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                                message.setTo(recipient.split(","));
                                message.setSubject(subject);
                                message.setFrom(getSender());
                                message.setText(content, true);
                            }
                        };
                        //productionsetup
                        send.send(preparator);
                    } catch (MailException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }).start();
        }
    }

    @Override
    public void sendNotificationForCostValidation(WorkOrder workOrder) {
        String content = generateContent(workOrder, getCostValidationTemplate());
        String subject = getCostValidationTitle() + workOrder.getNomorwo();
        sendEmail(getProcEmailAddress(), subject, content);
    }

    @Override
    public void sendNotificationForEdit(WorkOrder workOrder, String recipient) {
        String content = generateContent(workOrder, getEditTemplate());
        String subject = getEditTitle() + workOrder.getNomorwo();
        sendEmail(recipient, subject, content);
    }

    @Override
    public void sendNotificationForApproval(WorkOrder workOrder, String recipient) {
        String content = generateContent(workOrder, getApprovalTemplate());
        String subject = getApprovalTitle() + workOrder.getNomorwo();
        sendEmail(recipient, subject, content);
    }

    @Override
    public void sendNotificationForCancel(WorkOrder workOrder, String recipient) {
        String content = generateContent(workOrder, getCancelTemplate());
        String subject = getCancelTitle() + workOrder.getNomorwo();
        sendEmail(recipient, subject, content);
    }

    @Override
    public void sendNotificationForRevision(WorkOrder workOrder, String recipient) {
        String content = generateContent(workOrder, getRevisionTemplate());
        String subject = getRevisionTitle() + workOrder.getNomorwo();
        sendEmail(recipient, subject, content);
    }

    @Override
    public void sendNotificationForReminder(WorkOrder workOrder, String recipient) {
        String content = generateContent(workOrder, getReminderTemplate());
        String subject = getReminderTitle() + workOrder.getNomorwo();
        sendEmail(recipient, subject, content);
    }

    @Override
    public void sendNotificationWOHasBeenApproved(WorkOrder workOrder, String recipient) {
        String content = generateContent(workOrder, getHasBeenApprovedTemplate());
        String subject = getHasBeenApprovedTitle() + workOrder.getNomorwo();
        sendEmail(recipient, subject, content);
    }

    @Override
    public void sendCustomCostValidationMail(WorkOrder workOrder, String content) {
        String subject = getCustomCostValidationTitle() + workOrder.getNomorwo();
        sendEmail(getProcEmailAddress(), subject, content);
    }

    private String generateContent(WorkOrder workOrder, String templateLocation) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEE, dd-MM-yyyy");
        Map<String, Object> model = new HashMap<>();
        model.put("workOrder", workOrder);
        model.put("dateFormatter", sdf);
        model.put("approvalType", levelDao.selectApprovalTypeWithContent(workOrder.getMaxlevel()));
        model.put("approvalHistory", workorderflowDao.selectApprovalHistoryForThisWorkOrder(workOrder.getTworkorderPk()));
        model.put("user", getUserDao().selectUserByPrimaryKey(workOrder.getCreatedbyemployeeid()));
        return VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), templateLocation, model);
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String getCostValidationTemplate() {
        return costValidationTemplate;
    }

    @Override
    public void setCostValidationTemplate(String costValidationTemplate) {
        this.costValidationTemplate = costValidationTemplate;
    }

    @Override
    public String getEditTemplate() {
        return editTemplate;
    }

    @Override
    public void setEditTemplate(String editTemplate) {
        this.editTemplate = editTemplate;
    }

    @Override
    public String getApprovalTemplate() {
        return approvalTemplate;
    }

    @Override
    public void setApprovalTemplate(String approvalTemplate) {
        this.approvalTemplate = approvalTemplate;
    }

    @Override
    public String getCostValidationTitle() {
        return costValidationTitle;
    }

    @Override
    public void setCostValidationTitle(String costValidationTitle) {
        this.costValidationTitle = costValidationTitle;
    }

    @Override
    public String getEditTitle() {
        return editTitle;
    }

    @Override
    public void setEditTitle(String editTitle) {
        this.editTitle = editTitle;
    }

    @Override
    public String getApprovalTitle() {
        return approvalTitle;
    }

    @Override
    public void setApprovalTitle(String approvalTitle) {
        this.approvalTitle = approvalTitle;
    }

    @Override
    public String getProcEmailAddress() {
        return procEmailAddress;
    }

    @Override
    public void setProcEmailAddress(String procEmailAddress) {
        this.procEmailAddress = procEmailAddress;
    }

    public String getCancelTemplate() {
        return cancelTemplate;
    }

    public void setCancelTemplate(String cancelTemplate) {
        this.cancelTemplate = cancelTemplate;
    }

    public String getCancelTitle() {
        return cancelTitle;
    }

    public void setCancelTitle(String cancelTitle) {
        this.cancelTitle = cancelTitle;
    }

    public String getRevisionTemplate() {
        return revisionTemplate;
    }

    public void setRevisionTemplate(String revisionTemplate) {
        this.revisionTemplate = revisionTemplate;
    }

    public String getRevisionTitle() {
        return revisionTitle;
    }

    public void setRevisionTitle(String revisionTitle) {
        this.revisionTitle = revisionTitle;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getReminderTemplate() {
        return reminderTemplate;
    }

    public void setReminderTemplate(String reminderTemplate) {
        this.reminderTemplate = reminderTemplate;
    }

    public String getHasBeenApprovedTitle() {
        return hasBeenApprovedTitle;
    }

    public void setHasBeenApprovedTitle(String hasBeenApprovedTitle) {
        this.hasBeenApprovedTitle = hasBeenApprovedTitle;
    }

    public String getHasBeenApprovedTemplate() {
        return hasBeenApprovedTemplate;
    }

    public void setHasBeenApprovedTemplate(String hasBeenApprovedTemplate) {
        this.hasBeenApprovedTemplate = hasBeenApprovedTemplate;
    }

    public String getCustomCostValidationTitle() {
        return customCostValidationTitle;
    }

    public void setCustomCostValidationTitle(String customCostValidationTitle) {
        this.customCostValidationTitle = customCostValidationTitle;
    }

    public WorkOrderFlowDAO getWorkorderflowDao() {
        return workorderflowDao;
    }

    public void setWorkorderflowDao(WorkOrderFlowDAO workorderflowDao) {
        this.workorderflowDao = workorderflowDao;
    }

    public LevelDAO getLevelDao() {
        return levelDao;
    }

    public void setLevelDao(LevelDAO levelDao) {
        this.levelDao = levelDao;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
}
