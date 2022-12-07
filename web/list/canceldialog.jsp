<%-- 
    Document   : canceldialog
    Created on : May 26, 2011, 11:38:22 AM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            java.util.List<com.ckb.wo.client.model.Currency> currency = com.ckb.wo.server.service.util.CurrencyLocalServiceUtil.getCurrency();
            request.setAttribute("currency", currency);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            var args = window.dialogArguments;
            
            function submitval(){
                var r = new Array();
                tinyMCE.activeEditor.save();
                r[0]= document.getElementById("reason").value;
                r[1]= document.getElementById("fee").value;
                r[2]=document.getElementById("cur").value;
                if(isNaN(parseFloat(r[1]))){
                    r[1]=null;
                    r[2]=null;
                }
                //alert(r);
                // alert(r);
                window.returnValue = r;
                window.close();
            }
        </script>
        <jsp:include page="../scripts/inc_tiny.jsp"/>
    </head>
    <body>
        Cancel Reason:<br/>
        <textarea cols="20" rows="4" id="reason"></textarea>
        Cancellation Fee<br/>
        <input type="text" name="cancellationfee" id="fee"/>
        <select name="currency" id="cur">
            <option value="---">Select Currency</option>
            <c:forEach var="currency" items="${requestScope['currency']}">
                <option value="${currency.tcurrencyPk}">${currency.currencyname}</option>
            </c:forEach>
        </select><br/>
        <input type="button" value="Submit" onclick="submitval()"/>
        <input type="button" value="Cancel" onclick="window.close();"/>
    </body>
</html>
