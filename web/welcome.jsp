<div class="subheader">
    <p>Welcome to Work Order Management Application.<br>
        <%
            com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) request.getSession().getAttribute("loginInfo");
            if (com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.APPROVE_WO)) {
        %>
        Click <a href="#" onclick="openGrant()">here</a> to escalate your approval.
        <%            }
        %>
    </p>
</div>
<div style="padding: 10px 10px 10px 10px">
    <%
        //if (com.ckb.wo.server.service.util.UserLocalServiceUtil.isOperationUser(ub.getEmployeeId())) {
        if (ub.isLogon()) {
           
        }
        if (com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(),
                com.ckb.wo.client.model.Permission.APPROVE_WO)) {
            try {
                int app = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithoutGrant(ub.getEmployeeId());
                if (app > 0) {
                    out.print("<br/>You have <a href='list/workorder_app.jsp?t=0'>" + app + "</a> Outstanding Approval.");
                }
            } catch (Exception e) {
            }
            try {
                int appg = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderToBeApprovedWithGrant(ub.getEmployeeId());
                if (appg > 0) {
                    out.print("<br/>You have <a href='list/workorder_app.jsp?t=2'>" + appg + "</a> Escalated Approval.");
                }
            } catch (Exception e) {
            }
            try {
                int app = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderUsingPODToBeApprovedWithoutGrant(ub.getEmployeeId());
                if (app > 0) {
                    out.print("<br/>You have <a href='list/workorder_app.jsp?t=1'>" + app + "</a> Outstanding Approval (Using POD DA).");
                }
            } catch (Exception e) {
            }
            try {
                int appg = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderUsingPODToBeApprovedWithGrant(ub.getEmployeeId());
                if (appg > 0) {
                    out.print("<br/>You have <a href='list/workorder_app.jsp?t=3'>" + appg + "</a> Escalated Approval (Using POD DA).");
                }
            } catch (Exception e) {
            }
        }

        if (com.ckb.wo.server.service.util.UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId())) {
            int validate = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderForProcuremenUser(null);
            if (validate > 0) {
                out.print("<br/>You have <a href='list/workorder_proc.jsp'>" + validate + "</a> document" + (validate > 1 ? "s" : "") + " to be validated!");
            }
        }
        if (com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.RECEIVE_AP)
                || com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.RECEIVE_TR)) {

            com.ckb.wo.client.model.ExtendedWorkOrderExample example = new com.ckb.wo.client.model.ExtendedWorkOrderExample();
            com.ckb.wo.client.model.ExtendedWorkOrderExample.Criteria criteria = example.createCriteria();

            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.MONTH, java.util.Calendar.JANUARY);
            cal.set(java.util.Calendar.DATE, 1);
            criteria.andCreateddateGreaterThanOrEqualTo(cal.getTime());

            boolean ap = com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.RECEIVE_AP);
            boolean treasury = com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.RECEIVE_TR);
            String sql = "";
            if (ap && treasury) {
                sql = "atworkorder.wostatus IN ('" + com.ckb.wo.client.model.WorkOrder.PRINTED_STATUS + "', '" + com.ckb.wo.client.model.WorkOrder.APPROVED_STATUS + "') "
                        + " OR (atworkorder.cancellationfee IS NOT NULL AND atworkorder.wostatus = '" + com.ckb.wo.client.model.WorkOrder.CANCELLED_STATUS + "')";
                sql += "OR atworkorder.wostatus IN ('" + com.ckb.wo.client.model.WorkOrder.RECEIVED_AP + "', '" + com.ckb.wo.client.model.WorkOrder.CANCEL_RECEIVED_AP + "')";
            } else if (ap) {
                sql = "atworkorder.wostatus IN ('" + com.ckb.wo.client.model.WorkOrder.PRINTED_STATUS + "', '" + com.ckb.wo.client.model.WorkOrder.APPROVED_STATUS + "') "
                        + " OR (atworkorder.cancellationfee IS NOT NULL AND atworkorder.wostatus = '" + com.ckb.wo.client.model.WorkOrder.CANCELLED_STATUS + "')";
            } else {
                sql = "atworkorder.wostatus IN ('" + com.ckb.wo.client.model.WorkOrder.RECEIVED_AP + "', '" + com.ckb.wo.client.model.WorkOrder.CANCEL_RECEIVED_AP + "')";
            }
            criteria.setAndCustomSQL(sql);

            int count = 0;
            count = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderByExample(example);

            if (count > 0) {
                out.print("<br/>You have <a href='list/workorder_fin.jsp?t=0'>" + count + "</a> document" + (count > 1 ? "s" : "") + " to be completed!");
            }
        }
        if (com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.CREATE_WO)) {
            com.ckb.wo.client.model.WorkOrderExample example = new com.ckb.wo.client.model.WorkOrderExample();
            example.createCriteria().andWostatusEqualTo(com.ckb.wo.client.model.WorkOrder.EDIT_STATUS).andCreatedbyemployeeidEqualTo(ub.getEmployeeId());
            int count = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
            if (count > 0) {
                out.print("<br/>You have <a href='list/workorder.jsp?t=1'>" + count + "</a> document" + (count > 1 ? "s" : "") + " need your action!");
            }
        }
        if (com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.CREATE_WO)) {
            com.ckb.wo.client.model.WorkOrderExample example = new com.ckb.wo.client.model.WorkOrderExample();
            example.createCriteria().andWostatusEqualTo(com.ckb.wo.client.model.WorkOrder.APPROVED_STATUS).andCreatedbyemployeeidEqualTo(ub.getEmployeeId());
            int count = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.countWorkOrderByExample(example);
            if (count > 0) {
                out.print("<br/>You have <a href='list/workorder.jsp?t=3'>" + count + "</a> document" + (count > 1 ? "s" : "") + " approved!");
            }
        }
    %>
</div>
