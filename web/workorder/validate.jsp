<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 
    Document   : validate
    Created on : Jul 15, 2010, 3:27:03 PM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Validate WO</title>
        <%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                }
                if (!com.ckb.wo.server.service.util.UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId())) {
                    out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                }
            } else {
                out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
            }
        %>
        <link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/scripts/jquery.datepick.css"/>
        <script type="text/javascript" src="<%=application.getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath()%>/scripts/jquery.datepick.js"></script>
        <script type='text/javascript' src='<%=application.getContextPath()%>/dwr/interface/WorkOrder.js'></script>
        <script type='text/javascript' src='<%=application.getContextPath()%>/dwr/engine.js'></script>
        <jsp:include page="../scripts/inc_tiny.jsp"/>
        <script type="text/javascript">
            $(function() {
                $(".datepicker").datepick({
                    dateFormat: 'MM d, yy',
                    altField: '#isoDate',
                    altFormat: 'yy-mm-dd',
                    selectDefaultDate: true
                });
            });
            function sedit() {
                var reason = prompt("Enter edit reason", "");
                if (reason)
                    WorkOrder.edit(${sessionScope['vWo'].tworkorderPk}, reason, {
                        callback: function(result) {
                            if (result) {
                                alert('Document Sent for edit!');
                                parent.SeyLightbox.close();
                            } else {
                                alert('Edit failed!');
                            }
                        },
                        async: false
                    });
                parent.loadLast();
            }
            //            function cancel(){
            //                var reason=prompt("Enter cancel reason", "");
            //                if(reason)
            //                    WorkOrder.cancel(${param['wo']},reason,{
            //                        callback:function(result){
            //                            if(result){
            //                                alert('Document Canceled!');
            //                                parent.SeyLightbox.close();
            //                            }else{
            //                                alert('Cancelation Failed!');
            //                            }
            //                    },
            //                    async:false
            //                });
            //                parent.load();
            //            }
        </script>
    </head>
    <body>
        <form name="validateform" action="validate.do" method="POST">
            <table border="0">
                <tr>
                    <td>Origin</td>
                    <td >${sessionScope['vWo'].origin.locationname}</td>
                </tr>
                <tr>
                    <td>Destination</td>
                    <td>${sessionScope['vWo'].destination.locationname}</td>
                </tr>
                <c:if test="${sessionScope['vRemarks'] !=null}">
                    <tr>
                        <td>Reason to Choose</td>
                        <td>${sessionScope['vRemarks']}</td>
                    </tr>
                </c:if>
                <c:forEach var="wsmd" items="${sessionScope['vWo'].workOrderServiceModeDetail}">
                    <c:if test="${!wsmd.deleted}">
                        <tr>
                            <td>
                                ${wsmd.servicemodeDetail.smdname} x${wsmd.quantity}
                                <input type="hidden" name="hqty" value="${wsmd.quantity}" />
                            </td>
                            <td>
                                @${wsmd.currency.currencycode} <input type="text" name="wsmd" value="${wsmd.smdcharge}"<%-- onchange="recalculate()"--%>style="background-color: #fff;" />
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                <%--<tr>
                    <td>Charge</td>
                    <td style="border-top: black solid thin">${sessionScope['vWo'].currency.currencycode} <span id="charge"><fmt:formatNumber groupingUsed="true" maxFractionDigits="2" minFractionDigits="2" value="${sessionScope['vWo'].charge}"/></span<input type="hidden" id="hcharge" name="hcharge" value="${sessionScope['vWo'].charge}" /> </td>
                </tr>--%>
                <tr>
                    <td>PPN</td>
                    <td><input type="text" name="ppn" value="${sessionScope['vWo'].ppn}" size="5" />%</td>
                </tr>
                <tr>
                    <td>PPH</td>
                    <td><input type="text" name="pph" value="${sessionScope['vWo'].pph}" size="5" />%</td>
                </tr>
                <tr>
                    <td colspan="2">Delivery Note:</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="deliveryNote" rows="4" cols="20">${sessionScope['vWo'].deliverynote}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">Validation Note:</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="vNote" rows="4" cols="20"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>Rate Agreement#</td>
                    <td><input type="text" name="agreementno" value="${sessionScope['vWo'].agreementno}"/></td>
                </tr>
                <tr>
                    <td>Rate Agreement Date</td>
                    <td>
                        <input type="text" name="agreementdate" id="agreementdate" class="datepicker"  value="<fmt:formatDate pattern="MMMM d, yyyy" value="${sessionScope['vWo'].agreementdate}"/>"/>
                        <input type="hidden" id="isoDate" name="hagreementdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope['vWo'].agreementdate}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Created By</td>
                    <td>
                        <%= com.ckb.wo.report.ReportHelper.getCreatorName(((com.ckb.wo.client.model.WorkOrder) session.getAttribute("vWo")).getCreatedbyemployeeid())%>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" name="validate" value="Validate" />
                        <input type="button" name="edit" value="Return to Creator For Edit!" onclick="sedit()" />
                        <!--                        <input type="button" name="bcancel" value="Cancel This Document!" onclick="cancel()" />-->
                        <input type="button" name="close" value="Close" onclick="parent.SeyLightbox.close()" />
                    </td>
                </tr>
            </table>
        </form>
        <c:if test="${requestScope['saved']}">
            <script type="text/javascript">
            alert('Document Validated!');
            parent.loadLast();
            parent.SeyLightbox.close();
            </script>
        </c:if>
    </body>
</html>
