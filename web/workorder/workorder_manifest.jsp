<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (!ub.isLogon()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
        }
    } else {
        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
    }
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
        <script type="text/javascript">
            function SelectlastLevel() {
                var sel = document.getElementById('lastAppLevelId');
                var val = ${sessionScope['levelapproval']};
                // alert(val);
                for (var i = 0, j = sel.options.length; i < j; ++i) {
//                    alert(sel.options[i].value);
                    if (sel.options[i].value === val) {
                        sel.selectedIndex = i;
                        break;
                    }
                }
            }
        </script>
        <jsp:include page="../inc_sxlightbox.jsp"/>
        <jsp:include page="../scripts/inc_tiny.jsp"/>
    </head>
    <body onload="SelectlastLevel();">
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
                                                                            <u><h1>Step 4: ${ sessionScope['wo'].wostatus == "EDIT" ? "Edit" : sessionScope['wo'].wostatus == "REVISION" ? "Revision" : "Create"} ${sessionScope['wo'].adhoc?"Adhoc":"Regular"} Delivery Work Order - Manifests</h1></u>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <table cellpadding="3">
                                                                                <tr>
                                                                                    <td>
                                                                                        <h2>Manifest Information</h2>
                                                                                        <c:if test="${requestScope['errorMessage']!=null}">
                                                                                            <div style="border:dotted red thin">
                                                                                                ${requestScope['errorMessage']}
                                                                                            </div>
                                                                                        </c:if>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>
                                                                                        <form action="workorder.do" method="post">
                                                                                            Single Manifest <input type="text" name="manifest" value="" id="manifest" />
                                                                                            <script type="text/javascript">
                                                                                                deflink = 'manifestLookup.jsp?manifest=';
                                                                                                defParam = '&TB_iframe=1&width=700&height=' + (getHeight() - 90) + '&modal=1&title=Manifest Lookup';
                                                                                                document.write('<a href="' + deflink + defParam + '" rel="seylightbox" onclick="this.href=deflink+document.getElementById(\'manifest\').value+defParam"><img border="0" type="image" src="<%= getServletContext().getContextPath()%>/images/icons/search_16.png"/> Lookup</a>');
                                                                                            </script> <input type="submit" value="Add" name="manAdd" />
                                                                                        </form>
                                                                                    </td>                                                                                
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>
                                                                                        <form action="workorder.do" method="post" enctype="multipart/form-data">
                                                                                            Bulk Manifest <input type="file" name="bulkManifest"/><input type="submit" name="uploadManifest" value="Upload"/>
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
                                                                                                            action
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Manifest#
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Origin
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Destination
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Actual Shipment
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Package
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Weight (Kg)
                                                                                                        </th>
                                                                                                        <th>
                                                                                                            Volume (M<sup>3</sup>)
                                                                                                        </th>
                                                                                                    </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                    <c:forEach var="manifest" items="${sessionScope['manifests']}">
                                                                                                        <tr>
                                                                                                            <td>
                                                                                                                <a href="workorder.do?manRemove=${manifest.manifest_no}">remove</a>
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${manifest.manifest_no}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${manifest.ori}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${manifest.dest}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                <fmt:formatDate value="${manifest.actual_ship_date==null ? manifest.ship_date : manifest.actual_ship_date}"/>
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                ${manifest.jmlh_da}
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${manifest.weight}"/>
                                                                                                            </td>
                                                                                                            <td>
                                                                                                                <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${manifest.volume}"/>
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
                                                                                        <form action="workorder.do" method="post">
                                                                                            <table cellpadding="3">
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Total Weight
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${sessionScope['wo'].totalweight}"/> Kg
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        Total Volume
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${sessionScope['wo'].totalvolume}" /> M<sup>3</sup>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td style="vertical-align: top">
                                                                                                        Charge
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <%
                                                                                                            com.ckb.wo.client.model.WorkOrder wo = (com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo");
                                                                                                            if (!wo.getAdhoc()) {
                                                                                                        %>
                                                                                                        ${sessionScope['wo'].currency.currencycode} <fmt:formatNumber groupingUsed="true"  minFractionDigits="2" maxFractionDigits="2" value="${sessionScope['wo'].charge}"/>
                                                                                                        <%  } else {
                                                                                                                out.print(com.ckb.wo.client.listener.WorkOrderListener.getCharges(((com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo")).getWorkOrderServiceModeDetail()));
                                                                                                            }
                                                                                                        %>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <c:if test="${sessionScope.wo.currency != null && sessionScope.wo.currency.currencycode != 'IDR'}">
                                                                                                    <tr>
                                                                                                        <td>IDR Charge</td>
                                                                                                        <td><fmt:setLocale value = "id_ID"/><fmt:formatNumber value="${sessionScope.equivalentIDR}" type="currency" currencySymbol="IDR "/></td>
                                                                                                    </tr>
                                                                                                </c:if>
                                                                                                <tr>
                                                                                                    <td style="vertical-align: top">
                                                                                                        Weight Override
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <%
                                                                                                            com.ckb.wo.client.model.WorkOrder wos = (com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo");
                                                                                                            if (wos.getTservicemodeFk() != null) {
                                                                                                                if (wos.getTservicemodeFk() == 3 && !wos.getAdhoc()) {
                                                                                                        %>

                                                                                                        <%
                                                                                                                    out.print("<input type='text' name='override' value=0 style='text-aligh:right;'/>");
                                                                                                                }
                                                                                                            }
                                                                                                        %>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td style="vertical-align: top">
                                                                                                        Last Approval <span style="color: red">*</span>
                                                                                                    </td>
                                                                                                    <td valign="top">
                                                                                                        <select name="lastAppLevel" id='lastAppLevelId' disabled>
                                                                                                            <option value="---">Select Last Approval</option>
                                                                                                            <c:forEach var="level" items="${requestScope['levels']}">
                                                                                                                <option value="${level.levelValue}" ${sessionScope['wo'].maxlevel==level.levelValue?"selected":""}>${level.description}</option>
                                                                                                            </c:forEach>
                                                                                                        </select><br/><br/>
                                                                                                        <input type="hidden" name="lastAppLevelHide" value="${sessionScope['levelapproval']!=null? sessionScope['levelapproval']:sessionScope['wo'].maxlevel}" />
                                                                                                        <table border="1" style="border-collapse: collapse">
                                                                                                            <thead>
                                                                                                                <tr>
                                                                                                                    <th>Amount Project (IDR)</th>
                                                                                                                    <th>Amount Regular (IDR)</th>
                                                                                                                    <th>Last Approval</th>
                                                                                                                </tr>
                                                                                                            </thead>
                                                                                                            <tbody>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="500000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="10000000"/></td>
                                                                                                                    <td>Manager</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="750000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="50000000"/></td>
                                                                                                                    <td>Senior Manager</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="1500000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="250000000"/></td>
                                                                                                                    <td>General Manager</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="5000000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="750000000"/></td>
                                                                                                                    <td>Director 1</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&gt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="5000000000"/></td>
                                                                                                                    <td style="text-align: right">&le; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="1000000000"/></td>
                                                                                                                    <td>Director 2</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td style="text-align: right">&nbsp;</td>
                                                                                                                    <td style="text-align: right">&gt; <fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" value="1000000000"/></td>
                                                                                                                    <td>President Director</td>
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
                                                                                                        <textarea cols="20" rows="4" name="goodsDesc">${sessionScope['wo'].gooddescription}</textarea>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Remarks
                                                                                                    </td>
                                                                                                    <td>
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
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td valign="top">
                                                                                                        Delivery Note
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <textarea cols="20" rows="4" name="deliveryNote" id="deliveryNote">
                                                                                                            <c:if test="${sessionScope['wo'].tworkorderPk==null}">
                                                                                                                Wajib membawa dan mengenakan Safety Tools(helmet, safety shoes, glasses, hand gloves)<br/>
                                                                                                            </c:if>
                                                                                                            ${sessionScope['wo'].deliverynote}
                                                                                                        </textarea>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <%
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
                                                                                                <%      }
                                                                                                    }
                                                                                                %>
                                                                                            </table>
                                                                                            <input type="button" value="Back To Main" onclick="document.location.href = '<%= application.getContextPath()%>/main.jsp'" /><input type="button" value="Restart" onclick="document.location.href = 'workorder.do'" /><input type="submit" value="Back" name="${sessionScope['wo'].adhoc?"adhoc":"regular"}ManifestBack"/><input type="submit" value="Save" name="${sessionScope['wo'].adhoc?"adhoc":"regular"}Save" />
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
