<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" xmlns:wdg="http://ns.adobe.com/addt">
    <head>
        <title><%= application.getInitParameter("appTitle") %></title>
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
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/dwr/interface/WorkOrder.js"></script>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/dwr/engine.js"></script>
        <script type="text/javascript">

            function getDetail(pk){
                if(isNaN(pk)){
                    var def="---";
                    document.getElementById('aggNo').innerHTML=def;
                    document.getElementById('aggDate').innerHTML=def;
                    return;
                }
                WorkOrder.getRateAggreement(pk,{
                    callback:function(result){
                        document.getElementById('aggNo').innerHTML=result;
                    }
                });
                WorkOrder.getRateAggreementDateString(pk,{
                    callback:function(result){
                        document.getElementById('aggDate').innerHTML=result;
                    }
                });
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
                                                                <table border="0">
                                                                    <tr>
                                                                        <td>
                                                                            <u><h1>Step 3: ${ sessionScope['wo'].wostatus == "EDIT" ? "Edit" : sessionScope['wo'].wostatus == "REVISION" ? "Revision" : "Create"} Regular Delivery Work Order - Select Vendor</h1></u>
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
                                                                                            <select name="vendorrate" onchange="getDetail(this.options[this.selectedIndex].value)">
                                                                                                <option value="---">Select Vendor</option>
                                                                                                <c:forEach var="vendor" items="${requestScope['vendors']}">
                                                                                                    <option value="${vendor.tvendorPk}" ${vendor.tvendorPk==sessionScope['wo'].tvendorFk?"selected":""}>${vendor.vendorname} - ${vendor.address2}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                            <input type="hidden" name="qty" value="1" size="4" maxlength="4" name="qty"/>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Service
                                                                                        </td>
                                                                                        <td>
                                                                                            ${sessionScope['serviceModeDetail'].smdname}
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Currency <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select name="currency" id="currency">
                                                                                                <option value="---">Select Currency</option>
                                                                                                <c:forEach var="currency" items="${requestScope['currency']}">
                                                                                                    <option value="${currency.tcurrencyPk}" ${sessionScope['wo'].tcurrencyFk==currency.tcurrencyPk?"selected":""}>${currency.currencyname}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Rate Aggreement#
                                                                                        </td>
                                                                                        <td>
                                                                                            <div id="aggNo">---</div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Rate Aggreement Date
                                                                                        </td>
                                                                                        <td>
                                                                                            <div id="aggDate">---</div>
                                                                                        </td>
                                                                                    </tr>
                                                                                      
                                                                                    <tr>
                                                                                        <td colspan="2" align="center">
                                                                                            <input type="button" value="Back To Main" onclick="document.location.href='<%= application.getContextPath() %>/main.jsp'" /><input type="button" value="Restart" onclick="document.location.href='workorder.do'" /><input type="submit" value="Back" name="regularvendorBack"/><input type="submit" value="Next" name="regularvendorNext" />
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
