<%-- 
    Document   : vendorserviceadd
    Created on : Jul 14, 2010, 12:54:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <label for="vendorname">Vendor* : </label> <input type="text" size="30" readonly="true" name="vendorname" id="vendorname" /> <input type="button" value="change" onclick="popup('vendor?act=popup');" />
                <input type="hidden" name="tvendorFk" /> <br/>
                <label for="tservicetypeFk">Service type* : </label>
                <select name="tservicetypeFk" >
                    <option ></option>
                    <c:forEach items="${requestScope.servicetype}" var="varservicetype">
                        <option value="${varservicetype.tservicetypePk}">${varservicetype.servicename}</option>
                    </c:forEach>
                </select><br/>
                <label for="tservicemodeFk">Service mode* : </label>
                <select name="tservicemodeFk" >
                    <option ></option>
                    <c:forEach items="${requestScope.servicemode}" var="varservicemode">
                        <option value="${varservicemode.tservicemodePk}">${varservicemode.smodename}</option>
                    </c:forEach>
                </select><br/>
                <label for="smdcodename">Service* : </label>
                <input type="text" size="30" name="smdcodename" readonly="true" /> <input type="button" value="change" onclick="javascript:popup('servicemodedetail?act=popup');" />
                <input type="hidden" name="tservicemodedetailFk" /><br/>
                <label for="torderFk">Rate Type* : </label>
                <select name="torderFk">
                    <option ></option>
                    <c:forEach items="${requestScope.order}" var="varoder">
                        <option value="${varoder.torderPk}">${varoder.ordername} </option>
                    </c:forEach>
                </select><br/>
                <label for="tdeliverytermFk">Delivery term* : </label>
                <select name="tdeliverytermFk">
                    <option ></option>
                    <c:forEach items="${requestScope.deliveryterm}" var="dterm">
                        <option value="${dterm.tdeliverytermPk}">${dterm.dtcode} ${dterm.dtname}</option>
                    </c:forEach>
                </select><br/>
                <label for="originlocationname" >Origin* </label>
                <input type="text" name="originlocationname" size="30" readonly="true" /> <input type="button" value="change" onclick="javascript:popup('location?act=popup&origin=true');" />
                <input type="hidden" name="origintlocationFk" /><br/>
                <label for="destinationlocationname" >Destination* </label>
                <input type="text" name="destinationlocationname" size="30" readonly="true" /> <input type="button" value="change" onclick="javascript:popup('location?act=popup&destination=true');" />
                <input type="hidden" name="destinationtlocationFk" /><br/>
                <label for="tcurrencyFk">Currency* : </label>
                <select name="tcurrencyFk" >
                    <option></option>
                    <c:forEach items="${requestScope.currency}" var="varcurrency">
                        <option value="${varcurrency.tcurrencyPk}">${varcurrency.currencycode} ${varcurrency.currencyname}</option>
                    </c:forEach>
                </select><br/>
                <label for="rate" >Rate* : </label>
                <input type="text" name="rate" size="20" /><br/>
                <label for="validform">Valid from* : </label>
                <input type="text" name="valfrom" size="30" readonly />
                <input type="hidden" name="validfrom" />
                <br/>
                <label for="validto">Valid to* : </label>
                <input type="text" name="valto" size="30" readonly/>
                <input type="hidden" name="validto" /><br/>
                <label for="weightfrom">Weight from : </label>
                <input type="text" name="weightfrom" size="30" /><br/>
                <label for="weightform">Weight to : </label>
                <input type="text" name="weightto" size="30" /><br/>
                <label for="minimumweight">Minimum weight : </label>
                <input type="text" name="minimumweight" size="30" /><br/>
                <label for="refagreementno">Ref agreement no* : </label>
                <input type="text" name="refagreementno" size="30" /><br/>
                <label for="refagreementdate">Ref agreement date* : </label>
                <input type="text" name="radate" size="30" readonly />
                <input type="hidden" name="refagreementdate" /><br/>
                <label for="remarks">Remarks : </label>
                <textarea name="remarks"></textarea><br/>
                <label for="enabled">Enable : </label>
                <input type="checkbox" name="enabled" value="true" checked="<c:if test="${requestScope.vendorservice.enabled}">checked</c:if>"/><br>
                <input type="submit" value="save" />
                <input type="button" value="cancel" onClick="javascript:history.back(-1)" />
            </fieldset>
        </form>
    </body>
</html>
