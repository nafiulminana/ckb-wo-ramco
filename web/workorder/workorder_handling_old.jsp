<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    com.ckb.wo.client.model.UserBeans ub =
            (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (!ub.isLogon()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");

        }
    } else {
        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
    }
    java.util.List<com.ckb.wo.client.model.Vendor> vendors = com.ckb.wo.server.service.util.VendorLocalServiceUtil.getVendor();
    request.setAttribute("vendors", vendors);
    java.util.List<com.ckb.wo.client.model.Currency> currency = com.ckb.wo.server.service.util.CurrencyLocalServiceUtil.getCurrency();
    request.setAttribute("currency", currency);
    java.util.List<com.ckb.wo.client.model.Level> levels = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.getApprovalLevelForDropDown();
    request.setAttribute("levels", levels);
    java.util.List<com.ckb.wo.client.model.Location> location = com.ckb.wo.server.service.util.LocationLocalServiceUtil.getLocation();
    request.setAttribute("location", location);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" xmlns:wdg="http://ns.adobe.com/addt">
    <head>
        <title><%= application.getInitParameter("appTitle")%></title>
        <link rel="stylesheet" href="../main.css" type="text/css" />
        <meta name="author" content="Chandra Adriansyah" />
        <meta name="programmer" content="Amaran Sidhiq" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
        <link rel="stylesheet" href="../images/style.css" type="text/css" />

        <style type="text/css">
            @import "<%=getServletContext().getContextPath()%>/scripts/jquery.datepick.css";

            <!--
            body {
                margin-left: 10px;
                margin-right: 10px;
            }
            .style5 {
                font-size: 16px;
                font-weight: bold;
            }
            .wawa {
                color: #FFF;
            }
            .qa {
                color: #000;
                font-weight: bold;
            }
            .style14 {
                font-size: 10px;
                color: #0000FF;
            }
            .style15 {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 10px;
            }
            .style16 {color: #FF0000}
            table,td
            {
                border-collapse      : collapse;
                text-align:left;
                font                 : small/1.5 "Tahoma", "Bitstream Vera Sans", Verdana, Helvetica, sans-serif;
            }
            .tip-target {
                text-align:center;
                padding: 5px 0;
                color: #15428b;
                margin:10px;
                float:inherit;
            }
            -->
        </style>
        <jsp:include page="../inc_sxlightbox.jsp"/>
        <jsp:include page="../scripts/inc_tiny.jsp"/>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery.datepick.js"></script>

        <script type="text/javascript">
            $(function(){
                $(".datepicker").datepick({
                    dateFormat: 'MM d, yy',
                    altField: '.isoDate',
                    altFormat: 'yy-mm-dd',
                    selectDefaultDate: true
                });
            });
            function lookup(){
                var l=$('#loc').val();
                var v=$('#ven').val();
                if(v==='---'){
                    alert('Vendor Not Selected!');return;
                }
                if(l==='---'){
                    alert('Location Not Selected!');return;
                }
                var add='&l='+l+"&v="+v;
                SeyLightbox.display('daLookup.jsp?da='+document.getElementById('damps').value+add+'&TB_iframe=1&width=700&height='+(getHeight()-90)+'&modal=1&title=DA/MPS Lookup');
            }
            function openContainer(){
                SeyLightbox.display('containerMode.jsp?st=2&TB_iframe=1&width=500&height='+(getHeight()-90)+'&modal=1&title=Edit Container Mode');
            }
        </script>
    </head>
    <body>
        <div class="content">
            <!-- header -->
            <jsp:include page="../header.jsp"/>
            <!-- end header -->

            <div>
                <div class="uedge">
                    <div class="redge">
                        <div class="bedge">
                            <div class="ledge">
                                <div class="ulcorner">
                                    <div class="urcorner">
                                        <div class="blcorner">
                                            <div class="brcorner">
                                                <div class="innercontent">
                                                    <table width="100%" border="0" bgcolor="#FFFFFF">

                                                        <tr>
                                                            <td>
                                                                <table border="0" cellpadding="3">
                                                                    <tr>
                                                                        <td>
                                                                            <u><h1>Step 2: ${ sessionScope['wo'].wostatus == "EDIT" ? "Edit" : sessionScope['wo'].wostatus == "REVISION" ? "Revision" : "Create"} Handling Delivery Work Order - Delivery Advice/MPS</h1></u>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <table cellpadding="3">
                                                                                <tr>
                                                                                    <td>
                                                                                        <h2>Input Handling Information</h2>
                                                                                        <c:if test="${requestScope['errorMessage']!=null}">
                                                                                            <div style="border:dotted red thin">
                                                                                                ${requestScope['errorMessage']}
                                                                                            </div>
                                                                                        </c:if>
                                                                                        <c:if test="${sessionScope['wo'].editreason!=null}">
                                                                                            <div style="border:dotted black thin">
                                                                                                <b>Edit Reason:</b>${sessionScope['wo'].editreason}
                                                                                            </div>
                                                                                        </c:if>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>
                                                                                        <form action="handling.do" method="post">
                                                                                            DA/MPS# <input type="text" name="damps" value="" id="damps" /><a href="#" onclick="lookup()"><img border="0" type="image" src="<%= getServletContext().getContextPath()%>/images/icons/search_16.png"/> Lookup</a> <input type="submit" value="add" name="daAdd"/>
                                                                                            <i style="font-size: smaller">(select <b>vendor</b> and <b>location</b> first before adding DA/MPS#)</i>
                                                                                            <input type="hidden" name="loc" id="hloc" value="${sessionScope['wo'].origintlocationFk}"/>
                                                                                            <input type="hidden" name="ven" id="hven" value="${sessionScope['wo'].tvendorFk}"/>
                                                                                            <input type="hidden" name="hlvl" id="hlvl" value="${sessionScope['wo'].maxlevel}"/>
                                                                                            <input type="hidden" name="edate" class="isoDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope['wo'].executiondate}"/>"/>
                                                                                        </form>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>
                                                                                        <div>
                                                                                            <table  border="1" style="border-collapse: collapse" cellpadding="3">
                                                                                                <thead>
                                                                                                    <tr>
                                                                                                        <th>
                                                                                                            Action
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            DA#
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            origin
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            destination
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            package
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            weight (Kg)
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            volume (m<sup>3</sup>)
                                                                                                        </th>
                                                                                                    </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                    <c:forEach var="da" items="${sessionScope['da']}">
                                                                                                        <tr style="${da.pod?'background-color: #FFEBE8':''}">
                                                                                                            <td>
                                                                                                                <a href="handling.do?remove=${da.da}">remove</a>
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${da.da}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${da.origin}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${da.destination}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${da.totPackage}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${da.totWeight}"/>
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${da.totDim}"/>
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                    </c:forEach>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td colspan="2" align="center">
                                                                                        <form action="handling.do" method="post">
                                                                                            <table cellpadding="3">
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Total Weight
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <%--${sessionScope['wo'].totalweight!=null?sessionScope['wo'].totalweight:"0"}--%>
                                                                                                        <fmt:formatNumber pattern="0.000" value="${sessionScope['wo'].totalweight}"/> Kg
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Total Volume
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <%--${sessionScope['wo'].totalvolume!=null?sessionScope['wo'].totalvolume:"0"}--%>
                                                                                                        <fmt:formatNumber pattern="0.000" value="${sessionScope['wo'].totalvolume}"/> m<sup>3</sup>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Vendor <span style="color: red">*</span>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <select name="vendor" id="ven" onchange="$('#hven').val(this.value)">
                                                                                                            <option value="---">Select Vendor</option>
                                                                                                            <c:forEach var="vendor" items="${requestScope['vendors']}">
                                                                                                                <option value="${vendor.tvendorPk}" ${vendor.tvendorPk==sessionScope['wo'].tvendorFk?"selected":""}>${vendor.vendorname}</option>
                                                                                                            </c:forEach>
                                                                                                        </select>

                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Action Date <span style="color: red">*</span>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <input type="text" id="actionDate" value="<fmt:formatDate pattern="MMMM d, yyyy" value="${sessionScope['wo'].executiondate}"/>" class="datepicker" onblur="$('.isoDate').val(this.value);"/>
                                                                                                        <input type="hidden" name="actionDate" class="isoDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope['wo'].executiondate}"/>"/>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Location <span style="color: red">*</span>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <select name="location" id="loc" onchange="$('#hloc').val(this.value)">
                                                                                                            <option value="---">Select Location</option>
                                                                                                            <c:forEach var="location" items="${requestScope['location']}">
                                                                                                                <option value="${location.tlocationPk}" ${location.tlocationPk==sessionScope['wo'].origintlocationFk?"selected":""}>${location.locationname}</option>
                                                                                                            </c:forEach>
                                                                                                        </select>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Handling Service <span style="color: red">*</span>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <div id="containerModeList">
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
                                                                                                                    <%--${container.servicemodeDetail.smdname} x${container.quantity}<br/>--%>
                                                                                                                </c:if>
                                                                                                            </c:forEach>
                                                                                                        </div>
                                                                                                        <a href="#" onclick="openContainer()"><img border="0" type="image" src="<%= getServletContext().getContextPath()%>/images/icons/pencil_16.png"/> Add</a>

                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Charge
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <span id="charge">
                                                                                                            <%
                                                                                                                try {
                                                                                                                    out.print(com.ckb.wo.client.listener.WorkOrderListener.getCharges(((com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo")).getWorkOrderServiceModeDetail()));
                                                                                                                } catch (NullPointerException npe) {
                                                                                                                    out.print("--Select Handling--");
                                                                                                                }
                                                                                                            %>
                                                                                                        </span>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Last Approval <span style="color: red">*</span>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <select name="lastAppLevel" onchange="$('#hlvl').val(this.value)">
                                                                                                            <option value="---">Select Last Approval</option>
                                                                                                            <c:forEach var="level" items="${requestScope['levels']}">
                                                                                                                <option value="${level.levelValue}" ${sessionScope['wo'].maxlevel==level.levelValue?"selected":""}>${level.description}</option>
                                                                                                            </c:forEach>
                                                                                                        </select><br/><br/>
                                                                                                        <table border="1" style="border-collapse: collapse">
                                                                                                            <thead>
                                                                                                                <tr>
                                                                                                                    <th>Amount Regular(IDR)</th>
                                                                                                                    <th>Amount Project(IDR)</th>
                                                                                                                    <th>Last Approval</th>
                                                                                                                </tr>
                                                                                                            </thead>
                                                                                                            <tbody>

                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&lt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="10000000"/></td>
                                                                                                                    <td style="text-align: right">&lt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="50000000"/></td>
                                                                                                                    <td>Managers</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="50000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="300000000"/></td>
                                                                                                                    <td>Senior Managers</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&gt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="250000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="1000000000"/></td>
                                                                                                                    <td>General Manager</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&gt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="250000000"/></td>
                                                                                                                    <td style="text-align: right">&gt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="1000000000"/></td>
                                                                                                                    <td>Director</td>
                                                                                                                </tr>
                                                                                                            </tbody>
                                                                                                        </table>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Goods Description
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <textarea cols="20" rows="4" name="goodsDesc"></textarea>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Remarks
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <span id="remarks">
                                                                                                            <c:if test="${sessionScope['wo'].workOrderServiceModeDetail!=null}">
                                                                                                                <ul>
                                                                                                                    <c:forEach var="rem" items="${sessionScope['wo'].workOrderServiceModeDetail}">
                                                                                                                        <c:if test='${rem.smdremarks!=""}'>
                                                                                                                            <li>
                                                                                                                                ${rem.smdremarks}
                                                                                                                            </li>
                                                                                                                        </c:if>
                                                                                                                    </c:forEach>
                                                                                                                </ul>
                                                                                                            </c:if>
                                                                                                        </span>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Delivery Note
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <textarea cols="20" rows="4" name="deliveryNote">
                                                                                                            <c:if test="${sessionScope['wo'].tworkorderPk==null}">
                                                                                                                Wajib membawa dan mengenakan Safety Tools(helmet, safety shoes, glasses, hand gloves)<br/>
                                                                                                            </c:if>
                                                                                                            ${sessionScope['wo'].deliverynote}
                                                                                                        </textarea>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <%
                                                                                                    com.ckb.wo.client.model.WorkOrder wo = (com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo");
                                                                                                    if (wo.getWostatus() != null) {
                                                                                                        if (wo.getWostatus().equals(com.ckb.wo.client.model.WorkOrder.REVISION_STATUS)) {
                                                                                                %>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Revision Reason
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <textarea cols="20" rows="4" name="revisionreason">${sessionScope['wo'].revisionreason}</textarea>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Revision To Vendor
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <textarea cols="20" rows="4" name="revisionreasoneksternal">${sessionScope['wo'].revisionreasoneksternal}</textarea>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <%                                                                                                                }
                                                                                                    }
                                                                                                %>
                                                                                            </table>
                                                                                            <input type="button" value="Back To Main" onclick="document.location.href='<%= application.getContextPath()%>/main.jsp'" /><input type="button" value="Restart" onclick="document.location.href='workorder.do'" /><input type="button" value="Back" onclick="location.href='workorder.do'"/><input type="submit" value="Save" name="handlingSave" />
                                                                                        </form>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>

                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- footer -->
        <jsp:include page="../footer.jsp"/>
        <!-- end footer -->
    </body>
</html>
