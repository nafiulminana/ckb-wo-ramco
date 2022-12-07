<%--
    Document   : vendorservice
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
    function choose(tvendorPk,vendorname){
        opener.document.forms[0].tvendorFk.value=tvendorPk;
        opener.document.forms[0].vendorname.value=vendorname;

	//opener.document.form.caption.value=caption;
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
            <form method="post" action="vendor?act=popup" >
                Vendor name : <input type="text" size="40" name="vendorname" /> &nbsp;<input type="submit" value="Find" />
            </form>
        </span>
        <display:table name="vendor" id="vendortable" partialList="true" size="requestScope.count" pagesize="${requestScope.pagesize}" requestURI="vendor?act=popup" decorator="com.ckb.wo.client.displaytag.JavascriptColumnWrapper" requestURIcontext="false">
            <display:column property="vendorscriptlink"  title="Vendor Name"  />
        </display:table>

    </body>
</html>
