<%-- 
    Document   : containerMode
    Created on : Jul 5, 2010, 6:50:35 PM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (!ub.isLogon()) {
            out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
        }
    } else {
        out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
    }

    com.ckb.wo.client.model.ServiceModeExample exm = new com.ckb.wo.client.model.ServiceModeExample();
    try {
        exm.createCriteria().andTservicetypeFkEqualTo(new Long(request.getParameter("st")));
    } catch (Exception e) {
        //exm.createCriteria().andTservicetypeFkEqualTo(1L);
    }
    java.util.List<com.ckb.wo.client.model.ServiceMode> serviceMode = com.ckb.wo.server.service.util.ServiceModeLocalServiceUtil.getServiceModeByExample(exm);
    request.setAttribute("serviceMode", serviceMode);
    java.util.List<com.ckb.wo.client.model.Currency> currency = com.ckb.wo.server.service.util.CurrencyLocalServiceUtil.getCurrency();
    request.setAttribute("currency", currency);
    Double qty = null;
    java.math.BigDecimal charge = null;
    com.ckb.wo.client.model.Currency cur = null;
    String remarks = request.getParameter("remarks");
    String smddetailname = request.getParameter("smddetailname");
    try {
        smddetailname = smddetailname.isEmpty() ? null : smddetailname;
    } catch (NullPointerException e) {
    }
    Long containerMode = null;

    String charges = "";
    String deptId = "";
    String retApv = "";
    try {
        com.ckb.wo.client.model.WorkOrder wo = (com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo");
        if (request.getParameter("addContainer") != null) {
            boolean ufr = false;
            if (request.getParameter("addContainer").equalsIgnoreCase("Update First Row")) {
                ufr = true;
            }
            try {
                qty = Double.valueOf(request.getParameter("qty"));
                //qty = qty < 1 ? 1 : qty;
                containerMode = new Long(request.getParameter("containerMode"));
            } catch (Exception e) {
                throw new Exception("Required field not selected!");
            }
            try {
                cur = com.ckb.wo.server.service.util.CurrencyLocalServiceUtil.getCurrencyByPrimaryKey(new Long(request.getParameter("currency")));
            } catch (Exception e) {
                throw new Exception("Currency must be selected!");
            }
            try {
                charge = new java.math.BigDecimal(request.getParameter("charge"));
                if (charge.compareTo(java.math.BigDecimal.ZERO) <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new Exception("Charge is empty!");
            }
            if (wo != null) {
                com.ckb.wo.client.model.WorkOrderServiceModeDetail wsmd
                        = new com.ckb.wo.client.model.WorkOrderServiceModeDetail(
                                qty,
                                new com.ckb.wo.server.service.util.ServiceModeDetailLocalServiceUtil().getServiceModeDetailByPrimaryKey(containerMode));
                wsmd.setSmdcharge(charge);
                wsmd.setSmdremarks(remarks);
                wsmd.setSmddetailname(smddetailname);
                wsmd.setSmdtcurrencyFk(cur.getTcurrencyPk());
                wsmd.setCurrency(cur);
                java.util.List<com.ckb.wo.client.model.WorkOrderServiceModeDetail> lwsmd = wo.getWorkOrderServiceModeDetail();
                boolean valid = true;
                if (lwsmd == null) {
                    lwsmd = new java.util.ArrayList<com.ckb.wo.client.model.WorkOrderServiceModeDetail>();
                } else {
                    //Boolean freg = false;
                    //try {
                    //    if (session.getAttribute("fromRegular") != null) {
                    //        freg = (Boolean) session.getAttribute("fromRegular");
                    //    }
                    //} catch (Exception e) {
                    //}
                    for (int idx = 0; idx < lwsmd.size(); idx++) {
                        if (lwsmd.get(idx).getTservicemodedetailFk().equals(wsmd.getTservicemodedetailFk())) {
                            if (!lwsmd.get(idx).isDeleted() && (remarks.trim().isEmpty() || lwsmd.get(idx).getSmdremarks().trim().equalsIgnoreCase(remarks.trim()) || (idx == 0 && ufr))) {
                                if (ufr) {
                                    if (wsmd.getQuantity().compareTo(0D) <= 0) {
                                        throw new Exception("Quantity is lesser than equal to 0");
                                    }
                                    lwsmd.set(idx, wsmd);
                                } else {
                                    lwsmd.get(idx).setSmdcharge(charge);
                                    lwsmd.get(idx).setSmdtcurrencyFk(cur.getTcurrencyPk());
                                    lwsmd.get(idx).setCurrency(cur);
                                    lwsmd.get(idx).setSmdremarks(remarks);
                                    lwsmd.get(idx).setSmddetailname(smddetailname);
                                    if (!ufr) {
                                        lwsmd.get(idx).setQuantity(lwsmd.get(idx).getQuantity() + wsmd.getQuantity());
                                    }
                                }
                                valid = false;
                                break;
                            }
                        }
                    }
                }
                if (valid) {
                    //System.out.println((wsmd.getQuantity().compareTo(0D) <= 0));
                    if (wsmd.getQuantity().compareTo(0D) <= 0) {
                        throw new Exception("Quantity is lesser than equal to 0");
                    }
                    lwsmd.add(wsmd);
                }
            }
        }
        if (request.getParameter("remove") != null) {
            java.util.List<com.ckb.wo.client.model.WorkOrderServiceModeDetail> lwsmd = wo.getWorkOrderServiceModeDetail();
            Integer remove = null;
            try {
                remove = new Integer(request.getParameter("remove"));
            } catch (Exception e) {
                throw new Exception("Remove contaner mode failed!");
            }
            if (lwsmd != null) {
                //for (int idx = 0; idx < lwsmd.size(); idx++) {
                //    if (lwsmd.get(idx).getTservicemodedetailFk().equals(remove)) {
                //        lwsmd.remove(remove);
                //        break;
                //    }
                //}
                try {
                    if (wo.getWostatus().equals(com.ckb.wo.client.model.WorkOrder.EDIT_STATUS)
                            || wo.getWostatus().equals(com.ckb.wo.client.model.WorkOrder.REVISION_STATUS)) {
                        lwsmd.get(remove).setDeleted(true);
                    }
                } catch (Exception e) {
                    lwsmd.remove((int) remove);
                }

            }
        }
        //calculate charge
        java.util.List<com.ckb.wo.client.model.WorkOrderServiceModeDetail> lwsmd = wo.getWorkOrderServiceModeDetail();
        wo.setCharge(java.math.BigDecimal.ZERO);
        /*for (int idx = 0; idx < lwsmd.size(); idx++) {
        com.ckb.wo.client.model.WorkOrderServiceModeDetail elem = lwsmd.get(idx);
        wo.setCharge(wo.getCharge().add(elem.getSmdcharge().multiply(new java.math.BigDecimal(elem.getQuantity()))));
        }*/
        charges = com.ckb.wo.client.listener.WorkOrderListener.getCharges(wo.getWorkOrderServiceModeDetail());
        com.ckb.wo.client.listener.WorkOrderListener woListener = new com.ckb.wo.client.listener.WorkOrderListener();
        deptId = woListener.getRateByDept(ub.getEmployeeId());
        retApv = woListener.getLevelApproval(deptId, wo.getWorkOrderServiceModeDetail(), request);
        wo.setMaxlevel(Integer.valueOf(retApv));
    } catch (Exception e) {
        request.setAttribute("errorMessage", e.getMessage());
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Service Mode Detail</title>
    </head>
    <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/dwr/interface/WorkOrder.js"></script>
    <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/dwr/engine.js"></script>
    <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
    <script type="text/javascript">
        function getSmd(mode) {
            if (isNaN(mode)) {
                var def = "<option value='---'>Select Service Mode First</option>";
                $('#containerMode').html(def);
                return;
            }
            WorkOrder.getContainerList(mode, {
                callback: function (result) {
                    $('#containerMode').html(result);
                }, async: false
            });
        }
    </script>
    <body>
        <h2>Add Service Mode Detail</h2>
        <c:if test="${requestScope['errorMessage']!=null||requestScope['errorMessage']!=''}">
            <div style="border:dotted red thin">
                ${requestScope['errorMessage']}
            </div>
        </c:if>
        <form action="containerMode.jsp" method="post">
            <table>
                <tr>
                    <td>
                        <label for="transportaionMode">Service Mode</label>
                    </td>
                    <td>
                        <select name="serviceMode" id="serviceMode" onchange="getSmd(this.options[this.selectedIndex].value)">
                            <option value="---">Select Mode</option>
                            <c:forEach var="mode" items="${requestScope['serviceMode']}">
                                <option value="${mode.tservicemodePk}">${mode.smodename}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="containerMode">Service<span style="color: red">*</span></label>
                    </td>
                    <td>
                        <select name="containerMode" id="containerMode">
                            <option value='---'>Select Service Mode First</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="smddetailname">Fleet/Vessel Name</label>
                    </td>
                    <td>
                        <input type="text" name="smddetailname"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="qty">Quantity <span style="color: red">*</span></label>
                    </td>
                    <td>
                        <input type="text" name="qty" value="0" size="3" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="charge">@Charge <span style="color: red">*</span></label>
                    </td>
                    <td>
                        <input type="text" name="charge" value="0" />
                        <select name="currency">
                            <option value="---">Select Currency</option>
                            <c:forEach var="currency" items="${requestScope['currency']}">
                                <option value="${currency.tcurrencyPk}">${currency.currencyname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="remarks">Remarks</label>
                    </td>
                    <td>
                        <textarea name="remarks" rows="4" cols="20"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="st" value="${param['st']}" />
                        <c:if test="${sessionScope['fromRegular']}">
                            <input type="submit" value="Update First Row" name="addContainer" />
                        </c:if>
                        <input type="submit" value="Add Service" name="addContainer" />
                    </td>
                </tr>
            </table>
        </form>
        <table border="1" style="border-collapse: collapse">
            <thead>
                <tr>
                    <th>Action</th>
                    <th>Service</th>
                    <th>Fleet/Vessel Name</th>
                    <th>Qty</th>
                    <th>Charge</th>
                    <th>Remark</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="container" items="${sessionScope['wo'].workOrderServiceModeDetail}" varStatus="status">
                    <c:if test="${!container.deleted}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${status.index==0 && sessionScope['fromRegular']}">
                                        ---
                                    </c:when>
                                    <c:otherwise>
    <!--                                    <a href="containerMode.jsp?st=${param['st']!=null?param['st']:""}&remove=${container.tservicemodedetailFk}">remove</a>-->
                                        <a href="containerMode.jsp?st=${param['st']!=null?param['st']:""}&remove=${status.index}">remove</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${container.servicemodeDetail.smdname}</td>
                            <td>${container.smddetailname}</td>
                            <td>${container.quantity}</td>
                            <td>${container.currency.currencycode} <fmt:formatNumber groupingUsed="true" maxFractionDigits="2" minFractionDigits="2" value="${container.smdcharge}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${status.index==0 && sessionScope['fromRegular'] && container.smdcharge==null}">
                                        Use "Update First Row" to update this service
                                    </c:when>
                                    <c:otherwise>
                                        ${container.smdremarks}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
        <div id="containerModeList" style="display: none">
            <c:forEach var="container" items="${sessionScope['wo'].workOrderServiceModeDetail}">
                <c:if test="${!container.deleted}">
                    <c:choose>
                        <c:when test="${container.smddetailname!=null}">
                            ${container.servicemodeDetail.smdname}(${container.smddetailname}) x${container.quantity} @${container.currency.currencycode} <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="${container.smdcharge}"/><br/>
                        </c:when>
                        <c:otherwise>
                            ${container.servicemodeDetail.smdname} x${container.quantity} @${container.currency.currencycode} <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="${container.smdcharge}"/><br/>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
        </div>
        <div id="hremarks" style="display: none">
            <c:if test="${sessionScope['wo'].workOrderServiceModeDetail!=null}">
                <ul>
                    <c:forEach var="rem" items="${sessionScope['wo'].workOrderServiceModeDetail}">
                        <c:if test="${!rem.deleted}">
                            <c:if test='${rem.smdremarks!=""}'>
                                <li>
                                    ${rem.smdremarks}
                                </li>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <c:if test="${param['addContainer']!=null||param['remove']!=null}">
            <script type="text/javascript">
                parent.document.getElementById('containerModeList').innerHTML = document.getElementById('containerModeList').innerHTML;
                parent.document.getElementById('charge').innerHTML = '<%=charges%>';
                var sel = parent.document.getElementById('lastAppLevel');
                var opts = sel.options;
                console.log(<%=retApv%>);
                for (var opt, j = 0; opt = opts[j]; j++) {
                    console.log(j);
                    console.log(opt.value);
                    if (opt.value === '<%=retApv%>') {
                        sel.selectedIndex = j;
                        parent.document.getElementById('maxAppLvl').value = opt.value;
                        break;
                    }
                }
                if (parent.document.getElementById('remarks'))
                    parent.document.getElementById('remarks').innerHTML = document.getElementById('hremarks').innerHTML;
            </script>
        </c:if>

    </body>
</html>
