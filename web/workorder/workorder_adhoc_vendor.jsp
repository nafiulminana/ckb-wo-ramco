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
        <jsp:include page="../inc_sxlightbox.jsp"/>
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
                                                                <table border="0">
                                                                    <tr>
                                                                        <td>
                                                                            <u><h1>Step 3: ${ sessionScope["wo"].wostatus == "EDIT" ? "Edit" : sessionScope['wo'].wostatus == "REVISION" ? "Revision" : "Create"} ${sessionScope['wo'].adhoc?"Adhoc":"Regular"} Delivery Work Order - Select Vendor</h1></u>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <form action="workorder.do" method="post">
                                                                                <table cellpadding="3">
                                                                                    <tr>
                                                                                        <td colspan="2">
                                                                                            <h2>Work Order Information</h2>
                                                                                            <c:if test="${requestScope['errorMessage']!=null}">
                                                                                                <div style="border:dotted red thin">
                                                                                                    ${requestScope['errorMessage']}
                                                                                                </div>
                                                                                            </c:if>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Vendor <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select name="vendor">
                                                                                                <option value="---">Select Vendor</option>
                                                                                                <c:forEach var="vendor" items="${requestScope['vendors']}">
                                                                                                    <option value="${vendor.tvendorPk}" ${vendor.tvendorPk==sessionScope['wo'].tvendorFk?"selected":""}>${vendor.vendorname}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td valign="top">
                                                                                            Service <span style="color: red">*</span>
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
                                                                                            <a href="#" onclick="SeyLightbox.display('containerMode.jsp?st=1TB_iframe=1&width=500&height=' + (GetHeight() - 90) + '&modal=1&title=Container Mode')"><img border="0" type="image" src="<%= getServletContext().getContextPath()%>/images/icons/pencil_16.png"/> Add</a>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td style="vertical-align: top">
                                                                                            Charge <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <span id="charge">
                                                                                                <%
                                                                                                    boolean fromRegular = false;
                                                                                                    try {
                                                                                                        fromRegular = (Boolean) session.getAttribute("fromRegular");
                                                                                                    } catch (Exception e) {
                                                                                                    }
                                                                                                    try {
                                                                                                        if (fromRegular && ((com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo")).getWorkOrderServiceModeDetail().size() == 1) {
                                                                                                %>
                                                                                                <input type="text" name="charge" value="${sessionScope['wo'].charge}" />
                                                                                                <select name="currency">
                                                                                                    <option value="---">Select Currency</option>
                                                                                                    <c:forEach var="currency" items="${requestScope['currency']}">
                                                                                                        <option value="${currency.tcurrencyPk}" ${currency.tcurrencyPk==sessionScope['wo'].tcurrencyFk?"selected":""}>${currency.currencyname}</option>
                                                                                                    </c:forEach>
                                                                                                </select>
                                                                                                <%      } else {
                                                                                                            out.print(com.ckb.wo.client.listener.WorkOrderListener.getCharges(((com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo")).getWorkOrderServiceModeDetail()));
                                                                                                        }
                                                                                                    } catch (NullPointerException npe) {
                                                                                                        out.print("--Select Container--");
                                                                                                    }
                                                                                                %>
                                                                                            </span>
                                                                                        </td>

                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="2" align="center">
                                                                                            <input type="button" value="Back To Main" onclick="document.location.href = '<%= application.getContextPath()%>/main.jsp'" /><input type="button" value="Restart" onclick="document.location.href = 'workorder.do'" /><c:if test="${!sessionScope.fromRegular}"><input type="submit" value="Back" name="adhocvendorBack"/></c:if><input type="submit" value="Next" name="adhocvendorNext" />
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </form>
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
