<%-- 
    Document   : vendorserviceedit
    Created on : Jul 14, 2010, 12:54:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/form/form.css" />
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/jquery.datepick.css" />
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery.datepick.js"></script>
<%--<jsp:include page="/scripts/inc_tiny.jsp"/>--%>

<script type="text/javascript" >
    function popup(param) {
        var newwin = window.open(param, "winpopup", "width=310,height=600,scrollbars=yes");
    }
    $(function () {
        $("input[name='radate']").datepick({
            dateFormat: 'MM d, yy',
            altField: 'input[name="refagreementdate"]',
            altFormat: 'yy-mm-dd',
            selectDefaultDate: true
        });
        $("input[name='valfrom']").datepick({
            dateFormat: 'MM d, yy',
            altField: 'input[name="validfrom"]',
            altFormat: 'yy-mm-dd',
            selectDefaultDate: true
        });
        $("input[name='valto']").datepick({
            dateFormat: 'MM d, yy',
            altField: 'input[name="validto"]',
            altFormat: 'yy-mm-dd',
            selectDefaultDate: true
        });
    });
    $(function () {
        $('input[type="checkbox"]').change(function () {
            if ($(this).is(":checked")) {
                $(this).val(true);
            } else {
                $(this).val(false);
            }
        });
    });
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <ul>${requestScope.errormessage}</ul>
        <form method="post" action="vendorservice?act=save" >
            <fieldset>
                <input type="hidden" name="tvendorservicePk" value="${requestScope.vendorservice.tvendorservicePk}" />
                <label for="vendorname">Vendor*</label> <input type="text" size="30" readonly="true" name="vendorname" id="vendorname" value="${requestScope.vendorservice.vendor.vendorname}" /> <input type="button" value="change" onclick="popup('vendor?act=popup');" />
                <input type="hidden" name="tvendorFk" value="${requestScope.vendorservice.tvendorFk}"/> <br/>
                <label for="tservicetypeFk">Service type* : </label>
                <select name="tservicetypeFk" >
                    <option ></option>
                    <c:forEach items="${requestScope.servicetype}" var="varservicetype">
                        <option value="${varservicetype.tservicetypePk}" <c:if test="${varservicetype.tservicetypePk==requestScope.vendorservice.tservicetypeFk}">selected</c:if> >${varservicetype.servicename}</option>
                    </c:forEach>
                </select><br/>
                <label for="tservicemodeFk">Service mode* : </label>
                <select name="tservicemodeFk" >
                    <option ></option>
                    <c:forEach items="${requestScope.servicemode}" var="varservicemode">
                        <option value="${varservicemode.tservicemodePk}" <c:if test="${varservicemode.tservicemodePk==requestScope.vendorservice.tservicemodeFk}">selected</c:if> >${varservicemode.smodename}</option>
                    </c:forEach>
                </select><br/>
                <label for="smdcodename">Service* : </label>
                <input type="text" size="30" name="smdcodename" readonly="true" value="${requestScope.vendorservice.serviceModeDetail.smdname}" /> <input type="button" value="change" onclick="javascript:popup('servicemodedetail?act=popup');" />
                <input type="hidden" name="tservicemodedetailFk" value="${requestScope.vendorservice.tservicemodedetailFk}"/><br/>
                <label for="torderFk">Rate Type* : </label>
                <select name="torderFk">
                    <option ></option>
                    <c:forEach items="${requestScope.order}" var="varorder">
                        <option value="${varorder.torderPk}" <c:if test="${varorder.torderPk==requestScope.vendorservice.torderFk}">selected</c:if>>${varorder.ordername} </option>
                    </c:forEach>
                </select><br/>
                <label for="tdeliverytermFk">Delivery term* : </label>
                <select name="tdeliverytermFk">
                    <option ></option>
                    <c:forEach items="${requestScope.deliveryterm}" var="dterm">
                        <option value="${dterm.tdeliverytermPk}" <c:if test="${dterm.tdeliverytermPk==requestScope.vendorservice.tdeliverytermFk}">selected</c:if>>${dterm.dtcode} ${dterm.dtname}</option>
                    </c:forEach>
                </select><br/>
                <label for="originlocationname" >Origin* </label>
                <input type="text" name="originlocationname" size="30" readonly="true" value="${requestScope.vendorservice.originLocation.locationname}"/> <input type="button" value="change" onclick="javascript:popup('location?act=popup&origin=true');" />
                <input type="hidden" name="origintlocationFk" value="${requestScope.vendorservice.origintlocationFk}"/><br/>
                <label for="destinationlocationname" >Destination* </label>
                <input type="text" name="destinationlocationname" size="30" readonly="true" value="${requestScope.vendorservice.destinationLocation.locationname}"/> <input type="button" value="change" onclick="javascript:popup('location?act=popup&destination=true');" />
                <input type="hidden" name="destinationtlocationFk" value="${requestScope.vendorservice.destinationtlocationFk}"/><br/>
                <label for="tcurrencyFk">Currency* : </label>
                <select name="tcurrencyFk" >
                    <option></option>
                    <c:forEach items="${requestScope.currency}" var="varcurrency">
                        <option value="${varcurrency.tcurrencyPk}" <c:if test="${varcurrency.tcurrencyPk==requestScope.vendorservice.tcurrencyFk}">selected</c:if>>${varcurrency.currencycode} ${varcurrency.currencyname}</option>
                    </c:forEach>
                </select><br/>
                <label for="rate" >Rate* : </label>
                <input type="text" name="rate" size="20" value="${requestScope.vendorservice.rate}" /><br/>
                <label for="validform">Valid from* : </label>
                <input type="text" name="valfrom" size="30" readonly value='<fmt:formatDate value="${requestScope.vendorservice.validfrom}" pattern="MMM dd yyyy" />'  />
                <input type="hidden" name="validfrom" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${requestScope.vendorservice.validfrom}" />' />
                <br/>
                <label for="validto">Valid to* : </label>
                <input type="text" name="valto" size="30" readonly value='<fmt:formatDate pattern="MMM dd yyyy" value="${requestScope.vendorservice.validto}" />'/>
                <input type="hidden" name="validto"  value='<fmt:formatDate pattern="yyyy-MM-dd" value="${requestScope.vendorservice.validto}" />' /><br/>
                <label for="weightfrom">Weight from  : </label>
                <input type="text" name="weightfrom" size="30" value='<fmt:formatNumber pattern="#,###.#######" value="${requestScope.vendorservice.weightfrom}" />' /><br/>
                <label for="weightform">Weight to : </label>
                <input type="text" name="weightto" size="30" value='<fmt:formatNumber pattern="#,###.#######" value="${requestScope.vendorservice.weightto}" />' /><br/>
                <label for="minimumweight">Minimum weight : </label>
                <input type="text" name="minimumweight" size="30" value="${requestScope.vendorservice.minimumweight}" /><br/>
                <label for="refagreementno">Ref agreement no* : </label>
                <input type="text" name="refagreementno" size="30" value="${requestScope.vendorservice.refagreementno}" /><br/>
                <label for="radate">Ref agreement date* : </label>
                <input type="text" name="radate" size="30" readonly value='<fmt:formatDate pattern="MMM dd yyyy" value="${requestScope.vendorservice.refagreementdate}" />' />
                <input type="hidden" name="refagreementdate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${requestScope.vendorservice.refagreementdate}" />' /><br/>
                <label for="remarks">Remarks : </label>
                <textarea name="remarks">${requestScope.vendorservice.remarks}</textarea><br/>            
                <label for="enabled">Enable : </label>
                <input type="checkbox" name="enabled" value="${requestScope.vendorservice.enabled}" checked="<c:if test="${requestScope.vendorservice.enabled}">checked</c:if>"/><br>
                <input type="submit" value="save" />
                <input type="button" value="cancel" onClick="javascript:history.back(-1)" />
            </fieldset>
        </form>
    </body>
</html>
