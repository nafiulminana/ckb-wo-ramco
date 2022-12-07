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
        <script type='text/javascript' src='<%=getServletContext().getContextPath()%>/dwr/interface/WorkOrder.js'></script>
        <script type='text/javascript' src='<%=getServletContext().getContextPath()%>/dwr/engine.js'></script>
        <link rel="stylesheet" href="<%=getServletContext().getContextPath()%>/scripts/jquery-ui.css">
        <script src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.12.4.js"></script>
        <script src="<%=getServletContext().getContextPath()%>/scripts/jquery-ui.js"></script>
        <script type="text/javascript">
            function getSmd(mode) {
                if (isNaN(mode)) {
                    var def = "<option value='---'>Select Service Mode First</option>";
            <%--document.getElementById('containerMode').innerHTML=def;--%>
                        $('#containerMode').html(def);
                        return;
                    }
                    WorkOrder.getContainerList(mode, {
                        callback: function(result) {
            <%--alert(result);--%>
            <%--document.getElementById('containerMode').innerHTML=result;--%>
                            $('#containerMode').html(result);
            <%--alert(document.getElementById('containerMode').innerHTML);--%>
                        }, async: false
                    });
            <%--WorkOrder.getDeliveryTerms(mode,{
                callback:function(result){
                    document.getElementById('deliveryTerm').innerHTML=result;
                }
            });--%>
                }
            $( function() {
                $( "#actionDate" ).datepicker({
                    dateFormat: 'MM d, yy',
                    altField: "#isoDate",
                    altFormat: 'yy-mm-dd',
                    selectDefaultDate: true 
                });
            } );
            
            $(document).ready(function(){
                                                                           
                var availableTags = [];
                        <c:forEach var="origin" items="${requestScope['location']}">
                                    availableTags.push('<c:out value="${origin.tlocationPk}-${origin.locationname} "></c:out>');
                        </c:forEach>
                    $("#destination1").autocomplete({
                        source: availableTags,
                        minLength: 2,
                        select : function (event, ui){
                            var selectedObj = ui.item;
                            var pk = selectedObj.value;
                            var des = pk.split("-");
                            $("#destination1").val(selectedObj);
                            $("#destination").val(des[0]);
                        }
                    });
                    $("#origin1").autocomplete({
                        source: availableTags,
                        minLength: 2,
                        select : function (event, ui){
                            var selectedObj = ui.item;
                            var pk = selectedObj.value;
                            var des = pk.split("-");
                            $("#origin1").val(selectedObj);
                            $("#origin").val(des[0]);
                        }
                    });
                });        
               
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
                                                                            <u><h1>Step 2: ${ sessionScope['wo'].wostatus == "EDIT" ? "Edit" : sessionScope['wo'].wostatus == "REVISION" ? "Revision" : "Create"} Regular Delivery Work Order - Enter Work Order Information</h1></u>
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
                                                                                            <c:if test="${sessionScope['wo'].editreason!=null}">
                                                                                                <div style="border:dotted black thin">
                                                                                                    <b>Edit Reason:</b><br/>${sessionScope['wo'].editreason}
                                                                                                    <br/><input type="submit" value="Setup Additional Cost" name="adhocManifestBack" />
                                                                                                </div>
                                                                                            </c:if>
                                                                                        </td>
                                                                                    </tr>
                                                                                    
                                                                                    <tr>
                                                                                        <td>
                                                                                            Origin <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input name="origin1" id="origin1" size="100" value="${sessionScope['wo'].origintlocationFk}${sessionScope['locationfrom']!=null && sessionScope['wo'].origintlocationFk !=null ?"-":""}${sessionScope['locationfrom']!=null && sessionScope['wo'].origintlocationFk !=null ?sessionScope['locationfrom']:requestScope['locationfromdesc']}"/>
                                                                                            <input  type="hidden" name="origin" id="origin" size="10" value="${sessionScope['wo'].origintlocationFk}"/>
                                                                                        </td>
                                                                                        
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Destination <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input name="destination1" id="destination1" size="100" value="${sessionScope['wo'].destinationtlocationFk}${sessionScope['locationdest']!=null && sessionScope['wo'].destinationtlocationFk != null?"-":""}${sessionScope['locationdest']!=null&& sessionScope['wo'].destinationtlocationFk != null?sessionScope['locationdest']:requestScope['locationdestdesc']}"/>
                                                                                            <input type="hidden" name="destination" id="destination" size="10" value="${sessionScope['wo'].destinationtlocationFk}"/>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Service Mode <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select name="serviceMode" id="serviceMode" onchange="getSmd(this.options[this.selectedIndex].value)">
                                                                                                <option value="---">Select Mode</option>
                                                                                                <c:forEach var="mode" items="${requestScope['serviceMode']}">
                                                                                                    <option value="${mode.tservicemodePk}" ${mode.tservicemodePk==sessionScope['wo'].tservicemodeFk?"selected":""}>${mode.smodename}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Service <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select name="containerMode" id="containerMode">
                                                                                                <option value='---'>Select Service Mode First</option>

                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Delivery Term <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <select name="deliveryTerm" id="deliveryTerm">
                                                                                                <option value='---'>Select Service Mode First</option>
                                                                                                <c:forEach var="dterm" items="${requestScope['deliveryTerm']}">
                                                                                                    <option value="${dterm.tdeliverytermPk}" ${dterm.tdeliverytermPk==sessionScope['wo'].tdeliverytermFk?"selected":""}>${dterm.dtname}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Rate Type <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <c:if test="${not empty orders}">
                                                                                                <c:forEach var="orderType" items="${orders}">
                                                                                                    <label><input type="radio" name="orderType" value="${orderType.torderPk}" ${sessionScope.wo.torderFk==orderType.torderPk?'checked':''}>${orderType.ordername}</label>
                                                                                                </c:forEach>
                                                                                            </c:if>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                            Action Date (yyyy-mm-dd) <span style="color: red">*</span>
                                                                                        </td>
                                                                                        <td>
                                                                                            <input type="text" id="actionDate" value="<fmt:formatDate pattern="MMMM d, yyyy" value="${sessionScope['wo'].executiondate}"/>" class="datepicker" onblur="document.getElementById('isoDate').value = this.value"/>
                                                                                            <input type="hidden" name="actionDate" id="isoDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope['wo'].executiondate}"/>"/>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="2" align="center">
                                                                                            <input type="button" value="Back To Main" onclick="document.location.href = '<%= application.getContextPath()%>/main.jsp'" /><input type="button" value="Restart" onclick="document.location.href = 'workorder.do'" /><input type="button" value="Back" onclick="location.href = 'workorder.do'"/><input type="submit" value="Next" name="regularNext"/>
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
        <c:if test="${sessionScope['wo'].tservicemodeFk!=null}">
            <script type="text/javascript"> getSmd(${sessionScope['wo'].tservicemodeFk})</script>
        </c:if>
    </body>
</html>
