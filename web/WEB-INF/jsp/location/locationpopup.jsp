<%--
    Document   : location
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
    function choose(tlocationFk,locationname){
        var type = '<%=request.getAttribute("type")%>';
        if(type=="origin"){
            opener.document.forms[0].origintlocationFk.value=tlocationFk;
            opener.document.forms[0].originlocationname.value=locationname;

        }else{
            opener.document.forms[0].destinationtlocationFk.value=tlocationFk;
            opener.document.forms[0].destinationlocationname.value=locationname;
        }
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
            <form method="post" action="location?act=popup&${requestScope.type}=true" >
                Location name : <input type="text" size="40" name="locationname" /> &nbsp;<input type="submit" value="Find" />
            </form>
        </span>
        <display:table name="location" id="locationtable" partialList="true" size="requestScope.count" pagesize="${requestScope.pagesize}" requestURI="location?act=popup&${requestScope.type}=true" decorator="com.ckb.wo.client.displaytag.JavascriptColumnWrapper" >
            <display:column property="locationscriptlink" title="" maxLength="10" />
            <display:column property="locationcode"  title="Location Code"  />
            <display:column property="locationname" title="Location Name" />
        </display:table>

    </body>
</html>
