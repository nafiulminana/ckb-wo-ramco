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
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/dwr/interface/WorkOrder.js"></script>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/dwr/engine.js"></script>
        <script type="text/javascript">

            function getDetail(pk) {
                if (isNaN(pk)) {
                    var def = "---";
                    document.getElementById('aggNo').innerHTML = def;
                    document.getElementById('aggDate').innerHTML = def;
                    return;
                }
                WorkOrder.getRateAggreement(pk, {
                    callback: function (result) {
                        document.getElementById('aggNo').innerHTML = result;
                    }
                });
                WorkOrder.getRateAggreementDateString(pk, {
                    callback: function (result) {
                        document.getElementById('aggDate').innerHTML = result;
                    }
                });
            }

            function getStatusRate() {

                var e = document.getElementById("vendorrateid");
                var str = e.options[e.selectedIndex].text;
                var arrStr = str.split("||");
                str = arrStr[0];
                var marks = str.charAt(str.length - 4); 
                
                if (marks === ",") {
                    str = str.replace(/\./g, '');
                    str = str.replace(/\,/g, '.');
                } else {
                    str = str.replace(/\,/g, '');
                }
                
                str = str.replace(/@IDR/g, '');
                str = parseFloat(str);
                
                if (str === getLowestRate()) {
                    document.getElementById('reasonlabelid').style.display = 'none';
                    document.getElementById('reasonreadid').value = 'N';
                } else {
                    document.getElementById('reasonlabelid').style.display = 'block';
                    document.getElementById('reasonreadid').value = 'Y';
                }

            }
            var arrRates = new Array();

            function getLowestRate() {
                var smallest = arrRates[0];
                for (var i = 0; i < arrRates.length; i++) {
                    if (arrRates[i] < smallest) {
                        smallest = arrRates[i];
                    }
                }
                return smallest;
            }

            function documentLoad() {

            <c:forEach var="vendorGrp" items="${requestScope['vendors']}">

                <c:forEach var="vendor" items="${vendorGrp.value}">
                addRates(${vendor.rate});
                </c:forEach>
            </c:forEach>
                getStatusRate();
                // alert(getLowestRate());
            }
            function addRates(rates) {
//                alert(rates);
                arrRates.push(rates);
            }
        </script>
    </head>
    <body onload="documentLoad();">
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
                                                                                            <select name="vendorrate" id="vendorrateid" onchange="getDetail(this.options[this.selectedIndex].value);
                                                                                                    getStatusRate();">
                                                                                                <option value="---">Select Vendor</option>
                                                                                                <c:forEach var="vendorGrp" items="${requestScope['vendors']}">
                                                                                                    <optgroup label="${vendorGrp.key}">
                                                                                                        <c:forEach var="vendor" items="${vendorGrp.value}">
                                                                                                            <option value="${vendor.tvendorservicePk}" ${vendor.tvendorservicePk==sessionScope['wo'].tvendorserviceFk?"selected":""}>@${vendor.currency.currencycode} <fmt:formatNumber groupingUsed="true"  minFractionDigits="2" maxFractionDigits="2" value="${vendor.rate}"/> || ${vendor.remarks}</option>
                                                                                                        </c:forEach>
                                                                                                    </optgroup>
                                                                                                </c:forEach>
                                                                                            </select>
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
                                                                                            Qty <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="text" name="qty" value="${sessionScope['smdQty']}" size="4" maxlength="4" name="qty"/>
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
                                                                                        <td>
                                                                                            Reason
                                                                                        </td>
                                                                                        <td id='reasonlabelid'>
                                                                                            <select name="reasonover" id="reasonoverid">
                                                                                                <option value="Availability">Availability</option>
                                                                                                <option value="Past Performance">Past Performance</option>
                                                                                                <option value="Specification">Specification</option>
                                                                                            </select>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type='hidden' name='reasonread' id='reasonreadid'/>
                                                                                        </td>
                                                                                    </tr>                                                                                    
                                                                                    <tr>
                                                                                        <td colspan="2" align="center">
                                                                                            <input type="button" value="Back To Main" onclick="document.location.href = '<%= application.getContextPath()%>/main.jsp'" /><input type="button" value="Restart" onclick="document.location.href = 'workorder.do'" /><input type="submit" value="Back" name="regularvendorBack"/><input type="submit" value="Next" name="regularvendorNext" />
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
