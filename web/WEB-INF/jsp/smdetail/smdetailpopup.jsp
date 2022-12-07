<%--
    Document   : servicemodedetail
    Created on : Jul 12, 2010, 7:23:23 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/displaytag/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/displaytag/screen.css" />
<script type="text/javascript" >
    function choose(tservicemodedetailFk,smdcodename){
        opener.document.forms[0].tservicemodedetailFk.value=tservicemodedetailFk;
        opener.document.forms[0].smdcodename.value=smdcodename;
	window.close();
    }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <span >
            <form method="post" action="servicemodedetail?act=popup" >
                Service mode detail code : <input type="text" name="smdcode" /> &nbsp;<input type="submit" value="Find" />
            </form>
        </span>
        <display:table name="servicemodedetail" id="servicemodedetailtable" partialList="true" size="requestScope.count" pagesize="${requestScope.pagesize}" requestURI="servicemodedetail?act=popup" decorator="com.ckb.wo.client.displaytag.JavascriptColumnWrapper" >
            <display:column property="servicemodedetailscriptlink"  title="Service Mode Detail Code"  />
            <display:column property="smdname" title="Service Mode Detail Name" />
        </display:table>

    </body>
</html>
