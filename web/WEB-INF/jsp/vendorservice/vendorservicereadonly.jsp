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
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/form/form.css" />
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/displaytag/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/displaytag/screen.css" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <span >
            <form method="post" action="vendorservice" >
                <label for="vendorname">Vendor name : </label>
                <input type="text" size="40" name="vendorname" value="${requestScope.vendorserviceparam.vendor.vendorname}" /> <br/>
                <label for="originlocationname" >Origin</label>
                <select name="origintlocationFk">
                    <option></option>
                    <c:forEach items="${requestScope.location}" var="varlocation">
                        <option value="${varlocation.tlocationPk}" <c:if test="${varlocation.tlocationPk==requestScope.vendorserviceparam.origintlocationFk}">selected</c:if>  >${varlocation.locationname} </option>
                    </c:forEach>
                </select> <br/>
                <label for="destinationlocationname" >Destination </label>
		   <select name="destinationtlocationFk">
                       <option></option>
                    <c:forEach items="${requestScope.location}" var="varlocation">
                        <option value="${varlocation.tlocationPk}"  <c:if test="${varlocation.tlocationPk==requestScope.vendorserviceparam.destinationtlocationFk}">selected</c:if>  >${varlocation.locationname} </option>
                    </c:forEach>
				</select>
				<br />
                <label for="tservicemodedetail_fk" >Jenis Armada</label>
                <select name="tservicemodedetail_fk">
                    <option></option>
                    <c:forEach items="${requestScope.servicemodedetail}" var="varservicemodedetail">
                        <option value="${varservicemodedetail.tservicemodedetailPk}" <c:if test="${varservicemodedetail.tservicemodedetailPk==requestScope.vendorserviceparam.tservicemodedetailFk}">selected</c:if> >${varservicemodedetail.smdname} </option>
                    </c:forEach>
                </select>

                <input type="submit" src="<%=getServletContext().getContextPath()%>/images/cari.png" value="Find" />

            </form>
        </span>
        <display:table name="vendorservice" id="vendorservicetable" partialList="true" size="requestScope.count" pagesize="${requestScope.pagesize}" requestURI="vendorservice" sort="external">
            <display:column property="vendor.vendorname"  paramId="tvendorservicePk" paramProperty="tvendorservicePk" title="Vendor" sortable="true" sortName="vendorname" headerClass="sortable" />
            <display:column property="currency.currencycode" title="Currency" sortable="true" sortName="currencycode"  headerClass="sortable" />
            <display:column property="rate" title="Rate" sortable="true" sortName="rate" format="{0,number,#,###.#######}" headerClass="sortable" />
            <display:column property="serviceType.servicename" title="Service Type" sortable="true" sortName="servicename" headerClass="sortable" />
            <display:column property="serviceMode.smodename" title="Service Mode" sortable="true" sortName="smodename" headerClass="sortable" />
            <display:column property="serviceModeDetail.smdname" title="Service" sortable="true" sortName="smdname" headerClass="sortable" />
            <display:column property="remarks" title="Remarks" sortable="true" sortName="remarks" headerClass="sortable" style="white-space:nowarp;" />
            <display:column property="order.ordername" title="Rate Type" sortable="true" sortName="ordername" headerClass="sortable" />
            <display:column property="deliveryTerm.dtname" title="Delivery Term" sortable="true" sortName="dtname" headerClass="sortable" />
            <display:column property="originLocation.locationname" title="origin" sortable="true" sortName="originlocationname" headerClass="sortable" />
            <display:column property="destinationLocation.locationname" title="destination" sortable="true" sortName="destinationlocationname" headerClass="sortable" />
            <display:column property="validfrom" title="Valid from" sortable="true" sortName="validfrom" format="{0,date,MMM dd yyyy}" headerClass="sortable" />
            <display:column property="validto" title="Valid to" sortable="true" sortName="validto" format="{0,date,MMM dd yyyy}" headerClass="sortable" />
            <display:column property="weightfrom" title="Weight from" sortable="true" sortName="weightfrom" format="{0,number, ###.#######}" headerClass="sortable" />
            <display:column property="weightto" title="Weight to" sortable="true" sortName="weighto" format="{0,number,###.#######}" headerClass="sortable" />
            <display:column property="refagreementno" title="Ref Agreement no." sortable="true" sortName="refagreementno" headerClass="sortable" />
            <display:column property="refagreementdate" title="Ref Agreement date" sortable="true" sortName="refagreementdate" format="{0,date,MMM dd yyyy}" headerClass="sortable" />
        </display:table>
    </body>
</html>
